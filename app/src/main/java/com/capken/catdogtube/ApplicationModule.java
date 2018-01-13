package com.capken.catdogtube;

import android.content.Context;

import com.capken.catdogtube.common.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ken on 2017/06/25..
 */

@Module
public class ApplicationModule {

    private final Context mContext;

    ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    ThreadExecutor provideThreadExecutor() { return new ThreadExecutor(); }

}
