package com.capken.catdogtube.function.video.presentation.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.domain.model.Video;
import com.capken.catdogtubedomain.video.presentation.collection.VideoCollectionContract;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by 2ndDisplay on 2017/02/16.
 */

public final class VideoCollectionFragment extends Fragment implements VideoCollectionContract.View {

    private RecyclerView mVideoRecyclerView;
    private ImageView mReloadIcon;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private VideoCollectionContract.Presenter mPresenter;

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_collection, container, false);
        setupVideoRecyclerView(view);
        mReloadIcon = (ImageView) view.findViewById(R.id.loading_icon);
        mReloadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadVideo(true);
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.pull_to_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        return view;
    }

    private void setupVideoRecyclerView(View view) {
        mVideoRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mVideoRecyclerView.setHasFixedSize(true);
        final StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        mVideoRecyclerView.setLayoutManager(gridLayoutManager);
        final RecyclerViewAdapter rcAdapter =
                new RecyclerViewAdapter(getContext(), new VideoTappedListener() {
                    @Override
                    public void onTapped(Video video) {
                        if (mPresenter != null) {
                            mPresenter.onVideoTapped(video);
                        }
                    }
                });

        RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // int[0] : left row of recyclerView
                // int[1] : right row of recyclerView
                int[] lastVisible =
                        gridLayoutManager.findLastCompletelyVisibleItemPositions(null);
                mPresenter.onScrolled(lastVisible[1]);
            }
        };
        mVideoRecyclerView.addOnScrollListener(mScrollListener);
        mVideoRecyclerView.setAdapter(rcAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.loadVideo(true);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    //MARK VideoCollectionContract.View
    @Override
    public void setPresenter(VideoCollectionContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void show(@NotNull List<Video> videos) {
        ((RecyclerViewAdapter) mVideoRecyclerView.getAdapter()).setVideoList(videos);
    }

    @Override
    public void showErrorUI() {
        Toast.makeText(getContext(), R.string.MSG_FAILED_TO_LOAD, Toast.LENGTH_SHORT).show();
        mVideoRecyclerView.setVisibility(View.GONE);
        mReloadIcon.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void hideErrorUI() {
        mVideoRecyclerView.setVisibility(View.VISIBLE);
        mReloadIcon.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadingIndicator() {
        mVideoRecyclerView.setVisibility(View.GONE);
        mReloadIcon.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener =
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mPresenter.loadVideo(false);
                }
            };
}
