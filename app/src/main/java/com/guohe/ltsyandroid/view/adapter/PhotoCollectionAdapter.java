package com.guohe.ltsyandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guohe.ltsyandroid.R;

/**
 * Created by shuihan on 2017/7/18.
 * 图片集
 */

public class PhotoCollectionAdapter extends RecyclerView.Adapter<PhotoCollectionAdapter.PhotoCollectionViewHolder>{

    private Context mContext;

    public PhotoCollectionAdapter(Context context){
        mContext = context;
    }

    @Override
    public PhotoCollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoCollectionViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_photocollection_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoCollectionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class PhotoCollectionViewHolder extends RecyclerView.ViewHolder{
        public PhotoCollectionViewHolder(View itemView) {
            super(itemView);
        }
    }
}
