package com.guohe.ltsyandroid.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.graphics.Palette;
import android.view.Window;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.custome.WeakRefrenceHandler;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import java.util.List;

import static com.wou.commonutils.ColorGenerator.colorBurn;

/**
 * Created by 水寒 on 2017/7/14.
 * 闪屏界面
 */

public class SplashActivity extends BaseActivity{
    private static final int SPLASH_TIME = 3000;        //闪屏时间
    private static final int HAND_START_INIT = 0x0001;  //开始初始化操作
    private static final int HAND_TURN_NEXT = 0x0002;   //跳转到下一个界面

    private long mStartTime;  //开始启动页时间

    private SimpleDraweeView mSplashImage;

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
        mSplashImage = getView(R.id.splash_image);
    }

    @Override
    protected void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test_image7);
        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
        FrescoUtils.loadRes(mSplashImage, R.mipmap.test_image7, null, 0, 0, null);
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
