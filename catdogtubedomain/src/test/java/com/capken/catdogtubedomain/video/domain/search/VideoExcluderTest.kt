package com.capken.catdogtubedomain.video.domain.search

import com.capken.catdogtubedomain.video.domain.model.ContentType
import com.capken.catdogtubedomain.video.domain.model.Video
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test

/**
 * Created by ken on 2017/09/03..
 */
class VideoExcluderTest {

    private val videos: List<Video> = listOf(
            Video("1", "子犬のワルツ", "Yahoo", ContentType.cat),
            Video("2", "mmd video", "yahoo", ContentType.cat),
            Video("3", "ok video", "yahoo", ContentType.cat),
            Video("4", "ok video2", "yahoo", ContentType.cat)
    )

    @Test
    fun testExcludeVideo() {
        val excluded = VideoExcluder.excludeInappropriateVideos(videos)
        assertEquals(excluded!!.size, 2)
        val video = excluded!![0]
        assertEquals(video.title, "ok video")
    }


}