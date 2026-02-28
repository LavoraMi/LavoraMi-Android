package com.andreafilice.lavorami;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DotsLoaderView extends View {
    private static final int N = 8;
    private static final float RADIUS = 70f;
    private static final float BASE_R = 6f;
    private static final float PEAK_R = 10f;
    private static final float SPEED  = 0.012f;

    private final int[] COLORS = {
        0xFF4285F4, 0xFFEA4335, 0xFFFBBC05, 0xFF34A853,
        0xFF4285F4, 0xFFEA4335, 0xFFFBBC05, 0xFF34A853,
    };

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float angle = 0f;

    public DotsLoaderView(Context context) { super(context); }
    public DotsLoaderView(Context context, AttributeSet attrs) { super(context, attrs); }
    public DotsLoaderView(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        float density = getResources().getDisplayMetrics().density;
        float radius = RADIUS * density;
        float baseR  = BASE_R * density;
        float peakR  = PEAK_R * density;

        for (int i = 0; i < N; i++) {
            double theta = angle + (i / (double) N) * Math.PI * 2;
            float x = cx + (float)(Math.cos(theta) * radius);
            float y = cy + (float)(Math.sin(theta) * radius);
            float phase = (float)(Math.sin(angle * 2 + i * (Math.PI * 2 / N)) + 1) / 2f;
            float eased = easeInOut(phase);
            float r = baseR + (peakR - baseR) * eased;
            float alpha = 0.45f + 0.55f * eased;

            paint.setColor(COLORS[i]);
            paint.setAlpha((int)(alpha * 255));
            canvas.drawCircle(x, y, r, paint);
        }

        angle += SPEED;
        postInvalidateOnAnimation();
    }

    private float easeInOut(float t) {return t < 0.5f ? 2 * t * t : -1 + (4 - 2 * t) * t;}
}