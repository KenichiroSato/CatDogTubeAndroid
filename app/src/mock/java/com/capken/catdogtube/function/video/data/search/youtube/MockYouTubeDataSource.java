package com.capken.catdogtube.function.video.data.search.youtube;

import com.capken.catdogtubedomain.video.data.YouTubeVideo;
import com.capken.catdogtubedomain.video.domain.search.SearchVideoDataSourceProtocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/**
 * Created by ken on 2017/11/24..
 */

public class MockYouTubeDataSource implements SearchVideoDataSourceProtocol {

    @Override
    public void searchVideos(@NotNull String searchWord,
                             @Nullable String token,
                             @NotNull final Function2<? super List<YouTubeVideo>,
                                     ? super String, Unit> completionHandler) {
        String res = readResponseFromFile();

        List<YouTubeVideo> videos = YouTubeDataParser.parse(res);
        String nextToken = YouTubeDataParser.getPageToken(res);
        completionHandler.invoke(videos, nextToken);
    }

    private String readResponseFromFile() {
        String response = "";
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream("res/raw/search_response.json");

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

        return response;
    }

}
