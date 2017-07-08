package com.capken.catdogtube.common;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by ken on 2017/03/25..
 */

public class Screen {
    public static boolean isTablet(Context context) {
        return context.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }

    public static  boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
    }
}
