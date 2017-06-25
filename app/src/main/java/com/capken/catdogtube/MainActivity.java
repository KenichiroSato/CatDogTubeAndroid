package com.capken.catdogtube;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;

import com.capken.catdogtube.common.Screen;
import com.capken.catdogtube.function.player.PlayerFragment;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentFactory;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentedFragment;
import com.capken.catdogtubedomain.player.PlayVideoPresenter;
import com.capken.catdogtubedomain.player.PlayerContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentedContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentsPresenter;
import com.google.android.youtube.player.YouTubePlayer;

import javax.inject.Inject;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class MainActivity extends AppCompatActivity implements
        YouTubePlayer.OnFullscreenListener {

    private PlayerContract.Presenter mPlayerPresenter;
    private SegmentedContract.Presenter mSegmentsPresenter;
    private PlayerFragment mPlayerFragment;
    private SegmentedFragment mSegmentedFragment;
    @Inject
    SegmentFactory mSegmentFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        if (Screen.isTablet(this)) {
            setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
        }

        injectDependency();
        setupPlayer();
        setupSearchSegments();
    }

    private void injectDependency() {
        DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplication().getApplicationContext()))
                .build()
                .inject(this);
    }

    private void setupPlayer() {
        mPlayerFragment = (PlayerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.player_fragment);
        mPlayerPresenter = new PlayVideoPresenter(mPlayerFragment);
    }

    private void setupSearchSegments() {
        mSegmentedFragment = (SegmentedFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container_segmented);
        mSegmentsPresenter =
                new SegmentsPresenter(mSegmentedFragment, mPlayerPresenter, mSegmentFactory);
    }

    @Override
    public void onFullscreen(boolean b) {
        updateLayout(b);
    }

    private void updateLayout(boolean isFullScreen) {
        LayoutParams playerParams =
                (LayoutParams) mPlayerFragment.getView().getLayoutParams();

        if (isFullScreen) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mSegmentedFragment.getView().setVisibility(View.GONE);
            playerParams.height = LayoutParams.MATCH_PARENT;

        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mSegmentedFragment.getView().setVisibility(View.VISIBLE);
            playerParams.height = Screen.isTablet(this) ?
                    LayoutParams.MATCH_PARENT : LayoutParams.WRAP_CONTENT;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayout(Screen.isLandscape(this));
    }

}
