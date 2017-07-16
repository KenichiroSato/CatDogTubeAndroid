package com.capken.catdogtubedomain.video.domain.search

import com.capken.catdogtubedomain.video.domain.LoadVideoUseCase
import com.capken.catdogtubedomain.video.domain.model.ContentType
import com.capken.catdogtubedomain.video.domain.model.Video

/**
 * Created by ken on 2017/02/11..
 */

interface SearchVideoRepositoryProtocol {
    fun searchVideos(keyword:String,
                     contentType: ContentType,
                     token: String?,
                     completionHandler:  (videos:List<Video>?, token:String)-> Unit)
}

class SearchVideoUseCase(private val repository:SearchVideoRepositoryProtocol,
                          private val contentType: ContentType,
                          private val searchWordProvider: SearchWordProviderProtocol)
    : LoadVideoUseCase {

    // MARK: - LoadVideoUseCase
    override fun loadVideos(token: String?,
                            completionHandler: (videos:List<Video>?, token:String) -> Unit) {
        repository.searchVideos(searchWordProvider.searchWord(contentType),
                contentType, token) { videos, token ->
                val okVideos = VideoExcluder.excludeInappropriateVideos(videos)
            completionHandler(okVideos, token)
        }
    }
}
