package com.capken.catdogtubedomain.video.data

/**
 * Created by ken on 2017/01/21..
 */

class YouTubeVideo(val videoId: String,
                   val title: String,
                   val imageUrl: String) {

    fun description(): String = (videoId + "\n" + title + "\n" + imageUrl + "\n")
}
