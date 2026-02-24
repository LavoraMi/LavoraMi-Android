package com.andreafilice.lavorami;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RequestUserDatas extends AppCompatActivity {

    //*GLOBAL VARIABLES
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
        EditText etEmailAccount = findViewById(R.id.etEmailAccount);

        etEmailAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnRequestDatas.setBackgroundTintList((validateEmail(etEmailAccount.getText().toString())
                        ? ColorStateList.valueOf(getResources().getColor(R.color.redMetro))
                        : ColorStateList.valueOf(getResources().getColor(R.color.GRAY))
                ));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnRequestDatas.setBackgroundTintList((validateEmail(etEmailAccount.getText().toString())
                        ? ColorStateList.valueOf(getResources().getColor(R.color.redMetro))
                        : ColorStateList.valueOf(getResources().getColor(R.color.GRAY))
                ));
            }
        });

        btnRequestDatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateEmail(etEmailAccount.getText().toString())){
                    String subject = "Richiesta dei miei dati";
                    String body = "Buongiorno,\nVorrei richiedere l'invio dei miei dati in formato " + selectedFileFormat + " dell'Account con mail: " + etEmailAccount.getText() + "\nMessaggio inviato dall'App LavoraMi.";

                    String encodedBody = Uri.encode(body);

                    String mailtoUri = "mailto:info@lavorami.it?subject=" + Uri.encode(subject) + "&body=" + encodedBody;

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse(mailtoUri));

                    try {
                        startActivity(Intent.createChooser(intent, "Invia richiesta dati"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public boolean validateEmail(String mail){return (mail.contains("@") && !mail.isEmpty());}
}