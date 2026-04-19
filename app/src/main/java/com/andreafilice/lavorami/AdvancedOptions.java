package com.andreafilice.lavorami;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.cardview.widget.CardView;
import androidx.core.view.WindowInsetsCompat;


import java.io.File;
import java.security.KeyStore;
import java.util.concurrent.Executor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

public class AdvancedOptions extends AppCompatActivity {

    private static final String BIOMETRIC_KEY_ALIAS = "AdvancedOptionsBiometricKey";

    private void generateSecretKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        if (keyStore.containsAlias(BIOMETRIC_KEY_ALIAS)) {
            return;
        }
        KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(
                BIOMETRIC_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setUserAuthenticationRequired(true)
                .setInvalidatedByBiometricEnrollment(true)
                .build();
        KeyGenerator keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        keyGenerator.init(keyGenParameterSpec);
        keyGenerator.generateKey();
    }

    private SecretKey getSecretKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        return (SecretKey) keyStore.getKey(BIOMETRIC_KEY_ALIAS, null);
    }

    private Cipher getCipher() throws Exception {
        Cipher cipher = Cipher.getInstance(
                KeyProperties.KEY_ALGORITHM_AES + "/" +
                        KeyProperties.BLOCK_MODE_CBC + "/" +
                        KeyProperties.ENCRYPTION_PADDING_PKCS7);
        SecretKey secretKey = getSecretKey();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher;
    }

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

        //*VARIABLES
        String savedLang = DataManager.getStringData(this, DataKeys.KEY_DEFAULT_LANGUAGE, "🇮🇹 Italiano");
        String langCode = savedLang.contains("English") ? "en" : "it";

        //*LOCK THE ORIENTATION
        /// In this section of the code, we will block the orientation to PORTRAIT because in LANDSCAPE LavoraMi is not supported.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //*BUTTONS
        ImageButton backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(v -> {ActivityUtils.changeActivity(this, SettingsActivity.class);});

        //*LOAD DATAS
        /// Load the value of the Switch from the DataManager.
        boolean[] switchesStatus = {
            DataManager.getBoolData(this, DataKeys.KEY_SHOW_ERROR_MESSAGES, false),
            DataManager.getBoolData(this, DataKeys.KEY_SHOW_BANNERS, true),
            DataManager.getBoolData(this, DataKeys.KEY_REQUIRE_BIOMETRICS, true),
            DataManager.getBoolData(this, DataKeys.KEY_SHOW_DETAILS, true),
            DataManager.getBoolData(this, DataKeys.KEY_SHOW_TRANSLATE_BUTTON, false)
        };

        Switch[] switches = {
            findViewById(R.id.switchErrors),
            findViewById(R.id.switchBanner),
            findViewById(R.id.switchBiometrics),
            findViewById(R.id.switchDetails),
            findViewById(R.id.switchTranslation)
        };

        DataKeys[] switchesKey = {
            DataKeys.KEY_SHOW_ERROR_MESSAGES,
            DataKeys.KEY_SHOW_BANNERS,
            DataKeys.KEY_REQUIRE_BIOMETRICS,
            DataKeys.KEY_SHOW_DETAILS,
            DataKeys.KEY_SHOW_TRANSLATE_BUTTON
        };

        for (int i = 0; i < switches.length; i++) {setupSwitch(switches[i], switchesStatus[i], switchesKey[i]);}

        //*CACHE MEMORY
        /// In this section of the code, we set-up the code to delete the Cache Memory.
        CardView btnCacheMemory = findViewById(R.id.btnCacheMemory);
        btnCacheMemory.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.areYouSurePopUp)
                    .setMessage(R.string.cacheMemoryPopUpDeps)
                    .setNegativeButton(R.string.cancelPopUp, null)
                    .setPositiveButton(R.string.confirmPopUp, (dialog, which) -> {
                        new Thread(() -> {deleteCache(this);}).start();
                    }).show();
        });

        //*TRANSLATE OPTION
        /// In this section of the code, we catch if the language is Italian and, if it is, we activate the card with the option.
        CardView translateButtonCard = findViewById(R.id.cardButtonTranslate);
        TextView descButtonTranslate = findViewById(R.id.descButtonTranslate);
        translateButtonCard.setVisibility((langCode.equalsIgnoreCase("it")) ? View.VISIBLE : View.GONE);
        descButtonTranslate.setVisibility(translateButtonCard.getVisibility());
    }


    public void setupSwitch(Switch currentSwitch, boolean isActive, DataKeys dataToSave) {
        /// This method is a refactor one, we set up the swithes to avoid unusued memoryAllocation and better performance.
        /// @PARAMETERS
        /// Switch currentSwitch is the switch to apply the changes
        /// boolean isActive checks if the Switch is currently active or not.
        /// DataKeys dataToSave is the key to save the value of the Switch.

        //*INIT VALUES
        /// Initialize the current status of the Switch from the Data saved.
        currentSwitch.setChecked(isActive);
        currentSwitch.setTrackTintMode((currentSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);

        //*SAVE DATAS
        /// Save the value from the Switch Checked status to DataManager.
        currentSwitch.setOnClickListener(v -> {
            if(dataToSave == DataKeys.KEY_REQUIRE_BIOMETRICS)
                showBiometricPrompt(currentSwitch);
            else {
                DataManager.saveBoolData(this, dataToSave, currentSwitch.isChecked());
                currentSwitch.setTrackTintMode((currentSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
            }
        });
    }

    public static void deleteCache(Context context) {
        /// In this method, we catch from the Context the current CacheDirectory.
        /// @PARAMETERS
        /// Context context is the Activity from take the Directory Path to the Cache Memory.

        try {
            File dir = context.getCacheDir();
            deleteDir(dir);

            if (context instanceof AppCompatActivity)
                ((AppCompatActivity) context).runOnUiThread(() -> {Toast.makeText(context, context.getString(R.string.cacheMemoryToast), Toast.LENGTH_SHORT).show();});
        }
        catch (Exception e) {e.printStackTrace();}
        DataManager.saveBoolData(context, DataKeys.KEY_DOWNLOAD_POLICIES, true);
    }

    public static boolean deleteDir(File dir) {
        /// In this method, we delete effectually the Cache memory Partition.
        /// @PARAMETERS
        /// File dir is the directory to delete (Cache Directory in this case).

        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();

            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));

                if (!success)
                    return false;
            }

            return dir.delete();
        }
        else if(dir!= null && dir.isFile())
            return dir.delete();
        else
            return false;
    }

    private void showBiometricPrompt(Switch biometricsSwitch) {
        /// This method call a Google API to request a Biometric authentication.
        /// This method protects the user protection by veryfing who is accesing at that setting with that device.
        /// @FALLBACK rules:
        /// If no FaceID or TouchID are setted-up into the device, Fallback to ask the PIN or Device Password.
        /// If no PIN also is found, Fallback to unlock the screen automatically.

        BiometricManager biometricManager = BiometricManager.from(this);

        int authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL;
        int canAuthenticate = biometricManager.canAuthenticate(authenticators);
        biometricsSwitch.setChecked(DataManager.getBoolData(this, DataKeys.KEY_REQUIRE_BIOMETRICS, true));

        if (canAuthenticate != BiometricManager.BIOMETRIC_SUCCESS) {
            DataManager.saveBoolData(this, DataKeys.KEY_REQUIRE_BIOMETRICS, biometricsSwitch.isChecked());
            biometricsSwitch.setTrackTintMode((biometricsSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
            return;
        }

        Cipher cipherForPrompt = null;
        try {
            generateSecretKey();
            cipherForPrompt = getCipher();
        } catch (Exception e) {
            Log.e("AdvancedOptions", "Errore durante l'inizializzazione della chiave biometrica", e);
        }

        /// In this section of the code, we create the PopUp for log-in with Biometric Auth.
        /// The pop-up UI is different base by Manufacture Implementation.
        /// @FALLBACK
        /// If the Biometric Auth failed, don't apply the modifications to the DataManager.

        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(AdvancedOptions.this, AdvancedOptions.this.getString(R.string.authFailedToast), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                try {
                    Cipher cipher = result.getCryptoObject() != null ? result.getCryptoObject().getCipher() : null;

                    if (cipher != null) {
                        byte[] payload = "biometric_toggle".getBytes("UTF-8");
                        byte[] encrypted = cipher.doFinal(payload);

                        if (encrypted != null && encrypted.length > 0) {
                            biometricsSwitch.setChecked(false);
                            DataManager.saveBoolData(AdvancedOptions.this, DataKeys.KEY_REQUIRE_BIOMETRICS, biometricsSwitch.isChecked());
                            biometricsSwitch.setTrackTintMode((biometricsSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
                        }
                    }
                    else
                        Toast.makeText(AdvancedOptions.this, getString(R.string.verifyBiometricsToast), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Log.e("AdvancedOptions", "Errore durante l'operazione crittografica biometrica", e);
                    Toast.makeText(AdvancedOptions.this, getString(R.string.authFailedToast), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAuthenticationFailed() {super.onAuthenticationFailed();}
        });

        if(biometricsSwitch.isChecked()){
            BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle(ContextCompat.getString(this, R.string.editSettingsPopUpTitle))
                    .setSubtitle(ContextCompat.getString(this, R.string.editSettingsPopUpDeps))
                    .setAllowedAuthenticators(authenticators)
                    .build();

            if (cipherForPrompt != null)
                biometricPrompt.authenticate(promptInfo, new BiometricPrompt.CryptoObject(cipherForPrompt));
            else
                biometricPrompt.authenticate(promptInfo);
        }
        else {
            biometricsSwitch.setChecked(true);
            DataManager.saveBoolData(AdvancedOptions.this, DataKeys.KEY_REQUIRE_BIOMETRICS, biometricsSwitch.isChecked());
            biometricsSwitch.setTrackTintMode((biometricsSwitch.isChecked()) ? PorterDuff.Mode.ADD : PorterDuff.Mode.MULTIPLY);
        }
    }
}