package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;
import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.init(this);
    }
}
