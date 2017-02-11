package com.capken.catdogtubedomain.video.presentation.collection

import com.capken.catdogtubedomain.video.domain.model.Video
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentContract

/**
 * Created by ken on 2017/01/28..
 * Contract for VideoCollection class
 */

interface VideoCollectionContract {

    interface View: SegmentContract.View {
        fun show(videos:List<Video>)

        fun showErrorUI()

        fun hideErrorUI()

        fun showLoadingIndicator()
    }

    interface Presenter: SegmentContract.Presenter {
        fun set(view:VideoCollectionContract.View)

        fun loadVideo(withFullScreenIndicator:Boolean)

        fun onVideoTapped(video: Video)

    }
}