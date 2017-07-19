package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/19.
 * 添加图片
 */

public class AddPhotoActivity extends BaseActivity {

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_addphoto;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, AddPhotoActivity.class);
        context.startActivity(intent);
    }
}
