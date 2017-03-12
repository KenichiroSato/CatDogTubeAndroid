package com.capken.catdogtube.function.video.presentation.segmented;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.domain.model.ContentType;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentProtocol;

import org.jetbrains.annotations.NotNull;

/**
 * Created by ken on 2017/03/12..
 */

class SearchSegment implements SegmentProtocol {

    private SegmentContract.View mView;
    private ContentType mType;

    SearchSegment(SegmentContract.View view, ContentType type) {
        mView = view;
        mType = type;
    }

    @Override
    public int iconResourceId() {
        switch (mType) {
            case dog:
                return R.drawable.cat;
            case cat:
                return R.drawable.cat;
            default:
                return R.drawable.cat;
        }
    }

    @NotNull
    @Override
    public SegmentContract.View view() {
        return mView;
    }
}
