package com.andreafilice.lavorami;

import android.content.Context;
import androidx.annotation.Nullable;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupabaseAuthenticator implements Authenticator {
    private Context context;
    private String apiKey;
    private String baseUrl;

    public SupabaseAuthenticator(Context context, String apiKey, String baseUrl) {
        this.context = context.getApplicationContext();
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        SessionManager sessionManager = new SessionManager(context);
        String refreshToken = sessionManager.getRefreshToken();

        if (responseCount(response) >= 2) {
            sessionManager.logout();
            return null;
        }

        if (refreshToken == null || refreshToken.isEmpty())
            return null;

        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        SupabaseAPI api = retrofit.create(SupabaseAPI.class);
        retrofit2.Response<SupabaseModels.AuthResponse> refreshResponse = api.refreshToken(apiKey, new SupabaseModels.RefreshTokenRequest(refreshToken)).execute();

        if (refreshResponse.isSuccessful() && refreshResponse.body() != null) {
            SupabaseModels.AuthResponse newAuth = refreshResponse.body();

            sessionManager.saveSession(
                newAuth.access_token,
                newAuth.refresh_token,
                sessionManager.getUserEmail(),
                sessionManager.getUserName(),
                sessionManager.isLoggedInWithGoogle()
            );

            return response.request().newBuilder()
                    .header("Authorization", "Bearer " + newAuth.access_token)
                    .build();
        }

        sessionManager.logout();
        return null;
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {result++;}

        return result;
    }
}