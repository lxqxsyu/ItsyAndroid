package com.guohe.ltsyandroid.manage.http;


import com.guohe.ltsyandroid.manage.http.converter.ToGsonConverterFactory;
import com.guohe.ltsyandroid.manage.http.converter.ToStringConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by shuihan on 2016/12/8.
 */

public class RetrofitManage {

    //默认服务器路径
    public static final String DEFAULT_SERVER_HTTP = "http://localhost:8080/";

    //public static final String DEFAULT_SERVER_HTTP = "http://103.200.116.5:8080/";
    //public static final String DEFAULT_SERVER_HTTP = "http://192.168.31.99:8080/";

    public static final String SERVER_PATH = "laiqu/";

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    private static RetrofitManage mInstance;

    private RetrofitManage(){
        mOkHttpClient = OkHttpManager.getInstance().getOkHttpClient();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(DEFAULT_SERVER_HTTP + SERVER_PATH)
                .client(mOkHttpClient)
                .addConverterFactory(ToStringConverterFactory.create())
                .addConverterFactory(ToGsonConverterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitManage getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitManage();
        }
        return mInstance;
    }

    public static void refreshHttpByConfig(){
        mInstance = new RetrofitManage();
    }

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
