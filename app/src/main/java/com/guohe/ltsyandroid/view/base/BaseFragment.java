package com.guohe.ltsyandroid.view.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.MvpView;
import com.guohe.ltsyandroid.manage.rxbus.RxBus;
import com.guohe.ltsyandroid.manage.rxbus.bean.BaseBusEvent;
import com.guohe.ltsyandroid.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 水寒 on 2017/7/14.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseView mBaseView;
    private View mView;
    private List<Subscription> mSubscriptions = new ArrayList<>();
    private List<MvpPresenter> mPresenters = new ArrayList<>();
    private SwipeRefreshLayout mRefreshView;

    public BaseFragment(){
        mBaseView = new BaseView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getContentView(), null);
        initView(mView);
        initPresenter(mPresenters);
        for(MvpPresenter presenter : mPresenters){
            if(presenter == null) continue;
            presenter.attachView(this);
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        for(MvpPresenter presenter : mPresenters){
            if(presenter == null) continue;
            presenter.dettachView();
        }
        mPresenters.clear();
    }


    public SwipeRefreshLayout getRefreshView(){
        return mRefreshView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBaseView.onResume(this.getActivity());
        //MobclickAgent.onPageStart(this.getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        mBaseView.onPause(this.getActivity());
        //MobclickAgent.onPageEnd(this.getClass().getName());
    }

    protected <E extends BaseBusEvent> void observerRxBus(Class<E> busClass, final Action1<E> onNext) {
        Subscription subscription = RxBus.getDefault().observerRxBus(busClass, onNext);
        mSubscriptions.add(subscription);
    }

    protected <E extends BaseBusEvent> void observerRxBusSticky(Class<E> busClass, final Action1<E> onNext) {
        Subscription subscription = RxBus.getDefault().observerRxBusSticky(busClass, onNext);
        mSubscriptions.add(subscription);
    }

    /**
     * 设置布局文件
     * @return
     */
    protected abstract int getContentView();

    protected abstract void initData();

    protected abstract void initView(View view);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseView.onCreate(this.getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBaseView.onDestroy(this.getActivity());
        for(Subscription subscription : mSubscriptions){
            if(subscription == null) continue;
            if(subscription.isUnsubscribed()) continue;
            subscription.unsubscribe();
        }
        mSubscriptions.clear();
    }


    @SuppressWarnings("unchecked")
    protected final <E extends View> E getView (int id) {
        try {
            return (E) mView.findViewById(id);
        } catch (ClassCastException ex) {
            LogUtil.e("Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

    /**
     * 刷新界面
     * @param id
     * @param onRefresh
     */
    public SwipeRefreshLayout refreshView(int id, final SwipeRefreshLayout.OnRefreshListener onRefresh) {
        SwipeRefreshLayout refreshView = getView(id);
        if (refreshView == null) return null;
        mRefreshView = refreshView;
        if(mRefreshView.isRefreshing()) return mRefreshView;
        mBaseView.refreshView(refreshView, new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefresh.onRefresh();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        return refreshView;
    }
}
