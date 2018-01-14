package com.capken.catdogtube.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.Callable;

/**
 * Created by ken on 2018/01/13..
 */

public class TuneCommand implements Callable<Result> {

    private String mName;

    public TuneCommand(String name) {
        mName = name;
    }

    @Override
    public Result call() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Log.e("TuneCommand", "sleep error");
            return Result.Fail;
        }
        Log.d("TuneCommand", "called:" + mName);

        if (mName.equals("name3")) {

            return Result.Fail;
        }
        return Result.Success;
    }
}
