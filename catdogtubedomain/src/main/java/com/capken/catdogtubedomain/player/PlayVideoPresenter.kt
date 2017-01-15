package com.capken.catdogtubedomain.player

import com.capken.catdogtubedomain.video.domain.model.Video

/**
 * Created by ken on 2017/01/14..
 */

class PlayVideoPresenter(val view: PlayerContract.View): PlayerContract.Presenter {

    //var view: PlayerContract.View

    private var hasPlayed = false

    // MARK: PlayerContract_Presenter
    override fun onVideoTapped(video: Video) {
        play(video)
    }

    override fun onVideoLoaded(videos: List<Video>) {
        //guard let video = videos.first else { return }
        //https://medium.com/@adinugroho/unwrapping-sort-of-optional-variable-in-kotlin-9bfb640dc709#.ctjauxvx4
        val video = videos.first()?.let { it } ?: return

        if (shouldPlayVideo(hasPlayed)) {
            play(video)
        }
    }

    fun shouldPlayVideo(hasPlayed: Boolean) : Boolean {
        if (!hasPlayed) {
            return true
        }
        return false
    }

    private fun play(video: Video) {
        if (!hasPlayed) {
            hasPlayed = view.loadPlayerView(video.videoId)
        } else {
            view.loadVideo(video.videoId)
        }
    }

}