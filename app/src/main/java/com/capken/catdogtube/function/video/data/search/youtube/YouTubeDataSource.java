package com.capken.catdogtube.function.video.data.search.youtube;

import com.capken.catdogtubedomain.video.data.YouTubeVideo;
import com.capken.catdogtubedomain.video.domain.search.SearchVideoDataSourceProtocol;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class YouTubeDataSource implements SearchVideoDataSourceProtocol {

    @Override
    public void searchVideos(@NotNull String searchWord, @NotNull Function1<? super List<YouTubeVideo>, Unit> completionHandler) {

        YouTubeVideo v1 = new YouTubeVideo("id1", "title1", "http://www.yahoo.co.jp");
        YouTubeVideo v2 = new YouTubeVideo("id2", "title2", "http://www.yahoo.co.jp");
        List<YouTubeVideo> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        completionHandler.invoke(list);
    }



}
