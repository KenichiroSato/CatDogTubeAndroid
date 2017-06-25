package com.capken.catdogtube.function.player;

import com.capken.catdogtubedomain.player.PlayVideoPresenter;
import com.capken.catdogtubedomain.player.PlayerContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ken on 2017/06/25..
 */

@Module
public class PlayerPresenterModule {

    private final PlayerContract.View mView;

    public PlayerPresenterModule(PlayerContract.View view) {
        mView = view;
    }

    @Provides
    PlayerContract.Presenter providePlayerContractPresenter() {
        return new PlayVideoPresenter(mView);
    }

}
