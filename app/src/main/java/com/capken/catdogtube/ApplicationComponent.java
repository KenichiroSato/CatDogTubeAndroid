package com.capken.catdogtube;

import com.capken.catdogtube.function.player.PlayerPresenterModule;
import com.capken.catdogtube.function.video.data.search.youtube.YouTubeDataSourceModule;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentsPresenterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ken on 2017/06/25..
 */

@Singleton
@Component(modules = {ApplicationModule.class,
        YouTubeDataSourceModule.class,
        PlayerPresenterModule.class,
        SegmentsPresenterModule.class})
public interface ApplicationComponent {

    void inject(MainActivity activity);

}
