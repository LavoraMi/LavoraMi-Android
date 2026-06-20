package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mapbox.maps.Image;
import com.mapbox.maps.MapView;

import kotlin.Unit;

public class InfoNearMeActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        MapboxHelper.init(getMetaData(this, "MAPBOX_KEY"));
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_near_me);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back button
        ImageView backBtn = findViewById(R.id.backBtn);
        Button buttonClose= findViewById(R.id.btn_close);
        backBtn.setOnClickListener(v -> finish());
        buttonClose.setOnClickListener(v -> finish());

        // Position button
        ImageButton positionButton = findViewById(R.id.positionButton);
        positionButton.setOnClickListener(v -> {
            if (mapView != null) MapboxHelper.zoomToUserLocationCircle(mapView);
        });

        FrameLayout layoutMaps = findViewById(R.id.googleMapsFrameLayout);
        LinearLayout layoutLoadingMap = findViewById(R.id.loadingMapsFragmentLayout);
        mapView = findViewById(R.id.mapView);

        boolean darkMode = (getResources().getConfiguration().uiMode
                & android.content.res.Configuration.UI_MODE_NIGHT_MASK)
                == android.content.res.Configuration.UI_MODE_NIGHT_YES;

        MapboxHelper.loadMap(mapView, darkMode, loadedMapView -> {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                MapboxHelper.getUserLocationOnce(loadedMapView, (lat, lng) -> {
                    MapboxHelper.setCamera(loadedMapView, lat, lng, 9.5);
                    MapboxHelper.drawRadiusCircle(loadedMapView, lat, lng, 10_000, "#2196F3");
                    showMap(layoutMaps, layoutLoadingMap);
                });

            } else {
                // Nessun permesso: Milano come fallback
                MapboxHelper.setCamera(loadedMapView, 45.4654, 9.1859, 11.0);
                showMap(layoutMaps, layoutLoadingMap);
            }
        });
    }

    private void showMap(FrameLayout layoutMaps, LinearLayout layoutLoadingMap) {
        layoutMaps.setVisibility(android.view.View.VISIBLE);
        layoutMaps.setAlpha(0f);
        layoutMaps.animate().alpha(1f).setDuration(300).start();
        layoutLoadingMap.setVisibility(android.view.View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mapView != null) mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mapView != null) mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) mapView.onDestroy();
    }
}