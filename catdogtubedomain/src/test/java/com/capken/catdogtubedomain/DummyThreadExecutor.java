package com.capken.catdogtubedomain;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by ken on 2017/12/17..
 */

public class DummyThreadExecutor implements ThreadExecutorProtocol {

    @Override
    public void runOnMain(@NotNull final Function0<Unit> block) {
        Runnable action = new Runnable() {
            @Override
            public void run() {
                block.invoke();
            }
        };
        action.run();
    }

    @Override
    public void runOnBackground(@NotNull final Function0<Unit> block) {
        Runnable action = new Runnable() {
            @Override
            public void run() {
                block.invoke();
            }
        };
        action.run();
    }

}
