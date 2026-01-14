package com.andreafilice.lavorami;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class LinesDetailActvity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private String nomeLinea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines_detail);

        // Recuperiamo il nome della linea (es. "M3") passato dall'altra activity
        nomeLinea = getIntent().getStringExtra("NOME_LINEA");
        if (nomeLinea == null) nomeLinea = "M1"; // Default di sicurezza

        // Impostiamo i testi e il colore del badge nella parte superiore
        aggiornaUI();

        // Carichiamo la mappa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Dettaglio " + nomeLinea);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void aggiornaUI() {
        TextView detBadge = findViewById(R.id.detBadge);
        TextView detTitolo = findViewById(R.id.detTitolo);

        detTitolo.setText("Metro " + nomeLinea);
        detBadge.setText(nomeLinea);

        // Recupera l'ID del colore dal DB (es. R.color.m3_yellow)
        int colorResId = StationDB.getLineColor(nomeLinea);

        // TRASFORMA IL RIFERIMENTO IN COLORE REALE
        int coloreEffettivo = ContextCompat.getColor(this, colorResId);

        // Applica al badge
        detBadge.getBackground().setColorFilter(coloreEffettivo, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Coordinate di Milano
        LatLng milano = new LatLng(45.4642, 9.1900);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(milano, 11f));

        // Disegniamo i puntini di tutte le stazioni
        for (MetroStation stazione : StationDB.getAllStations()) {
            // FILTRO: Se la linea della stazione è uguale a quella scelta dall'utente
            if (stazione.getLine().equals(nomeLinea)) {
                int colorResId = StationDB.getLineColor(stazione.getLine());
                int coloreEffettivo = ContextCompat.getColor(this, colorResId);

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(stazione.getLatitude(), stazione.getLongitude()))
                        .anchor(0.5f, 0.5f)
                        .icon(creaPuntino(coloreEffettivo)));
            }
        }
        PolylineOptions polylineOptions = new PolylineOptions()
                .width(10) // Spessore della linea
                .color(ContextCompat.getColor(this, StationDB.getLineColor(nomeLinea)))
                .geodesic(true);

        for (MetroStation stazione : StationDB.getAllStations()) {
            if (stazione.getLine().equals(nomeLinea)) {
                polylineOptions.add(new LatLng(stazione.getLatitude(), stazione.getLongitude()));
            }
        }

        mMap.addPolyline(polylineOptions);
    }

    // Funzione per creare il puntino con bordo bianco
    private BitmapDescriptor creaPuntino(int colore) {
        int size = 20; // Dimensione del pallino
        Bitmap bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // Bordo bianco
        paint.setColor(Color.WHITE);
        canvas.drawCircle(size/2f, size/2f, size/2f, paint);

        // Centro colorato
        paint.setColor(colore);
        canvas.drawCircle(size/2f, size/2f, (size/2f) - 4, paint);

        return BitmapDescriptorFactory.fromBitmap(bmp);
    }
}