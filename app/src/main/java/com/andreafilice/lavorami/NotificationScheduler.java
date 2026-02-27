package com.andreafilice.lavorami;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class NotificationScheduler {

    private static final String TAG = "Scheduler_Log";

    public static void scheduleWorkNotifications(Context context, ArrayList<EventDescriptor> eventList) {
        boolean isEnabled = DataManager.getBoolData(context, DataKeys.KEY_NOTIFICATION_SWITCH, true);

        if (!isEnabled) return;

        Set<String> favorites = DataManager.getStringArray(context, DataKeys.KEY_FAVORITE_LINES, null);
        if (favorites == null || favorites.isEmpty()) return;
        if (eventList == null || eventList.isEmpty()) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        boolean notifyStart = DataManager.getBoolData(context, DataKeys.KEY_NOTIFICATION_STARTWORKS, true);
        boolean notifyEnd = DataManager.getBoolData(context, DataKeys.KEY_NOTIFICATION_ENDWORKS, true);

        for (EventDescriptor event : eventList) {
            boolean isMatch = false;

            if (event.lines != null) {

                for (String line : event.lines) {
                    if (favorites.contains(line)) {
                        isMatch = true;
                        break;
                    }
                }

                if (!isMatch) {
                    for (String fav : favorites) {
                        if (isMatch) break;

                        switch (fav) {
                            case "Metro":
                                for (String line : event.lines) {
                                    if (line.matches("(?i)M[1-5].*")) { isMatch = true; break; }
                                }
                                break;
                            case "S":
                                for (String line : event.lines) {
                                    if (line.matches("(?i)^S\\d+$")) { isMatch = true; break; }
                                }
                                break;
                            case "R":
                                for (String line : event.lines) {
                                    if (line.matches("(?i)^R\\d+$")) { isMatch = true; break; }
                                }
                                break;
                            case "RE":
                                for (String line : event.lines) {
                                    if (line.matches("(?i)^RE\\d+$")) { isMatch = true; break; }
                                }
                                break;
                            case "Tram":
                                for (String line : event.lines) {
                                    if (line.matches("^([1-9]|[1-2][0-9]|3[0-3])$")) { isMatch = true; break; }
                                }
                                break;
                            case "z5":
                                for (String line : event.lines) {
                                    if (line.toLowerCase().startsWith("z5")) { isMatch = true; break; }
                                }
                                break;
                            case "z6":
                                for (String line : event.lines) {
                                    if (line.toLowerCase().startsWith("z6")) { isMatch = true; break; }
                                }
                                break;
                            case "Autoguidovie":
                                if (event.company != null && event.company.toLowerCase().contains("autoguidovie")) {
                                    isMatch = true;
                                }
                                break;
                            case "Bus":
                                for (String line : event.lines) {
                                    boolean isTram  = line.matches("^([1-9]|[1-2][0-9]|3[0-3])$");
                                    boolean isMetro = line.matches("(?i)M[1-5].*");
                                    boolean isTreno = line.matches("(?i)^(S|R|RE|RV).*");
                                    if (!isTram && !isMetro && !isTreno) { isMatch = true; break; }
                                }
                                break;
                        }
                    }
                }
            }

            if (isMatch) {
                long startMillis = event.getDateMillis(event.startDate);
                long endMillis = event.getDateMillis(event.endDate);
                long now = System.currentTimeMillis();
                long oneDayMillis  = 24L * 60 * 60 * 1000;

                int baseId = (event.roads + event.startDate + event.endDate + event.getStringLines()).hashCode();
                int idStart = baseId * 10;
                int idPreStart = baseId * 10 + 1;
                int idEnd = baseId * 10 + 2;
                int idPreEnd = baseId * 10 + 3;

                if (notifyStart) {
                    if (startMillis > now) {
                        long notifTime = getSelectedTime(context, startMillis);
                        if (notifTime > now) {
                            schedule(context, alarmManager, idStart, notifTime,
                                    "Lavori Iniziati!",
                                    String.format("I lavori in %s delle linee %s sono iniziati oggi. Consulta il sito di %s per maggiori info.",
                                            event.roads, event.getStringLines(), event.company));
                        }
                    }
                    long startDayBefore = startMillis - oneDayMillis;
                    if (startDayBefore > now) {
                        long notifTimePre = getSelectedTime(context, startDayBefore);
                        if (notifTimePre > now) {
                            schedule(context, alarmManager, idPreStart, notifTimePre,
                                    "⚠\uFE0F I lavori iniziano domani!",
                                    String.format("Domani iniziano i lavori in %s per %s. Consulta il sito di %s per maggiori info.",
                                            event.roads, event.getStringLines(), event.company));
                        }
                    }
                }

                if (notifyEnd) {
                    if (endMillis > now) {
                        long notifTimeEnd = getSelectedTime(context, endMillis);
                        if (notifTimeEnd > now) {
                            schedule(context, alarmManager, idEnd, notifTimeEnd,
                                    "Lavori terminati!",
                                    String.format("I lavori in %s delle linee %s dovrebbero terminare oggi. Consulta il sito di %s per gli ultimi aggiornamenti.",
                                            event.roads, event.getStringLines(), event.company));
                        }
                    }
                    long endDayBefore = endMillis - oneDayMillis;
                    if (endDayBefore > now) {
                        long notifTimePreEnd = getSelectedTime(context, endDayBefore);
                        if (notifTimePreEnd > now) {
                            schedule(context, alarmManager, idPreEnd, notifTimePreEnd,
                                    "⚠\uFE0F I lavori finiscono domani!",
                                    String.format("Domani terminano i lavori in %s per %s. Consulta il sito di %s per maggiori info.",
                                            event.roads, event.getStringLines(), event.company));
                        }
                    }
                }
            }
        }
    }

    public static void scheduleStrikeNotification(Context context, StrikeDescriptor strike) {
        boolean isEnabled = DataManager.getBoolData(context, DataKeys.KEY_NOTIFICATION_SWITCH, true);
        if (!isEnabled) return;

        boolean notifyStrikes = DataManager.getBoolData(context, DataKeys.KEY_NOTIFICATION_STRIKES, true);
        if (!notifyStrikes) return;

        if (strike == null || strike.getStrikeDate() == null) return;

        long strikeMillis = parseDateMillis(strike.getStrikeDate());
        if (strikeMillis < 0) {
            Log.e(TAG, "Data sciopero non valida: " + strike.getStrikeDate());
            return;
        }

        long now = System.currentTimeMillis();
        long oneDayMillis = 24L * 60 * 60 * 1000;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        int baseId = (strike.getStrikeDate() + strike.getStrikeCompanies() + strike.getStrikeGuaranteed()).hashCode();
        int idStrike = baseId * 10 + 20;
        int idPreStrike = baseId * 10 + 21;

        if (strikeMillis > now) {
            long notifTime = getSelectedTime(context, strikeMillis);
            if (notifTime > now) {
                schedule(context, alarmManager, idStrike, notifTime,
                        "\uD83D\uDEAB Oggi Sciopero!",
                        String.format("Oggi è previsto uno sciopero di %s, le fascie garantite %s",
                                strike.getStrikeCompanies(), strike.getStrikeGuaranteed()));
            }
        }

        long strikeDayBefore = strikeMillis - oneDayMillis;
        if (strikeDayBefore > now) {
            long notifTimePre = getSelectedTime(context, strikeDayBefore);
            if (notifTimePre > now) {
                schedule(context, alarmManager, idPreStrike, notifTimePre,
                        "⚠\uFE0F Sciopero domani!",
                        String.format("Domani c'è sciopero per %s, le fascie garantite %s",
                                strike.getStrikeCompanies(), strike.getStrikeGuaranteed()));
            }
        }
    }

    private static long parseDateMillis(String dateStr) {
        String[] formats = { "yyyy-MM-dd", "dd/MM/yyyy", "dd-MM-yyyy" };
        for (String fmt : formats) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmt, Locale.getDefault());

                simpleDateFormat.setLenient(false);
                Date date = simpleDateFormat.parse(dateStr);

                if (date != null)
                    return date.getTime();
            }
            catch (ParseException ignored) { }
        }
        return -1;
    }


    @SuppressLint("ScheduleExactAlarm")
    private static void schedule(Context context, AlarmManager am, int id, long time, String title, String msg) {
        try {
            Intent intent = new Intent(context, NotificationReceiver.class);
            intent.putExtra("title", title);
            intent.putExtra("message", msg);
            intent.putExtra("id", id);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (am.canScheduleExactAlarms())
                    am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
                else
                    am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
            else
                am.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);

            Log.d(TAG, "Schedulato ID=" + id + " | " + title + " | " + msg);

        } catch (SecurityException se) {
            Log.e(TAG, "Errore Permessi (SecurityException): " + se.getMessage());
            try {
                Intent intentFallback = new Intent(context, NotificationReceiver.class);
                intentFallback.putExtra("title", title);
                intentFallback.putExtra("message", msg);
                intentFallback.putExtra("id", id);
                PendingIntent piFallback = PendingIntent.getBroadcast(context, id, intentFallback,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                am.set(AlarmManager.RTC_WAKEUP, time, piFallback);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        catch (Exception e) {
            Log.e(TAG, "Errore: " + e.getMessage());
        }
    }

    private static long getSelectedTime(Context context, long eventDateMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(eventDateMillis);
        calendar.set(Calendar.HOUR_OF_DAY, DataManager.getIntData(context, DataKeys.KEY_HOURS_NOTIFICATIONS, 10));
        calendar.set(Calendar.MINUTE, DataManager.getIntData(context, DataKeys.KEY_MINUTES_NOTIFICATIONS, 0));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}