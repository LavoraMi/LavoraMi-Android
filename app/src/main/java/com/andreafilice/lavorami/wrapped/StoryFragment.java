package com.andreafilice.lavorami.wrapped;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andreafilice.lavorami.R;

public class StoryFragment extends Fragment {
    private static final String ARG_POSITION = "position";

    public static StoryFragment newInstance(int position) {
        StoryFragment fragment = new StoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_story, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int position = getArguments() != null ? getArguments().getInt(ARG_POSITION) : 0;
        TextView title = view.findViewById(R.id.storyTitle);
        title.setText("Storia " + (position + 1));

        title.setAlpha(0f);
        title.setTranslationY(50f);
        title.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(500)
            .start();
    }
}