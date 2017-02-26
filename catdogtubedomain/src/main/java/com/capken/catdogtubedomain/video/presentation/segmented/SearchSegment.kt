package com.capken.catdogtubedomain.video.presentation.segmented

import com.capken.catdogtubedomain.video.domain.model.ContentType

/**
 * Created by ken on 2017/01/21..
 */

 class SearchSegment(val _view:SegmentContract.View,
                     internal val contentType:ContentType) : SegmentProtocol {

    //MARK: SegmentProtocol
    override fun iconName(): String {
        return when(contentType) {
            ContentType.cat -> "cat"
            ContentType.dog -> "dot"
        }
    }

    override fun view(): SegmentContract.View {
        return _view
    }

}
