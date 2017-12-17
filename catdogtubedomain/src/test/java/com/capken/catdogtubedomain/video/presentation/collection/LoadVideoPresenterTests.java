package com.capken.catdogtubedomain.video.presentation.collection;

import com.capken.catdogtubedomain.DummyThreadExecutor;
import com.capken.catdogtubedomain.player.PlayerContract;
import com.capken.catdogtubedomain.video.domain.LoadVideoUseCase;
import com.capken.catdogtubedomain.video.domain.model.ContentType;
import com.capken.catdogtubedomain.video.domain.model.Video;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by ken on 2017/12/17..
 */

public class LoadVideoPresenterTests {

    private List<Video> videos = Arrays.asList(
            new Video("1", "子犬のワルツ", "yahoo", ContentType.cat),
            new Video("2", "mmd video", "yahoo", ContentType.cat),
            new Video("3", "ok video", "yahoo", ContentType.cat),
            new Video("4", "ok video2", "yahoo", ContentType.cat)
    );

    private VideoCollectionContract.View mMockView;

    private PlayerContract.Presenter mMockPlayerPresenter;

    private LoadVideoUseCase mMockUseCase;

    @Before
    public void setup() {
        mMockView = mock(VideoCollectionContract.View.class);
        mMockPlayerPresenter = mock(PlayerContract.Presenter.class);
        mMockUseCase = mock(LoadVideoUseCase.class);

        doNothing().when(mMockView).setPresenter(any(VideoCollectionContract.Presenter.class));
        doNothing().when(mMockView).show(ArgumentMatchers.<Video>anyList());
        doNothing().when(mMockView).showErrorUI();
        doNothing().when(mMockView).hideErrorUI();
        doNothing().when(mMockView).showLoadingIndicator();

        doNothing().when(mMockPlayerPresenter).onVideoLoaded(ArgumentMatchers.<Video>anyList());
    }

    @Test
    public void testIndicator() {
        doNothing().when(mMockUseCase).loadVideos(anyString(),
                (Function2<? super List<Video>, ? super String, Unit>) any());

        LoadVideoPresenter presenter =
                new LoadVideoPresenter(mMockView, mMockUseCase,
                        new DummyThreadExecutor(), mMockPlayerPresenter);
        presenter.loadVideo();
        verify(mMockView).showLoadingIndicator();
    }

    @Test
    public void testLoadSuccess() {
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Function2<? super List<Video>, ? super String, Unit> func2 =
                        invocation.getArgument(1);
                func2.invoke(videos, "token");
                return null;
            }
        }).when(mMockUseCase).loadVideos(nullable(String.class), (Function2<? super List<Video>, ? super String, Unit>) any());

        LoadVideoPresenter presenter =
                new LoadVideoPresenter(mMockView, mMockUseCase,
                        new DummyThreadExecutor(), mMockPlayerPresenter);
        presenter.loadVideo();

        verify(mMockUseCase).loadVideos(nullable(String.class), (Function2<? super List<Video>, ? super String, Unit>) any());
        verify(mMockView, times(0)).showErrorUI();
        verify(mMockView, times(1)).hideErrorUI();
        verify(mMockView).show(ArgumentMatchers.<Video>anyList());
        verify(mMockPlayerPresenter, times(0)).onVideoLoaded(ArgumentMatchers.<Video>anyList());
    }

    @Test
    public void testLoadSuccessWithPrimal() {
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Function2<? super List<Video>, ? super String, Unit> func2 =
                        invocation.getArgument(1);
                func2.invoke(videos, "token");
                return null;
            }
        }).when(mMockUseCase).loadVideos(nullable(String.class), (Function2<? super List<Video>, ? super String, Unit>) any());

        LoadVideoPresenter presenter =
                new LoadVideoPresenter(mMockView, mMockUseCase,
                        new DummyThreadExecutor(), mMockPlayerPresenter);
        presenter.markAsPrimal();
        presenter.loadVideo();

        verify(mMockPlayerPresenter, times(1))
                .onVideoLoaded(ArgumentMatchers.<Video>anyList());
    }

    @Test
    public void testLoadFail() {
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Function2<? super List<Video>, ? super String, Unit> func2 =
                        invocation.getArgument(1);
                //invoke video list as null to simulate error case
                func2.invoke(null, "token");
                return null;
            }
        }).when(mMockUseCase).loadVideos(nullable(String.class), (Function2<? super List<Video>, ? super String, Unit>) any());

        LoadVideoPresenter presenter =
                new LoadVideoPresenter(mMockView, mMockUseCase,
                        new DummyThreadExecutor(), mMockPlayerPresenter);
        presenter.loadVideo();

        verify(mMockUseCase).loadVideos(nullable(String.class), (Function2<? super List<Video>, ? super String, Unit>) any());
        verify(mMockView, times(1)).showErrorUI();
        verify(mMockView, times(0)).hideErrorUI();
        verify(mMockView).show(ArgumentMatchers.<Video>anyList());
        verify(mMockPlayerPresenter, times(0))
                .onVideoLoaded(ArgumentMatchers.<Video>anyList());
    }

}
