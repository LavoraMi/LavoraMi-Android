package com.andreafilice.lavorami;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import static com.andreafilice.lavorami.ActivityUtils.getLocalizedString;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MaintenanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maintenance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {}
        });

        //*SETUP DETAILS
        /// Get the UI setted-up with details and more.
        TextView maintenanceDeps = findViewById(R.id.maintenanceDeps);
        maintenanceDeps.setText(MainActivity.maintenanceDetails);

        Button btnRefreshOnError = findViewById(R.id.btnRefreshOnError);
        btnRefreshOnError.setOnClickListener(v -> updateDatas());
    }

    public void updateDatas() {
        /// In this section of the code, we GET the 'requirements.json' file from our CDN and load the Requirements Configuration.
        Button btnRefreshOnError = findViewById(R.id.btnRefreshOnError);
        btnRefreshOnError.setBackgroundColor(ContextCompat.getColor(MaintenanceActivity.this, R.color.GRAY));

        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("cdn.lavorami.it", "sha256/VMw18sAhS/3iF/FDknmBakQ123t+OXJqqVG9NWkti/o=")
                .build();

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cdn.lavorami.it/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        APIWorks apiworks = retrofit.create(APIWorks.class);

        /// Get the JSON from the CDN server
        apiworks.getRequirements().enqueue(new Callback<RequirementsDescriptor>() {
            @Override
            public void onResponse(Call<RequirementsDescriptor> call, Response<RequirementsDescriptor> response) {
                if(response.isSuccessful()){
                    String versionMinimum = response.body().getMinimumVersionAndroid();
                    String currentVersion = ContextCompat.getString(MaintenanceActivity.this, R.string.app_version);

                    int responseComparable = RequirementsDescriptor.compareSemanticVersions(currentVersion, versionMinimum);

                    //*CHECK FOR MAINTENANCE
                    /// Check if LavoraMi is in maintenance mode and get the details from the CDN.
                    String savedLang = DataManager.getStringData(MaintenanceActivity.this, DataKeys.KEY_DEFAULT_LANGUAGE, "🇮🇹 Italiano");
                    String langCode = savedLang.contains("English") ? "en" : "it";

                    boolean isInMaintenanceMode = response.body().isMaintenanceEnabled();
                    String maintenanceDetails = (langCode.equalsIgnoreCase("en")) ? response.body().getMaintenanceDepsEnglish() : response.body().getMaintenanceDeps();

                    TextView maintenanceDeps = findViewById(R.id.maintenanceDeps);
                    maintenanceDeps.setText(maintenanceDetails);

                    if(isInMaintenanceMode)
                        Toast.makeText(MaintenanceActivity.this, "LavoraMi è ancora in manutenzione.", Toast.LENGTH_SHORT).show();
                    else
                        ActivityUtils.changeActivity(MaintenanceActivity.this, MainActivity.class);

                    if(responseComparable < 0){
                        new AlertDialog.Builder(MaintenanceActivity.this)
                                .setTitle(getLocalizedString(MaintenanceActivity.this, R.string.newVersionAvailableTitle))
                                .setMessage(getLocalizedString(MaintenanceActivity.this, R.string.maintenanceDeps))
                                .setPositiveButton(getLocalizedString(MaintenanceActivity.this, R.string.updateButton), ((dialog, which) -> {
                                    String packageName = getPackageName();
                                    String link = "https://play.google.com/store/apps/details?id=" + packageName;

                                    ActivityUtils.openURL(MaintenanceActivity.this, link);
                                }))
                                .setCancelable(false)
                                .create()
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RequirementsDescriptor> call, Throwable t) {
                Log.d("REQUIREMENTS_GET", "Errore durante il retrieve del Requirements.json");
            }
        });

        btnRefreshOnError.setBackgroundColor(ContextCompat.getColor(MaintenanceActivity.this, R.color.redMetro));
    }
}