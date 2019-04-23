package com.wou.commonutils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.wou.commonutils.logger.Logger;

import java.util.List;
import java.util.Stack;

/**
 * @author zshu
 *         应用程序Activity管理类：用于Activity管理和应用程序退出
 * @date 2014-8-6 下午6:04:25
 */
public class ActivityManager {

    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public static void setActivityStack(Stack<Activity> activityStack) {
        ActivityManager.activityStack = activityStack;
    }

    public static ActivityManager getAppManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    public void removeActivity(String className) {
        if (TextUtils.isEmpty(className)) {
        return ;
        }
        if (activityStack != null) {
            for (int i = 0; i < activityStack.size(); i++) {
                if (null != activityStack.get(i)) {
                    if (activityStack.get(i).getClass().getName().equals(className)) {
                        activityStack.get(i).finish();
                    }
                }
            }
        }
    }

    public boolean isContain(String className) {
        Logger.d("className" + className);
        if (TextUtils.isEmpty(className)) {
            return false;
        }
        if (activityStack != null) {
            Logger.d("activityStack is not   null ");
            for (int i = 0; i < activityStack.size(); i++) {
                Logger.d("activityStack.get(i).getComponentName()" + activityStack.get(i).getComponentName());
                Logger.d("className" + className);
                if (null != activityStack.get(i)) {
                    if (activityStack.get(i).getClass().getName().equals(className)) {
                        return true;
                    }
                }
            }
        }
        Logger.d("activityStack is   null ");
        return false;
    }


    public void finishAllActivityAndExit() {
        if (null != activityStack) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    Logger.d("activityStack.get(i).getComponentName()" + activityStack.get(i).getClass().getName());
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
            System.exit(0);
        }
    }


    public void finishAllActivity() {
        if (null != activityStack) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }


    public static boolean isAppAlive(Context context, String packageName) {
        android.app.ActivityManager activityManager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                Log.i("NotificationLaunch", String.format("the %s is running, isAppAlive return true", packageName));
                return true;
            }
        }
        Log.i("NotificationLaunch", String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }


}