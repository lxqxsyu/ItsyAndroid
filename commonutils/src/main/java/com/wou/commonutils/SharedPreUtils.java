
package com.wou.commonutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 *
 */
public class SharedPreUtils {

    /**
     * 全局shared preference的名称
     */
    private static final String SHARED_PREFERANCE_NAME = "shared_prefs";


    public static void clear(Context context) {
    }

    public static void setInteger(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(
                SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInteger(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(
                SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(
                SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * @param context
     * @param key
     * @return 默认返回("")
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(
                SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static String getString(Context context, String key, String defaultStr) {
        SharedPreferences sp = context.getSharedPreferences(
                SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultStr);
    }


    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(
                SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(
                SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void setStringSharedPreferences(Context context, String sp,
                                                  String key, String value) {
        SharedPreferences sps = context.getSharedPreferences(sp,
                Context.MODE_PRIVATE);
        Editor editor = sps.edit();
        editor.putString(key, value);
        editor.commit();
    }


    /**
     * 获取String类型的sp值
     *
     * @param context
     * @param sp
     * @param key
     */
    public static String getStringSharedPreferences(Context context, String sp,
                                                    String key, String defValue) {
        SharedPreferences sps = context.getSharedPreferences(sp,
                Context.MODE_PRIVATE);
        return sps.getString(key, defValue);
    }


}
