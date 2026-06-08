package com.andreafilice.lavorami;

import android.content.Context;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.ArrayList;

public class SupabaseDataManager {
    private Context context;
    private SupabaseAPI api;
    private String supabaseANON;
    private String bearerToken;
    private String userEmail;

    public interface DataCallback<T> {
        void onSuccess(T result);
        void onError(String error);
    }

    public SupabaseDataManager(Context context, SupabaseAPI api, String supabaseANON, String bearerToken, String userEmail) {
        this.context = context;
        this.api = api;
        this.supabaseANON = supabaseANON;
        this.bearerToken = "Bearer " + bearerToken;
        this.userEmail = userEmail;
    }

    public void saveFavoritesAndLines(ArrayList<String> favorites, ArrayList<String> yourLines, DataCallback<Void> callback) {
        SupabaseModels.LinesFavoriteDatas linesToSave = new SupabaseModels.LinesFavoriteDatas(userEmail, favorites, yourLines);

        insertDataToDB(linesToSave, callback);
    }

    private void insertDataToDB(SupabaseModels.LinesFavoriteDatas linesToSave, DataCallback<Void> callback) {
        if (userEmail == null || userEmail.isEmpty()) {
            Log.e("DataManager", "ERRORE: Email vuota.");
            if (callback != null)
                callback.onError("Email vuota");
            return;
        }

        Call<Void> call = api.upsertUserLines(supabaseANON, bearerToken, "resolution=merge-duplicates", linesToSave);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("DataManager", "Dati salvati con successo nel database.");
                    if (callback != null)
                        callback.onSuccess(null);
                }
                else {
                    Log.e("DataManager", "Errore durante l'upsert: " + response.code());
                    if (callback != null)
                        callback.onError("Errore durante il salvataggio: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("DataManager", "Errore di rete durante l'upsert: " + t.getMessage());
                if (callback != null)
                    callback.onError("Errore di rete: " + t.getMessage());
            }
        });
    }

    public void saveUserPreferences(boolean enableFavorites, boolean enableYourLines, DataCallback<Void> callback) {
        SupabaseModels.UserPreferencesDatas preferencesToSave = new SupabaseModels.UserPreferencesDatas(userEmail, enableFavorites, enableYourLines);

        if (userEmail == null || userEmail.isEmpty()) {
            Log.e("DataManager", "ERRORE: Email vuota.");
            if (callback != null)
                callback.onError("Email vuota");
            return;
        }

        Call<Void> call = api.upsertUserPreferences(supabaseANON, bearerToken, preferencesToSave);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("DataManager", "Preferenze salvate con successo.");
                    if (callback != null)
                        callback.onSuccess(null);
                }
                else {
                    Log.e("DataManager", "Errore durante il salvataggio delle preferenze: " + response.code());
                    if (callback != null)
                        callback.onError("Errore durante il salvataggio: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("DataManager", "Errore di rete durante il salvataggio: " + t.getMessage());
                if (callback != null)
                    callback.onError("Errore di rete: " + t.getMessage());
            }
        });
    }

    public void fetchUserFavorites(DataCallback<ArrayList<String>> callback) {
        Call<ArrayList<SupabaseModels.LinesFavoriteDatas>> call = api.fetchUserLines(supabaseANON, bearerToken, userEmail, "*");

        call.enqueue(new Callback<ArrayList<SupabaseModels.LinesFavoriteDatas>>() {
            @Override
            public void onResponse(Call<ArrayList<SupabaseModels.LinesFavoriteDatas>> call, Response<ArrayList<SupabaseModels.LinesFavoriteDatas>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    ArrayList<String> favorites = response.body().get(0).lines;
                    Log.d("DataManager", "Favorites recuperati: " + favorites.size() + " linee");
                    if (callback != null)
                        callback.onSuccess(favorites != null ? favorites : new ArrayList<>());
                }
                else {
                    Log.d("DataManager", "Nessun dato trovato per le favorites");
                    if (callback != null)
                        callback.onSuccess(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SupabaseModels.LinesFavoriteDatas>> call, Throwable t) {
                Log.e("DataManager", "Errore nel fetch delle favorites: " + t.getMessage());
                if (callback != null)
                    callback.onError("Errore di rete: " + t.getMessage());
            }
        });
    }

    public void fetchUserCustomLines(DataCallback<ArrayList<String>> callback) {
        Call<ArrayList<SupabaseModels.LinesFavoriteDatas>> call = api.fetchUserLines(supabaseANON, bearerToken, userEmail, "*");

        call.enqueue(new Callback<ArrayList<SupabaseModels.LinesFavoriteDatas>>() {
            @Override
            public void onResponse(Call<ArrayList<SupabaseModels.LinesFavoriteDatas>> call, Response<ArrayList<SupabaseModels.LinesFavoriteDatas>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    ArrayList<String> yourLines = response.body().get(0).your_lines;
                    Log.d("DataManager", "Custom lines recuperate: " + yourLines.size() + " linee");
                    if (callback != null)
                        callback.onSuccess(yourLines != null ? yourLines : new ArrayList<>());
                }
                else {
                    Log.d("DataManager", "Nessun dato trovato per le custom lines");
                    if (callback != null)
                        callback.onSuccess(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SupabaseModels.LinesFavoriteDatas>> call, Throwable t) {
                Log.e("DataManager", "Errore nel fetch delle custom lines: " + t.getMessage());
                if (callback != null)
                    callback.onError("Errore di rete: " + t.getMessage());
            }
        });
    }

    public void fetchUserPreferences(DataCallback<SupabaseModels.UserPreferencesDatas> callback) {
        Call<ArrayList<SupabaseModels.UserPreferencesDatas>> call = api.fetchUserPreferences(supabaseANON, bearerToken, userEmail, "*");

        call.enqueue(new Callback<ArrayList<SupabaseModels.UserPreferencesDatas>>() {
            @Override
            public void onResponse(Call<ArrayList<SupabaseModels.UserPreferencesDatas>> call, Response<ArrayList<SupabaseModels.UserPreferencesDatas>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    SupabaseModels.UserPreferencesDatas preferences = response.body().get(0);
                    Log.d("DataManager", "Preferenze recuperate per l'utente");
                    if (callback != null)
                        callback.onSuccess(preferences);
                }
                else {
                    SupabaseModels.UserPreferencesDatas defaultPrefs = new SupabaseModels.UserPreferencesDatas(userEmail, true, true);
                    Log.d("DataManager", "Preferenze non trovate, usando default");
                    if (callback != null)
                        callback.onSuccess(defaultPrefs);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SupabaseModels.UserPreferencesDatas>> call, Throwable t) {
                Log.e("DataManager", "Errore nel fetch delle preferenze: " + t.getMessage());
                if (callback != null)
                    callback.onError("Errore di rete: " + t.getMessage());
            }
        });
    }

    public void setUserEmail(String email) {this.userEmail = email;}
    public void setBearerToken(String token) {this.bearerToken = "Bearer " + token;}
}