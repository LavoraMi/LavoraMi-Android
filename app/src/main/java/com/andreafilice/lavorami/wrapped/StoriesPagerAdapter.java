package com.andreafilice.lavorami.wrapped;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.andreafilice.lavorami.R;

public class StoriesPagerAdapter extends FragmentStateAdapter {
    private final int totalStories;

    private final int[] id_storie = {R.raw.august_story1, R.raw.august_story2, R.raw.august_story3, R.raw.august_story4, R.raw.august_story5};

    public StoriesPagerAdapter(@NonNull FragmentActivity activity, int totalStories) {
        super(activity);
        this.totalStories = totalStories;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return StoryFragment.newInstance(id_storie[position]);
    }

    @Override
    public int getItemCount() {
        return totalStories;
    }
}