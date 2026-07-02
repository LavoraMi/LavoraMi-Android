package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.util.Log;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.os.LocaleListCompat;
import androidx.core.text.HtmlCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ArrayList<EventDescriptor> events = new ArrayList<EventDescriptor>();
    private ArrayList<EventDescriptor> eventsDisplay = new ArrayList<EventDescriptor>();
    private final List<NativeAd> pendingBatch = new ArrayList<>();
    private ShimmerFrameLayout loadingLayout;
    private LinearLayout errorLayout;
    private WorkAdapter adapter;
    private CategoriesEnum defaultCategory;
    private boolean hasCompletedSetup;
    private static ExecutorService threadManager = Executors.newFixedThreadPool(3);
    private StrikeDescriptor strikeCDNResponse;
    private ImageButton btnRefresh;
    private MaterialButton btnSetupNext;
    private Animation animSpin;
    static String maintenanceDetails = "";
    private boolean strikeBannerClosed = false;
    private boolean  definitelyClosedSavedLinesHint;
    public static boolean alreadyRefreshedLines = false;
    private List<NativeAd> mNativeAds = new ArrayList<>();

    //*HINT VARIABLES
    /// In this section of the code, we will create the variables for our HintAnimations
    private TextSwitcher hintSwitcher;
    private EditText editSearch;
    private int indexHintAnimation;
    private final Handler handler = new Handler(Looper.getMainLooper());

    //*DATABASE SYNCH VARIABLES
    /// In this section of the code, we will create the variables for synch datas to DB ones.
    private SessionManager sessionManager;
    private SupabaseDataManager supabaseDataManager;
    SupabaseAPI api;
    Retrofit retrofitAPI;
    private String SupabaseANON, SupabaseURL;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if(isGranted){
                    Log.d("PERMISSIONS", "Notifiche concesse!");
                    if (EventData.listaEventiCompleta != null && !EventData.listaEventiCompleta.isEmpty())
                        NotificationScheduler.scheduleWorkNotifications(MainActivity.this, EventData.listaEventiCompleta);
                }
                else
                    Log.e("PERMISSIONS", "Permesso notifiche rifiutato.");

                if (btnSetupNext != null){
                    btnSetupNext.setEnabled(true);
                    btnSetupNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.redMetro)));
                }
            });
    private final ActivityResultLauncher<String> requestLocationPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted)
                    btnSetupNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.redMetro)));
                else
                    btnSetupNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.redMetro)));

                btnSetupNext.setEnabled(true);
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        defaultCategory = CategoriesEnum.valueOf(DataManager.getStringData(DataKeys.KEY_DEFAULT_FILTER, "TUTTI").toUpperCase());
        ThemeSettings.setTheme();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        String[] hints = {getString(R.string.hintMain1), getString(R.string.hintMain2), getString(R.string.hintMain3), getString(R.string.hintMain4), getString(R.string.hintMain5), getString(R.string.hintMain6), getString(R.string.hintMain7)};

        //*LOCK THE ORIENTATION
        /// In this section of the code, we will block the orientation to PORTRAIT because in LANDSCAPE LavoraMi is not supported.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

        sessionManager = new SessionManager(this);

        if (sessionManager.isLoggedIn()) {
            supabaseDataManager = new SupabaseDataManager(
                    this,
                    api,
                    SupabaseANON,
                    sessionManager.getToken(),
                    sessionManager.getUserEmail()
            );

            loadUserPreferences();
        }

        MobileAds.initialize(this, initializationStatus -> {loadNativeAds();});

        //*SETUP PAGES
        /// In this section of the code, we create the Setup-Pages for our OnBoarding screen.
        /// This method is also used into the iOS version of LavoraMi.
        hasCompletedSetup = DataManager.getBoolData(DataKeys.KEY_END_SETUP, false);

        ConstraintLayout setupOverlay = findViewById(R.id.setupOverlay);
        if(hasCompletedSetup)
            setupOverlay.setVisibility(View.GONE);
        else{
            setupOverlay.setVisibility(View.VISIBLE);
            findViewById(R.id.floatingBottomBar).setVisibility(View.GONE);
        }

        List<SetupModels.SetupPage> pages = new ArrayList<>();
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle1), getString(R.string.setupDeps1), R.drawable.ic_app, ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle2), getString(R.string.setupDeps2), R.drawable.ic_map, ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle3), getString(R.string.setupDeps3), R.drawable.ic_star_fill, ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle4), getString(R.string.setupDeps4), R.drawable.ic_bell_fill, ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitleTranslate), getString(R.string.setupDepsTranslate), R.drawable.ic_translate, getString(R.string.setupMiniDepsTranslate)));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitlePosition), getString(R.string.setupDepsPosition), R.drawable.ic_location, ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupAccessibilityTitle), getString(R.string.setupDepsAccessiblity), R.drawable.ic_accessibility, ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle5), getString(R.string.setupDeps5), R.drawable.ic_lock, getString(R.string.setupMiniDetails)));

        ViewPager2 viewPager = findViewById(R.id.setupViewPager);
        SetupModels.SetupAdapter adapter = new SetupModels.SetupAdapter(pages);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.setupIndicator);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {}).attach();

        /// In this section of the code, we set-up the "NEXT" and "SKIP" buttons from the view.
        btnSetupNext = findViewById(R.id.btnSetupNext);
        btnSetupNext.setOnClickListener(v -> {
            int currentPage = viewPager.getCurrentItem();

            if(currentPage < pages.size() - 1)
                viewPager.setCurrentItem(currentPage + 1);
            else {
                DataManager.saveBoolData(DataKeys.KEY_END_SETUP, true);
                setupOverlay.setVisibility(View.GONE);
                findViewById(R.id.floatingBottomBar).setVisibility(View.VISIBLE);
                hasCompletedSetup = true;
                downloadJSONData(defaultCategory, false);
            }

            /// In this section, we ask the permission of notifications to the user, beacuse in this Index there is the "Notification" page.
            if(currentPage == 3)
                askForNotificationPermission();
            if(currentPage == 5)
                askForPositionPermission();
        });

        Button btnSetupSkip = findViewById(R.id.btnSetupSkip);
        btnSetupSkip.setOnClickListener(v ->{
            /// In this section of the code, we ask the user if he wants to skip the Setup.
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.areYouSurePopUp))
                    .setMessage(getString(R.string.skipConfigDeps))
                    .setNegativeButton(getString(R.string.cancelPopUp), null)
                    .setPositiveButton(getString(R.string.confirmPopUp), ((dialog, which) -> {
                        DataManager.saveBoolData(DataKeys.KEY_END_SETUP, true);
                        setupOverlay.setVisibility(View.GONE);
                        findViewById(R.id.floatingBottomBar).setVisibility(View.VISIBLE);
                        askForNotificationPermission();
                        askForPositionPermission();
                    }))
                    .create()
                    .show();
        });

        /// In this section we also set the "NEXT" button to "END" when it's the last Page.
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
            super.onPageSelected(position);

            btnSetupNext.setText((position == pages.size() -1) ? getString(R.string.endPages) : getString(R.string.nextPages));
            btnSetupSkip.setVisibility((position == pages.size() -1) ? View.GONE : View.VISIBLE);
            if(position == 3)
                askForNotificationPermission();
            if(position == 5)
                askForPositionPermission();
            }
        });

        //*INITIALIZE THE LOADING LAYOUT
        /// In this section of the code, we will initialize the loading layout of the Application.
        loadingLayout = findViewById(R.id.loadingLayout);
        errorLayout = findViewById(R.id.errorNetwork);
        animSpin = AnimationUtils.loadAnimation(this, R.anim.rotate_360);
        btnRefresh = findViewById(R.id.buttonRefresh);

        if(loadingLayout != null){
            loadingLayout.startShimmer();
            loadingLayout.setVisibility(View.VISIBLE);
            btnRefresh.setVisibility(View.VISIBLE);
            btnRefresh.startAnimation(animSpin);
            findViewById(R.id.recyclerView).setVisibility(View.GONE);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //*SEARCHBAR HINT ANIMATION
        /// In this section of the code, we set-up the Animation for our Hint in searchEditText.
        editSearch = findViewById(R.id.editSearch);
        hintSwitcher = findViewById(R.id.hintSwitcher);

        hintSwitcher.setFactory(() -> {
            TextView title = new TextView(MainActivity.this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            title.setLayoutParams(params);
            title.setGravity(Gravity.CENTER_VERTICAL);
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            title.setTextColor(Color.parseColor("#666666"));
            title.setPadding(5, 0, 0, 0);

            Typeface tf = ResourcesCompat.getFont(MainActivity.this, R.font.inter);
            title.setTypeface(tf);

            return title;
        });

        hintSwitcher.setCurrentText(getString(R.string.hintMain1));

        hintSwitcher.setInAnimation(this, R.anim.slide_up_in);
        hintSwitcher.setOutAnimation(this, R.anim.slide_up_out);

        startHintLoop(hints);

        //*NAVBAR
        View btnLines = findViewById(R.id.linesButton);
        btnLines.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            ActivityUtils.changeActivity(this, LinesActivity.class);
        });

        View btnSettings = findViewById(R.id.settingsButton);
        btnSettings.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            ActivityUtils.changeActivity(this, SettingsActivity.class);
        });

        //*REFRESH BUTTON
        Button btnRefreshOnError = findViewById(R.id.btnRefreshOnError);

        btnRefresh.setOnClickListener(v -> {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
            editSearch.clearFocus();
            editSearch.setText("");
            downloadJSONData(getCategory(), true);}
        );

        btnRefreshOnError.setOnClickListener(v -> {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
            editSearch.clearFocus();
            editSearch.setText("");
            downloadJSONData(getCategory(), true);
        });

        //* CHIP GROUP (FILTERS)
        ChipGroup filterGroup = findViewById(R.id.filterChipGroup);

        if(filterGroup != null){
            filterGroup.setSaveEnabled(false);

            for (int i = 0; i < filterGroup.getChildCount(); i++) {filterGroup.getChildAt(i).setSaveEnabled(false);}

            switch(defaultCategory){
                case LE_TUE_LINEE:
                    filterGroup.check(R.id.chipYourLines);
                    break;
                case TUTTI:
                    filterGroup.check(R.id.chipAll);
                    break;
                case BUS:
                    filterGroup.check(R.id.chipBus);
                    break;
                case TRAM:
                    filterGroup.check(R.id.chipTram);
                    break;
                case METROPOLITANA:
                    filterGroup.check(R.id.chipMetro);
                    break;
                case TRENO:
                    filterGroup.check(R.id.chipTreno);
                    break;
                case IN_CORSO:
                    filterGroup.check(R.id.chipInCorso);
                    break;
                case PROGRAMMATI:
                    filterGroup.check(R.id.chipProgrammati);
                    break;
                case DI_ATM:
                    filterGroup.check(R.id.chipATM);
                    break;
                case DI_TRENORD:
                    filterGroup.check(R.id.chipTrenord);
                    break;
                case DI_MOVIBUS:
                    filterGroup.check(R.id.chipMovibus);
                    break;
                case DI_STAV:
                    filterGroup.check(R.id.chipStav);
                    break;
                case DI_STAR:
                    filterGroup.check(R.id.chipStar);
                    break;
                case DI_AUTOGUIDOVIE:
                    filterGroup.check(R.id.chipAutoguidovie);
                    break;
            }

            for (int i = 0; i < filterGroup.getChildCount(); i++) {
                View child = filterGroup.getChildAt(i);

                if (child instanceof Chip) {
                    Chip chip = (Chip) child;

                    chip.setChipStrokeWidth(3f);
                    chip.setChipStrokeColor(ColorStateList.valueOf(Color.parseColor("#CCCCCC")));
                }
            }

            int checkedId = filterGroup.getCheckedChipId();
            HorizontalScrollView filterScroll = findViewById(R.id.filterScroll);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    View selectedChip = filterGroup.findViewById(checkedId);
                    if (selectedChip != null && (selectedChip != filterGroup.findViewById(R.id.chipAll) && selectedChip != filterGroup.findViewById((R.id.chipBus)))) {
                        int targetX = selectedChip.getLeft() - 55;//allineato alla search bar
                        filterScroll.smoothScrollTo(targetX, 0);
                    }
                }
            });
        }

        //* SEARCH BAR
        editSearch.setBackgroundResource(R.drawable.bg_edittext_search);
        int iconSize = (int) (22 * getResources().getDisplayMetrics().density);

        Drawable searchIcon = ContextCompat.getDrawable(this, R.drawable.ic_search_small);
        if (searchIcon != null) searchIcon.setBounds(0, 0, iconSize, iconSize);

        Drawable deleteIcon = ContextCompat.getDrawable(this, R.drawable.ic_delete_small);
        if (deleteIcon != null) deleteIcon.setBounds(0, 0, iconSize, iconSize);

        editSearch.setCompoundDrawables(searchIcon, null, null, null);

        int hPadding = (int) (0 * getResources().getDisplayMetrics().density);
        int iconWithPadding = iconSize + hPadding;
        editSearch.setPadding(editSearch.getPaddingLeft(), editSearch.getPaddingTop(), iconWithPadding, editSearch.getPaddingBottom());

        editSearch.setOnEditorActionListener((v, actionID, event) -> {
            if(actionID == EditorInfo.IME_ACTION_DONE){
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
                editSearch.clearFocus();
                return true;
            }

            return false;
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hintSwitcher.setVisibility(s.length() > 0 ? View.GONE : View.VISIBLE);
                if (s.length() > 0 && filterGroup != null) {
                    filterGroup.clearCheck();
                    editSearch.setCompoundDrawables(searchIcon, null, deleteIcon, null);
                }
                else
                    editSearch.setCompoundDrawables(searchIcon, null, null, null);

                String testoRicerca = s.toString().toLowerCase().trim();
                filtra(testoRicerca, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        /// If the "X" button is clicked, clean all the text into the EditText
        editSearch.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Drawable drawableEnd = editSearch.getCompoundDrawables()[2];
                if (drawableEnd != null) {
                    int rightBoundary = editSearch.getRight() - editSearch.getPaddingRight();
                    int leftBoundary = rightBoundary - drawableEnd.getBounds().width();
                    if (event.getRawX() >= leftBoundary && event.getRawX() <= rightBoundary) {
                        editSearch.setText("");
                        return true;
                    }
                }
            }
            return false;
        });

        //* LISTENER PER I FILTRI (CHIP)
        definitelyClosedSavedLinesHint = DataManager.getBoolData(DataKeys.KEY_HINT_SAVED_LINES_CLOSED, false);
        if (filterGroup != null) {
            filterGroup.setOnCheckedChangeListener((group, checkedId) -> {
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                int densita = (int)getResources().getDisplayMetrics().density;
                if (checkedId == R.id.chipYourLines) {
                    if (!definitelyClosedSavedLinesHint) {
                        View infoSavedLines = findViewById(R.id.infoSavedLine);
                        infoSavedLines.setVisibility(View.VISIBLE);
                        showTutorialDialog();
                        definitelyClosedSavedLinesHint = true;
                        DataManager.saveBoolData(DataKeys.KEY_HINT_SAVED_LINES_CLOSED, true);
                        infoSavedLines.setOnClickListener(v -> showTutorialDialog());
                    }
                    else {
                        View infoSavedLines = findViewById(R.id.infoSavedLine);
                        infoSavedLines.setVisibility(View.VISIBLE);
                        findViewById(R.id.recyclerView).setPadding(16 *densita,42*densita,16*densita,120*densita);
                        infoSavedLines.setOnClickListener(v -> showTutorialDialog());
                    }
                }
                else {
                    findViewById(R.id.infoSavedLine).setVisibility(View.GONE);
                    findViewById(R.id.recyclerView).setPadding(16 *densita,11*densita,16*densita,112*densita);
                }
                if (checkedId == View.NO_ID)
                    filterGroup.check(R.id.chipAll);
                else {
                    if (checkedId != R.id.chipAll)
                        editSearch.setText("");
                    Chip selectedChip = findViewById(checkedId);

                    if (selectedChip != null) {
                        CategoriesEnum categoria = getCategory();
                        ActivityUtils.triggerFeedback(this);
                        applicaFiltroCategoria(categoria);
                    }
                }

                recyclerView.scrollToPosition(0);
            });
        }

        //*SETUP LANGUAGE
        /// Setting up the language of the application base from the Data saved
        String savedLang = DataManager.getStringData(DataKeys.KEY_DEFAULT_LANGUAGE, "🇮🇹 Italiano");
        String langCode = savedLang.contains("English") ? "en" : savedLang.contains("Spanish") ? "es" : "it";
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(langCode));

        //*CHIP COLORS
        /// In this section of the code, we define the Chip colors for better visibility.
        Chip[] filterChips = {
            findViewById(R.id.chipYourLines),
            findViewById(R.id.chipAll),
            findViewById(R.id.chipBus),
            findViewById(R.id.chipTram),
            findViewById(R.id.chipMetro),
            findViewById(R.id.chipTreno),
            findViewById(R.id.chipInCorso),
            findViewById(R.id.chipProgrammati),
            findViewById(R.id.chipATM),
            findViewById(R.id.chipTrenord),
            findViewById(R.id.chipMovibus),
            findViewById(R.id.chipStav),
            findViewById(R.id.chipStar),
            findViewById(R.id.chipAutoguidovie)
        };
        int colorChecked = ContextCompat.getColor(this, R.color.White);
        int colorUnchecked = ContextCompat.getColor(this, R.color.text_primary);

        ColorStateList chipColors = new ColorStateList(
            new int[][]{
                new int[]{ android.R.attr.state_checked },
                new int[]{ -android.R.attr.state_checked }
            },
            new int[]{
                colorChecked,
                colorUnchecked
            }
        );

        Typeface fontChip = ResourcesCompat.getFont(MainActivity.this, R.font.font_main);

        for(Chip chip : filterChips) {
            chip.setTextColor(chipColors);
            chip.setChipIconTint(chipColors);
            chip.setTypeface(fontChip);
        }

        //*DOWNLOADING EVENTS
        downloadJSONData(defaultCategory, false);

        CategoriesEnum categoriaFinale = defaultCategory;
        if (filterGroup != null)
            filterGroup.post(() -> applicaFiltroCategoria(categoriaFinale));

        //*UI REFINEMENTS
        /// In this section of the code, we fix the bug of the "Work in Progress" dimensions.
        TextView mainTextView = findViewById(R.id.mainTextView);

        if(mainTextView.getText().toString().contains("Work in progress"))
            mainTextView.setTextSize(30);
    }

    private void startHintLoop(String[] hints){
        handler.post(new Runnable() {
            @Override
            public void run() {
                hintSwitcher.setText(hints[indexHintAnimation]);
                indexHintAnimation = (indexHintAnimation + 1) % hints.length;
                handler.postDelayed(this, 5000);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
        editSearch.clearFocus();
        editSearch.setText("");

        CategoriesEnum categorySelected = getCategory();

        downloadJSONData(categorySelected, false);
        checkForStrikes();
    }

    private void askForNotificationPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                btnSetupNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.GRAY)));
                btnSetupNext.setEnabled(false);
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
            else {
                btnSetupNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.redMetro)));
                btnSetupNext.setEnabled(true);
            }
        }
    }

    private void askForPositionPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            btnSetupNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.GRAY)));
            btnSetupNext.setEnabled(false);
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            btnSetupNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.redMetro)));
            btnSetupNext.setEnabled(true);
        }
    }

    public CategoriesEnum getCategory() {
        ChipGroup filterGroup = findViewById(R.id.filterChipGroup);
        if (filterGroup == null) return CategoriesEnum.TUTTI;

        int checkedId = filterGroup.getCheckedChipId();
        if (checkedId == View.NO_ID || checkedId == R.id.chipAll)
            return CategoriesEnum.TUTTI;

        int[] chipIDS = {
            R.id.chipYourLines,
            R.id.chipAll,
            R.id.chipBus,
            R.id.chipTram,
            R.id.chipMetro,
            R.id.chipTreno,
            R.id.chipInCorso,
            R.id.chipProgrammati,
            R.id.chipATM,
            R.id.chipTrenord,
            R.id.chipMovibus,
            R.id.chipStav,
            R.id.chipStar,
            R.id.chipAutoguidovie
        };

        CategoriesEnum[] arrayEnums = {
            CategoriesEnum.LE_TUE_LINEE,
            CategoriesEnum.TUTTI,
            CategoriesEnum.BUS,
            CategoriesEnum.TRAM,
            CategoriesEnum.METROPOLITANA,
            CategoriesEnum.TRENO,
            CategoriesEnum.IN_CORSO,
            CategoriesEnum.PROGRAMMATI,
            CategoriesEnum.DI_ATM,
            CategoriesEnum.DI_TRENORD,
            CategoriesEnum.DI_MOVIBUS,
            CategoriesEnum.DI_STAV,
            CategoriesEnum.DI_STAR,
            CategoriesEnum.DI_AUTOGUIDOVIE
        };

        for (int i = 0; i < arrayEnums.length; i++) {
            if(checkedId == chipIDS[i])
                return arrayEnums[i];
        }

        return CategoriesEnum.TUTTI;
    }

    public void downloadJSONData(CategoriesEnum categoryToFilter, boolean reloadingDatas) {
        /// In this section of the code, we create a CACHE Memory to save the JSON Downloaded.
        /// This part is very important because avoids to use Bandwith of our CDN for nothing.
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MaterialCardView strikeBanner = findViewById(R.id.strikeBanner);

        if (EventData.listaEventiCompleta != null && !EventData.listaEventiCompleta.isEmpty() && !reloadingDatas) {
            if (loadingLayout != null) {
                loadingLayout.setVisibility(View.GONE);
                btnRefresh.clearAnimation();
                errorLayout.setVisibility(View.GONE);
                findViewById(R.id.recyclerView).setVisibility(View.VISIBLE);
            }

            events = new ArrayList<>(EventData.listaEventiCompleta);
            eventsDisplay = new ArrayList<>(events);

            recyclerView = findViewById(R.id.recyclerView);
            if (recyclerView.getLayoutManager() == null) recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            adapter = new WorkAdapter(MainActivity.this, new ArrayList<>(events));
            if (!mNativeAds.isEmpty()) adapter.setAdsList(mNativeAds);
            recyclerView.setAdapter(adapter);

            applicaFiltroCategoria(categoryToFilter);
            checkForStrikes();
            return;
        }

        strikeBannerClosed = (reloadingDatas) ? false : strikeBannerClosed;

        if (loadingLayout != null) {
            loadingLayout.setVisibility(View.VISIBLE);
            errorLayout.setVisibility(View.GONE);
            loadingLayout.startShimmer();
            btnRefresh.startAnimation(animSpin);
            recyclerView.setVisibility(View.GONE);
            strikeBanner.setVisibility(View.GONE);
        }

        APIWorks apiworks = RetrofitManager.get().create(APIWorks.class);

        /// In this section of the code, we fetch the lavoriAttuali.json file from our CDN and, after the succesfull operation, we create the Event ArrayList.
        apiworks.getLavori().enqueue(new Callback<ArrayList<EventDescriptor>>() {
            @Override
            public void onResponse(Call<ArrayList<EventDescriptor>> call, Response<ArrayList<EventDescriptor>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<EventDescriptor> datiRaw = response.body();
                    int totale = datiRaw.size();
                    if (totale == 0) {
                        runOnUiThread(() -> {
                            if (loadingLayout != null) loadingLayout.setVisibility(View.GONE);
                            applicaFiltroCategoria(categoryToFilter);
                        });
                        return;
                    }

                    AtomicInteger pronti = new AtomicInteger(0);

                    if (datiRaw.isEmpty()) {
                        runOnUiThread(() -> {
                            if (loadingLayout != null) loadingLayout.setVisibility(View.GONE);
                            applicaFiltroCategoria(categoryToFilter);
                        });
                        return;
                    }

                    int BATCH_SIZE = 50;
                    int totalBatches = (int) Math.ceil((double) datiRaw.size() / BATCH_SIZE);
                    AtomicInteger completedBatches = new AtomicInteger(0);

                    for (int i = 0; i < datiRaw.size(); i += BATCH_SIZE) {
                        final int batchStart = i;
                        final int batchEnd = Math.min(i + BATCH_SIZE, datiRaw.size());

                        threadManager.submit(() -> {
                            for (int j = batchStart; j < batchEnd; j++) {
                                EventDescriptor lavoro = datiRaw.get(j);
                                lavoro.setEndDateMillis(DateUtils.toMillis(lavoro.getEndDate()));
                                lavoro.setStartDateMillis(DateUtils.toMillis(lavoro.getStartDate()));
                            }

                            int completati = completedBatches.incrementAndGet();

                            if (completati == totalBatches) {
                                runOnUiThread(() -> {
                                    events = datiRaw;
                                    EventData.listaEventiCompleta = events;
                                    if(!alreadyRefreshedLines) {
                                        new Thread(() -> NotificationScheduler.scheduleWorkNotifications(MainActivity.this, EventData.listaEventiCompleta)).start();
                                        alreadyRefreshedLines = true;
                                    }

                                    if (adapter == null) {
                                        adapter = new WorkAdapter(MainActivity.this, new ArrayList<>(events));
                                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        recyclerView.setAdapter(adapter);
                                    }

                                    applicaFiltroCategoria(getCategory());

                                    if (loadingLayout != null) {
                                        loadingLayout.stopShimmer();
                                        loadingLayout.setVisibility(View.GONE);
                                        btnRefresh.clearAnimation();
                                        findViewById(R.id.recyclerView).setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EventDescriptor>> call, Throwable t) {
                //* ON FAILURE, ACTIVATE THE "ERROR" LAYOUT
                if (loadingLayout != null) {
                    boolean showErrorMessage = DataManager.getBoolData(DataKeys.KEY_SHOW_ERROR_MESSAGES, false);
                    TextView errorDeps = findViewById(R.id.errorDeps);
                    ImageView iconWiFi = findViewById(R.id.iconWifi);

                    errorDeps.setText(t.getMessage());
                    errorDeps.setVisibility((showErrorMessage) ? View.VISIBLE : View.GONE);
                    EventData.networkError = true;

                    loadingLayout.setVisibility(View.GONE);
                    errorLayout.setVisibility(View.VISIBLE);
                    strikeBanner.setVisibility(View.GONE);
                    btnRefresh.clearAnimation();

                    /// In this section of the code, we check the android version and adapt the style to that version.
                    iconWiFi.setImageResource((Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) ? R.drawable.ic_no_wifi_connection : R.drawable.ic_wifi_slash_android10_previous);
                }
            }
        });

        checkForStrikes();
    }

    private void checkForStrikes(){
        /// In this section of the code, we GET the '_vars.json' file from our CDN and load the Strikes Configuration.

        APIWorks apiworks = RetrofitManager.get().create(APIWorks.class);
        MaterialCardView strikeBanner = findViewById(R.id.strikeBanner);

        apiworks.getStrike().enqueue(new Callback<StrikeDescriptor> () {
            @Override
            public void onResponse(Call<StrikeDescriptor> call, Response<StrikeDescriptor> response) {
                if(response.isSuccessful()) {
                    strikeCDNResponse = response.body();
                    updateStrike(strikeCDNResponse);
                }
                else
                    strikeBanner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<StrikeDescriptor> call, Throwable t) {
                if (loadingLayout != null) {
                    boolean showErrorMessage = DataManager.getBoolData(DataKeys.KEY_SHOW_ERROR_MESSAGES, false);
                    TextView errorDeps = findViewById(R.id.errorDeps);

                    errorDeps.setText(t.getMessage());
                    errorDeps.setVisibility((showErrorMessage) ? View.VISIBLE : View.GONE);
                    strikeBanner.setVisibility(View.GONE);

                    loadingLayout.setVisibility(View.GONE);
                    errorLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        apiworks.getRequirements().enqueue(new Callback<RequirementsDescriptor> () {
            @Override
            public void onResponse(Call<RequirementsDescriptor> call, Response<RequirementsDescriptor> response) {
                if(response.isSuccessful())
                    updateRequirements(response.body());
            }

            @Override
            public void onFailure(Call<RequirementsDescriptor> call, Throwable t) {
                Log.d("REQUIREMENTS_GET", "Errore durante il retrieve del Requirements.json");
            }
        });
    }

    private void updateRequirements(RequirementsDescriptor descriptor) {
        /// In this method, we update the Requirements backend, with the details catched before from the CDN.
        /// @PARAMETER
        /// RequirementsDescriptor descriptor is the response body from the CDN Request executed before this method.

        String versionMinimum = descriptor.getMinimumVersionAndroid();
        String currentVersion = ContextCompat.getString(this, R.string.app_version);

        String savedLang = DataManager.getStringData(DataKeys.KEY_DEFAULT_LANGUAGE, "🇮🇹 Italiano");
        String langCode = savedLang.contains("English") ? "en" : savedLang.contains("Spanish") ? "es" : "it";

        int responseComparable = RequirementsDescriptor.compareSemanticVersions(currentVersion, versionMinimum);

        //*CHECK FOR MAINTENANCE
        /// Check if LavoraMi is in maintenance mode and get the details from the CDN.
        boolean isInMaintenanceMode = descriptor.isMaintenanceEnabled();
        maintenanceDetails = (langCode.equalsIgnoreCase("en")) ? descriptor.getMaintenanceDepsEnglish() : descriptor.getMaintenanceDeps();

        if(isInMaintenanceMode)
            ActivityUtils.changeActivity(this, MaintenanceActivity.class);

        if(responseComparable < 0)
            ActivityUtils.changeActivity(this, ObsoleteVersion.class);
    }

    private void updateStrike(StrikeDescriptor strikeDescriptor) {
        /// In this method, we update the Strike Banner TextViews, with the strikeDetails catched before from the CDN.
        /// In this method, we set also the Advanced Options Key for "Show Strikes Banner" setting.
        /// @PARAMETER
        /// StrikeDescriptor strikeDescriptor is the response body from the CDN Request executed before this method.

        if(DataManager.getBoolData(DataKeys.KEY_SHOW_BANNERS, true) && hasCompletedSetup){
            MaterialCardView strikeBanner = findViewById(R.id.strikeBanner);
            TextView strikeDesc = findViewById(R.id.strikeDesc);
            TextView strikeGuaranteed = findViewById(R.id.strikeGuaranteed);
            TextView strikeCompanies = findViewById(R.id.strikeCompanies);
            TextView liveText = findViewById(R.id.liveText);

            ImageView closeBtn = findViewById(R.id.closeBtn);

            View strikeOpenClose = findViewById(R.id.strikeOpenClose);
            View liveBarrier = findViewById(R.id.liveBarrier);
            View liveDot = findViewById(R.id.liveDot);

            LinearLayout liveLayout = findViewById(R.id.liveLayout);

            strikeBanner.setVisibility((strikeDescriptor.isStrikeEnabled()) ? View.VISIBLE : View.GONE);
            if (strikeDescriptor.isStrikeToday()) {
                Animation blinkAnim = AnimationUtils.loadAnimation(this, R.anim.live_blink);

                liveBarrier.setVisibility(View.VISIBLE);
                liveLayout.setVisibility(View.VISIBLE);
                liveText.setSelected(true);
                liveText.setText(strikeDescriptor.getStrikeUpdateLive());
                liveDot.startAnimation(blinkAnim);
            }
            else {
                liveLayout.setVisibility(View.GONE);
                liveBarrier.setVisibility(View.GONE);
                liveDot.clearAnimation();
            }

            //*UPDATE TEXT VALUES
            String formattedTextDate = String.format(getString(R.string.strikeBannerTitle), strikeDescriptor.getStrikeDate());

            strikeDesc.setText(HtmlCompat.fromHtml(formattedTextDate, HtmlCompat.FROM_HTML_MODE_LEGACY));
            strikeGuaranteed.setText(String.format(getString(R.string.strikeBannerGuaranteed), strikeDescriptor.getStrikeGuaranteed()));
            strikeCompanies.setText(String.format("%s", strikeDescriptor.getStrikeCompanies()));

            strikeOpenClose.setOnClickListener(v -> {
                ActivityUtils.triggerFeedback(this);
                findViewById(R.id.strikeDescription).setVisibility((strikeBannerClosed) ? View.VISIBLE : View.GONE);
                strikeBannerClosed = !strikeBannerClosed;
                closeBtn.animate().rotation(!strikeBannerClosed ? 0f : -90f).setDuration(200).start();
            });
        }
        else
            findViewById(R.id.strikeBanner).setVisibility(View.GONE);

        new Thread(() -> NotificationScheduler.scheduleStrikeNotification(MainActivity.this, strikeCDNResponse)).start();
    }

    private void checkForEmptyList(List<EventDescriptor> list, String searchInfo, String searchDefault) {
        /// In this method, we will check if a list is empty or not, to display the text correctly.
        /// @PARAMETERS
        /// List<EventDescriptor> list is the list of all works of that category.
        /// String searchInfo is the query that the user write into the EditText.
        /// String searchDefault is the query NOT LOWERCASED for better case-switching.

        TextView noWorkFounds = findViewById(R.id.emptyView);
        RecyclerView view = findViewById(R.id.recyclerView);

        noWorkFounds.setVisibility((list.isEmpty()) ? View.VISIBLE : View.GONE);
        noWorkFounds.setText((searchInfo.equals("null") ? getString(R.string.noWorkOnFilter) : String.format(getString(R.string.noWorksFoundInput), searchDefault)));
        view.setVisibility((list.isEmpty() && errorLayout.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE);
    }

    private void filtra(String testo, String testoOriginale) {
        List<EventDescriptor> listaFiltrata = new ArrayList<>();

        if (adapter == null || events == null || events.isEmpty() || errorLayout.getVisibility() == View.VISIBLE)
            return;

        if (testo == null || testo.trim().isEmpty()) {
            for (EventDescriptor item : events){
                long now = System.currentTimeMillis();
                long terminated = item.getEndDateMillis();

                if(terminated > now)
                    listaFiltrata.add(item);
            }
            adapter.setFilteredList(listaFiltrata);

            checkForEmptyList(events, testo, testoOriginale);
            return;
        }

        String testoLower = testo.toLowerCase().trim();

        for (EventDescriptor item : events) {
            boolean found = false;
            long now = System.currentTimeMillis();
            long terminated = item.getEndDateMillis();

            if(item.getRoads().toLowerCase().contains(testoLower) || item.getDetails().toLowerCase().contains(testoLower))
                found = true;

            if (!found && item.getLines() != null) {
                for (String line : item.getLines()) {
                    if (line != null && (line.toLowerCase().contains(testoLower))) {
                        found = true;
                        break;
                    }
                }
            }
            if (found && terminated > now)
                listaFiltrata.add(item);
        }

        adapter.setFilteredList(listaFiltrata);
        checkForEmptyList(listaFiltrata, testo, testoOriginale);
    }

    private void applicaFiltroCategoria(CategoriesEnum categoria) {
        if(adapter == null || errorLayout.getVisibility() == View.VISIBLE)
            return;

        if (events == null || events.isEmpty())
            return;

        List<EventDescriptor> filtrata = new ArrayList<>();
        Set<String> linesSaved = new HashSet<>(DataManager.getStringArray(DataKeys.KEY_ARRAY_YOUR_LINES, new HashSet<>()));

        long now = System.currentTimeMillis();
        long limiteMassimo = now - 86400000L;

        for (EventDescriptor item : events) {
            long terminated = item.getEndDateMillis();
            long start = item.getStartDateMillis();

            switch (categoria) {
                case LE_TUE_LINEE:
                    for(String line: item.lines) {
                        if(linesSaved.contains(line)){
                            filtrata.add(item);
                            break;
                        }
                    }
                    break;

                case TUTTI:
                    if (terminated > limiteMassimo)
                        filtrata.add(item);
                    break;

                case BUS:
                    if (isBus(item) && terminated > limiteMassimo) filtrata.add(item);
                    break;

                case TRAM:
                    if(isTram(item) && terminated > limiteMassimo) filtrata.add(item);
                    break;

                case METROPOLITANA:
                    if (isMetro(item) && terminated > limiteMassimo) filtrata.add(item);
                    break;

                case TRENO:
                    if (isTreno(item) && terminated > limiteMassimo) filtrata.add(item);
                    break;

                case IN_CORSO:
                    if (start > 0 && terminated > 0 && now >= start && now <= terminated)  filtrata.add(item);
                    break;

                case PROGRAMMATI:
                    if (start > 0 && now < start)  filtrata.add(item);
                    break;

                case DI_ATM:
                    if(item.company.equalsIgnoreCase("ATM") && terminated > limiteMassimo) filtrata.add(item);
                    break;

                case DI_TRENORD:
                    if(item.company.equalsIgnoreCase("Trenord") && terminated > limiteMassimo) filtrata.add(item);
                    break;

                case DI_MOVIBUS:
                    if(item.company.equalsIgnoreCase("Movibus") && terminated > limiteMassimo) filtrata.add(item);
                    break;

                case DI_STAV:
                    if(item.company.equalsIgnoreCase("STAV") && terminated > limiteMassimo) filtrata.add(item);
                    break;

                case DI_STAR:
                    if(item.company.equalsIgnoreCase("STAR") && terminated > limiteMassimo) filtrata.add(item);
                    break;

                case DI_AUTOGUIDOVIE:
                    if(item.company.equalsIgnoreCase("Autoguidovie") && terminated > limiteMassimo) filtrata.add(item);
                    break;
            }
        }
        adapter.setFilteredList(filtrata);
        checkForEmptyList(filtrata, "null", "");
    }

    private boolean isTram(EventDescriptor item) {return (item.typeOfTransport.contains("tram") && !item.typeOfTransport.equalsIgnoreCase("tram.fill.tunnel"));}

    private boolean isTreno(EventDescriptor item) {
        for (String l : item.getLines()) {
            if (l.matches("(?i)^(S|R|RE|RV).*")) return true;
        }
        return false;
    }

    private boolean isMetro(EventDescriptor item) {
        for (String l : item.getLines()) {
            if (l.matches("(?i)^M[1-5].*")) return true;
        }
        return false;
    }

    private boolean isBus(EventDescriptor item) {
        for (String l : item.getLines()) {
            if ((l.matches("^[0-9]+$") && item.getTypeOfTransport().contains("bus")) || l.startsWith("Z") || l.startsWith("z") || l.startsWith("Filobus")) return true;
        }
        return false;
    }

    private void loadNativeAds() {
        NativeAdOptions nativeAdOptions = new NativeAdOptions.Builder().build();

        loadAdsInBatches(getMetaData(this, "AdUnitID"), nativeAdOptions, 8);
    }

    private void loadAdsInBatches(String adUnitId, NativeAdOptions options, int totalDesired) {
        final int BATCH_SIZE = 5;
        final int[] loadedCount = {0};

        loadNextBatch(adUnitId, options, totalDesired, BATCH_SIZE, loadedCount);
    }

    private void loadNextBatch(String adUnitId, NativeAdOptions options, int totalDesired, int batchSize, int[] loadedCount) {
        if (loadedCount[0] >= totalDesired) return;

        int remaining = totalDesired - loadedCount[0];
        int toLoad = Math.min(batchSize, remaining);

        AdLoader adLoader = new AdLoader.Builder(this, adUnitId)
            .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    mNativeAds.add(nativeAd);
                    pendingBatch.add(nativeAd);
                    loadedCount[0]++;

                    if (loadedCount[0] % batchSize == 0 || loadedCount[0] >= totalDesired) {
                        if (adapter != null) {
                            adapter.addAdsBatch(new ArrayList<>(pendingBatch));
                            pendingBatch.clear();
                        }
                        if (loadedCount[0] < totalDesired) loadNextBatch(adUnitId, options, totalDesired, batchSize, loadedCount);
                    }
                }
            })
            .withAdListener(new com.google.android.gms.ads.AdListener() {
                @Override
                public void onAdFailedToLoad(com.google.android.gms.ads.LoadAdError adError) {
                    Log.e("ADMOB", "Fallito: " + adError.getMessage());
                    if (loadedCount[0] < totalDesired) loadNextBatch(adUnitId, options, totalDesired, batchSize, loadedCount);
                }
            })
            .withNativeAdOptions(options)
            .build();

        adLoader.loadAds(new AdRequest.Builder().build(), toLoad);
    }

    private void showTutorialDialog() {
        final Handler handler = new Handler(Looper.getMainLooper());

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_saved_lines, null);

        AlertDialog dialog = new AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create();

        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));

        //* SETUP ANIMATION
        ImageButton heartIcon = dialogView.findViewById(R.id.buttonAddLine);
        Animation scaleDownUp = AnimationUtils.loadAnimation(this, R.anim.scale_down_up);

        final boolean[] isFilled = {false};

        Runnable heartAnimation = new Runnable() {
            @Override
            public void run() {
                isFilled[0] = !isFilled[0];

                if (isFilled[0]) {
                    heartIcon.setImageResource(R.drawable.ic_heart);
                    heartIcon.setImageTintList(ColorStateList.valueOf(getColor(R.color.heartColor)));
                }
                else {
                    heartIcon.setImageResource(R.drawable.ic_heart_empty);
                    heartIcon.setImageTintList(ColorStateList.valueOf(getColor(R.color.text_primary)));
                }

                heartIcon.startAnimation(scaleDownUp);
                handler.postDelayed(this, 1800);
            }
        };

        handler.post(heartAnimation);

        Button btnClose = dialogView.findViewById(R.id.btn_close_tutorial);
        btnClose.setOnClickListener(v -> dialog.dismiss());

        //* DESTROY HANDLER
        dialog.setOnDismissListener(dialogInterface -> {
            handler.removeCallbacks(heartAnimation);
            handler.removeCallbacksAndMessages(null);
        });

        dialog.show();
    }

    private void loadUserPreferences() {
        supabaseDataManager.fetchUserPreferences(new SupabaseDataManager.DataCallback<SupabaseModels.UserPreferencesDatas>() {
            @Override
            public void onSuccess(SupabaseModels.UserPreferencesDatas result) {
                DataManager.saveBoolData(DataKeys.KEY_SAVE_DB_FAVORITES, result.enable_favorites);
                DataManager.saveBoolData(DataKeys.KEY_SAVE_DB_YOUR_LINES, result.enable_your_lines);
            }

            @Override
            public void onError(String error) {Toast.makeText(MainActivity.this, getString(R.string.connectionErrorToast), Toast.LENGTH_SHORT).show();}
        });
    }

    @Override
    protected void onDestroy() {
        if (mNativeAds != null && !mNativeAds.isEmpty()) {
            for (NativeAd ad : mNativeAds) if (ad != null) ad.destroy();
            mNativeAds.clear();
        }

        super.onDestroy();
    }
}