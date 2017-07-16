package com.capken.catdogtubedomain.video.domain

import com.capken.catdogtubedomain.video.domain.model.Video

/**
 * Created by ken on 2017/01/28..
 */

interface LoadVideoUseCase {

    fun loadVideos(token: String?, completionHandler: (videos:List<Video>?, token:String) -> Unit)

}