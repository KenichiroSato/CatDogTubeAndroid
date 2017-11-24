package com.capken.catdogtube.function.video.data.search.youtube;

import com.capken.catdogtubedomain.video.domain.search.SearchVideoDataSourceProtocol;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ken on 2017/11/24..
 */

@Module
public class YouTubeDataSourceModule {

    @Provides
    SearchVideoDataSourceProtocol provideVideoDataSource() {
        return new MockYouTubeDataSource();
    }
}
