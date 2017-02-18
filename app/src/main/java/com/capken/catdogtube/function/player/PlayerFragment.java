package com.capken.catdogtube.function.player;

import android.os.Bundle;

import com.capken.catdogtubedomain.player.PlayerContract;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import org.jetbrains.annotations.NotNull;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class PlayerFragment extends YouTubePlayerFragment
        implements YouTubePlayer.OnInitializedListener, PlayerContract.View {

    private final String KEY = "AIzaSyBHs3tQKF67rsa-p94hVyk2a9qozOI0DJk";

    private YouTubePlayer player;
    private String videoId = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(KEY, this);
        setVideoId("Ndbe8XbpYVc");
    }

    @Override
    public void onDestroy() {
        if (player != null) {
            player.release();
        }
        super.onDestroy();
    }


    private void setVideoId(String videoId) {
        if (videoId != null && videoId != this.videoId) {
            this.videoId = videoId;
            if (player != null) {
                player.cueVideo(videoId);
            }
        }
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    //MARK: YouTubePlayer.OnInitializedListener
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
        this.player = youTubePlayer;
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        //player.setOnFullscreenListener(activity as VideoListDemoActivity)
        if (!restored && videoId != null) {
            //player.cueVideo(videoId);
            player.loadVideo(videoId);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        this.player = null;
    }


    //MARK: PlayerContract.View
    @Override
    public boolean loadPlayerView(@NotNull String videoId) {
        return false;
    }

    @Override
    public void loadVideo(@NotNull String videoId) {

    }

    @Override
    public void play() {

    }

    @Override
    public void showPlayer() {

    }
}
