package com.capken.catdogtubedomain.video.presentation.collection

import com.capken.catdogtubedomain.player.PlayerContract
import com.capken.catdogtubedomain.video.domain.model.Video
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentContract

/**
 * Created by ken on 2017/01/28..
 * Contract for VideoCollection class
 */

interface VideoCollectionContract {

    interface View: SegmentContract.View {

        fun setPresenter(presenter: VideoCollectionContract.Presenter)

        fun show(videos:List<Video>)

        fun showErrorUI()

        fun hideErrorUI()

        fun showLoadingIndicator()
    }

    interface Presenter: SegmentContract.Presenter {

        fun setPlayer(player: PlayerContract.Presenter)

        fun loadVideo()

        fun refreshVideos()

        fun onVideoTapped(video: Video)

        fun onScrolled(visiblePosition: Int)

    }
}