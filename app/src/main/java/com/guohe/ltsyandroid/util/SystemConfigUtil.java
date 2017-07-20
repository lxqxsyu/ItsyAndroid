package com.guohe.ltsyandroid.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by shuihan on 2016/12/9.
 * 系统配置相关的工具类
 */

public class SystemConfigUtil {

    /**
     * 获取版本名称
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = " ";
        if (context != null) {
            PackageManager pm = context.getPackageManager();
            if (pm != null) {
                PackageInfo pi;
                try {
                    pi = pm.getPackageInfo(context.getPackageName(), 0);
                    if (pi != null) {
                        versionName = pi.versionName;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return versionName;
    }

    /**
     * 获取版本号
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = 0;
        if (context != null) {
            PackageManager pm = context.getPackageManager();
            if (pm != null) {
                PackageInfo pi;
                try {
                    pi = pm.getPackageInfo(context.getPackageName(), 0);
                    if (pi != null) {
                        versionCode = pi.versionCode;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return versionCode;
    }
}
