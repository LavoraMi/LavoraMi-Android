package com.andreafilice.lavorami;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LibrariesActivity extends AppCompatActivity {

    private NestedScrollView nestedLinesView;
    private static final String APACHE_LICENSE_TEXT =
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                    "you may not use this file except in compliance with the License.\n" +
                    "You may obtain a copy of the License at\n\n" +
                    "    https://www.apache.org/licenses/LICENSE-2.0\n\n" +
                    "Unless required by applicable law or agreed to in writing, software\n" +
                    "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                    "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                    "See the License for the specific language governing permissions and\n" +
                    "limitations under the License.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_libraries);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nestedLinesView = findViewById(R.id.nestedLinesView);

        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());

        //*POPULATE THE VIEW
        /// In this section of the code, we populate the View with the 'buildLibraries' method
        nestedLinesView.setVisibility(View.GONE);

        LibraryModel[] items = buildLibraries();
        LinearLayout groupLibraries = findViewById(R.id.groupLibraries);
        for (LibraryModel item : items) {aggiungiLibreria(item, groupLibraries);}

        nestedLinesView.setVisibility(View.VISIBLE);
    }

    private LibraryModel[] buildLibraries() {
        return new LibraryModel[]{
                new LibraryModel(
                        "AndroidX Activity",
                        "1.14.0",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "AndroidX AppCompat",
                        "1.7.1",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "AndroidX Browser",
                        "1.10.0",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "AndroidX Biometric",
                        "1.1.0",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "AndroidX ConstraintLayout",
                        "2.2.1",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "Material Components for Android",
                        "1.14.0",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "AndroidX CardView",
                        "1.0.0",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "AndroidX Fragment",
                        "1.8.9",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "AndroidX RecyclerView",
                        "1.4.0",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "AndroidX Security Crypto",
                        "1.1.0",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "AndroidX Test JUnit",
                        "2.0.1",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "dotenv-java",
                        "3.2.0",
                        "Apache License 2.0",
                        "Copyright (c) Carmine DiMascio OSS",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "Espresso",
                        "3.7.0",
                        "Apache License 2.0",
                        "Copyright The Android Open Source Project",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "Firebase BOM",
                        "34.16.0",
                        "Apache License 2.0",
                        "Copyright Google LLC",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "Firebase Cloud Messaging",
                        "34.16.0",
                        "Apache License 2.0",
                        "Copyright Google LLC",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "Gson",
                        "2.14.0",
                        "Apache License 2.0",
                        "Copyright 2008 Google Inc.",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "Google Play Services Auth",
                        "21.6.0",
                        "Google APIs Terms of Service",
                        "Copyright Google LLC",
                        "Use of the Google Play Services Auth SDK is subject to the Google APIs Terms of Service.\n" +
                                "See https://developers.google.com/terms for details."
                ),
                new LibraryModel(
                        "JUnit",
                        "6.1.1",
                        "Eclipse Public License 1.0",
                        "Copyright (c) 2000-2021 JUnit contributors",
                        "This program and the accompanying materials are made available under the\n" +
                                "terms of the Eclipse Public License 1.0 which is available at\n" +
                                "https://www.eclipse.org/legal/epl-v10.html"
                ),
                new LibraryModel(
                        "Mapbox Maps SDK for Android",
                        "11.26.0",
                        "Mapbox Terms of Service",
                        "Copyright (c) Mapbox",
                        "Licensed under the Mapbox Terms of Service. See https://www.mapbox.com/legal/tos for details."
                ),
                new LibraryModel(
                        "OkHttp",
                        "5.4.0",
                        "Apache License 2.0",
                        "Copyright 2019 Square, Inc.",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "OkHttp Logging Interceptor",
                        "5.4.0",
                        "Apache License 2.0",
                        "Copyright 2019 Square, Inc.",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "Retrofit",
                        "3.0.0",
                        "Apache License 2.0",
                        "Copyright 2013 Square, Inc.",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "Retrofit Converter Gson",
                        "3.0.0",
                        "Apache License 2.0",
                        "Copyright 2013 Square, Inc.",
                        APACHE_LICENSE_TEXT
                ),
                new LibraryModel(
                        "Shimmer for Android",
                        "0.5.0",
                        "BSD License",
                        "Copyright (c) Meta Platforms, Inc. and affiliates.",
                        "BSD License\n\n" +
                                "Redistribution and use in source and binary forms, with or without\n" +
                                "modification, are permitted provided that the following conditions are met:\n\n" +
                                "* Redistributions of source code must retain the above copyright notice,\n" +
                                "  this list of conditions and the following disclaimer.\n\n" +
                                "* Redistributions in binary form must reproduce the above copyright notice,\n" +
                                "  this list of conditions and the following disclaimer in the documentation\n" +
                                "  and/or other materials provided with the distribution.\n\n" +
                                "* Neither the name Meta nor the names of its contributors may be used to\n" +
                                "  endorse or promote products derived from this software without specific\n" +
                                "  prior written permission.\n\n" +
                                "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\"\n" +
                                "AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE\n" +
                                "IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE\n" +
                                "ARE DISCLAIMED."
                ),
        };
    }

    public void aggiungiLibreria(LibraryModel item, LinearLayout groupLibraries) {
        /// In this method, we add the Library from the item to the UI Elements
        /// @PARAMETERS:
        /// LibraryModel item is the iterator item from the 'buildLibraries' method.

        View row = getLayoutInflater().inflate(R.layout.item_library, groupLibraries, false);

        TextView txtTitle = row.findViewById(R.id.txtTitle);
        TextView txtVersion = row.findViewById(R.id.txtVersion);
        TextView txtLicense = row.findViewById(R.id.txtLicense);
        TextView textCopyright = row.findViewById(R.id.textCopyright);

        txtTitle.setText(item.getName());
        txtVersion.setText(item.getVersion());
        txtLicense.setText(item.getLicenseType());
        textCopyright.setText(item.getCopyright());

        row.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetailsLibrariesActivity.class);
            intent.putExtra("ITEM", item);
            startActivity(intent);
        });

        groupLibraries.addView(row);
    }
}