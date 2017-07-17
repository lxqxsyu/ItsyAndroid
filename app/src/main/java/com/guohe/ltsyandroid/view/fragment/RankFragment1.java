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

public class RankFragment1 extends BaseFragment {

    private RecyclerView mRecyclerView;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_rank_one;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mRecyclerView = getView(R.id.rank1_recyclerview);
    }

    class Rank1Adapter extends RecyclerView.Adapter<Rank1ViewHolder>{

        @Override
        public Rank1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(Rank1ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class Rank1ViewHolder extends RecyclerView.ViewHolder{

        public Rank1ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
