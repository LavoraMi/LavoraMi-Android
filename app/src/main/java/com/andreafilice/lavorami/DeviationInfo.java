package com.andreafilice.lavorami;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DeviationInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deviation_info);

        ImageButton btnDismiss = findViewById(R.id.backBtn);
        btnDismiss.setOnClickListener(v -> finish());
    }
}