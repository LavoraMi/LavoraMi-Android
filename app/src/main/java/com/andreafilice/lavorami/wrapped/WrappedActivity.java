package com.andreafilice.lavorami.wrapped;

import android.animation.ValueAnimator;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.andreafilice.lavorami.R;

import java.util.ArrayList;
import java.util.List;

public class WrappedActivity extends AppCompatActivity {
    ///WrappedActivity (il regista, gestisce timer/progress/navigazione)
    ///       │
    ///       ├── contiene → ViewPager2
    ///       │                   │
    ///       │                   └── usa → StoriesPagerAdapter (il catalogo)
    ///       │                                     │
    ///       │                                     └── crea → StoryFragment (il contenuto di ogni storia)
    ///
    private static final int STORY_DURATION_MS = 5000; // durata di ogni storia
    private static final int TOTAL_STORIES = 5;

    private ViewPager2 viewPager;
    private LinearLayout progressContainer;
    private final List<View> progressFills = new ArrayList<>();
    private ValueAnimator currentAnimator;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_wrapped);

        viewPager = findViewById(R.id.storiesViewPager);
        progressContainer = findViewById(R.id.progressContainer);

        setupViewPager();
        setupProgressBars();
        setupTapZones();

        startStoryTimer(currentIndex);
    }

    private void setupViewPager() {
        StoriesPagerAdapter adapter = new StoriesPagerAdapter(this, TOTAL_STORIES);
        viewPager.setAdapter(adapter);

        // Disabilita lo swipe "libero" se vuoi solo avanzamento controllato,
        // oppure lascialo true per permettere swipe manuale (consigliato)
        viewPager.setUserInputEnabled(true);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                onStoryChanged(position);
            }
        });
    }

    private void setupProgressBars() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < TOTAL_STORIES; i++) {
            View segment = inflater.inflate(R.layout.item_stories_progress_bar, progressContainer, false);
            progressContainer.addView(segment);
            progressFills.add(segment.findViewById(R.id.progressFill));
        }
    }

    private void setupTapZones() {
        findViewById(R.id.tapPrevious).setOnClickListener(v -> goToStory(currentIndex - 1));
        findViewById(R.id.tapNext).setOnClickListener(v -> goToStory(currentIndex + 1));
    }

    private void onStoryChanged(int position) {
        // Segna come "piene" tutte le storie precedenti
        for (int i = 0; i < position; i++) {
            setFillWidth(progressFills.get(i), 1f);
        }
        // Svuota quelle successive
        for (int i = position + 1; i < TOTAL_STORIES; i++) {
            setFillWidth(progressFills.get(i), 0f);
        }

        currentIndex = position;
        startStoryTimer(position);
    }

    private void startStoryTimer(int position) {
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }

        View fill = progressFills.get(position);
        setFillWidth(fill, 0f);

        currentAnimator = ValueAnimator.ofFloat(0f, 1f);
        currentAnimator.setDuration(STORY_DURATION_MS);
        currentAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            setFillWidth(fill, value);
        });
        currentAnimator.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(@NonNull android.animation.Animator animation) {
                if (currentIndex < TOTAL_STORIES) {
                    goToStory(currentIndex);
                } else {
                    finish(); // ultima storia finita, chiudi (o vai a un'altra Activity)
                }
            }
        });
        currentAnimator.start();
    }

    private void setFillWidth(View fill, float fraction) {
        View parent = (View) fill.getParent();
        parent.post(() -> {
            int totalWidth = parent.getWidth();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fill.getLayoutParams();
            params.width = (int) (totalWidth * fraction);
            fill.setLayoutParams(params);
        });
    }

    private void goToStory(int index) {
        if (index < 0) return; // già alla prima, ignora tap indietro
        if (index >= TOTAL_STORIES) {
            finish();
            return;
        }
        viewPager.setCurrentItem(index, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }
    }
}