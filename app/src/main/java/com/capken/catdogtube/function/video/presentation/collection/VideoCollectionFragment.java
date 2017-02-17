package com.capken.catdogtube.function.video.presentation.collection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.domain.model.Video;
import com.capken.catdogtubedomain.video.presentation.collection.VideoCollectionContract;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by 2ndDisplay on 2017/02/16.
 */

public final class VideoCollectionFragment extends Fragment implements VideoCollectionContract.View {

    private ListView videoListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_collection, container, false);
        videoListView = (ListView) view.findViewById(R.id.list_view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showList();
    }

    private void showList() {
        VideoCollectionAdapter adapter = new VideoCollectionAdapter(getActivity());

        if (videoListView != null) {
            videoListView.setAdapter(adapter);
        }

    }

    //MARK VideoCollectionContract.View
    @Override
    public void show(@NotNull List<Video> videos) {

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
