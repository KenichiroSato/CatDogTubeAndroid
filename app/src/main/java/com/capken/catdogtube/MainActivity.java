package com.capken.catdogtube;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.capken.catdogtube.function.player.PlayerFragment;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentFactory;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentedFragment;
import com.capken.catdogtubedomain.player.PlayVideoPresenter;
import com.capken.catdogtubedomain.player.PlayerContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentedContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentsPresenter;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class MainActivity extends AppCompatActivity implements
        SegmentedFragment.PresenterOwner,
        PlayerFragment.PresenterOwner {

    private PlayVideoPresenter mPlayerPresenter;
    private SegmentsPresenter mSegmentsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    @Override
    public void bindToPresenter(SegmentedContract.View view) {
        if (mSegmentsPresenter == null) {
            mSegmentsPresenter =
                    new SegmentsPresenter(view, mPlayerPresenter, new SegmentFactory(this));
            view.setPresenter(mSegmentsPresenter);
        }
    }

    @Override
    public void bindToPresenter(PlayerContract.View view) {
        if (mPlayerPresenter == null) {
            mPlayerPresenter = new PlayVideoPresenter(view);
        }
    }
}
