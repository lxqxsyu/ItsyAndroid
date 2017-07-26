package com.guohe.ltsyandroid.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.view.jameson.library.CardAdapterHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuihan on 2017/7/26.
 */

public class CollectionCardAdapter extends RecyclerView.Adapter<CollectionCardAdapter.ViewHolder> {

    private List<Integer> mList = new ArrayList<>();
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();

    public CollectionCardAdapter(List<Integer> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photocollection_card_item, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        FrescoUtils.loadRes(holder.cardImageView, mList.get(position), null, 0, 0, null);
        holder.cardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToastUtils.show(holder.mImageView.getContext(), "" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView cardImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            cardImageView = (SimpleDraweeView) itemView.findViewById(R.id.card_imageview);
        }

    }
}
