package com.guohe.ltsyandroid.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.adapter.PhotoCollectionAdapter;
import com.guohe.ltsyandroid.view.adapter.PhotoDynamicAdapter;
import com.wou.commonutils.DensityUtil;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class MainFragment2 extends BaseMainFragment {

    public static final int TYPE_PHOTO = 1;
    public static final int TYPE_COLLECTION = 2;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    public static int mCurrentType;

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
        refreshView(R.id.main_fragment2_refresh, new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {

            }
        });
        mRecyclerView = getView(R.id.dynamic_recyclerview);
        bindView();
    }

    private void bindView(){
        if(mCurrentType == TYPE_PHOTO){
            bindPhotoView();
        }else{
            bindCollectionView();
        }
    }

    private void bindPhotoView() {
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new PhotoDynamicAdapter(this.getActivity());
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mRecyclerView.setAdapter(headAdapter);
        View space = new View(this.getActivity());
        space.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(this.getActivity(), 5)));
        RecyclerViewUtils.setHeaderView(mRecyclerView, space);
    }

    private void bindCollectionView() {
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new PhotoCollectionAdapter(this.getActivity());
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mRecyclerView.setAdapter(headAdapter);
        View space = new View(this.getActivity());
        space.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(this.getActivity(), 5)));
        RecyclerViewUtils.setHeaderView(mRecyclerView, space);
    }

    @Override
    public void attachActionBarView(View actionbarView) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_type:
                if("作品".equals(item.getTitle())){
                    item.setTitle("图集");
                    item.setIcon(R.drawable.ic_style_24px);
                    mCurrentType = TYPE_COLLECTION;
                }else{
                    item.setTitle("作品");
                    item.setIcon(R.drawable.ic_insert_photo_24px);
                    mCurrentType = TYPE_PHOTO;
                }
                bindView();
                initData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
