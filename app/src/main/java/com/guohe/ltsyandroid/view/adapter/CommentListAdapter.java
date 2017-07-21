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
 * Created by 水寒 on 2017/7/21.
 * 评价适配器
 */

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentListViewHolder>{

    private Context mContext;

    public CommentListAdapter(Context context){
        mContext = context;
    }

    @Override
    public CommentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentListViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_comment_list, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentListViewHolder holder, int position) {
        FrescoUtils.setCircle(holder.headView,
                mContext.getResources().getColor(android.R.color.white));
        FrescoUtils.loadRes(holder.headView, R.mipmap.test_image5, null, 0, 0, null);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class CommentListViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView headView;
        public CommentListViewHolder(View itemView) {
            super(itemView);
            headView = (SimpleDraweeView) itemView.findViewById(R.id.comment_list_head);
        }
    }
}
