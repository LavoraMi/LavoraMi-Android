package com.andreafilice.lavorami;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class App extends Application implements Application.ActivityLifecycleCallbacks {

    private static final long CHECK_BACKGROUND_DELAY = 1000; // 1 secondo di tolleranza per transizione Activity
    private static final long XP_INTERVAL = 60000; // 1 minuto

    private int resumedActivitiesCount = 0;
    private boolean isForeground = false;
    private Activity currentActivity;
    private AccountLevel accountLevel;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable checkBackgroundRunnable = new Runnable() {
        @Override
        public void run() {
            if (resumedActivitiesCount <= 0) {
                isForeground = false;
                currentActivity = null;
                handler.removeCallbacks(xpRunnable);
            }
        }
    };

    private final Runnable xpRunnable = new Runnable() {
        @Override
        public void run() {
            if (isForeground) {
                if (accountLevel == null) {
                    accountLevel = AccountLevel.getInstance(App.this);
                }
                accountLevel.addMinuteXp(currentActivity);
                handler.postDelayed(this, XP_INTERVAL);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.init(this);
        accountLevel = AccountLevel.getInstance(this);
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(@NonNull Activity activity) {}

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivity = activity;
        resumedActivitiesCount++;

        handler.removeCallbacks(checkBackgroundRunnable);

        if (!isForeground) {
            isForeground = true;
            handler.removeCallbacks(xpRunnable);
            handler.postDelayed(xpRunnable, XP_INTERVAL);
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        resumedActivitiesCount--;
        if (resumedActivitiesCount <= 0) {
            resumedActivitiesCount = 0;
            handler.postDelayed(checkBackgroundRunnable, CHECK_BACKGROUND_DELAY);
        }
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {}

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        if (currentActivity == activity) {
            currentActivity = null;
        }
    }
}
