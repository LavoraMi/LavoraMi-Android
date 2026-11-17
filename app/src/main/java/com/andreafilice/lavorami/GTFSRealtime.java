package com.andreafilice.lavorami;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GTFSRealtime {
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    private static final long MIN_INTERVAL_MS = 8_000;

    private static final Map<String, Snapshot> cache = new HashMap<>();
    private static final Map<String, List<Callback>> pending = new HashMap<>();

    public static class Prediction {
        public final Integer delay;
        public final Long time;
        public final boolean skipped;

        Prediction(Integer delay, Long time, boolean skipped) {
            this.delay = delay;
            this.time = time;
            this.skipped = skipped;
        }
    }

    public static class Snapshot {
        final long fetchedAtMs;
        final long feedTimestampMs;
        final Map<String, Map<String, Prediction>> predictions;
        final Set<String> canceledTrips;

        Snapshot(long fetchedAtMs, long feedTimestampMs, Map<String, Map<String, Prediction>> predictions, Set<String> canceledTrips) {
            this.fetchedAtMs = fetchedAtMs;
            this.feedTimestampMs = feedTimestampMs;
            this.predictions = predictions;
            this.canceledTrips = canceledTrips;
        }

        public boolean isFresh() {
            long now = System.currentTimeMillis();
            return now - fetchedAtMs < 60_000 && now - feedTimestampMs < 180_000;
        }
        public boolean isCanceled(String tripId) {return tripId != null && canceledTrips.contains(tripId.toLowerCase(Locale.ROOT));}

        public Prediction prediction(String tripId, String stopId) {
            if (tripId == null || stopId == null) return null;
            Map<String, Prediction> perStop = predictions.get(tripId.toLowerCase(Locale.ROOT));
            return perStop == null ? null : perStop.get(stopId.toLowerCase(Locale.ROOT));
        }

        public int tripCount() {return predictions.size();}
        public long ageSeconds() {return (System.currentTimeMillis() - feedTimestampMs) / 1000;}
    }

    public interface Callback {
        void onResult(Snapshot snapshot);
    }

    public static synchronized void load(String urlString, Callback callback) {
        Snapshot cached = cache.get(urlString);
        if (cached != null && System.currentTimeMillis() - cached.fetchedAtMs < MIN_INTERVAL_MS) {
            callback.onResult(cached);
            return;
        }

        List<Callback> waiters = pending.get(urlString);
        if (waiters != null) {
            waiters.add(callback);
            return;
        }

        List<Callback> newWaiters = new ArrayList<>();
        newWaiters.add(callback);
        pending.put(urlString, newWaiters);

        EXECUTOR.execute(() -> {
            Snapshot snap = null;
            try {snap = download(urlString);}
            catch (Exception ignored) {}

            List<Callback> toNotify;
            synchronized (GTFSRealtime.class) {
                if (snap != null) cache.put(urlString, snap);
                toNotify = pending.remove(urlString);
            }

            if (toNotify != null) for (Callback cb : toNotify) cb.onResult(snap);
        });
    }

    private static Snapshot download(String urlString) throws Exception {
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) new URL(urlString).openConnection();
            conn.setConnectTimeout(8_000);
            conn.setReadTimeout(10_000);
            conn.setUseCaches(false);
            conn.setRequestProperty("X-BusOne-Client", "LavoraMi/Android;info@lavorami.it");
            conn.setRequestProperty("X-BusOne-Use", "personal");

            if (conn.getResponseCode() / 100 != 2) throw new IOException("HTTP " + conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);

            return parse(new JSONObject(sb.toString()));
        }
        finally {if (conn != null) conn.disconnect();}
    }

    private static Snapshot parse(JSONObject root) {
        long headerTs = 0;
        JSONObject header = root.optJSONObject("header");

        if (header != null) headerTs = header.optLong("timestamp", 0);

        Map<String, Map<String, Prediction>> predictions = new HashMap<>();
        Set<String> canceled = new HashSet<>();

        JSONArray entities = root.optJSONArray("entity");
        if (entities != null) {
            for (int i = 0; i < entities.length(); i++) {
                JSONObject entity = entities.optJSONObject(i);
                if (entity == null) continue;

                JSONObject tripUpdate = entity.optJSONObject("trip_update");
                if (tripUpdate == null) continue;

                JSONObject trip = tripUpdate.optJSONObject("trip");
                if (trip == null) continue;

                String tripId = trip.optString("trip_id", "").toLowerCase(Locale.ROOT);
                if (tripId.isEmpty()) continue;

                if ("CANCELED".equals(trip.optString("schedule_relationship", ""))) {
                    canceled.add(tripId);
                    continue;
                }

                Map<String, Prediction> perStop = new HashMap<>();
                JSONArray updates = tripUpdate.optJSONArray("stop_time_update");
                if (updates != null) {
                    for (int j = 0; j < updates.length(); j++) {
                        JSONObject update = updates.optJSONObject(j);
                        if (update == null) continue;

                        String stopId = update.optString("stop_id", "").toLowerCase(Locale.ROOT);
                        String rel = update.optString("schedule_relationship", "");
                        if (stopId.isEmpty() || "NO_DATA".equals(rel)) continue;

                        boolean skipped = "SKIPPED".equals(rel);

                        JSONObject event = update.optJSONObject("departure");
                        if (!hasValue(event)) event = update.optJSONObject("arrival");

                        Integer delay = (event != null && event.has("delay")) ? event.optInt("delay") : null;
                        Long time = (event != null && event.has("time")) ? event.optLong("time") : null;

                        if (skipped || delay != null || time != null)
                            perStop.put(stopId, new Prediction(delay, time, skipped));
                    }
                }
                if (!perStop.isEmpty()) predictions.put(tripId, perStop);
            }
        }

        return new Snapshot(System.currentTimeMillis(), headerTs * 1000L, predictions, canceled);
    }

    private static boolean hasValue(JSONObject event) {return event != null && (event.has("delay") || event.has("time"));}
}