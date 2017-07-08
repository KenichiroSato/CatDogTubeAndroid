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
        mVideoRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
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
        if (mVideoRecyclerView != null) {
            mVideoRecyclerView.setHasFixedSize(true);
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
            mVideoRecyclerView.setLayoutManager(gridLayoutManager);
            RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getContext(), videos, mPresenter);
            mVideoRecyclerView.setAdapter(rcAdapter);
        }
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

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.loadVideo(false);
        }
    };
}
