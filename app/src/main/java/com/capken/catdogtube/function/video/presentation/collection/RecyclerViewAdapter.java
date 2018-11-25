package com.capken.catdogtube.function.video.presentation.collection;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.domain.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2017/04/22..
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private List<Video> mItemList;
    final private Context mContext;
    final private VideoTappedListener mListener;

    RecyclerViewAdapter(Context context,
                               VideoTappedListener listener) {
        mContext = context;
        mListener = listener;
        mItemList = new ArrayList<>();
    }

    void setVideoList(List<Video> list) {
        mItemList = list;
        notifyDataSetChanged();
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list_item, null);
        return new VideoViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder,final int position) {
        holder.mTitle.setText(mItemList.get(position).getTitle());
        Picasso.with(mContext).load(mItemList.get(position).getImageUrl()).into(holder.mImage);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onTapped(mItemList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mItemList.size();
    }
}
