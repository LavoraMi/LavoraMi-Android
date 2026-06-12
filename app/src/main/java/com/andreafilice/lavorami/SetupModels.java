package com.andreafilice.lavorami;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
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
        String title, description, smallDescription;
        int icon;

        public SetupPage(String title, String description, int icon, String smallDescription) {
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

            holder.img.setImageResource(page.icon);

            //*APP ICON
            /// In this section of the code, we will check if the user is in the first slide and disable the color.
            if(page.icon != R.drawable.ic_app) {
                holder.img.setVisibility(View.VISIBLE);
                holder.imgFirstSlide.setVisibility(View.GONE);

                holder.imgFirstSlide.animate().cancel();
            }
            else {
                holder.img.setVisibility(View.GONE);
                holder.imgFirstSlide.setVisibility(View.VISIBLE);
                holder.imgFirstSlide.animate().cancel();

                holder.imgFirstSlide.setAlpha(0f);
                holder.imgFirstSlide.setScaleX(0.7f);
                holder.imgFirstSlide.setScaleY(0.7f);

                holder.imgFirstSlide.animate()
                        .alpha(1f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(1000)
                        .setInterpolator(new OvershootInterpolator(1.3f))
                        .start();
            }

            //*LANGUAGE DROPDOWN
            /// In this section of the code, we create a Dropdown for choose the starting Language of the application.
            if (position == 0) {
                holder.spinnerSetup.setVisibility(View.VISIBLE);

                String[] languageNames = {"Italiano", "English", "Español"};
                int[] languageFlags = {R.drawable.ic_italy, R.drawable.ic_uk, R.drawable.ic_spain};
                String[] languageCodes = {"it", "en", "es"};

                ArrayAdapter<String> languageAdapter = new ArrayAdapter<String>(holder.itemView.getContext(), R.layout.spinner_item_language, R.id.languageText, Arrays.asList(languageNames)) {
                    @Override
                    public View getView(int pos, View convertView, ViewGroup parent) {
                        View v = super.getView(pos, convertView, parent);
                        ((ImageView) v.findViewById(R.id.flagIcon)).setImageResource(languageFlags[pos]);
                        return v;
                    }

                    @Override
                    public View getDropDownView(int pos, View convertView, ViewGroup parent) {
                        View v = super.getDropDownView(pos, convertView, parent);
                        ((ImageView) v.findViewById(R.id.flagIcon)).setImageResource(languageFlags[pos]);
                        return v;
                    }
                };

                String savedLanguage = DataManager.getStringData(DataKeys.KEY_DEFAULT_LANGUAGE, "Italiano");
                languageAdapter.setDropDownViewResource(R.layout.spinner_item_language);
                holder.spinnerSetup.setAdapter(languageAdapter);

                int savedPosition = Arrays.asList(languageNames).indexOf(savedLanguage);
                if (savedPosition >= 0)
                    holder.spinnerSetup.setSelection(savedPosition, false);

                holder.spinnerSetup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        DataManager.saveStringData(DataKeys.KEY_DEFAULT_LANGUAGE, languageNames[pos]);
                        LocaleListCompat localeList = LocaleListCompat.forLanguageTags(languageCodes[pos]);
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
            ImageView img, imgFirstSlide;
            Spinner spinnerSetup;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvSetupTitle);
                tvDesc = itemView.findViewById(R.id.tvSetupDesc);
                tvSetupSmallDesc = itemView.findViewById(R.id.tvSetupSmallDesc);
                img = itemView.findViewById(R.id.imgSetup);
                imgFirstSlide = itemView.findViewById(R.id.imgSetupFirst);
                spinnerSetup = itemView.findViewById(R.id.spinnerSetup);
            }
        }
    }
}