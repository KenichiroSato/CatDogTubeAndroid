package com.capken.catdogtube.function.video.presentation.collection;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.capken.catdogtube.R;
import com.capken.catdogtube.function.video.presentation.segmented.SegmentFactory;
import com.capken.catdogtubedomain.video.domain.model.ContentType;
import com.capken.catdogtubedomain.video.domain.model.Video;
import com.capken.catdogtubedomain.video.presentation.collection.VideoCollectionContract;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by 2ndDisplay on 2017/02/16.
 */

public final class VideoCollectionFragment extends Fragment implements VideoCollectionContract.View {

    public interface PresenterOwner {
        void bindToPresenter(VideoCollectionContract.View view, ContentType type, int index);
    }

    private ListView mVideoListView;
    private ImageView mLoadingIcon;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private VideoCollectionContract.Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof VideoCollectionFragment.PresenterOwner) {
            VideoCollectionFragment.PresenterOwner owner = (VideoCollectionFragment.PresenterOwner) context;
            ContentType type = (ContentType) getArguments().getSerializable(SegmentFactory.KEY_CONTENT_TYPE);
            int index = getArguments().getInt(SegmentFactory.KEY_INDEX);
            owner.bindToPresenter(this, type, index);
        }
    }

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
        mVideoListView = (ListView) view.findViewById(R.id.list_view);
        mLoadingIcon = (ImageView) view.findViewById(R.id.loading_icon);
        mLoadingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadVideo(true);
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.pull_to_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

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
        VideoCollectionAdapter adapter = new VideoCollectionAdapter(getActivity(), videos, mPresenter);

        if (mVideoListView != null) {
            mVideoListView.setAdapter(adapter);
        }
    }

    @Override
    public void showErrorUI() {
        Toast.makeText(getContext(), R.string.MSG_FAILED_TO_LOAD, Toast.LENGTH_SHORT).show();
        mVideoListView.setVisibility(View.GONE);
        mLoadingIcon.setVisibility(View.VISIBLE);
        mLoadingIcon.clearAnimation();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void hideErrorUI() {
        mVideoListView.setVisibility(View.VISIBLE);
        mLoadingIcon.clearAnimation();
        mLoadingIcon.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadingIndicator() {
        mVideoListView.setVisibility(View.GONE);
        final Animation animRefresh = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_loading);
        mLoadingIcon.startAnimation(animRefresh);
        mLoadingIcon.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.loadVideo(false);
        }
    };
}
