package com.guohe.ltsyandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guohe.ltsyandroid.R;

/**
 * Created by 水寒 on 2017/7/21.
 * 照片分类
 */

public class PhotoCategoryAdapter extends RecyclerView.Adapter<PhotoCategoryAdapter.PhotoCategoryViewHolder>{

    private Context mContext;

    public PhotoCategoryAdapter(Context context){
        mContext = context;
    }

    @Override
    public PhotoCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoCategoryViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_photocollection_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoCategoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class PhotoCategoryViewHolder extends RecyclerView.ViewHolder{

        public PhotoCategoryViewHolder(View itemView) {
            super(itemView);
        }
    }
}
