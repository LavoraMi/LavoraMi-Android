package com.andreafilice.lavorami;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.Call;
import retrofit2.http.Query;

public interface SupabaseAPI {
    @POST("auth/v1/signup")
    Call<Void> signup(
        @Header("apikey") String apiKey,
        @Body SupabaseModels.AuthRequest request
    );

    @POST("auth/v1/token?grant_type=password")
    Call<SupabaseModels.AuthResponse> login(
        @Header("apikey") String apiKey,
        @Body SupabaseModels.AuthRequest request
    );

    @POST("auth/v1/logout")
    Call<Void> logout(
        @Header("apikey") String apiKey,
        @Header("Authorization") String bearerToken
    );

    @POST("auth/v1/token?grant_type=refresh_token")
    Call<SupabaseModels.AuthResponse> refreshToken(
        @Header("apikey") String apiKey,
        @Body SupabaseModels.RefreshTokenRequest request
    );

    @POST("rest/v1/rpc/delete_self_dev_test")
    Call<Void> deleteAccount(
        @Header("apikey") String apiKey,
        @Header("Authorization") String bearerToken
    );

    @POST("auth/v1/token?grant_type=id_token")
    Call<SupabaseModels.AuthResponse> loginWithGoogle(
        @Header("apikey") String apiKey,
        @Body SupabaseModels.GoogleLoginRequest request
    );

    @POST("auth/v1/recover")
    Call<Void> resetPassword(
        @Header("apikey") String apiKey,
        @Body SupabaseModels.ResetPasswordRequest request
    );

    @POST("rest/v1/userDatas")
    Call<Void> upsertUserLines(
        @Header("apikey") String apiKey,
        @Header("Authorization") String bearerToken,
        @Header("Prefer") String prefer,
        @Body SupabaseModels.LinesFavoriteDatas data
    );

    @GET("rest/v1/userDatas")
    Call<java.util.ArrayList<SupabaseModels.LinesFavoriteDatas>> fetchUserLines(
        @Header("apikey") String apiKey,
        @Header("Authorization") String bearerToken,
        @Query("user_email") String userEmail,
        @Query("select") String select
    );

    @POST("rest/v1/userPreferences")
    Call<Void> upsertUserPreferences(
        @Header("apikey") String apiKey,
        @Header("Authorization") String bearerToken,
        @Header("Prefer") String prefer,
        @Body SupabaseModels.UserPreferencesDatas preferences
    );

    @GET("rest/v1/userPreferences")
    Call<java.util.ArrayList<SupabaseModels.UserPreferencesDatas>> fetchUserPreferences(
        @Header("apikey") String apiKey,
        @Header("Authorization") String bearerToken,
        @Query("user_email") String userEmail,
        @Query("select") String select
    );
}
