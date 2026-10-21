package com.andreafilice.lavorami;

import android.app.Activity;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AccountLevel {

    private static final int BASE_XP = 50;
    private static final double LEVEL_MULTIPLIER = 1.30;

    private int level;
    private int xp;

    public AccountLevel(Context context) {
        DataManager.init(context);
        this.level = DataManager.getIntData(DataKeys.KEY_USER_LEVEL, 1);
        this.xp = DataManager.getIntData(DataKeys.KEY_USER_XP, 0);
    }

    public static AccountLevel getInstance(Context context) {
        return new AccountLevel(context);
    }

    /**
     * Calcola il titolo/grado in base al livello.
     */
    public static String getRankTitle(int level) {
        if (level <= 1) return "Pendolare della Domenica";
        if (level <= 3) return "Pendolare";
        if (level <= 5) return "Viaggiatore";
        if (level <= 7) return "Esploratore delle Linee";
        if (level <= 9) return "Conoscitore dei Mezzi";
        if (level <= 14) return "Esperto di Trasporti";
        if (level <= 19) return "Mastro della Viabilità";
        if (level <= 29) return "Leggenda della Metro";
        return "Inquilino delle Metro";
    }

    public String getRankTitle() {
        return getRankTitle(this.level);
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

        String oldRankTitle = getRankTitle();

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

        if (leveledUp && context != null) {
            String newRankTitle = getRankTitle();
            if (!oldRankTitle.equals(newRankTitle)) {
                showLevelUpDialog(context, this.level);
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
        String title = String.format(java.util.Locale.ITALIAN, "LIVELLO %d RAGGIUNTO", newLevel);
        String message = String.format(java.util.Locale.ITALIAN, "Hai raggiunto il livello %d, ora sei \"%s\"", newLevel, getRankTitle(newLevel));

        SessionManager sessionManager = new SessionManager(context);
        if (!sessionManager.isLoggedIn()) {
            message += "\n\nper evitare di perdere i tuoi progressi, registrati nella sezione Impostazioni > Account";
        }

        final String finalMessage = message;

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                activity.runOnUiThread(() -> DialogHelper.createDefaultDialog(context, title, finalMessage));
            }
        } else {
            DialogHelper.createDefaultDialog(context, title, finalMessage);
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
            levelText.setText("Liv. " + level);
        }
        if (xpText != null) {
            xpText.setText(xp + " / " + max + " XP");
        }
    }
}
