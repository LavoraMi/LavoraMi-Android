package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapboxHelper.init(getMetaData(this, "MAPBOX_KEY"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_details);

        //*LOCK THE ORIENTATION
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //*INTENT EXTRAS
        /// In questa sezione leggiamo il nome della fermata e il nome della linea passati dalla LinesDetailActivity.
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
        /// In questa sezione aggiorniamo i TextView con i dati reali della fermata e della linea.
        /// L'orario e la direzione restano hardcoded, in attesa dei dati elaborati.

        TextView detTitolo = findViewById(R.id.detTitolo);
        TextView detSottotitolo = findViewById(R.id.detSottotitolo);
        TextView detBadge = findViewById(R.id.detBadge);

        detTitolo.setText(nomeFermata);
        detSottotitolo.setText(getString(R.string.tramLinesScroll) + " " + nomeLinea);

        detBadge.setText(nomeLinea);

        coloreLinea = ContextCompat.getColor(this, StationDB.getLineColor(this, nomeLinea));
        detBadge.getBackground().setTint(coloreLinea);
        detBadge.getBackground().setTintMode(android.graphics.PorterDuff.Mode.SRC_IN);

        //*HARDCODED - in attesa dei dati reali di orario/direzione
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

        elaboraFermata(layoutMaps, layoutLoadingMap, mapView);
    }

    private void elaboraFermata(FrameLayout layoutMaps, LinearLayout layoutLoadingMap, MapView mapView) {
        //*RECUPERO STAZIONI DELLA LINEA
        /// In questa sezione prendiamo tutte le stazioni della linea corrente, nello stesso ordine
        /// usato da LinesDetailActivity per disegnare la mappa (StationDB.getAllStations()).

        List<MetroStation> tutteLeStazioni = new ArrayList<>();
        for (MetroStation s : StationDB.getAllStations()) {
            if (s.getLine().trim().equalsIgnoreCase(nomeLinea.trim()))
                tutteLeStazioni.add(s);
        }

        coloreLinea = ContextCompat.getColor(this, nomeLinea.equalsIgnoreCase("S12") ? R.color.GRAY : StationDB.getLineColor(this, nomeLinea));
        int coloreDefaultText = ContextCompat.getColor(this, R.color.text_primary);
        String hexColor = String.format("#%06X", (0xFFFFFF & coloreLinea));
        String hexColorText = String.format("#%06X", (0xFFFFFF & coloreDefaultText));

        //*TROVA INDICE DELLA FERMATA CORRENTE
        int indiceCorrente = -1;
        for (int i = 0; i < tutteLeStazioni.size(); i++) {
            if (tutteLeStazioni.get(i).getName().equalsIgnoreCase(nomeFermata)) {
                indiceCorrente = i;
                break;
            }
        }

        //*COSTRUISCI LISTA RIDOTTA: precedente, corrente, successiva
        List<MetroStation> stazioniDaMostrare = new ArrayList<>();
        if (indiceCorrente != -1) {
            if (indiceCorrente - 1 >= 0) stazioniDaMostrare.add(tutteLeStazioni.get(indiceCorrente - 1));
            stazioniDaMostrare.add(tutteLeStazioni.get(indiceCorrente));
            if (indiceCorrente + 1 < tutteLeStazioni.size()) stazioniDaMostrare.add(tutteLeStazioni.get(indiceCorrente + 1));
        }
        else {
            //*FALLBACK: se non troviamo la fermata (nome non combacia), mostriamo tutta la linea
            stazioniDaMostrare = tutteLeStazioni;
        }

        disegnaMarkers(mapView, stazioniDaMostrare, hexColor, hexColorText);

        //*ZOOM SULLA FERMATA + PRECEDENTE + SUCCESSIVA
        if (!stazioniDaMostrare.isEmpty()) {
            List<Point> puntiDaInquadrare = new ArrayList<>();
            for (MetroStation s : stazioniDaMostrare)
                puntiDaInquadrare.add(Point.fromLngLat(s.getLongitude(), s.getLatitude()));

            MapboxHelper.setCameraToBounds(mapView, puntiDaInquadrare, 150.0);
        }

        layoutMaps.setVisibility(android.view.View.VISIBLE);
        layoutMaps.setAlpha(0f);
        layoutMaps.animate().alpha(1f).setDuration(300).start();
        layoutLoadingMap.setVisibility(android.view.View.GONE);

        mapViewRef = mapView;

        ImageButton positionButton = findViewById(R.id.positionButton);
        positionButton.setImageTintList(android.content.res.ColorStateList.valueOf(coloreLinea));
        positionButton.setOnClickListener(v -> positionButtonClick());

        //*IL PULSANTE CAMBIA DIREZIONE NON SERVE IN QUESTA ACTIVITY
        ImageButton changeRouteButton = findViewById(R.id.changeRouteButton);
        changeRouteButton.setVisibility(android.view.View.GONE);

        if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED)
            MapboxHelper.enableUserLocation(mapViewRef, false);
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

    private boolean isDarkMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}