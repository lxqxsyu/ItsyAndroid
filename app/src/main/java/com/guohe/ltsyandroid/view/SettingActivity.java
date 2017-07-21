package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/19.
 */

public class SettingActivity extends BaseActivity{

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected boolean canSlidr() {
        return true;
    }

    @Override
    protected void initView() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
