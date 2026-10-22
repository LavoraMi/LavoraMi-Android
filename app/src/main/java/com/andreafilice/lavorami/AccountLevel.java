package com.andreafilice.lavorami;

import android.app.Activity;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AccountLevel {

    private static final int BASE_XP = 50;
    private static final double LEVEL_MULTIPLIER = 1.30;

    private final Context context;
    private int level;
    private int xp;

    public AccountLevel(Context context) {
        if (context != null) {
            this.context = context.getApplicationContext();
            DataManager.init(this.context);
        } else {
            this.context = null;
        }
        this.level = DataManager.getIntData(DataKeys.KEY_USER_LEVEL, 1);
        this.xp = DataManager.getIntData(DataKeys.KEY_USER_XP, 0);
    }

    public static AccountLevel getInstance(Context context) {
        return new AccountLevel(context);
    }

    /** Calcola il titolo/grado in base al livello e al Context per la localizzazione. */
    public static String getRankTitle(Context context, int level) {
        if (context == null) {
            return getRankTitle(level); ///Fallback
        }
        if (level <= 1) return context.getString(R.string.rank_sunday_commuter);
        if (level <= 3) return context.getString(R.string.rank_platform_strategist);
        if (level <= 4) return context.getString(R.string.rank_zen_passenger);
        if (level == 5) return context.getString(R.string.rank_seat_hunter);
        if (level <= 10) return context.getString(R.string.rank_tram_acrobat);
        if (level <= 15) return context.getString(R.string.rank_pro_passenger);
        if (level <= 30) return context.getString(R.string.rank_ticket_collector);
        if (level <= 50) return context.getString(R.string.rank_train_philosopher);
        return context.getString(R.string.rank_subway_resident);
    }

    public static String getRankTitle(int level) {
        if (level == 1) return "Pendolare della Domenica";
        if (level == 3) return "Stratega della Banchina";
        if (level == 4) return "Passeggero Zen";
        if (level == 5) return "Cacciatore di Posti";
        if (level < 10) return "Acrobata del Tram";
        if (level < 15) return "Passeggero Professionista";
        if (level < 30) return "Collezionista di Biglietti";
        if (level < 50) return "Filosofo del Treno";
        return "Inquilino della Metropolitana";
    }

    public String getRankTitle() {
        return getRankTitle(this.context, this.level);
    }

    public String getRankTitle(Context context) {
        return getRankTitle(context != null ? context : this.context, this.level);
    }

    /**
     * Calcola l'XP necessario per completare il livello specificato.
     * Livello 1: 50 XP. Ogni livello successivo richiede il 30% in più di quello precedente.
     */
    public int getXpForLevel(int lvl) {
        if (lvl <= 1) {
            return BASE_XP;
        }
        double xpRequired = BASE_XP;
        for (int i = 1; i < lvl; i++) {
            xpRequired *= LEVEL_MULTIPLIER;
        }
        return (int) Math.round(xpRequired);
    }

    public int getRequiredXpForCurrentLevel() {
        return getXpForLevel(this.level);
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    /**
     * Aggiunge XP all'account e gestisce l'eventuale passaggio di livello con il dialogo.
     *
     * @param context Context dell'Activity
     * @param amount Quantità di XP da aggiungere
     */
    public void addXp(Context context, int amount) {
        if (amount <= 0) return;

        Context effectiveContext = context != null ? context : this.context;
        String oldRankTitle = getRankTitle(effectiveContext);

        this.xp += amount;
        int requiredXp = getRequiredXpForCurrentLevel();
        boolean leveledUp = false;

        while (this.xp >= requiredXp) {
            this.xp -= requiredXp;
            this.level++;
            leveledUp = true;
            requiredXp = getRequiredXpForCurrentLevel();
        }

        save();

        if (leveledUp && effectiveContext != null) {
            String newRankTitle = getRankTitle(effectiveContext);
            if (!oldRankTitle.equals(newRankTitle)) {
                showLevelUpDialog(effectiveContext, this.level);
            }
        }
    }

    public void addXp(int amount) {
        addXp(null, amount);
    }

    /**
     * Aggiunge i 10 XP previsti per ogni minuto passato nell'app.
     *
     * @param context Context dell'Activity per mostrare il dialogo al passaggio di livello
     */
    public void addMinuteXp(Context context) {
        addXp(context, 10);
    }

    private void showLevelUpDialog(Context context, int newLevel) {
        Context effContext = context != null ? context : this.context;
        String title;
        String message;

        if (effContext != null) {
            title = effContext.getString(R.string.levelUpDialogTitle, newLevel);
            message = effContext.getString(R.string.levelUpDialogMessage, newLevel, getRankTitle(effContext, newLevel));
            SessionManager sessionManager = new SessionManager(effContext);
            if (!sessionManager.isLoggedIn()) {
                message += "\n\n" + effContext.getString(R.string.levelUpDialogRegisterNotice);
            }
        } else {
            ///Fallback
            title = String.format(java.util.Locale.ITALIAN, "LIVELLO %d RAGGIUNTO", newLevel);
            message = String.format(java.util.Locale.ITALIAN, "Hai raggiunto il livello %d, ora sei \"%s\"", newLevel, getRankTitle(null, newLevel));
        }

        final String finalTitle = title;
        final String finalMessage = message;

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                activity.runOnUiThread(() -> DialogHelper.createDefaultDialog(context, finalTitle, finalMessage));
            }
        } else {
            DialogHelper.createDefaultDialog(context, finalTitle, finalMessage);
        }
    }

    public void save() {
        DataManager.saveIntData(DataKeys.KEY_USER_LEVEL, this.level);
        DataManager.saveIntData(DataKeys.KEY_USER_XP, this.xp);
    }

    /**
     * Aggiorna gli elementi grafici dell'interfaccia utente (ProgressBar, livello e XP).
     */
    public void updateUI(ProgressBar progressBar, TextView levelText, TextView xpText) {
        int max = getRequiredXpForCurrentLevel();
        if (progressBar != null) {
            progressBar.setMax(max);
            progressBar.setProgress(xp);
        }
        if (levelText != null) {
            if (this.context != null) {
                levelText.setText(this.context.getString(R.string.accountLevelShortFormat, level));
            } else {
                levelText.setText("Liv. " + level);
            }
        }
        if (xpText != null) {
            xpText.setText(xp + " / " + max + " XP");
        }
    }
}
