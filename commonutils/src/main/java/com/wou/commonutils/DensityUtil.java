package com.wou.commonutils;

import android.content.Context;

/**
 * 单位转换工具类
 *
 * @author lizhangqu
 * @version 1.0
 */
public class DensityUtil {

    /**
     * dip转px
     *
     * @param context 上下文
     * @param dip     dip值
     * @return 像素值
     */
    public static int dip2px(Context context, float dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param context 上下文
     * @param px      像素值
     * @return dip值
     */
    public static int px2dip(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param context 上下文
     * @param sp      sp值
     * @return 像素值
     */
    public static int sp2px(Context context, float sp) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param context 上下文
     * @param px      像素
     * @return sp值
     */
    public static int px2sp(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scale + 0.5f);
    }
}
