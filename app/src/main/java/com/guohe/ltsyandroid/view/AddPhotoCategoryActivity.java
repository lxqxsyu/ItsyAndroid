package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import java.util.List;

/**
 * Created by shuihan on 2017/7/21.
 * 图片分类选择
 */

public class AddPhotoCategoryActivity extends BaseActivity {

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected boolean canSlidr() {
        return true;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_addphoto_category;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                AddPhotoCategoryActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, AddPhotoCategoryActivity.class);
        context.startActivity(intent);
    }
}
