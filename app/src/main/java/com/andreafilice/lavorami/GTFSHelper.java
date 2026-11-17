package com.andreafilice.lavorami;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GTFSHelper {
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private static final Map<String, GTFSRoute> routeCache = new HashMap<>();
    private static final Map<String, List<GTFSCallback>> pendingCallbacks = new HashMap<>();

    public static class ServiceInfo {
        public List<String> dates = new ArrayList<>();
        public String daytype = null;
    }

    public static class GTFSRoute {
        public String route;
        public List<String> headsigns = new ArrayList<>();
        public Map<String, ServiceInfo> services = new HashMap<>();
        public Map<String, GTFSStop> stops = new HashMap<>();
    }

    public static class GTFSStop {
        public String name;
        public Map<String, JSONArray> departuresByDir = new HashMap<>();
    }

    public static class Departure {
        public String time;
        public String scheduledTime;
        public String headsign;
        public int minutesFromNow;
        public String tripId;
        public Integer delayMinutes;

        public Departure(String time, String headsign, int minutesFromNow) {
            this(time, time, headsign, minutesFromNow, null, null);
        }

        public Departure(String time, String scheduledTime, String headsign, int minutesFromNow, String tripId, Integer delayMinutes) {
            this.time = time;
            this.scheduledTime = scheduledTime;
            this.headsign = headsign;
            this.minutesFromNow = minutesFromNow;
            this.tripId = tripId;
            this.delayMinutes = delayMinutes;
        }

        public boolean isRealtime() {return delayMinutes != null;}
    }

    public interface GTFSCallback {
        void onSuccess(GTFSRoute route);
        void onError();
    }

    public static void load(String urlString, GTFSCallback callback) {
        EXECUTOR.execute(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(urlString);
                conn = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) sb.append(line);

                JSONObject json = new JSONObject(sb.toString());
                GTFSRoute route = new GTFSRoute();
                route.route = json.getString("route");

                JSONArray hs = json.getJSONArray("headsigns");
                for (int i = 0; i < hs.length(); i++) route.headsigns.add(hs.getString(i));

                JSONObject svs = json.getJSONObject("services");
                Iterator<String> svKeys = svs.keys();
                while (svKeys.hasNext()) {
                    String key = svKeys.next();
                    JSONObject svObj = svs.getJSONObject(key);

                    ServiceInfo info = new ServiceInfo();

                    JSONArray dates = svObj.optJSONArray("dates");
                    if (dates != null) for (int i = 0; i < dates.length(); i++) info.dates.add(dates.getString(i));

                    if (svObj.has("daytype") && !svObj.isNull("daytype"))
                        info.daytype = svObj.getString("daytype");

                    route.services.put(key, info);
                }

                JSONObject stps = json.getJSONObject("stops");
                Iterator<String> stKeys = stps.keys();

                while (stKeys.hasNext()) {
                    String key = stKeys.next();
                    JSONObject sObj = stps.getJSONObject(key);
                    GTFSStop stop = new GTFSStop();
                    stop.name = sObj.getString("n");
                    JSONObject dObj = sObj.getJSONObject("d");
                    Iterator<String> dKeys = dObj.keys();
                    while (dKeys.hasNext()) {
                        String dKey = dKeys.next();
                        stop.departuresByDir.put(dKey, dObj.getJSONArray(dKey));
                    }

                    route.stops.put(key, stop);
                }
                callback.onSuccess(route);
            }
            catch (Exception e) {callback.onError();}
            finally {if(conn != null) conn.disconnect();}
        });
    }

    public static synchronized void loadCached(String lineKey, String urlString, GTFSCallback callback) {
        GTFSRoute cached = routeCache.get(lineKey);
        if (cached != null) {
            callback.onSuccess(cached);
            return;
        }

        List<GTFSCallback> waiters = pendingCallbacks.get(lineKey);
        if (waiters != null) {
            waiters.add(callback);
            return;
        }

        List<GTFSCallback> newWaiters = new ArrayList<>();
        newWaiters.add(callback);
        pendingCallbacks.put(lineKey, newWaiters);

        load(urlString, new GTFSCallback() {
            @Override
            public void onSuccess(GTFSRoute route) {
                List<GTFSCallback> toNotify;
                synchronized (GTFSHelper.class) {
                    routeCache.put(lineKey, route);
                    toNotify = pendingCallbacks.remove(lineKey);
                }
                if (toNotify != null) {
                    for (GTFSCallback cb : toNotify) cb.onSuccess(route);
                }
            }

            @Override
            public void onError() {
                List<GTFSCallback> toNotify;
                synchronized (GTFSHelper.class) {toNotify = pendingCallbacks.remove(lineKey);}
                if (toNotify != null)
                    for (GTFSCallback cb : toNotify) cb.onError();
            }
        });
    }

    public static synchronized void invalidateCache(String lineKey) {routeCache.remove(lineKey);}
    public static synchronized void invalidateAllCache() {routeCache.clear();}
    public static Map<String, List<Departure>> getDepartures(Context context, String stopId, GTFSRoute route, int limit) {return getDepartures(context, stopId, route, limit, null);}

    public static Map<String, List<Departure>> getDepartures(Context context, String stopId, GTFSRoute route, int limit, GTFSRealtime.Snapshot realtime) {
        GTFSStop stop = route.stops.get(stopId);
        if (stop == null) return null;

        Map<String, List<Departure>> result = new HashMap<>();
        String today = todayString();
        String todayType = todayDaytype();
        int nowMins = nowMinutes();

        List<String> activeServices = new ArrayList<>();
        for (Map.Entry<String, ServiceInfo> entry : route.services.entrySet()) {
            ServiceInfo info = entry.getValue();

            boolean active;
            if (info.daytype != null) {
                if (info.daytype.equals("sconosciuto")) {
                    active = false;
                }
                else {
                    active = info.daytype.equals(todayType);
                }
            }
            else {
                active = info.dates.isEmpty() || info.dates.contains(today);
            }

            if (active) activeServices.add(entry.getKey());
        }

        for (Map.Entry<String, JSONArray> entry : stop.departuresByDir.entrySet()) {
            String dirId = entry.getKey();
            JSONArray departuresJson = entry.getValue();
            List<Departure> dirDepartures = new ArrayList<>();

            for (int i = 0; i < departuresJson.length(); i++) {
                try {
                    JSONArray depArray = departuresJson.getJSONArray(i);
                    String timeStr = depArray.getString(0);
                    int headsignIdx = depArray.getInt(1);
                    String serviceId = depArray.getString(2);
                    String tripId = depArray.length() > 3 ? depArray.optString(3, null) : null;

                    if (!activeServices.contains(serviceId)) continue;

                    String[] parts = timeStr.split(":");
                    int depMins = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);

                    Integer delayMin = null;
                    if (tripId != null && realtime != null) {
                        if (realtime.isCanceled(tripId)) continue;

                        GTFSRealtime.Prediction prediction = realtime.prediction(tripId, stopId);
                        if (prediction != null) {
                            if (prediction.skipped) continue;

                            if (prediction.delay != null) delayMin = (int) Math.round(prediction.delay / 60.0);
                            else if (prediction.time != null) {
                                int delayMinutes = romeMinutesOfDay(prediction.time) - depMins;
                                if (Math.abs(delayMinutes) < 720) delayMin = delayMinutes;
                            }
                        }
                    }

                    int effective = depMins + (delayMin != null ? delayMin : 0);
                    int diff = effective - nowMins;
                    if (diff < 0) continue;

                    String headsign = headsignIdx < route.headsigns.size() ? route.headsigns.get(headsignIdx) : context.getString(R.string.unknownErrorToast);
                    dirDepartures.add(new Departure(formatMinutes(effective), formatMinutes(depMins), headsign, diff, tripId, delayMin));
                }
                catch (Exception ignored) {}
            }

            Collections.sort(dirDepartures, (a, b) -> Integer.compare(a.minutesFromNow, b.minutesFromNow));
            if (dirDepartures.size() > limit) dirDepartures = new ArrayList<>(dirDepartures.subList(0, limit));

            if (!dirDepartures.isEmpty()) result.put(dirId, dirDepartures);
        }
        return result.isEmpty() ? null : result;
    }

    private static String formatMinutes(int minutes) {
        int formattedMinutes = ((minutes % 1440) + 1440) % 1440;

        return String.format(Locale.ROOT, "%02d:%02d", formattedMinutes / 60, formattedMinutes % 60);
    }

    private static int romeMinutesOfDay(long epochSeconds) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
        calendar.setTimeInMillis(epochSeconds * 1000L);

        return calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
    }

    private static String todayString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));

        return simpleDateFormat.format(new Date());
    }

    private static int nowMinutes() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
        return cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
    }

    private static String todayDaytype() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
        int dow = cal.get(Calendar.DAY_OF_WEEK);

        if (dow == Calendar.SUNDAY) return "festivo";
        if (dow == Calendar.SATURDAY) return "sabato";
        return "feriale";
    }
}