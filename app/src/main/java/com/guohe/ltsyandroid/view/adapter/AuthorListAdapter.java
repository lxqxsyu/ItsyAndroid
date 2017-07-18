package com.guohe.ltsyandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.FrescoUtils;

/**
 * Created by shuihan on 2017/7/18.
 */

public class AuthorListAdapter extends RecyclerView.Adapter<AuthorListAdapter.AuthorListViewHolder>{

    private Context mContext;

    public AuthorListAdapter(Context context){
        mContext = context;
    }


    @Override
    public AuthorListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AuthorListViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_author_list, parent, false));
    }

    @Override
    public void onBindViewHolder(AuthorListViewHolder holder, int position) {
        FrescoUtils.loadRes(holder.wallpaper, R.mipmap.test_image1, null, 0, 0, null);
        FrescoUtils.setCircle(holder.authorHead, 0);
        FrescoUtils.loadRes(holder.authorHead, R.mipmap.ic_launcher_round, null, 0, 0, null);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class AuthorListViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private SimpleDraweeView wallpaper;
        private SimpleDraweeView authorHead;
        private TextView authorName;
        private TextView authorDescript;
        private Button attentionButton;
        public AuthorListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            wallpaper = (SimpleDraweeView) itemView.findViewById(R.id.item_author_wallpaper);
            authorHead = (SimpleDraweeView) itemView.findViewById(R.id.item_author_head);
            authorName = (TextView) itemView.findViewById(R.id.item_author_name);
            authorDescript = (TextView) itemView.findViewById(R.id.item_author_descript);
            attentionButton = (Button) itemView.findViewById(R.id.item_attention_button);
        }
    }
}
