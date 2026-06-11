package com.andreafilice.lavorami;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatabaseDataPreferences extends AppCompatActivity {

    private Switch switchFavorites;
    private Switch switchYourLines;
    private SessionManager sessionManager;
    private SupabaseDataManager supabaseDataManager;
    LinearLayout loaderToggle;
    SupabaseAPI api;
    Retrofit retrofitAPI;
    private String SupabaseANON, SupabaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_database_data_preferences);

        //*BUTTONS
        /// In this section of the code, we setup the Back button action.
        findViewById(R.id.backBtn).setOnClickListener(v -> finish());
        loaderToggle = findViewById(R.id.loaderToggle);

        //*GET METADATA
        /// In this section of the code, we initialize the SupabaseURL and SupabaseANON variables for performance boost.
        SupabaseANON = getMetaData("supabaseANON");
        SupabaseURL = getMetaData("supabaseURL");

        /// In this section of the code, we initialize the Supabase Server from the keys of the .env file.
        if(SupabaseURL != null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .authenticator(new SupabaseAuthenticator(this, SupabaseANON, SupabaseURL))
                    .build();

            retrofitAPI = new Retrofit.Builder()
                    .baseUrl(SupabaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            api = retrofitAPI.create(SupabaseAPI.class);
        }
        else
            Toast.makeText(this, getString(R.string.connectionErrorToast), Toast.LENGTH_SHORT).show();

        switchFavorites = findViewById(R.id.switchFavoriteLines);
        switchYourLines = findViewById(R.id.switchYourLines);

        sessionManager = new SessionManager(this);

        if (sessionManager.isLoggedIn()) {
            supabaseDataManager = new SupabaseDataManager(
                    this,
                    api,
                    SupabaseANON,
                    sessionManager.getToken(),
                    sessionManager.getUserEmail()
            );

            loadUserPreferences();
        }
        else
            finish();
    }

    private String getMetaData(String key){
        /// This function get from our AndroidManifest.xml the values of .env files.
        /// @PARAMETER
        /// String key is the actual key of the value that we need to grab from the manifest file.

        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = info.metaData;

            if(bundle != null)
                return bundle.getString(key);
        }
        catch (PackageManager.NameNotFoundException e) {Toast.makeText(this, getString(R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();}
        return null;
    }

    private void loadUserPreferences() {
        supabaseDataManager.fetchUserPreferences(new SupabaseDataManager.DataCallback<SupabaseModels.UserPreferencesDatas>() {
            @Override
            public void onSuccess(SupabaseModels.UserPreferencesDatas result) {
                switchFavorites.setOnCheckedChangeListener(null);
                switchYourLines.setOnCheckedChangeListener(null);

                switchFavorites.setChecked(result.enable_favorites);
                switchYourLines.setChecked(result.enable_your_lines);

                DataManager.saveBoolData(DataKeys.KEY_SAVE_DB_FAVORITES, result.enable_favorites);
                DataManager.saveBoolData(DataKeys.KEY_SAVE_DB_YOUR_LINES, result.enable_your_lines);

                switchFavorites.setTrackTintMode((result.enable_favorites) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
                switchYourLines.setTrackTintMode((result.enable_your_lines) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
                switchFavorites.setEnabled(true);
                switchYourLines.setEnabled(true);

                setupSwitchListeners();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(DatabaseDataPreferences.this, getString(R.string.connectionErrorToast), Toast.LENGTH_SHORT).show();
                setupSwitchListeners();
            }
        });
    }


    private void setupSwitchListeners() {
        CompoundButton.OnCheckedChangeListener changeListener = (buttonView, isChecked) -> {
            ActivityUtils.triggerFeedback(DatabaseDataPreferences.this);
            switchYourLines.setTrackTintMode((switchYourLines.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
            switchFavorites.setTrackTintMode((switchFavorites.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
            saveUserPreferences();
        };

        switchFavorites.setOnCheckedChangeListener(changeListener);
        switchYourLines.setOnCheckedChangeListener(changeListener);

        loaderToggle.setVisibility(View.GONE);
    }

    private void saveUserPreferences() {
        boolean favEnabled = switchFavorites.isChecked();
        boolean linesEnabled = switchYourLines.isChecked();

        supabaseDataManager.saveUserPreferences(favEnabled, linesEnabled, new SupabaseDataManager.DataCallback<Void>() {
            @Override
            public void onSuccess(Void result) {Log.d("PREFS_ACTIVITY", "Preferenze aggiornate correttamente nel database.");}

            @Override
            public void onError(String error) {
                Log.e("PREFS_ACTIVITY", "Errore durante il salvataggio istantaneo: " + error);
                Toast.makeText(DatabaseDataPreferences.this, getString(R.string.syncErrorMessageToast), Toast.LENGTH_SHORT).show();
            }
        });
    }
}