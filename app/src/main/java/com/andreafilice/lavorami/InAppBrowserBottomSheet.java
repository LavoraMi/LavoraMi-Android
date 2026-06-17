package com.andreafilice.lavorami;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class InAppBrowserBottomSheet extends BottomSheetDialogFragment {
    private WebView webView;
    private ProgressBar progressBar;
    private TextView urlText;
    private TextView urlTitleText;

    private ImageButton shareBtn;
    private ImageButton doneBtn;
    private ImageButton openBrowserBtn;
    private ImageView iconCharacter;
    private ImageView iconZoomHint;

    private LinearLayout actionsPill;
    private FrameLayout zoomBtn;

    private final Handler tintHandler = new Handler(Looper.getMainLooper());

    private final String[] ALLOWED_DOMAINS = {
        "lavorami.it",
        "www.lavorami.it",
        "docs.google.com",
        "trenord.it",
        "atm.it",
        "giromilano.atm.it",
        "tilo.ch",
        "malpensaexpress.it",
        "movibus.it",
        "stavautolinee.it",
        "starmobility.it",
        "autoguidovie.it",
        "patreon.com",
        "buymeacoffee.com"
    };

    public static InAppBrowserBottomSheet newInstance(String url) {
        InAppBrowserBottomSheet fragment = new InAppBrowserBottomSheet();

        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {return inflater.inflate(R.layout.bottom_sheet_browser, container, false);}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        dialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
        dialog.getBehavior().setSkipCollapsed(true);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog == null) return;

        FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (bottomSheet == null) return;

        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int sheetHeight = (int) (screenHeight * 0.92);

        ViewGroup.LayoutParams params = bottomSheet.getLayoutParams();
        params.height = sheetHeight;
        bottomSheet.setLayoutParams(params);

        BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        behavior.setSkipCollapsed(true);
        behavior.setPeekHeight(sheetHeight);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = view.findViewById(R.id.webview);
        progressBar = view.findViewById(R.id.progress_bar);
        urlText = view.findViewById(R.id.url_text);
        urlTitleText = view.findViewById(R.id.webTitle);

        shareBtn = view.findViewById(R.id.share_btn);
        doneBtn = view.findViewById(R.id.done_btn);
        openBrowserBtn = view.findViewById(R.id.open_browser_btn);
        iconCharacter = view.findViewById(R.id.icon_character);
        iconZoomHint = view.findViewById(R.id.icon_zoom_hint);

        actionsPill = view.findViewById(R.id.actions_pill);

        FrameLayout closeBtn = view.findViewById(R.id.close_btn);
        zoomBtn = view.findViewById(R.id.zoom_btn);

        zoomBtn.setOnClickListener(v -> {
            showZoomPopup(v);
            ActivityUtils.triggerFeedback(getContext());
        });

        setupWebView();
        loadInitialUrl();

        tintHandler.postDelayed(() -> {
            if (getContext() != null) startZoomHintAnimation();
        }, 5000);

        setupClickListeners(closeBtn);
    }

    private void startZoomHintAnimation() {
        if (iconCharacter == null || iconZoomHint == null) return;

        iconCharacter.setAlpha(1f);
        iconZoomHint.setAlpha(0f);

        long fadeDuration = 400;

        iconCharacter.animate()
                .alpha(0f)
                .setDuration(fadeDuration)
                .start();

        iconZoomHint.animate()
                .alpha(1f)
                .setDuration(fadeDuration)
                .start();

        tintHandler.postDelayed(() -> {
            if (getContext() == null || iconCharacter == null) return;

            iconZoomHint.animate()
                    .alpha(0f)
                    .setDuration(fadeDuration)
                    .start();

            iconCharacter.animate()
                    .alpha(1f)
                    .setDuration(fadeDuration)
                    .start();

        }, 3000);
    }

    private void showZoomPopup(View anchorView) {
        if (getContext() == null || webView == null) return;

        View popupView = LayoutInflater.from(getContext()).inflate(R.layout.popup_zoom, null);

        android.widget.PopupWindow popupWindow = new android.widget.PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        );

        popupWindow.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageButton btnZoomIn = popupView.findViewById(R.id.btn_zoom_in);
        ImageButton btnZoomOut = popupView.findViewById(R.id.btn_zoom_out);

        updateZoomButtonsState(btnZoomIn, btnZoomOut);

        btnZoomIn.setOnClickListener(v -> {
            webView.zoomIn();
            ActivityUtils.triggerFeedback(getContext());
            v.postDelayed(() -> updateZoomButtonsState(btnZoomIn, btnZoomOut), 150);
        });

        btnZoomOut.setOnClickListener(v -> {
            webView.zoomOut();
            ActivityUtils.triggerFeedback(getContext());
            v.postDelayed(() -> updateZoomButtonsState(btnZoomIn, btnZoomOut), 150);
        });

        int xOffset = -120;
        int yOffset = 10;

        popupWindow.showAsDropDown(anchorView, xOffset, yOffset);
    }

    private void updateZoomButtonsState(ImageButton btnIn, ImageButton btnOut) {
        if (webView == null) return;

        boolean canZoomIn = webView.canZoomIn();
        boolean canZoomOut = webView.canZoomOut();

        btnIn.setEnabled(canZoomIn);
        btnIn.setAlpha(canZoomIn ? 1.0f : 0.4f);

        btnOut.setEnabled(canZoomOut);
        btnOut.setAlpha(canZoomOut ? 1.0f : 0.4f);
    }

    private void setupWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {}

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                urlText.setText(url);

                String title = webView.getTitle();

                if(title.length() > 30)
                    title = title.substring(0, 27) + "...";

                urlTitleText.setText(title);

                if(webView.getTitle().isEmpty() || urlTitleText.getText().isEmpty())
                    urlTitleText.setText("LavoraMi");

                tintHandler.postDelayed(InAppBrowserBottomSheet.this::updateAdaptiveTint, 300);
            }
        });

        webView.setWebChromeClient(new android.webkit.WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress < 100) {
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setAlpha(1f);
                    progressBar.setVisibility(View.VISIBLE);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    progressBar.setProgress(newProgress, true);
                else
                    progressBar.setProgress(newProgress);
            }
            else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    progressBar.setProgress(100, true);
                else
                    progressBar.setProgress(100);

                progressBar.animate()
                    .alpha(0f)
                    .setDuration(400)
                    .withEndAction(() -> {
                        progressBar.setVisibility(View.GONE);
                        progressBar.setAlpha(1f);
                    })
                    .start();
            }
            }
        });

        webView.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(webView.getScrollY() > 0);
            return false;
        });

        webView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            tintHandler.removeCallbacksAndMessages(null);
            tintHandler.postDelayed(this::updateAdaptiveTint, 150);
        });
    }

    private boolean isDomainAllowed(String urlString) {
        Uri uri = Uri.parse(urlString);
        String host = uri.getHost();

        if (host != null) {
            for (String domain : ALLOWED_DOMAINS) {
                if (host.equals(domain) || host.endsWith("." + domain)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void loadInitialUrl() {
        String url = getArguments() != null ? getArguments().getString("url") : null;
        if (url != null && !url.isEmpty()) {

            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "https://" + url;

            if (url.toLowerCase().contains(".pdf"))
                url = "https://docs.google.com/gview?embedded=true&url=" + url;


            if (isDomainAllowed(url)) {
                urlText.setText(url);
                webView.loadUrl(url);
            }
            else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (getContext() != null) getContext().startActivity(intent);
                dismiss();
            }
        }
    }

    private void setupClickListeners(FrameLayout closeBtn) {
        closeBtn.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(getContext());
            dismiss();
        });

        doneBtn.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(getContext());
            webView.reload();
        });

        shareBtn.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(getContext());
            shareCurrentUrl();
        });

        openBrowserBtn.setOnClickListener(v -> {
            ActivityUtils.triggerFeedback(getContext());
            openInExternalBrowser();
        });
    }

    private void shareCurrentUrl() {
        if (webView.getUrl() == null) return;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());

        startActivity(Intent.createChooser(intent, "Share"));
    }

    private void openInExternalBrowser() {
        if (webView.getUrl() == null) return;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
        startActivity(intent);
    }

    private void updateAdaptiveTint() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return;
        if (getDialog() == null || getDialog().getWindow() == null) return;
        if (actionsPill == null || actionsPill.getWidth() == 0) return;

        sampleAndApply(actionsPill);
    }

    private void sampleAndApply(View target) {
        int[] location = new int[2];
        target.getLocationInWindow(location);

        int width = target.getWidth();
        int height = target.getHeight();
        if (width <= 0 || height <= 0) return;

        Rect rect = new Rect(location[0], location[1], location[0] + width, location[1] + height);
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        PixelCopy.request(getDialog().getWindow(), rect, bitmap, copyResult -> {
            if (copyResult == PixelCopy.SUCCESS) {
                double luminance = computeAverageLuminance(bitmap);
                boolean isLight = luminance > 0.55;

                if (getActivity() != null)
                    getActivity().runOnUiThread(() -> applyAdaptiveStyle(isLight));
            }
            bitmap.recycle();
        }, tintHandler);
    }

    private double computeAverageLuminance(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int step = Math.max(1, Math.min(width, height) / 8);

        double totalLuminance = 0;
        int count = 0;

        for (int x = 0; x < width; x += step) {
            for (int y = 0; y < height; y += step) {
                int pixel = bitmap.getPixel(x, y);
                double luminance = (0.299 * Color.red(pixel) + 0.587 * Color.green(pixel) + 0.114 * Color.blue(pixel)) / 255.0;

                totalLuminance += luminance;
                count++;
            }
        }

        return count > 0 ? totalLuminance / count : 1.0;
    }

    private void applyAdaptiveStyle(boolean isLight) {
        int pillDrawable = isLight ? R.drawable.pill_glass_light : R.drawable.pill_glass_dark;
        int iconColor = isLight ? Color.BLACK : Color.WHITE;

        actionsPill.setBackgroundResource(pillDrawable);

        shareBtn.setColorFilter(iconColor);
        doneBtn.setColorFilter(iconColor);
        openBrowserBtn.setColorFilter(iconColor);
    }

    @Override
    public void onDestroyView() {
        tintHandler.removeCallbacksAndMessages(null);

        if (webView != null) {
            webView.stopLoading();
            webView.setWebViewClient(null);
            webView.destroy();
        }

        super.onDestroyView();
    }
}