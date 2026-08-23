package com.andreafilice.lavorami.wrapped;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.andreafilice.lavorami.R;

import java.util.ArrayList;
import java.util.List;

public class WrappedActivity extends AppCompatActivity implements StoryFragment.OnVideoReadyListener {
    private static final int TOTAL_STORIES = 5;
    private ViewPager2 viewPager;
    private LinearLayout progressContainer;
    private final List<View> progressFills = new ArrayList<>();
    private ValueAnimator currentAnimator;
    private int currentIndex = 0;
    private int waitingForVideoDurationIndex = -1;
    private boolean isFirstPageSelection = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapped);

        viewPager = findViewById(R.id.storiesViewPager);
        progressContainer = findViewById(R.id.progressContainer);

        setupViewPager();
        setupProgressBars();
        setupTapZones();

        startStoryForPosition(currentIndex);
    }

    private void setupViewPager() {
        StoriesPagerAdapter adapter = new StoriesPagerAdapter(this, TOTAL_STORIES);
        viewPager.setAdapter(adapter);
        viewPager.setUserInputEnabled(true);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
            super.onPageSelected(position);

                if (isFirstPageSelection) {
                    // La storia 0 è già stata avviata da onCreate(); ignora
                    isFirstPageSelection = false;
                    if (position == currentIndex) return;
                }

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
        boolean movingForward = position > currentIndex;

        for (int i = 0; i < position; i++) {setFillWidthInstant(progressFills.get(i), 1f);}
        for (int i = position + 1; i < TOTAL_STORIES; i++) {setFillWidthInstant(progressFills.get(i), 0f);}

        if (currentAnimator != null) {
            currentAnimator.cancel();
            currentAnimator = null;
        }
        if (currentIndex >= 0 && currentIndex < TOTAL_STORIES && currentIndex != position) setFillWidthInstant(progressFills.get(currentIndex), movingForward ? 1f : 0f);
        waitingForVideoDurationIndex = -1;

        currentIndex = position;
        startStoryForPosition(position);
    }

    /**
     * Tutte le storie sono video: aspettiamo sempre la callback con la durata
     * reale prima di avviare il timer della progress bar.
     */
    private void startStoryForPosition(int position) {
        waitingForVideoDurationIndex = position;
        setFillWidthInstant(progressFills.get(position), 0f);
    }

    @Override
    public void onVideoDurationReady(int durationMs) {
        if (waitingForVideoDurationIndex == currentIndex) {
            waitingForVideoDurationIndex = -1;
            startStoryTimer(currentIndex, durationMs);
        }
    }

    private void startStoryTimer(int position, int durationMs) {
        if (currentAnimator != null) {
            currentAnimator.cancel();
            currentAnimator = null;
        }

        View fill = progressFills.get(position);
        setFillWidthInstant(fill, 0f);

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(durationMs);
        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            setFillWidthDirect(fill, value);
        });

        animator.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(@NonNull android.animation.Animator animation) {
                if (currentAnimator != animation) return;
                currentAnimator = null;

                int nextIndex = position + 1;
                if (nextIndex < TOTAL_STORIES) goToStory(nextIndex);
                else finish();
            }

            @Override
            public void onAnimationCancel(@NonNull android.animation.Animator animation) {
                if (currentAnimator == animation) currentAnimator = null;
            }
        });

        currentAnimator = animator;
        animator.start();
    }

    private void setFillWidthInstant(View fill, float fraction) {
        View parent = (View) fill.getParent();
        if (parent.getWidth() > 0) applyFillWidth(fill, parent.getWidth(), fraction);
        else {
            parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    parent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    applyFillWidth(fill, parent.getWidth(), fraction);
                }
            });
        }
    }

    private void setFillWidthDirect(View fill, float fraction) {
        View parent = (View) fill.getParent();
        int totalWidth = parent.getWidth();
        if (totalWidth > 0) applyFillWidth(fill, totalWidth, fraction);
    }

    private void applyFillWidth(View fill, int totalWidth, float fraction) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fill.getLayoutParams();
        params.width = (int) (totalWidth * fraction);
        fill.setLayoutParams(params);
    }

    private void goToStory(int index) {
        if (index < 0) return;

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
            currentAnimator = null;
        }
    }
}