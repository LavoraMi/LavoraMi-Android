package com.andreafilice.lavorami;

import android.animation.TypeEvaluator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThemeSettings extends AppCompatActivity {

    //*GLOBAL VARIABLES
    RelativeLayout systemSelected;
    RelativeLayout darkSelected;
    RelativeLayout ligthSelected;

    ImageView systemTick;
    ImageView darkTick;
    ImageView lightTick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_theme_settings);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //*UI ELEMENTS
        /// In this section of the code, we declared RelativeLayouts and ImageResources for the Tick icon.

        systemSelected = findViewById(R.id.system);
        darkSelected = findViewById(R.id.dark);
        ligthSelected = findViewById(R.id.light);

        systemTick = findViewById(R.id.checkSystem);
        darkTick = findViewById(R.id.checkDark);
        lightTick = findViewById(R.id.checkLight);

        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {ActivityManager.changeActivity(this, SettingsActivity.class);});

        //*LOAD DATAs
        loadSavedData();

        //*EVENT TRIGGERS
        /// Set the default Trigger when changing the Theme Color
        systemSelected.setOnClickListener(v -> {changeTheme(TypeSelected.Sistema);});
        darkSelected.setOnClickListener(v -> {changeTheme(TypeSelected.Scuro);});
        ligthSelected.setOnClickListener(v -> {changeTheme(TypeSelected.Chiaro);});
    }

    private void changeTheme(TypeSelected type) {
        /// This method change the Theme when a RelativeLayout is clicked.
        /// @PARAMETERS
        /// TypeSelected type is the type the user choose for the new Theme.

        systemTick.setVisibility((type == TypeSelected.Sistema) ? View.VISIBLE : View.GONE);
        darkTick.setVisibility((type == TypeSelected.Scuro) ? View.VISIBLE : View.GONE);
        lightTick.setVisibility((type == TypeSelected.Chiaro) ? View.VISIBLE : View.GONE);

        /// Save the data to the DataManager
        DataManager.saveStringData(this, DataKeys.KEY_DEFAULT_THEME, type.toString());

        /// Set the Theme
        setTheme();
    }

    private void setTheme(){
        /// This method apply the theme that the user have selected by Loading the Data and cased it.
        /// @PARAMETERS
        /// There are no parameters.

        TypeSelected typeLoaded = TypeSelected.valueOf(DataManager.getStringData(this, DataKeys.KEY_DEFAULT_THEME, TypeSelected.Sistema.toString()));
        int modeSelected;

        switch (typeLoaded){
            case Sistema:
                modeSelected = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                break;
            case Scuro:
                modeSelected = AppCompatDelegate.MODE_NIGHT_YES;
                break;
            case Chiaro:
                modeSelected = AppCompatDelegate.MODE_NIGHT_NO;
                break;
            default:
                modeSelected = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                break;
        }

        AppCompatDelegate.setDefaultNightMode(modeSelected);
    }

    private void loadSavedData(){
        /// In this method we load the selection of the user made before.
        /// @PARAMETERS
        /// No parameters in this method.

        TypeSelected typeLoaded = TypeSelected.valueOf(DataManager.getStringData(this, DataKeys.KEY_DEFAULT_THEME, TypeSelected.Sistema.toString()));

        systemTick.setVisibility((typeLoaded == TypeSelected.Sistema) ? View.VISIBLE : View.GONE);
        darkTick.setVisibility((typeLoaded == TypeSelected.Scuro) ? View.VISIBLE : View.GONE);
        lightTick.setVisibility((typeLoaded == TypeSelected.Chiaro) ? View.VISIBLE : View.GONE);

        setTheme();
    }

    private enum TypeSelected {
        Sistema,
        Scuro,
        Chiaro
    }
}