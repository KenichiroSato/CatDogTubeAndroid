package com.capken.catdogtube.common;

import android.content.Context;

/**
 * Created by ken on 2017/03/25..
 */

public class Screen {
    public static boolean isTablet(Context context) {
        return context.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }
}
