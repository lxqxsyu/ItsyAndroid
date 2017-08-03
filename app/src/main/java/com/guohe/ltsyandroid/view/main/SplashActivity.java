package com.guohe.ltsyandroid.view;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.LayoutRes;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.custome.WeakRefrenceHandler;
import com.guohe.ltsyandroid.manage.config.GlobalConfigManage;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.util.LogUtil;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;
import com.wou.commonutils.ScreenSizeUtil;

import org.polaric.colorful.Colorful;

import java.util.List;
import java.util.Random;

/**
 * Created by 水寒 on 2017/7/14.
 * 闪屏界面
 */

public class SplashActivity extends BaseActivity{
    private static final int SPLASH_TIME = 3000;        //闪屏时间
    private static final int HAND_START_INIT = 0x0001;  //开始初始化操作
    private static final int HAND_TURN_NEXT = 0x0002;   //跳转到下一个界面
    private static final int HAND_LOAD_BITMAP = 0x0003;  //加载了图片

    private long mStartTime;  //开始启动页时间

    private ImageView mSplashImage;
    private View mBottomInfo;
    private View mOffset;

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
                case HAND_LOAD_BITMAP:
                    setBitmap((Bitmap)msg.obj);
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
        if(GlobalConfigManage.getInstance().getRandomTheme()) {
            Colorful.ThemeColor[] themeColors = Colorful.ThemeColor.values();
            Random random = new Random();
            int primaryIndex = random.nextInt(themeColors.length);
            int secondaryIndex = random.nextInt(themeColors.length);
            while (secondaryIndex == primaryIndex) {
                secondaryIndex = random.nextInt(themeColors.length);
            }
            Colorful.config(SplashActivity.this)
                    .primaryColor(themeColors[primaryIndex])
                    .accentColor(themeColors[secondaryIndex])
                    .translucent(false)
                    .dark(false)
                    .apply();
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mOffset = getView(R.id.view_need_offset);
        StatusBarUtil.setTranslucentForImageView(this, 100, mOffset);
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
                                mBottomInfo.setBackgroundColor(vibrant.getRgb());
                                mBottomInfo.getBackground().setAlpha(30);
                            }
                        });
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        Message message = Message.obtain();
                        message.what = HAND_LOAD_BITMAP;
                        if(width > height) {
                            // 创建操作图片用的matrix对象
                            Matrix matrix = new Matrix();
                            //旋转图片 动作
                            matrix.postRotate(90);
                            //创建新的图片
                            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                                    width, height, matrix, true);
                            message.obj = resizedBitmap;
                        }else{
                            message.obj = bitmap;
                        }
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    private void setBitmap(Bitmap bitmap){
        if(bitmap == null) return;
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
        MainActivity.startActivity(SplashActivity.this);
        SplashActivity.this.finish();
    }
}
