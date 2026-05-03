package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.changeActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.Task;

import java.util.HashSet;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {

    SessionManager sessionManager;
    private Set<String> favorites = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //*LOCK THE ORIENTATION
        /// In this section of the code, we will block the orientation to PORTRAIT because in LANDSCAPE LavoraMi is not supported.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sessionManager = new SessionManager(this);

        //*NAVBAR
        ImageButton btnLines = (ImageButton) findViewById(R.id.linesButton);
        btnLines.setOnClickListener(v -> {changeActivity(this, LinesActivity.class);});

        ImageButton btnSettings = (ImageButton) findViewById(R.id.homeButton);
        btnSettings.setOnClickListener(v -> {changeActivity(this, MainActivity.class);});

        //*SETTINGS BUTTONS
        RelativeLayout accountBtn = findViewById(R.id.btnAccount);
        accountBtn.setOnClickListener(v -> {changeActivity(this, AccountManagement.class);});

        RelativeLayout sourcesBtn = findViewById(R.id.btnFonts);
        sourcesBtn.setOnClickListener(v -> {changeActivity(this, SourcesDevelopment.class);});

        RelativeLayout notificationsBtn = findViewById(R.id.btnNotifiche);
        notificationsBtn.setOnClickListener(v -> {changeActivity(this, NotificationSettings.class);});

        RelativeLayout filtersButton = findViewById(R.id.btnFiltro);
        filtersButton.setOnClickListener(v -> {changeActivity(this, FilterSelection.class);});

        RelativeLayout themeButton = findViewById(R.id.btnTema);
        themeButton.setOnClickListener(v -> {changeActivity(this, ThemeSettings.class);});

        RelativeLayout advancedOptionsButton = findViewById(R.id.btnAdvanced);
        advancedOptionsButton.setOnClickListener(v -> {changeActivity(this, AdvancedOptions.class);});

        RelativeLayout howAppWorksButton = findViewById(R.id.btnHowWorks);
        howAppWorksButton.setOnClickListener(v -> changeActivity(this, HowAppWorks.class));

        //*FAVORITES LINES
        RelativeLayout groupTrenord = findViewById(R.id.groupTrenord);
        groupTrenord.setOnClickListener(v -> {
            LinearLayout trenordLayout = findViewById(R.id.disclosureContentTrenord);
            ImageView arrowDisclosure = findViewById(R.id.disclosureArrowTrenord);

            trenordLayout.setVisibility((trenordLayout.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
            arrowDisclosure.setRotation((arrowDisclosure.getRotation() == 270) ? 180 : 270);
        });

        RelativeLayout groupATM = findViewById(R.id.groupATM);
        groupATM.setOnClickListener(v -> {
            LinearLayout atmLayout = findViewById(R.id.disclosureContentAtm);
            ImageView arrowDisclosure = findViewById(R.id.disclosureArrowAtm);

            atmLayout.setVisibility((atmLayout.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
            arrowDisclosure.setRotation((arrowDisclosure.getRotation() == 270) ? 180 : 270);
        });

        //*UI UPDATES
        /// Update the UI Setting Button Texts with this function.
        reloadDatas();

        //*SET FAVORITES
        ImageView[] starIcons = {
            findViewById(R.id.sLinesFavorites), //? "Linee S"
            findViewById(R.id.rLinesFavorites), //? "Linee R"
            findViewById(R.id.reLinesFavorites), //? "Linee RE"
            findViewById(R.id.metroLinesFavorites), //? "Linee Metropolitane"
            findViewById(R.id.tramLinesFavorites), //? "Linee Tram"
            findViewById(R.id.busLinesFavorites), //? "Linee Bus"
            findViewById(R.id.movibusLinesFavorites), //? "Linee Movibus"
            findViewById(R.id.stavLinesFavorites), //? "Linee STAV"
            findViewById(R.id.autoguidovieLinesFavorites) //? "Linee Autoguidovie"
        };

        RelativeLayout[] starButtons = {
            findViewById(R.id.sLinesLayout),
            findViewById(R.id.rLinesLayout),
            findViewById(R.id.reLinesLayout),
            findViewById(R.id.busLinesATMLayout),
            findViewById(R.id.tramLinesATMLayout),
            findViewById(R.id.metroLinesATMLayout),
            findViewById(R.id.disclosureHeaderMovibus),
            findViewById(R.id.disclosureHeaderStav),
            findViewById(R.id.disclosureHeaderAutoguidovie)
        };

        String[] lineCodes = {
            "S",
            "R",
            "RE",
            "Metro",
            "Tram",
            "Bus",
            "z6",
            "z5",
            "Autoguidovie"
        };

        favorites = new HashSet<>(DataManager.getStringArray(DataKeys.KEY_FAVORITE_LINES, new HashSet<>()));
        Log.d("DATA", favorites.toString());

        setStarIcons(starIcons, starButtons, lineCodes);
        loadFavorites(starIcons, lineCodes);

        //*SET THE VERSION TEXT
        /// In this section of the code, we change dynamically the text of appVersionFull TextView
        /// From the baseVersion to fullVersion for see also the Build Number.

        TextView appVersionText = findViewById(R.id.appVersionFull);
        RelativeLayout versionButton = findViewById(R.id.btnVersion);

        versionButton.setOnClickListener(v -> {
            String currentText = appVersionText.getText().toString();
            String fullVersionText = getString(R.string.appVersionFull);
            boolean isFullVersion = currentText.equals(fullVersionText);

            appVersionText.setText((isFullVersion) ? R.string.app_version : R.string.appVersionFull);
        });

        appVersionText.setOnClickListener(v -> {
            String currentText = appVersionText.getText().toString();
            String fullVersionText = getString(R.string.appVersionFull);
            boolean isFullVersion = currentText.equals(fullVersionText);

            appVersionText.setText((isFullVersion) ? R.string.app_version : R.string.appVersionFull);
        });

        /// In this section of the code, we handle the LongClick of the versionButton and copy the
        /// Current version of the application.
        versionButton.setOnLongClickListener(v -> {
            String textToCopy = appVersionText.getText().toString();

            ClipboardManager clipboard = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Versione App", textToCopy);
            clipboard.setPrimaryClip(clipData);
            Toast.makeText(this, getString(R.string.versionAppCopied), Toast.LENGTH_LONG).show();

            return true;
        });

        //*SHARE LAVORAMI
        /// In this section, we add the listener to the "Share" button
        RelativeLayout shareBtn = findViewById(R.id.btnShare);

        shareBtn.setOnClickListener(v -> {
            String packageName = getPackageName();
            String link = "https://play.google.com/store/apps/details?id=" + packageName;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "LavoraMi");
            intent.putExtra(Intent.EXTRA_TEXT, ContextCompat.getString(this, R.string.shareContentLavoraMi) + link);

            startActivity(Intent.createChooser(intent, ContextCompat.getString(this, R.string.shareLavoraMi)));
        });

        //*RATE LAVORAMI
        /// In this section, we add a listener to the "Rate us" button
        RelativeLayout rateUsBtn = findViewById(R.id.btnRateUs);

        rateUsBtn.setOnClickListener(v -> {
            final String appPackageName = getPackageName();

            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException anfe) {startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));}
        });

        //*SUPPORT LAVORAMI
        /// In this section, we add a listener to the "Support Us" button in Settings view.
        RelativeLayout supportBtn = findViewById(R.id.btnSupportUs);

        supportBtn.setOnClickListener(v -> ActivityUtils.openURLWithTabBuilder(this, "https://www.buymeacoffee.com/lavorami"));

        //*RESET SETTINGS
        /// In this section of the code we create the Listener for the Reset Settings button
        RelativeLayout btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(v -> {resetSettings(starIcons, lineCodes);});
    }

    @Override
    protected void onResume(){
        /// In this section of the code, we reload the datas of the SettingsActivity for updating it to the latest versions.
        super.onResume();
        reloadDatas();
    }

    public void reloadDatas(){
        //*LOADING DATAS
        /// In this section of the code, we will loading the datas from the DataManager file also AFTER the Back Gesture.

        String selectedFilter = DataManager.getStringData(DataKeys.KEY_DEFAULT_FILTER, "Tutti");
        TextView filterSelectedText = findViewById(R.id.filterText);

        /// Localize the current filter to the correct language
        String selectedFilterLocalized = getLocalizedMessage(selectedFilter);
        filterSelectedText.setText(selectedFilterLocalized);

        TextView nameSettingsText = findViewById(R.id.nameSettingsText);
        nameSettingsText.setText((sessionManager.isLoggedIn()) ? sessionManager.getUserName() : getString(R.string.yourAccountSettingsTitle));

        ImageView profileImage = findViewById(R.id.profileImage);
        profileImage.setImageResource((!sessionManager.isLoggedInWithGoogle()) ? R.drawable.ic_account_circle : R.drawable.ic_google_logo);

        if(!sessionManager.isLoggedInWithGoogle())
            profileImage.setColorFilter(ContextCompat.getColor(this, R.color.redMetro));
        else if(sessionManager.isLoggedInWithGoogle())
            profileImage.clearColorFilter();
    }

    public String getLocalizedMessage(String toCompare){
        switch(toCompare) {
            case "Tutti": return getString(R.string.allFilter);
            case "Bus": return "Bus";
            case "Tram": return "Tram";
            case "Metropolitana": return getString(R.string.metroFilter);
            case "Treno": return getString(R.string.trainFilter);
            case "In Corso": return getString(R.string.inProgressFilter);
            case "Programmati": return getString(R.string.scheduledFilter);
            case "di ATM": return getString(R.string.atmFilter);
            case "di Trenord": return getString(R.string.trenordFilter);
            case "di Movibus": return getString(R.string.movibusFilter);
            case "di STAV": return getString(R.string.stavFilter);
            case "di Autoguidovie": return getString(R.string.autoguidovieFilter);
            default: return "Errore";
        }
    }

    public void setStarIcons(ImageView[] icons, RelativeLayout[] layouts, String[] lineCodes){
        for (int i = 0; i < icons.length; i++) {
            int finalI = i;

            icons[i].setOnClickListener(v -> {
                Integer currentTag = (Integer) icons[finalI].getTag();

                int currentRes = (currentTag != null) ? currentTag : R.drawable.ic_star_empty;
                int newRes = (currentRes == R.drawable.ic_star_empty) ? R.drawable.ic_star_fill : R.drawable.ic_star_empty;

                icons[finalI].setImageResource(newRes);
                icons[finalI].setTag(newRes);

                if (newRes == R.drawable.ic_star_fill)
                    favorites.add(lineCodes[finalI]);
                else
                    favorites.remove(lineCodes[finalI]);

                NotificationScheduler.scheduleWorkNotifications(this, EventData.listaEventiCompleta);
                DataManager.saveArrayStringsData(DataKeys.KEY_FAVORITE_LINES, favorites);
            });

            layouts[i].setOnClickListener(v -> {
                Integer currentTag = (Integer) icons[finalI].getTag();

                int currentRes = (currentTag != null) ? currentTag : R.drawable.ic_star_empty;
                int newRes = (currentRes == R.drawable.ic_star_empty) ? R.drawable.ic_star_fill : R.drawable.ic_star_empty;

                icons[finalI].setImageResource(newRes);
                icons[finalI].setTag(newRes);

                if (newRes == R.drawable.ic_star_fill)
                    favorites.add(lineCodes[finalI]);
                else
                    favorites.remove(lineCodes[finalI]);

                NotificationScheduler.scheduleWorkNotifications(this, EventData.listaEventiCompleta);
                DataManager.saveArrayStringsData(DataKeys.KEY_FAVORITE_LINES, favorites);
            });
        }
    }

    public void loadFavorites(ImageView[] starIcons, String[] lineCodes) {
        for (int i = 0; i < lineCodes.length; i++) {
            if (favorites.contains(lineCodes[i])) {
                starIcons[i].setImageResource(R.drawable.ic_star_fill);
                starIcons[i].setTag(R.drawable.ic_star_fill);
            }
            else {
                starIcons[i].setImageResource(R.drawable.ic_star_empty);
                starIcons[i].setTag(R.drawable.ic_star_empty);
            }
        }
    }

    public void resetSettings(ImageView[] images, String[] lines){
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.areYouSurePopUp))
                .setMessage(getString(R.string.resetSettingsPopUp))
                .setNegativeButton(getString(R.string.cancelPopUp), null)
                .setPositiveButton(getString(R.string.confirmPopUp), (dialog, which) -> {
                    DataManager.saveStringData(DataKeys.KEY_DEFAULT_FILTER, "Tutti");
                    DataManager.saveBoolData(DataKeys.KEY_NOTIFICATION_SWITCH, true);
                    DataManager.saveBoolData(DataKeys.KEY_NOTIFICATION_STARTWORKS, true);
                    DataManager.saveBoolData(DataKeys.KEY_NOTIFICATION_ENDWORKS, true);
                    DataManager.saveBoolData(DataKeys.KEY_NOTIFICATION_STRIKES, true);
                    DataManager.saveIntData(DataKeys.KEY_HOURS_NOTIFICATIONS, 10);
                    DataManager.saveIntData(DataKeys.KEY_MINUTES_NOTIFICATIONS, 00);
                    DataManager.saveArrayStringsData(DataKeys.KEY_FAVORITE_LINES, new HashSet<>());
                    DataManager.saveBoolData(DataKeys.KEY_SHOW_ERROR_MESSAGES, false);
                    DataManager.saveBoolData(DataKeys.KEY_SHOW_BANNERS, true);
                    DataManager.saveBoolData(DataKeys.KEY_REQUIRE_BIOMETRICS, true);
                    DataManager.saveBoolData(DataKeys.KEY_SHOW_DETAILS, true);
                    DataManager.saveStringData(DataKeys.KEY_DEFAULT_THEME, "Sistema");
                    Toast.makeText(this, getString(R.string.settingResettedPopUp), Toast.LENGTH_SHORT).show();
                    favorites.clear();
                    reloadDatas();
                    setTheme();
                    loadFavorites(images, lines);
                }).show();
    }

    private void setTheme(){
        /// This method apply the theme that the user have selected by Loading the Data and cased it.
        /// @PARAMETERS
        /// There are no parameters.

        String typeLoaded = DataManager.getStringData(DataKeys.KEY_DEFAULT_THEME, "Sistema");
        int modeSelected;

        switch (typeLoaded){
            case "Sistema":
                modeSelected = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                break;
            case "Scuro":
                modeSelected = AppCompatDelegate.MODE_NIGHT_YES;
                break;
            case "Chiaro":
                modeSelected = AppCompatDelegate.MODE_NIGHT_NO;
                break;
            default:
                modeSelected = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                break;
        }

        AppCompatDelegate.setDefaultNightMode(modeSelected);
    }
}