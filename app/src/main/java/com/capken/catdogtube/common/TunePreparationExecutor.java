package com.capken.catdogtube.common;

import android.content.Context;
import android.widget.Toast;

import com.capken.catdogtubedomain.video.domain.model.ContentType;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by ken on 2018/01/13..
 */

public class TunePreparationExecutor {

    private final Context mContext;

    public TunePreparationExecutor(Context context) {
        mContext = context;
    }

    private ExecutorService mBackgroundExecutor = Executors.newSingleThreadExecutor();

    public void execute(List<TuneCommand> list) {
        for ( TuneCommand command : list) {
            Future<Result> future = mBackgroundExecutor.submit(command);
            try {
                Result result = future.get();
                if (result.equals(Result.Fail)) {
                    exitWithError();
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                exitWithError();
            } catch (ExecutionException e) {
                e.printStackTrace();
                exitWithError();
            }
        }
    }

    private void exitWithError() {
        Toast.makeText(mContext, "Errorrr", Toast.LENGTH_LONG).show();
    }

}
