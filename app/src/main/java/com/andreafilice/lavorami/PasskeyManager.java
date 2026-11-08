package com.andreafilice.lavorami.supabase;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.credentials.Credential;
import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.CreatePublicKeyCredentialResponse;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.GetPublicKeyCredentialOption;
import androidx.credentials.PublicKeyCredential;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.NoCredentialException;

import com.andreafilice.lavorami.SessionManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasskeyManager {
    public interface Result<T> {
        void onSuccess(T result);
        void onError(String error);
    }

    public static final String ERROR_CANCELLED = "CANCELLED";
    public static final String ERROR_NO_CREDENTIAL = "NO_CREDENTIAL";

    private final Context context;
    private final SupabaseAPI api;
    private final String apiKey;
    private final CredentialManager credentialManager;

    public PasskeyManager(Context ctx, SupabaseAPI api, String apiKey) {
        this.context = ctx.getApplicationContext();
        this.api = api;
        this.apiKey = apiKey;
        this.credentialManager = CredentialManager.create(this.context);
    }

    public void register(Activity activity, Result<Void> callback) {
        String token = new SessionManager(context).getToken();

        if (token == null || token.isEmpty()) {
            callback.onError("Sessione non valida");
            return;
        }

        String bearer = "Bearer " + token;

        api.passkeyRegistrationOptions(apiKey, bearer).enqueue(new Callback<SupabaseModels.PasskeyChallenge>() {
            @Override
            public void onResponse(Call<SupabaseModels.PasskeyChallenge> call, Response<SupabaseModels.PasskeyChallenge> response) {
                if (!response.isSuccessful() || response.body() == null || response.body().options == null) {
                    callback.onError(errorCode(response));
                    return;
                }

                SupabaseModels.PasskeyChallenge challenge = response.body();
                CreatePublicKeyCredentialRequest request = new CreatePublicKeyCredentialRequest(challenge.options.toString());

                credentialManager.createCredentialAsync(activity, request, null, ContextCompat.getMainExecutor(activity), new CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException>() {
                    @Override
                    public void onResult(CreateCredentialResponse result) {
                        if (!(result instanceof CreatePublicKeyCredentialResponse)) {
                            callback.onError("Risposta non valida");
                            return;
                        }

                        String json = ((CreatePublicKeyCredentialResponse) result).getRegistrationResponseJson();
                        JsonObject credential = JsonParser.parseString(json).getAsJsonObject();

                        api.passkeyRegistrationVerify(apiKey, bearer, new SupabaseModels.PasskeyVerifyRequest(challenge.challenge_id, credential))
                            .enqueue(new Callback<SupabaseModels.PasskeyItem>() {
                                @Override
                                public void onResponse(Call<SupabaseModels.PasskeyItem> call, Response<SupabaseModels.PasskeyItem> response) {
                                    if (response.isSuccessful()) callback.onSuccess(null);
                                    else callback.onError(errorCode(response));
                                }

                                @Override
                                public void onFailure(Call<SupabaseModels.PasskeyItem> call, Throwable throwable) {callback.onError(String.valueOf(throwable.getMessage()));}
                            });
                    }

                    @Override
                    public void onError(@NonNull CreateCredentialException exception) {
                        if (exception instanceof CreateCredentialCancellationException) callback.onError(ERROR_CANCELLED);
                        else callback.onError(exception.getClass().getSimpleName() + ": " + exception.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<SupabaseModels.PasskeyChallenge> call, Throwable t) {
                callback.onError(String.valueOf(t.getMessage()));
            }
        });
    }

    public void signIn(Activity activity, Result<SupabaseModels.AuthResponse> callback) {
        api.passkeyAuthenticationOptions(apiKey).enqueue(new Callback<SupabaseModels.PasskeyChallenge>() {
            @Override
            public void onResponse(Call<SupabaseModels.PasskeyChallenge> call, Response<SupabaseModels.PasskeyChallenge> response) {
                if (!response.isSuccessful() || response.body() == null || response.body().options == null) {
                    callback.onError(errorCode(response));
                    return;
                }

                SupabaseModels.PasskeyChallenge challenge = response.body();
                GetCredentialRequest request = new GetCredentialRequest.Builder()
                    .addCredentialOption(new GetPublicKeyCredentialOption(challenge.options.toString()))
                    .build();

                credentialManager.getCredentialAsync(activity, request, null, ContextCompat.getMainExecutor(activity), new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() {
                    @Override
                    public void onResult(GetCredentialResponse result) {
                        Credential credential = result.getCredential();
                        if (!(credential instanceof PublicKeyCredential)) {
                            callback.onError("Credenziale non valida");
                            return;
                        }

                        JsonObject json = JsonParser.parseString(
                                ((PublicKeyCredential) credential).getAuthenticationResponseJson()).getAsJsonObject();

                        api.passkeyAuthenticationVerify(apiKey, new SupabaseModels.PasskeyVerifyRequest(challenge.challenge_id, json))
                            .enqueue(new Callback<SupabaseModels.AuthResponse>() {
                                @Override
                                public void onResponse(Call<SupabaseModels.AuthResponse> call, Response<SupabaseModels.AuthResponse> response) {
                                    if (response.isSuccessful() && response.body() != null) callback.onSuccess(response.body());
                                    else callback.onError(errorCode(response));
                                }

                                @Override
                                public void onFailure(Call<SupabaseModels.AuthResponse> call, Throwable throwable) {callback.onError(String.valueOf(throwable.getMessage()));}
                            });
                    }

                    @Override
                    public void onError(@NonNull GetCredentialException exception) {
                        if (exception instanceof GetCredentialCancellationException) callback.onError(ERROR_CANCELLED);
                        else if (exception instanceof NoCredentialException) callback.onError(ERROR_NO_CREDENTIAL);
                        else callback.onError(exception.getClass().getSimpleName() + ": " + exception.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<SupabaseModels.PasskeyChallenge> call, Throwable t) {
                callback.onError(String.valueOf(t.getMessage()));
            }
        });
    }

    private String errorCode(Response<?> response) {
        try {
            if (response.errorBody() != null) {
                JsonObject obj = JsonParser.parseString(response.errorBody().string()).getAsJsonObject();

                if (obj.has("error_code") && !obj.get("error_code").isJsonNull())
                    return obj.get("error_code").getAsString();
            }
        }
        catch (Exception ignored) {}

        return "http_" + response.code();
    }
}