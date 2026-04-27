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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class GTFSHelper {
    public static class GTFSRoute {
        public String route;
        public List<String> headsigns = new ArrayList<>();
        public Map<String, List<String>> services = new HashMap<>();
        public Map<String, GTFSStop> stops = new HashMap<>();
    }

    public static class GTFSStop {
        public String name;
        public Map<String, JSONArray> departuresByDir = new HashMap<>();
    }

    public static class Departure {
        public String time;
        public String headsign;
        public int minutesFromNow;

        public Departure(String time, String headsign, int minutesFromNow) {
            this.time = time;
            this.headsign = headsign;
            this.minutesFromNow = minutesFromNow;
        }
    }

    public interface GTFSCallback {
        void onSuccess(GTFSRoute route);
        void onError();
    }

    public static void load(String urlString, GTFSCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
                    JSONArray dates = svs.getJSONObject(key).getJSONArray("dates");
                    List<String> dateList = new ArrayList<>();
                    for (int i = 0; i < dates.length(); i++) dateList.add(dates.getString(i));
                    route.services.put(key, dateList);
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
            catch (Exception e) {
                callback.onError();
            }
        }).start();
    }

    public static Map<String, List<Departure>> getDepartures(Context context, String stopId, GTFSRoute route, int limit) {
        GTFSStop stop = route.stops.get(stopId);
        if (stop == null) return null;

        Map<String, List<Departure>> result = new HashMap<>();
        String today = todayString();
        int nowMins = nowMinutes();

        List<String> activeServices = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : route.services.entrySet()) {
            if (entry.getValue().contains(today)) activeServices.add(entry.getKey());
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

                    if (!activeServices.contains(serviceId)) continue;

                    String[] parts = timeStr.split(":");
                    int depMins = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
                    int diff = depMins - nowMins;

                    if (diff >= 0) {
                        String headsign = headsignIdx < route.headsigns.size() ? route.headsigns.get(headsignIdx) : context.getString(R.string.unknownErrorToast) ;
                        dirDepartures.add(new Departure(timeStr, headsign, diff));
                    }
                    if (dirDepartures.size() >= limit) break;
                }
                catch (Exception ignored) {}
            }
            if (!dirDepartures.isEmpty()) result.put(dirId, dirDepartures);
        }
        return result.isEmpty() ? null : result;
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
}