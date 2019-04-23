package com.wou.commonutils.callback;

/**
 * Created by zshu on 15/11/7.
 */
public interface LoadResultCallBack {

    int SUCCESS_OK = 1001;
    int SUCCESS_NONE = 1002;
    int ERROR_NET = 1003;

    void onSuccess(int result, Object object);

    void onError(int code, String msg);
}
