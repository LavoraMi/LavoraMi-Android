package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LinesActivity extends AppCompatActivity {

    LinearLayout containerRecent, headerMetro, containerMetro, containerSub, containerRegioExpress, containerRegional, containerMXP, containerTram, containerTrans, containerFilobus, containerMovibus, containerStav, containerSTAR, containerAutoGuidovie;
    LinearLayout titleRecent, titleMetro, titleSub, titleRegio, titleRegional, titleMXP, titleTram, titleTrans, titleFilobus, titleMovibus, titleStav, titleSTAR, titleAutoguidovie;
    TextView tvNoResults;
    ShimmerFrameLayout loadingLayout;
    EditText searchLines;
    boolean linesLoaded = false;
    boolean isRecentEmpty = false;
    private Set<String> recentLinesSet = new LinkedHashSet<>();
    private final Handler searchHandler = new Handler(Looper.getMainLooper());
    private Runnable searchRunnable;
    private Set<String> linesSaved = new HashSet<>(DataManager.getStringArray(DataKeys.KEY_ARRAY_YOUR_LINES, new HashSet<>()));

    //* BOOLEAN VALUES
    boolean hasRecent, hasMetro, hasSub, hasRegioExpress, hasRegional, hasMXP, hasTrans, hasTram, hasFilobus, hasMovibus, hasStav, hasSTAR, hasAuto;

    //* SUPABASE VALUES
    SessionManager sessionManager;
    SupabaseAPI api;
    Retrofit retrofitAPI;
    private String SupabaseANON, SupabaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lines);

        //*LOCK THE ORIENTATION
        /// In this section of the code, we will block the orientation to PORTRAIT because in LANDSCAPE LavoraMi is not supported.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //*INITIALIZE COMPONENTS
        /// In this section of the code, we initialize all the containers for the sub-menus.
        containerRecent = findViewById(R.id.groupRecent);
        headerMetro = findViewById(R.id.headerMetro);
        containerMetro = findViewById(R.id.groupMetro);
        containerSub = findViewById(R.id.groupSub);
        containerRegioExpress = findViewById(R.id.groupRE);
        containerRegional = findViewById(R.id.groupR);
        containerMXP = findViewById(R.id.groupMXP);
        containerTram = findViewById(R.id.groupTram);
        containerTrans = findViewById(R.id.groupTrans);
        containerFilobus = findViewById(R.id.groupFilobus);
        containerMovibus = findViewById(R.id.groupMovibus);
        containerStav = findViewById(R.id.groupStav);
        containerSTAR = findViewById(R.id.groupStar);
        containerAutoGuidovie = findViewById(R.id.groupAutoGuidoVie);

        titleRecent = findViewById(R.id.headerRecentSearch);
        titleMetro = findViewById(R.id.headerMetro);
        titleSub = findViewById(R.id.headerSuburbane);
        titleRegio = findViewById(R.id.headerRegioExpress);
        titleRegional = findViewById(R.id.headerRegional);
        titleMXP = findViewById(R.id.headerMXP);
        titleTrans = findViewById(R.id.headerTransfrontaliere);
        titleTram = findViewById(R.id.headerTram);
        titleFilobus = findViewById(R.id.headerFilobus);
        titleMovibus = findViewById(R.id.headerMovibus);
        titleStav = findViewById(R.id.headerSTAV);
        titleSTAR = findViewById(R.id.headerSTAR);
        titleAutoguidovie = findViewById(R.id.headerAutoguidovie);
        tvNoResults = findViewById(R.id.emptyView);

        loadingLayout = findViewById(R.id.loadingLayout);
        loadingLayout.startShimmer();

        findViewById(R.id.main).post(() -> {
            findViewById(R.id.main).postDelayed(() -> {
                loadLines();
                linesLoaded = true;
                findViewById(R.id.nestedLinesView).setVisibility(View.VISIBLE);
            }, 500);
        });

        searchLines = findViewById(R.id.editSearch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recentLinesSet = new LinkedHashSet<>(DataManager.getStringArray(DataKeys.KEY_ARRAY_RECENT_LINES, new LinkedHashSet<>()));

        sessionManager = new SessionManager(this);

        //*GET METADATA
        /// In this section of the code, we initialize the SupabaseURL and SupabaseANON variables for performance boost.
        SupabaseANON = getMetaData(this, "supabaseANON");
        SupabaseURL = getMetaData(this, "supabaseURL");

        /// In this section of the code, we initialize the Supabase Server from the keys of the .env file.
        if(SupabaseURL != null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .authenticator(new SupabaseAuthenticator(this, SupabaseANON, SupabaseURL))
                    .build();

            retrofitAPI = new Retrofit.Builder()
                    .baseUrl(SupabaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            api = retrofitAPI.create(SupabaseAPI.class);
        }
        else
            Toast.makeText(this, getString(R.string.connectionErrorToast), Toast.LENGTH_SHORT).show();

        //*WEBSITE LINKS
        /// In this section of the code, we set the default action (OnClick) of the ImageView
        /// From the infoButtons, and create an array with the URLs in it.
        /// In the first section we initialize the arrays, in the second one we
        /// Create a For-Loop for set automatically the action of the button.

        ImageView[] infoButtons = {
            findViewById(R.id.infoIconMetro),
            findViewById(R.id.infoIconSuburbane),
            findViewById(R.id.infoIconRegioExpress),
            findViewById(R.id.infoIconRegional),
            findViewById(R.id.infoIconTransfrontaliere),
            findViewById(R.id.infoIconMXP),
            findViewById(R.id.infoIconTram),
            findViewById(R.id.infoIconFilobus),
            findViewById(R.id.infoIconMovibus),
            findViewById(R.id.infoIconStav),
            findViewById(R.id.infoIconStar),
            findViewById(R.id.infoIconAutoGuidoVie)
        };

        String[] infoUrls = {
            "https://giromilano.atm.it/assets/images/schema_rete_metro.jpg",
            "https://www.trenord.it/linee-e-orari/circolazione/le-nostre-linee/",
            "https://www.trenord.it/linee-e-orari/circolazione/le-nostre-linee/",
            "https://www.trenord.it/linee-e-orari/circolazione/le-nostre-linee/",
            "https://www.tilo.ch",
            "https://www.malpensaexpress.it",
            "https://www.atm.it/it/AltriServizi/Trasporto/Documents/Carta%20ATM_WEB_2025.11.pdf",
            "https://www.atm.it/it/AltriServizi/Trasporto/Documents/Carta%20ATM_WEB_2025.11.pdf",
            "https://movibus.it/news/",
            "https://stavautolinee.it/reti-servite/",
            "https://starmobility.it/orari-autobus/",
            "https://autoguidovie.it/it/avvisi"
        };

        for (int i = 0; i < infoButtons.length; i++) {
            int finalI = i;
            infoButtons[i].setOnClickListener(v -> ActivityUtils.openURLWithTabBuilder(this, getSupportFragmentManager(), infoUrls[finalI]));
        }

        //* SEARCH BAR
        searchLines.setBackgroundResource(R.drawable.bg_edittext_search);

        int iconSize = (int) (22 * getResources().getDisplayMetrics().density);

        Drawable searchIcon = ContextCompat.getDrawable(this, R.drawable.ic_search_small);
        if (searchIcon != null) searchIcon.setBounds(0, 0, iconSize, iconSize);

        Drawable deleteIcon = ContextCompat.getDrawable(this, R.drawable.ic_delete_small);
        if (deleteIcon != null) deleteIcon.setBounds(0, 0, iconSize, iconSize);

        searchLines.setCompoundDrawables(searchIcon, null, null, null);

        int hPadding = (int) (0 * getResources().getDisplayMetrics().density);
        int iconWithPadding = iconSize + hPadding;
        searchLines.setPadding(searchLines.getPaddingLeft(), searchLines.getPaddingTop(), iconWithPadding, searchLines.getPaddingBottom());

        searchLines.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!linesLoaded) return;

                searchHandler.removeCallbacks(searchRunnable);
                searchRunnable = () -> {
                    String query = s.toString().toLowerCase().trim();

                    hasRecent = (query.isEmpty() && DataManager.getBoolData(DataKeys.KEY_SHOW_RECENT_LINES, true));
                    hasMetro = filtraContainer(containerMetro, query);
                    hasSub = filtraContainer(containerSub, query);
                    hasRegioExpress = filtraContainer(containerRegioExpress, query);
                    hasRegional = filtraContainer(containerRegional, query);
                    hasMXP = filtraContainer(containerMXP, query);
                    hasTrans = filtraContainer(containerTrans, query);
                    hasTram = filtraContainer(containerTram, query);
                    hasFilobus = filtraContainer(containerFilobus, query);
                    hasMovibus = filtraContainer(containerMovibus, query);
                    hasStav = filtraContainer(containerStav, query);
                    hasSTAR = filtraContainer(containerSTAR, query);
                    hasAuto = filtraContainer(containerAutoGuidovie, query);

                    //*RECENT LINES
                    titleRecent.setVisibility(hasRecent ? View.VISIBLE : View.GONE);
                    findViewById(R.id.emptyViewRecent).setVisibility((query.isEmpty() && isRecentEmpty) ? View.VISIBLE : View.GONE);
                    containerRecent.setVisibility(hasRecent ? View.VISIBLE : View.GONE);

                    boolean[] firstContainerTracker = { false };

                    //*METRO LINES
                    titleMetro.setVisibility(hasMetro ? View.VISIBLE : View.GONE);
                    containerMetro.setVisibility(hasMetro ? View.VISIBLE : View.GONE);
                    setUpMargin(headerMetro, isFirstVisibleContainer(hasMetro, firstContainerTracker));

                    //*SUBURBAN LINES
                    titleSub.setVisibility(hasSub ? View.VISIBLE : View.GONE);
                    containerSub.setVisibility(hasSub ? View.VISIBLE : View.GONE);
                    setUpMargin(titleSub, isFirstVisibleContainer(hasSub, firstContainerTracker));

                    //*REGIO EXPRESS LINES
                    titleRegio.setVisibility(hasRegioExpress ? View.VISIBLE : View.GONE);
                    containerRegioExpress.setVisibility(hasRegioExpress ? View.VISIBLE : View.GONE);
                    setUpMargin(titleRegio, isFirstVisibleContainer(hasRegioExpress, firstContainerTracker));

                    //*REGIONAL LINES
                    titleRegional.setVisibility(hasRegional ? View.VISIBLE : View.GONE);
                    containerRegional.setVisibility(hasRegional ? View.VISIBLE : View.GONE);
                    setUpMargin(titleRegional, isFirstVisibleContainer(hasRegional, firstContainerTracker));

                    //*MXP LINES
                    titleMXP.setVisibility(hasMXP ? View.VISIBLE : View.GONE);
                    containerMXP.setVisibility(hasMXP ? View.VISIBLE : View.GONE);
                    setUpMargin(titleMXP, isFirstVisibleContainer(hasMXP, firstContainerTracker));

                    //*TRANSFRONTALIERE LINES
                    titleTrans.setVisibility(hasTrans ? View.VISIBLE : View.GONE);
                    containerTrans.setVisibility(hasTrans ? View.VISIBLE : View.GONE);
                    setUpMargin(titleTrans, isFirstVisibleContainer(hasTrans, firstContainerTracker));

                    //*TRAM LINES
                    titleTram.setVisibility(hasTram ? View.VISIBLE : View.GONE);
                    containerTram.setVisibility(hasTram ? View.VISIBLE : View.GONE);
                    setUpMargin(titleTram, isFirstVisibleContainer(hasTram, firstContainerTracker));

                    //*FILOBUS LINES
                    titleFilobus.setVisibility(hasFilobus ? View.VISIBLE : View.GONE);
                    containerFilobus.setVisibility(hasFilobus ? View.VISIBLE : View.GONE);
                    setUpMargin(titleFilobus, isFirstVisibleContainer(hasFilobus, firstContainerTracker));

                    //*MOVIBUS LINES
                    titleMovibus.setVisibility(hasMovibus ? View.VISIBLE : View.GONE);
                    containerMovibus.setVisibility(hasMovibus ? View.VISIBLE : View.GONE);
                    setUpMargin(titleMovibus, isFirstVisibleContainer(hasMovibus, firstContainerTracker));

                    //*STAV LINES
                    titleStav.setVisibility(hasStav ? View.VISIBLE : View.GONE);
                    containerStav.setVisibility(hasStav ? View.VISIBLE : View.GONE);
                    setUpMargin(titleStav, isFirstVisibleContainer(hasStav, firstContainerTracker));

                    //*STAR LINES
                    titleSTAR.setVisibility(hasSTAR ? View.VISIBLE : View.GONE);
                    containerSTAR.setVisibility(hasSTAR ? View.VISIBLE : View.GONE);
                    setUpMargin(titleSTAR, isFirstVisibleContainer(hasSTAR, firstContainerTracker));

                    //*AUTOGUIDOVIE LINES
                    titleAutoguidovie.setVisibility(hasAuto ? View.VISIBLE : View.GONE);
                    containerAutoGuidovie.setVisibility(hasAuto ? View.VISIBLE : View.GONE);
                    setUpMargin(titleAutoguidovie, isFirstVisibleContainer(hasAuto, firstContainerTracker));

                    if (tvNoResults != null){
                        tvNoResults.setVisibility((!hasMetro && !hasSub && !hasRegioExpress && !hasRegional && !hasMXP && !hasTrans && !hasTram && !hasFilobus && !hasMovibus && !hasStav && !hasSTAR && !hasAuto) ? View.VISIBLE : View.GONE);
                        tvNoResults.setText(String.format(getString(R.string.noLinesFound), s));
                    }

                    searchLines.setCompoundDrawables(searchIcon, null, (s.length() > 0) ? deleteIcon : null, null);
                };
                searchHandler.postDelayed(searchRunnable, 150);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        /// If the "X" button is clicked, clean all the text into the EditText
        searchLines.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Drawable drawableEnd = searchLines.getCompoundDrawables()[2];
                if (drawableEnd != null) {
                    int rightBoundary = searchLines.getRight() - searchLines.getPaddingRight();
                    int leftBoundary = rightBoundary - drawableEnd.getBounds().width();
                    if (event.getRawX() >= leftBoundary && event.getRawX() <= rightBoundary) {
                        searchLines.setText("");
                        return true;
                    }
                }
            }
            return false;
        });

        //*NAVBAR
        View btnHome = findViewById(R.id.homeButton);
        btnHome.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            ActivityUtils.changeActivity(this, MainActivity.class);
        });

        View btnSettings = findViewById(R.id.settingsButton);
        btnSettings.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            ActivityUtils.changeActivity(this, SettingsActivity.class);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        linesSaved = new HashSet<>(DataManager.getStringArray(DataKeys.KEY_ARRAY_YOUR_LINES, new HashSet<>()));
        reloadSavedLines();

        reloadRecentLines();

        if(!searchLines.getText().toString().isEmpty()) {
            titleRecent.setVisibility(View.GONE);
            containerRecent.setVisibility(View.GONE);
        }
    }

    public void reloadRecentLines() {
        /// In this function, we will reload the Recent Lines when the Activity is resumed.
        ImageView deleteIconRecent = findViewById(R.id.deleteIconRecent);
        findViewById(R.id.subTitleSearch).setVisibility((DataManager.getBoolData(DataKeys.KEY_SHOW_RECENT_LINES, true)) ? View.VISIBLE : View.GONE);
        findViewById(R.id.headerRecentSearch).setVisibility((DataManager.getBoolData(DataKeys.KEY_SHOW_RECENT_LINES, true)) ? View.VISIBLE : View.GONE);
        findViewById(R.id.groupRecent).setVisibility((DataManager.getBoolData(DataKeys.KEY_SHOW_RECENT_LINES, true)) ? View.VISIBLE : View.GONE);

        setUpMargin(headerMetro, !(DataManager.getBoolData(DataKeys.KEY_SHOW_RECENT_LINES, true)));

        boolean hasRecent = (searchLines.getText().toString().isEmpty() && DataManager.getBoolData(DataKeys.KEY_SHOW_RECENT_LINES, true));
        titleRecent.setVisibility(hasRecent ? View.VISIBLE : View.GONE);
        containerRecent.setVisibility(hasRecent ? View.VISIBLE : View.GONE);

        if (containerRecent != null)
            containerRecent.removeAllViews();

        recentLinesSet = new LinkedHashSet<>(DataManager.getStringArray(DataKeys.KEY_ARRAY_RECENT_LINES, new LinkedHashSet<>()));

        if(!recentLinesSet.isEmpty()){
            deleteIconRecent.setVisibility(View.VISIBLE);
            deleteIconRecent.setOnClickListener(v -> {
                ActivityUtils.triggerFeedback(this);
                new AlertDialog.Builder(this)
                        .setTitle(R.string.areYouSurePopUp)
                        .setMessage(R.string.popUpDeleteRecentDeps)
                        .setNegativeButton(R.string.cancelPopUp, null)
                        .setPositiveButton(R.string.confirmPopUp, (dialog, which) -> {
                            DataManager.saveArrayStringsData(DataKeys.KEY_ARRAY_RECENT_LINES, new LinkedHashSet<>());
                            reloadRecentLines();
                        }).show();
            });

            for (String entry : recentLinesSet) {
                String[] parts = entry.split("\\|");

                if (parts.length == 2) {
                    String title = parts[0];
                    String deps = parts[1];
                    int color = StationDB.getLineColor(this, title);

                    if(deps.contains("Suburban"))
                        deps = getString(R.string.suburban);
                    else if (deps.contains("Tra"))
                        deps = getString(R.string.tramLinesScroll);
                    else if (deps.contains("Region"))
                        deps = getString(R.string.regionalLinesScroll);

                    aggiungiLinea(containerRecent, title, color, deps);
                }
            }
            isRecentEmpty = false;
        }
        else {
            deleteIconRecent.setVisibility(View.GONE);
            isRecentEmpty = true;
        }

        findViewById(R.id.emptyViewRecent).setVisibility((!isRecentEmpty) ? View.GONE : View.VISIBLE);
    }

    private void loadLines(){
        /// In this section of the code, we generate the Lines from the StationsDB file.
        /// Every type of line is sorted in order of importance.

        // METRO
        String[] metroLines = {"M1", "M2", "M3", "M4", "M5"};
        int[] metroColors = {R.color.M1, R.color.M2, R.color.M3, R.color.M4, R.color.M5};
        for (int i = 0; i < metroLines.length; i++) {
            int finalI = i;
            aggiungiLinea(containerMetro, metroLines[finalI], metroColors[finalI], "Metro");
        }

        // SUBURBANE
        String[] suburbanLines = {"S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9",
                "S11", "S12", "S13", "S19", "S31"};
        int[] suburbanColors = {R.color.S1, R.color.S2, R.color.S3, R.color.S4, R.color.S5,
                R.color.S6, R.color.S7, R.color.S8, R.color.S9, R.color.S11,
                R.color.S12, R.color.S13, R.color.S19, R.color.S31};
        for (int i = 0; i < suburbanLines.length; i++) {
            int finalI = i;
            aggiungiLinea(containerSub, suburbanLines[finalI], suburbanColors[finalI], ContextCompat.getString(this, R.string.suburban));
        }

        // REGIO EXPRESS
        String[] regioLines = {"RE1", "RE2", "RE3", "RE4", "RE5", "RE6", "RE7", "RE8", "RE11", "RE13"};
        for (int i = 0; i < regioLines.length; i++) {
            int finalI = i;
            aggiungiLinea(containerRegioExpress, regioLines[finalI], R.color.RE, "Regio Express");
        }

        // REGIONAL
        String[] regionalLines = {"R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R11", "R12",
                "R13", "R14", "R15", "R16", "R17", "R18", "R21", "R22", "R23", "R24", "R25", "R27", "R31", "R32", "R33", "R34",
                "R35", "R36", "R37", "R38", "R39", "R40", "R41"};
        for (int i = 0; i < regionalLines.length; i++) {
            int finalI = i;
            aggiungiLinea(containerRegional, regionalLines[finalI], R.color.REGIONAL, getString(R.string.regionalLinesScroll));
        }

        // TILO
        String[] tiloLines = {"S10", "S20", "S30", "S40", "S50", "S90", "RE80"};
        int[] tiloColors = {R.color.S10, R.color.S20, R.color.S30, R.color.S40, R.color.S50, R.color.S90, R.color.RE80};
        for (int i = 0; i < tiloLines.length; i++) {
            int finalI = i;
            aggiungiLinea(containerTrans, tiloLines[finalI], tiloColors[finalI], ContextCompat.getString(this, R.string.tilo));
        }

        // MXP
        String[] mxpLines = {"MXP1", "MXP2"};
        for (String line : mxpLines) {
            aggiungiLinea(containerMXP, line, R.color.MXP, "Malpensa Express");
        }

        // TRAM
        String[] tramLines = {"1", "2", "3", "4", "5", "7", "9", "10", "12", "14",
                "15", "16", "19", "24", "27", "31", "33"};
        for (String line : tramLines) {
            aggiungiLinea(containerTram, line, R.color.TRAM, getString(R.string.tramLinesScroll));
        }

        // FILOBUS
        String[] filobusLines = {"90", "91", "92", "93"};
        for (String line : filobusLines) {
            aggiungiLinea(containerFilobus, line, R.color.FILOBUS, "Filobus");
        }

        // MOVIBUS
        String[] movibusLines = {"z601", "z602", "z603", "z6C3", "z606", "z611", "z612",
                "z616", "z617", "z618", "z619", "z620", "z621", "z622",
                "z625", "z627", "z636", "z641", "z642", "z643", "z644",
                "z646", "z647", "z649"};
        for (String line : movibusLines) {
            aggiungiLinea(containerMovibus, line, R.color.BUS, "Movibus");
        }

        // STAR
        String[] starLines = {"z501", "z509", "z510", "z515", "z516"};
        for (String line : starLines) {
            aggiungiLinea(containerSTAR, line, R.color.BUS, "STAR Mobility");
        }

        // STAV
        String[] stavLines = {"z551", "z552", "z553", "z554", "z555", "z556",
                "z557", "z559", "z560"};
        for (String line : stavLines) {
            aggiungiLinea(containerStav, line, R.color.BUS, "STAV");
        }

        // AUTOGUIDOVIE
        String[] autoguidovieLines = {"z401", "z402", "z403", "z404", "z405", "z406",
                "z407", "z409", "z410", "z411", "z412", "z413",
                "z415", "z418", "z419", "z420", "z431", "z432",
                "z203", "z205", "z209", "z219", "z221", "z222",
                "z225", "z227", "z228", "z229", "z231", "z232",
                "z233", "z234", "z250", "z251"};
        for (String line : autoguidovieLines) {
            aggiungiLinea(containerAutoGuidovie, line, R.color.BUS, "Autoguidovie");
        }

        //Stop the Shimmer animation.
        loadingLayout.setVisibility(View.GONE);
    }

    private void aggiungiLinea(LinearLayout container, String label, int colorHex, String description) {
        //TODO: Comment better this code.
        View row = getLayoutInflater().inflate(R.layout.item_linea_list, container, false);

        row.setTag(label+"|"+description);

        TextView badge = row.findViewById(R.id.lineBadge);
        TextView name = row.findViewById(R.id.lineName);
        TextView shimmerAnim = row.findViewById(R.id.shimmerTextAnim);
        TextView shimmerBadgeAnim = row.findViewById(R.id.shimmerLineBadge);
        TextView lineBadge = row.findViewById(R.id.lineBadge);

        ImageButton buttonAddLine = row.findViewById(R.id.buttonAddLine);

        if (badge != null && name != null) {
            badge.setText(label);
            name.setText((description + " " +  label));
            shimmerAnim.setVisibility(View.GONE);
            shimmerBadgeAnim.setVisibility(View.GONE);
            lineBadge.setVisibility(View.VISIBLE);

            int colore = ContextCompat.getColor(this, colorHex);

            if (badge.getBackground() != null)
                badge.getBackground().mutate().setTint(colore);
            else
                badge.setBackgroundColor(getResources().getColor(R.color.GRAY));

            row.setOnClickListener(v -> {
                Intent intent = new Intent(this, LinesDetailActivity.class);
                intent.putExtra("NOME_LINEA", label);
                intent.putExtra("TIPO_DI_LINEA", (description + " " + label));

                insertRecentLine(label, description);

                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
            container.addView(row);
            isSavedLine(label, buttonAddLine, container, description);
        }
    }

    private void isSavedLine(String label, ImageButton buttonAddLine, LinearLayout container, String description) {
        //TODO: Comment better this code.
        boolean isEnableSyncWithCloud = DataManager.getBoolData(DataKeys.KEY_SAVE_DB_YOUR_LINES, true);
        Animation scaleDownUp = AnimationUtils.loadAnimation(this, R.anim.scale_down_up);

        if(linesSaved.contains(label)) {
            buttonAddLine.setImageTintList(ColorStateList.valueOf(getColor(R.color.heartColor)));
            buttonAddLine.setImageResource(R.drawable.ic_heart);

            buttonAddLine.setOnClickListener(v -> {
                linesSaved.remove(label);
                DataManager.saveArrayStringsData(DataKeys.KEY_ARRAY_YOUR_LINES, linesSaved);
                if(isEnableSyncWithCloud) syncYourLinesToSupabase(linesSaved);

                buttonAddLine.startAnimation(scaleDownUp);
                buttonAddLine.setImageTintList(ColorStateList.valueOf(getColor(R.color.text_primary)));
                buttonAddLine.setImageResource(R.drawable.ic_heart_empty);
                ActivityUtils.triggerFeedback(this);

                isSavedLine(label, buttonAddLine,container,description);
                if(container.getId() == containerRecent.getId() || recentLinesSet.contains(label+"|"+description))
                    reloadSavedLines();
            });
        }
        else {
            buttonAddLine.setImageTintList(ColorStateList.valueOf(getColor(R.color.text_primary)));
            buttonAddLine.setImageResource(R.drawable.ic_heart_empty);

            buttonAddLine.setOnClickListener(v -> {
                linesSaved.add(label);
                DataManager.saveArrayStringsData(DataKeys.KEY_ARRAY_YOUR_LINES, linesSaved);
                if(isEnableSyncWithCloud) syncYourLinesToSupabase(linesSaved);

                buttonAddLine.startAnimation(scaleDownUp);
                buttonAddLine.setImageTintList(ColorStateList.valueOf(getColor(R.color.heartColor)));
                buttonAddLine.setImageResource(R.drawable.ic_heart);
                ActivityUtils.triggerFeedback(this);

                isSavedLine(label, buttonAddLine,container,description);
                if(container.getId() == containerRecent.getId() || recentLinesSet.contains(label+"|"+description))
                    reloadSavedLines();
            });
        }
    }

    public void reloadSavedLines(){
        LinearLayout [] containers = {containerRecent, containerMetro, containerSub, containerRegioExpress, containerRegional, containerMXP, containerTram, containerTrans, containerFilobus, containerMovibus, containerStav, containerSTAR, containerAutoGuidovie};
        for (LinearLayout container : containers) {
            int numeroLinee = container.getChildCount();

            for (int i = 0; i < numeroLinee; i++) {
                View row = container.getChildAt(i);
                String linea = (String) row.getTag();

                if (linea != null) {
                    String [] line= linea.split("\\|");
                    ImageButton buttonAddLine = row.findViewById(R.id.buttonAddLine);

                    if (buttonAddLine != null)
                        isSavedLine(line[0], buttonAddLine, container, line[1]);
                }
            }
        }
    }

    private boolean filtraContainer(LinearLayout container, String query) {
        boolean trovatoAtLeastOne = false;

        for (int i = 0; i < container.getChildCount(); i++) {
            View row = container.getChildAt(i);
            TextView badge = row.findViewById(R.id.lineBadge);
            TextView name = row.findViewById(R.id.lineName);

            if (badge != null && name != null) {
                String bText = badge.getText().toString().toLowerCase();
                String nText = name.getText().toString().toLowerCase();

                if ((bText.contains(query) || nText.contains(query)) || query.isEmpty()) {
                    row.setVisibility(View.VISIBLE);
                    trovatoAtLeastOne = true;
                }
                else
                    row.setVisibility(View.GONE);
            }
        }

        return trovatoAtLeastOne;
    }

    private void setUpMargin(LinearLayout layout, boolean isActive) {
        /// In this method, we adjust the Layout Margins when searching for a transport category.
        /// @PARAMETERS
        /// LinearLayout layout is the Header of the transport category.
        /// boolean isActive is the bool variable that define if a Header is enable (the user is searching for that) or not.

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();

        layoutParams.topMargin = isActive ? 0 : (int)(20 * getResources().getDisplayMetrics().density);
    }

    private void insertRecentLine(String nameLine, String description) {
        /// In this function, we add the line that the user have clicked to the recent researched ones.
        /// @PARAMETERS
        /// String nameLine is the name of the line, also called "title".
        /// String description is the Company of the line, can be for example: "STAV".

        description=(description.contains("Suburban")) ? "Suburbano" : description;
        String combinedEntry = nameLine + "|" + description;
        recentLinesSet.remove(combinedEntry);

        if(recentLinesSet.size() >= 5) {
            String oldestEntry = recentLinesSet.iterator().next();
            recentLinesSet.remove(oldestEntry);
        }

        recentLinesSet.add(combinedEntry);
        DataManager.saveArrayStringsData(DataKeys.KEY_ARRAY_RECENT_LINES, recentLinesSet);
    }

    private boolean isFirstVisibleContainer(boolean isContainerActive, boolean[] tracker) {
        /// This method fix the issue where EVERY LinearLayout is set to 0dp.

        if (!isContainerActive)  return false;

        if (!tracker[0]) {
            tracker[0] = true;
            return true;
        }

        return false;
    }

    private void syncYourLinesToSupabase(Set<String> yourLinesSet) {
        if (sessionManager != null && sessionManager.isLoggedIn()) {
            Set<String> favoritesSet = DataManager.getStringArray(DataKeys.KEY_FAVORITE_LINES, new java.util.HashSet<>());

            ArrayList<String> favoritesList = new ArrayList<>(favoritesSet);
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
                public void onSuccess(Void result) {Log.d("SUPABASE_SYNC", "Tue Linee (Cuore) aggiornate nel cloud!");}

                @Override
                public void onError(String error) {Log.e("SUPABASE_SYNC", "Errore salvataggio Cuore nel cloud: " + error);}
            });
        }
    }
}