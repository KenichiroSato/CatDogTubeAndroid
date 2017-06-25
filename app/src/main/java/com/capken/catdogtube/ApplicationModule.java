package com.capken.catdogtube;

import android.content.Context;

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

}
