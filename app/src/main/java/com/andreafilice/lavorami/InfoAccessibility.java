package com.andreafilice.lavorami;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class InfoAccessibility extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_accessibility);

        ImageButton btnDismiss = findViewById(R.id.backBtn);
        btnDismiss.setOnClickListener(v -> finish());
    }
}