package com.guohe.ltsyandroid;

import android.app.Application;
import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.util.LogUtil;

import org.polaric.colorful.Colorful;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class CustomeApplication extends Application {

    private static CustomeApplication mInstance;
    public AMapLocationClient mLocationClient = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FrescoUtils.init(this, 1024);  //1G缓存
        Colorful.defaults()
                .primaryColor(Colorful.ThemeColor.DEFAULT_PRIMARY)
                .accentColor(Colorful.ThemeColor.DEFAULT_SECODARY)
                .translucent(false)
                .dark(true);
        Colorful.init(this);
        //高德定位
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setInterval(1000 * 60 * 10);  //10分钟重新获取定位
        mLocationClient.setLocationOption(mLocationOption);
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if(aMapLocation == null) return;
                if(aMapLocation.getErrorCode() == 0){
                    LogUtil.d("当前位置信息：" + aMapLocation);

                }
            }
        });
    }

    public static CustomeApplication getApplication(){
        return mInstance;
    }

    public static Context getContext(){
        return mInstance.getApplicationContext();
    }
}
