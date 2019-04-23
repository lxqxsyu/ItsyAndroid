/**
 *
 */
package com.wou.commonutils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.List;

/**
 *
 * @项目名称：mvp
 * @类名称：NetWorkUtils
 * @类描述：
 * @创建人：zshu
 * @创建时间：2015-6-26 上午11:43:44
 * @修改人：
 * @修改时间：2015-6-26 上午11:43:44
 * @修改备注：
 * @version
 *
 */
public class NetWorkUtils {
	/**
	 * @param context
	 * @return 返回有网络状态
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 网络连接提示
	 *
	 * @param context
	 */
	public static void netWorkStateTips(Context context) {
		if (!isNetworkConnected(context))
			Toast.makeText(context, "网络连接失败！", Toast.LENGTH_LONG).show();
	}
	/**
	 * 判断是否是wifi连接
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;

	}
	/**
	 * GPS 是否打开
	 *
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		List<String> accessibleProviders = locationManager.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;

	}
	public static void openSetting(Activity activity){
		Intent intent=new Intent("/");
		ComponentName cm=new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
		intent.setComponent(cm);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivityForResult(intent, 0);
	}
}
