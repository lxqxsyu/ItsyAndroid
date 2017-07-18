package com.guohe.ltsyandroid.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
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

public class RankFragment3 extends BaseFragment{

    private RecyclerView mRecyclerView;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_rank_three;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        refreshView(R.id.rank_fragment3_refresh, new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        mRecyclerView = getView(R.id.rank3_recyclerview);
    }

    class Rank3Adapter extends RecyclerView.Adapter<Rank3ViewHolder>{

        @Override
        public Rank3ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(Rank3ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class Rank3ViewHolder extends RecyclerView.ViewHolder{

        public Rank3ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
