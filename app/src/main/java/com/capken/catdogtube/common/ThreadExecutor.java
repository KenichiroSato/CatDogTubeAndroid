package com.capken.catdogtube.common;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.capken.catdogtubedomain.ThreadExecutorProtocol;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

import static android.R.attr.action;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class ThreadExecutor implements ThreadExecutorProtocol {

    @Override
    public void runOnMain(@NotNull final Function0<Unit> block) {
        Runnable action = new Runnable() {
            @Override
            public void run() {
                block.invoke();
            }
        };
        Looper mainLooper = Looper.getMainLooper();
        if (Thread.currentThread() != mainLooper.getThread()) {
            new Handler(Looper.getMainLooper()).post(action);
        } else {
            action.run();
        }

    }

    @Override
    public void runOnBackground(@NotNull final Function0<Unit> block) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                block.invoke();
            }
        }).start();
    }
}
