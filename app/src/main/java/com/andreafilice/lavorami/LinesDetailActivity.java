package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.WorkAdapter.translateStrings;

import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LinesDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private String nomeLinea;
    private String tipoDiLinea;
    private View lavoriNested;
    private View interscambiNested;
    private View lavoriWrapper;
    private View interscambiWrapper;
    private StrikeDescriptor strikeCDNResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines_detail);

        //*LOCK THE ORIENTATION
        /// In this section of the code, we will block the orientation to PORTRAIT because in LANDSCAPE LavoraMi is not supported.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Chip chipMappa = findViewById(R.id.chipMappa);
        Chip chipLavori = findViewById(R.id.chipLavoriInCorso);
        Chip chipInterscambi = findViewById(R.id.chipInterscambi);

        CardView cardMappa = findViewById(R.id.mapCard);
        LinearLayout containerLavori = findViewById(R.id.containerLavori);
        LinearLayout containerInterscambi = findViewById(R.id.containerInterscambi);
        ArrayList<String> tramLinesWithMap = new ArrayList<>(Arrays.asList("1", "3", "5", "7", "24"));

        lavoriNested = findViewById(R.id.lavoriNested);
        interscambiNested = findViewById(R.id.interscambiNested);
        lavoriWrapper = findViewById(R.id.lavoriSezioneWrapper);
        interscambiWrapper = findViewById(R.id.interscambiWrapper);

        chipMappa.setChecked(true);

        /// Take the EXTRA Arguments from the Intent.
        nomeLinea = getIntent().getStringExtra("NOME_LINEA");
        tipoDiLinea = getIntent().getStringExtra("TIPO_DI_LINEA");

        /// Check if the line is a TRAM, else do not display the Map View.
        if (nomeLinea == null) nomeLinea = "M1";
        if ((tipoDiLinea.contains("Tram") && !(tramLinesWithMap.contains(nomeLinea))) || nomeLinea.contains("z")){
            chipMappa.setVisibility(View.GONE);
            chipInterscambi.setVisibility(View.GONE);
            cardMappa.setVisibility(View.GONE);
            containerInterscambi.setVisibility(View.GONE);
            containerLavori.setVisibility(View.VISIBLE);
            caricaEventiFiltrati();

            if(!tipoDiLinea.contains("Tram"))
                caricaFermateInterscambio();

            chipMappa.setChecked(false);
            chipLavori.setChecked(true);
            chipInterscambi.setChecked(false);
        }

        chipMappa.setTypeface(Typeface.create("@font/inter_medium",Typeface.BOLD));
        chipLavori.setTypeface(Typeface.create("@font/inter_medium",Typeface.BOLD));
        chipInterscambi.setTypeface(Typeface.create("@font/inter_medium",Typeface.BOLD));

        aggiornaUI();

        findViewById(android.R.id.content).post(() -> {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null)
                mapFragment.getMapAsync(this);
        });

        chipMappa.setChipStrokeWidth(3f);
        chipMappa.setChipStrokeColor(ColorStateList.valueOf(Color.parseColor("#CCCCCC")));
        chipLavori.setChipStrokeWidth(3f);
        chipLavori.setChipStrokeColor(ColorStateList.valueOf(Color.parseColor("#CCCCCC")));
        chipInterscambi.setChipStrokeWidth(3f);
        chipInterscambi.setChipStrokeColor(ColorStateList.valueOf(Color.parseColor("#CCCCCC")));

        chipMappa.setOnClickListener(v -> {
            cardMappa.setVisibility(View.VISIBLE);
            containerLavori.setVisibility(View.GONE);
            containerInterscambi.setVisibility(View.GONE);
            lavoriWrapper.setVisibility(View.GONE);
            lavoriNested.setVisibility(View.GONE);
            interscambiWrapper.setVisibility(View.GONE);
            interscambiNested.setVisibility(View.GONE);
            findViewById(R.id.lavoriSezioneWrapper).setVisibility(View.GONE);
            findViewById(R.id.emptyView).setVisibility(View.GONE);

            if(!chipLavori.isChecked() && !chipMappa.isChecked() && !chipInterscambi.isChecked()){
                chipMappa.setChecked(true);
                chipLavori.setChecked(false);
                chipInterscambi.setChecked(false);
            }
        });

        chipLavori.setOnClickListener(v -> {
            cardMappa.setVisibility(View.GONE);
            containerLavori.setVisibility(View.VISIBLE);
            containerInterscambi.setVisibility(View.GONE);
            interscambiWrapper.setVisibility(View.GONE);
            interscambiNested.setVisibility(View.GONE);
            caricaEventiFiltrati();

            if(!chipLavori.isChecked() && !chipMappa.isChecked() && !chipInterscambi.isChecked() && !haveMapAvailable()){
                chipMappa.setChecked(true);
                chipLavori.setChecked(false);
                cardMappa.setVisibility(View.VISIBLE);
                containerLavori.setVisibility(View.GONE);
                containerInterscambi.setVisibility(View.GONE);
                findViewById(R.id.emptyView).setVisibility(View.GONE);
            }
            else if(!chipLavori.isChecked() && !chipMappa.isChecked() && !chipInterscambi.isChecked() && haveMapAvailable()){
                chipMappa.setChecked(false);
                chipLavori.setChecked(true);
                cardMappa.setVisibility(View.GONE);
                containerLavori.setVisibility(View.VISIBLE);
                containerInterscambi.setVisibility(View.GONE);
                findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
            }
        });

        chipInterscambi.setOnClickListener(v -> {
            cardMappa.setVisibility(View.GONE);
            containerLavori.setVisibility(View.GONE);
            containerInterscambi.setVisibility(View.VISIBLE);
            lavoriWrapper.setVisibility(View.GONE);
            lavoriNested.setVisibility(View.GONE);
            findViewById(R.id.emptyView).setVisibility(View.GONE);
            caricaInterscambiLinee();

            if(!chipLavori.isChecked() && !chipMappa.isChecked() && !chipInterscambi.isChecked() && haveMapAvailable()){
                chipMappa.setChecked(false);
                chipLavori.setChecked(true);
                cardMappa.setVisibility(View.GONE);
                containerLavori.setVisibility(View.VISIBLE);
                containerInterscambi.setVisibility(View.GONE);
                findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
            }
            else if (!chipLavori.isChecked() && !chipMappa.isChecked() && !chipInterscambi.isChecked() && !haveMapAvailable()){
                chipMappa.setChecked(true);
                chipLavori.setChecked(false);
                cardMappa.setVisibility(View.VISIBLE);
                containerLavori.setVisibility(View.GONE);
                containerInterscambi.setVisibility(View.GONE);
                findViewById(R.id.emptyView).setVisibility(View.GONE);
            }
        });

        ImageButton btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(v -> finish());
        aggiornaInfoSuperiori();
        fetchDeviations();

        //*S12 LIMITATION
        /// In this section of the code, we compose the phrase for S12 line limitation.
        TextView attestazioneLinea = findViewById(R.id.attestazioneLinea);

        attestazioneLinea.setText(ActivityUtils.getLocalizedString(this, R.string.lineaAttesta) + "MILANO BOVISA.");
        attestazioneLinea.setVisibility((nomeLinea.equals("S12")) ? View.VISIBLE : View.GONE);

        //*CHIP BACKGROUND COLOR
        /// In this section of the code we setup the Chip Background color when selected and when is not selected.
        int coloreLinea = (nomeLinea.equalsIgnoreCase("S12")) ? ContextCompat.getColor(this, R.color.text_primary) : ContextCompat.getColor(this, StationDB.getLineColor(nomeLinea));
        int coloreDefault = ContextCompat.getColor(this, R.color.background_app);
        ColorStateList chipColor = new ColorStateList(
            new int[][]{
                new int[]{ android.R.attr.state_checked },
                new int[]{ -android.R.attr.state_checked }
            },
            new int[]{
                coloreLinea,
                coloreDefault
            }
        );
        chipMappa.setChipBackgroundColor(chipColor);
        chipLavori.setChipBackgroundColor(chipColor);
        chipInterscambi.setChipBackgroundColor(chipColor);

        //*CHIP TEXT COLOR
        /// In this section of the code we setup the Chip Text color when selected and when is not selected.
        int bianco = (nomeLinea.equalsIgnoreCase("S12")) ? ContextCompat.getColor(this, R.color.BlackS12) : ContextCompat.getColor(this, R.color.White);
        int testoDefault = ContextCompat.getColor(this, R.color.text_primary);
        ColorStateList chipTextColor = new ColorStateList(
            new int[][]{
                new int[]{ android.R.attr.state_checked },
                new int[]{ -android.R.attr.state_checked }
            },
            new int[]{
                bianco,
                testoDefault
            }
        );
        chipMappa.setTextColor(chipTextColor);
        chipLavori.setTextColor(chipTextColor);
        chipInterscambi.setTextColor(chipTextColor);

        //*ACCESSIBILITY RATE
        /// In this section of the code, we get the current accessibility rate of the line in question.
        String rate = getAccessibility(nomeLinea);
        TextView accessibilityText = findViewById(R.id.accessibilityText);
        ImageView iconAccessibility = findViewById(R.id.iconAccessibility);
        ImageView infoIconMetro = findViewById(R.id.infoIconMetro);
        LinearLayout lineAccessibilityLayout = findViewById(R.id.lineAccessibilityLayout);

        accessibilityText.setText(rate);

        if(rate.equalsIgnoreCase(ActivityUtils.getLocalizedString(this, R.string.fullyAccessible))) {
            iconAccessibility.setImageResource(R.drawable.ic_checkmark);
            iconAccessibility.setImageTintList(ContextCompat.getColorStateList(this, R.color.M2));
        }
        else if(rate.equalsIgnoreCase(ActivityUtils.getLocalizedString(this, R.string.partiallyTitle))) {
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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void aggiornaUI() {
        TextView lineLimitation = findViewById(R.id.lineLimitation);
        lineLimitation.setVisibility((nomeLinea.equals("S2") || nomeLinea.equals("S12") || nomeLinea.equals("S19")) ? View.VISIBLE : View.GONE);
        TextView detBadge = findViewById(R.id.detBadge);
        TextView detTitolo = findViewById(R.id.detTitolo);

        if(nomeLinea.startsWith("M"))
            detTitolo.setText("Metro " + nomeLinea);
        if(nomeLinea.startsWith("S"))
            detTitolo.setText("Suburbano " + nomeLinea);
        if(nomeLinea.matches("^[1-9][0-9]?$"))
            detTitolo.setText("Tram "+nomeLinea);
        if(nomeLinea.equals("S10") || nomeLinea.equals("S30") || nomeLinea.equals("S40") || nomeLinea.equals("S50") || nomeLinea.equals("RE80"))
            detTitolo.setText("TILO "+ nomeLinea);
        if(nomeLinea.startsWith("MXP"))
            detTitolo.setText("Malpensa Express");
        if(nomeLinea.startsWith("z6"))
            detTitolo.setText("Movibus " + nomeLinea);
        if(nomeLinea.startsWith("z5"))
            detTitolo.setText("STAV " + nomeLinea);
        if(nomeLinea.startsWith("z4") || nomeLinea.startsWith("z2"))
            detTitolo.setText("Autoguidovie " + nomeLinea);

        detBadge.setText(nomeLinea);
        int colorResId = StationDB.getLineColor(nomeLinea);
        int coloreEffettivo = ContextCompat.getColor(this, colorResId);

        detBadge.getBackground().setTint(coloreEffettivo);
        detBadge.getBackground().setTintMode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        FrameLayout layoutMaps = findViewById(R.id.googleMapsFrameLayout);
        LinearLayout layoutLoadingMap = findViewById(R.id.loadingMapsFragmentLayout);
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, (isDarkMode()) ? R.raw.map_style : R.raw.map_style_light));

        for (MetroStation stazione : StationDB.getAllStations()) {
            if (stazione.getLine().trim().equalsIgnoreCase(nomeLinea.trim())) {
                int colorResId = StationDB.getLineColor(stazione.getLine());
                int coloreEffettivo = ContextCompat.getColor(this, colorResId);

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(stazione.getLatitude(), stazione.getLongitude()))
                        .anchor(0.5f, 0.5f)
                        .icon(creaPuntino(coloreEffettivo)));
            }
        }

        int coloreLinea = (nomeLinea.equalsIgnoreCase("S12")) ? R.color.GRAY : ContextCompat.getColor(this, StationDB.getLineColor(nomeLinea));
        List<MetroStation> tutteLeStazioni = new ArrayList<>();

        for (MetroStation s : StationDB.getAllStations()) {
            if (s.getLine().trim().equalsIgnoreCase(nomeLinea.trim()))
                tutteLeStazioni.add(s);
        }

        if (!tutteLeStazioni.isEmpty()) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (MetroStation s : tutteLeStazioni) {
                builder.include(new LatLng(s.getLatitude(), s.getLongitude()));
            }
            LatLngBounds bounds = builder.build();

            int padding = (tipoDiLinea.contains("Tram") ? 100 : 120);
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding),
                    new GoogleMap.CancelableCallback() {
                        @Override
                        public void onFinish() {
                            if (tipoDiLinea.contains("Tram")) {
                                double latMedia = 0, lngMedia = 0;
                                for (MetroStation s : tutteLeStazioni) {
                                    latMedia += s.getLatitude();
                                    lngMedia += s.getLongitude();
                                }
                                latMedia /= tutteLeStazioni.size();
                                lngMedia /= tutteLeStazioni.size();

                                float currentZoom = mMap.getCameraPosition().zoom;
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(latMedia, lngMedia), currentZoom + 1.5f));
                            }
                        }
                        @Override
                        public void onCancel() {}
                    });
        }
        else {
            LatLng milano = new LatLng(45.4642, 9.1900);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(milano, 11f));
        }

        if (nomeLinea.equalsIgnoreCase("M1"))
            disegnaM1(tutteLeStazioni, coloreLinea);
        else if (nomeLinea.equalsIgnoreCase("M2"))
            disegnaM2(tutteLeStazioni, coloreLinea);
        else
            disegnaPolilinea(tutteLeStazioni, coloreLinea);

        layoutMaps.setVisibility(View.VISIBLE);
        layoutMaps.setAlpha(0f);

        mMap.setOnMapLoadedCallback(() -> {
            layoutMaps.animate().alpha(1f).setDuration(300).start();
            layoutLoadingMap.setVisibility(View.GONE);
        });
    }

    private void disegnaPolilinea(List<MetroStation> stazioni, int colore) {
        if (stazioni.size() < 2) return;

        List<PatternItem> pattern = Arrays.asList(new Dash(20f), new Gap(20f));

        List<MetroStation> troncoPrincipale = new ArrayList<>();
        List<MetroStation> troncoNew = new ArrayList<>();
        boolean inNewSection = false;

        for (MetroStation s : stazioni) {
            if (s.getBranch().contains("New")) {
                if (!inNewSection && !troncoPrincipale.isEmpty())
                    troncoNew.add(troncoPrincipale.get(troncoPrincipale.size() - 1));
                inNewSection = true;
                troncoNew.add(s);
            } else {
                troncoPrincipale.add(s);
            }
        }

        if (!troncoPrincipale.isEmpty()) {
            PolylineOptions opts = new PolylineOptions()
                    .width(10).color(colore).geodesic(true).zIndex(1000f);
            for (MetroStation s : troncoPrincipale)
                opts.add(new LatLng(s.getLatitude(), s.getLongitude()));
            mMap.addPolyline(opts);
        }

        if (!troncoNew.isEmpty()) {
            PolylineOptions opts = new PolylineOptions()
                    .width(10).color(colore).geodesic(true).zIndex(1000f).pattern(pattern);
            for (MetroStation s : troncoNew)
                opts.add(new LatLng(s.getLatitude(), s.getLongitude()));
            mMap.addPolyline(opts);
        }
    }

    private void disegnaM1(List<MetroStation> stazioni, int colore) {
        List<MetroStation> troncoPrincipale = new ArrayList<>();
        List<MetroStation> ramoBisceglie = new ArrayList<>();
        List<MetroStation> ramoLavori = new ArrayList<>();

        List<String> nomiRamoBisceglie = Arrays.asList(
            "Wagner", "De Angeli", "Gambara", "Bande Nere",
            "Primaticcio", "Inganni", "Bisceglie"
        );

        MetroStation snodoPagano = null;
        MetroStation snodoLavoriBisceglie = null;

        for (MetroStation s : stazioni) {
            String nome = s.getName();
            if (nome.contains("Pagano"))
                snodoPagano = s;
            else if (nome.contains("Bisceglie"))
                snodoLavoriBisceglie = s;

            boolean isRamo = false;
            for (String nomeRamo : nomiRamoBisceglie) {
                if (nome.contains(nomeRamo)) {
                    isRamo = true;
                    break;
                }
            }

            if (isRamo)
                ramoBisceglie.add(s);
            else if(!isRamo && !s.getBranch().equalsIgnoreCase("Bisceglie - New"))
                troncoPrincipale.add(s);
            else if(!isRamo && s.getBranch().equalsIgnoreCase("Bisceglie - New"))
                ramoLavori.add(s);
        }
        disegnaPolilinea(troncoPrincipale, colore);
        if (snodoPagano != null) ramoBisceglie.add(0, snodoPagano);
        disegnaPolilinea(ramoBisceglie, colore);
        if (snodoLavoriBisceglie != null) ramoLavori.add(0, snodoLavoriBisceglie);
        disegnaPolilinea(ramoLavori, colore);
    }

    private void disegnaM2(List<MetroStation> stazioni, int colore) {
        List<MetroStation> troncoCentrale = new ArrayList<>();
        List<MetroStation> ramoCologno = new ArrayList<>();
        List<MetroStation> ramoGessate = new ArrayList<>();
        List<MetroStation> ramoAssago = new ArrayList<>();
        List<MetroStation> ramoAbbiategrasso = new ArrayList<>();

        List<String> nomiCologno = Arrays.asList("Cologno");
        List<String> nomiAssago = Arrays.asList("Assago");
        List<String> nomiAbbiategrasso = Arrays.asList("Abbiategrasso");
        List<String> nomiGessate = Arrays.asList("Vimodrone", "Cascina Burrona", "Cernusco", "Villa Fiorita", "Cassina", "Bussero", "Villa Pompea", "Gorgonzola", "Antonietta", "Gessate");

        MetroStation snodoGobba = null;
        MetroStation snodoFamagosta = null;

        for (MetroStation s : stazioni) {
            String nome = s.getName();
            if (nome.contains("Cascina Gobba")) snodoGobba = s;
            if (nome.contains("Famagosta")) snodoFamagosta = s;
        }

        for (MetroStation s : stazioni) {
            String nome = s.getName();
            boolean assegnata = false;

            for (String n : nomiCologno) {
                if (nome.contains(n)) {
                    ramoCologno.add(s);
                    assegnata = true;
                    break;
                }
            }
            if(assegnata) continue;

            for (String n : nomiAssago) {
                if (nome.contains(n)) {
                    ramoAssago.add(s);
                    assegnata = true;
                    break;
                }
            }
            if(assegnata) continue;

            for (String n : nomiAbbiategrasso) {
                if (nome.contains(n)) {
                    ramoAbbiategrasso.add(s);
                    assegnata = true;
                    break;
                }
            }
            if(assegnata) continue;

            for (String n : nomiGessate) {
                if (nome.contains(n)) {
                    ramoGessate.add(s);
                    assegnata = true;
                    break;
                }
            }
            if(assegnata) continue;

            troncoCentrale.add(s);
        }

        disegnaPolilinea(troncoCentrale, colore);

        if (snodoGobba != null) {
            ramoCologno.add(0, snodoGobba);
            ramoGessate.add(0, snodoGobba);
        }

        disegnaPolilinea(ramoCologno, colore);
        disegnaPolilinea(ramoGessate, colore);

        if (snodoFamagosta != null) {
            ramoAssago.add(0, snodoFamagosta);
            ramoAbbiategrasso.add(0, snodoFamagosta);
        }
        disegnaPolilinea(ramoAssago, colore);
        disegnaPolilinea(ramoAbbiategrasso, colore);
    }

    private BitmapDescriptor creaPuntino(int colore) {
        int size = 20;
        Bitmap bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(size/2f, size/2f, size/2f, paint);
        paint.setColor(colore);
        canvas.drawCircle(size/2f, size/2f, (size/2f) - 4, paint);

        return BitmapDescriptorFactory.fromBitmap(bmp);
    }

    private void caricaEventiFiltrati() {
        LinearLayout container = findViewById(R.id.containerLavori);
        View wrapper = findViewById(R.id.lavoriSezioneWrapper);
        TextView emptyView = findViewById(R.id.emptyView);

        if (container == null || wrapper == null) return;

        container.removeAllViews();
        boolean foundAtLeastOne = false;

        String searchTag = nomeLinea.trim().toUpperCase();

        for (EventDescriptor evento : EventData.listaEventiCompleta) {
            if (evento.getLines() == null) continue;

            boolean matchFound = false;
            for (String lineInEvent : evento.getLines()) {
                if (lineInEvent != null) {
                    if (lineInEvent.trim().toUpperCase().equals(searchTag)) {
                        matchFound = true;
                        break;
                    }
                }
            }

            if (matchFound && !evento.isEventTerminated()) {
                foundAtLeastOne = true;

                View card = getLayoutInflater().inflate(R.layout.item_lavoro, container, false);
                String savedLang = DataManager.getStringData(this, DataKeys.KEY_DEFAULT_LANGUAGE, "🇮🇹 Italiano");
                String langCode = savedLang.contains("English") ? "en" : "it";

                ImageView icona = card.findViewById(R.id.iconEvent);
                if (icona != null) {
                    icona.setImageResource(evento.getCardImageID());
                    icona.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                }

                TextView titolo = card.findViewById(R.id.txtTitle);
                TextView desc = card.findViewById(R.id.txtDescription);
                if (titolo != null) titolo.setText(evento.getTitle());
                if (desc != null) desc.setText(evento.getDetails());

                TextView txtInizio = card.findViewById(R.id.txtStartDate);
                TextView txtFine = card.findViewById(R.id.txtEndDate);
                TextView companyTxt = card.findViewById(R.id.txtOperator);
                TextView roadsTxt = card.findViewById(R.id.txtRoute);

                //*TRANSLATE BUTTON
                Button btnTranslate = card.findViewById(R.id.btnTranslate);
                btnTranslate.setVisibility((langCode.equalsIgnoreCase("en") || DataManager.getBoolData(this, DataKeys.KEY_SHOW_TRANSLATE_BUTTON, false)) ? View.VISIBLE : View.GONE);

                btnTranslate.setOnClickListener(v -> {
                    //*VARIABLES
                    /// In this section of the code, we initialize some components that we will user later in the code.
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                    View sheetView = LayoutInflater.from(this).inflate(R.layout.item_sheet_translated, null);
                    ShimmerFrameLayout loadingLayout = sheetView.findViewById(R.id.loadingLayout);
                    LinearLayout layoutDefault = sheetView.findViewById(R.id.layoutDefault);
                    LinearLayout layoutTerms = sheetView.findViewById(R.id.layoutPrivacy);
                    Button acceptTerms = sheetView.findViewById(R.id.btnContinue);
                    Button cancelTerms = sheetView.findViewById(R.id.btnCancel);
                    TextView downloadingText = sheetView.findViewById(R.id.textDownloading);
                    boolean isAcceptingTerms = DataManager.getBoolData(this, DataKeys.KEY_DOWNLOAD_POLICIES, false);

                    loadingLayout.startShimmer();
                    bottomSheetDialog.setContentView(sheetView);

                    bottomSheetDialog.show();

                    if(isAcceptingTerms) {
                        layoutTerms.setVisibility(View.GONE);
                        layoutDefault.setVisibility(View.VISIBLE);
                        downloadingText.setVisibility(View.GONE);

                        translateStrings(sheetView, evento, loadingLayout);
                    }
                    else {
                        layoutDefault.setVisibility(View.GONE);
                        layoutTerms.setVisibility(View.VISIBLE);

                        acceptTerms.setOnClickListener(view -> {
                            layoutDefault.setVisibility(View.VISIBLE);
                            layoutTerms.setVisibility(View.GONE);
                            downloadingText.setVisibility(View.VISIBLE);
                            DataManager.saveBoolData(this, DataKeys.KEY_DOWNLOAD_POLICIES, true);

                            translateStrings(sheetView, evento, loadingLayout);
                        });

                        cancelTerms.setOnClickListener(unusued -> {bottomSheetDialog.cancel();});
                    }
                });

                int color = ContextCompat.getColor(this, R.color.text_primary);
                ImageViewCompat.setImageTintList(card.findViewById(R.id.iconEvent), ColorStateList.valueOf(color));

                if (txtInizio != null)
                    txtInizio.setText(EventDescriptor.formattaData(evento.getStartDate()));
                if (txtFine != null)
                    txtFine.setText(EventDescriptor.formattaData(evento.getEndDate()));
                if(companyTxt != null)
                    companyTxt.setText(evento.getCompany());
                if(roadsTxt != null)
                    roadsTxt.setText(evento.getRoads());

                ProgressBar pb = card.findViewById(R.id.progressBarDate);
                if (pb != null) {
                    int perc = evento.calcolaPercentuale(evento.getStartDate(), evento.getEndDate());
                    pb.setProgress(perc);
                    pb.setProgressTintList(ColorStateList.valueOf(perc >= 100 ?
                            Color.parseColor("#4CAF50") : Color.parseColor("#FF5252")));
                }

                ChipGroup chipGroup = card.findViewById(R.id.chipGroupLinee);

                if (chipGroup != null && evento.getLines() != null) {
                    chipGroup.removeAllViews();

                    for (String lineName : evento.getLines()) {
                        String nomePulito = lineName.trim();
                        Chip chip = new Chip(this);
                        chip.setText(nomePulito);

                        ShapeAppearanceModel cornerRadius = chip.getShapeAppearanceModel()
                                .toBuilder()
                                .setAllCornerSizes(10f)
                                .build();
                        chip.setShapeAppearanceModel(cornerRadius);

                        float density = getResources().getDisplayMetrics().density;
                        int heightPx = (int) (26 * density);
                        chip.setEnsureMinTouchTargetSize(false);
                        chip.setChipMinHeight((float) heightPx);
                        chip.setMinHeight(heightPx);

                        chip.setChipStartPadding(0f);
                        chip.setChipEndPadding(0f);
                        chip.setTextStartPadding(15f);
                        chip.setTextEndPadding(15f);
                        chip.setChipStrokeWidth(0f);

                        chip.setTextSize(13f);
                        Typeface interMedium = androidx.core.content.res.ResourcesCompat.getFont(this, R.font.inter_medium);
                        chip.setTypeface(Typeface.create(interMedium, Typeface.BOLD));

                        int coloreLinea = WorkAdapter.getColorForLinea(nomePulito);
                        int coloreTestoEffettivo = ContextCompat.getColor(this, R.color.White);
                        int coloreEffettivo = ContextCompat.getColor(this, coloreLinea);
                        chip.setChipBackgroundColor(ColorStateList.valueOf(coloreEffettivo));
                        chip.setTextColor(coloreTestoEffettivo);

                        chip.setCloseIconVisible(false);
                        chip.setClickable(false);
                        chip.setCheckable(false);

                        chipGroup.addView(chip);
                    }
                }
                container.addView(card);
            }
        }

        wrapper.setVisibility((foundAtLeastOne) ? View.VISIBLE : View.GONE);
        lavoriNested.setVisibility((foundAtLeastOne) ? View.VISIBLE : View.GONE);
        emptyView.setVisibility((foundAtLeastOne) ? View.GONE : View.VISIBLE);
        emptyView.setText((EventData.networkError) ? ContextCompat.getString(this, R.string.noInternetConnectionError) : ContextCompat.getString(this, R.string.noWorksOnThisLine));
    }

    private void caricaInterscambiLinee() {
        LinearLayout container = findViewById(R.id.containerInterscambi);
        View wrapper = findViewById(R.id.interscambiWrapper);
        TextView emptyView = findViewById(R.id.emptyView);

        if (container == null || wrapper == null) return;

        container.removeAllViews();
        boolean foundAtLeastOne = false;

        String searchTag = nomeLinea.trim().toUpperCase();

        List<InterchangeInfo> interchanges = new ArrayList<>();

        if(tipoDiLinea.contains("Tram"))
            interchanges = StationDB.getInterchangesTrams();
        else
            interchanges =StationDB.getInterchanges();

        for (InterchangeInfo evento : interchanges) {
            if (evento.getLines() == null) continue;

            boolean matchFound = false;
            for (String lineInEvent : evento.getLines()) {
                if (lineInEvent != null) {
                    if (lineInEvent.trim().toUpperCase().equals(searchTag)) {
                        matchFound = true;
                        break;
                    }
                }
            }

            if (matchFound) {
                foundAtLeastOne = true;

                View card = getLayoutInflater().inflate(R.layout.item_interchange, container, false);

                ImageView icona = card.findViewById(R.id.iconTransport);
                if (icona != null) {
                    icona.setImageResource(evento.getCardImageID());
                    icona.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                }

                TextView titolo = card.findViewById(R.id.txtTitle);
                TextView desc = card.findViewById(R.id.txtLineCode);
                TextView txtStationSubtitle = card.findViewById(R.id.txtStationSubtitle);

                if(txtStationSubtitle != null) txtStationSubtitle.setText(evento.getKey());
                if (titolo != null)
                    titolo.setText((evento.getKey().equals("Lodi TIBB")) ? "Milano Scalo Romana" : evento.getKey());
                if (desc != null) desc.setText(nomeLinea);

                int color = ContextCompat.getColor(this, R.color.text_primary);
                ImageViewCompat.setImageTintList(card.findViewById(R.id.iconTransport), ColorStateList.valueOf(color));

                ChipGroup chipGroup = card.findViewById(R.id.chipGroupLinee);

                if (chipGroup != null && evento.getLines() != null) {
                    chipGroup.removeAllViews();

                    for (String lineName : evento.getLines()) {
                        String nomePulito = lineName.trim();
                        Chip chip = new Chip(this);
                        chip.setText(nomePulito);

                        ShapeAppearanceModel cornerRadius = chip.getShapeAppearanceModel()
                                .toBuilder()
                                .setAllCornerSizes(10f)
                                .build();
                        chip.setShapeAppearanceModel(cornerRadius);

                        float density = getResources().getDisplayMetrics().density;
                        int heightPx = (int) (26 * density);
                        chip.setEnsureMinTouchTargetSize(false);
                        chip.setChipMinHeight((float) heightPx);
                        chip.setMinHeight(heightPx);

                        chip.setChipStartPadding(0f);
                        chip.setChipEndPadding(0f);
                        chip.setTextStartPadding(15f);
                        chip.setTextEndPadding(15f);
                        chip.setChipStrokeWidth(0f);

                        chip.setTextSize(13f);
                        Typeface interMedium = androidx.core.content.res.ResourcesCompat.getFont(this, R.font.inter_medium);
                        chip.setTypeface(Typeface.create(interMedium, Typeface.BOLD));

                        int coloreLinea = WorkAdapter.getColorForLinea(nomePulito);
                        int coloreTestoEffettivo = ContextCompat.getColor(this, R.color.White);
                        int coloreEffettivo = ContextCompat.getColor(this, coloreLinea);
                        chip.setChipBackgroundColor(ColorStateList.valueOf(coloreEffettivo));
                        chip.setTextColor(coloreTestoEffettivo);

                        chip.setCloseIconVisible(false);
                        chip.setClickable(false);
                        chip.setCheckable(false);

                        chipGroup.addView(chip);;
                    }
                }
                container.addView(card);
            }
        }

        wrapper.setVisibility((foundAtLeastOne) ? View.VISIBLE : View.GONE);
        interscambiNested.setVisibility((foundAtLeastOne) ? View.VISIBLE : View.GONE);
        emptyView.setVisibility((foundAtLeastOne) ? View.GONE : View.VISIBLE);
        emptyView.setText(ContextCompat.getString(this, R.string.noInterchanges));
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

                    for (String lineName : info.getLines()) {
                        String nomePulito = lineName.trim();
                        Chip chip = new Chip(this);
                        chip.setText(nomePulito);

                        ShapeAppearanceModel cornerRadius = chip.getShapeAppearanceModel()
                                .toBuilder()
                                .setAllCornerSizes(10f)
                                .build();
                        chip.setShapeAppearanceModel(cornerRadius);

                        float density = getResources().getDisplayMetrics().density;
                        int heightPx = (int) (26 * density);
                        chip.setEnsureMinTouchTargetSize(false);
                        chip.setChipMinHeight((float) heightPx);
                        chip.setMinHeight(heightPx);

                        chip.setChipStartPadding(0f);
                        chip.setChipEndPadding(0f);
                        chip.setTextStartPadding(15f);
                        chip.setTextEndPadding(15f);
                        chip.setChipStrokeWidth(0f);

                        chip.setTextSize(13f);
                        Typeface workSans = androidx.core.content.res.ResourcesCompat.getFont(this, R.font.work_sans_medium);
                        chip.setTypeface(Typeface.create(workSans, Typeface.BOLD));

                        int coloreLinea = WorkAdapter.getColorForLinea(nomePulito);
                        int coloreTestoEffettivo = ContextCompat.getColor(this, R.color.White);
                        int coloreEffettivo = ContextCompat.getColor(this, coloreLinea);
                        chip.setChipBackgroundColor(ColorStateList.valueOf(coloreEffettivo));
                        chip.setTextColor(coloreTestoEffettivo);

                        chip.setCloseIconVisible(false);
                        chip.setClickable(false);
                        chip.setCheckable(false);

                        chipGroupLinee.addView(chip);
                    }
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

    public long getDateMillis(String dateString) {
        if (dateString == null) return 0;
        String serverFormat = "yyyy-MM-dd'T'HH:mm:ss'+01:00'";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(serverFormat, Locale.getDefault());
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

            Date date = sdf.parse(dateString);
            return (date != null) ? date.getTime() : 0;
        } catch (Exception e) {
            Log.e("DATA_ERROR", "Impossibile leggere: " + dateString + " | Errore: " + e.getMessage());
            return 0;
        }
    }

    private void aggiornaInfoSuperiori() {
        TextView tvDirezioni = findViewById(R.id.detDirezioni);
        TextView tvAttesa = findViewById(R.id.detAttesa);
        TextView tvLavori = findViewById(R.id.detLavori);

        tvDirezioni.setText(getCapolinea(nomeLinea));

        Log.d("LINEA", nomeLinea);

        if (nomeLinea.startsWith("M") || nomeLinea.startsWith("S") || nomeLinea.equalsIgnoreCase("RE80"))
            tvAttesa.setText(getFrequenza(nomeLinea));
        else if(nomeLinea.matches("^[1-9][0-9]?$"))
            tvAttesa.setText(ContextCompat.getString(this, R.string.averageWaitingTimeTitle) + "5-20 min.");
        else
            tvAttesa.setText("Frequenza variabile");

        int numeroLavoriProgrammati = 0, numeroLavoriAttuali = 0, numeroLavori = 0;
        for (EventDescriptor e : EventData.listaEventiCompleta) {
            long start = getDateMillis(e.getStartDate());
            long end = getDateMillis(e.getEndDate());
            long oggi = System.currentTimeMillis();
            long startP = getDateMillis(e.getStartDate());

            for (String l : e.getLines()) {
                if (l.equalsIgnoreCase(nomeLinea)) {
                    numeroLavori++;
                    if(start > 0 && end > 0 && oggi >= start && oggi <= end) numeroLavoriAttuali++;
                    else if(startP > 0 && oggi < startP) numeroLavoriProgrammati++;
                    break;
                }
            }
        }

        tvLavori.setText((numeroLavori > 0)
                ? String.format("%s %s, %s %s.", numeroLavoriAttuali, ContextCompat.getString(this, R.string.currentWorksTitle), numeroLavoriProgrammati, ContextCompat.getString(this, R.string.scheduledWorksTitle))
                : ContextCompat.getString(this, R.string.fallbackNoWorks));
    }

    public void fetchDeviations() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cdn.lavorami.it/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIWorks apiworks = retrofit.create(APIWorks.class);

        apiworks.getStrike().enqueue(new Callback<StrikeDescriptor>() {
            @Override
            public void onResponse(Call<StrikeDescriptor> call, Response<StrikeDescriptor> response) {
                if(response.isSuccessful()){
                    strikeCDNResponse = response.body();
                    String[] lineeDeviate = strikeCDNResponse.getLinesDeviation();
                    String[] linkLinee = strikeCDNResponse.getLinesDeviationLinks();
                    int i = 0;

                    ImageView mapDeviationBtn = findViewById(R.id.mapDeviationBtn);

                    for(String linea : lineeDeviate) {
                        if(linea.equals(nomeLinea)){
                            String lineLink = linkLinee[i];

                            findViewById(R.id.deviazioneLinea).setVisibility(View.VISIBLE);
                            mapDeviationBtn.setVisibility((lineLink.equalsIgnoreCase("null")) ? View.GONE : View.VISIBLE);
                            mapDeviationBtn.setOnClickListener(v -> ActivityUtils.openURL(LinesDetailActivity.this, lineLink));
                        }
                        i++;
                    }
                }
            }

            @Override
            public void onFailure(Call<StrikeDescriptor> call, Throwable t) {
                Toast.makeText(LinesDetailActivity.this, ActivityUtils.getLocalizedString(LinesDetailActivity.this, R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isDarkMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }

    private String getCapolinea(String linea) {
        if (linea == null) return "Direzioni non disponibili";

        switch (linea.toUpperCase().trim()) {
            case "M1": return "Sesto 1° Maggio FS - Rho Fiera / Bisceglie";
            case "M2": return "Assago Forum / Abbiategrasso - Gessate / Cologno Nord";
            case "M3": return "San Donato - Comasina";
            case "M4": return "San Cristoforo - Linate Aeroporto";
            case "M5": return "San Siro Stadio - Bignami";

            case "S1": return "Saronno - Lodi";
            case "S2": return "Mariano Comense - Milano Rogoredo";
            case "S3": return "Saronno - Milano Cadorna";
            case "S4": return "Camnago-Lentate - Milano Cadorna";
            case "S5": return "Varese - Treviglio";
            case "S6": return "Novara - Pioltello-Limito / Treviglio";
            case "S7": return "Lecco - Milano Pta Garibaldi";
            case "S8": return "Lecco - Carnate - Milano Pta Garibaldi";
            case "S9": return "Saronno - Albairate-Vermezzo";
            case "S11": return "Rho - Como S. Giovanni";
            case "S12": return "Melegnano - Cormano";
            case "S13": return "Pavia - Milano Bovisa";
            case "S19": return "Albairate Vermezzo - Milano Rogoredo";
            case "S31": return "Brescia - Iseo";

            case "MXP1": return "Gallarate - Malpensa - Milano Centrale";
            case "MXP2": return "Malpensa - Milano Cadorna";

            case "S10": return "Biasca - Como";
            case "S20": return "Castione - Locarno";
            case "S30": return "Cadenazzo - Gallarate";
            case "S40": return "Como - Varese";
            case "S50": return "Biasca - Malpensa Aeroporto T1-T2";
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
            case "27": return "V.Le Ungheria - Duomo M1 M3";
            case "31": return "Bicocca M5 - Cinisello (1° Maggio)";
            case "33": return "P.Le Lagosta - Rimembranze di Lambrate";

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
            case "Z648": return "Arconate - Busto Garolfo - Molino Dorino M1";
            case "Z649": return "Magenta - Arluno - Molino Dorino M1";

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
            default: return "Direzioni non disponibili per " + linea;
        }
    }

    public boolean haveMapAvailable(){return tipoDiLinea.contains("Tram") || this.nomeLinea.contains("z");}

    public String getFrequenza(String lineName){
        switch(lineName) {
            case "M1": return "Sesto FS: 3 min | Rho/Bisceglie: 7-8 min.";
            case "M2": return "Gessate: 12-15 min | Assago: 9-10 min.";
            case "M3": return "4-5 min.";
            case "M4": return "2-3 min.";
            case "M5": return "4 min.";
            case "S10": return "1 h - 45 min.";
            case "S30": return "2 h.";
            case "RE80":
            case "S7":
                return "30 min - 1 h.";
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
            case "MXP1":
            case "MXP2":
                return "30 min.";
            case "S31":
            case "S40":
            case "S50":
                return "1 h.";
            default: return "Errore";
        }
    }

    public String getAccessibility(String line) {
        switch (line){
            case "M1":
            case "M2":
            case "M4":
            case "M5":
            case "S5":
            case "S6":
            case "S12":
            case "S13":
            case "S40":
            case "S50":
            case "RE80":
            case "MXP1":
            case "MXP2":
                return ActivityUtils.getLocalizedString(this, R.string.fullyAccessible);
            case "S7":
            case "S8":
            case "S9":
            case "1":
            case "5":
            case "10":
            case "19":
            case "33":
                return ActivityUtils.getLocalizedString(this, R.string.nonAccessible);
            default:
                return ActivityUtils.getLocalizedString(this, R.string.partiallyTitle);
        }
    }
}