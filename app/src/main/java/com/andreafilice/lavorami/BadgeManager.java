package com.andreafilice.lavorami;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BadgeManager {

    public static List<Badge> getAllBadges(Context context) {
        List<Badge> list = new ArrayList<>();
        if (context == null) return list;

        Context appContext = context.getApplicationContext();

        AccountLevel accountLevel = AccountLevel.getInstance(appContext);
        int level = accountLevel.getLevel();
        int xp = accountLevel.getXp();

        Set<String> favs = DataManager.getStringArray(DataKeys.KEY_FAVORITE_LINES, new HashSet<>());
        int favCount = favs.size();

        SessionManager sessionManager = new SessionManager(appContext);
        boolean isLoggedIn = sessionManager.isLoggedIn();

        boolean notifSwitch = DataManager.getBoolData(DataKeys.KEY_NOTIFICATION_SWITCH, true);
        boolean notifStrikes = DataManager.getBoolData(DataKeys.KEY_NOTIFICATION_STRIKES, true);
        boolean notifWorks = DataManager.getBoolData(DataKeys.KEY_NOTIFICATION_STARTWORKS, true);

        boolean haptics = DataManager.getBoolData(DataKeys.KEY_HAPTIC_FEEDBACKS, true);
        boolean wrapped = DataManager.getBoolData(DataKeys.KEY_WRAPPED_OPENED, false);
        String theme = DataManager.getStringData(DataKeys.KEY_DEFAULT_THEME, "Sistema");

        int adsWatched = DataManager.getIntData(DataKeys.KEY_ADS_WATCHED_COUNT, 0);
        boolean biometrics = DataManager.getBoolData(DataKeys.KEY_REQUIRE_BIOMETRICS, true);
        boolean recentLines = DataManager.getBoolData(DataKeys.KEY_SHOW_RECENT_LINES, true);
        String openLinkType = DataManager.getStringData(DataKeys.KEY_OPEN_LINK_TYPE, "IN_APP");
        String widgetLine = DataManager.getStringData(DataKeys.KEY_LINE_WIDGET, "");

        list.add(new Badge("badge_lvl_1", "Pendolare Novizio", "Raggiungi il Livello 1 dell'account.", R.drawable.ic_account_circle, level, 1));
        list.add(new Badge("badge_lvl_3", "Stratega della Banchina", "Raggiungi il Livello 3 dell'account.", R.drawable.ic_direction, level, 3));
        list.add(new Badge("badge_lvl_4", "Passeggero Zen", "Raggiungi il Livello 4 dell'account.", R.drawable.ic_heart, level, 4));
        list.add(new Badge("badge_lvl_5", "Cacciatore di Posti", "Raggiungi il Livello 5 dell'account.", R.drawable.ic_star_fill, level, 5));
        list.add(new Badge("badge_lvl_10", "Acrobata del Tram", "Raggiungi il Livello 10 dell'account.", R.drawable.ic_tram, level, 10));
        list.add(new Badge("badge_lvl_15", "Passeggero Pro", "Raggiungi il Livello 15 dell'account.", R.drawable.ic_bolt, level, 15));
        list.add(new Badge("badge_lvl_30", "Collezionista di Biglietti", "Raggiungi il Livello 30 dell'account.", R.drawable.ic_event, level, 30));
        list.add(new Badge("badge_lvl_50", "Filosofo del Treno", "Raggiungi il Livello 50 dell'account.", R.drawable.ic_train, level, 50));

        list.add(new Badge("badge_fav_1", "Primo Preferito", "Aggiungi la tua prima linea ai preferiti.", R.drawable.ic_star_empty, favCount, 1));
        list.add(new Badge("badge_fav_5", "Cinque Stelle", "Aggiungi 5 linee ai preferiti.", R.drawable.ic_star_fill, favCount, 5));
        list.add(new Badge("badge_fav_10", "Collezionista di Linee", "Aggiungi 10 linee ai preferiti.", R.drawable.ic_heart_empty, favCount, 10));

        list.add(new Badge("badge_fav_metro", "Fan della Metro", "Salva le linee Metropolitane nei preferiti.", R.drawable.ic_metro, favs.contains("Metro") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_tram", "Amante dei Tram", "Salva le linee Tram nei preferiti.", R.drawable.ic_tram, favs.contains("Tram") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_bus", "Viaggiatore in Bus", "Salva le linee Bus nei preferiti.", R.drawable.ic_bus, favs.contains("Bus") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_suburban", "Passeggero Suburbano", "Salva le linee Suburbane (S) nei preferiti.", R.drawable.ic_slines, favs.contains("S") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_regional", "Pendolare Regionale", "Salva le linee Regionali (R) nei preferiti.", R.drawable.ic_rlines, favs.contains("R") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_re", "Regio Express", "Salva le linee Regio Express (RE) nei preferiti.", R.drawable.icon_relines, favs.contains("RE") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_tilo", "Confine Svizzero", "Salva la linea TILO nei preferiti.", R.drawable.ic_train, favs.contains("Tilo") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_movibus", "Rete Movibus", "Salva la linea Movibus nei preferiti.", R.drawable.ic_bus, favs.contains("z6") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_net", "Rete NET", "Salva la linea NET nei preferiti.", R.drawable.ic_bus, favs.contains("z3") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_stav", "Rete STAV", "Salva la linea STAV nei preferiti.", R.drawable.ic_bus, favs.contains("z55") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_star", "Rete STAR", "Salva la linea STAR Mobility nei preferiti.", R.drawable.ic_star_fill, favs.contains("z50") ? 1 : 0, 1));
        list.add(new Badge("badge_fav_auto", "Rete Autoguidovie", "Salva la linea Autoguidovie nei preferiti.", R.drawable.ic_bus, favs.contains("Autoguidovie") ? 1 : 0, 1));

        list.add(new Badge("badge_account", "Account Registrato", "Registrati o effettua l'accesso con il tuo account.", R.drawable.ic_at, isLoggedIn ? 1 : 0, 1));
        list.add(new Badge("badge_ad_1", "Cacciatore di XP", "Guarda 1 video promozionale per guadagnare XP.", R.drawable.ic_bolt, adsWatched, 1));
        list.add(new Badge("badge_ad_5", "Sostenitore di XP", "Guarda 5 video promozionali per guadagnare XP.", R.drawable.ic_bolt_horizontal, adsWatched, 5));

        list.add(new Badge("badge_notif", "Sempre Informato", "Attiva le notifiche generali dell'applicazione.", R.drawable.ic_bell, notifSwitch ? 1 : 0, 1));
        list.add(new Badge("badge_notif_strike", "Allerta Scioperi", "Attiva le notifiche degli scioperi.", R.drawable.ic_bell_strikes, notifStrikes ? 1 : 0, 1));
        list.add(new Badge("badge_notif_works", "Allerta Lavori", "Attiva le notifiche sui lavori in corso.", R.drawable.ic_cone, notifWorks ? 1 : 0, 1));

        list.add(new Badge("badge_haptics", "Feedback Tattile", "Mantieni attivo il feedback con vibrazione.", R.drawable.ic_feedback_haptic, haptics ? 1 : 0, 1));
        list.add(new Badge("badge_wrapped", "LavoraMi Wrapped", "Apri la sezione LavoraMi Wrapped.", R.drawable.ic_banner, wrapped ? 1 : 0, 1));
        list.add(new Badge("badge_map", "Mappe & Deviazioni", "Consulta la pagina con le info sulle mappe.", R.drawable.ic_map, 1, 1));
        list.add(new Badge("badge_sources", "Fonti & Sviluppo", "Visita la sezione Fonti e Sviluppatori.", R.drawable.ic_sources, 1, 1));
        list.add(new Badge("badge_accessibility", "Inclusività", "Consulta le informazioni sull'accessibilità.", R.drawable.ic_accessibility, 1, 1));
        list.add(new Badge("badge_how_it_works", "Guida App", "Scopri come funziona l'app LavoraMi.", R.drawable.ic_question, 1, 1));

        list.add(new Badge("badge_support", "Sostenitore", "Supporta lo sviluppo di LavoraMi.", R.drawable.ic_buymeacoffee, 1, 1));
        list.add(new Badge("badge_share", "Ambasciatore", "Condividi l'app LavoraMi con i tuoi amici.", R.drawable.ic_share, 1, 1));
        list.add(new Badge("badge_rate", "Recensore", "Valuta l'applicazione sullo Store.", R.drawable.ic_heart, 1, 1));

        list.add(new Badge("badge_dark_mode", "Nottambulo", "Attiva il tema Scuro nelle impostazioni.", R.drawable.ic_dark, "Scuro".equalsIgnoreCase(theme) ? 1 : 0, 1));
        list.add(new Badge("badge_light_mode", "Luce del Sole", "Attiva il tema Chiaro nelle impostazioni.", R.drawable.ic_light, "Chiaro".equalsIgnoreCase(theme) ? 1 : 0, 1));

        list.add(new Badge("badge_xp_100", "Accumulatore XP", "Raggiungi 100 XP complessivi.", R.drawable.ic_clock, xp, 100));
        list.add(new Badge("badge_xp_500", "Venerabile XP", "Raggiungi 500 XP complessivi.", R.drawable.ic_clock_badge, xp, 500));
        list.add(new Badge("badge_xp_1000", "Leggenda XP", "Raggiungi 1000 XP complessivi.", R.drawable.ic_clock_one, xp, 1000));

        list.add(new Badge("badge_widget", "Widget Maniac", "Configura una linea per il widget della home.", R.drawable.ic_add_line_widget, (!widgetLine.isEmpty()) ? 1 : 0, 1));
        list.add(new Badge("badge_recent_lines", "Storie Recenti", "Mantieni attiva l'opzione linee recenti.", R.drawable.ic_recent_searches, recentLines ? 1 : 0, 1));
        list.add(new Badge("badge_inapp_browser", "Navigatore In-App", "Mantieni l'apertura link in-app attiva.", R.drawable.ic_browser, "IN_APP".equals(openLinkType) ? 1 : 0, 1));
        list.add(new Badge("badge_biometrics", "Sicurezza Forte", "Attiva la verifica biometrica.", R.drawable.ic_fingerprint, biometrics ? 1 : 0, 1));
        list.add(new Badge("badge_libraries", "Open Source", "Scopri le librerie open source dell'app.", R.drawable.ic_libraries, 1, 1));
        list.add(new Badge("badge_app_version", "Versione App", "Visualizza le informazioni sulla versione dell'app.", R.drawable.ic_info, 1, 1));

        int unlockedCount = 0;
        for (Badge b : list) {
            if (b.isUnlocked()) unlockedCount++;
        }

        list.add(new Badge("badge_master", "Maestro del Trasporto", "Sblocca almeno 25 altri distintivi dell'app.", R.drawable.ic_heart_empty, unlockedCount, 25));

        return list;
    }

    public static int getTotalBadgesCount() {
        return 50;
    }

    public static int getUnlockedBadgesCount(Context context) {
        List<Badge> badges = getAllBadges(context);
        int count = 0;
        for (Badge b : badges) {
            if (b.isUnlocked()) count++;
        }
        return count;
    }
}
