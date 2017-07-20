package com.guohe.ltsyandroid.manage.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.guohe.ltsyandroid.CustomeApplication;

import java.util.Set;

/**
 * Created by shuihan on 2016/12/8.
 * 配置文件的基础管理类
 */
public class BaseConfigManage {
	protected SharedPreferences mSharePreference;

	protected BaseConfigManage(String shareName){
		mSharePreference = CustomeApplication.getContext()
				.getSharedPreferences(shareName, Context.MODE_PRIVATE);
	}
	
	/**
	 * 设置配置
	 * @param key
	 * @param value
	 */
	protected void setConfig(String key, int value){
		Editor editor = mSharePreference.edit();
		editor.putInt(key, value);
		editor.apply();
	}

	/**
	 * 设置配置
	 * @param key
	 * @param value
	 */
	protected void setConfig(String key, String value){
		Editor editor = mSharePreference.edit();
		editor.putString(key, value);
		editor.apply();
	}
	
	/**
	 * 设置配置
	 * @param key
	 * @param value
	 */
	protected void setConfig(String key, long value){
		Editor editor = mSharePreference.edit();
		editor.putLong(key, value);
		editor.apply();
	}
	
	/**
	 * 设置配置
	 * @param key
	 * @param value
	 */
	protected void setConfig(String key, Set<String> value){
		Editor editor = mSharePreference.edit();
		editor.putStringSet(key, value);
		editor.apply();
	}
	
	/**
	 * 设置配置
	 * @param key
	 * @param value
	 */
	protected void setConfig(String key, boolean value){
		Editor editor = mSharePreference.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}
}
