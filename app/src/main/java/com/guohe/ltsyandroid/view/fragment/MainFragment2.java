package com.guohe.ltsyandroid.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.PhotoDetailActivity;
import com.wou.commonutils.DensityUtil;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class MainFragment2 extends BaseMainFragment {

    private RecyclerView mRecyclerView;
    private DynamicListAdapter mAdapter;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_two;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mRecyclerView = getView(R.id.dynamic_recyclerview);
        bindView();
    }

    private void bindView() {
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new DynamicListAdapter();
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mRecyclerView.setAdapter(headAdapter);
        View space = new View(this.getActivity());
        space.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(this.getActivity(), 10)));
        RecyclerViewUtils.setHeaderView(mRecyclerView, space);
    }

    @Override
    public void attachActionBarView(View actionbarView) {

    }

    class DynamicListAdapter extends RecyclerView.Adapter<DynamicViewHolder>{

        @Override
        public DynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DynamicViewHolder(LayoutInflater.from(MainFragment2.this.getActivity())
                    .inflate(R.layout.item_dynamic_list, parent, false));
        }

        @Override
        public void onBindViewHolder(DynamicViewHolder holder, int position) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoDetailActivity.startActivity(MainFragment2.this.getContext());
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    class DynamicViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        public DynamicViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
