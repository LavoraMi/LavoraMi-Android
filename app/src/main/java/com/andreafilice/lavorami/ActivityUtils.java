package com.andreafilice.lavorami;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.util.Log;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

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

        context.startActivity(browserIntent);
    }

    public static void openURLWithTabBuilder(Context context, FragmentManager manager, String url) {
        ///@PARAMETER
        /// String url is the URL to open in-app Browser.

        BrowserSelectedType selectedType = BrowserSelectedType.valueOf(DataManager.getStringData(DataKeys.KEY_OPEN_LINK_TYPE, "IN_APP"));

        Log.d("BROWSER_TYPE", selectedType.toString());

        if(selectedType == BrowserSelectedType.IN_APP) InAppBrowserBottomSheet.newInstance(url).show(manager, "browser");
        else openURL(context, url);
    }

    public static void triggerFeedback(Context context) {
        /// This function trigger the Haptic Feedback of the phone.
        boolean hapticsEnabled = DataManager.getBoolData(DataKeys.KEY_HAPTIC_FEEDBACKS, true);
        Vibrator vibrator;

        if(hapticsEnabled) {
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

    public static String getMetaData(Context context, String key){
        /// This function get from our AndroidManifest.xml the values of .env files.
        /// @PARAMETER
        /// String key is the actual key of the value that we need to grab from the manifest file.

        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = info.metaData;

            if(bundle != null)
                return bundle.getString(key);
        }
        catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, context.getString(R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();
            Log.d("ERROR", "Impossibile trovare questo valore. ERROR MESSAGE: " + e.getMessage());
        }
        return null;
    }
}
