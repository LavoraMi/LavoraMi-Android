package com.andreafilice.lavorami;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
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
        LinearLayout containerTILO = findViewById(R.id.groupTrans);
        LinearLayout containerMXP = findViewById(R.id.groupMXP);
        LinearLayout containerTram = findViewById(R.id.groupTram);
        LinearLayout containerTrans = findViewById(R.id.groupTrans);
        LinearLayout containerMovibus = findViewById(R.id.groupMovibus);
        LinearLayout containerStav = findViewById(R.id.groupStav);
        LinearLayout containerAutoGuidovie = findViewById(R.id.groupAutoGuidoVie);

        EditText searchLines = findViewById(R.id.editSearch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /// In this section of the code, we generate the Lines from the StationsDB file.
        /// Every type of line is sorted in order of importance.

        //METRO
        aggiungiLinea(containerMetro, "M1", R.color.M1, "Metro");
        aggiungiLinea(containerMetro, "M2", R.color.M2, "Metro");
        aggiungiLinea(containerMetro, "M3", R.color.M3, "Metro");
        aggiungiLinea(containerMetro, "M4", R.color.M4, "Metro");
        aggiungiLinea(containerMetro, "M5", R.color.M5, "Metro");

        // SUBURBANI
        aggiungiLinea(containerSub, "S1", R.color.S1, "Suburbano");
        aggiungiLinea(containerSub, "S2", R.color.S2, "Suburbano");
        aggiungiLinea(containerSub, "S3", R.color.S3, "Suburbano");
        aggiungiLinea(containerSub, "S4", R.color.S4, "Suburbano");
        aggiungiLinea(containerSub, "S5", R.color.S5, "Suburbano");
        aggiungiLinea(containerSub, "S6", R.color.S6, "Suburbano");
        aggiungiLinea(containerSub, "S7", R.color.S7, "Suburbano");
        aggiungiLinea(containerSub, "S8", R.color.S8, "Suburbano");
        aggiungiLinea(containerSub, "S9", R.color.S9, "Suburbano");
        aggiungiLinea(containerSub, "S11", R.color.S11, "Suburbano");
        aggiungiLinea(containerSub, "S12", R.color.S12, "Suburbano");
        aggiungiLinea(containerSub, "S13", R.color.S13, "Suburbano");
        aggiungiLinea(containerSub, "S19", R.color.S19, "Suburbano");
        aggiungiLinea(containerSub, "S31", R.color.S31, "Suburbano");

        //TILO
        aggiungiLinea(containerTILO,"S10",R.color.S10,"Transfrontaliera");
        aggiungiLinea(containerTILO,"S30",R.color.S30,"Transfrontaliera");
        aggiungiLinea(containerTILO,"S40",R.color.S40,"Transfrontaliera");
        aggiungiLinea(containerTILO,"S50",R.color.S50,"Transfrontaliera");
        aggiungiLinea(containerTILO,"RE80",R.color.RE80,"Transfrontaliera");

        //MXP
        aggiungiLinea(containerMXP,"MXP1",R.color.MXP,"Malpensa Express");
        aggiungiLinea(containerMXP,"MXP2",R.color.MXP,"Malpensa Express");

        //TRAM
        aggiungiLinea(containerTram,"1",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"2",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"3",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"4",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"5",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"7",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"9",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"10",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"12",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"14",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"15",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"16",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"19",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"24",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"27",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"31",R.color.TRAM,"Tram");
        aggiungiLinea(containerTram,"33",R.color.TRAM,"Tram");

        //MOVIBUS
        aggiungiLinea(containerMovibus,"z601",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z602",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z603",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z6C3",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z606",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z611",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z612",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z616",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z617",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z618",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z619",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z620",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z621",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z622",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z625",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z627",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z636",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z641",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z642",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z643",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z644",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z646",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z647",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z648",R.color.BUS,"Movibus");
        aggiungiLinea(containerMovibus,"z649",R.color.BUS,"Movibus");

        //STAV
        aggiungiLinea(containerStav,"z551",R.color.BUS,"STAV");
        aggiungiLinea(containerStav,"z552",R.color.BUS,"STAV");
        aggiungiLinea(containerStav,"z553",R.color.BUS,"STAV");
        aggiungiLinea(containerStav,"z554",R.color.BUS,"STAV");
        aggiungiLinea(containerStav,"z555",R.color.BUS,"STAV");
        aggiungiLinea(containerStav,"z556",R.color.BUS,"STAV");
        aggiungiLinea(containerStav,"z557",R.color.BUS,"STAV");
        aggiungiLinea(containerStav,"z559",R.color.BUS,"STAV");
        aggiungiLinea(containerStav,"z560",R.color.BUS,"STAV");

        //AUTOGUIDOVIE
        aggiungiLinea(containerAutoGuidovie,"z401", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z402", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z403", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z404", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z405", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z406", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z407", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z409", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z410", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z411", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z412", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z413", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z415", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z418", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z419", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z420", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z431", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z432", R.color.BUS,"Autoguidovie");

        aggiungiLinea(containerAutoGuidovie,"z203", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z205", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z209", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z219", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z221", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z222", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z225", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z227", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z228", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z229", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z231", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z232", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z233", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z234", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z250", R.color.BUS,"Autoguidovie");
        aggiungiLinea(containerAutoGuidovie,"z251", R.color.BUS,"Autoguidovie");

        //*WEBSITE LINKS
        /// In this section of the code, we set the default action (OnClick) of the ImageView
        /// From the infoButtons, and create an array with the URLs in it.
        /// In the first section we initialize the arrays, in the second one we
        /// Create a For-Loop for set automatically the action of the button.

        ImageView[] infoButtons = {
                findViewById(R.id.infoIconMetro),
                findViewById(R.id.infoIconSuburbane),
                findViewById(R.id.infoIconTransfrontaliere),
                findViewById(R.id.infoIconMXP),
                findViewById(R.id.infoIconTram),
                findViewById(R.id.infoIconMovibus),
                findViewById(R.id.infoIconStav),
                findViewById(R.id.infoIconAutoGuidoVie)
        };

        String[] infoUrls = {
                "https://giromilano.atm.it/assets/images/schema_rete_metro.jpg",
                "https://www.trenord.it/linee-e-orari/circolazione/le-nostre-linee/",
                "https://www.tilo.ch",
                "https://www.malpensaexpress.it",
                "https://www.atm.it/it/AltriServizi/Trasporto/Documents/Carta%20ATM_WEB_2025.11.pdf",
                "https://movibus.it/news/",
                "https://stavautolinee.it/reti-servite/",
                "https://autoguidovie.it/it/avvisi"
        };

        for (int i = 0; i < infoButtons.length; i++) {
            int finalI = i;
            infoButtons[i].setOnClickListener(v -> {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse(infoUrls[finalI]));
            });
        }

        //* SEARCH BAR
        searchLines.setBackgroundResource(R.drawable.bg_edittext_search);
        searchLines.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase().trim();

                LinearLayout titleMetro = findViewById(R.id.headerMetro);
                LinearLayout titleSub = findViewById(R.id.headerSuburbane);
                LinearLayout titleMXP = findViewById(R.id.headerMXP);
                LinearLayout titleTrans = findViewById(R.id.headerTransfrontaliere);
                LinearLayout titleTram = findViewById(R.id.headerTram);
                LinearLayout titleMovibus = findViewById(R.id.headerMovibus);
                LinearLayout titleStav = findViewById(R.id.headerSTAV);
                LinearLayout titleAutoGuidoVie= findViewById(R.id.headerAutoguidovie);
                TextView tvNoResults = findViewById(R.id.emptyView);

                boolean hasMetro = filtraContainer(containerMetro, query);
                boolean hasSub = filtraContainer(containerSub, query);
                boolean hasMXP = filtraContainer(containerMXP, query);
                boolean hasTrans = filtraContainer(containerTrans, query);
                boolean hasTram = filtraContainer(containerTram, query);
                boolean hasMovibus = filtraContainer(containerMovibus, query);
                boolean hasStav= filtraContainer(containerStav, query);
                boolean hasAuto = filtraContainer(containerAutoGuidovie, query);

                //*METRO LINES
                titleMetro.setVisibility(hasMetro ? View.VISIBLE : View.GONE);
                containerMetro.setVisibility(hasMetro ? View.VISIBLE : View.GONE);

                //*SUBURBAN LINES
                titleSub.setVisibility(hasSub ? View.VISIBLE : View.GONE);
                containerSub.setVisibility(hasSub ? View.VISIBLE : View.GONE);

                //*MXP LINES
                titleMXP.setVisibility(hasMXP ? View.VISIBLE : View.GONE);
                containerMXP.setVisibility(hasMXP ? View.VISIBLE : View.GONE);

                //*TRANSFRONTALIERE LINES
                titleTrans.setVisibility(hasTrans ? View.VISIBLE : View.GONE);
                containerTrans.setVisibility(hasTrans ? View.VISIBLE : View.GONE);

                //*TRAM LINES
                titleTram.setVisibility(hasTram ? View.VISIBLE : View.GONE);
                containerTram.setVisibility(hasTram ? View.VISIBLE : View.GONE);

                //*MOVIBUS LINES
                titleMovibus.setVisibility(hasMovibus ? View.VISIBLE : View.GONE);
                containerMovibus.setVisibility(hasMovibus ? View.VISIBLE : View.GONE);

                //*STAV LINES
                titleStav.setVisibility(hasStav ? View.VISIBLE : View.GONE);
                containerStav.setVisibility(hasStav ? View.VISIBLE : View.GONE);

                //*AUTOGUIDOVIE LINES
                titleAutoGuidoVie.setVisibility(hasAuto ? View.VISIBLE : View.GONE);
                containerAutoGuidovie.setVisibility(hasAuto ? View.VISIBLE : View.GONE);

                if (tvNoResults != null) {
                    if (!hasMetro && !hasSub && !hasMXP && !hasTrans && !hasTram && !hasMovibus && !hasStav && !hasAuto)
                        tvNoResults.setVisibility(View.VISIBLE);
                    else
                        tvNoResults.setVisibility(View.GONE);
                }

                if (s.length() > 0)
                    searchLines.setCompoundDrawablesWithIntrinsicBounds(
                            android.R.drawable.ic_menu_search, 0,
                            R.drawable.ic_close, 0);
                else
                    searchLines.setCompoundDrawablesWithIntrinsicBounds(
                            android.R.drawable.ic_menu_search, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        /// If the "X" button is clicked, clean all the text into the EditText
        searchLines.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Drawable drawableEnd = searchLines.getCompoundDrawables()[2];
                if (drawableEnd != null) {
                    if (event.getRawX() >= (searchLines.getRight() - drawableEnd.getBounds().width())) {
                        searchLines.setText("");
                        return true;
                    }
                }
            }
            return false;
        });

        //*NAVBAR
        ImageButton btnHome = (ImageButton) findViewById(R.id.homeButton);
        btnHome.setOnClickListener(v -> {ActivityManager.changeActivity(this, MainActivity.class);});

        ImageButton btnSettings = (ImageButton) findViewById(R.id.settingsButton);
        btnSettings.setOnClickListener(v -> {ActivityManager.changeActivity(this, SettingsActivity.class);});
    }

    private void aggiungiLinea(LinearLayout container, String label, int colorHex, String description) {
        View row = getLayoutInflater().inflate(R.layout.item_linea_list, container, false);

        TextView badge = row.findViewById(R.id.lineBadge);
        TextView name = row.findViewById(R.id.lineName);
        if (badge != null && name != null) {
            badge.setText(label);
            name.setText((description + " " +  label));

            int colore = ContextCompat.getColor(this, colorHex);

            if (badge.getBackground() != null)
                badge.getBackground().mutate().setTint(colore);
            else
                badge.setBackgroundColor(colore);


            row.setOnClickListener(v -> {
                Intent intent = new Intent(this, LinesDetailActivity.class);
                intent.putExtra("NOME_LINEA", label);
                intent.putExtra("TIPO_DI_LINEA", (description + " " + label));
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
                } else
                    row.setVisibility(View.GONE);
            }
        }
        return trovatoAtLeastOne;
    }
}