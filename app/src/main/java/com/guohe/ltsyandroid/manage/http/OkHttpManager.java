package com.guohe.ltsyandroid.manage.http;


import com.guohe.ltsyandroid.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by shuihan on 2016/12/8.
 * Http请求管理类
 */

public class OkHttpManager {

    public static final int HTTP_CONNECT_TIMEOUT = 30 * 1000;//毫秒数
    public static final int HTTP_READ_TIMEOUT = 30 * 1000;//毫秒数
    public static final int HTTP_WRIGHT_TIMEOUT = 30 * 1000;//毫秒数

    private static OkHttpManager mInstance;

    private OkHttpClient mOkHttpClient;

    private OkHttpManager(){
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(HTTP_WRIGHT_TIMEOUT, TimeUnit.MILLISECONDS);
        if (BuildConfig.DEBUG) {
            //配置日志
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            httpBuilder.addInterceptor(logging);
        }

        mOkHttpClient = httpBuilder.build();
    }

    public static OkHttpManager getInstance(){
        if(mInstance == null){
            synchronized (OkHttpManager.class) {
                if(mInstance == null) {
                    mInstance = new OkHttpManager();
                }
            }
        }
        return mInstance;
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }
}
