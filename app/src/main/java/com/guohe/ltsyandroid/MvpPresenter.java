package com.guohe.ltsyandroid;

/**
 * Created by shuihan on 2016/12/7.
 * 处理用户事件的基类
 */

public interface MvpPresenter <V extends MvpView, M extends  MvpModel> {

    /**
     * 将视图依附到展示器（Presenter）
     * @param mvpView
     */
    void attachView(V mvpView);

    /**
     * 解除依附关系
     */
    void dettachView();
}
