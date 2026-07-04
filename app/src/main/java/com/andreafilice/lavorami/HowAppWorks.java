package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mapbox.maps.MapView;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.mapbox.maps.plugin.gestures.GesturesUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HowAppWorks extends AppCompatActivity {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable starAnimation;
    private MapView mapViewRef;
    private int coloreLinea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        MapboxHelper.init(getMetaData(this, "MAPBOX_KEY"));
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_how_app_works);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MapView mapView = findViewById(R.id.mapView);
        MapboxHelper.loadMap(mapView, isDarkMode(), mapViewReady -> {
            onMapReady(mapViewReady);
        });

        //*EXAMPLE WORK
        /// In this section of the code, we add 20 days for the work to avoid the "End Work" animation in the example work.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 20);

        Date dateToDisplay = calendar.getTime();
        TextView txtWorksiteEndDate = findViewById(R.id.txtWorksiteEndDate);
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

        txtWorksiteEndDate.setText(simpleDate.format(dateToDisplay));

        //*BUTTONS
        /// In this section of the code, we create the actions to go back to Settings menu.
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());

        //*ANIMATIONS
        /// In this section of the code, we set up some animations in this screen.
        ImageView favIcon = findViewById(R.id.iconFavorite);
        ImageButton heartIcon = findViewById(R.id.buttonAddLine);
        Animation scaleDownUp = AnimationUtils.loadAnimation(this, R.anim.scale_down_up);

        final boolean[] isFilled = {false};

        starAnimation = new Runnable() {
            @Override
            public void run() {
                isFilled[0] = !isFilled[0];

                if (isFilled[0]) {
                    heartIcon.setImageResource(R.drawable.ic_heart);
                    heartIcon.setImageTintList(ColorStateList.valueOf(getColor(R.color.heartColor)));
                    favIcon.setImageResource(R.drawable.ic_star_fill);
                } else {
                    heartIcon.setImageResource(R.drawable.ic_heart_empty);
                    heartIcon.setImageTintList(ColorStateList.valueOf(getColor(R.color.text_primary)));
                    favIcon.setImageResource(R.drawable.ic_star_empty);
                }

                heartIcon.startAnimation(scaleDownUp);
                favIcon.startAnimation(scaleDownUp);
                handler.postDelayed(this, 2000);
            }
        };
        handler.post(starAnimation);

        //*EXPAND WORK
        ImageView worksiteArrowDesc = findViewById(R.id.worksiteArrowDesc);
        TextView txtWorksiteStopExample = findViewById(R.id.txtWorksiteStopExample);
        ConstraintLayout cardExampleWork = findViewById(R.id.cardExampleWork);

        cardExampleWork.setOnClickListener(v -> {
            boolean isExpanded = txtWorksiteStopExample.getVisibility() == View.VISIBLE;
            if (isExpanded) {
                txtWorksiteStopExample.setVisibility(View.GONE);
                worksiteArrowDesc.animate().rotation(-90).setDuration(200).start();
            }
            else {
                txtWorksiteStopExample.setVisibility(View.VISIBLE);
                worksiteArrowDesc.animate().rotation(0).setDuration(200).start();
            }
            ActivityUtils.triggerFeedback(this);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler != null && starAnimation != null) {
            handler.removeCallbacks(starAnimation);
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void onMapReady(MapView mapView) {
        FrameLayout layoutMaps = findViewById(R.id.googleMapsFrameLayout);
        LinearLayout layoutLoadingMap = findViewById(R.id.loadingMapsFragmentLayout);
        elaboraStazioni(layoutMaps, layoutLoadingMap, mapView);
    }

    private void elaboraStazioni(FrameLayout layoutMaps, LinearLayout layoutLoadingMap, MapView mapView) {

        List<MetroStation> tutteLeStazioni = new ArrayList<>();
        for (MetroStation s : StationDB.getAllStations(false)) {
            if (s.getLine().trim().equalsIgnoreCase("M1")) tutteLeStazioni.add(s);
        }

        coloreLinea = ContextCompat.getColor(this, StationDB.getLineColor(this, "M1"));
        int coloreDefaultText = ContextCompat.getColor(this, R.color.text_primary);
        String hexColor = String.format("#%06X", (0xFFFFFF & coloreLinea));
        String hexColorText = String.format("#%06X", (0xFFFFFF & coloreDefaultText));

        List<Feature> markerFeatures = new ArrayList<>();
        for (MetroStation station : tutteLeStazioni) {
            if (station.getName().equalsIgnoreCase("NO_DRAW")) continue;

            markerFeatures.add(MapboxHelper.makeStationFeature(station.getLatitude(), station.getLongitude(), station.getName()));
        }

        MapboxHelper.addCircleLayer(mapView, markerFeatures, hexColor, hexColorText);

        disegnaPolilinea(mapView, tutteLeStazioni, hexColor);

        if (!tutteLeStazioni.isEmpty()) {
            double latMedia = 45.4682, lngMedia = 9.17588;
            double zoom = 11.5;

            MapboxHelper.setCamera(mapView, latMedia, lngMedia, zoom);
        }

        layoutMaps.setVisibility(View.VISIBLE);
        layoutMaps.setAlpha(0f);
        layoutMaps.animate().alpha(1f).setDuration(300).start();
        layoutLoadingMap.setVisibility(View.GONE);

        mapViewRef = mapView;

        if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED)
            MapboxHelper.enableUserLocation(mapViewRef, false);
    }

    private void disegnaPolilinea(MapView mapView, List<MetroStation> stazioni, String hexColor) {
        if (stazioni.size() < 2) return;

        Map<String, List<MetroStation>> branchMap = new LinkedHashMap<>();
        for (MetroStation station : stazioni) {
            if (!branchMap.containsKey(station.getBranch())) branchMap.put(station.getBranch(), new ArrayList<>());
            branchMap.get(station.getBranch()).add(station);
        }

        List<MetroStation> mainStations = branchMap.containsKey("Main") ? branchMap.get("Main") : new ArrayList<>();
        int index = 0;

        if (mainStations.size() >= 2) {
            List<Point> points = new ArrayList<>();

            for (MetroStation station : mainStations)
                points.add(Point.fromLngLat(station.getLongitude(), station.getLatitude()));

            //*CALLBACK TO HELPER
            /// CallBack to add the layer with this current data now extracted.
            MapboxHelper.addLineLayer(mapView, "line-source-main", "line-layer-main", points, hexColor, false);
        }

        for (Map.Entry<String, List<MetroStation>> entry : branchMap.entrySet()) {
            if (entry.getKey().equals("Main")) continue;

            List<MetroStation> branch = entry.getValue();
            if (branch.isEmpty()) continue;

            boolean isPlanned = entry.getKey().toLowerCase().contains("new") || entry.getKey().toLowerCase().contains("nuova");
            List<MetroStation> pool = isPlanned ? new ArrayList<>(stazioni) : new ArrayList<>(mainStations);

            if (isPlanned) pool.removeIf(s -> s.getBranch().equals(entry.getKey()));
            if (pool.isEmpty()) continue;

            //*GENERATED BY StackOverflow
            /// This code is taken from StackOverflow forums.
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

    private boolean isDarkMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}