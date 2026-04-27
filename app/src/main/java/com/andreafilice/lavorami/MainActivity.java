package com.andreafilice.lavorami;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ArrayList<EventDescriptor> events = new ArrayList<EventDescriptor>();
    private ArrayList<EventDescriptor> eventsDisplay = new ArrayList<EventDescriptor>();
    private ShimmerFrameLayout loadingLayout;
    private LinearLayout errorLayout;
    private WorkAdapter adapter;
    private String defaultCategory;
    private boolean hasCompletedSetup;
    private StrikeDescriptor strikeCDNResponse;
    private ImageButton btnRefresh;
    private Animation animSpin;
    static String maintenanceDetails = "";

    //*HINT VARIABLES
    /// In this section of the code, we will create the variables for our HintAnimations
    private TextSwitcher hintSwitcher;
    private EditText editSearch;
    private int indexHintAnimation;
    private final Handler handler = new Handler();
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if(isGranted){
                    Log.d("PERMISSIONS", "Notifiche concesse!");
                    if (EventData.listaEventiCompleta != null && !EventData.listaEventiCompleta.isEmpty())
                        NotificationScheduler.scheduleWorkNotifications(MainActivity.this, EventData.listaEventiCompleta);
                }
                else
                    Log.e("PERMISSIONS", "Permesso notifiche rifiutato.");
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        defaultCategory = DataManager.getStringData(DataKeys.KEY_DEFAULT_FILTER, "Tutti");
        setTheme();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        String[] hints = {getString(R.string.hintMain1), getString(R.string.hintMain2), getString(R.string.hintMain3), getString(R.string.hintMain4), getString(R.string.hintMain5), getString(R.string.hintMain6), getString(R.string.hintMain7)};

        //*LOCK THE ORIENTATION
        /// In this section of the code, we will block the orientation to PORTRAIT because in LANDSCAPE LavoraMi is not supported.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //*SETUP PAGES
        /// In this section of the code, we create the Setup-Pages for our OnBoarding screen.
        /// This method is also used into the iOS version of LavoraMi.
        hasCompletedSetup = DataManager.getBoolData(DataKeys.KEY_END_SETUP, false);

        ConstraintLayout setupOverlay = findViewById(R.id.setupOverlay);
        setupOverlay.setVisibility((hasCompletedSetup) ? View.GONE : View.VISIBLE);

        List<SetupModels.SetupPage> pages = new ArrayList<>();
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle1), getString(R.string.setupDeps1), "ic_train", ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle2), getString(R.string.setupDeps2), "ic_map", ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle3), getString(R.string.setupDeps3), "ic_star_fill", ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle4), getString(R.string.setupDeps4), "ic_bell_fill", ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitleTranslate), getString(R.string.setupDepsTranslate), "ic_translate", getString(R.string.setupMiniDepsTranslate)));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupAccessibilityTitle), getString(R.string.setupDepsAccessiblity), "ic_accessibility", ""));
        pages.add(new SetupModels.SetupPage(getString(R.string.setupTitle5), getString(R.string.setupDeps5), "ic_lock", getString(R.string.setupMiniDetails)));

        ViewPager2 viewPager = findViewById(R.id.setupViewPager);
        SetupModels.SetupAdapter adapter = new SetupModels.SetupAdapter(pages);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.setupIndicator);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {}).attach();

        /// In this section of the code, we set-up the "NEXT" and "SKIP" buttons from the view.
        MaterialButton btnSetupNext = findViewById(R.id.btnSetupNext);
        btnSetupNext.setOnClickListener(v -> {
            int currentPage = viewPager.getCurrentItem();

            if(currentPage < pages.size() - 1)
                viewPager.setCurrentItem(currentPage + 1);
            else {
                DataManager.saveBoolData(DataKeys.KEY_END_SETUP, true);
                setupOverlay.setVisibility(View.GONE);
                hasCompletedSetup = true;
                downloadJSONData(defaultCategory, false);
            }

            /// In this section, we ask the permission of notifications to the user, beacuse in this Index there is the "Notification" page.
            if(currentPage == 3)
                askForNotificationPermission();
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
                        askForNotificationPermission();
                    }))
                    .create()
                    .show();
        });

        /// In this section we also set the "NEXT" button to "END" when it's the last Page.
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
            super.onPageSelected(position);
            int currentPage = viewPager.getCurrentItem();

            btnSetupNext.setText((position == pages.size() -1) ? getString(R.string.endPages) : getString(R.string.nextPages));
            btnSetupSkip.setVisibility((position == pages.size() -1) ? View.GONE : View.VISIBLE);

            if(currentPage == 3)
                askForNotificationPermission();
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
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            title.setTextColor(Color.parseColor("#666666"));
            title.setPadding(5, 0, 0, 0);

            Typeface tf = ResourcesCompat.getFont(MainActivity.this, R.font.inter_medium);
            title.setTypeface(tf);

            return title;
        });

        hintSwitcher.setCurrentText(getString(R.string.hintMain1));

        hintSwitcher.setInAnimation(this, R.anim.slide_up_in);
        hintSwitcher.setOutAnimation(this, R.anim.slide_up_out);

        startHintLoop(hints);

        //*NAVBAR
        ImageButton btnLines = findViewById(R.id.linesButton);
        btnLines.setOnClickListener(v -> {ActivityUtils.changeActivity(this, LinesActivity.class);});

        ImageButton btnSettings = findViewById(R.id.settingsButton);
        btnSettings.setOnClickListener(v -> {ActivityUtils.changeActivity(this, SettingsActivity.class);});

        //*REFRESH BUTTON
        Button btnRefreshOnError = findViewById(R.id.btnRefreshOnError);

        btnRefresh.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
            editSearch.clearFocus();
            editSearch.setText("");
            downloadJSONData(getCategory(), true);}
        );
        btnRefreshOnError.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
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
                case "Tutti":
                    filterGroup.check(R.id.chipAll);
                    break;
                case "Bus":
                    filterGroup.check(R.id.chipBus);
                    break;
                case "Tram":
                    filterGroup.check(R.id.chipTram);
                    break;
                case "Metropolitana":
                    filterGroup.check(R.id.chipMetro);
                    break;
                case "Treno":
                    filterGroup.check(R.id.chipTreno);
                    break;
                case "In Corso":
                    filterGroup.check(R.id.chipInCorso);
                    break;
                case "Programmati":
                    filterGroup.check(R.id.chipProgrammati);
                    break;
                case "di ATM":
                    filterGroup.check(R.id.chipATM);
                    break;
                case "di Trenord":
                    filterGroup.check(R.id.chipTrenord);
                    break;
                case "di Movibus":
                    filterGroup.check(R.id.chipMovibus);
                    break;
                case "di STAV":
                    filterGroup.check(R.id.chipStav);
                    break;
                case "di Autoguidovie":
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
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
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
        if (filterGroup != null) {
            filterGroup.setOnCheckedChangeListener((group, checkedId) -> {
                RecyclerView recyclerView = findViewById(R.id.recyclerView);

                if (checkedId == View.NO_ID)
                    filterGroup.check(R.id.chipAll);
                else {
                    if (checkedId != R.id.chipAll)
                        editSearch.setText("");
                    Chip selectedChip = findViewById(checkedId);
                    if (selectedChip != null) {
                        String categoria = selectedChip.getText().toString().toLowerCase().trim();
                        applicaFiltroCategoria(categoria);
                    }
                }

                recyclerView.scrollToPosition(0);
            });
        }

        //*SETUP LANGUAGE
        /// Setting up the language of the application base from the Data saved
        String savedLang = DataManager.getStringData(DataKeys.KEY_DEFAULT_LANGUAGE, "🇮🇹 Italiano");
        String langCode = savedLang.contains("English") ? "en" : "it";
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(langCode));

        //*CHIP COLORS
        /// In this section of the code, we define the Chip colors for better visibility.
        Chip[] filterChips = {
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
            findViewById(R.id.chipAutoguidovie)
        };
        int colorChecked = ContextCompat.getColor(this, R.color.background_app);
        int colorUncecked = ContextCompat.getColor(this, R.color.text_primary);

        ColorStateList chipColors = new ColorStateList(
            new int[][]{
                new int[]{ android.R.attr.state_checked },
                new int[]{ -android.R.attr.state_checked }
            },
            new int[]{
                colorChecked,
                colorUncecked
            }
        );

        for(Chip chip : filterChips) {
            chip.setTextColor(chipColors);
            chip.setChipIconTint(chipColors);
            chip.setTypeface(Typeface.create("@font/inter_medium", Typeface.BOLD));
        }

        //*DOWNLOADING EVENTS
        downloadJSONData(defaultCategory, false);

        String categoriaFinale = defaultCategory;
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

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
        editSearch.clearFocus();
        editSearch.setText("");

        String categorySelected = getCategory();

        downloadJSONData(categorySelected, false);
    }

    private void askForNotificationPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }

    public String getCategory() {
        ChipGroup filterGroup = findViewById(R.id.filterChipGroup);
        if (filterGroup == null) return "tutti";

        int checkedId = filterGroup.getCheckedChipId();
        if (checkedId == View.NO_ID || checkedId == R.id.chipAll)
            return "tutti";

        Chip selectedChip = findViewById(checkedId);
        if (selectedChip != null)
            return selectedChip.getText().toString().toLowerCase().trim();

        return "tutti";
    }

    public void downloadJSONData(String categoryToFilter, boolean reloadingDatas) {
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
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter = new WorkAdapter(this, eventsDisplay);
            adapter.setFilteredList(eventsDisplay);
            recyclerView.setAdapter(adapter);

            applicaFiltroCategoria(categoryToFilter);
            checkForStrikes();
            return;
        }

        if (loadingLayout != null) {
            loadingLayout.setVisibility(View.VISIBLE);
            errorLayout.setVisibility(View.GONE);
            loadingLayout.startShimmer();
            btnRefresh.startAnimation(animSpin);
            recyclerView.setVisibility(View.GONE);
            strikeBanner.setVisibility(View.GONE);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cdn.lavorami.it/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIWorks apiworks = retrofit.create(APIWorks.class);

        /// In this section of the code, we fetch the lavoriAttuali.json file from our CDN and, after the succesfull operation, we create the Event ArrayList.
        apiworks.getLavori().enqueue(new Callback<ArrayList<EventDescriptor>>() {
            @Override
            public void onResponse(Call<ArrayList<EventDescriptor>> call, Response<ArrayList<EventDescriptor>> response) {
                //? DISABLE THE LOADING LAYOUT
                if (loadingLayout != null) {
                    loadingLayout.setVisibility(View.GONE);
                    errorLayout.setVisibility(View.GONE);
                    btnRefresh.clearAnimation();
                    findViewById(R.id.recyclerView).setVisibility(View.VISIBLE);
                }

                if (response.isSuccessful() && response.body() != null) {
                    events.clear();
                    events = response.body();
                    eventsDisplay.clear();
                    eventsDisplay = response.body();
                    EventData.listaEventiCompleta = events;
                    EventData.networkError = false;
                    NotificationScheduler.scheduleWorkNotifications(MainActivity.this, EventData.listaEventiCompleta);

                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    adapter = new WorkAdapter(MainActivity.this, eventsDisplay);
                    adapter.setFilteredList(eventsDisplay);
                    recyclerView.setAdapter(adapter);

                    applicaFiltroCategoria(categoryToFilter);
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
                    iconWiFi.setImageResource(
                        (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                            ? R.drawable.ic_wifi_slash_android11_later
                            : R.drawable.ic_wifi_slash_android10_previous
                    );
                }
            }
        });

        checkForStrikes();
    }

    private void checkForStrikes(){
        /// In this section of the code, we GET the '_vars.json' file from our CDN and load the Strikes Configuration.

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cdn.lavorami.it/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIWorks apiworks = retrofit.create(APIWorks.class);
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
        String langCode = savedLang.contains("English") ? "en" : "it";

        int responseComparable = RequirementsDescriptor.compareSemanticVersions(currentVersion, versionMinimum);

        //*CHECK FOR MAINTENANCE
        /// Check if LavoraMi is in maintenance mode and get the details from the CDN.
        boolean isInMaintenanceMode = descriptor.isMaintenanceEnabled();
        maintenanceDetails = (langCode.equalsIgnoreCase("en")) ? descriptor.getMaintenanceDepsEnglish() : descriptor.getMaintenanceDeps();

        if(isInMaintenanceMode)
            ActivityUtils.changeActivity(this, MaintenanceActivity.class);

        if(responseComparable < 0){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.newVersionAvailableTitle))
                    .setMessage(getString(R.string.newVersionAvailableDeps))
                    .setPositiveButton(getString(R.string.updateButton), ((dialog, which) -> {
                        String packageName = getPackageName();
                        String link = "https://play.google.com/store/apps/details?id=" + packageName;

                        ActivityUtils.openURL(this, link);
                    }))
                    .setCancelable(false)
                    .create()
                    .show();
        }
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
            ImageView closeBtn = findViewById(R.id.closeBtn);

            strikeBanner.setVisibility((strikeDescriptor.isStrikeEnabled().equals("true")) ? View.VISIBLE : View.GONE);

            //*UPDATE TEXT VALUES
            strikeDesc.setText(String.format(getString(R.string.strikeBannerTitle), strikeDescriptor.getStrikeDate()));
            strikeGuaranteed.setText(String.format(getString(R.string.strikeBannerGuaranteed), strikeDescriptor.getStrikeGuaranteed()));
            strikeCompanies.setText(String.format("%s", strikeDescriptor.getStrikeCompanies()));

            closeBtn.setOnClickListener(v -> {strikeBanner.setVisibility(View.GONE);});
        }

        NotificationScheduler.scheduleStrikeNotification(MainActivity.this, strikeCDNResponse);
    }

    private void checkForEmptyList(List<EventDescriptor> list, String searchInfo, String searchDefault) {
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
                long terminated = getDateMillis(item.getEndDate());
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
            long terminated = getDateMillis(item.getEndDate());

            if (!found && item.getLines() != null) {
                for (String line : item.getLines()) {
                    if (line != null && line.toLowerCase().contains(testoLower)) {
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

    private void applicaFiltroCategoria(String categoria) {
        if(adapter == null || errorLayout.getVisibility() == View.VISIBLE)
            return;

        if (events == null || events.isEmpty())
            return;

        List<EventDescriptor> filtrata = new ArrayList<>();
        long oggi = System.currentTimeMillis();

        /// NOTE: toLowerCase() for better switch-casing
        categoria = categoria.toLowerCase();

        long now = System.currentTimeMillis();
        long terminated;

        for (EventDescriptor item : events) {
            terminated = getDateMillis(item.getEndDate());

            switch (categoria) {
                case "tutti":
                    if (terminated > now)
                        filtrata.add(item);
                    break;

                case "bus":
                    if (isBus(item) && terminated > now) filtrata.add(item);
                    break;

                case "tram":
                    if(isTram(item) && terminated > now) filtrata.add(item);
                    break;

                case "metropolitana":
                    if (isMetro(item) && terminated > now) filtrata.add(item);
                    break;

                case "treno":
                    if (isTreno(item) && terminated > now) filtrata.add(item);
                    break;

                case "in corso":
                    long start = getDateMillis(item.getStartDate());
                    long end = getDateMillis(item.getEndDate());
                    if (start > 0 && end > 0 && oggi >= start && oggi <= end)  filtrata.add(item);
                    break;

                case "programmati":
                    long startP = getDateMillis(item.getStartDate());
                    if (startP > 0 && oggi < startP)  filtrata.add(item);
                    break;

                case "di atm":
                    if(item.company.equalsIgnoreCase("ATM") && terminated > now) filtrata.add(item);
                    break;

                case "di trenord":
                    if(item.company.equalsIgnoreCase("Trenord") && terminated > now) filtrata.add(item);
                    break;

                case "di movibus":
                    if(item.company.equalsIgnoreCase("Movibus") && terminated > now) filtrata.add(item);
                    break;

                case "di stav":
                    if(item.company.equalsIgnoreCase("STAV") && terminated > now) filtrata.add(item);
                    break;

                case "di autoguidovie":
                    if(item.company.equalsIgnoreCase("Autoguidovie") && terminated > now) filtrata.add(item);
                    break;
            }
        }
        adapter.setFilteredList(filtrata);
        checkForEmptyList(filtrata, "null", "");
    }

    private int calcolaPercentuale(EventDescriptor item) {
        long start = getDateMillis(item.startDate);
        long end = getDateMillis(item.endDate);
        long now = System.currentTimeMillis();

        long totalDuration = end - start;
        if (totalDuration <= 0) return 100;

        long elapsed = now - start;
        double fraction = (double) elapsed / totalDuration;
        double clamped = Math.max(0.0, Math.min(fraction, 1.0));
        return (int) (clamped * 100);
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

    public long getDateMillis(String dateString) {
        if (dateString == null) return 0;
        String serverFormat = "yyyy-MM-dd'T'HH:mm:ss'+01:00'";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(serverFormat, Locale.getDefault());
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

            Date date = sdf.parse(dateString);
            return (date != null) ? date.getTime() : 0;

        }
        catch (Exception e) {
            Log.e("DATA_ERROR", "Impossibile leggere: " + dateString + " | Errore: " + e.getMessage());
            return 0;
        }
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

        if (AppCompatDelegate.getDefaultNightMode() != modeSelected)
            AppCompatDelegate.setDefaultNightMode(modeSelected);
    }
}