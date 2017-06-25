package com.capken.catdogtube;

import dagger.Component;

/**
 * Created by ken on 2017/06/25..
 */

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
