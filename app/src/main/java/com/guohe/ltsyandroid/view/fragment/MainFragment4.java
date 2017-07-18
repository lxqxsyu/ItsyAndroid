package com.guohe.ltsyandroid.view.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.PhotoCollectionActivity;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class MainFragment4 extends BaseMainFragment{

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_four;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        FloatingActionButton fab = getView(R.id.fab_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                PhotoCollectionActivity.startActivity(MainFragment4.this.getContext());
            }
        });
    }

    @Override
    public void attachActionBarView(View actionbarView) {

    }
}
