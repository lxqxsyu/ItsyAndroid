package com.guohe.ltsyandroid.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.adapter.PhotoCollectionAdapter;
import com.guohe.ltsyandroid.view.base.BaseFragment;
import com.wou.commonutils.DensityUtil;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class RankFragment3 extends BaseFragment{

    private RecyclerView mRecyclerView;
    private PhotoCollectionAdapter mAdapter;

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
        bindView();
    }

    private void bindView() {
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new PhotoCollectionAdapter(this.getContext());
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mRecyclerView.setAdapter(headAdapter);
        View space = new View(this.getActivity());
        space.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(this.getActivity(), 5)));
        RecyclerViewUtils.setHeaderView(mRecyclerView, space);
    }
}
