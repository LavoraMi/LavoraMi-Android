package com.andreafilice.lavorami.wrapped;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StoriesPagerAdapter extends FragmentStateAdapter {

    private final int totalStories;

    public StoriesPagerAdapter(@NonNull FragmentActivity activity, int totalStories) {
        super(activity);
        this.totalStories = totalStories;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return StoryFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return totalStories;
    }
}