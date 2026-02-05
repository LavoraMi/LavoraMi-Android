package com.andreafilice.lavorami;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class SourcesDevelopment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sources_development);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //*BUTTONS
        MaterialButton btnReportBug = findViewById(R.id.btnReportBug);
        MaterialButton btnWebsite = findViewById(R.id.btnWebsite);
        MaterialButton btnPatreon = findViewById(R.id.btnPatreon);
        ImageButton btnBack = (ImageButton) findViewById(R.id.backBtn);

        btnReportBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Segnalazione Bug LavoraMI";

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:info@lavorami.it"));
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);

                try {
                    startActivity(Intent.createChooser(intent, "Invia segnalazione bug"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnWebsite.setOnClickListener(v -> {ActivityManager.openURL(this, "https://lavorami.it");});
        btnPatreon.setOnClickListener(v -> {ActivityManager.openURL(this, "https://www.patreon.com/cw/LavoraMi");});
        btnBack.setOnClickListener(v -> {ActivityManager.changeActivity(this, SettingsActivity.class);});
    }
}