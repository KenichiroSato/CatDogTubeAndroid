package com.capken.catdogtube;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.capken.catdogtube.function.player.PlayerFragment;

/**
 * Created by 2ndDisplay on 2017/02/17.
 */

public final class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        setupViews();
    }

    private void setupViews() {
        PlayerFragment playerFragment = (PlayerFragment)getFragmentManager().findFragmentById(R.id.player_fragment);
        //PlayVideoPresenter videoPresenter = new PlayVideoPresenter(playerFragment);
        Log.d("tag", "setupViews");
    }
}
