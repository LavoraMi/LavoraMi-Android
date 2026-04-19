package com.andreafilice.lavorami;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.init(this);
    }
}
