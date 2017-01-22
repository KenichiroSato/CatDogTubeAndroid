package com.capken.catdogtube

import android.os.Bundle
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment

/**
 * Created by ken on 2017/01/22..
 */

class PlayerFragment : YouTubePlayerFragment(), YouTubePlayer.OnInitializedListener {

    private val KEY = "AIzaSyBHs3tQKF67rsa-p94hVyk2a9qozOI0DJk"

    private var player: YouTubePlayer? = null
    private var videoId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize(KEY, this)
        setVideoId("Ndbe8XbpYVc")
    }

    override fun onDestroy() {
        if (player != null) {
            player!!.release()
        }
        super.onDestroy()
    }

    fun setVideoId(videoId: String?) {
        if (videoId != null && videoId != this.videoId) {
            this.videoId = videoId
            if (player != null) {
                player!!.cueVideo(videoId)
            }
        }
    }

    fun pause() {
        if (player != null) {
            player!!.pause()
        }
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, restored: Boolean) {
        this.player = player
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT)
        //player.setOnFullscreenListener(activity as VideoListDemoActivity)
        if (!restored && videoId != null) {
            //player.cueVideo(videoId)
            player.loadVideo(videoId)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, result: YouTubeInitializationResult) {
        this.player = null
    }

    companion object {

        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }

}