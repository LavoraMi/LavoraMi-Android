package com.andreafilice.lavorami;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdvancedOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_advanced_options);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //*BUTTONS
        ImageButton backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(v -> {ActivityManager.changeActivity(this, SettingsActivity.class);});

        //*LOAD DATAS
        /// Load the value of the Switch from the DataManager.
        boolean isErrorActive = DataManager.getBoolData(this, DataKeys.KEY_SHOW_ERROR_MESSAGES, false);
        boolean isBannerActive = DataManager.getBoolData(this, DataKeys.KEY_SHOW_BANNERS, true);
        boolean isRequireBiometrics = DataManager.getBoolData(this, DataKeys.KEY_REQUIRE_BIOMETRICS, true);

        Switch errorMessagesSwitch = findViewById(R.id.switchErrors);
        Switch strikeBannersSwitch = findViewById(R.id.switchBanner);
        Switch biometricsSwitch = findViewById(R.id.switchBiometrics);

        errorMessagesSwitch.setChecked(isErrorActive);
        errorMessagesSwitch.setTrackTintMode((errorMessagesSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);

        strikeBannersSwitch.setChecked(isBannerActive);
        strikeBannersSwitch.setTrackTintMode((strikeBannersSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);

        biometricsSwitch.setChecked(isRequireBiometrics);
        biometricsSwitch.setTrackTintMode((biometricsSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);

        //*SAVE DATAS
        /// Save the value from the Switch Checked status to DataManager.
        errorMessagesSwitch.setOnClickListener(v -> {
            DataManager.saveBoolData(this, DataKeys.KEY_SHOW_ERROR_MESSAGES, errorMessagesSwitch.isChecked());
            errorMessagesSwitch.setTrackTintMode((errorMessagesSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
        });

        strikeBannersSwitch.setOnClickListener(v -> {
            DataManager.saveBoolData(this, DataKeys.KEY_SHOW_BANNERS, strikeBannersSwitch.isChecked());
            strikeBannersSwitch.setTrackTintMode((strikeBannersSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
        });

        biometricsSwitch.setOnClickListener(v -> {
            DataManager.saveBoolData(this, DataKeys.KEY_REQUIRE_BIOMETRICS, biometricsSwitch.isChecked());
            biometricsSwitch.setTrackTintMode((biometricsSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
        });
    }
}