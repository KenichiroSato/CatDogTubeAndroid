package com.capken.catdogtube.function.video.presentation.segmented;

import android.content.Context;

import com.capken.catdogtube.function.video.domain.search.SearchWordProvider;
import com.capken.catdogtubedomain.player.PlayerContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentedContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ken on 2017/06/25..
 */

@Module
public class SegmentsPresenterModule {

    private final SegmentedContract.View mView;

    public SegmentsPresenterModule(SegmentedContract.View view) {
        mView = view;
    }

    @Provides
    SegmentedContract.View provideSegmentedContractView() {
        return mView;
    }

    @Provides
    SegmentedContract.Presenter provideSegmentsPresenter(Context context,
                                                         PlayerContract.Presenter player) {
        SegmentFactory factory = new SegmentFactory(new SearchWordProvider(context));
        return new SegmentsPresenter(mView, player, factory);
    }

}
