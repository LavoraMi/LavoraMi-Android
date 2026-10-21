package com.andreafilice.lavorami;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class App extends Application implements Application.ActivityLifecycleCallbacks {

    private int resumedActivitiesCount = 0;
    private Activity currentActivity;
    private AccountLevel accountLevel;

    private final Handler xpHandler = new Handler(Looper.getMainLooper());
    private final Runnable xpRunnable = new Runnable() {
        @Override
        public void run() {
            if (accountLevel != null && resumedActivitiesCount > 0) {
                accountLevel.addMinuteXp(currentActivity);
            }
            if (resumedActivitiesCount > 0) {
                xpHandler.postDelayed(this, 60000);
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
        if (resumedActivitiesCount == 1) {
            xpHandler.removeCallbacks(xpRunnable);
            xpHandler.postDelayed(xpRunnable, 60000);
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        resumedActivitiesCount--;
        if (resumedActivitiesCount <= 0) {
            resumedActivitiesCount = 0;
            currentActivity = null;
            xpHandler.removeCallbacks(xpRunnable);
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
