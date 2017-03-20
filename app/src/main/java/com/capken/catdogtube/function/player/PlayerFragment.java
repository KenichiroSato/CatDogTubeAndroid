package com.capken.catdogtube.function.player;

import android.content.Context;
import android.os.Bundle;

import com.capken.catdogtube.MainActivity;
import com.capken.catdogtube.function.video.data.search.youtube.YouTubeInfo;
import com.capken.catdogtubedomain.player.PlayerContract;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.jetbrains.annotations.NotNull;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class PlayerFragment extends YouTubePlayerSupportFragment
        implements YouTubePlayer.OnInitializedListener, PlayerContract.View {

    public interface PresenterOwner {
        void bindToPresenter(PlayerContract.View view);
    }

    private YouTubePlayer mPlayer;
    private String mVideoId = "";
    private PlayerContract.Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PlayerFragment.PresenterOwner) {
            PlayerFragment.PresenterOwner owner = (PlayerFragment.PresenterOwner) context;
            owner.bindToPresenter(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (mPlayer != null) {
            mPlayer.release();
        }
        super.onDestroy();
    }


    private void setVideoId(String mVideoId) {
        if (mVideoId != null && mVideoId != this.mVideoId) {
            this.mVideoId = mVideoId;
            if (mPlayer != null) {
                mPlayer.cueVideo(mVideoId);
            }
        }
    }

    public void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
        }
    }

    //MARK: YouTubePlayer.OnInitializedListener
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
        this.mPlayer = youTubePlayer;
        mPlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        mPlayer.setOnFullscreenListener((MainActivity )getActivity());
        if (!restored && mVideoId != null) {
            //mPlayer.cueVideo(mVideoId);
            mPlayer.loadVideo(mVideoId);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        this.mPlayer = null;
    }


    //MARK: PlayerContract.View
    @Override
    public boolean loadPlayerView(@NotNull String videoId) {
        initialize(YouTubeInfo.INFO, this);
        mVideoId = videoId;
        return true;
    }

    @Override
    public void loadVideo(@NotNull String videoId) {
        if (mPlayer == null) {
            loadPlayerView(videoId);
            return;
        }
        mPlayer.loadVideo(videoId);
        mVideoId = videoId;
    }

    @Override
    public void play() {
        mPlayer.play();
    }

    @Override
    public void showPlayer() {

    }
}
