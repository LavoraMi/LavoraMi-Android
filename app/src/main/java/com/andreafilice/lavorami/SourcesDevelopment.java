package com.andreafilice.lavorami;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;
import android.widget.ImageButton;

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

        //*LOCK THE ORIENTATION
        /// In this section of the code, we will block the orientation to PORTRAIT because in LANDSCAPE LavoraMi is not supported.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //*BUTTONS
        /// In this section of the code, we initialize the end-screen buttons for the redirects.
        MaterialButton btnReportBug = findViewById(R.id.btnReportBug);
        MaterialButton btnWebsite = findViewById(R.id.btnWebsite);
        //MaterialButton btnPatreon = findViewById(R.id.btnPatreon);
        //MaterialButton btnCofee = findViewById(R.id.btnCofee);
        MaterialButton btnShare = findViewById(R.id.btnShare);
        MaterialButton btnRequestDatas = findViewById(R.id.btnRequestDatas);
        MaterialButton btnInstagram = findViewById(R.id.btnInstagram);
        MaterialButton btnTikTok = findViewById(R.id.btnTikTok);
        MaterialButton btnRiconoscimenti = findViewById(R.id.btnLibraries);
        MaterialButton btnPrivacyPolicy = findViewById(R.id.btnPrivacyPolicy);
        MaterialButton btnTermsOfService = findViewById(R.id.btnTermsService);
        ImageButton btnBack = findViewById(R.id.backBtn);

        btnReportBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = ContextCompat.getString(SourcesDevelopment.this, R.string.bugIntentSubject);
                String body = String.format(ContextCompat.getString(SourcesDevelopment.this, R.string.bugIntentBody), Build.DEVICE, Build.VERSION.RELEASE, Build.MODEL, Build.MANUFACTURER);

                String encodedBody = Uri.encode(body);
                String mailtoUri = "mailto:info@lavorami.it?subject=" + Uri.encode(subject) + "&body=" + encodedBody;

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(mailtoUri));

                try {startActivity(Intent.createChooser(intent, "Invia richiesta dati"));}
                catch (Exception e) {e.printStackTrace();}
            }
        });

        btnInstagram.setOnClickListener(v -> ActivityManager.openURL(this, "https://www.instagram.com/lavoramiapp_official/"));
        btnTikTok.setOnClickListener(v -> ActivityManager.openURL(this, "https://www.tiktok.com/@applavorami.official"));
        //btnPatreon.setOnClickListener(v -> ActivityManager.openURLWithTabBuilder(this, "https://www.patreon.com/cw/LavoraMi"));
        //btnCofee.setOnClickListener(v -> ActivityManager.openURLWithTabBuilder(this, "https://www.buymeacoffee.com/lavorami"));
        btnPrivacyPolicy.setOnClickListener(v -> ActivityManager.openURLWithTabBuilder(this, "https://lavorami.it/privacypolicy"));
        btnTermsOfService.setOnClickListener(v -> ActivityManager.openURLWithTabBuilder(this, "https://lavorami.it/termsofservice"));
        btnRequestDatas.setOnClickListener(v -> ActivityManager.changeActivity(this, RequestUserDatas.class));
        btnBack.setOnClickListener(v -> ActivityManager.changeActivity(this, SettingsActivity.class));
        btnRiconoscimenti.setOnClickListener(v ->{ActivityManager.changeActivity(this, LibrariesActivity.class);});
        btnWebsite.setOnClickListener(v -> ActivityManager.openURLWithTabBuilder(this, "https://lavorami.it"));

        btnShare.setOnClickListener(v -> {
            String packageName = getPackageName();
            String link = "https://play.google.com/store/apps/details?id=" + packageName;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "LavoraMi");
            intent.putExtra(Intent.EXTRA_TEXT, "Scarica LavoraMi: " + link);

            startActivity(Intent.createChooser(intent, "Condividi LavoraMi"));
        });
    }
}