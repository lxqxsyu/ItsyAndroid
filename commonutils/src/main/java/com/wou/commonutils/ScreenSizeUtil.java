package com.wou.commonutils;

import android.app.Activity;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by zshu on 15/11/26.
 */
public class ScreenSizeUtil {

    public static int getScreenWidth(Activity activity) {
//        return activity.getWindowManager().getDefaultDisplay().getWidth();
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width2 = outMetrics.widthPixels;
//        int height2 = outMetrics.heightPixels;
        return width2;
    }

    public static int getScreenHeight(Activity activity) {
//        return activity.getWindowManager().getDefaultDisplay().getHeight();
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
//        int width2 = outMetrics.widthPixels;
        int height2 = outMetrics.heightPixels;
        return height2;
    }

    public static float getTextViewLength(TextView textView, String text) {
        TextPaint paint = textView.getPaint();
        float textLength = paint.measureText(text);
        return textLength;
    }


    public static float getTextViewLength(TextView textView, String text,int  textsize) {
        TextPaint paint = textView.getPaint();
        paint.setTextSize(textsize);
        float textLength = paint.measureText(text);
        return textLength;
    }
}
