package com.andreafilice.lavorami;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lines);
        LinearLayout containerMetro = findViewById(R.id.groupMetro);
        LinearLayout containerSub = findViewById(R.id.groupSub);
        EditText searchLines = findViewById(R.id.editSearch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // AGGIUNGI LE METRO
        aggiungiLinea(containerMetro, "M1", R.color.M1, "Metro M1");
        aggiungiLinea(containerMetro, "M2", R.color.M2, "Metro M2");
        aggiungiLinea(containerMetro, "M3", R.color.M3, "Metro M3");
        aggiungiLinea(containerMetro, "M4", R.color.M4, "Metro M4");
        aggiungiLinea(containerMetro, "M5", R.color.M5, "Metro M5");

        // AGGIUNGI LE SUBURBANE
        aggiungiLinea(containerSub, "S1", R.color.S1, "Suburbano S1");
        aggiungiLinea(containerSub, "S2", R.color.S2, "Suburbano S2");
        aggiungiLinea(containerSub, "S3", R.color.S3, "Suburbano S3");
        aggiungiLinea(containerSub, "S4", R.color.S4, "Suburbano S4");
        aggiungiLinea(containerSub, "S5", R.color.S5, "Suburbano S5");
        aggiungiLinea(containerSub, "S6", R.color.S6, "Suburbano S6");
        aggiungiLinea(containerSub, "S7", R.color.S7, "Suburbano S7");
        aggiungiLinea(containerSub, "S8", R.color.S8, "Suburbano S8");
        aggiungiLinea(containerSub, "S9", R.color.S9, "Suburbano S9");
        aggiungiLinea(containerSub, "S11", R.color.S11, "Suburbano S11");
        aggiungiLinea(containerSub, "S12", R.color.S12, "Suburbano S12");
        aggiungiLinea(containerSub, "S13", R.color.S13, "Suburbano S13");
        aggiungiLinea(containerSub, "S19", R.color.S19, "Suburbano S19");
        aggiungiLinea(containerSub, "S31", R.color.S31, "Suburbano S31");

        //* SEARCH BAR
        searchLines.setBackgroundResource(R.drawable.bg_edittext_search);
        searchLines.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase().trim();

                // Riferimenti ai titoli e al messaggio "vuoto"
                TextView titleMetro = findViewById(R.id.subTitleMetro);
                TextView titleSub = findViewById(R.id.subTitleSuburban);
                TextView tvNoResults = findViewById(R.id.emptyView);

                // Filtriamo i due container
                boolean metroHaRisultati = filtraContainer(containerMetro, query);
                boolean subHaRisultati = filtraContainer(containerSub, query);

                // 1. Gestione Titoli
                if (titleMetro != null) titleMetro.setVisibility(metroHaRisultati ? View.VISIBLE : View.GONE);
                if (titleSub != null) titleSub.setVisibility(subHaRisultati ? View.VISIBLE : View.GONE);

                // 2. Gestione Messaggio "Nessun Risultato"
                // Se entrambi sono false, mostriamo il messaggio
                if (tvNoResults != null) {
                    if (!metroHaRisultati && !subHaRisultati) {
                        tvNoResults.setVisibility(View.VISIBLE);
                    } else {
                        tvNoResults.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        //*NAVBAR
        ImageButton btnHome = (ImageButton) findViewById(R.id.homeButton);
        btnHome.setOnClickListener(v -> {
            changeActivity(MainActivity.class);
        });

        ImageButton btnSettings = (ImageButton) findViewById(R.id.settingsButton);
        btnSettings.setOnClickListener(v -> {
            changeActivity(SettingsActivity.class);
        });

    }

    public void changeActivity(Class<?> destinationLayout) {
        ///@PARAMETER
        /// Class<?> destinationLayout is a destination activity which this function change.

        //*CHANGE LAYOUT
        Intent layoutChange = new Intent(LinesActivity.this, destinationLayout); //*CREATE THE INTENT WITH THE DESTINATION
        startActivity(layoutChange); //*CHANGE LAYOUT
        overridePendingTransition(1, 0);
    }

    private void aggiungiLinea(LinearLayout container, String label, int colorHex, String description) {
        View row = getLayoutInflater().inflate(R.layout.item_linea_list, container, false);

        TextView badge = row.findViewById(R.id.lineBadge);
        TextView name = row.findViewById(R.id.lineName);
        if (badge != null && name != null) {
            badge.setText(label);
            name.setText(description);

            int colore = ContextCompat.getColor(this, colorHex);

            if (badge.getBackground() != null) {
                badge.getBackground().mutate().setTint(colore);
            } else {
                badge.setBackgroundColor(colore);
            }


            row.setOnClickListener(v -> {
                Intent intent = new Intent(this, LinesDetailActivity.class);
                intent.putExtra("NOME_LINEA", label);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
            container.addView(row);

        }
    }
    private boolean filtraContainer(LinearLayout container, String query) {
        boolean trovatoAtLeastOne = false;
        for (int i = 0; i < container.getChildCount(); i++) {
            View row = container.getChildAt(i);
            TextView badge = row.findViewById(R.id.lineBadge);
            TextView name = row.findViewById(R.id.lineName);

            if (badge != null && name != null) {
                String bText = badge.getText().toString().toLowerCase();
                String nText = name.getText().toString().toLowerCase();

                if (bText.contains(query) || nText.contains(query)) {
                    row.setVisibility(View.VISIBLE);
                    trovatoAtLeastOne = true;
                } else {
                    row.setVisibility(View.GONE);
                }
            }
        }
        return trovatoAtLeastOne;
    }
}