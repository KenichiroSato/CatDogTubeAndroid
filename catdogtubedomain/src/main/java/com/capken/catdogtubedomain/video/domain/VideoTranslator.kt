package com.capken.catdogtubedomain.video.domain

import com.capken.catdogtubedomain.video.data.YouTubeVideo
import com.capken.catdogtubedomain.video.domain.model.ContentType
import com.capken.catdogtubedomain.video.domain.model.Video

/**
 * Created by ken on 2017/01/28..
 *   This class translates YouTubeVideo(which is used in Data layer) into
 * Video(which is used in Domain/Presentation layer)
 */

internal class VideoTranslater {

    companion object {
        fun translate(videos: List<YouTubeVideo>?, contentType: ContentType): List<Video>? {
            return videos?.mapNotNull { translateVideo(it, contentType) }
            //return videos?.map{this.translateVideo(it, contentType)}.requireNoNulls()
        }

        private fun translateVideo(entity: YouTubeVideo, contentType: ContentType): Video? {
            // cannot converted from Swift

            val url = entity.imageUrl
            return Video(entity.videoId, entity.title, url, contentType)
        }
    }

/*
    class func translateVideos(_ videos:[FavoriteVideo]?) -> [Video]? {
        return videos?.flatMap(translateVideo)
    }

    class private func translateVideo(_ entity:FavoriteVideo) -> Video? {
        guard let url = URL(string: entity.imageUrl),
            let contentType = ContentType(rawValue: entity.contentType) else {
            return nil
        }
        return Video(id: entity.videoId, title:entity.title , url: url, type: contentType)
    }
 */
}
