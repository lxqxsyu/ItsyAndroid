package com.guohe.ltsyandroid.view.base;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.guohe.ltsyandroid.R;

/**
 * Created by shuihan on 2016/12/7.
 * 所有可见视图的基类
 */

public class BaseView {

    public BaseView(){

    }

    public void onCreate(Activity activity){

    }

    public void onResume(Activity activity){

    }

    public void onPause(Activity activity){

    }

    public void onDestroy(Activity activity){

    }

    /**
     * 刷新界面
     * @param refreshView
     */
    public void refreshView(SwipeRefreshLayout refreshView, SwipeRefreshLayout.OnRefreshListener onRefresh) {
        if(refreshView == null) return;
        refreshView.setOnRefreshListener(onRefresh);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        refreshView.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorSecondaryDark,
                R.color.colorPrimary, R.color.colorSecondary);
        refreshView.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        refreshView.setProgressBackgroundColor(android.R.color.white); // 设定下拉圆圈的背景
        refreshView.setSize(SwipeRefreshLayout.DEFAULT); // 设置圆圈的大小
    }
}
