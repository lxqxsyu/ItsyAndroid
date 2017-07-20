package com.guohe.ltsyandroid.view;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.custome.WeakRefrenceHandler;
import com.guohe.ltsyandroid.manage.config.GlobalConfigManage;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.util.LogUtil;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.wou.commonutils.ScreenSizeUtil;

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

    private ImageView mSplashImage;
    private View mBottomInfo;

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
        mBottomInfo = getView(R.id.bottom_info_area);
        GlobalConfigManage.getInstance().setScreenWidth(ScreenSizeUtil.getScreenWidth(this));
        GlobalConfigManage.getInstance().setScreenHeight(ScreenSizeUtil.getScreenHeight(this));
    }

    @Override
    protected void initData() {
        FrescoUtils.getBitmapByRes(R.mipmap.test_image4, this, GlobalConfigManage.getInstance().getScreenWidth(),
                GlobalConfigManage.getInstance().getScreenHeight(), new FrescoUtils.BitmapListener() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        LogUtil.d("bitmap == " + bitmap);
                        if(bitmap == null) return;
                        // Palette的部分
                        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                Palette.Swatch vibrant = palette.getVibrantSwatch();
                                mBottomInfo.setBackgroundColor(colorBurn(vibrant.getRgb()));
                                mBottomInfo.setAlpha(0.3f);
                                if (android.os.Build.VERSION.SDK_INT >= 21) {
                                    Window window = getWindow();
                                    window.setStatusBarColor(vibrant.getRgb());
                                    window.setNavigationBarColor(vibrant.getRgb());
                                }
                            }
                        });
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        if(width > height) {
                            // 创建操作图片用的matrix对象
                            Matrix matrix = new Matrix();
                            //旋转图片 动作
                            matrix.postRotate(90);
                            //创建新的图片
                            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                                    width, height, matrix, true);
                            setBitmap(resizedBitmap);
                        }else{
                            setBitmap(bitmap);
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    private void setBitmap(Bitmap bitmap){
        mSplashImage.setImageBitmap(bitmap);
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
