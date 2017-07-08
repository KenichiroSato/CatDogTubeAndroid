package com.capken.catdogtubedomain.player

import com.capken.catdogtubedomain.video.domain.model.Video

/**
 * Created by ken on 2017/01/14..
 */

class PlayerContract {
    interface View {
        /**
        call this when video is played for the first time
        This will initialize the video module
        - returns: true when play succeed, false if fail
         */
        fun loadPlayerView(videoId: String) : Boolean

        /**
        Call this when video module is already initialized.
         */
        fun loadVideo(videoId: String)

        fun play()

        fun pause()

        fun showPlayer()

        //only Android
        fun changeLayout(isFullScreen: Boolean)

    }

    interface Presenter {
        fun onVideoLoaded(videos: List<Video>)

        fun onVideoTapped(video: Video)

        //Android only
        fun updateLayout(isFullScreen: Boolean)
    }
}