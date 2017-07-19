package com.guohe.ltsyandroid.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.view.PhotoCollectionActivity;
import com.wou.commonutils.ScreenSizeUtil;

/**
 * Created by shuihan on 2017/7/18.
 * 图片集
 */

public class PhotoCollectionAdapter extends RecyclerView.Adapter<PhotoCollectionAdapter.PhotoCollectionViewHolder>{

    private Context mContext;
    private int mScreenWidth;

    public PhotoCollectionAdapter(Activity context){
        mContext = context;
        mScreenWidth = ScreenSizeUtil.getScreenWidth(context);
    }

    @Override
    public PhotoCollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoCollectionViewHolder(mScreenWidth / 3 * 2, LayoutInflater.from(mContext)
                .inflate(R.layout.item_photocollection_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoCollectionViewHolder holder, int position) {
        int imageRes;
        if(position % 4 == 0){
            imageRes = R.mipmap.test_image4;
        }else if(position % 4 == 1){
            imageRes = R.mipmap.test_imag2;
        }else if(position % 4 == 2){
            imageRes = R.mipmap.test_image6;
        }else{
            imageRes = R.mipmap.test_image7;
        }
        FrescoUtils.loadRes(holder.authorBg, imageRes, null, 0, 0, null);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoCollectionActivity.startActivity(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class PhotoCollectionViewHolder extends RecyclerView.ViewHolder{

        private View itemView;
        private SimpleDraweeView authorBg;

        public PhotoCollectionViewHolder(int bgHeight, View itemView) {
            super(itemView);
            this.itemView = itemView;
            authorBg = (SimpleDraweeView) itemView.findViewById(R.id.item_photocollection_imageview);
            ViewGroup.LayoutParams params = authorBg.getLayoutParams();
            params.height = bgHeight;
            authorBg.setLayoutParams(params);
        }
    }
}
