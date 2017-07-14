package com.guohe.ltsyandroid;

import java.util.List;

/**
 * Created by shuihan on 2016/12/7.
 * 视图层基类，定义视图基本操作
 */

public interface MvpView{

    /**
     * 初始化presenter
     */
    void initPresenter(List<MvpPresenter> presenters);

    /**
     * 跳转到其他视图
     */
    void turnToOtherView();
}
