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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class NotificationScheduler {
    private static final String TAG = "Scheduler_Log";

    public static void scheduleWorkNotifications(Context context, ArrayList<EventDescriptor> eventList) {
        boolean isEnabled = DataManager.getBoolData(DataKeys.KEY_NOTIFICATION_SWITCH, true);

        if (!isEnabled) return;

        Set<String> favorites = DataManager.getStringArray(DataKeys.KEY_FAVORITE_LINES, null);
        if (favorites == null || favorites.isEmpty()) return;
        if (eventList == null || eventList.isEmpty()) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        boolean notifyStart = DataManager.getBoolData(DataKeys.KEY_NOTIFICATION_STARTWORKS, true);
        boolean notifyEnd = DataManager.getBoolData(DataKeys.KEY_NOTIFICATION_ENDWORKS, true);

        for (EventDescriptor event : eventList) {
            boolean isMatch = false;

            if (event.lines != null) {
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
                                if (line.matches("(?i)^S\\d+$") && !isLineaTilo(line)) { isMatch = true; break; }
                            }
                            break;
                        case "R":
                            for (String line : event.lines) {
                                if (line.matches("(?i)^R\\d+$") && !isLineaTilo(line)) { isMatch = true; break; }
                            }
                            break;
                        case "RE":
                            for (String line : event.lines) {
                                if (line.matches("(?i)^RE\\d+$") && !isLineaTilo(line)) { isMatch = true; break; }
                            }
                            break;
                        case "Tram":
                            for (String line : event.lines) {
                                if (line.matches("^([1-9]|[1-2][0-9]|3[0-3])$")) { isMatch = true; break; }
                            }
                            break;
                        case "Tilo":
                            for (String line : event.lines) {
                                if (isLineaTilo(line)) { isMatch = true; break; }
                            }
                            break;
                        case "z55":
                            for (String line : event.lines) {
                                if (line.toLowerCase().startsWith("z55") || line.toLowerCase().startsWith("z56")) { isMatch = true; break; }
                            }
                            break;
                        case "z50":
                            for (String line : event.lines) {
                                if (line.toLowerCase().startsWith("z50") || line.toLowerCase().startsWith("z51")) { isMatch = true; break; }
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
                        long notifTime = getSelectedTime(startMillis);
                        Log.d(TAG, String.format("Schedulato: %s linee/a %s", event.roads, event.getStringLines()));
                        if (notifTime > now) {
                            if(isViaChecked(event.roads.toLowerCase())) {
                                if(event.getLines().length <= 1)
                                    schedule(context, alarmManager, idStart, notifTime,
                                            context.getString(R.string.startWorkNotificationTitle),
                                            String.format(context.getString(R.string.startWorkNotificationVar1),
                                                    event.roads, event.getStringLines(), event.company));
                                else
                                    schedule(context, alarmManager, idStart, notifTime,
                                            context.getString(R.string.startWorkNotificationTitle),
                                            String.format(context.getString(R.string.startWorkNotificationVar2),
                                                    event.roads, event.getStringLines(), event.company));
                            }
                            else {
                                if(event.getLines().length <= 1) {
                                    schedule(context, alarmManager, idStart, notifTime,
                                            context.getString(R.string.startWorkNotificationTitle),
                                            String.format(context.getString(R.string.startWorkNotificationVar3),
                                                    event.roads, event.getStringLines(), event.company));
                                }
                                else
                                    schedule(context, alarmManager, idStart, notifTime,
                                            context.getString(R.string.startWorkNotificationTitle),
                                            String.format(context.getString(R.string.startWorkNotificationVar4),
                                                    event.roads, event.getStringLines(), event.company));
                            }
                        }
                    }
                    long startDayBefore = startMillis - oneDayMillis;
                    if (startDayBefore > now) {
                        long notifTimePre = getSelectedTime(startDayBefore);
                        Log.d(TAG, String.format("Schedulato: %s linee/a %s", event.roads, event.getStringLines()));
                        if (notifTimePre > now) {
                            if(isViaChecked(event.roads.toLowerCase())) {
                                if(event.getLines().length <= 1)
                                    schedule(context, alarmManager, idPreStart, notifTimePre,
                                            context.getString(R.string.startWorkTomorrowNotificationTitle),
                                            String.format(context.getString(R.string.startWorkTomorrowVar1),
                                                    event.roads, event.getStringLines(), event.company));
                                else
                                    schedule(context, alarmManager, idPreStart, notifTimePre,
                                            context.getString(R.string.startWorkTomorrowNotificationTitle),
                                            String.format(context.getString(R.string.startWorkTomorrowVar2),
                                                    event.roads, event.getStringLines(), event.company));
                            }
                            else {
                                if(event.getLines().length <= 1)
                                    schedule(context, alarmManager, idPreStart, notifTimePre,
                                            context.getString(R.string.startWorkTomorrowNotificationTitle),
                                            String.format(context.getString(R.string.startWorkTomorrowVar3),
                                                    event.roads, event.getStringLines(), event.company));
                                else
                                    schedule(context, alarmManager, idPreStart, notifTimePre,
                                            context.getString(R.string.startWorkTomorrowNotificationTitle),
                                            String.format(context.getString(R.string.startWorkTomorrowVar4),
                                                    event.roads, event.getStringLines(), event.company));
                            }
                        }
                    }
                }

                if (notifyEnd) {
                    if (endMillis > now) {
                        long notifTimeEnd = getSelectedTime(endMillis);
                        Log.d(TAG, String.format("Schedulato: %s linee/a %s", event.roads, event.getStringLines()));
                        if (notifTimeEnd > now) {
                            if(isViaChecked(event.roads.toLowerCase())) {
                                if(event.getLines().length <= 1)
                                    schedule(context, alarmManager, idEnd, notifTimeEnd,
                                            context.getString(R.string.endWorksNotificationTitle),
                                            String.format(context.getString(R.string.endWorksNotificationDepsVar1),
                                                    event.roads, event.getStringLines(), event.company));
                                else
                                    schedule(context, alarmManager, idEnd, notifTimeEnd,
                                            context.getString(R.string.endWorksNotificationTitle),
                                            String.format(context.getString(R.string.endWorksNotificationDepsVar2),
                                                    event.roads, event.getStringLines(), event.company));
                            }
                            else {
                                if(event.getLines().length <= 1)
                                    schedule(context, alarmManager, idEnd, notifTimeEnd,
                                            context.getString(R.string.endWorksNotificationTitle),
                                            String.format(context.getString(R.string.endWorksNotificationDepsVar3),
                                                    event.roads, event.getStringLines(), event.company));
                                else
                                    schedule(context, alarmManager, idEnd, notifTimeEnd,
                                            context.getString(R.string.endWorksNotificationTitle),
                                            String.format(context.getString(R.string.endWorksNotificationDepsVar4),
                                                    event.roads, event.getStringLines(), event.company));
                            }
                        }
                    }
                    long endDayBefore = endMillis - oneDayMillis;
                    if (endDayBefore > now) {
                        long notifTimePreEnd = getSelectedTime(endDayBefore);
                        Log.d(TAG, String.format("Schedulato: %s linee/a %s", event.roads, event.getStringLines()));
                        if (notifTimePreEnd > now) {
                            if(isViaChecked(event.roads.toLowerCase())) {
                                if(event.getLines().length <= 1)
                                    schedule(context, alarmManager, idPreEnd, notifTimePreEnd,
                                            context.getString(R.string.endWorksTomorrowTitle),
                                            String.format(context.getString(R.string.endWorksTomorrowNotificationDepsVar1),
                                                    event.roads, event.getStringLines(), event.company));
                                else
                                    schedule(context, alarmManager, idPreEnd, notifTimePreEnd,
                                            context.getString(R.string.endWorksTomorrowTitle),
                                            String.format(context.getString(R.string.endWorksTomorrowNotificationDepsVar2),
                                                    event.roads, event.getStringLines(), event.company));
                            }
                            else {
                                if(event.getLines().length <= 1)
                                    schedule(context, alarmManager, idPreEnd, notifTimePreEnd,
                                            context.getString(R.string.endWorksTomorrowTitle),
                                            String.format(context.getString(R.string.endWorksTomorrowNotificationDepsVar3),
                                                    event.roads, event.getStringLines(), event.company));
                                else
                                    schedule(context, alarmManager, idPreEnd, notifTimePreEnd,
                                            context.getString(R.string.endWorksTomorrowTitle),
                                            String.format(context.getString(R.string.endWorksTomorrowNotificationDepsVar4),
                                                    event.roads, event.getStringLines(), event.company));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void scheduleStrikeNotification(Context context, StrikeDescriptor strike) {
        boolean isEnabled = DataManager.getBoolData(DataKeys.KEY_NOTIFICATION_SWITCH, true);
        if (!isEnabled) return;

        boolean notifyStrikes = DataManager.getBoolData(DataKeys.KEY_NOTIFICATION_STRIKES, true);
        if (!notifyStrikes) return;

        if (strike == null || strike.getStrikeDate() == null) return;

        long strikeMillis = parseStrikeDateMillis(strike.getStrikeDate());
        if (strikeMillis < 0) {
            Log.e(TAG, "Data sciopero non valida: " + strike.getStrikeDate());
            return;
        }

        long now = System.currentTimeMillis();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        int baseId = (strike.getStrikeDate() + strike.getStrikeCompanies() + strike.getStrikeGuaranteed()).hashCode();
        int idStrike = baseId * 10 + 20;
        int idPreStrike = baseId * 10 + 21;

        Calendar calStrike = Calendar.getInstance();
        calStrike.setTimeInMillis(strikeMillis);
        calStrike.set(Calendar.HOUR_OF_DAY, 7);
        calStrike.set(Calendar.MINUTE, 0);
        calStrike.set(Calendar.SECOND, 0);

        long notifTimeStrike = calStrike.getTimeInMillis();
        if (notifTimeStrike > now) {
            schedule(context, alarmManager, idStrike, notifTimeStrike,
                    context.getString(R.string.strikeNotificationTitle),
                    String.format(context.getString(R.string.strikeNotificationDeps),
                            strike.getStrikeCompanies(), strike.getStrikeGuaranteed()));
        }

        Calendar calPreStrike = Calendar.getInstance();
        calPreStrike.setTimeInMillis(strikeMillis);
        calPreStrike.add(Calendar.DAY_OF_MONTH, -1);
        calPreStrike.set(Calendar.HOUR_OF_DAY, 18);
        calPreStrike.set(Calendar.MINUTE, 0);
        calPreStrike.set(Calendar.SECOND, 0);

        long notifTimePreStrike = calPreStrike.getTimeInMillis();
        if (notifTimePreStrike > now) {
            schedule(context, alarmManager, idPreStrike, notifTimePreStrike,
                    context.getString(R.string.strikeTomorrowNotificationTitle),
                    String.format(context.getString(R.string.strikeTomorrowNotificationDeps),
                            strike.getStrikeCompanies(), strike.getStrikeGuaranteed()));
        }
    }

    private static boolean isLineaTilo(String nomeLinea) {
        ArrayList<String> tiloLines = new ArrayList<>(Arrays.asList("S10", "S20", "S30", "S40", "S50", "S90", "RE80"));
        boolean isValid = false;

        for(int i = 0; i < tiloLines.size(); i++){
            if (tiloLines.get(i).equalsIgnoreCase(nomeLinea)) isValid = true;
        }

        return nomeLinea != null && isValid;
    }

    private static boolean isViaChecked(String value) {
        /// In this method, we check if we had to insert "in" or "a" word when displaying a Notification.
        /// @PARAMETERS
        /// String value is the value passed lowercased.

        return value.contains("via") || value.contains("corso") || value.contains("largo");
    }

    private static long parseDateMillis(String dateStr) {
        String[] formats = { "yyyy-MM-dd", "dd/MM/yyyy", "dd-MM-yyyy" };

        for (String format : formats) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());

                simpleDateFormat.setLenient(false);
                Date date = simpleDateFormat.parse(dateStr);

                if (date != null)
                    return date.getTime();
            }
            catch (ParseException ignored) {Log.e(TAG, ignored.getLocalizedMessage());}
        }
        return -1;
    }

    private static long parseStrikeDateMillis(String dateStr) {
        if (dateStr == null) return -1;

        try {
            String[] parts = dateStr.trim().split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day, 0, 0, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            return calendar.getTimeInMillis();
        } catch (Exception e) {
            Log.e(TAG, "Parsing data failed:" + dateStr);
            return -1;
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private static void schedule(Context context, AlarmManager alarmManager, int id, long time, String title, String msg) {
        try {
            Intent intent = new Intent(context, NotificationReceiver.class);
            intent.putExtra("title", title);
            intent.putExtra("message", msg);
            intent.putExtra("id", id);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms())
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
                else
                    alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
            }
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
            else
                alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        }
        catch (SecurityException se) {
            try {
                Intent intentFallback = new Intent(context, NotificationReceiver.class);

                intentFallback.putExtra("title", title);
                intentFallback.putExtra("message", msg);
                intentFallback.putExtra("id", id);

                PendingIntent piFallback = PendingIntent.getBroadcast(context, id, intentFallback, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, time, piFallback);
            }
            catch (Exception e2) {e2.printStackTrace();}
        }
        catch (Exception e) {Log.e(TAG, "Errore: " + e.getMessage());}
    }

    private static long getSelectedTime(long eventDateMillis) {
        /// In this method, we will get the current selected Time for deliver notifications, setted in Settings > Notifications.
        /// @PARAMETERS
        /// long eventDateMillis is the event date in milliseconds

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(eventDateMillis);
        calendar.set(Calendar.HOUR_OF_DAY, DataManager.getIntData(DataKeys.KEY_HOURS_NOTIFICATIONS, 10));
        calendar.set(Calendar.MINUTE, DataManager.getIntData(DataKeys.KEY_MINUTES_NOTIFICATIONS, 0));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }

    private static long getStrikeNotificationTime(long eventDateMillis) {
        /// In this method, we will get the current selected Time for deliver notifications, but not setted in settings.
        /// @PARAMETERS
        /// long eventDateMillis is the event date in milliseconds

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(eventDateMillis);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }
}