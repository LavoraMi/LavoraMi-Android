package com.andreafilice.lavorami;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class SetupModels {

    public static class SetupPage {
        String title, description, icon, smallDescription;

        public SetupPage(String title, String description, String icon, String smallDescription) {
            this.title = title;
            this.description = description;
            this.icon = icon;
            this.smallDescription = smallDescription;
        }
    }

    public static class SetupAdapter extends RecyclerView.Adapter<SetupAdapter.ViewHolder> {

        private List<SetupPage> pages;

        public SetupAdapter(List<SetupPage> pages) {
            this.pages = pages;
        }

        @NonNull
        @Override
        public SetupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setup_page, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SetupAdapter.ViewHolder holder, int position) {
            SetupPage page = pages.get(position);
            holder.tvTitle.setText(page.title);
            holder.tvDesc.setText(page.description);

            if(!page.smallDescription.trim().isEmpty()) {
                holder.tvSetupSmallDesc.setVisibility(View.VISIBLE);
                holder.tvSetupSmallDesc.setText(page.smallDescription);
            }
            else
                holder.tvSetupSmallDesc.setVisibility(View.GONE);

            int resID = holder.itemView.getContext().getResources().getIdentifier(page.icon, "drawable", holder.itemView.getContext().getPackageName());
            holder.img.setImageResource(resID);

            //*LANGUAGE DROPDOWN
            /// In this section of the code, we create a Dropdown for choose the starting Language of the application.
            if (position == 0) {
                holder.spinnerSetup.setVisibility(View.VISIBLE);

                List<String> languages = Arrays.asList("🇮🇹 Italiano", "🇬🇧 English");
                ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(
                        holder.itemView.getContext(),
                        android.R.layout.simple_spinner_item,
                        languages
                );

                String savedLanguage = DataManager.getStringData(holder.itemView.getContext(), DataKeys.KEY_DEFAULT_LANGUAGE, "🇮🇹 Italiano");

                languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.spinnerSetup.setAdapter(languageAdapter);

                int savedPosition = languages.indexOf(savedLanguage);
                if (savedPosition >= 0)
                    holder.spinnerSetup.setSelection(savedPosition, false);

                holder.spinnerSetup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        String[] languageSupported = {"it", "en"};
                        String selected = languages.get(pos);
                        DataManager.saveStringData(holder.itemView.getContext(), DataKeys.KEY_DEFAULT_LANGUAGE, selected);

                        LocaleListCompat localeList = LocaleListCompat.forLanguageTags(languageSupported[pos]);
                        AppCompatDelegate.setApplicationLocales(localeList);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            } else
                holder.spinnerSetup.setVisibility(View.GONE);
        }

        @Override
        public int getItemCount() {return pages.size();}

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDesc, tvSetupSmallDesc;
            ImageView img;
            Spinner spinnerSetup;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvSetupTitle);
                tvDesc = itemView.findViewById(R.id.tvSetupDesc);
                tvSetupSmallDesc = itemView.findViewById(R.id.tvSetupSmallDesc);
                img = itemView.findViewById(R.id.imgSetup);
                spinnerSetup = itemView.findViewById(R.id.spinnerSetup);
            }
        }
    }
}
