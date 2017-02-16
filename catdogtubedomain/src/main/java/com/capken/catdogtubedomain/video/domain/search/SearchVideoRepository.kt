package com.capken.catdogtubedomain.video.domain.search

import com.capken.catdogtubedomain.video.data.YouTubeVideo
import com.capken.catdogtubedomain.video.domain.VideoTranslater
import com.capken.catdogtubedomain.video.domain.model.ContentType
import com.capken.catdogtubedomain.video.domain.model.Video

/**
 * Created by ken on 2017/02/11..
 */

interface SearchVideoDataSourceProtocol {
    fun searchVideos(searchWord:String, completionHandler: (videoEntities:List<YouTubeVideo>?) -> Unit)
}

class SearchVideoRepository(val dataSource: SearchVideoDataSourceProtocol): SearchVideoRepositoryProtocol {

    override fun searchVideos(keyword:String, contentType: ContentType,
                     completionHandler: (videos:List<Video>?) -> Unit) {
        dataSource.searchVideos(keyword, { videoEntities ->
                val videos = VideoTranslater.translate(videoEntities, contentType)
            completionHandler(videos)
        })
    }
}