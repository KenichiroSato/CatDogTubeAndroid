package com.capken.catdogtube.common;

import android.os.Handler;
import android.os.Looper;

import com.capken.catdogtubedomain.ThreadExecutorProtocol;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class ThreadExecutor implements ThreadExecutorProtocol {

    private static final int THREAD_NUM = 2;

    private ExecutorService mBackgroundExecutor = Executors.newFixedThreadPool( THREAD_NUM );

    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    @Override
    public void runOnMain(@NotNull final Function0<Unit> block) {
        if (isMainThread()) {
            block.invoke();
        } else {
            mMainHandler.post(createRunnable(block));
        }
    }

    @Override
    public void runOnBackground(@NotNull final Function0<Unit> block) {
        mBackgroundExecutor.submit(createRunnable(block));
    }

    private boolean isMainThread() {
        Looper mainLooper = Looper.getMainLooper();
        return Thread.currentThread() == mainLooper.getThread();
    }

    private Runnable createRunnable(@NotNull final Function0<Unit> block) {
        return new Runnable() {
            @Override
            public void run() {
                block.invoke();
            }
        };
    }
}
