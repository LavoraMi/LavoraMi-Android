package com.andreafilice.lavorami;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.shimmer.BuildConfig;
import com.google.gson.internal.GsonBuildConfig;

import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountManagement extends AppCompatActivity {
    SessionManager sessionManager;
    SupabaseAPI api;
    LinearLayout loginView;
    LinearLayout loggedInView;
    LinearLayout signUpView;
    TextView fullNameTextLoginPage;
    TextView tvProfileName;
    TextView tvProfileEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionManager = new SessionManager(this);

        String supabaseURL = getMetaData("supabaseURL");
        if(supabaseURL != null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(supabaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            api = retrofit.create(SupabaseAPI.class);
        }
        else
            Toast.makeText(this, "ERRORE DURANTE L'INIZIALIZZAZIONE DI SUPABASE.", Toast.LENGTH_SHORT).show();

        ImageView backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            ActivityManager.changeActivity(this, SettingsActivity.class);
        });

        //*VIEWS
        loginView = findViewById(R.id.login_view_container);
        loggedInView = findViewById(R.id.profile_view_container);
        signUpView = findViewById(R.id.signup_view_container);

        //*LOGIN VIEW
        TextView tvGoToSignup = findViewById(R.id.tvGoToSignup);
        tvGoToSignup.setOnClickListener(v -> {
            loginView.setVisibility(View.GONE);
            loggedInView.setVisibility(View.GONE);
            signUpView.setVisibility(View.VISIBLE);
        });

        EditText emailLogin = findViewById(R.id.etLoginEmail);
        EditText passwordLogin = findViewById(R.id.etLoginPassword);
        CardView btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            login(emailLogin.getText().toString(), passwordLogin.getText().toString());
        });

        //*LOGGED IN VIEW
        fullNameTextLoginPage = findViewById(R.id.tvProfileWelcome);
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileEmail = findViewById(R.id.tvProfileEmail);
        CardView btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            logout();
        });

        //*SIGNUP VIEW
        TextView tvGoToLogin = findViewById(R.id.tvGoToLogin);
        tvGoToLogin.setOnClickListener(v -> {
            loginView.setVisibility(View.VISIBLE);
            loggedInView.setVisibility(View.GONE);
            signUpView.setVisibility(View.GONE);
        });

        EditText emailSignUp = findViewById(R.id.etSignupEmail);
        EditText passwordSignUp = findViewById(R.id.etSignupPassword);
        EditText nameSignUp = findViewById(R.id.etSignupName);
        CardView btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(v -> {
            signUp(nameSignUp.getText().toString(), emailSignUp.getText().toString(), passwordSignUp.getText().toString());
        });

        //*SET THE BASE VIEW
        updateUI();
    }

    private void login(String email, String password) {
        SupabaseModels.AuthRequest req = new SupabaseModels.AuthRequest(email, password);

        api.login(getMetaData("supabaseANON"), req).enqueue(new Callback<SupabaseModels.AuthResponse>() {
            @Override
            public void onResponse(Call<SupabaseModels.AuthResponse> call, Response<SupabaseModels.AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().access_token;
                    String email = response.body().user.email;

                    String nameUser = "Utente";

                    if (response.body().user.userMetadata != null) {
                        if (response.body().user.userMetadata.containsKey("full_name"))
                            nameUser = response.body().user.userMetadata.get("full_name").toString();
                        else if (response.body().user.userMetadata.containsKey("name"))
                            nameUser = response.body().user.userMetadata.get("name").toString();
                        else if (response.body().user.userMetadata.containsKey("display_name"))
                            nameUser = response.body().user.userMetadata.get("display_name").toString();
                    }

                    sessionManager.saveSession(token, email, nameUser);

                    updateUI();
                    Toast.makeText(AccountManagement.this, "Bentornato!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(AccountManagement.this, "Errore Login: Credenziali errate", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SupabaseModels.AuthResponse> call, Throwable t) {
                Toast.makeText(AccountManagement.this, "Errore Rete: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signUp(String name, String email, String password){
        Map<String, Object> userMetadata = new HashMap<>();
        userMetadata.put("full_name", name);

        SupabaseModels.AuthRequest req = new SupabaseModels.AuthRequest(email, password, userMetadata);

        api.signup(getMetaData("supabaseANON"), req).enqueue(new Callback<Void>(){

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AccountManagement.this, "Registrazione COMPLETATA!", Toast.LENGTH_SHORT).show();
                    updateUI();
                }
                else{
                    Toast.makeText(AccountManagement.this, "ERRORE REGISTRAZIONE.", Toast.LENGTH_SHORT).show();
                    Log.d("ERRORE_REG", response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AccountManagement.this, "Errore Rete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logout(){
        String token = sessionManager.getToken();

        if(token == null){
            sessionManager.logout();
            return;
        }

        api.logout(getMetaData("supabaseANON"), "Bearer " + token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                sessionManager.logout();
                Toast.makeText(AccountManagement.this, "Account disconnesso!", Toast.LENGTH_SHORT).show();
                updateUI();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AccountManagement.this, "Errore durante la disconnesione", Toast.LENGTH_SHORT).show();
                Log.d("DISCONNECTING_ERROR", t.getMessage());
            }
        });
    }

    private void updateUI(){
        if(sessionManager.isLoggedIn()){
            loginView.setVisibility(View.GONE);
            signUpView.setVisibility(View.GONE);
            loggedInView.setVisibility(View.VISIBLE);

            fullNameTextLoginPage.setText("Ciao " + sessionManager.getUserName());
            tvProfileName.setText(sessionManager.getUserName());
            tvProfileEmail.setText(sessionManager.getUserEmail());
        }
        else{
            loginView.setVisibility(View.VISIBLE);
            signUpView.setVisibility(View.GONE);
            loggedInView.setVisibility(View.GONE);
        }
    }

    private String getMetaData(String key){
        try{
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = info.metaData;

            if(bundle != null)
                return bundle.getString(key);
        }
        catch (PackageManager.NameNotFoundException e){
            Log.d("ERROR", "Impossibile trovare questo valore. ERR: " + e.getMessage());
        }
        return null;
    }
}