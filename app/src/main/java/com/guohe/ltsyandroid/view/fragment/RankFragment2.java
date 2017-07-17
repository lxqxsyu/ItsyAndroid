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

public class RankFragment2 extends BaseFragment{

    private RecyclerView mRecyclerView;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_rank_two;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mRecyclerView = getView(R.id.rank2_recyclerview);
    }

    class Rank2Adapter extends RecyclerView.Adapter<Rank2ViewHolder>{

        @Override
        public Rank2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(Rank2ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class Rank2ViewHolder extends RecyclerView.ViewHolder{

        public Rank2ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
