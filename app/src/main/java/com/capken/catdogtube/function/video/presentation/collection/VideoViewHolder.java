package com.capken.catdogtube.function.video.presentation.collection;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.capken.catdogtube.R;

/**
 * Created by ken on 2017/04/22..
 */

class VideoViewHolder extends RecyclerView.ViewHolder {

    public TextView mTitle;
    public ImageView mImage;
    public View mItemView;

    public VideoViewHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.item_name);
        mImage = (ImageView) itemView.findViewById(R.id.image_view);
        mItemView = itemView;
    }

}
