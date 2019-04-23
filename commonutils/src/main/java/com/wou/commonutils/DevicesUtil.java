package com.wou.commonutils;

import android.content.Context;
import android.telephony.TelephonyManager;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by shuzhou on 16/5/23.
 */

public class DevicesUtil {

    public static String getIMEI(Context context) {
        String Imei = "";
        if (context != null) {
            try {
                Imei = ((TelephonyManager) context.getSystemService(TELEPHONY_SERVICE))
                        .getDeviceId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Imei;
    }

}
