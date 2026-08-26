package com.andreafilice.lavorami;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static Retrofit instance;

    public static Retrofit get() {
        if (instance == null)
            instance = new Retrofit.Builder()
                .baseUrl("https://cdn.lavorami.it/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }
}
