package com.andreafilice.lavorami;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;

public class ActivityManager {
    public static void changeActivity(Context context, Class<?> destinationLayout) {
        ///@PARAMETER
        /// Class<?> destinationLayout is a destination activity which this function change.

        //*CHANGE LAYOUT
        Intent layoutChange = new Intent(context, destinationLayout);
        layoutChange.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        Bundle animOptions = ActivityOptionsCompat
                .makeCustomAnimation(context, 0, 0)
                .toBundle();

        context.startActivity(layoutChange, animOptions);
    }

    public static void openURL(Context context, String url) {
        ///@PARAMETER
        ///String url is the URL to open in Browser.

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

    public static void openURLWithTabBuilder(Context context, String url) {
        ///@PARAMETER
        /// String url is the URL to open in-app Browser.
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
