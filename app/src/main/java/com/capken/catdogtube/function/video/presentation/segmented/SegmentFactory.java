package com.capken.catdogtube.function.video.presentation.segmented;

import com.capken.catdogtube.common.ThreadExecutor;
import com.capken.catdogtube.function.video.data.search.youtube.YouTubeDataSource;
import com.capken.catdogtube.function.video.domain.search.SearchWordProvider;
import com.capken.catdogtube.function.video.presentation.collection.VideoCollectionFragment;
import com.capken.catdogtubedomain.player.PlayerContract;
import com.capken.catdogtubedomain.video.domain.model.ContentType;
import com.capken.catdogtubedomain.video.domain.search.SearchVideoRepository;
import com.capken.catdogtubedomain.video.domain.search.SearchVideoUseCase;
import com.capken.catdogtubedomain.video.presentation.collection.LoadVideoPresenter;
import com.capken.catdogtubedomain.video.presentation.collection.VideoCollectionContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentFactoryProtocol;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentProtocol;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class SegmentFactory implements SegmentFactoryProtocol {

    private final SearchWordProvider mWordProvider;

    public SegmentFactory(SearchWordProvider provider) {
        mWordProvider = provider;
    }

    @NotNull
    @Override
    public List<SegmentProtocol> createSegments(@NotNull PlayerContract.Presenter playerPresenter) {
        List<SegmentProtocol> list = new ArrayList<>();
        list.add(searchSegment(0, ContentType.cat, playerPresenter));
        list.add(searchSegment(1, ContentType.dog, playerPresenter));
        return list;
    }

    private SearchSegment searchSegment(int index,
                                        ContentType contentType,
                                        PlayerContract.Presenter playerPresenter) {

        VideoCollectionFragment fragment = new VideoCollectionFragment();

        SearchVideoRepository repo = new SearchVideoRepository(new YouTubeDataSource());
        SearchVideoUseCase useCase = new SearchVideoUseCase(repo, contentType, mWordProvider);

        VideoCollectionContract.Presenter presenter =
                new LoadVideoPresenter(fragment, useCase, new ThreadExecutor(), playerPresenter);
        if (index == 0) {
            presenter.markAsPrimal();
        }

        return new SearchSegment(fragment, contentType);
    }

}
