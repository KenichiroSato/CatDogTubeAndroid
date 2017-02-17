package com.capken.catdogtube.function.video.presentation.collection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.domain.model.Video;

import java.util.List;

/**
 * Created by 2ndDisplay on 2017/02/16.
 */

final class VideoCollectionAdapter extends ArrayAdapter {

    final private Context context;
    final private List<Video> videos;

    public VideoCollectionAdapter(Context context, List<Video> videos) {
        super(context, 0);
        this.context = context;
        this.videos = videos;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("tag", "position =" + position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.video_list_item, parent, false);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.item_name);
        ImageView itemIcon = (ImageView) convertView.findViewById(R.id.image_view);

        if (position < videos.size()) {
            itemName.setText(videos.get(position).getTitle());
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return videos.size();
    }


}
