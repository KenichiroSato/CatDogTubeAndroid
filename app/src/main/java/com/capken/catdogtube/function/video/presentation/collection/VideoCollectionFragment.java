package com.capken.catdogtube.function.video.presentation.collection;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    }

    @Override
    public void hideErrorUI() {

    }

    @Override
    public void showLoadingIndicator() {

    }
}
