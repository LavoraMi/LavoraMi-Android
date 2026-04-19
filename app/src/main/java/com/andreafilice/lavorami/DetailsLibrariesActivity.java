package com.andreafilice.lavorami;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsLibrariesActivity extends AppCompatActivity {

    private LibraryModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details_libraries);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        item = getIntent().getParcelableExtra("ITEM");

        //*UI SET-UP
        /// In this section of the code, we setup the UI Elements for the Library Details
        TextView txtHeaderTitle = findViewById(R.id.txtHeaderTitle);
        TextView detailName = findViewById(R.id.detailName);
        TextView detailVersion = findViewById(R.id.detailVersion);
        TextView detailLicenseType = findViewById(R.id.detailLicenseType);
        TextView detailCopyright = findViewById(R.id.detailCopyright);
        TextView detailLicenseText = findViewById(R.id.detailLicenseText);

        txtHeaderTitle.setText(item.getName());
        detailName.setText(item.getName());
        detailVersion.setText(item.getVersion());
        detailLicenseType.setText(item.getLicenseType());
        detailCopyright.setText(item.getCopyright());
        detailLicenseText.setText(item.getLicenseText());

        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());
    }
}