package com.guohe.ltsyandroid.util;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

import com.guohe.ltsyandroid.CustomeApplication;


/**
 * 尺寸转换相关的工具类
 */
public class DimenUtil {
    private static int mScreenWidth = -1;                           //手机屏幕的宽度
    private static int mScreenHeight = -1;                           //手机屏幕的高度


    /**
     * 得到视图测量后的宽度
     *
     * @param rootView
     * @return
     */
    public static int getMeasuredWidth(View rootView) {
        if (rootView == null) return 0;
        rootView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        return rootView.getMeasuredWidth();    //实际想在父view中得到的视图宽度
    }

    /**
     * 得到视图测量后的高度
     *
     * @param rootView
     * @return
     */
    public static int getMeasuredHeight(View rootView) {
        if (rootView == null) return 0;
        rootView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        return rootView.getMeasuredHeight(); //实际想在父view中得到的视图高度
    }

    /**
     * 标题栏的高度
     *
     * @param window
     * @return
     */
    public static int getTitleBarHeight(Window window) {
        int titleBarHeight = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return titleBarHeight;
    }

    /**
     * dip转pix
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        final float scale = CustomeApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * dip转pix
     *
     * @param sp
     * @return
     */
    public static int sp2px(float sp) {
        float scale = CustomeApplication.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    /**
     * 获取屏幕的宽度和高度
     *
     * @param context
     * @return
     */
    public static int[] getScreenWidthAndHeight(Context context) {
        //WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int[] screenDimen = new int[2];
        // mScreenWidth = wm.getDefaultDisplay().getWidth();
        //  mScreenHeight = wm.getDefaultDisplay().getHeight();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;
        mScreenHeight = displayMetrics.heightPixels;
        screenDimen[0] = mScreenWidth;
        screenDimen[1] = mScreenHeight;
        return screenDimen;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (mScreenWidth == -1) {
            getScreenWidthAndHeight(context);
        }
        return mScreenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (mScreenHeight == -1) {
            getScreenWidthAndHeight(context);
        }
        return mScreenHeight;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static String getShowByNumberWeight(int number, int weight) {
        if (number < weight) return String.valueOf(number);
        float dnumber = (float) number / weight;
        return String.format("%.1f", dnumber) + "k";

    }
}
