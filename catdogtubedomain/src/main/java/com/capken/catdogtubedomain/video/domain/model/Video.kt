package com.capken.catdogtubedomain.video.domain.model

import java.net.URL

/**
 * Created by ken on 2017/01/14..
 */

class Video (val videoId: String,
             val title: String,
             val imageUrl: URL,
             val contentType: ContentType) {

    fun describe() : String {
        return videoId + " " + title + " " + imageUrl + " " + contentType
    }
}