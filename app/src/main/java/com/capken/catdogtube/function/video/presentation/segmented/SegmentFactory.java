package com.capken.catdogtube.function.video.presentation.segmented;

import android.os.Bundle;

import com.capken.catdogtube.function.video.presentation.collection.VideoCollectionFragment;
import com.capken.catdogtubedomain.player.PlayerContract;
import com.capken.catdogtubedomain.video.domain.model.ContentType;
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

    public static final String KEY_CONTENT_TYPE = "key.contenttype";
    public static final String KEY_INDEX = "key.index";


    @NotNull
    @Override
    public List<SegmentProtocol> createSegments(@NotNull PlayerContract.Presenter presenter) {
        List<SegmentProtocol> list = new ArrayList<>();
        list.add(searchSegment(0, ContentType.cat));
        list.add(searchSegment(1, ContentType.dog));
        return list;
    }

    private SearchSegment searchSegment(int index, ContentType contentType) {

        VideoCollectionFragment fragment = new VideoCollectionFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(SegmentFactory.KEY_CONTENT_TYPE, contentType);
        arguments.putInt(SegmentFactory.KEY_INDEX, index);
        fragment.setArguments(arguments);

        return new SearchSegment(fragment, contentType);
    }

}
