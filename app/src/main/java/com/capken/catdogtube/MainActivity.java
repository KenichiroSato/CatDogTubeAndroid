package com.capken.catdogtube;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.WindowManager;

import com.capken.catdogtube.common.Screen;
import com.capken.catdogtube.function.player.PlayerFragment;
import com.capken.catdogtube.function.player.PlayerPresenterModule;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentedFragment;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentsPresenterModule;
import com.capken.catdogtubedomain.player.PlayerContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentedContract;
import com.google.android.youtube.player.YouTubePlayer;

import javax.inject.Inject;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class MainActivity extends AppCompatActivity implements
        YouTubePlayer.OnFullscreenListener {

    @Inject
    PlayerContract.Presenter mPlayerPresenter;

    @Inject
    SegmentedContract.Presenter mSegmentsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        if (Screen.isTablet(this)) {
            setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
        }

        injectDependency();
    }

    private void injectDependency() {
        PlayerFragment playerFragment = (PlayerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.player_fragment);
        SegmentedFragment segmentedFragment = (SegmentedFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container_segmented);

        ApplicationComponent comp = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplication().getApplicationContext()))
                .playerPresenterModule(new PlayerPresenterModule(playerFragment))
                .segmentsPresenterModule(new SegmentsPresenterModule(segmentedFragment))
                .build();
        comp.inject(this);
    }

    @Override
    public void onFullscreen(boolean b) {
        updateLayout(b);
    }

    private void updateLayout(boolean isFullScreen) {
        if (isFullScreen) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        mPlayerPresenter.updateLayout(isFullScreen);
        mSegmentsPresenter.updateLayout(isFullScreen);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayout(Screen.isLandscape(this));
    }

}
