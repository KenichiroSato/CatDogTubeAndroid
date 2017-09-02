package com.capken.catdogtube;

import com.capken.catdogtube.function.video.data.search.youtube.YouTubeDataParser;
import com.capken.catdogtubedomain.video.data.YouTubeVideo;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class YouTubeDataParserTests {

    private String response;

    @Before
    public void setUp() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("searchResponse.json");
        assertThat(in, is(notNullValue()));

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder strBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = br.readLine()) != null) {
                strBuilder.append(inputStr);
            }
            response = strBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testParse() throws Exception {
        List<YouTubeVideo> videos = YouTubeDataParser.parse(response);
        YouTubeVideo video = videos.get(0);

        assertEquals(video.getTitle(), "Three Little Kittens");
        assertEquals(video.getVideoId(), "k6X2wJ6L0SY");
        assertEquals(video.getImageUrl(), "https://i.ytimg.com/vi/k6X2wJ6L0SY/mqdefault.jpga");
    }

    @Test
    public void testCount() {
        List<YouTubeVideo> videos = YouTubeDataParser.parse(response);
        assertEquals(videos.size(), 10);
    }

    @Test
    public void testToken() {
        String token = YouTubeDataParser.getPageToken(response);
        assertEquals(token, "CAoQAA");
    }

}