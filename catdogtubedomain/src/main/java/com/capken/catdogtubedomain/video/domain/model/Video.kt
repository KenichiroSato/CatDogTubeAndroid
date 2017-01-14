package com.capken.catdogtubedomain.video.domain.model

/**
 * Created by ken on 2017/01/14..
 */

class Video (val videoId: String,
                    val title: String,
                    val imageUrl: String) {

    fun describe() : String {
        return videoId + " " + title
    }
}