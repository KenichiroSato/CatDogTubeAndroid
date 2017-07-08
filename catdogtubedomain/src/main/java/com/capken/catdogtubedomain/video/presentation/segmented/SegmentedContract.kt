package com.capken.catdogtubedomain.video.presentation.segmented

/**
 * Created by ken on 2017/01/21..
 */

interface SegmentedContract {
    interface View {

        fun setPresenter(presenter: SegmentedContract.Presenter)

        fun show(segments: List<SegmentProtocol>)

        fun reorder(segments: List<SegmentProtocol>)

        //only Android
        fun setVisibility(isVisible: Boolean)
    }

    interface Presenter {
        fun loadSegments()

        //only Android
        fun updateLayout(isFullScreen: Boolean)
    }
}