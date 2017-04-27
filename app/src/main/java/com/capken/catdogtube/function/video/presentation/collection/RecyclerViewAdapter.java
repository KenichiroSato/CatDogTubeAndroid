package com.capken.catdogtube.function.video.presentation.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.domain.model.Video;
import com.capken.catdogtubedomain.video.presentation.collection.VideoCollectionContract;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ken on 2017/04/22..
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private List<Video> mItemList;
    final private Context mContext;
    final private VideoCollectionContract.Presenter mPresenter;


    public RecyclerViewAdapter(Context context,
                               List<Video> itemList,
                               VideoCollectionContract.Presenter presenter) {
        mItemList = itemList;
        mContext = context;
        mPresenter = presenter;
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
                mPresenter.onVideoTapped(mItemList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mItemList.size();
    }
}
