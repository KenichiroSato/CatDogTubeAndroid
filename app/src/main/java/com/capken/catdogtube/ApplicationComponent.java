package com.capken.catdogtube;

import com.capken.catdogtube.function.player.PlayerPresenterModule;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentsPresenterModule;

import dagger.Component;

/**
 * Created by ken on 2017/06/25..
 */

@Component(modules = {ApplicationModule.class,
        PlayerPresenterModule.class,
        SegmentsPresenterModule.class})
public interface ApplicationComponent {

    void inject(MainActivity activity);

}
