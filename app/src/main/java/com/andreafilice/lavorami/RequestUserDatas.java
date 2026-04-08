package com.andreafilice.lavorami;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static com.andreafilice.lavorami.ActivityUtils.getLocalizedString;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RequestUserDatas extends AppCompatActivity {
    String selectedFileFormat = "JSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_user_datas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //*LOCK THE ORIENTATION
        /// In this section of the code, we will block the orientation to PORTRAIT because in LANDSCAPE LavoraMi is not supported.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SessionManager sessionManager = new SessionManager(this);

        //*BACK BUTTON
        /// In this section of the code, we come back to the previous Activity with the 'finish()' instruction.
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {finish();});

        //*CHECK BUTTONS
        /// In this section of the code, we set the Checks icons nearby the options to choose for the format of the File.
        RelativeLayout jsonSelection = findViewById(R.id.JSON);
        RelativeLayout htmlSelection = findViewById(R.id.HTML);
        ImageView jsonCheck = findViewById(R.id.checkedJSON);
        ImageView htmlCheck = findViewById(R.id.checkedHTML);

        jsonSelection.setOnClickListener(v -> {
            jsonCheck.setVisibility(View.VISIBLE);
            htmlCheck.setVisibility(View.GONE);
            selectedFileFormat = "JSON";
        });

        htmlSelection.setOnClickListener(v -> {
            jsonCheck.setVisibility(View.GONE);
            htmlCheck.setVisibility(View.VISIBLE);
            selectedFileFormat = "HTML";
        });

        //*SEND THE REQUEST
        /// In this section of the code, we create the objects from the ActivityFile for send the email with the Request.
        CardView btnRequestDatas = findViewById(R.id.btnRequestDatas);
        btnRequestDatas.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(RequestUserDatas.this, R.color.redMetro)));

        btnRequestDatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = ContextCompat.getString(RequestUserDatas.this, R.string.requestDataIntentSubject);
                String body = getLocalizedString(RequestUserDatas.this, R.string.requestDataIntentPart1) + selectedFileFormat + "\n" + getLocalizedString(RequestUserDatas.this, R.string.requestDataIntentPart2) + sessionManager.getUserEmail() + "\n" + getLocalizedString(RequestUserDatas.this, R.string.requestDataIntentPart3);

                String encodedBody = Uri.encode(body);
                String mailtoUri = "mailto:info@lavorami.it?subject=" + Uri.encode(subject) + "&body=" + encodedBody;

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(mailtoUri));

                try {startActivity(Intent.createChooser(intent, "Invia richiesta dati"));}
                catch (Exception e) {
                    Toast.makeText(RequestUserDatas.this, getLocalizedString(RequestUserDatas.this, R.string.unknownErrorToast), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
    public boolean validateEmail(String mail){return (mail.matches("^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$"));}
}