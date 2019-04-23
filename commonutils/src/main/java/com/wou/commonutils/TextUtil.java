package com.wou.commonutils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    public static final String REG_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    private static Typeface mTypefaceNum;

    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile(REG_EMAIL);
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * 判断是否为null、空字符串或者是"null"
     *
     * @param str
     * @return
     */
    public static boolean isNull(CharSequence... str) {

        for (CharSequence cha : str) {
            if (cha == null || cha.length() == 0 || cha.equals("null")) {
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    public static void copy(Activity activity, String copyText) {
        ClipboardManager clip = (ClipboardManager)
                activity.getSystemService(Context
                        .CLIPBOARD_SERVICE);
        clip.setPrimaryClip(ClipData.newPlainText
                (null, copyText));
    }

    public static void setNumberText(Context context, TextView textView, int num){
        setNumberText(context, textView, String.valueOf(num));
    }

    public static void setNumberText(Context context, TextView textView, String num){
        if(mTypefaceNum == null) {
            mTypefaceNum = Typeface.createFromAsset(context.getAssets(), "fonts/MS1451.ttf");
        }
        textView.setTypeface(mTypefaceNum);
        textView.setText(num);
    }

}