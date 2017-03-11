package com.capken.catdogtube.function.video.presentation.collection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.domain.model.Video;
import com.capken.catdogtubedomain.video.presentation.collection.VideoCollectionContract;

import java.util.List;

/**
 * Created by 2ndDisplay on 2017/02/16.
 */

final class VideoCollectionAdapter extends ArrayAdapter {

    final private Context mContext;
    final private List<Video> mVideos;
    final private VideoCollectionContract.Presenter mPresenter;

    public VideoCollectionAdapter(Context context, List<Video> videos, VideoCollectionContract.Presenter presenter) {
        super(context, 0);
        mContext = context;
        mVideos = videos;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.video_list_item, parent, false);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.item_name);
        ImageView itemIcon = (ImageView) convertView.findViewById(R.id.image_view);

        if (position < mVideos.size()) {
            itemName.setText(mVideos.get(position).getTitle());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onVideoTapped(mVideos.get(position));
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return mVideos.size();
    }

}
