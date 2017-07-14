package com.guohe.ltsyandroid.presenter;

import com.guohe.ltsyandroid.MvpModel;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.MvpView;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class BasePresenter<T extends MvpView, M extends MvpModel> implements MvpPresenter<T, M> {

    @Override
    public void attachView(T mvpView) {

    }

    @Override
    public void dettachView() {

    }
}
