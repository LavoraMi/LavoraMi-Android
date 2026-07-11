package com.andreafilice.lavorami;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class BouncingMarqueeTextView extends AppCompatTextView {
    private static final long EDGE_PAUSE_MS = 1200L;
    private static final float SPEED_PX_PER_SEC = 60f;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private ValueAnimator animator;
    private boolean isRunning = false;
    private boolean goingForward = true;
    private boolean componentReady = false;

    public BouncingMarqueeTextView(Context context) {
        super(context);
        init();
    }

    public BouncingMarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BouncingMarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setSingleLine(true);
        setEllipsize(null);
        setHorizontallyScrolling(true);
        componentReady = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (componentReady) restart();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (componentReady) restart();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (componentReady) restart();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    private void restart() {
        stop();
        scrollTo(0, 0);
        handler.post(this::maybeStart);
    }

    private void maybeStart() {
        if (isRunning) return;
        if (getText() == null) return;

        int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        if (viewWidth <= 0) return;

        float textWidth = getPaint().measureText(getText().toString());
        float distance = textWidth - viewWidth;

        if (distance <= 1f) return;

        isRunning = true;
        goingForward = true;
        scheduleNextLeg(distance);
    }

    private void scheduleNextLeg(float distance) {
        if (!isRunning) return;

        long duration = (long) ((distance / SPEED_PX_PER_SEC) * 1000f);

        handler.postDelayed(() -> {
            if (!isRunning) return;

            int from = goingForward ? 0 : (int) distance;
            int to = goingForward ? (int) distance : 0;

            animator = ValueAnimator.ofInt(from, to);
            animator.setDuration(duration);
            animator.setInterpolator(new LinearInterpolator());
            animator.addUpdateListener(anim -> scrollTo((int) anim.getAnimatedValue(), 0));
            animator.start();

            goingForward = !goingForward;

            handler.postDelayed(() -> scheduleNextLeg(distance), duration);
        }, EDGE_PAUSE_MS);
    }

    private void stop() {
        isRunning = false;
        handler.removeCallbacksAndMessages(null);
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }
}