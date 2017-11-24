package com.capken.catdogtube.function.video.presentation.segmented;

import com.capken.catdogtubedomain.player.PlayerContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentedContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Provides SegmentedContract.Presenter
 */

@Module
public class SegmentsPresenterModule {

    private final SegmentedContract.View mView;

    public SegmentsPresenterModule(SegmentedContract.View view) {
        mView = view;
    }

    @Provides
    SegmentedContract.Presenter provideSegmentsPresenter(PlayerContract.Presenter player,
                                                         SegmentFactory factory) {
        return new SegmentsPresenter(mView, player, factory);
    }

}
