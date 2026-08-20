package com.andreafilice.lavorami.wrapped;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import com.andreafilice.lavorami.R;

public class StoryFragment extends Fragment {

    private static final String ARG_VIDEO_RES = "video_res";

    private ExoPlayer player;
    private PlayerView playerView;
    private boolean durationReported = false;

    public interface OnVideoReadyListener {
        void onVideoDurationReady(int durationMs);
    }

    private OnVideoReadyListener listener;

    public static StoryFragment newInstance(int videoResId) {
        StoryFragment fragment = new StoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_VIDEO_RES, videoResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        if (context instanceof OnVideoReadyListener) {
            listener = (OnVideoReadyListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_story, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Reset esplicito: se questo fragment viene riutilizzato (view ricreata
        // senza che l'istanza Fragment venga distrutta), il flag deve ripartire da zero.
        durationReported = false;

        playerView = view.findViewById(R.id.playerView);
        player = new ExoPlayer.Builder(requireContext()).build();
        playerView.setPlayer(player);

        int videoResId = getArguments() != null ? getArguments().getInt(ARG_VIDEO_RES) : 0;
        Uri videoUri = Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + videoResId);

        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.setPlayWhenReady(true);

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                // STATE_READY = il player conosce già la durata reale del media
                if (state == Player.STATE_READY && !durationReported) {
                    durationReported = true;
                    long durationMs = player.getDuration();
                    if (listener != null && durationMs > 0) {
                        listener.onVideoDurationReady((int) durationMs);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.play();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.pause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (player != null) {
            player.release();
            player = null;
        }
    }
}