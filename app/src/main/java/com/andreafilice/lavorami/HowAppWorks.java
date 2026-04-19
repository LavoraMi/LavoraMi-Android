package com.andreafilice.lavorami;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HowAppWorks extends AppCompatActivity {

    private final Handler handler = new Handler();
    private Runnable starAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_how_app_works);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //*BUTTONS
        /// In this section of the code, we create the actions to go back to Settings menu.
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());

        //*ANIMATIONS
        /// In this section of the code, we set up some animations in this screen.
        ImageView favIcon = findViewById(R.id.iconFavorite);
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);

        starAnimation = new Runnable() {
            @Override
            public void run() {
                favIcon.setImageResource(R.drawable.ic_star_empty);
                favIcon.startAnimation(scaleDown);
                handler.postDelayed(() -> {
                        favIcon.startAnimation(scaleUp);
                        favIcon.setImageResource(R.drawable.ic_star_fill);
                handler.postDelayed(this, 5000);
                },500);
            }
        };
        handler.post(starAnimation);

        //*MAP THEME
        /// In this section of the code, we set up the map image based from the Value of Theme Saved
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        boolean isNightMode = nightModeFlags == Configuration.UI_MODE_NIGHT_YES;

        ImageView mapImage = findViewById(R.id.imgLineOnMap);
        mapImage.setImageResource(isNightMode ? R.drawable.ic_line_on_map : R.drawable.ic_line_on_map_light);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //*DESTROY HANDLER
        /// In this section of the code, we will destroy the handler to avoid error loops.
        if(handler != null && starAnimation != null) {
            handler.removeCallbacks(starAnimation);
            handler.removeCallbacksAndMessages(null);
        }
    }
}
