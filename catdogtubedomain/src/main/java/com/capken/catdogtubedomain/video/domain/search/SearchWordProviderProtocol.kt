package com.capken.catdogtubedomain.video.domain.search

import com.capken.catdogtubedomain.video.domain.model.ContentType

/**
 * Created by ken on 2017/02/11..
 */

interface SearchWordProviderProtocol {
    fun searchWord(content: ContentType) : String
}