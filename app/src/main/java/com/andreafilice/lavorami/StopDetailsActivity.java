package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;

import java.util.ArrayList;
import java.util.List;

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

            MapboxHelper.setCameraToBounds(mapView, puntiDaInquadrare, 150.0, 14.5, 15.0);
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

    private boolean isDarkMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}