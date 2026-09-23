package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StopDetailsActivity extends AppCompatActivity{

    private String nomeFermata;
    private String nomeLinea;
    private MapView pendingMapView;
    private boolean mapAlreadyLoaded = false;
    private MapView mapViewRef;
    private int coloreLinea;
    private BottomSheetBehavior<LinearLayout> bottomSheetBehavior;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapboxHelper.init(getMetaData(this, "MAPBOX_KEY"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_details);

        //*LOCK THE ORIENTATION
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        LinearLayout bottomSheetStop = findViewById(R.id.bottomSheetStop);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetStop);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        //*INTENT EXTRAS
        nomeFermata = getIntent().getStringExtra("NOME_FERMATA");
        nomeLinea = getIntent().getStringExtra("NOME_LINEA");

        if (nomeFermata == null) nomeFermata = "";
        if (nomeLinea == null) nomeLinea = "";

        ImageButton btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(v -> finish());

        aggiornaTestView();

        MapView mapView = findViewById(R.id.mapView);
        MapboxHelper.loadMap(mapView, isDarkMode(), mapViewReady -> {
            pendingMapView = mapViewReady;
            checkIfReadyToLoadMap();
        });
    }

    private void aggiornaTestView() {
        //ORARIO HARDCODED IN ATTESA DI GTFS

        TextView detTitolo = findViewById(R.id.detTitolo);
        TextView detSottotitolo = findViewById(R.id.detSottotitolo);
        TextView detBadge = findViewById(R.id.detBadge);

        detTitolo.setText(nomeFermata);
        detSottotitolo.setText(getString(R.string.tramLinesScroll) + " " + nomeLinea);

        detBadge.setText(nomeLinea);

        coloreLinea = ContextCompat.getColor(this, StationDB.getLineColor(this, nomeLinea));
        detBadge.getBackground().setTint(coloreLinea);
        detBadge.getBackground().setTintMode(android.graphics.PorterDuff.Mode.SRC_IN);

        TextView detDirezioni = findViewById(R.id.detDirezioni);
        TextView nextArrivals = findViewById(R.id.nextArrivals);
        detDirezioni.setText("direzione: DIREZIONE");
        nextArrivals.setText("12:45");

        caricaInterscambioFermata();
    }

    private void checkIfReadyToLoadMap() {
        if (pendingMapView != null && !mapAlreadyLoaded) {
            onMapReady(pendingMapView);
            mapAlreadyLoaded = true;
        }
    }

    private void onMapReady(MapView mapView) {
        FrameLayout layoutMaps = findViewById(R.id.googleMapsFrameLayout);
        LinearLayout layoutLoadingMap = findViewById(R.id.loadingMapsFragmentLayout);
        MapboxHelper.removeScale(mapView);
        elaboraFermata(layoutMaps, layoutLoadingMap, mapView);
    }

    private void elaboraFermata(FrameLayout layoutMaps, LinearLayout layoutLoadingMap, MapView mapView) {
        List<MetroStation> tutteLeStazioni = new ArrayList<>();
        for (MetroStation s : StationDB.getAllStations()) {
            if (s.getLine().trim().equalsIgnoreCase(nomeLinea.trim()))
                tutteLeStazioni.add(s);
        }

        coloreLinea = ContextCompat.getColor(this, nomeLinea.equalsIgnoreCase("S12") ? R.color.GRAY : StationDB.getLineColor(this, nomeLinea));
        int coloreDefaultText = ContextCompat.getColor(this, R.color.text_primary);
        String hexColor = String.format("#%06X", (0xFFFFFF & coloreLinea));
        String hexColorText = String.format("#%06X", (0xFFFFFF & coloreDefaultText));

        String branchCorrente = null;
        for (MetroStation s : tutteLeStazioni) {
            if (!s.getName().equalsIgnoreCase("NO_DRAW") && s.getName().equalsIgnoreCase(nomeFermata)) {
                branchCorrente = s.getBranch();
                break;
            }
        }

        List<MetroStation> stazioniBranch = new ArrayList<>();
        if (branchCorrente != null) {
            for (MetroStation s : tutteLeStazioni) {
                if (s.getBranch() != null && s.getBranch().equals(branchCorrente))
                    stazioniBranch.add(s);
            }
        }
        else {
            stazioniBranch = tutteLeStazioni;
        }

        int indiceCorrente = -1;
        for (int i = 0; i < stazioniBranch.size(); i++) {
            if (stazioniBranch.get(i).getName().equalsIgnoreCase(nomeFermata)) {
                indiceCorrente = i;
                break;
            }
        }

        List<MetroStation> stazioniDaMostrare = new ArrayList<>();
        List<MetroStation> stazioniPerPolilinea = new ArrayList<>();

        if (indiceCorrente != -1) {
            int indicePrecedente = trovaIndiceVicinaReale(stazioniBranch, indiceCorrente, -1);
            int indiceSuccessiva = trovaIndiceVicinaReale(stazioniBranch, indiceCorrente, +1);

            int inizio = (indicePrecedente != -1) ? indicePrecedente : indiceCorrente;
            int fine = (indiceSuccessiva != -1) ? indiceSuccessiva : indiceCorrente;

            for (int i = inizio; i <= fine; i++)
                stazioniPerPolilinea.add(stazioniBranch.get(i));

            if (indicePrecedente != -1) stazioniDaMostrare.add(stazioniBranch.get(indicePrecedente));
            stazioniDaMostrare.add(stazioniBranch.get(indiceCorrente));
            if (indiceSuccessiva != -1) stazioniDaMostrare.add(stazioniBranch.get(indiceSuccessiva));
        }
        else {
            stazioniPerPolilinea = tutteLeStazioni;
            for (MetroStation s : tutteLeStazioni)
                if (!s.getName().equalsIgnoreCase("NO_DRAW")) stazioniDaMostrare.add(s);
        }

        disegnaPolilinea(mapView, stazioniPerPolilinea, hexColor);
        disegnaMarkers(mapView, stazioniDaMostrare, hexColor, hexColorText);

        com.mapbox.maps.plugin.gestures.GesturesUtils.getGestures(mapView).addOnMapClickListener(point -> {
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
                                selezionaNuovaFermata(mapView, stationName);
                            }
                        }
                    }
            );
            return true;
        });

        if (!stazioniDaMostrare.isEmpty()) {
            List<Point> puntiDaInquadrare = new ArrayList<>();
            for (MetroStation s : stazioniDaMostrare)
                puntiDaInquadrare.add(Point.fromLngLat(s.getLongitude(), s.getLatitude()));

            MapboxHelper.setCameraToBounds(mapView, puntiDaInquadrare, 150.0, 15.0, 15.0);
        }

        layoutMaps.setVisibility(android.view.View.VISIBLE);
        layoutMaps.setAlpha(0f);
        layoutMaps.animate().alpha(1f).setDuration(300).start();
        layoutLoadingMap.setVisibility(android.view.View.GONE);

        mapViewRef = mapView;

        ImageButton positionButton = findViewById(R.id.positionButton);
        positionButton.setImageTintList(android.content.res.ColorStateList.valueOf(coloreLinea));
        positionButton.setOnClickListener(v -> positionButtonClick());

        if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED)
            MapboxHelper.enableUserLocation(mapViewRef, false);
    }

    private int trovaIndiceVicinaReale(List<MetroStation> stazioni, int indice, int direzione) {
        int i = indice + direzione;
        while (i >= 0 && i < stazioni.size()) {
            if (!stazioni.get(i).getName().equalsIgnoreCase("NO_DRAW")) return i;
            i += direzione;
        }
        return -1;
    }

    private void disegnaPolilinea(MapView mapView, List<MetroStation> stazioni, String hexColor) {
        if (stazioni.size() < 2) return;

        List<Point> points = new ArrayList<>();
        for (MetroStation s : stazioni)
            points.add(Point.fromLngLat(s.getLongitude(), s.getLatitude()));

        MapboxHelper.addLineLayer(mapView, "line-source-main", "line-layer-main", points, hexColor, false);
    }

    private void disegnaMarkers(MapView mapView, List<MetroStation> stazioni, String hexColor, String hexColorText) {
        List<com.mapbox.geojson.Feature> markerFeatures = new ArrayList<>();

        for (MetroStation station : stazioni) {
            if (station.getName().equalsIgnoreCase("NO_DRAW")) continue;
            markerFeatures.add(MapboxHelper.makeStationFeature(station.getLatitude(), station.getLongitude(), station.getName(), nomeLinea, java.util.Collections.emptyList()));
        }

        MapboxHelper.addCircleLayer(mapView, markerFeatures, hexColor, hexColorText);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                if (mapViewRef != null) {
                    MapboxHelper.enableUserLocation(mapViewRef, false);
                    MapboxHelper.zoomToUserLocation(mapViewRef);
                }
            }
        }
    }

    private void selezionaNuovaFermata(MapView mapView, String nuovaFermata) {
        if (nuovaFermata == null || nuovaFermata.equalsIgnoreCase(nomeFermata)) return;

        ActivityUtils.triggerFeedback(this);
        nomeFermata = nuovaFermata;

        aggiornaTestView();

        FrameLayout layoutMaps = findViewById(R.id.googleMapsFrameLayout);
        LinearLayout layoutLoadingMap = findViewById(R.id.loadingMapsFragmentLayout);

        MapboxHelper.clearAllLineLayers(mapView);
        MapboxHelper.clearMarkers(mapView);

        elaboraFermata(layoutMaps, layoutLoadingMap, mapView);
    }
    private Map<String, String> dizionarioAbbreviazioni = Map.of(
            "p.le", "piazzale",
            "p.za", "piazza",
            "p.ta", "porta",
            "v.le", "viale",
            "c.so", "corso",
            "l.go", "largo",
            "m.te", "monte",
            "s.",   "san",
            "c.",   "console",
            "p.",   "principe"
    );

    private String espandiAbbreviazioni(String testo) {
        if (testo == null) return null;

        String risultato = testo;
        for (Map.Entry<String, String> entry : dizionarioAbbreviazioni.entrySet()) {
            String abbreviazione = entry.getKey();
            String espansa = entry.getValue();

            String abbreviazioneEscaped = java.util.regex.Pattern.quote(abbreviazione);
            String regex = "(?i)(?<=^|\\s)" + abbreviazioneEscaped + "(?=\\s|$)";
            risultato = risultato.replaceAll(regex, java.util.regex.Matcher.quoteReplacement(espansa));
        }
        return risultato;
    }

    private InterchangeInfo trovaInterscambioPerFermata(List<InterchangeInfo> interscambi, String nomeFermataOriginale) {
        if (nomeFermataOriginale == null) return null;

        String nomeFermataEspansa = espandiAbbreviazioni(nomeFermataOriginale);

        for (InterchangeInfo info : interscambi) {
            if (info.getKey().equalsIgnoreCase(nomeFermataOriginale)
                    || info.getKey().equalsIgnoreCase(nomeFermataEspansa)) {
                return info;
            }
        }

        for (InterchangeInfo info : interscambi) {
            String chiave = info.getKey();
            if (chiave.toLowerCase().contains(nomeFermataEspansa.toLowerCase())
                    || nomeFermataEspansa.equalsIgnoreCase("Lodi TIBB") && chiave.equalsIgnoreCase("Milano Scalo Romana")) {
                return info;
            }
        }

        String[] paroleNomeFermata = nomeFermataEspansa.split("\\s");
        for (InterchangeInfo info : interscambi) {
            String chiave = info.getKey();
            for (String parola : paroleNomeFermata) {
                if (parola.length() > 2 && chiave.toLowerCase().contains(parola.toLowerCase())) {
                    return info;
                }
            }
        }

        return null;
    }

    private void caricaInterscambioFermata() {
        LinearLayout container = findViewById(R.id.containerInterscambioFermata);
        if (container == null) return;

        container.removeAllViews();
        container.setVisibility(View.GONE);

        List<InterchangeInfo> interscambi = InterchangesDB.getTramInterchanges(this);
        InterchangeInfo trovato = trovaInterscambioPerFermata(interscambi, nomeFermata);

        if (trovato == null || trovato.getLines() == null) return;

        View card = LayoutInflater.from(this).inflate(R.layout.item_interchange, container, false);

        card.setPadding(0, card.getPaddingTop(), 0, card.getPaddingBottom());

        ImageView icona = card.findViewById(R.id.iconTransport);
        if (icona != null) icona.setImageResource(trovato.getCardImageID());

        TextView titolo = card.findViewById(R.id.txtTitle);
        if (titolo != null) titolo.setText(trovato.getKey().toUpperCase());

        ChipGroup chipGroup = card.findViewById(R.id.chipGroupLinee);
        if (chipGroup != null) {
            chipGroup.removeAllViews();
            for (String lineName : trovato.getLines()) {
                if (!lineName.equalsIgnoreCase(nomeLinea))
                    chipGroup.addView(createChipInterscambio(lineName));
            }
        }

        TextView emptyInterchanges = card.findViewById(R.id.noInterchangesTxt);
        if (chipGroup != null && chipGroup.getChildCount() == 0 && emptyInterchanges != null) {
            emptyInterchanges.setVisibility(View.VISIBLE);
            if (icona != null) icona.setImageResource(R.drawable.ic_no_interchanges);
        }

        View dot = card.findViewById(R.id.dotInterchange);
        if (dot != null) {
            Drawable background = dot.getBackground();
            if (background instanceof GradientDrawable) {
                GradientDrawable gd = (GradientDrawable) background.mutate();
                float density = getResources().getDisplayMetrics().density;
                gd.setStroke((int) (3 * density), coloreLinea);
            }
        }

        View lineTop = card.findViewById(R.id.lineTop);
        View lineBottom = card.findViewById(R.id.lineBottom);
        if (lineTop != null) {
            lineTop.setVisibility(View.VISIBLE);
            lineTop.setBackgroundColor(coloreLinea);
        }
        if (lineBottom != null) {
            lineBottom.setVisibility(View.VISIBLE);
            lineBottom.setBackgroundColor(coloreLinea);
        }

        container.addView(card);
        container.setVisibility(View.VISIBLE);
    }

    private Chip createChipInterscambio(String name) {
        Chip chip = new Chip(this, null, com.google.android.material.R.attr.chipStyle);
        chip.setEnsureMinTouchTargetSize(false);
        chip.setText(name);

        chip.setShapeAppearanceModel(chip.getShapeAppearanceModel().toBuilder().setAllCornerSizes(10f).build());

        float density = getResources().getDisplayMetrics().density;
        int heightPx = (int) (26 * density);

        if (name.contains(getString(R.string.filobusKey)) || name.matches("9[0-3]")) {
            chip.setChipIcon(ContextCompat.getDrawable(this, R.drawable.ic_bolt));
            chip.setChipIconTint(android.content.res.ColorStateList.valueOf(android.graphics.Color.WHITE));
            chip.setIconStartPadding(10);
        }
        else if (name.contains("N")) {
            chip.setChipIcon(ContextCompat.getDrawable(this, R.drawable.ic_dark));
            chip.setChipIconTint(android.content.res.ColorStateList.valueOf(android.graphics.Color.WHITE));
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
        chip.setTypeface(androidx.core.content.res.ResourcesCompat.getFont(this, R.font.inter), android.graphics.Typeface.BOLD);

        int colore = ContextCompat.getColor(this, StationDB.getLineColor(this, name));
        chip.setChipBackgroundColor(android.content.res.ColorStateList.valueOf(colore));
        chip.setTextColor((name.equalsIgnoreCase(getString(R.string.monumentKey))) ? ContextCompat.getColor(this, R.color.Black) : ContextCompat.getColor(this, R.color.White));
        chip.setCloseIconVisible(false);
        chip.setClickable(false);
        chip.setCheckable(false);
        chip.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        chip.setGravity(android.view.Gravity.CENTER);
        return chip;
    }

    private boolean isDarkMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}