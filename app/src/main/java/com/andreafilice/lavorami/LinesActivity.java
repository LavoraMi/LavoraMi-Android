package com.andreafilice.lavorami;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

import com.facebook.shimmer.ShimmerFrameLayout;

public class LinesActivity extends AppCompatActivity {

    LinearLayout containerMetro;
    LinearLayout containerSub;
    LinearLayout containerTILO;
    LinearLayout containerMXP;
    LinearLayout containerTram;
    LinearLayout containerTrans;
    LinearLayout containerMovibus;
    LinearLayout containerStav;
    LinearLayout containerAutoGuidovie;
    ShimmerFrameLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lines);

        //*LOCK THE ORIENTATION
        /// In this section of the code, we will block the orientation to PORTRAIT because in LANDSCAPE LavoraMi is not supported.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //*INITIALIZE COMPONENTS
        /// In this section of the code, we initialize all the containers for the sub-menus.
        containerMetro = findViewById(R.id.groupMetro);
        containerSub = findViewById(R.id.groupSub);
        containerTILO = findViewById(R.id.groupTrans);
        containerMXP = findViewById(R.id.groupMXP);
        containerTram = findViewById(R.id.groupTram);
        containerTrans = findViewById(R.id.groupTrans);
        containerMovibus = findViewById(R.id.groupMovibus);
        containerStav = findViewById(R.id.groupStav);
        containerAutoGuidovie = findViewById(R.id.groupAutoGuidoVie);

        loadingLayout = findViewById(R.id.loadingLayout);
        loadingLayout.startShimmer();

        findViewById(R.id.main).post(() -> {
            findViewById(R.id.main).postDelayed(() -> {
                loadLines();
                findViewById(R.id.nestedLinesView).setVisibility(View.VISIBLE);
            }, 1000);
        });

        EditText searchLines = findViewById(R.id.editSearch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

    private void loadLines(){
        /// In this section of the code, we generate the Lines from the StationsDB file.
        /// Every type of line is sorted in order of importance.

        // METRO
        String[] metroLines = {"M1", "M2", "M3", "M4", "M5"};
        int[] metroColors = {R.color.M1, R.color.M2, R.color.M3, R.color.M4, R.color.M5};
        for (int i = 0; i < metroLines.length; i++) {
            int finalI = i;
            aggiungiLinea(containerMetro, metroLines[finalI], metroColors[finalI], "Metro");
        }

        // SUBURBANE
        String[] suburbanLines = {"S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9",
                "S11", "S12", "S13", "S19", "S31"};
        int[] suburbanColors = {R.color.S1, R.color.S2, R.color.S3, R.color.S4, R.color.S5,
                R.color.S6, R.color.S7, R.color.S8, R.color.S9, R.color.S11,
                R.color.S12, R.color.S13, R.color.S19, R.color.S31};
        for (int i = 0; i < suburbanLines.length; i++) {
            int finalI = i;
            aggiungiLinea(containerSub, suburbanLines[finalI], suburbanColors[finalI], "Suburbano");
        }

        // TILO
        String[] tiloLines = {"S10", "S30", "S40", "S50", "RE80"};
        int[] tiloColors = {R.color.S10, R.color.S30, R.color.S40, R.color.S50, R.color.RE80};
        for (int i = 0; i < tiloLines.length; i++) {
            int finalI = i;
            aggiungiLinea(containerTILO, tiloLines[finalI], tiloColors[finalI], "Transfrontaliera");
        }

        // MXP
        String[] mxpLines = {"MXP1", "MXP2"};
        for (String line : mxpLines) {
            aggiungiLinea(containerMXP, line, R.color.MXP, "Malpensa Express");
        }

        // TRAM
        String[] tramLines = {"1", "2", "3", "4", "5", "7", "9", "10", "12", "14",
                "15", "16", "19", "24", "27", "31", "33"};
        for (String line : tramLines) {
            aggiungiLinea(containerTram, line, R.color.TRAM, "Tram");
        }

        // MOVIBUS
        String[] movibusLines = {"z601", "z602", "z603", "z6C3", "z606", "z611", "z612",
                "z616", "z617", "z618", "z619", "z620", "z621", "z622",
                "z625", "z627", "z636", "z641", "z642", "z643", "z644",
                "z646", "z647", "z648", "z649"};
        for (String line : movibusLines) {
            aggiungiLinea(containerMovibus, line, R.color.BUS, "Movibus");
        }

        // STAV
        String[] stavLines = {"z551", "z552", "z553", "z554", "z555", "z556",
                "z557", "z559", "z560"};
        for (String line : stavLines) {
            aggiungiLinea(containerStav, line, R.color.BUS, "STAV");
        }

        // AUTOGUIDOVIE
        String[] autoguidovieLines = {"z401", "z402", "z403", "z404", "z405", "z406",
                "z407", "z409", "z410", "z411", "z412", "z413",
                "z415", "z418", "z419", "z420", "z431", "z432",
                "z203", "z205", "z209", "z219", "z221", "z222",
                "z225", "z227", "z228", "z229", "z231", "z232",
                "z233", "z234", "z250", "z251"};
        for (String line : autoguidovieLines) {
            aggiungiLinea(containerAutoGuidovie, line, R.color.BUS, "Autoguidovie");
        }

        //Stop the Shimmer animation.
        loadingLayout.setVisibility(View.GONE);
    }

    private void aggiungiLinea(LinearLayout container, String label, int colorHex, String description) {
        findViewById(R.id.shimmerTextAnim).setVisibility(View.GONE);
        findViewById(R.id.shimmerLineBadge).setVisibility(View.GONE);
        View row = getLayoutInflater().inflate(R.layout.item_linea_list, container, false);

        TextView badge = row.findViewById(R.id.lineBadge);
        TextView name = row.findViewById(R.id.lineName);
        TextView shimmerAnim = row.findViewById(R.id.shimmerTextAnim);
        TextView shimmerBadgeAnim = row.findViewById(R.id.shimmerLineBadge);
        TextView lineBadge = row.findViewById(R.id.lineBadge);

        if (badge != null && name != null) {
            badge.setText(label);
            name.setText((description + " " +  label));
            shimmerAnim.setVisibility(View.GONE);
            shimmerBadgeAnim.setVisibility(View.GONE);
            lineBadge.setVisibility(View.VISIBLE);

            int colore = ContextCompat.getColor(this, colorHex);

            if (badge.getBackground() != null)
                badge.getBackground().mutate().setTint(colore);
            else
                badge.setBackgroundColor(R.color.White);


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