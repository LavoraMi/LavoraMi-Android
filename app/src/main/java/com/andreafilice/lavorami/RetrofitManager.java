package com.andreafilice.lavorami;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static Retrofit instance;

    public static Retrofit get() {
        /// This method optimize Object-Creation performance.

        if (instance == null)
            instance = new Retrofit.Builder()
                    .baseUrl("https://cdn.lavorami.it/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return instance;
    }
}
