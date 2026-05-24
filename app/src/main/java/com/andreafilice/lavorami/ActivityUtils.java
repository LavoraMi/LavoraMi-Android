package com.andreafilice.lavorami;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;

public class ActivityUtils {
    public static void changeActivity(Context context, Class<?> destinationLayout) {
        ///@PARAMETER
        /// Class<?> destinationLayout is a destination activity which this function change.

        Intent layoutChange = new Intent(context, destinationLayout);
        layoutChange.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        layoutChange.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle animOptions = ActivityOptionsCompat
                .makeCustomAnimation(context, 0, 0)
                .toBundle();

        context.startActivity(layoutChange, animOptions);
    }

    public static void openURL(Context context, String url) {
        ///@PARAMETER
        ///String url is the URL to open in Browser.

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        if(browserIntent.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(browserIntent);
    }

    public static void openURLWithTabBuilder(Context context, String url) {
        ///@PARAMETER
        /// String url is the URL to open in-app Browser.
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }

    public static void triggerFeedback(Context context) {
        /// This function trigger the Haptic Feedback of the phone.

        Vibrator vibrator;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            VibratorManager manager = (VibratorManager) context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
            vibrator = manager.getDefaultVibrator();
        }
        else
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        else
            vibrator.vibrate(20);
    }
}
