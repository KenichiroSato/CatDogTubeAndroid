package com.capken.catdogtube;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.capken.catdogtube.function.player.PlayerFragment;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentFactory;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentedFragment;
import com.capken.catdogtubedomain.player.PlayVideoPresenter;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentsPresenter;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        setupViews();
    }

    private void setupViews() {
        PlayerFragment playerFragment = (PlayerFragment)getFragmentManager().findFragmentById(R.id.player_fragment);
        PlayVideoPresenter videoPresenter = new PlayVideoPresenter(playerFragment);

        SegmentedFragment segmentedFragment = (SegmentedFragment)getSupportFragmentManager().findFragmentById(R.id.container_segmented);
        SegmentsPresenter segmentsPresenter = new SegmentsPresenter(segmentedFragment, videoPresenter, new SegmentFactory(this));
        segmentedFragment.setPresenter(segmentsPresenter);
        segmentsPresenter.loadSegments();
/*
        let segmentedVC = UIStoryboard.instantiateVcWithId(SegmentedVC.ID) as! SegmentedVC
        segmentedVC.view.frame = segmentContainerView.bounds
        let presenter = SegmentsPresenter(view: segmentedVC as SegmentedContract_View,
                playerPresenter: playerPresenter,
                segmentFactory: SegmentFactory())
        segmentedVC.presenter = presenter
        segmentContainerView.addSubview(segmentedVC.view)
        self.addChildViewController(segmentedVC)
        segmentedVC.didMove(toParentViewController: self)
        */

        Log.d("tag", "setupViews");
    }
}
