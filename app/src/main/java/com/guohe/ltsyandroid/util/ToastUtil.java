package com.guohe.ltsyandroid.util;

import android.widget.Toast;

import com.guohe.ltsyandroid.CustomeApplication;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by shuihan on 2016/12/7.
 */

public class ToastUtil {

    private static Toast mToast;

    public enum ToastType{
        ERROR,
        INFO,
        SUCCESS
    }

    /**
     * 显示一个toast到界面上
     * @param message
     */
    public static void showToast(String message){
        showToast(message, ToastType.INFO);
    }

    /**
     * 显示toast
     * @param message
     * @param toastType
     */
    public static void showToast(String message, ToastType toastType){
        int type;
        switch (toastType){
            case ERROR:
                type = TastyToast.ERROR;
                break;
            case SUCCESS:
                type = TastyToast.SUCCESS;
                break;
            case INFO:
                type = TastyToast.INFO;
                break;
            default:
                type = TastyToast.INFO;
                break;
        }
         mToast = TastyToast.makeText(CustomeApplication.getContext(), message,
                TastyToast.LENGTH_SHORT, type);
    }

    public static void closeToast(){
        if(mToast == null) return;
        mToast.cancel();
    }
}
