package com.andreafilice.lavorami;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WidgetLines extends AppWidgetProvider {

    private static final String PREFS_NAME = "lavorami_widget_prefs";
    private static final String PREF_SELECTED_LINE = "selected_line_"; // + appWidgetId
    private static final String PREF_SHOWN_LINES = "shown_lines_"; // + appWidgetId

    public static final String ACTION_SELECT_LINE = "com.andreafilice.lavorami.ACTION_SELECT_LINE";
    public static final String ACTION_BACK_TO_SELECTION = "com.andreafilice.lavorami.ACTION_BACK_TO_SELECTION";
    public static final String EXTRA_LINE_NAME = "extra_line_name";

    public enum LineType {
        METRO("Metro", R.drawable.ic_metro),
        SUBURBAN("Linea S", R.drawable.ic_train),
        REGIO_EXPRESS("Regio Express", R.drawable.ic_train),
        REGIONAL("Regionale", R.drawable.ic_train),
        TILO("TILO", R.drawable.ic_train),
        MXP("Malpensa Express", R.drawable.ic_train),
        TRAM("Tram", R.drawable.ic_tram),
        FILOBUS("Filobus", R.drawable.ic_bus),
        MOVIBUS("Movibus", R.drawable.ic_bus),
        STAR("STAR", R.drawable.ic_bus),
        STAV("STAV", R.drawable.ic_bus),
        AUTOGUIDOVIE("Autoguidovie", R.drawable.ic_bus);

        final String label;
        final int iconRes;

        LineType(String label, int iconRes) {
            this.label = label;
            this.iconRes = iconRes;
        }
    }

    public static class LineInfo {
        public final String code;
        public final LineType type;
        public final int colorRes;

        public LineInfo(String code, LineType type, int colorRes) {
            this.code = code;
            this.type = type;
            this.colorRes = colorRes;
        }
    }

    private static final String[] METRO_LINES = {"M1", "M2", "M3", "M4", "M5"};

    private static final String[] SUBURBAN_LINES = {"S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9",
            "S11", "S12", "S13", "S19", "S31"};

    private static final String[] REGIO_LINES = {"RE1", "RE2", "RE3", "RE4", "RE5", "RE6", "RE7", "RE8", "RE11", "RE13"};

    private static final String[] REGIONAL_LINES = {"R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R11", "R12",
            "R13", "R14", "R15", "R16", "R17", "R18", "R21", "R22", "R23", "R24", "R25", "R27", "R31", "R32", "R33", "R34",
            "R35", "R36", "R37", "R38", "R39", "R40", "R41"};

    private static final String[] TILO_LINES = {"S10", "S20", "S30", "S40", "S50", "S90", "RE80"};

    private static final String[] MXP_LINES = {"MXP1", "MXP2"};

    private static final String[] TRAM_LINES = {"1", "2", "3", "4", "5", "7", "9", "10", "12", "14",
            "15", "16", "19", "24", "27", "31", "33"};

    private static final String[] FILOBUS_LINES = {"90", "91", "92", "93"};

    private static final String[] MOVIBUS_LINES = {"z601", "z602", "z603", "z6C3", "z606", "z611", "z612",
            "z616", "z617", "z618", "z619", "z620", "z621", "z622",
            "z625", "z627", "z636", "z641", "z642", "z643", "z644",
            "z646", "z647", "z649"};

    private static final String[] STAR_LINES = {"z501", "z509", "z510", "z515", "z516"};

    private static final String[] STAV_LINES = {"z551", "z552", "z553", "z554", "z555", "z556",
            "z557", "z559", "z560"};

    private static final String[] AUTOGUIDOVIE_LINES = {"z401", "z402", "z403", "z404", "z405", "z406",
            "z407", "z409", "z410", "z411", "z412", "z413",
            "z415", "z418", "z419", "z420", "z431", "z432",
            "z203", "z205", "z209", "z219", "z221", "z222",
            "z225", "z227", "z228", "z229", "z231", "z232",
            "z233", "z234", "z250", "z251"};

    private static final int[] METRO_COLORS = {
            R.color.M1, R.color.M2, R.color.M3, R.color.M4, R.color.M5
    };

    private static final int[] SUBURBAN_COLORS = {
            R.color.S1, R.color.S2, R.color.S3, R.color.S4, R.color.S5,
            R.color.S6, R.color.S7, R.color.S8, R.color.S9, R.color.S11,
            R.color.S12, R.color.S13, R.color.S19, R.color.S31
    };
    private static final LineType[][] CATEGORY_GROUPS = {
            {LineType.METRO},
            {LineType.SUBURBAN},
            {LineType.REGIO_EXPRESS, LineType.REGIONAL, LineType.TILO, LineType.MXP},
            {LineType.TRAM, LineType.FILOBUS},
            {LineType.MOVIBUS, LineType.STAR, LineType.STAV, LineType.AUTOGUIDOVIE}
    };

    private List<LineInfo> buildAllLines() {
        List<LineInfo> all = new ArrayList<>();

        addAllWithIndividualColors(all, METRO_LINES, LineType.METRO, METRO_COLORS);
        addAllWithIndividualColors(all, SUBURBAN_LINES, LineType.SUBURBAN, SUBURBAN_COLORS);

        addAllWithSharedColor(all, REGIO_LINES, LineType.REGIO_EXPRESS, R.color.RE);
        addAllWithSharedColor(all, REGIONAL_LINES, LineType.REGIONAL, R.color.REGIONAL);
        addAllWithSharedColor(all, TILO_LINES, LineType.TILO, R.color.REGIONAL);
        addAllWithSharedColor(all, MXP_LINES, LineType.MXP, R.color.MXP);
        addAllWithSharedColor(all, TRAM_LINES, LineType.TRAM, R.color.TRAM);
        addAllWithSharedColor(all, FILOBUS_LINES, LineType.FILOBUS, R.color.FILOBUS);
        addAllWithSharedColor(all, MOVIBUS_LINES, LineType.MOVIBUS, R.color.BUS);
        addAllWithSharedColor(all, STAR_LINES, LineType.STAR, R.color.BUS);
        addAllWithSharedColor(all, STAV_LINES, LineType.STAV, R.color.BUS);
        addAllWithSharedColor(all, AUTOGUIDOVIE_LINES, LineType.AUTOGUIDOVIE, R.color.BUS);

        return all;
    }

    private void addAllWithIndividualColors(List<LineInfo> target, String[] codes, LineType type, int[] colors) {
        for (int i = 0; i < codes.length; i++) {
            int colorRes = (i < colors.length) ? colors[i] : R.color.BUS;
            target.add(new LineInfo(codes[i], type, colorRes));
        }
    }

    private void addAllWithSharedColor(List<LineInfo> target, String[] codes, LineType type, int colorRes) {
        for (String code : codes) {
            target.add(new LineInfo(code, type, colorRes));
        }
    }

    private LineInfo findLine(String code) {
        for (LineInfo info : buildAllLines()) {
            if (info.code.equals(code)) return info;
        }
        return null;
    }

    /**Sceglie una linea casuale per ciascun gruppo di CATEGORY_GROUPS,**/
    private List<LineInfo> pickOneLinePerCategory() {
        List<LineInfo> allLines = buildAllLines();
        Random random = new Random();
        List<LineInfo> result = new ArrayList<>();

        for (LineType[] group : CATEGORY_GROUPS) {
            List<LineInfo> candidatesInGroup = new ArrayList<>();
            for (LineInfo info : allLines) {
                for (LineType type : group) {
                    if (info.type == type) {
                        candidatesInGroup.add(info);
                        break;
                    }
                }
            }
            if (!candidatesInGroup.isEmpty()) {
                LineInfo chosen = candidatesInGroup.get(random.nextInt(candidatesInGroup.size()));
                result.add(chosen);
            }
        }

        return result;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String selectedLine = prefs.getString(PREF_SELECTED_LINE + appWidgetId, null);

        if (selectedLine != null) {
            showDetailView(context, appWidgetManager, appWidgetId, selectedLine);
        } else {
            showSelectionView(context, appWidgetManager, appWidgetId);
        }
    }

    private void showSelectionView(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String shownKey = PREF_SHOWN_LINES + appWidgetId;
        String saved = prefs.getString(shownKey, null);

        List<LineInfo> candidates = new ArrayList<>();

        if (saved != null && !saved.isEmpty()) {
            for (String code : saved.split(",")) {
                if (code.isEmpty()) continue;
                LineInfo info = findLine(code);
                if (info != null) candidates.add(info);
            }
        }

        if (candidates.isEmpty()) {
            candidates = pickOneLinePerCategory();

            StringBuilder sb = new StringBuilder();
            for (LineInfo info : candidates) sb.append(info.code).append(",");
            prefs.edit().putString(shownKey, sb.toString()).apply();
        }

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_lines_selection);

        int[] chipIds = {R.id.chip1, R.id.chip2, R.id.chip3, R.id.chip4, R.id.chip5};
        for (int i = 0; i < chipIds.length; i++) {
            if (i < candidates.size()) {
                LineInfo info = candidates.get(i);
                views.setTextViewText(chipIds[i], info.code);
                views.setInt(chipIds[i], "setBackgroundResource", R.drawable.chip_line_bg);
                applyChipTint(views, chipIds[i], context, info.colorRes);
                views.setViewVisibility(chipIds[i], android.view.View.VISIBLE);

                Intent clickIntent = new Intent(context, WidgetLines.class);
                clickIntent.setAction(ACTION_SELECT_LINE);
                clickIntent.putExtra(EXTRA_LINE_NAME, info.code);
                clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

                int requestCode = (appWidgetId + "_" + info.code).hashCode();

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        context, requestCode, clickIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                views.setOnClickPendingIntent(chipIds[i], pendingIntent);
            } else {
                views.setViewVisibility(chipIds[i], android.view.View.GONE);
            }
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    private void showDetailView(Context context, AppWidgetManager appWidgetManager, int appWidgetId, String lineCode) {

        LineInfo info = findLine(lineCode);
        if (info == null) {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            prefs.edit()
                    .remove(PREF_SELECTED_LINE + appWidgetId)
                    .remove(PREF_SHOWN_LINES + appWidgetId)
                    .apply();
            showSelectionView(context, appWidgetManager, appWidgetId);
            return;
        }

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_lines_detail);

        views.setTextViewText(R.id.detail_line_chip, info.code);
        views.setInt(R.id.detail_line_chip, "setBackgroundResource", R.drawable.chip_line_bg);
        applyChipTint(views, R.id.detail_line_chip, context, info.colorRes);
        views.setTextViewText(R.id.detail_line_name, info.type.label + " " + info.code);
        views.setImageViewResource(R.id.detail_type_icon, info.type.iconRes);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            views.setColorStateList(
                    R.id.detail_type_icon,
                    "setImageTintList",
                    ColorStateList.valueOf(
                            ContextCompat.getColor(context, R.color.text_primary)
                    )
            );
            views.setColorStateList(
                    R.id.lavoriInCorsoIcon,
                    "setImageTintList",
                    ColorStateList.valueOf(
                            ContextCompat.getColor(context, R.color.text_primary)
                    )
            );
            views.setColorStateList(
                    R.id.lavoriProgrammatiIcon,
                    "setImageTintList",
                    ColorStateList.valueOf(
                            ContextCompat.getColor(context, R.color.text_primary)
                    )
            );
        }

        int[] counts = countWorksForLine(info);
        views.setTextViewText(R.id.detail_in_corso_count, String.valueOf(counts[0]));
        views.setTextViewText(R.id.detail_programmati_count, String.valueOf(counts[1]));

        Intent backIntent = new Intent(context, WidgetLines.class);
        backIntent.setAction(ACTION_BACK_TO_SELECTION);
        backIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent backPendingIntent = PendingIntent.getBroadcast(
                context, appWidgetId, backIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.detail_line_chip, backPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private int[] countWorksForLine(LineInfo info) {
        int inCorso = 0;
        int programmati = 0;

        String searchTag = (info.type == LineType.FILOBUS)
                ? "FILOBUS " + info.code.trim()
                : info.code.trim().toUpperCase();

        long now = System.currentTimeMillis();

        if (EventData.listaEventiCompleta == null) {
            return new int[]{0, 0};
        }

        for (EventDescriptor evento : EventData.listaEventiCompleta) {
            if (evento.getLines() == null || evento.isEventTerminated()) continue;

            boolean matches = false;
            for (String lineInEvent : evento.getLines()) {
                if (lineInEvent != null && lineInEvent.trim().toUpperCase().equals(searchTag)) {
                    matches = true;
                    break;
                }
            }
            if (!matches) continue;

            long startMillis = evento.getDateMillis(evento.getStartDate());
            if (startMillis <= now) {
                inCorso++;
            } else {
                programmati++;
            }
        }

        return new int[]{inCorso, programmati};
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        if (action == null) return;

        int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) return;

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        if (ACTION_SELECT_LINE.equals(action)) {
            String lineCode = intent.getStringExtra(EXTRA_LINE_NAME);
            prefs.edit().putString(PREF_SELECTED_LINE + appWidgetId, lineCode).apply();
            showDetailView(context, appWidgetManager, appWidgetId, lineCode);

        } else if (ACTION_BACK_TO_SELECTION.equals(action)) {
            prefs.edit()
                    .remove(PREF_SELECTED_LINE + appWidgetId)
                    .remove(PREF_SHOWN_LINES + appWidgetId)
                    .apply();
            showSelectionView(context, appWidgetManager, appWidgetId);
        }
    }

    private void applyChipTint(RemoteViews views, int viewId, Context context, int colorRes) {
        int color = context.getColor(colorRes);
        if (Build.VERSION.SDK_INT >= 31) {
            views.setColorStateList(
                    viewId,
                    "setBackgroundTintList",
                    ColorStateList.valueOf(color)
            );
        } else {
            views.setInt(viewId, "setBackgroundColor", color);
        }
    }
}