package com.guohe.ltsyandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.FrescoUtils;

/**
 * Created by shuihan on 2017/7/18.
 * 竞争列表
 */

public class ContestListAdapter extends RecyclerView.Adapter<ContestListAdapter.ContestListViewHolder>{

    private Context mContext;

    public ContestListAdapter(Context context){
        mContext = context;
    }

    @Override
    public ContestListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContestListViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_contest_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ContestListViewHolder holder, int position) {
        FrescoUtils.loadRes(holder.imageView, R.mipmap.test_image1, null, 0, 0, null);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class ContestListViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView imageView;
        private View itemView;
        public ContestListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.item_contest_imageview);
        }
    }
}
