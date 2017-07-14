package com.guohe.ltsyandroid.view;

import android.os.Message;
import android.os.SystemClock;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.custome.WeakRefrenceHandler;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/14.
 * 闪屏界面
 */

public class SplashActivity extends BaseActivity{
    private static final int SPLASH_TIME = 3000;        //闪屏时间
    private static final int HAND_START_INIT = 0x0001;  //开始初始化操作
    private static final int HAND_TURN_NEXT = 0x0002;   //跳转到下一个界面

    private long mStartTime;  //开始启动页时间

    private WeakRefrenceHandler<SplashActivity> mHandler = new WeakRefrenceHandler<SplashActivity>(this) {
        @Override
        protected void handleMessage(SplashActivity ref, Message msg) {
            switch (msg.what){
                case HAND_START_INIT:
                    mStartTime = SystemClock.elapsedRealtime();
                    doInit();
                    int remainDelay =(int)(SPLASH_TIME -
                            (SystemClock.elapsedRealtime() - mStartTime));
                    if(remainDelay <= 0){
                        turnToOtherView();
                    }else{
                        mHandler.sendEmptyMessageDelayed(HAND_TURN_NEXT, remainDelay);
                    }
                    break;
                case HAND_TURN_NEXT:
                    turnToOtherView();
                    break;
            }
        }
    };

   /* @Override
    protected void initWindow() {
        super.initWindow();
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
    }*/

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {


    }

    /**
     * 做一些初始化操作
     */
    private void doInit(){
        //mGlobalPresenter.requestServerConfig();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            mHandler.sendEmptyMessage(HAND_START_INIT);
        }
    }

    @Override
    public void turnToOtherView() {
        MainActivity.startActivity(this);
        SplashActivity.this.finish();
    }
}
