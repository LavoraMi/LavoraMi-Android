package com.andreafilice.lavorami;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObsoleteVersion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_obsolete_version);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnRefreshOnError = findViewById(R.id.btnRefreshOnError);
        TextView latestVersionSupported = findViewById(R.id.latestVersionSupported);

        btnRefreshOnError.setOnClickListener(v -> {
            String packageName = getPackageName();
            String link = "https://play.google.com/store/apps/details?id=" + packageName;

            ActivityUtils.openURL(this, link);
        });

        APIWorks apiworks = RetrofitManager.get().create(APIWorks.class);

        apiworks.getRequirements().enqueue(new Callback<RequirementsDescriptor> () {
            @Override
            public void onResponse(Call<RequirementsDescriptor> call, Response<RequirementsDescriptor> response) {
                if(response.isSuccessful())
                    latestVersionSupported.setText(getString(R.string.latestVersionSupported) + response.body().getMinimumVersionAndroid());
            }

            @Override
            public void onFailure(Call<RequirementsDescriptor> call, Throwable t) {Log.d("REQUIREMENTS_GET", "Errore durante il retrieve del Requirements.json");}
        });
    }
}