package com.guohe.ltsyandroid.view.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class MainFragment3 extends BaseMainFragment {

    private ViewPager mRankViewPager;
    private SmartTabLayout mSmartTabLayout;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_three;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mRankViewPager = getView(R.id.rank_viewpager);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(MainFragment3.this.getActivity())
                .add("关注榜", RankFragment1.class)
                .add("赞赏榜", RankFragment2.class)
                .add("图集榜", RankFragment3.class)
                .add("摄影赛", RankFragment4.class)
                .create());
        mRankViewPager.setAdapter(adapter);
        if(mSmartTabLayout != null) {
            mSmartTabLayout.setViewPager(mRankViewPager);
        }
    }

    @Override
    public void attachActionBarView(View actionbarView) {
        if(actionbarView == null) return;
        if(mSmartTabLayout != null) return;
        mSmartTabLayout = (SmartTabLayout) actionbarView.findViewById(R.id.rank_viewpagertab);
        if(mRankViewPager != null) {
            mSmartTabLayout.setViewPager(mRankViewPager);
        }
    }
}
