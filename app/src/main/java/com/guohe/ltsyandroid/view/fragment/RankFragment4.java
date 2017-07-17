package com.guohe.ltsyandroid.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.base.BaseFragment;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class RankFragment4 extends BaseFragment{

    private RecyclerView mRecyclerView;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_rank_four;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mRecyclerView = getView(R.id.rank4_recyclerview);
    }

    class Rank4Adapter extends RecyclerView.Adapter<Rank4ViewHolder>{

        @Override
        public Rank4ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(Rank4ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class Rank4ViewHolder extends RecyclerView.ViewHolder{

        public Rank4ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
