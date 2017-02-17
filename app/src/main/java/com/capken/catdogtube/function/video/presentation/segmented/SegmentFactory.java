package com.capken.catdogtube.function.video.presentation.segmented;

import android.content.Context;

import com.capken.catdogtube.common.ThreadExecutor;
import com.capken.catdogtube.function.video.data.search.youtube.YouTubeDataSource;
import com.capken.catdogtube.function.video.domain.search.SearchWordProvider;
import com.capken.catdogtube.function.video.presentation.collection.VideoCollectionFragment;
import com.capken.catdogtubedomain.player.PlayerContract;
import com.capken.catdogtubedomain.video.domain.model.ContentType;
import com.capken.catdogtubedomain.video.domain.search.SearchVideoRepository;
import com.capken.catdogtubedomain.video.domain.search.SearchVideoUseCase;
import com.capken.catdogtubedomain.video.presentation.collection.LoadVideoPresenter;
import com.capken.catdogtubedomain.video.presentation.segmented.SearchSegment;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentFactoryProtocol;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentProtocol;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class SegmentFactory implements SegmentFactoryProtocol {

    private Context context;

    public SegmentFactory(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public List<SegmentProtocol> createSegments(@NotNull PlayerContract.Presenter playerPresenter) {
        List<SegmentProtocol> list = new ArrayList<>();
        list.add(searchSegment(ContentType.cat, playerPresenter));
        list.add(searchSegment(ContentType.dog, playerPresenter));
        return list;
    }

    private SearchSegment searchSegment(ContentType contentType, PlayerContract.Presenter playerPresenter) {

        VideoCollectionFragment fragment = new VideoCollectionFragment();

        SearchVideoRepository repo = new SearchVideoRepository(new YouTubeDataSource());
        SearchWordProvider provider = new SearchWordProvider(context);
        SearchVideoUseCase useCase = new SearchVideoUseCase(repo, contentType, provider);

        LoadVideoPresenter presenter = new LoadVideoPresenter(useCase, new ThreadExecutor(), playerPresenter);
        fragment.setPresenter(presenter);

        return new SearchSegment(fragment, contentType, presenter);
        /*
        let vc = UIStoryboard.instantiateVcWithId(VideoCollectionVC.ID)
        as! VideoCollectionVC

        let useCase = SearchVideoUseCase(content: contentType,
                repo:  SearchVideoRepository(dataSource: YouTubeDataSource()),
        wordProvider:SearchWordProvider())
        let presenter = LoadVideoPresenter(
                useCase: useCase, executor:ThreadExecutor(), playerPresenter:playerPresenter)
        vc.presenter = presenter

        return SearchSegment(view: vc, contentType: contentType, presenter:presenter)
        */
    }

}
