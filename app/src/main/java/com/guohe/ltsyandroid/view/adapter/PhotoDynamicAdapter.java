package com.guohe.ltsyandroid.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.DimenUtil;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.view.PhotoDetailActivity;
import com.wou.commonutils.ScreenSizeUtil;

/**
 * Created by shuihan on 2017/7/18.
 */

public class PhotoDynamicAdapter extends RecyclerView.Adapter<PhotoDynamicAdapter.DynamicViewHolder>{

    private Context mContext;
    private int mScreenWidth;

    public PhotoDynamicAdapter(Activity context){
        mContext = context;
        mScreenWidth = ScreenSizeUtil.getScreenWidth(context);
    }

    @Override
    public DynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DynamicViewHolder(mScreenWidth - DimenUtil.dp2px(10), LayoutInflater.from(mContext)
                .inflate(R.layout.item_dynamic_list, parent, false));
    }

    @Override
    public void onBindViewHolder(DynamicViewHolder holder, int position) {
        int imageRes;
        if(position % 4 == 0){
            imageRes = R.mipmap.test_image4;
        }else if(position % 4 == 1){
            imageRes = R.mipmap.test_image7;
        }else if(position % 4 == 2){
            imageRes = R.mipmap.test_image6;
        }else{
            imageRes = R.mipmap.test_image3;
        }
        FrescoUtils.loadRes(holder.imageView, imageRes, null, 0 , 0, null);
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
        public DynamicViewHolder(int imageWidth, View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.item_dynamic_imageview);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = imageWidth;
            imageView.setLayoutParams(params);
        }
    }
}
