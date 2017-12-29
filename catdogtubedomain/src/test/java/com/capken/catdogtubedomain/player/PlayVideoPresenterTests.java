package com.capken.catdogtubedomain.player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
/**
 * Created by ken on 2017/12/17..
 */

public class PlayVideoPresenterTests {

    private PlayerContract.View mView;

    private PlayVideoPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mView = mock(PlayerContract.View.class);
        mPresenter = new PlayVideoPresenter(mView);
    }

    @Test
    public void testShouldPlayVideo1() {
        boolean hasPlayed = false;
        assertTrue(mPresenter.shouldPlayVideo(hasPlayed));
    }

    @Test
    public void testShouldPlayVideo2() {
        boolean hasPlayed = true;
        assertFalse(mPresenter.shouldPlayVideo(hasPlayed));
    }
}
