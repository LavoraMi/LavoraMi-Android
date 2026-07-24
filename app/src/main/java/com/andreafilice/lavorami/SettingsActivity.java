package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.changeActivity;
import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsActivity extends AppCompatActivity {

    SessionManager sessionManager;
    SupabaseAPI api;
    Retrofit retrofitAPI;
    private String SupabaseANON, SupabaseURL;
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

        //*GET METADATA
        /// In this section of the code, we initialize the SupabaseURL and SupabaseANON variables for performance boost.
        SupabaseANON = getMetaData(this, "supabaseANON");
        SupabaseURL = getMetaData(this, "supabaseURL");

        /// In this section of the code, we initialize the Supabase Server from the keys of the .env file.
        if(SupabaseURL != null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

            retrofitAPI = new Retrofit.Builder()
                    .baseUrl(SupabaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            api = retrofitAPI.create(SupabaseAPI.class);
        }
        else
            Toast.makeText(this, getString(R.string.connectionErrorToast), Toast.LENGTH_SHORT).show();

        //*NAVBAR
        View btnLines = findViewById(R.id.linesButton);
        btnLines.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            changeActivity(this, LinesActivity.class);
        });

        View btnSettings = findViewById(R.id.homeButton);
        btnSettings.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            changeActivity(this, MainActivity.class);
        });

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
        ImageView infoFavouriteLines = findViewById(R.id.infoFavouriteLines);
        infoFavouriteLines.setOnClickListener(v -> {DialogHelper.createDefaultDialog(this, getString(R.string.dialogFavouriteLineTextTitle), getString(R.string.dialogFavouriteLineText));});

        RelativeLayout groupTrenord = findViewById(R.id.groupTrenord);
        groupTrenord.setOnClickListener(v -> {
            LinearLayout trenordLayout = findViewById(R.id.disclosureContentTrenord);
            ImageView arrowDisclosure = findViewById(R.id.disclosureArrowTrenord);

            ActivityUtils.triggerFeedback(this);
            trenordLayout.setVisibility((trenordLayout.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
            arrowDisclosure.animate().rotation((trenordLayout.getVisibility() == View.GONE) ? 180 : 270).setDuration(200).start();
        });

        RelativeLayout groupATM = findViewById(R.id.groupATM);
        groupATM.setOnClickListener(v -> {
            LinearLayout atmLayout = findViewById(R.id.disclosureContentAtm);
            ImageView arrowDisclosure = findViewById(R.id.disclosureArrowAtm);

            ActivityUtils.triggerFeedback(this);
            atmLayout.setVisibility((atmLayout.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
            arrowDisclosure.animate().rotation((atmLayout.getVisibility() == View.GONE) ? 180 : 270).setDuration(200).start();
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
            findViewById(R.id.starLinesFavorites), //? "Linee STAR"
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
            findViewById(R.id.disclosureHeaderStar),
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
            "z55",
            "z50",
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

            ActivityUtils.triggerFeedback(this);
            appVersionText.setText((isFullVersion) ? R.string.app_version : R.string.appVersionFull);
        });

        appVersionText.setOnClickListener(v -> {
            String currentText = appVersionText.getText().toString();
            String fullVersionText = getString(R.string.appVersionFull);
            boolean isFullVersion = currentText.equals(fullVersionText);

            ActivityUtils.triggerFeedback(this);
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

            ActivityUtils.triggerFeedback(this);
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
        /// In this section, we add a listener to the "Support Us" button in Settings View.
        RelativeLayout supportBtn = findViewById(R.id.btnSupportUs);

        supportBtn.setOnClickListener(v -> ActivityUtils.openURLWithTabBuilder(this, getSupportFragmentManager(), "https://www.buymeacoffee.com/lavorami"));

        //*MAPS INFOS
        /// In this section, we add a listener to the "Info about Maps" button in Settings View.
        RelativeLayout btnSeeMaps = findViewById(R.id.btnSeeMaps);
        btnSeeMaps.setOnClickListener(v -> ActivityUtils.changeActivity(this, DeviationInfo.class));

        //*RESET SETTINGS
        /// In this section of the code we create the Listener for the Reset Settings button
        RelativeLayout btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(v -> {resetSettings(starIcons, lineCodes);});

        //*BOTTOM BAR
        /// In this section of the code, we setup the buttons for the Bottom bar links.
        ImageView btnInstagram = findViewById(R.id.btnInstagram);
        ImageView btnTikTok = findViewById(R.id.btnTikTok);
        ImageView btnWebsite = findViewById(R.id.btnWebsite);

        btnInstagram.setOnClickListener(v -> ActivityUtils.openURL(this, "https://www.instagram.com/lavoramiapp_official/"));
        btnTikTok.setOnClickListener(v -> ActivityUtils.openURL(this, "https://www.tiktok.com/@applavorami.official"));
        btnWebsite.setOnClickListener(v -> ActivityUtils.openURLWithTabBuilder(this, getSupportFragmentManager(), "https://lavorami.it"));
    }

    @Override
    protected void onResume(){
        /// In this section of the code, we reload the datas of the SettingsActivity for updating it to the latest versions.
        super.onResume();

        favorites = new HashSet<>(DataManager.getStringArray(DataKeys.KEY_FAVORITE_LINES, new HashSet<>()));

        //*SET FAVORITES
        ImageView[] starIcons = {
            findViewById(R.id.sLinesFavorites), //? "Linee S"
            findViewById(R.id.rLinesFavorites), //? "Linee R"
            findViewById(R.id.reLinesFavorites), //? "Linee RE"
            findViewById(R.id.metroLinesFavorites), //? "Linee Metropolitane"
            findViewById(R.id.tramLinesFavorites), //? "Linee Tram"
            findViewById(R.id.busLinesFavorites), //? "Linee Bus"
            findViewById(R.id.tiloLinesFavorites), //? "Linee TILO"
            findViewById(R.id.movibusLinesFavorites), //? "Linee Movibus"
            findViewById(R.id.stavLinesFavorites), //? "Linee STAV"
            findViewById(R.id.starLinesFavorites), //? "Linee STAR"
            findViewById(R.id.autoguidovieLinesFavorites) //? "Linee Autoguidovie"
        };

        RelativeLayout[] starButtons = {
            findViewById(R.id.sLinesLayout),
            findViewById(R.id.rLinesLayout),
            findViewById(R.id.reLinesLayout),
            findViewById(R.id.busLinesATMLayout),
            findViewById(R.id.tramLinesATMLayout),
            findViewById(R.id.metroLinesATMLayout),
            findViewById(R.id.disclosureHeaderTILO),
            findViewById(R.id.disclosureHeaderMovibus),
            findViewById(R.id.disclosureHeaderStav),
            findViewById(R.id.disclosureHeaderStar),
            findViewById(R.id.disclosureHeaderAutoguidovie)
        };

        String[] lineCodes = {
            "S",
            "R",
            "RE",
            "Bus",
            "Tram",
            "Metro",
            "Tilo",
            "z6",
            "z55",
            "z50",
            "Autoguidovie"
        };

        setStarIcons(starIcons, starButtons, lineCodes);
        loadFavorites(starIcons, lineCodes);
        reloadDatas();
    }

    private void syncFavoritesToSupabase() {
        if (sessionManager.isLoggedIn()) {
            Set<String> yourLinesSet = DataManager.getStringArray(DataKeys.KEY_ARRAY_YOUR_LINES, new HashSet<>());

            ArrayList<String> favoritesList = new ArrayList<>(favorites);
            ArrayList<String> yourLinesList = new ArrayList<>(yourLinesSet);

            String userEmail = sessionManager.getUserEmail();
            String token = sessionManager.getToken();

            SupabaseDataManager supabaseDataManager = new SupabaseDataManager(
                    this,
                    api,
                    SupabaseANON,
                    token,
                    userEmail
            );

            supabaseDataManager.saveFavoritesAndLines(favoritesList, yourLinesList, new SupabaseDataManager.DataCallback<Void>() {
                @Override
                public void onSuccess(Void result) {Log.d("SUPABASE_SYNC", "Preferiti aggiornati nel cloud con successo!");}

                @Override
                public void onError(String error) {Log.e("SUPABASE_SYNC", "Errore durante la sincronizzazione cloud: " + error);}
            });
        }
    }

    public void reloadDatas(){
        //*LOADING DATAS
        /// In this section of the code, we will loading the datas from the DataManager file also AFTER the Back Gesture.

        CategoriesEnum selectedFilter = CategoriesEnum.valueOf(DataManager.getStringData(DataKeys.KEY_DEFAULT_FILTER, "TUTTI").toUpperCase());
        TextView filterSelectedText = findViewById(R.id.filterText);

        /// Localize the current filter to the correct language
        String selectedFilterLocalized = getLocalizedMessage(selectedFilter);
        filterSelectedText.setText(selectedFilterLocalized);

        TextView nameSettingsText = findViewById(R.id.nameSettingsText);
        nameSettingsText.setText((sessionManager.isLoggedIn()) ? sessionManager.getUserName() : getString(R.string.yourAccountSettingsTitle));

        ImageView profileImage = findViewById(R.id.profileImage);
        profileImage.setImageResource((!sessionManager.isLoggedInWithGoogle()) ? R.drawable.ic_account_circle : R.drawable.ic_google_logo);

        LinearLayout layoutTextsAccount = findViewById(R.id.layoutTextsAccount);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutTextsAccount.getLayoutParams();
        TextView profileImageText = findViewById(R.id.profileImageText);

        if(sessionManager.isLoggedIn()) {
            profileImageText.setText(getAccountIcon(sessionManager));
            profileImageText.setVisibility(View.VISIBLE);
            profileImage.setVisibility(View.GONE);
            params.addRule(RelativeLayout.END_OF, R.id.profileImageText);
            layoutTextsAccount.setLayoutParams(params);
        }
        else {
            profileImageText.setVisibility(View.GONE);
            profileImage.setVisibility(View.VISIBLE);
            params.addRule(RelativeLayout.END_OF, R.id.profileImage);
            layoutTextsAccount.setLayoutParams(params);
        }

        if(!sessionManager.isLoggedInWithGoogle())
            profileImage.setColorFilter(ContextCompat.getColor(this, R.color.redMetro));
        else if(sessionManager.isLoggedInWithGoogle())
            profileImage.clearColorFilter();
    }

    public String getAccountIcon(SessionManager manager) {
        /// In this method, we get the user FullName value and split it to create an Account icon.
        /// @PARAMETERS
        /// SessionManager manager is the manager of the session which contains the FullName attribute.

        String[] values = manager.getUserName().split("\\s+");

        //*STRING SPLIT VALIDATION
        /// In this section of the code, we avoid some crashes with special cases
        if(values.length == 0 || values[0].isEmpty()) return manager.getUserName();
        if (values.length == 1) return String.valueOf(values[0].charAt(0));

        return String.format("%s%s", values[0].charAt(0), values[1].charAt(0));
    }

    public String getLocalizedMessage(CategoriesEnum toCompare){
        /// In this method, we return the value that the option "Default Filter" have as a value.
        /// @PARAMETERS
        /// CategoriesEnum toCompare is the value taken from the Data folder.

        switch(toCompare) {
            case LE_TUE_LINEE: return getString(R.string.yourLinesPrefix);
            case TUTTI: return getString(R.string.allFilter);
            case BUS: return getString(R.string.busKey);
            case TRAM: return getString(R.string.tramLinesScroll);
            case METROPOLITANA: return getString(R.string.metroFilter);
            case TRENO: return getString(R.string.trainFilter);
            case IN_CORSO: return getString(R.string.inProgressFilter);
            case PROGRAMMATI: return getString(R.string.scheduledFilter);
            case DI_ATM: return getString(R.string.atmFilter);
            case DI_TRENORD: return getString(R.string.trenordFilter);
            case DI_TILO: return getString(R.string.diTiloLines);
            case DI_MOVIBUS: return getString(R.string.movibusFilter);
            case DI_STAV: return getString(R.string.stavFilter);
            case DI_STAR: return getString(R.string.starFilter);
            case DI_AUTOGUIDOVIE: return getString(R.string.autoguidovieFilter);
            default: return getString(R.string.error);
        }
    }

    public void setStarIcons(ImageView[] icons, RelativeLayout[] layouts, String[] lineCodes) {
        /// This function set the stars icons in Settings -> Favourites section.
        /// @PARAMETERS
        /// ImageView[] icons are the ID of the icons to set the draggable.
        /// RelativeLayout[] layouts are the ID of the buttons to set.
        /// String[] linesCodes are the codes to save into the DataManager script.

        for (int i = 0; i < icons.length; i++) {
            int finalI = i;

            icons[i].setOnClickListener(v -> {toggleFavorite(icons[finalI], lineCodes[finalI]);});
            layouts[i].setOnClickListener(v -> toggleFavorite(icons[finalI], lineCodes[finalI]));
        }
    }

    private void toggleFavorite(ImageView icon, String lineCode) {
        //TODO: Comment better this code.
        Integer currentTag = (Integer) icon.getTag();
        Animation scaleDownUp = AnimationUtils.loadAnimation(this, R.anim.scale_down_up);

        int currentRes = (currentTag != null) ? currentTag : R.drawable.ic_star_empty;
        int newRes = (currentRes == R.drawable.ic_star_empty) ? R.drawable.ic_star_fill : R.drawable.ic_star_empty;
        boolean isSyncWithCloudEnabled = DataManager.getBoolData(DataKeys.KEY_SAVE_DB_FAVORITES, true);

        icon.startAnimation(scaleDownUp);
        icon.setImageResource(newRes);
        icon.setTag(newRes);

        if (newRes == R.drawable.ic_star_fill)
            favorites.add(lineCode);
        else
            favorites.remove(lineCode);

        new Thread(() -> NotificationScheduler.scheduleWorkNotifications(this, EventData.listaEventiCompleta)).start();
        DataManager.saveArrayStringsData(DataKeys.KEY_FAVORITE_LINES, favorites);
        ActivityUtils.triggerFeedback(this);

        if(isSyncWithCloudEnabled) syncFavoritesToSupabase();
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
        ActivityUtils.triggerFeedback(this);
        Runnable deleteAllKeys = new Runnable() {
            @Override
            public void run() {
                DataManager.saveStringData(DataKeys.KEY_DEFAULT_FILTER, "TUTTI");
                DataManager.saveBoolData(DataKeys.KEY_NOTIFICATION_SWITCH, true);
                DataManager.saveBoolData(DataKeys.KEY_NOTIFICATION_STARTWORKS, true);
                DataManager.saveBoolData(DataKeys.KEY_NOTIFICATION_ENDWORKS, true);
                DataManager.saveBoolData(DataKeys.KEY_NOTIFICATION_STRIKES, true);
                DataManager.saveBoolData(DataKeys.KEY_NOTIFICATION_PUSH, true);
                DataManager.saveBoolData(DataKeys.KEY_HAPTIC_FEEDBACKS, true);
                DataManager.saveIntData(DataKeys.KEY_HOURS_NOTIFICATIONS, 10);
                DataManager.saveIntData(DataKeys.KEY_MINUTES_NOTIFICATIONS, 00);
                DataManager.saveArrayStringsData(DataKeys.KEY_FAVORITE_LINES, new HashSet<>());
                DataManager.saveArrayStringsData(DataKeys.KEY_ARRAY_YOUR_LINES, new HashSet<>());
                DataManager.saveArrayStringsData(DataKeys.KEY_ARRAY_RECENT_LINES, new HashSet<>());
                DataManager.saveBoolData(DataKeys.KEY_SHOW_ERROR_MESSAGES, false);
                DataManager.saveBoolData(DataKeys.KEY_SHOW_BANNERS, true);
                DataManager.saveBoolData(DataKeys.KEY_REQUIRE_BIOMETRICS, true);
                DataManager.saveBoolData(DataKeys.KEY_SHOW_TRANSLATE_BUTTON, false);
                DataManager.saveBoolData(DataKeys.KEY_SHOW_RECENT_LINES, true);
                DataManager.saveStringData(DataKeys.KEY_DEFAULT_THEME, "Sistema");

                Toast.makeText(SettingsActivity.this, getString(R.string.settingResettedPopUp), Toast.LENGTH_SHORT).show();
                favorites.clear();
                reloadDatas();
                ThemeSettings.setTheme();

                loadFavorites(images, lines);
            }
        };

        DialogHelper.createDefaultAnswerDialog(this, getString(R.string.areYouSurePopUp), getString(R.string.resetSettingsPopUp), deleteAllKeys);
    }
}