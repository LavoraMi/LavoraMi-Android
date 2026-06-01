package com.andreafilice.lavorami;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HowAppWorks extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
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

        //*MAP THEME
        /// In this section of the code, we set up the map image based from the Value of Theme Saved
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        boolean isNightMode = nightModeFlags == Configuration.UI_MODE_NIGHT_YES;

        ImageView mapImage = findViewById(R.id.imgLineOnMap);
        mapImage.setImageResource(isNightMode ? R.drawable.ic_line_on_map : R.drawable.ic_line_on_map_light);

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
}