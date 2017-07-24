package com.guohe.ltsyandroid;

import android.app.Application;
import android.content.Context;

import com.guohe.ltsyandroid.util.FrescoUtils;

import org.polaric.colorful.Colorful;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class CustomeApplication extends Application {

    private static CustomeApplication mInstance;

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
    }

    public static CustomeApplication getApplication(){
        return mInstance;
    }

    public static Context getContext(){
        return mInstance.getApplicationContext();
    }
}
