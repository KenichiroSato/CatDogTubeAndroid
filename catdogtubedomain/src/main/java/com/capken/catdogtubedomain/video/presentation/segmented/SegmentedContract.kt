package com.capken.catdogtubedomain.video.presentation.segmented

/**
 * Created by ken on 2017/01/21..
 */

interface SegmentedContract {
    interface View {
        fun show(segments: List<SegmentProtocol>)

        fun reorder(segments: List<SegmentProtocol>)

    }

    interface Presenter {
        fun loadSegments()
    }
}