package com.andreafilice.lavorami;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class DataManager{
    static SharedPreferences sharedPref;

    public static void init(Context context) {
        if (sharedPref == null)
            sharedPref = context.getApplicationContext().getSharedPreferences("LavoraMiPreferences", Context.MODE_PRIVATE);
    }

    public static void saveStringData(DataKeys key, String value){sharedPref.edit().putString(key.toString(), value).apply();}

    public static void saveArrayStringsData(DataKeys key, Set<String> values){sharedPref.edit().putStringSet(key.toString(), values).apply();}

    public static void saveIntData(DataKeys key, int value){sharedPref.edit().putInt(key.toString(), value).apply();}

    public static void saveBoolData(DataKeys key, boolean value){sharedPref.edit().putBoolean(key.toString(), value).apply();}

    public static String getStringData(DataKeys key, String defaulValue){return sharedPref.getString(key.toString(), defaulValue);}

    public static Set<String> getStringArray(DataKeys key, Set<String> defaultValue){return sharedPref.getStringSet(key.toString(), defaultValue);}

    public static int getIntData(DataKeys key, int defaultValue){return sharedPref.getInt(key.toString(), defaultValue);}

    public static boolean getBoolData(DataKeys key, boolean defaultValue){return sharedPref.getBoolean(key.toString(), defaultValue);}
}
