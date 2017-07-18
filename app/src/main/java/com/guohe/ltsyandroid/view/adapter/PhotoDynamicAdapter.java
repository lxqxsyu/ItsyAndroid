package com.guohe.ltsyandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.view.PhotoDetailActivity;

/**
 * Created by shuihan on 2017/7/18.
 */

public class PhotoDynamicAdapter extends RecyclerView.Adapter<PhotoDynamicAdapter.DynamicViewHolder>{

    private Context mContext;

    public PhotoDynamicAdapter(Context context){
        mContext = context;
    }

    @Override
    public DynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DynamicViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_dynamic_list, parent, false));
    }

    @Override
    public void onBindViewHolder(DynamicViewHolder holder, int position) {
        FrescoUtils.loadRes(holder.imageView, R.mipmap.test_image1, null, 0 , 0, null);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoDetailActivity.startActivity(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    static class DynamicViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private SimpleDraweeView imageView;
        public DynamicViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.item_dynamic_imageview);
        }
    }
}
