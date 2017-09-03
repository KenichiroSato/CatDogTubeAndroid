package com.capken.catdogtubedomain.video.domain

import com.capken.catdogtubedomain.video.data.YouTubeVideo
import com.capken.catdogtubedomain.video.domain.model.ContentType
import com.capken.catdogtubedomain.video.domain.model.Video
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.assertNotNull

/**
 * Created by ken on 2017/09/03..
 */
class VideoTranslaterTest {

    private val nonNilVideos = listOf<YouTubeVideo>(
            YouTubeVideo( "id1",  "title1",  "https://image1"),
            YouTubeVideo( "id2",  "title2",  "https://image2"),
            YouTubeVideo( "id3",  "title3",  "https://image3"),
            YouTubeVideo( "id4",  "title4",  "https://image4")
    )

    @Test
    fun testNonNilCase() {
        val videos: List<Video> = VideoTranslater.translate(nonNilVideos,  ContentType.cat)!!
        assertNotNull(videos)
        assertEquals(videos.size, 4)
        assertEquals(videos[2].videoId, "id3")
        assertEquals(videos[2].title, "title3")
        assertEquals(videos[2].imageUrl, "https://image3")
        assertEquals(videos[2].contentType, ContentType.cat)
    }

}