package com.andreafilice.lavorami;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SetupModels {

    public static class SetupPage {
        String title, description, icon, smallDescription;

        public SetupPage(String title, String description, String icon, String smallDescription){
            this.title = title;
            this.description = description;
            this.icon = icon;
            this.smallDescription = smallDescription;
        }
    }

    public static class SetupAdapter extends RecyclerView.Adapter<SetupAdapter.ViewHolder> {

        private List<SetupPage> pages;

        public SetupAdapter(List<SetupPage> pages){this.pages = pages;}

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

            if(page.smallDescription.trim() != "") {
                holder.tvSetupSmallDesc.setVisibility(View.VISIBLE);
                holder.tvSetupSmallDesc.setText(page.smallDescription);
            }
            else
                holder.tvSetupSmallDesc.setVisibility(View.GONE);

            int resID = holder.itemView.getContext().getResources().getIdentifier(page.icon, "drawable", holder.itemView.getContext().getPackageName());
            holder.img.setImageResource(resID);
        }

        @Override
        public int getItemCount() {return pages.size();}

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDesc, tvSetupSmallDesc;
            ImageView img;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvSetupTitle);
                tvDesc = itemView.findViewById(R.id.tvSetupDesc);
                tvSetupSmallDesc = itemView.findViewById(R.id.tvSetupSmallDesc);
                img = itemView.findViewById(R.id.imgSetup);
            }
        }
    }
}
