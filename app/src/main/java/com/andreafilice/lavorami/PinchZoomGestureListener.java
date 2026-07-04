package com.andreafilice.lavorami;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.webkit.WebView;

import androidx.annotation.NonNull;

public class PinchZoomGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

    private WebView webView;
    private ScaleGestureDetector scaleGestureDetector;
    private static final float MIN_SCALE = 0.8f;
    private static final float MAX_SCALE = 3.0f;
    private static final float SCALE_SENSITIVITY = 0.5f;

    private float currentScale = 1.0f;

    public PinchZoomGestureListener(Context context, WebView webView) {
        this.webView = webView;
        this.scaleGestureDetector = new ScaleGestureDetector(context, this);
    }

    public boolean onTouch(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {return true;}

    @Override
    public void onScaleEnd(@NonNull ScaleGestureDetector scaleGestureDetector) {}

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();

        scaleFactor = 1 + (scaleFactor - 1) * SCALE_SENSITIVITY;

        currentScale *= scaleFactor;
        currentScale = Math.max(MIN_SCALE, Math.min(currentScale, MAX_SCALE));

        int zoomLevel = Math.round((currentScale - 1.0f) * 100);
        webView.setInitialScale(100 + zoomLevel);

        return true;
    }

    public void resetZoom() {
        currentScale = 1.0f;
        webView.setInitialScale(100);
    }

    public ScaleGestureDetector getScaleGestureDetector() {return scaleGestureDetector;}
}