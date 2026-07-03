package com.andreafilice.lavorami;

import static com.andreafilice.lavorami.ActivityUtils.getMetaData;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeUsername extends AppCompatActivity {
    //* VARIABLES
    SessionManager sessionManager;
    SupabaseAPI api;
    SupabaseDataManager dataManager;
    Retrofit retrofitAPI;
    private String SupabaseANON, SupabaseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_username);

        //*GET METADATA
        /// In this section of the code, we initialize the SupabaseURL and SupabaseANON variables for performance boost.
        SupabaseANON = getMetaData(this, "supabaseANON");
        SupabaseURL = getMetaData(this, "supabaseURL");

        ///In this section of the code, we initialize the SessionManager and the DataManager for save the credentials of the Logged Account.
        sessionManager = new SessionManager(this);
        dataManager = new SupabaseDataManager(this, api, SupabaseANON, "", "");

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

        if(sessionManager.isLoggedIn()) {
            dataManager.setUserEmail(sessionManager.getUserEmail());
            dataManager.setBearerToken(sessionManager.getToken());
        }

        //* LISTENERS
        /// In this section of the code, we will setup the listeners for the "Continue" button
        LinearLayout buttonContinue = findViewById(R.id.buttonContinue);
        TextInputEditText usernameEditText = findViewById(R.id.usernameEditText);

        buttonContinue.setOnClickListener(v -> performUsernameUpdate(usernameEditText.getText().toString()));
    }

    public void performUsernameUpdate(String newFullName) {
        /// This method updates the user's full name in Supabase.
        /// @PARAMETERS
        /// String newFullName is the new full name to set for the user.
        /// @CALLS updateUsername endpoint from SupabaseAPI with proper authentication.
        /// @CALLBACKS Shows success/error Toast messages and updates UI accordingly.

        if(!sessionManager.isLoggedIn()) {
            Toast.makeText(this, getString(R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();
            return;
        }

        String bearerToken = "Bearer " + sessionManager.getToken();
        SupabaseModels.UpdateUserRequest request = new SupabaseModels.UpdateUserRequest(newFullName);

        api.updateUsername(SupabaseANON, bearerToken, request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(ChangeUsername.this, getString(R.string.usernameUpdated), Toast.LENGTH_SHORT).show();

                    sessionManager.saveSession(sessionManager.getToken(), sessionManager.getRefreshToken(), sessionManager.getUserEmail(), newFullName, sessionManager.isLoggedInWithGoogle());

                    finish();
                }
                else Toast.makeText(ChangeUsername.this, getString(R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {Toast.makeText(ChangeUsername.this, getString(R.string.connectionErrorToast), Toast.LENGTH_SHORT).show();}
        });
    }
}
