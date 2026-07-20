package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.WorkAdapter.translateStrings;
import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.accessibilityservice.GestureDescription;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.mapbox.maps.MapView;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.mapbox.maps.plugin.gestures.GesturesUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LinesDetailActivity extends AppCompatActivity {
    private String nomeLinea;
    private String tipoDiLinea;
    private String selectedBranch = null;
    private View lavoriNested;
    private View interscambiNested;
    private View lavoriWrapper;
    private View interscambiWrapper;
    private StrikeDescriptor strikeCDNResponse;
    private View arriviWrapper;
    private View arriviNested;
    private LinearLayout interscambiLoadingState;
    private AutoCompleteTextView dropdownFermate;
    private RecyclerView arriviRecyclerView;
    private ProgressBar arriviProgressBar;
    private LinearLayout arriviEmptyState;
    private ChipGroup detActionGroup;
    private GTFSHelper.GTFSRoute routeData;
    private String selectedStopId;
    private int coloreLinea;
    private boolean foundAtLeastOne = false;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable arriviRunnable;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler interchangeHandler = new Handler(Looper.getMainLooper());
    private volatile boolean interscambiPreloaded = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private MapView mapViewRef;
    private MapView pendingMapView;
    private boolean mapAlreadyLoaded = false;
    private boolean strikeFetchAttempted = false;
    private boolean modalitaRitorno = false;
    private boolean hintWidgetClosed;
    private TextView txtDirezioneMappa;
    private List<MetroStation> ultimeStazioniDisegnate;
    SessionManager sessionManager;
    SupabaseAPI api;
    Retrofit retrofitAPI;
    private String SupabaseANON, SupabaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapboxHelper.init(getMetaData(this, "MAPBOX_KEY"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines_detail);

        //*LOCK THE ORIENTATION
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        detActionGroup = findViewById(R.id.detActionGroup);

        Chip chipMappa = findViewById(R.id.chipMappa);
        Chip chipLavori = findViewById(R.id.chipLavoriInCorso);
        Chip chipInterscambi = findViewById(R.id.chipInterscambi);
        Chip chipArrivi = findViewById(R.id.chipArrivi);

        CardView cardMappa = findViewById(R.id.mapCard);
        LinearLayout containerLavori = findViewById(R.id.containerLavori);
        LinearLayout containerInterscambi = findViewById(R.id.containerInterscambi);
        ArrayList<String> tramLinesWithMap = new ArrayList<>(Arrays.asList("1", "3", "5", "7", "9", "10", "15", "16", "19", "24", "27", "31", "33"));
        ArrayList<String> busLinesWithMap = new ArrayList<>(Arrays.asList()); // "z620", "z622"

        lavoriNested = findViewById(R.id.lavoriNested);
        interscambiNested = findViewById(R.id.interscambiNested);
        lavoriWrapper = findViewById(R.id.lavoriSezioneWrapper);
        interscambiWrapper = findViewById(R.id.interscambiWrapper);
        interscambiLoadingState = findViewById(R.id.interscambiLoadingState);

        arriviWrapper = findViewById(R.id.arriviWrapper);
        arriviNested = findViewById(R.id.arriviNested);
        dropdownFermate = findViewById(R.id.dropdownFermate);
        arriviRecyclerView = findViewById(R.id.arriviRecyclerView);
        arriviProgressBar = findViewById(R.id.arriviProgressBar);
        arriviEmptyState = findViewById(R.id.arriviEmptyState);

        arriviRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chipMappa.setChecked(true);
        updateChipGroupSizes(detActionGroup);

        nomeLinea = getIntent().getStringExtra("NOME_LINEA");
        tipoDiLinea = getIntent().getStringExtra("TIPO_DI_LINEA");

        if (nomeLinea == null) nomeLinea = "M1";
        if ((tipoDiLinea.contains(getString(R.string.tramLinesScroll)) && !(tramLinesWithMap.contains(nomeLinea))) || (tipoDiLinea.contains("z")&& !(busLinesWithMap.contains(nomeLinea)))){ // && !(busLinesWithMap.contains(nomeLinea))
            chipMappa.setVisibility(View.GONE);
            chipInterscambi.setVisibility(View.GONE);
            cardMappa.setVisibility(View.GONE);
            containerInterscambi.setVisibility(View.GONE);
            containerLavori.setVisibility(View.VISIBLE);
            caricaEventiFiltrati();

            if(!tipoDiLinea.contains(getString(R.string.tramLinesScroll)))
                caricaFermateInterscambio();

            chipMappa.setChecked(false);
            chipLavori.setChecked(true);
            chipInterscambi.setChecked(false);
            chipArrivi.setChecked(false);
        }

        if(tipoDiLinea.contains("Movibus")){
            caricaFermateInterscambio();
            chipInterscambi.setVisibility(View.GONE);
        }

        Typeface typeface = ResourcesCompat.getFont(this, R.font.inter);
        chipMappa.setTypeface(typeface, Typeface.BOLD);
        chipLavori.setTypeface(typeface, Typeface.BOLD);
        chipInterscambi.setTypeface(typeface, Typeface.BOLD);
        chipArrivi.setTypeface(typeface, Typeface.BOLD);

        aggiornaUI();

        MapView mapView = findViewById(R.id.mapView);
        MapboxHelper.loadMap(mapView, isDarkMode(), mapViewReady -> {
            pendingMapView = mapViewReady;
            checkIfReadyToLoadMap();
        });

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


        chipMappa.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            chipMappa.setChecked(true);
            dismissActiveBranchDialog();

            cardMappa.setVisibility(View.VISIBLE);
            containerLavori.setVisibility(View.GONE);
            containerInterscambi.setVisibility(View.GONE);

            lavoriWrapper.setVisibility(View.GONE);
            lavoriNested.setVisibility(View.GONE);
            interscambiWrapper.setVisibility(View.GONE);
            interscambiNested.setVisibility(View.GONE);
            arriviWrapper.setVisibility(View.GONE);
            arriviNested.setVisibility(View.GONE);

            findViewById(R.id.lavoriSezioneWrapper).setVisibility(View.GONE);
            findViewById(R.id.emptyViewContainer).setVisibility(View.GONE);

            if (!chipLavori.isChecked() && !chipMappa.isChecked() && !chipInterscambi.isChecked()) {
                chipMappa.setChecked(true);
                chipLavori.setChecked(false);
                chipInterscambi.setChecked(false);
                chipArrivi.setChecked(false);
            }

            updateChipGroupSizes(detActionGroup);
        });

        chipLavori.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            chipLavori.setChecked(true);
            dismissActiveBranchDialog();

            cardMappa.setVisibility(View.GONE);
            containerLavori.setVisibility(View.VISIBLE);
            containerInterscambi.setVisibility(View.GONE);

            interscambiWrapper.setVisibility(View.GONE);
            interscambiNested.setVisibility(View.GONE);
            arriviWrapper.setVisibility(View.GONE);
            arriviNested.setVisibility(View.GONE);

            caricaEventiFiltrati();
            updateChipGroupSizes(detActionGroup);
        });

        chipInterscambi.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            chipInterscambi.setChecked(true);

            cardMappa.setVisibility(View.GONE);
            containerLavori.setVisibility(View.GONE);
            containerInterscambi.setVisibility(View.VISIBLE);

            lavoriWrapper.setVisibility(View.GONE);
            lavoriNested.setVisibility(View.GONE);
            arriviWrapper.setVisibility(View.GONE);
            arriviNested.setVisibility(View.GONE);
            findViewById(R.id.emptyViewContainer).setVisibility(View.GONE);

            caricaInterscambiLinee();
            updateChipGroupSizes(detActionGroup);
        });

        chipArrivi.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(this);
            chipArrivi.setChecked(true);

            cardMappa.setVisibility(View.GONE);
            containerLavori.setVisibility(View.GONE);
            containerInterscambi.setVisibility(View.GONE);

            lavoriWrapper.setVisibility(View.GONE);
            lavoriNested.setVisibility(View.GONE);
            interscambiWrapper.setVisibility(View.GONE);
            interscambiNested.setVisibility(View.GONE);

            findViewById(R.id.emptyViewContainer).setVisibility(View.GONE);
            arriviWrapper.setVisibility(View.VISIBLE);
            arriviNested.setVisibility(View.VISIBLE);

            updateChipGroupSizes(detActionGroup);
        });

        ImageButton btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(v -> finish());

        //*FETCH VARS
        /// In this section of the code, we fetch the variables from the .json file in cdn
        APIWorks apiworks = RetrofitManager.get().create(APIWorks.class);

        apiworks.getStrike().enqueue(new Callback<StrikeDescriptor>() {
            @Override
            public void onResponse(Call<StrikeDescriptor> call, Response<StrikeDescriptor> response) {
                strikeFetchAttempted = true;
                if (response.isSuccessful()) {
                    strikeCDNResponse = response.body();
                    aggiornaInfoSuperiori();
                    fetchDeviations(strikeCDNResponse);
                    preloadInterscambi();
                }
                checkIfReadyToLoadMap();
            }

            @Override
            public void onFailure(Call<StrikeDescriptor> call, Throwable t) {
                strikeFetchAttempted = true;
                Toast.makeText(LinesDetailActivity.this, getString(R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();
                checkIfReadyToLoadMap();
            }
        });

        //*SAVE TO "YOUR LINES"
        /// In this section we save the line currently selected to the Array of "your lines".
        updateSavedLines();

        //*S12 LIMITATION
        /// In this section of the code, we compose the phrase for S12 line limitation.
        TextView attestazioneLinea = findViewById(R.id.attestazioneLinea);
        attestazioneLinea.setText(getString(R.string.lineaAttesta) + "MILANO BOVISA.");
        attestazioneLinea.setVisibility((nomeLinea.equals("S12")) ? View.VISIBLE : View.GONE);

        //*CHIP BACKGROUND COLOR
        /// In this section of the code we setup the Chip Background color when selected and when is not selected.
        coloreLinea = ContextCompat.getColor(this, (nomeLinea.equalsIgnoreCase("S12") ? R.color.GRAY : StationDB.getLineColor(this, nomeLinea)));
        int coloreDefault = ContextCompat.getColor(this, R.color.background_app);

        ColorStateList chipBgColor = new ColorStateList(
            new int[][]{
                new int[]{ android.R.attr.state_checked },
                new int[]{ -android.R.attr.state_checked }
            },
            new int[]{ coloreLinea, coloreDefault }
        );
        chipMappa.setChipBackgroundColor(chipBgColor);
        chipLavori.setChipBackgroundColor(chipBgColor);
        chipInterscambi.setChipBackgroundColor(chipBgColor);
        chipArrivi.setChipBackgroundColor(chipBgColor);

        // CHIP ICON COLOR
        ColorStateList chipIconColors = new ColorStateList(
            new int[][] {
                new int[]{ android.R.attr.state_checked },
                new int[]{ -android.R.attr.state_checked }
            },
            new int[] {
                ContextCompat.getColor(this, R.color.White),
                coloreLinea
            }
        );
        chipMappa.setChipIconTint(chipIconColors);
        chipLavori.setChipIconTint(chipIconColors);
        chipInterscambi.setChipIconTint(chipIconColors);
        chipArrivi.setChipIconTint(chipIconColors);

        // CHIP TEXT COLOR
        ColorStateList chipTextColor = new ColorStateList(
            new int[][]{
                new int[]{ android.R.attr.state_checked },
                new int[]{ -android.R.attr.state_checked }
            },
            new int[]{
                ContextCompat.getColor(this, R.color.White),
                Color.TRANSPARENT
            }
        );
        chipMappa.setTextColor(chipTextColor);
        chipLavori.setTextColor(chipTextColor);
        chipInterscambi.setTextColor(chipTextColor);
        chipArrivi.setTextColor(chipTextColor);

        //*ACCESSIBILITY RATE
        /// In this section of the code, we get the current accessibility rate of the line in question.
        String rate = getAccessibility(nomeLinea);
        TextView accessibilityText = findViewById(R.id.accessibilityText);
        ImageView iconAccessibility = findViewById(R.id.iconAccessibility);
        ImageView infoIconMetro = findViewById(R.id.infoIconMetro);
        LinearLayout lineAccessibilityLayout = findViewById(R.id.lineAccessibilityLayout);

        accessibilityText.setText(rate);

        if(rate.equalsIgnoreCase(getString(R.string.fullyAccessible))) {
            iconAccessibility.setImageResource(R.drawable.ic_checkmark);
            iconAccessibility.setImageTintList(ContextCompat.getColorStateList(this, R.color.M2));
        }
        else if(rate.equalsIgnoreCase(getString(R.string.partiallyTitle))) {
            iconAccessibility.setImageResource(R.drawable.ic_warning);
            iconAccessibility.setImageTintList(ContextCompat.getColorStateList(this, R.color.M3));
        }
        else {
            iconAccessibility.setImageResource(R.drawable.ic_xmark);
            iconAccessibility.setImageTintList(ContextCompat.getColorStateList(this, R.color.redMetro));
        }
        infoIconMetro.setOnClickListener(v -> ActivityUtils.changeActivity(this, InfoAccessibility.class));

        //*DISABLE THIS FEATURE FOR BUS LINES.
        if(nomeLinea.contains("z")){
            lineAccessibilityLayout.setVisibility(View.GONE);
            findViewById(R.id.titleAccessibility).setVisibility(View.GONE);
        }

        //*WIDGET SELECTION
        /// In this section of the code, we handle the Widget Selection for different lines
        ImageButton addToWidget = findViewById(R.id.buttonAddInWidget);
        hintWidgetClosed = DataManager.getBoolData(DataKeys.KEY_HINT_WIDGET_CLOSED, false);

        boolean isCurrentlyInWidget = nomeLinea.equals(DataManager.getStringData(DataKeys.KEY_LINE_WIDGET, ""));
        if (isCurrentlyInWidget) {
            addToWidget.setImageResource(R.drawable.ic_added_line_widget);
            addToWidget.setImageTintList(ColorStateList.valueOf(getColor(R.color.S6)));
        }
        else {
            addToWidget.setImageResource(R.drawable.ic_add_line_widget);
            addToWidget.setImageTintList(ColorStateList.valueOf(getColor(R.color.text_primary)));
        }

        addToWidget.setOnClickListener(v -> {
            boolean isInWidget = nomeLinea.equals(DataManager.getStringData(DataKeys.KEY_LINE_WIDGET, ""));

            ActivityUtils.triggerFeedback(this);
            addToWidget.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_down_up));

            if (isInWidget) {
                DataManager.saveStringData(DataKeys.KEY_LINE_WIDGET, "");
                addToWidget.setImageResource(R.drawable.ic_add_line_widget);
                addToWidget.setImageTintList(ColorStateList.valueOf(getColor(R.color.text_primary)));

                WidgetLines.refreshAllWidgets(this);
                return;
            }

            if (!hintWidgetClosed) {
                View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_hint_widget_lines, null);

                AlertDialog dialog = new AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setCancelable(true)
                    .create();
                dialog.show();

                if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));

                DataManager.saveBoolData(DataKeys.KEY_HINT_WIDGET_CLOSED, true);
                hintWidgetClosed = true;

                TextView descriptionText = dialogView.findViewById(R.id.text_description);
                descriptionText.setText(getString(R.string.widget_line_added_description, nomeLinea));

                Button btnClose = dialogView.findViewById(R.id.btn_close_tutorial);
                btnClose.setOnClickListener(v1 -> dialog.dismiss());
            }

            addToWidget.setImageResource(R.drawable.ic_added_line_widget);
            addToWidget.setImageTintList(ColorStateList.valueOf(getColor(R.color.S6)));

            DataManager.saveStringData(DataKeys.KEY_LINE_WIDGET, nomeLinea);

            WidgetLines.refreshAllWidgets(this);
        });
    }

    private void updateSavedLines() {
        /// In this function, we apply the changes into the UI and also on the AppData folder.

        Set<String> linesSaved = new HashSet<>(DataManager.getStringArray(DataKeys.KEY_ARRAY_YOUR_LINES, new HashSet<>()));
        ImageButton buttonAddLine = findViewById(R.id.buttonAddLine);
        Animation scaleUpDown = AnimationUtils.loadAnimation(this, R.anim.scale_up_down);
        boolean isSyncWithCloudEnabled = DataManager.getBoolData(DataKeys.KEY_SAVE_DB_YOUR_LINES, true);

        if(linesSaved.contains(nomeLinea)) {
            buttonAddLine.setImageTintList(ColorStateList.valueOf(getColor(R.color.heartColor)));
            buttonAddLine.setImageResource(R.drawable.ic_heart);

            buttonAddLine.setOnClickListener(v -> {
                linesSaved.remove(nomeLinea);
                DataManager.saveArrayStringsData(DataKeys.KEY_ARRAY_YOUR_LINES, linesSaved);
                if(isSyncWithCloudEnabled) syncYourLinesToSupabase(linesSaved);

                ActivityUtils.triggerFeedback(this);
                buttonAddLine.startAnimation(scaleUpDown);
                buttonAddLine.setImageTintList(ColorStateList.valueOf(getColor(R.color.text_primary)));
                buttonAddLine.setImageResource(R.drawable.ic_heart_empty);
                updateSavedLines();
            });
        }
        else {
            buttonAddLine.setImageTintList(ColorStateList.valueOf(getColor(R.color.text_primary)));
            buttonAddLine.setImageResource(R.drawable.ic_heart_empty);

            buttonAddLine.setOnClickListener(v -> {
                linesSaved.add(nomeLinea);
                DataManager.saveArrayStringsData(DataKeys.KEY_ARRAY_YOUR_LINES, linesSaved);
                if(isSyncWithCloudEnabled) syncYourLinesToSupabase(linesSaved);

                ActivityUtils.triggerFeedback(this);
                buttonAddLine.startAnimation(scaleUpDown);
                buttonAddLine.setImageTintList(ColorStateList.valueOf(getColor(R.color.heartColor)));
                buttonAddLine.setImageResource(R.drawable.ic_heart);
                updateSavedLines();
            });
        }
    }

    private boolean getLineLimitation() {
        /// This method is a refactor one that checks if the current line selected is available ONLY ON WORKING DAYS.

        String[] lineeAttiveLavorativi = {"S12", "S19", "R15", "R18", "R24", "R37"};

        for(String linea: lineeAttiveLavorativi) {
            if(linea.equals(nomeLinea))
                return true;
        }

        return false;
    }

    private void aggiornaUI() {
        TextView lineLimitation = findViewById(R.id.lineLimitation);
        lineLimitation.setVisibility((getLineLimitation()) ? View.VISIBLE : View.GONE);
        TextView detBadge = findViewById(R.id.detBadge);
        TextView detTitolo = findViewById(R.id.detTitolo);

        if(nomeLinea.startsWith("M"))
            detTitolo.setText("Metro " + nomeLinea);
        if(nomeLinea.startsWith("S"))
            detTitolo.setText(String.format("%s %s", getString(R.string.suburban), nomeLinea));
        if(nomeLinea.matches("^[1-9][0-9]?$"))
            detTitolo.setText(String.format("%s %s", getString(R.string.tramLinesScroll), nomeLinea));
        if(isLineaTilo())
            detTitolo.setText("TILO " + nomeLinea);
        if(nomeLinea.startsWith("MXP"))
            detTitolo.setText("Malpensa Express");
        if(nomeLinea.startsWith("z6"))
            detTitolo.setText("Movibus");
        if(nomeLinea.startsWith("z55") || nomeLinea.startsWith("z56"))
            detTitolo.setText("STAV");
        if(nomeLinea.startsWith("z50") || nomeLinea.startsWith("z51"))
            detTitolo.setText("STAR Mobility");
        if(nomeLinea.startsWith("z4") || nomeLinea.startsWith("z2"))
            detTitolo.setText("Autoguidovie");
        if(nomeLinea.startsWith("R") && !nomeLinea.equalsIgnoreCase("RE80"))
            detTitolo.setText(String.format("%s %s", getString(R.string.regionalLinesScroll), nomeLinea));
        if(nomeLinea.startsWith("RE") && !nomeLinea.equalsIgnoreCase("RE80"))
            detTitolo.setText("Regio Express " + nomeLinea);
        if(nomeLinea.matches("9[0-3]"))
            detTitolo.setText(getString(R.string.filobusKey) + " " + nomeLinea);

        detBadge.setText((nomeLinea.startsWith("MXP")) ? "MXP" : nomeLinea);
        int colorResId = StationDB.getLineColor(this, nomeLinea);
        int coloreEffettivo = ContextCompat.getColor(this, colorResId);

        detBadge.getBackground().setTint(coloreEffettivo);
        detBadge.getBackground().setTintMode(PorterDuff.Mode.SRC_IN);
    }

    private void checkIfReadyToLoadMap() {
        if (pendingMapView != null && (strikeCDNResponse != null || strikeFetchAttempted) && !mapAlreadyLoaded) {
            onMapReady(pendingMapView, strikeCDNResponse);
            mapAlreadyLoaded = true;
        }
    }

    private void onMapReady(MapView mapView, StrikeDescriptor cdnData) {
        FrameLayout layoutMaps = findViewById(R.id.googleMapsFrameLayout);
        LinearLayout layoutLoadingMap = findViewById(R.id.loadingMapsFragmentLayout);
        elaboraStazioni(layoutMaps, layoutLoadingMap, mapView, cdnData);
    }

    private void elaboraStazioni(FrameLayout layoutMaps, LinearLayout layoutLoadingMap, MapView mapView, StrikeDescriptor cdnData) {

        boolean passanteWork = (cdnData != null) && cdnData.isPassanteWorkEnabled();
        List<MetroStation> tutteLeStazioni = new ArrayList<>();
        for (MetroStation s : StationDB.getAllStations(passanteWork)) {
            if (s.getLine().trim().equalsIgnoreCase(nomeLinea.trim()))
                tutteLeStazioni.add(s);
        }

        coloreLinea = ContextCompat.getColor(this, nomeLinea.equalsIgnoreCase("S12") ? R.color.GRAY : StationDB.getLineColor(this, nomeLinea));
        int coloreDefaultText = ContextCompat.getColor(this, R.color.text_primary);
        String hexColor = String.format("#%06X", (0xFFFFFF & coloreLinea));
        String hexColorText = String.format("#%06X", (0xFFFFFF & coloreDefaultText));

        disegnaMarkers(mapView, tutteLeStazioni, hexColor, hexColorText);

        disegnaPolilinea(mapView, tutteLeStazioni, hexColor);

        if(tipoDiLinea.contains("Movibus")) {
            GesturesUtils.getGestures(mapView).addOnMapClickListener(new com.mapbox.maps.plugin.gestures.OnMapClickListener() {
                @Override
                public boolean onMapClick(@NonNull com.mapbox.geojson.Point point) {

                    com.mapbox.maps.ScreenCoordinate pixel = mapView.getMapboxMap().pixelForCoordinate(point);
                    float tolerance = 20f;

                    com.mapbox.maps.ScreenBox screenBox = new com.mapbox.maps.ScreenBox(
                        new com.mapbox.maps.ScreenCoordinate(pixel.getX() - tolerance, pixel.getY() - tolerance),
                        new com.mapbox.maps.ScreenCoordinate(pixel.getX() + tolerance, pixel.getY() + tolerance)
                    );

                    mapView.getMapboxMap().queryRenderedFeatures(
                        new com.mapbox.maps.RenderedQueryGeometry(screenBox),
                        new com.mapbox.maps.RenderedQueryOptions(List.of("marker-layer"), null),
                        expected -> {
                            if (expected.isValue() && !expected.getValue().isEmpty()) {
                                com.mapbox.maps.QueriedRenderedFeature queriedFeature = expected.getValue().get(0);
                                com.mapbox.geojson.Feature clickedFeature = queriedFeature.getQueriedFeature().getFeature();

                                if (clickedFeature.hasProperty("name")) {
                                    String stationName = clickedFeature.getStringProperty("name");
                                    selezionaFermataDaMappa(stationName);
                                }
                            }
                        }
                    );
                    return true;
                }
            });
        }

        if (!tutteLeStazioni.isEmpty()) {
            double latMedia = 0, lngMedia = 0;

            for (MetroStation station : tutteLeStazioni) {
                latMedia += station.getLatitude();
                lngMedia += station.getLongitude();
            }

            latMedia /= tutteLeStazioni.size();
            lngMedia /= tutteLeStazioni.size();
            double zoom = (tipoDiLinea.contains(getString(R.string.tramLinesScroll)) || tipoDiLinea.contains(getString(R.string.filobusKey))) ? 12.5 : (isLineaMetro() ? 11.5 : 10);

            MapboxHelper.setCamera(mapView, latMedia, lngMedia, zoom);
        }

        layoutMaps.setVisibility(View.VISIBLE);
        layoutMaps.setAlpha(0f);
        layoutMaps.animate().alpha(1f).setDuration(300).start();
        layoutLoadingMap.setVisibility(View.GONE);

        mapViewRef = mapView;
        ImageButton positionButton = findViewById(R.id.positionButton);

        positionButton.setImageTintList(ColorStateList.valueOf(coloreLinea));
        positionButton.setOnClickListener(v -> positionButtonClick());

        ///in this section we change the routes only in bus lines with map
        txtDirezioneMappa = findViewById(R.id.txtDirezioneMappa);
        ImageButton changeRouteButton = findViewById(R.id.changeRouteButton);

        boolean isBusLine = nomeLinea != null && nomeLinea.startsWith("z");
        boolean hasRitornoBranch = false;
        for (MetroStation s : tutteLeStazioni) {
            if (s.getBranch() != null && s.getBranch().contains("Ritorno")) {
                hasRitornoBranch = true;
                break;
            }
        }

        if (isBusLine && hasRitornoBranch) {
            changeRouteButton.setVisibility(View.VISIBLE);
            changeRouteButton.setImageTintList(ColorStateList.valueOf(coloreLinea));

            ultimeStazioniDisegnate = tutteLeStazioni;

            aggiornaTestoDirezione(tutteLeStazioni);
            txtDirezioneMappa.setVisibility(View.VISIBLE);

            changeRouteButton.setOnClickListener(v -> {
                ActivityUtils.triggerFeedback(this);
                modalitaRitorno = !modalitaRitorno;

                MapboxHelper.clearAllLineLayers(mapView);
                MapboxHelper.clearMarkers(mapView);

                disegnaPolilinea(mapView, ultimeStazioniDisegnate, hexColor);
                disegnaMarkers(mapView, ultimeStazioniDisegnate, hexColor, hexColorText);
                aggiornaTestoDirezione(ultimeStazioniDisegnate);
            });
        }
        else {
            changeRouteButton.setVisibility(View.GONE);
            if (txtDirezioneMappa != null) txtDirezioneMappa.setVisibility(View.GONE);
        }

        if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED)
            MapboxHelper.enableUserLocation(mapViewRef, false);
    }

    private void disegnaMarkers(MapView mapView, List<MetroStation> stazioni, String hexColor, String hexColorText) {
        List<Feature> markerFeatures = new ArrayList<>();

        for (MetroStation station : stazioni) {
            if (station.getName().equalsIgnoreCase("NO_DRAW")) continue;

            String branch = station.getBranch();
            boolean branchHasRitorno = branch != null && branch.contains("Ritorno");
            boolean branchIsOnlyRitorno = branch != null && branch.trim().equalsIgnoreCase("Ritorno");

            boolean includiStazione = modalitaRitorno ? branchHasRitorno : !branchIsOnlyRitorno;
            if (!includiStazione) continue;

            markerFeatures.add(MapboxHelper.makeStationFeature(station.getLatitude(), station.getLongitude(), station.getName()));
        }

        MapboxHelper.addCircleLayer(mapView, markerFeatures, hexColor, hexColorText);
    }

    private void disegnaPolilinea(MapView mapView, List<MetroStation> stazioni, String hexColor) {
        if (stazioni.size() < 2) return;

        if (modalitaRitorno) {
            disegnaPolilineaRitorno(mapView, stazioni, hexColor);
        } else {
            disegnaPolilineaAndata(mapView, stazioni, hexColor);
        }
    }

    private void disegnaPolilineaRitorno(MapView mapView, List<MetroStation> stazioni, String hexColor) {
        List<Point> points = new ArrayList<>();

        for (MetroStation station : stazioni) {
            String branch = station.getBranch();
            if (branch != null && branch.contains("Ritorno"))
                points.add(Point.fromLngLat(station.getLongitude(), station.getLatitude()));
        }

        if (points.size() < 2) return;

        MapboxHelper.addLineLayer(mapView, "line-source-main", "line-layer-main", points, hexColor, false);
    }

    private void disegnaPolilineaAndata(MapView mapView, List<MetroStation> stazioni, String hexColor) {
        List<MetroStation> stazioniFiltrate = new ArrayList<>();
        for (MetroStation s : stazioni) {
            String branch = s.getBranch();
            boolean branchIsOnlyRitorno = branch != null && branch.trim().equalsIgnoreCase("Ritorno");
            if (!branchIsOnlyRitorno) stazioniFiltrate.add(s);
        }

        if (stazioniFiltrate.size() < 2) return;

        List<MetroStation> mainStations = new ArrayList<>();
        Map<String, List<MetroStation>> branchSecondari = new LinkedHashMap<>();

        for (MetroStation station : stazioniFiltrate) {
            String branch = station.getBranch();
            boolean isPartOfMain = branch != null && branch.contains("Main");

            if (isPartOfMain) {
                mainStations.add(station);
            } else {
                if (!branchSecondari.containsKey(branch)) branchSecondari.put(branch, new ArrayList<>());
                branchSecondari.get(branch).add(station);
            }
        }

        int index = 0;

        if (mainStations.size() >= 2) {
            List<Point> points = new ArrayList<>();

            for (MetroStation station : mainStations)
                points.add(Point.fromLngLat(station.getLongitude(), station.getLatitude()));

            MapboxHelper.addLineLayer(mapView, "line-source-main", "line-layer-main", points, hexColor, false);
        }

        for (Map.Entry<String, List<MetroStation>> entry : branchSecondari.entrySet()) {
            List<MetroStation> branch = entry.getValue();
            if (branch.isEmpty()) continue;

            boolean isPlanned = entry.getKey().toLowerCase().contains("new") || entry.getKey().toLowerCase().contains("nuova");
            List<MetroStation> pool = isPlanned ? new ArrayList<>(stazioniFiltrate) : new ArrayList<>(mainStations);

            if (isPlanned) pool.removeIf(s -> s.getBranch().equals(entry.getKey()));
            if (pool.isEmpty()) continue;

            MetroStation firstS = branch.get(0), lastS = branch.get(branch.size()-1);
            double fd = Double.MAX_VALUE, ld = Double.MAX_VALUE;

            for (MetroStation station : pool) {
                double d1 = Math.hypot(firstS.getLatitude() - station.getLatitude(), firstS.getLongitude() - station.getLongitude());
                double d2 = Math.hypot(lastS.getLatitude() - station.getLatitude(), lastS.getLongitude() - station.getLongitude());
                if (d1 < fd) fd = d1;
                if (d2 < ld) ld = d2;
            }

            List<MetroStation> oriented = new ArrayList<>(branch);
            if (ld < fd) Collections.reverse(oriented);

            MetroStation junction = null;
            double md = Double.MAX_VALUE;
            for (MetroStation p : pool) {
                double d = Math.hypot(oriented.get(0).getLatitude()-p.getLatitude(), oriented.get(0).getLongitude()-p.getLongitude());
                if (d < md) { md = d; junction = p; }
            }

            if (junction == null) continue;

            List<Point> points = new ArrayList<>();
            points.add(Point.fromLngLat(junction.getLongitude(), junction.getLatitude()));
            for (MetroStation station : oriented)
                points.add(Point.fromLngLat(station.getLongitude(), station.getLatitude()));

            index++;

            MapboxHelper.addLineLayer(mapView, "line-source-branch-" + index, "line-layer-branch-" + index, points, hexColor, isPlanned);
        }
    }

    private void aggiornaTestoDirezione(List<MetroStation> stazioni) {
        if (txtDirezioneMappa == null || stazioni == null || stazioni.isEmpty()) return;

        List<MetroStation> filtrate = new ArrayList<>();
        for (MetroStation s : stazioni) {
            if (s.getName().equalsIgnoreCase("NO_DRAW")) continue;

            String branch = s.getBranch();
            boolean branchHasRitorno = branch != null && branch.contains("Ritorno");
            boolean branchIsOnlyRitorno = branch != null && branch.trim().equalsIgnoreCase("Ritorno");

            if (modalitaRitorno) {
                if (branchHasRitorno) filtrate.add(s);
            } else {
                if (!branchIsOnlyRitorno) filtrate.add(s);
            }
        }

        if (filtrate.isEmpty()) {
            txtDirezioneMappa.setText("");
            return;
        }

        String destinazione;
        if (modalitaRitorno) {
            // Ritorno: prima fermata del branch "Ritorno"
            destinazione = filtrate.get(0).getName();
        } else {
            // Andata: ultima fermata del branch "Main"
            destinazione = filtrate.get(filtrate.size() - 1).getName();
        }

        txtDirezioneMappa.setText(String.format("%s → %s", getString(R.string.directionTitleArrivals), destinazione));
    }

    private void selezionaFermataDaMappa(String nomeStazioneMappa) {
        if (routeData == null || routeData.stops == null || dropdownFermate == null) {
            Toast.makeText(this, R.string.arrivalsNotLoaded, Toast.LENGTH_SHORT).show();
            return;
        }

        //UI ELEMENTS
        ActivityUtils.triggerFeedback(this);
        findViewById(R.id.mapCard).setVisibility(View.GONE);
        arriviWrapper.setVisibility(View.VISIBLE);
        arriviNested.setVisibility(View.VISIBLE);

        Chip chipMappa = findViewById(R.id.chipMappa);
        Chip chipArrivi = findViewById(R.id.chipArrivi);

        chipArrivi.setChecked(true);
        chipMappa.setChecked(false);

        updateChipGroupSizes(detActionGroup);

        String stopIdTrovato = null;
        String nomeFermataTrovato = null;

        for (Map.Entry<String, GTFSHelper.GTFSStop> entry : routeData.stops.entrySet()) {
            String nomeGTFS = entry.getValue().name;

            if (nomeGTFS.equalsIgnoreCase(nomeStazioneMappa)) {

                stopIdTrovato = entry.getKey();
                nomeFermataTrovato = nomeGTFS;
                break;
            }
        }

        if (stopIdTrovato != null) {
            selectedStopId = stopIdTrovato;

            dropdownFermate.setText(nomeFermataTrovato, false);
            updateArriviList();
        }
    }

    private void positionButtonClick() {
        if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED)
            MapboxHelper.zoomToUserLocation(mapViewRef);
        else {
            androidx.core.app.ActivityCompat.requestPermissions(
                this,
                new String[]{ android.Manifest.permission.ACCESS_FINE_LOCATION },
                LOCATION_PERMISSION_REQUEST_CODE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                if (mapViewRef != null) {
                    MapboxHelper.enableUserLocation(mapViewRef, false);
                    MapboxHelper.zoomToUserLocation(mapViewRef);
                }
            }
            else
                Toast.makeText(this, getString(R.string.locationPermissionDenied), Toast.LENGTH_SHORT).show();
        }
    }

    private void caricaEventiFiltrati() {
        LinearLayout container = findViewById(R.id.containerLavori);
        View wrapper = findViewById(R.id.lavoriSezioneWrapper);
        View emptyView = findViewById(R.id.emptyViewContainer);

        if (container == null || wrapper == null) return;

        container.removeAllViews();
        wrapper.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

        String searchTag = nomeLinea.matches("9[0-3]") ? "FILOBUS " + nomeLinea.trim() : nomeLinea.trim().toUpperCase();

        String savedLang = DataManager.getStringData(DataKeys.KEY_DEFAULT_LANGUAGE, "🇮🇹 Italiano");
        String langCode = savedLang.contains("English") ? "en" : (savedLang.contains("Spanish") ? "es" : "it");

        List<EventDescriptor> eventiTrovati = new ArrayList<>();

        for (EventDescriptor evento : EventData.listaEventiCompleta) {
            if (evento.getLines() == null || evento.isEventTerminated()) continue;

            for (String lineInEvent : evento.getLines()) {
                if (lineInEvent != null && lineInEvent.trim().toUpperCase().equals(searchTag)) {
                    eventiTrovati.add(evento);
                    break;
                }
            }
        }

        List<View> cards = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (EventDescriptor evento : eventiTrovati) {
            View card = inflater.inflate(R.layout.item_lavoro, container, false);

            boolean important = evento.getDetails() != null && evento.getDetails().contains("[LAVORO IMPORTANTE]");
            String cleanDet = important ? evento.getDetails().replace("[LAVORO IMPORTANTE]", "").trim() : evento.getDetails();

            card.setTag(new Object[]{evento, cleanDet, langCode});
            cards.add(card);
        }

        boolean found = !cards.isEmpty();

        for (View card : cards) {
            Object[] tag = (Object[]) card.getTag();
            EventDescriptor evento = (EventDescriptor) tag[0];
            String cleanDet = (String) tag[1];
            String lang = (String) tag[2];
            card.setTag(null);

            ImageView icona = card.findViewById(R.id.iconEvent);
            if (icona != null) {
                icona.setImageResource(evento.getCardImageID());
                icona.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.text_primary)));
            }

            TextView titolo = card.findViewById(R.id.txtTitle);
            TextView desc   = card.findViewById(R.id.txtDescription);
            ImageView openCloseIcon = card.findViewById(R.id.open_close_descriprion);

            if (titolo != null) titolo.setText(evento.getTitle());
            if (desc != null) {
                desc.setText(cleanDet);
                desc.setVisibility(View.GONE);
            }
            if (openCloseIcon != null) openCloseIcon.setImageResource(R.drawable.ic_down);

            card.setOnClickListener(v -> {
                boolean isExpanded = desc.getVisibility() == View.VISIBLE;
                desc.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
                openCloseIcon.animate().rotation(isExpanded ? -90f : 0f).setDuration(200).start();
                ActivityUtils.triggerFeedback(this);
            });

            TextView txtInizio = card.findViewById(R.id.txtStartDate);
            TextView txtFine = card.findViewById(R.id.txtEndDate);
            TextView companyTxt = card.findViewById(R.id.txtOperator);
            TextView roadsTxt = card.findViewById(R.id.txtRoute);

            if (txtInizio != null) txtInizio.setText(EventDescriptor.formattaData(evento.getStartDate()));
            if (txtFine != null)   txtFine.setText(EventDescriptor.formattaData(evento.getEndDate()));
            if (companyTxt != null) companyTxt.setText(evento.getCompany());
            if (roadsTxt != null)   roadsTxt.setText(evento.getRoads());

            ProgressBar pb = card.findViewById(R.id.progressBarDate);
            if (pb != null) {
                int perc = evento.calcolaPercentuale(evento.getStartDate(), evento.getEndDate());
                pb.setProgress(perc);
                pb.setProgressTintList(ColorStateList.valueOf(perc >= 100 ? Color.parseColor("#4CAF50") : Color.parseColor("#FF5252")));
            }

            Button btnTranslate = card.findViewById(R.id.btnTranslate);
            boolean showTranslate = !lang.equalsIgnoreCase("it") || DataManager.getBoolData(DataKeys.KEY_SHOW_TRANSLATE_BUTTON, false);
            btnTranslate.setVisibility(showTranslate ? View.VISIBLE : View.GONE);
            btnTranslate.setOnClickListener(v -> mostraBottomSheetTraduzione(evento, cleanDet, lang));

            ChipGroup chipGroup = card.findViewById(R.id.chipGroupLinee);
            if (chipGroup != null && evento.getLines() != null) {
                chipGroup.removeAllViews();
                for (String lineName : evento.getLines()) chipGroup.addView(createChip((lineName.contains("Filobus")) ? lineName.replace("Filobus", getString(R.string.filobusKey)) : lineName));
            }

            container.addView(card);
        }

        foundAtLeastOne = found;
        wrapper.setVisibility(found ? View.VISIBLE : View.GONE);
        lavoriNested.setVisibility(found ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(found ? View.GONE : View.VISIBLE);

        ((TextView) findViewById(R.id.emptyView)).setText(EventData.networkError ? getString(R.string.noInternetConnectionError)  : getString(R.string.noWorksOnThisLine));
        ((ImageView) findViewById(R.id.emptyViewIcon)).setImageResource(EventData.networkError ? R.drawable.ic_no_wifi_connection : R.drawable.ic_info);
    }

    private void mostraBottomSheetTraduzione(EventDescriptor evento, String cleanDet, String langCode) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = LayoutInflater.from(this).inflate(R.layout.item_sheet_translated, null);

        ShimmerFrameLayout loadingLayout = sheetView.findViewById(R.id.loadingLayout);
        LinearLayout layoutDefault = sheetView.findViewById(R.id.layoutDefault);
        LinearLayout layoutTerms = sheetView.findViewById(R.id.layoutPrivacy);
        Button acceptTerms = sheetView.findViewById(R.id.btnContinue);
        Button cancelTerms = sheetView.findViewById(R.id.btnCancel);
        TextView downloadingText = sheetView.findViewById(R.id.textDownloading);
        boolean isAcceptingTerms = DataManager.getBoolData(DataKeys.KEY_DOWNLOAD_POLICIES, false);

        loadingLayout.startShimmer();
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();

        if (isAcceptingTerms) {
            layoutTerms.setVisibility(View.GONE);
            layoutDefault.setVisibility(View.VISIBLE);
            downloadingText.setVisibility(View.GONE);
            translateStrings(sheetView, evento, cleanDet, langCode, loadingLayout);
        }
        else {
            layoutDefault.setVisibility(View.GONE);
            layoutTerms.setVisibility(View.VISIBLE);

            acceptTerms.setOnClickListener(v -> {
                layoutDefault.setVisibility(View.VISIBLE);
                layoutTerms.setVisibility(View.GONE);
                downloadingText.setVisibility(View.VISIBLE);
                DataManager.saveBoolData(DataKeys.KEY_DOWNLOAD_POLICIES, true);
                translateStrings(sheetView, evento, cleanDet, langCode, loadingLayout);
            });

            cancelTerms.setOnClickListener(v -> bottomSheetDialog.cancel());
        }
    }

    private boolean isLineaMetro() {return nomeLinea != null && nomeLinea.startsWith("M") && !nomeLinea.startsWith("MXP");}
    private boolean isMalpensaExpress() {return nomeLinea != null && nomeLinea.startsWith("MXP");}
    private boolean isLineaSuburbano() {return nomeLinea != null && nomeLinea.startsWith("S") && !isLineaTilo();}
    private boolean isLineaRegionale() {
        ArrayList<String> suburbanWithNewGraphic = new ArrayList<>(Arrays.asList("R1", "R2", "R3", "R4", "R5", "R6"));
        boolean isValid = false;

        for(int i = 0; i < suburbanWithNewGraphic.size(); i++){
            if (suburbanWithNewGraphic.get(i).equalsIgnoreCase(nomeLinea)) isValid = true;
        }

        return nomeLinea != null && nomeLinea.startsWith("R") && isValid;
    }

    private boolean isLineaTilo() {
        ArrayList<String> tiloLines = new ArrayList<>(Arrays.asList("S10", "S20", "S30", "S40", "S50", "S90", "RE80"));
        boolean isValid = false;

        for(int i = 0; i < tiloLines.size(); i++){
            if (tiloLines.get(i).equalsIgnoreCase(nomeLinea)) isValid = true;
        }

        return nomeLinea != null && isValid;
    }

    private boolean isLineaTram() {
        ArrayList<String> tramLines = new ArrayList<>(Arrays.asList("1", "7"));
        boolean isValid = false;

        for(int i = 0; i < tramLines.size(); i++){
            if (tramLines.get(i).equalsIgnoreCase(nomeLinea)) isValid = true;
        }

        return nomeLinea != null && isValid && tipoDiLinea.contains(getString(R.string.tramLinesScroll));
    }

    private void applyMetroLineColor(View card, int lineColor) {
        View lineTop = card.findViewById(R.id.lineTop);
        if (lineTop != null) lineTop.setBackgroundColor(lineColor);

        View lineBottom = card.findViewById(R.id.lineBottom);
        if (lineBottom != null) lineBottom.setBackgroundColor(lineColor);

        View dot = card.findViewById(R.id.dotInterchange);
        if (dot != null) {
            Drawable background = dot.getBackground();
            if (background instanceof GradientDrawable) {
                GradientDrawable gd = (GradientDrawable) background.mutate();
                float density = getResources().getDisplayMetrics().density;
                gd.setStroke((int) (3 * density), lineColor);
            }
        }
    }

    private void preloadInterscambi() {
        executor.execute(() -> {
            String searchTag = nomeLinea.trim().toUpperCase();
            List<InterchangeInfo> interchanges;

            /*if (isLineaTram())
                interchanges = InterchangesDB.getTramInterchanges(this);*/
            if (tipoDiLinea.contains(getString(R.string.tramLinesScroll)) && !isLineaTram())
                interchanges = StationDB.getInterchangesTrams();
            else if (tipoDiLinea.contains(getString(R.string.filobusKey)))
                interchanges = StationDB.getInterchangesFilobus();
            else if (isLineaMetro())
                interchanges = InterchangesDB.getMetroInterchanges(this);
            else if (isLineaSuburbano())
                interchanges = InterchangesDB.getSuburbanInterchanges();
            else if(isLineaRegionale())
                interchanges = InterchangesDB.getRegionalInterchanges();
            else if (isMalpensaExpress())
                interchanges = InterchangesDB.getMalpensaExpressInterchanges(this);
            else if(isLineaTilo())
                interchanges = InterchangesDB.getTILOInterchanges(this);
            else
                interchanges = StationDB.getInterchanges(this);

            Set<String> seenKeys = new LinkedHashSet<>();
            List<InterchangeInfo> matched = new ArrayList<>();

            for (InterchangeInfo info : interchanges) {
                if (info.getLines() == null || info.getLines().length == 0) continue;

                boolean match = false;
                if (isLineaMetro() || isLineaSuburbano() || isLineaRegionale() || isMalpensaExpress() || isLineaTilo() || isLineaTram()) {
                    String primaryLine = info.getLines()[0].trim().toUpperCase();
                    match = primaryLine.equals(searchTag);
                }
                else {
                    for (String line : info.getLines()) {
                        if (line.trim().toUpperCase().equals(searchTag)) {
                            match = true;
                            break;
                        }
                    }
                }
                if (!match) continue;

                String compositeKey = info.getKey() + "|" + searchTag;
                if (!seenKeys.contains(compositeKey)) {
                    seenKeys.add(compositeKey);
                    matched.add(info);
                }
            }

            Set<String> branchSet = new LinkedHashSet<>();
            for (InterchangeInfo info : matched) {
                String branch = info.getBranch();
                if (branch != null && !branch.isEmpty() && !branch.equals("Main"))
                    branchSet.add(branch);
            }

            List<String> availableBranches = new ArrayList<>(branchSet);
            boolean hasMultipleBranches = !availableBranches.isEmpty();

            LinearLayout container = findViewById(R.id.containerInterscambi);
            prebuildAllBranchViews(matched, container, () -> {
                if (selectedBranch == null && hasMultipleBranches)
                    selectedBranch = availableBranches.get(0);

                Button btnBranch = findViewById(R.id.buttonSelectBranch);
                if (btnBranch != null) {
                    btnBranch.setVisibility(hasMultipleBranches ? View.VISIBLE : View.GONE);
                    if (hasMultipleBranches) btnBranch.setText(selectedBranch);
                    btnBranch.setOnClickListener(v -> {
                        ActivityUtils.triggerFeedback(this);
                        showBranchDialog(availableBranches, matched);
                    });
                }

                interscambiPreloaded = true;

                Chip chipInterscambi = findViewById(R.id.chipInterscambi);
                if (chipInterscambi != null && chipInterscambi.isChecked())
                    mostraInterscambiCaricati(matched);
            });
        });
    }

    private BottomSheetDialog activeBranchDialog;
    private void showBranchDialog(List<String> branches, List<InterchangeInfo> allMatched) {

        dismissActiveBranchDialog();

        BottomSheetDialog dialog = new BottomSheetDialog(this);

        activeBranchDialog = dialog;
        dialog.setOnDismissListener(d -> {
            if (activeBranchDialog == dialog) activeBranchDialog = null;
        });

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        int px16 = (int)(16 * getResources().getDisplayMetrics().density);
        int px12 = (int)(12 * getResources().getDisplayMetrics().density);
        int px24 = (int)(24 * getResources().getDisplayMetrics().density);
        root.setPadding(px16, px24, px16, px24);

        TextView title = new TextView(this);
        title.setText(getString(R.string.selectBranch));
        title.setTextSize(18);
        title.setTypeface(ResourcesCompat.getFont(this, R.font.font_main), Typeface.BOLD);
        title.setTextColor(ContextCompat.getColor(this, R.color.text_primary));

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(0, 0, 0, px16);
        title.setLayoutParams(titleParams);
        root.addView(title);

        ChipGroup chipGroup = new ChipGroup(this);
        chipGroup.setSingleSelection(true);
        chipGroup.setChipSpacingHorizontal(px12);

        int lineColor = coloreLinea;
        int uncheckedBg = Color.BLACK;
        int uncheckedStroke = Color.GRAY;

        float strokeWidthDp = 1.5f;
        int strokeWidthPx = (int)(strokeWidthDp * getResources().getDisplayMetrics().density);

        chipGroup.setSelectionRequired(true);

        for (String branch : branches) {
            Chip chip = new Chip(this);
            chip.setText(branch);
            chip.setCheckable(true);
            chip.setChecked(branch.equals(selectedBranch));
            chip.setTypeface(ResourcesCompat.getFont(this, R.font.font_main), Typeface.BOLD);

            ColorStateList bgStates = new ColorStateList(
                new int[][]{
                    new int[]{ android.R.attr.state_checked },
                    new int[]{ -android.R.attr.state_checked }
                },
                new int[]{ lineColor, uncheckedBg }
            );
            chip.setChipBackgroundColor(bgStates);

            ColorStateList strokeStates = new ColorStateList(
                new int[][]{
                    new int[]{ android.R.attr.state_checked },
                    new int[]{ -android.R.attr.state_checked }
                },
                new int[]{ lineColor, uncheckedStroke }
            );
            chip.setChipStrokeColor(strokeStates);
            chip.setChipStrokeWidth(strokeWidthPx);

            ColorStateList textStates = new ColorStateList(
                new int[][]{
                    new int[]{ android.R.attr.state_checked },
                    new int[]{ -android.R.attr.state_checked }
                },
                new int[]{ Color.WHITE, Color.WHITE }
            );
            chip.setTextColor(textStates);

            float density = getResources().getDisplayMetrics().density;
            int heightPx = (int)(36 * density);
            chip.setChipMinHeight(heightPx);
            chip.setMinHeight(heightPx);
            chip.setChipStartPadding(px12);
            chip.setChipEndPadding(px12);

            chip.setChecked(branch.equals(selectedBranch));
            chip.setOnClickListener(v -> {
                if (branch.equals(selectedBranch))
                    dialog.dismiss();
            });

            chipGroup.addView(chip);
        }

        root.addView(chipGroup);

        chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;
            int checkedId = checkedIds.get(0);
            int idx = group.indexOfChild(group.findViewById(checkedId));
            selectedBranch = branches.get(idx);

            Button btnBranch = findViewById(R.id.buttonSelectBranch);
            if (btnBranch != null) btnBranch.setText(selectedBranch);

            applyBranchFromCache(findViewById(R.id.containerInterscambi), allMatched);
            dialog.dismiss();
        });

        dialog.setContentView(root);
        dialog.show();
    }

    private void dismissActiveBranchDialog() {
        if (activeBranchDialog != null && activeBranchDialog.isShowing()) {
            activeBranchDialog.dismiss();
        }
        activeBranchDialog = null;
    }

    private final Map<String, List<View>> branchViewCache = new LinkedHashMap<>();
    private void prebuildAllBranchViews(List<InterchangeInfo> allMatched, LinearLayout container, Runnable onComplete) {
        executor.execute(() -> {
            Map<String, List<InterchangeInfo>> branchMap = new LinkedHashMap<>();
            List<InterchangeInfo> mainItems = new ArrayList<>();

            for (InterchangeInfo info : allMatched) {
                if ("Main".equals(info.getBranch()))
                    mainItems.add(info);
                else
                    branchMap.computeIfAbsent(info.getBranch(), k -> new ArrayList<>()).add(info);
            }

            Collections.sort(mainItems, (a, b) -> Integer.compare(a.getLineOrder(), b.getLineOrder()));
            for (List<InterchangeInfo> list : branchMap.values())
                Collections.sort(list, (a, b) -> Integer.compare(a.getLineOrder(), b.getLineOrder()));

            boolean isValidNewInterface = (isLineaMetro() || isLineaSuburbano() || isLineaRegionale() || isMalpensaExpress() || isLineaTilo() || isLineaTram());

            int lineColor = isValidNewInterface ? ContextCompat.getColor(this, StationDB.getLineColor(this, nomeLinea)) : 0;
            LayoutInflater inflater = LayoutInflater.from(this);

            Map<String, List<View>> cache = new LinkedHashMap<>();

            List<View> mainViews = buildViewsForList(mainItems, inflater, container, isValidNewInterface, lineColor);
            cache.put("Main", mainViews);

            for (Map.Entry<String, List<InterchangeInfo>> entry : branchMap.entrySet()) {
                List<InterchangeInfo> combined = new ArrayList<>();
                String branchName = entry.getKey();

                if (branchName.equals("P.Za Abbiategrasso") || branchName.equals("Assago Milanofiori Forum")) {
                    combined.addAll(mainItems);
                    combined.addAll(entry.getValue());
                }
                else {
                    combined.addAll(entry.getValue());
                    combined.addAll(mainItems);
                }

                List<View> views = buildViewsForList(combined, inflater, container, isValidNewInterface, lineColor);
                cache.put(entry.getKey(), views);
            }

            runOnUiThread(() -> {
                branchViewCache.putAll(cache);
                onComplete.run();
            });
        });
    }

    private List<View> buildViewsForList(List<InterchangeInfo> items, LayoutInflater inflater, LinearLayout container, boolean isMetro, int lineColor) {
        List<View> views = new ArrayList<>();

        for (InterchangeInfo evento : items) {
            View card = inflater.inflate(
                isMetro ? R.layout.item_interchange : R.layout.interchange_info_old,
                container,
                false
            );

            if (isMetro) {
                ImageView icona = card.findViewById(R.id.iconTransport);
                if (icona != null) icona.setImageResource(evento.getCardImageID());

                TextView titolo = card.findViewById(R.id.txtTitle);
                if (titolo != null) titolo.setText(evento.getKey().toUpperCase());

                ChipGroup chipGroup = card.findViewById(R.id.chipGroupLinee);
                if (chipGroup != null && evento.getLines() != null) {
                    chipGroup.removeAllViews();
                    for (String lineName : evento.getLines()) {
                        if (!lineName.equalsIgnoreCase(nomeLinea))
                            chipGroup.addView(createChip(lineName));
                    }
                }

                TextView emptyInterchanges = card.findViewById(R.id.noInterchangesTxt);
                ImageView iconTransport = card.findViewById(R.id.iconTransport);
                if (chipGroup != null && chipGroup.getChildCount() == 0 && emptyInterchanges != null) {
                    emptyInterchanges.setVisibility(View.VISIBLE);
                    iconTransport.setImageResource(R.drawable.ic_no_interchanges);
                }

                applyMetroLineColor(card, lineColor);
            }
            else {
                ImageView icona = card.findViewById(R.id.iconTransport);
                if (icona != null) {
                    icona.setImageResource(evento.getCardImageID());
                    icona.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.text_primary)));
                }

                TextView titolo = card.findViewById(R.id.txtTitle);
                TextView desc = card.findViewById(R.id.txtLineCode);
                TextView txtStationSub = card.findViewById(R.id.txtStationSubtitle);

                if (txtStationSub != null) txtStationSub.setText(evento.getKey());
                if (titolo != null)
                    titolo.setText(evento.getKey().equals("Lodi TIBB") ? "Milano Scalo Romana" : evento.getKey());
                if (desc != null) desc.setText(nomeLinea);

                ChipGroup chipGroup = card.findViewById(R.id.chipGroupLinee);
                if (chipGroup != null && evento.getLines() != null) {
                    chipGroup.removeAllViews();
                    for (String lineName : evento.getLines()) chipGroup.addView(createChip(lineName));
                }
            }

            views.add(card);
        }

        if (isMetro && !views.isEmpty()) {
            View lineTop = views.get(0).findViewById(R.id.lineTop);
            View lineBottom = views.get(views.size() - 1).findViewById(R.id.lineBottom);

            if (lineTop != null) lineTop.setVisibility(View.INVISIBLE);
            if (lineBottom != null) lineBottom.setVisibility(View.INVISIBLE);
        }

        return views;
    }

    private void applyBranchFromCache(LinearLayout container, List<InterchangeInfo> allMatched) {
        String key = (selectedBranch != null) ? selectedBranch : "Main";
        List<View> views = branchViewCache.get(key);

        if (views == null || views.isEmpty()) return;

        container.removeAllViews();
        for (View v : views) {
            if (v.getParent() != null)
                ((ViewGroup) v.getParent()).removeView(v);

            container.addView(v);
        }
    }

    private void caricaInterscambiLinee() {
        if (interscambiPreloaded) {
            mostraInterscambiCaricati(null);
            return;
        }

        if (interscambiWrapper != null) interscambiWrapper.setVisibility(View.VISIBLE);
        if (interscambiNested != null) interscambiNested.setVisibility(View.GONE);
        if (interscambiLoadingState != null) interscambiLoadingState.setVisibility(View.VISIBLE);

        Button btnBranch = findViewById(R.id.buttonSelectBranch);
        if (btnBranch != null) btnBranch.setVisibility(View.GONE);

        findViewById(R.id.emptyViewContainer).setVisibility(View.GONE);
    }

    private void mostraInterscambiCaricati(List<InterchangeInfo> allMatched) {
        if (interscambiLoadingState != null) interscambiLoadingState.setVisibility(View.GONE);

        LinearLayout container = findViewById(R.id.containerInterscambi);
        boolean hasChildren = container != null && container.getChildCount() > 0;

        if (!hasChildren) {
            if (allMatched != null)
                applyBranchFromCache(container, allMatched);
            else if (interscambiPreloaded && !branchViewCache.isEmpty()) {
                String key = (selectedBranch != null) ? selectedBranch : "Main";
                List<View> cachedViews = branchViewCache.get(key);

                if (cachedViews != null && !cachedViews.isEmpty() && container != null) {
                    container.removeAllViews();

                    for (View v : cachedViews) {
                        if (v.getParent() != null) ((ViewGroup) v.getParent()).removeView(v);
                        container.addView(v);
                    }
                }
            }
        }

        hasChildren = container != null && container.getChildCount() > 0;

        if (interscambiWrapper != null) interscambiWrapper.setVisibility(View.VISIBLE);
        if (interscambiNested != null) interscambiNested.setVisibility(hasChildren ? View.VISIBLE : View.GONE);

        View emptyViewContainer = findViewById(R.id.emptyViewContainer);
        TextView emptyView = findViewById(R.id.emptyView);

        if (emptyViewContainer != null)
            emptyViewContainer.setVisibility(hasChildren ? View.GONE : View.VISIBLE);
        if (emptyView != null)
            emptyView.setText(getString(R.string.noInterchanges));
    }

    private void caricaFermateInterscambio() {
        LinearLayout layoutDetInterscambio = findViewById(R.id.layoutDetInterscambio);
        TextView detInterscambio = findViewById(R.id.detInterscambio);
        TextView detAttesa = findViewById(R.id.detAttesa);
        TextView txtInterscambio = findViewById(R.id.txtInterscambio);
        TextView txtMinutes = findViewById(R.id.txtMinutes);
        ChipGroup chipGroupLinee = findViewById(R.id.chipGroupLinee);
        ImageView iconInterchange = findViewById(R.id.iconInterscambio);

        layoutDetInterscambio.setVisibility(View.VISIBLE);
        detAttesa.setVisibility(View.GONE);
        txtInterscambio.setVisibility(View.VISIBLE);
        txtMinutes.setVisibility(View.GONE);
        chipGroupLinee.setVisibility(View.VISIBLE);

        for (InterchangeInfo info : StationDB.getBusInterchanges()) {
            if (info == null || info.getLinesToShow() == null) continue;

            String searchTag = nomeLinea.trim().toUpperCase();
            boolean matchFound = false;

            for (String lineInEvent : info.getLinesToShow()) {
                if (lineInEvent != null) {
                    if (lineInEvent.trim().toUpperCase().equals(searchTag)) {
                        matchFound = true;
                        break;
                    }
                }
            }

            if (matchFound) {
                if (info.getLines() != null) {
                    detInterscambio.setText(info.getKey());
                    chipGroupLinee.removeAllViews();
                    iconInterchange.setImageResource(info.getCardImageID());

                    for (String lineName : info.getLines()) {chipGroupLinee.addView(createChip(lineName));}
                }
            }
        }
        if (detInterscambio.getText().toString().isEmpty()) {
            detInterscambio.setTypeface(detInterscambio.getTypeface(), Typeface.NORMAL);
            layoutDetInterscambio.setVisibility(View.GONE);
            findViewById(R.id.detInterscambioEmpty).setVisibility(View.VISIBLE);
            findViewById(R.id.viewChipGroup).setVisibility(View.GONE);
        }
    }

    private Chip createChip(String name) {
        /// This method is a refactor one, helps handling the intense Chip creation process to the CPU.
        /// @PARAMETERS
        /// String name is the name of the line to display.

        Chip chip = new Chip(this);
        chip.setText(name);

        ShapeAppearanceModel cornerRadius = chip.getShapeAppearanceModel().toBuilder().setAllCornerSizes(10f).build();
        chip.setShapeAppearanceModel(cornerRadius);

        float density = getResources().getDisplayMetrics().density;
        int heightPx = (int) (26 * density);

        if(name.contains(getString(R.string.filobusKey)) || name.matches("9[0-3]")){
            chip.setChipIcon(ContextCompat.getDrawable(this, R.drawable.ic_bolt));
            chip.setChipIconTint(ColorStateList.valueOf(Color.WHITE));
            chip.setIconStartPadding(10);
        }
        else if(name.contains("N")){
            chip.setChipIcon(ContextCompat.getDrawable(this, R.drawable.ic_dark));
            chip.setChipIconTint(ColorStateList.valueOf(Color.WHITE));
            chip.setIconStartPadding(10);
        }

        chip.setChipMinHeight(heightPx);
        chip.setMinHeight(heightPx);
        chip.setChipStartPadding(0f);
        chip.setChipEndPadding(0f);
        chip.setTextStartPadding(15f);
        chip.setTextEndPadding(15f);
        chip.setChipStrokeWidth(0f);
        chip.setTextSize(13f);
        chip.setTypeface(Typeface.create(ResourcesCompat.getFont(this, R.font.inter), Typeface.BOLD));

        int colore = ContextCompat.getColor(this, StationDB.getLineColor(this, name));
        chip.setChipBackgroundColor(ColorStateList.valueOf(colore));
        chip.setTextColor(ContextCompat.getColor(this, R.color.White));
        chip.setCloseIconVisible(false);
        chip.setClickable(false);
        chip.setCheckable(false);
        chip.setEnsureMinTouchTargetSize(false);
        return chip;
    }

    private void aggiornaInfoSuperiori() {
        TextView tvDirezioni = findViewById(R.id.detDirezioni);
        TextView tvAttesa = findViewById(R.id.detAttesa);
        TextView tvLavori = findViewById(R.id.detLavori);

        tvDirezioni.setText(getCapolinea(nomeLinea));

        Log.d("LINEA", nomeLinea);

        if (nomeLinea.startsWith("M") || nomeLinea.startsWith("S") || nomeLinea.equalsIgnoreCase("RE80") || nomeLinea.startsWith("RE") || nomeLinea.startsWith("R") || (nomeLinea.startsWith("9") && !tipoDiLinea.contains(getString(R.string.tramLinesScroll))))
            tvAttesa.setText(getFrequenza(nomeLinea));
        else if(tipoDiLinea.contains(getString(R.string.tramLinesScroll)))
            tvAttesa.setText("5-20 min.");
        else
            tvAttesa.setText(getString(R.string.variableFrequency));

        int numeroLavoriProgrammati = 0, numeroLavoriAttuali = 0, numeroLavori = 0;
        String searchTag = (nomeLinea.matches("9[0-3]")) ? ("FILOBUS " + nomeLinea.trim()) : nomeLinea.trim();

        for (EventDescriptor event : EventData.listaEventiCompleta) {
            long start = event.getStartDateMillis();
            long end = event.getEndDateMillis();
            long oggi = System.currentTimeMillis();
            long startP = event.getStartDateMillis();

            for (String line : event.getLines()) {
                if (line.equalsIgnoreCase(searchTag)) {
                    numeroLavori++;

                    if(start > 0 && end > 0 && oggi >= start && oggi <= end) numeroLavoriAttuali++;
                    else if(startP > 0 && oggi < startP) numeroLavoriProgrammati++;

                    break;
                }
            }
        }

        tvLavori.setText((numeroLavori > 0) ? String.format("%s %s, %s %s.", numeroLavoriAttuali, ContextCompat.getString(this, R.string.currentWorksTitle), numeroLavoriProgrammati, ContextCompat.getString(this, R.string.scheduledWorksTitle)) : ContextCompat.getString(this, R.string.fallbackNoWorks));
    }

    public void fetchDeviations(StrikeDescriptor cdnData) {
        if (cdnData == null) return;
        String[] lineeDeviate = cdnData.getLinesDeviation();
        String[] linkLinee = cdnData.getLinesDeviationLinks();
        String[] gtfsSupportedLines = cdnData.getSupportedGTFSLines();
        String[] suburbanInterruptions = cdnData.getSuburbanWithInterruptions();
        String[] suburbanLinks = cdnData.getSuburbanInterruptionLinks();

        LinearLayout deviazioneLinea = findViewById(R.id.deviazioneLinea);
        ImageView mapDeviationBtn = findViewById(R.id.mapDeviationBtn);

        int i = 0;
        for (String linea : lineeDeviate) {
            if (linea.equals(nomeLinea)) {
                String lineLink = linkLinee[i];
                deviazioneLinea.setVisibility(View.VISIBLE);
                deviazioneLinea.setOnClickListener(v -> ActivityUtils.openURLWithTabBuilder(this, getSupportFragmentManager(), lineLink));
                mapDeviationBtn.setVisibility(lineLink.equalsIgnoreCase("null") ? View.GONE : View.VISIBLE);
                mapDeviationBtn.setOnClickListener(v -> ActivityUtils.openURLWithTabBuilder(this, getSupportFragmentManager(), lineLink));
            }
            i++;
        }

        //*MODIFICHE CIRCOLAIZONE
        /// Le linee suburbane hanno lavori di modifiche della circolazione, in questa sezione mostriamo questa info.
        LinearLayout interruzioneTratta = findViewById(R.id.interruzioneTratta);
        ImageView mapTrackBtn = findViewById(R.id.mapTrackBtn);

        for (int j = 0; j < suburbanInterruptions.length; j++) {
            int finalJ = j;
            if (suburbanInterruptions[j].equalsIgnoreCase(nomeLinea)) {
                interruzioneTratta.setVisibility(View.VISIBLE);
                mapTrackBtn.setOnClickListener(v -> ActivityUtils.openURLWithTabBuilder(this, getSupportFragmentManager(), suburbanLinks[finalJ]));
                interruzioneTratta.setOnClickListener(v -> ActivityUtils.openURLWithTabBuilder(this, getSupportFragmentManager(), suburbanLinks[finalJ]));
            }
        }

        if (Arrays.stream(gtfsSupportedLines).anyMatch(nomeLinea::equals)) {
            findViewById(R.id.chipArrivi).setVisibility(View.VISIBLE);
            updateChipGroupSizes(detActionGroup);
            loadGTFSData();
        }
        else
            findViewById(R.id.chipArrivi).setVisibility(View.GONE);
    }

    private boolean isDarkMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }

    private String getCapolinea(String linea) {
        if (linea == null) return getString(R.string.directionsNotAvailable);

        switch (linea.toUpperCase().trim()) {
            case "M1": return "Sesto 1° Maggio FS - Rho Fiera / Bisceglie";
            case "M2": return "Assago Forum / Abbiategrasso - Gessate / Cologno Nord";
            case "M3": return "San Donato - Comasina";
            case "M4": return "San Cristoforo - Linate Aeroporto";
            case "M5": return "San Siro Stadio - Bignami";

            case "S1": return (strikeCDNResponse != null && strikeCDNResponse.isPassanteWorkEnabled()) ? "Milano Bovisa - Lodi" : "Saronno - Lodi";
            case "S2": return "Seveso - Milano Rogoredo"; //* Mariano Comense - Milano Rogoredo
            case "S3": return "Saronno - Milano Cadorna";
            case "S4": return "Palazzolo Milanese - Milano Cadorna"; //* Camnago-Lentate - Milano Cadorna
            case "S5": return (strikeCDNResponse != null && strikeCDNResponse.isPassanteWorkEnabled()) ? "Varese - Milano Lambrate - Treviglio" : "Varese - Treviglio";
            case "S6": return "Novara - Rho"; //* Novara - Pioltello-Limito / Treviglio
            case "S7": return "Lecco - Milano Porta Garibaldi";
            case "S8": return "Lecco - Carnate - Milano Porta Garibaldi";
            case "S9": return "Saronno - Albairate Vermezzo";
            case "S11": return "Milano Porta Garibaldi - Como S. Giovanni"; //* Rho - Como S. Giovanni
            case "S12": return "Melegnano - Cormano";
            case "S13": return (strikeCDNResponse != null && strikeCDNResponse.isPassanteWorkEnabled()) ? "Milano Rogoredo - Pavia" : "Garbagnate Milanese - Pavia";
            case "S19": return "Albairate Vermezzo - Milano Rogoredo";
            case "S31": return "Brescia - Iseo";

            case "R1": return "Bergamo - Brescia";
            case "R2": return "Bergamo - Treviglio";
            case "R3": return "Brescia - Iseo - Breno";
            case "R4": return "Brescia - Treviglio - Milano";
            case "R5": return "Brescia - Cremona";
            case "R6": return "Cremona - Treviglio";
            case "R7": return "Lecco - Bergamo";
            case "R8": return "Brescia - Piadena - Parma";
            case "R9": return "Rovato - Bornato - Iseo";
            case "R11": return "Colico - Chiavenna";
            case "R12": return "Sondrio - Tirano";
            case "R13": return "Lecco - Colico - Sondrio";
            case "R14": return "Bergamo - Carnate - Milano";
            case "R15": return "Seregno - Carnate";
            case "R16": return "Asso - Milano";
            case "R17": return "Como - Saronno - Milano";
            case "R18": return "Como - Molteno - Lecco";
            case "R21": return "Luino - Gallarate - Milano";
            case "R22": return "Varese - Saronno - Milano";
            case "R23": return "Domodossola - Gallarate - Milano";
            case "R24": return "Laveno - Sesto Calende";
            case "R25": return "Novara - Mortara";
            case "R27": return "Novara - Saronno - Milano";
            case "R31": return "Mortara - Milano";
            case "R32": return "Mortara - Alessandria";
            case "R33": return "Pavia - Voghera";
            case "R34": return "Stradella - Pavia - Milano";
            case "R35": return "Pavia - Torreberetti - Alessandria";
            case "R36": return "Pavia - Mortara - Vercelli";
            case "R37": return "Pavia - Codogno";
            case "R38": return "Piacenza - Lodi - Milano";
            case "R39": return "Codogno - Cremona";
            case "R40": return "Cremona - Mantova";
            case "R41": return "Voghera - Piacenza";

            case "RE1": return "Laveno - Saronno - Milano";
            case "RE2": return "Milano - Bergamo";
            case "RE3": return "Brescia - Iseo - Edolo";
            case "RE4": return "Domodossola - Milano";
            case "RE5": return "Porto Ceresio - Varese - Milano";
            case "RE6": return "Verona - Brescia - Milano";
            case "RE7": return "Como - Saronno - Milano";
            case "RE8": return "Tirano - Lecco - Milano";
            case "RE11": return "Mantova - Codogno - Milano";
            case "RE13": return "Asti/Arquata - Pavia - Milano";

            case "MXP1": return "Malpensa T2 - Milano Centrale"; //* Gallarate - Malpensa T2 - Milano Centrale
            case "MXP2": return "Malpensa T2 - Milano Cadorna";
            case "S10": return "Biasca - Como";
            case "S20": return "Castione - Locarno";
            case "S30": return "Cadenazzo - Gallarate";
            case "S40": return "Como - Varese";
            case "S50": return "Biasca - Malpensa Aeroporto T1-T2";
            case "S90": return "Bellinzona - Mendrisio";
            case "RE80": return "Locarno - Milano Centrale";

            case "1": return "Roserio - Greco";
            case "2": return "P.Le Negrelli - P.Za Bausan";
            case "3": return "Duomo M1 M3 - Gratosoglio";
            case "4": return "Cairoli M1 - Niguarda (Parco Nord)";
            case "5": return "Niguarda (Ospedale) - Ortica";
            case "7": return "P.Le Lagosta - Q.Re Adriano";
            case "9": return "Centrale FS M2 M3 - P.Ta Genova M2";
            case "10": return "P.Za 24 Maggio - V.Le Lunigiana";
            case "12": return "P.Za Ovidio - Roserio";
            case "14": return "Lorenteggio - Cimitero Maggiore";
            case "15": return "Duomo M1 M3 - Rozzano (Via G. Rossa)";
            case "16": return "Stadio San Siro M5 - Via Monte Velino";
            case "19": return "P.Za Castelli - Lambrate FS M2";
            case "24": return "Piazza Fontana - Vigentino";
            case "27": return "V.Le Ungheria - Piazza Fontana";
            case "31": return "Bicocca M5 - Cinisello (1° Maggio)";
            case "33": return "P.Le Lagosta - Rimembranze di Lambrate";

            case "90": return String.format("%s (Lotto M1 M5 - Lodi M3)", getString(R.string.circolareDestraDesc));
            case "91": return String.format("%s (Lodi M3 - Lotto M1 M5)", getString(R.string.circolareDestraDesc));
            case "92": return "Via Varè (Bovisa FN) - Lodi M3";
            case "93": return "V.Le Omero - Lambrate FS";

            case "Z601": return "Legnano - Molino Dorino M1";
            case "Z602": return "Milano Cadorna - Legnano";
            case "Z603": return "Milano Cadorna - Nerviano/S.Vittore";
            case "Z6C3": return "San Vittore Olona - Cerro Maggiore - Milano Cadorna";
            case "Z606": return "Molino Dorino M1 - Cerro Maggiore";
            case "Z611": return "Legnano - Parabiago";
            case "Z612": return "Legnano - Arese (Il CENTRO)";
            case "Z616": return "Pregnana Milanese - Rho FS";
            case "Z617": return "Molino Dorino M1 - Origgio / Lainate";
            case "Z618": return "Rho FS - Vanzago";
            case "Z619": return "Pogliano M. - Plesso IST Maggiolini";
            case "Z620": return "Magenta - Molino Dorino M1";
            case "Z621": return "Cuggiono - Molino Dorino M1";
            case "Z622": return "Cuggiono - Ossona - Cornaredo";
            case "Z625": return "Busto Arsizio - Busto Garolfo";
            case "Z627": return "Castano Primo - Legnano";
            case "Z636": return "Nosate - Legnano";
            case "Z641": return "Castano Primo - Magenta FS";
            case "Z642": return "Magenta - Legnano";
            case "Z643": return "Vittuone - Parabiago";
            case "Z644": return "Arconate - Parabiago";
            case "Z646": return "Castano Primo - Magenta FS";
            case "Z647": return "Cornaredo - Castano Primo";
            case "Z649": return "Magenta - Arluno - Molino Dorino M1";

            case "Z501": return "Milano Famagosta - Binasco";
            case "Z509": return "Motta Visconti - Milano Famagosta";
            case "Z510": return "Milano Famagosta - Lacchiarella - Giussano";
            case "Z515": return "Milano Famagosta - Zibido";
            case "Z516": return "Milano Famagosta - Rosate - Besate";

            case "Z551": return "Abbiategrasso - Bisceglie M1";
            case "Z552": return "Abbiategrasso - S. Stefano FS";
            case "Z553": return "Abbiategrasso - Milano Romolo M2";
            case "Z554": return "Albairate - Bubbiano";
            case "Z555": return "Abbiategrasso - Casorate/Binasco";
            case "Z556": return "Abbiategrasso FS - Motta Visconti";
            case "Z557": return "Gaggiano (De Gasperi) - San Vito";
            case "Z559": return "Magenta FS - Abbiategrasso FS";
            case "Z560": return "Abbiategrasso FS - Bisceglie M1";

            case "Z401": return "Melzo FS - Vignate - Villa Fiorita M2";
            case "Z402": return "Cernusco M2 - Pioltello FS - S.Felice";
            case "Z403": return "Gorgonzola M2 - Melzo (Circolare)";
            case "Z404": return "Melzo FS - Inzago - Gessate M2";
            case "Z405": return "Gessate M2 - Cassano D'Adda - Treviglio";
            case "Z406": return "Trecella - Bellinzago - Gessate M2";
            case "Z407": return "Gorgonzola M2 - Truccazzano";
            case "Z409": return "Rodano - S.Felice - Linate Aereoporto";
            case "Z410": return "Pantigliate - Peschiera - S.Donato M3";
            case "Z411": return "Melzo FS - Settala - S.Donato M3";
            case "Z412": return "Zelo B.P - Paullo - S.Donato M3";
            case "Z413": return "Tribiano - S.Donato M3";
            case "Z415": return "Melegnano - Dresano - S.Donato M3";
            case "Z418": return "S.Zenone FS - Casalmaiocco";
            case "Z419": return "Paullo - Melzo - Gorgonzola M2";
            case "Z420": return "Vizzolo - Melegnano - S.Donato M3";
            case "Z431": return "Melegnano FS - Carpiano/Cerro L.";
            case "Z432": return "Melegnano FS - Dresano - Vizzolo (Circolare)";
            case "Z203": return "Muggiò - Monza FS - Cologno Nord M2";
            case "Z205": return "Limbiate Mombello - Varedo - Monza FS";
            case "Z209": return "Cesano FN - Desio - Lissone";
            case "Z219": return "Monza FS - Muggiò - Paderno Dugnano";
            case "Z221": return "Sesto S.G. - Monza FS - Carate";
            case "Z222": return "Sesto S.G. - S. Fruttoso - Monza FS";
            case "Z225": return "Sesto S.G. - Cinisello B. - Nova M.se";
            case "Z227": return "Monza H/Lissone FS - Muggiò - Cinisello";
            case "Z228": return "Seregno FS - Lissone - Monza FS";
            case "Z229": return "Paderno ITC - Cusano - Cinisello B.";
            case "Z231": return "Carate - Giussano - Seregno FS - Desio";
            case "Z232": return "Desio - Seregno - Besana FS";
            case "Z233": return "Triuggio - Albiate - Seregno FS";
            case "Z234": return "Vedano Al L. - Lissone - Muggiò";
            case "Z242": return "Desio - Seregno FS - Renate";
            case "Z250": return "Lissone FS - Desio FS - Cesano FN";
            case "Z251": return "Desio FS - Bovisio M. - Limbiate - Cesano FN";
            default: return getString(R.string.directionsNotAvailableFor) + linea;
        }
    }

    public boolean haveMapAvailable(){return tipoDiLinea.contains(getString(R.string.tramLinesScroll)) || this.nomeLinea.contains("z");}

    public String getFrequenza(String lineName){
        switch(lineName) {
            case "M1": return "Sesto FS: 3 min | Rho/Bisceglie: 7-8 min.";
            case "M2": return "Gessate: 12-15 min | Assago: 9-10 min.";
            case "M3": return "4-5 min.";
            case "M4": return "2-3 min.";
            case "M5": return "4 min.";
            case "S10": return String.format("1 %s - 45 min.", getString(R.string.hourPrefix));
            case "S30": return String.format("2 %s.", getString(R.string.hoursPrefix));
            case "RE80":
            case "S7":
                return String.format("30 min - 1 %s.", getString(R.string.hourPrefix));
            case "S1":
            case "S2":
            case "S3":
            case "S4":
            case "S5":
            case "S6":
            case "S8":
            case "S9":
            case "S11":
            case "S12":
            case "S13":
            case "S19":
            case "S20":
            case "S90":
            case "MXP1":
            case "MXP2":
                return "30 min.";
            case "S31":
            case "S40":
            case "S50":
            case "R2":
            case "R5":
            case "R6":
            case "R7":
            case "R13":
            case "R21":
            case "R23":
            case "R25":
            case "R33":
            case "R34":
                return String.format("1 %s.", getString(R.string.hourPrefix));
            case "RE1":
            case "RE8":
            case "R1":
            case "R4":
            case "R14":
            case "R16":
            case "R17":
            case "R22":
            case "R27":
            case "R31":
            case "R38":
                return String.format("1 %s - 30 min.", getString(R.string.hourPrefix));
            case "RE2":
            case "RE4":
            case "RE5":
            case "RE6":
            case "RE7":
            case "RE11":
            case "RE13":
                return String.format("1 %s.", getString(R.string.hourPrefix));
            case "RE3":
            case "R3":
            case "R8":
            case "R9":
            case "R11":
            case "R12":
            case "R15":
            case "R18":
            case "R24":
            case "R32":
            case "R35":
            case "R36":
            case "R37":
            case "R39":
            case "R40":
            case "R41":
                return String.format("2 %s - 1 %s.", getString(R.string.hoursPrefix), getString(R.string.hourPrefix));
            case "90":
            case "91":
                return "4-6 min.";
            case "92":
                return "7-10 min.";
            case "93":
                return "10-12 min.";
            default: return getString(R.string.error);
        }
    }

    public String getAccessibility(String line) {
        switch (line){
            case "M1":
            case "M2":
            case "M3":
            case "M4":
            case "M5":
            case "S5":
            case "S6":
            case "S12":
            case "S13":
            case "S20":
            case "S40":
            case "S50":
            case "S90":
            case "RE80":
            case "MXP1":
            case "MXP2":
            case "RE1":
            case "RE2":
            case "RE4":
            case "RE5":
            case "RE6":
            case "RE7":
            case "RE8":
            case "RE11":
            case "RE13":
            case "R4":
            case "R22":
            case "R27":
            case "R38":
            case "90":
            case "91":
            case "92":
                return getString(R.string.fullyAccessible);
            case "S7":
            case "S8":
            case "S9":
            case "1":
            case "5":
            case "10":
            case "19":
            case "33":
            case "R9":
            case "R11":
            case "R12":
            case "R15":
            case "R18":
            case "R24":
            case "R25":
            case "R32":
            case "R35":
            case "R36":
            case "R37":
            case "R39":
            case "R40":
            case "R41":
                return getString(R.string.nonAccessible);
            default:
                return getString(R.string.partiallyTitle);
        }
    }

    private void loadGTFSData() {
        if (nomeLinea == null) return;
        String url = "https://cdn.lavorami.it/gtfs/" + nomeLinea.toLowerCase() + ".json";

        if (arriviProgressBar != null) arriviProgressBar.setVisibility(View.VISIBLE);

        GTFSHelper.load(url, new GTFSHelper.GTFSCallback() {
            @Override
            public void onSuccess(GTFSHelper.GTFSRoute route) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    routeData = route;
                    if (arriviProgressBar != null) arriviProgressBar.setVisibility(View.GONE);
                    setupStopDropdown();
                });
            }

            @Override
            public void onError() {
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (arriviProgressBar != null) arriviProgressBar.setVisibility(View.GONE);
                    Toast.makeText(LinesDetailActivity.this, getString(R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void setupStopDropdown() {
        if (routeData == null || dropdownFermate == null) return;

        List<String> stopNames = new ArrayList<>();
        final List<String> stopIds = new ArrayList<>();

        List<Map.Entry<String, GTFSHelper.GTFSStop>> sortedStops = new ArrayList<>(routeData.stops.entrySet());
        Collections.sort(sortedStops, (e1, e2) -> e1.getValue().name.compareTo(e2.getValue().name));

        for (Map.Entry<String, GTFSHelper.GTFSStop> entry : sortedStops) {
            stopNames.add(entry.getValue().name);
            stopIds.add(entry.getKey());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, stopNames);
        dropdownFermate.setAdapter(adapter);

        dropdownFermate.setOnItemClickListener((parent, view, position, id) -> {
            ActivityUtils.triggerFeedback(this);
            selectedStopId = stopIds.get(position);
            updateArriviList();
        });

        if (!stopIds.isEmpty() && selectedStopId == null) {
            selectedStopId = stopIds.get(0);
            dropdownFermate.setText(stopNames.get(0), false);
            updateArriviList();
        }
    }

    private void updateArriviList() {
        if (routeData == null || selectedStopId == null) return;

        Map<String, List<GTFSHelper.Departure>> departuresByDir = GTFSHelper.getDepartures(this, selectedStopId, routeData, 3);

        if (departuresByDir == null || departuresByDir.isEmpty()) {
            arriviRecyclerView.setVisibility(View.GONE);
            arriviEmptyState.setVisibility(View.VISIBLE);
        }
        else {
            arriviEmptyState.setVisibility(View.GONE);
            if (arriviRecyclerView != null) {
                arriviRecyclerView.setVisibility(View.VISIBLE);
                arriviRecyclerView.setAdapter(new ArriviAdapter(departuresByDir));
            }
        }

        scheduleArriviRefresh();
    }

    private void scheduleArriviRefresh() {
        handler.removeCallbacks(arriviRunnable);
        arriviRunnable = () -> updateArriviList();
        handler.postDelayed(arriviRunnable, 10000);
    }

    private boolean isChipTransitioning = false;
    private boolean pendingChipUpdate = false;
    private void updateChipGroupSizes(ChipGroup chipGroup) {
        if (chipGroup.getWidth() == 0) {
            chipGroup.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        chipGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        updateChipGroupSizes(chipGroup);
                    }
                }
            );
            return;
        }

        if (isChipTransitioning) {
            pendingChipUpdate = true;
            return;
        }

        int childCount = chipGroup.getChildCount();
        if (childCount == 0) return;

        float density = chipGroup.getContext().getResources().getDisplayMetrics().density;
        int unselectedWidth = (int) (70 * density);
        float chipSpacing = chipGroup.getChipSpacingHorizontal();
        int totalWidth = chipGroup.getWidth() - chipGroup.getPaddingLeft() - chipGroup.getPaddingRight();

        ArrayList<Chip> visibleChips = new ArrayList<>(childCount);
        Chip selectedChip = null;

        for (int i = 0; i < childCount; i++) {
            View child = chipGroup.getChildAt(i);
            if (child instanceof Chip && child.getVisibility() != View.GONE) {
                Chip chip = (Chip) child;
                visibleChips.add(chip);
                if (chip.isChecked()) selectedChip = chip;
            }
        }

        int totalVisibleCount = visibleChips.size();
        if (totalVisibleCount == 0) return;

        int visibleUnselectedCount = totalVisibleCount - (selectedChip != null ? 1 : 0);
        int totalSpacingSpace = (int) ((totalVisibleCount - 1) * chipSpacing);
        int totalUnselectedSpace = visibleUnselectedCount * unselectedWidth;
        int remainingWidth = totalWidth - totalUnselectedSpace - totalSpacingSpace;

        boolean animate = chipGroup.getTag() != null;
        if (animate) {
            isChipTransitioning = true;

            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.setDuration(250);
            changeBounds.addListener(new TransitionListenerAdapter() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    isChipTransitioning = false;

                    if (pendingChipUpdate) {
                        pendingChipUpdate = false;
                        updateChipGroupSizes(chipGroup);
                    }
                }
            });

            TransitionManager.beginDelayedTransition(chipGroup, changeBounds);
        }
        chipGroup.setTag(Boolean.TRUE);

        for (int i = 0; i < totalVisibleCount; i++) {
            Chip chip = visibleChips.get(i);
            ViewGroup.LayoutParams params = chip.getLayoutParams();

            if (chip == selectedChip) {
                if (params.width != remainingWidth) {
                    params.width = remainingWidth;
                    chip.setLayoutParams(params);
                }

                int textResId = getChipTextResId(chip.getId());
                String targetText = chip.getContext().getString(textResId);
                if (!targetText.equals(chip.getText().toString())) chip.setText(targetText);

                float textWidth = chip.getPaint().measureText(targetText);
                float iconSize = chip.getChipIconSize();
                float itemSpacing = 6 * density;
                float totalContentWidth = iconSize + itemSpacing + textWidth;
                int calculatedStartPadding = 0;
                if (remainingWidth > totalContentWidth) calculatedStartPadding = (int) ((remainingWidth - totalContentWidth) / 2);

                chip.setChipBackgroundColor(ColorStateList.valueOf(coloreLinea));
                safelyUpdatePadding(chip, calculatedStartPadding, (int) itemSpacing, 0, 0);
            }
            else {
                if (params.width != unselectedWidth) {
                    params.width = unselectedWidth;
                    chip.setLayoutParams(params);
                }

                if (chip.getText().length() > 0) chip.setText("");

                chip.setChipBackgroundColor(ColorStateList.valueOf(ColorUtils.setAlphaComponent(coloreLinea, 38)));
                float iconSize = chip.getChipIconSize();
                int calculatedIconPadding = (int) ((unselectedWidth - iconSize) / 2);
                safelyUpdatePadding(chip, calculatedIconPadding, 0, 0, 0);
            }
        }
    }

    private void safelyUpdatePadding(Chip chip, int iconStart, int iconEnd, int textStart, int textEnd) {
        if (chip.getIconStartPadding() != iconStart) chip.setIconStartPadding(iconStart);
        if (chip.getIconEndPadding() != iconEnd) chip.setIconEndPadding(iconEnd);
        if (chip.getTextStartPadding() != textStart) chip.setTextStartPadding(textStart);
        if (chip.getTextEndPadding() != textEnd) chip.setTextEndPadding(textEnd);
        if (chip.getChipStartPadding() != 0f) chip.setChipStartPadding(0f);
        if (chip.getChipEndPadding() != 0f) chip.setChipEndPadding(0f);
    }

    private int getChipTextResId(int chipId) {
        if (chipId == R.id.chipMappa) return R.string.mapChip;
        if (chipId == R.id.chipLavoriInCorso) return R.string.lineWorksChip;
        if (chipId == R.id.chipInterscambi) return R.string.interchangesChip;
        if (chipId == R.id.chipArrivi) return R.string.arrivalsChipText;

        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        executor.shutdownNow();
        interchangeHandler.removeCallbacksAndMessages(null);
        interchangeHandler.removeCallbacksAndMessages(null);
        dismissActiveBranchDialog();
    }

    private class ArriviAdapter extends RecyclerView.Adapter<ArriviAdapter.ArrivalsView> {
        private final List<String> directions;
        private final Map<String, List<GTFSHelper.Departure>> data;

        ArriviAdapter(Map<String, List<GTFSHelper.Departure>> data) {
            this.data = data;
            this.directions = new ArrayList<>(data.keySet());
            Collections.sort(directions);
        }

        @NonNull
        @Override
        public ArrivalsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_arrivi_direction, parent, false);
            return new ArrivalsView(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ArrivalsView holder, int position) {
            String dirId = directions.get(position);
            List<GTFSHelper.Departure> deps = data.get(dirId);

            if (deps != null && !deps.isEmpty()) {
                GTFSHelper.Departure first = deps.get(0);
                holder.txtDirectionHeadsign.setText(getString(R.string.directionTitleArrivals) + first.headsign.toUpperCase());

                int colorFirst;
                if (first.minutesFromNow < 10) {
                    if (first.minutesFromNow < 5)
                        colorFirst = (first.minutesFromNow == 0) ? Color.parseColor("#FFEB3B") : Color.parseColor("#F44336");
                    else
                        colorFirst = Color.parseColor("#FF9800");
                }
                else colorFirst = Color.parseColor("#4CAF50");

                if(first.minutesFromNow > 5)
                    holder.iconFirstClock.setImageDrawable(getDrawable(R.drawable.ic_clock_three));
                else {
                    if(first.minutesFromNow < 1) holder.iconFirstClock.setImageDrawable(getDrawable(R.drawable.ic_clock_one));
                    if(first.minutesFromNow < 2) holder.iconFirstClock.setImageDrawable(getDrawable(R.drawable.ic_clock_two));
                    else holder.iconFirstClock.setImageDrawable(getDrawable(R.drawable.ic_clock_three));
                }

                holder.iconFirstClock.setColorFilter(colorFirst);
                holder.txtFirstMins.setTextColor(colorFirst);

                if (first.minutesFromNow == 0) holder.txtFirstMins.setText(getString(R.string.leavingTitle));
                else if (first.minutesFromNow >= 60) {
                    int hours = first.minutesFromNow / 60;
                    int mins = first.minutesFromNow % 60;

                    if (mins == 0) holder.txtFirstMins.setText(hours + " h");
                    else holder.txtFirstMins.setText(hours + " h " + mins + " min");
                }
                else holder.txtFirstMins.setText(first.minutesFromNow + " min");

                holder.txtFirstTime.setText(first.time);

                holder.containerOtherDepartures.removeAllViews();
                if (deps.size() > 1) {
                    holder.dividerArrivi.setVisibility(View.VISIBLE);
                    holder.containerOtherDepartures.setVisibility(View.VISIBLE);

                    for (int i = 1; i < Math.min(deps.size(), 3); i++) {
                        GTFSHelper.Departure dep = deps.get(i);
                        View otherDepView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.item_arrivi_secondary, holder.containerOtherDepartures, false);

                        TextView txtMins = otherDepView.findViewById(R.id.txtSecMins);
                        TextView txtTime = otherDepView.findViewById(R.id.txtSecTime);
                        if (dep.minutesFromNow >= 60) {
                            int hours = dep.minutesFromNow / 60;
                            int mins = dep.minutesFromNow % 60;

                            if (mins == 0) txtMins.setText(hours + " h");
                            else txtMins.setText(hours + " h " + mins + " min");
                        }
                        else txtMins.setText(dep.minutesFromNow + " min");
                        txtTime.setText(dep.time);

                        holder.containerOtherDepartures.addView(otherDepView);
                    }
                }
                else {
                    holder.nextArrivals.setVisibility(View.GONE);
                    holder.dividerArrivi.setVisibility(View.GONE);
                    holder.containerOtherDepartures.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public int getItemCount() {return directions.size();}

        class ArrivalsView extends RecyclerView.ViewHolder {
            TextView txtDirectionHeadsign, txtFirstMins, txtFirstTime, nextArrivals;
            ImageView iconFirstClock;
            View dividerArrivi;
            LinearLayout containerOtherDepartures;

            ArrivalsView(View view) {
                super(view);
                txtDirectionHeadsign = view.findViewById(R.id.txtDirectionHeadsign);
                iconFirstClock = view.findViewById(R.id.iconFirstClock);
                txtFirstMins = view.findViewById(R.id.txtFirstMins);
                txtFirstTime = view.findViewById(R.id.txtFirstTime);
                dividerArrivi = view.findViewById(R.id.dividerArrivi);
                containerOtherDepartures = view.findViewById(R.id.containerOtherDepartures);
                nextArrivals = view.findViewById(R.id.nextArrivals);
            }
        }
    }

    private void syncYourLinesToSupabase(Set<String> yourLinesSet) {
        if (sessionManager != null && sessionManager.isLoggedIn()) {
            Set<String> favoritesSet = DataManager.getStringArray(DataKeys.KEY_FAVORITE_LINES, new java.util.HashSet<>());

            ArrayList<String> favoritesList = new ArrayList<>(favoritesSet);
            ArrayList<String> yourLinesList = new ArrayList<>(yourLinesSet);

            String userEmail = sessionManager.getUserEmail();
            String token = sessionManager.getToken();

            SupabaseDataManager supabaseDataManager = new SupabaseDataManager(this, api, SupabaseANON, token, userEmail);

            supabaseDataManager.saveFavoritesAndLines(favoritesList, yourLinesList, new SupabaseDataManager.DataCallback<Void>() {
                @Override
                public void onSuccess(Void result) {Log.d("SUPABASE_SYNC", "Tue Linee (Cuore) aggiornate nel cloud!");}

                @Override
                public void onError(String error) {Log.e("SUPABASE_SYNC", "Errore salvataggio Cuore nel cloud: " + error);}
            });
        }
    }
}