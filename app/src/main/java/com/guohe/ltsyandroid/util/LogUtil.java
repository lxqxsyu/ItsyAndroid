package com.guohe.ltsyandroid.util;

import android.util.Log;

public final class LogUtil {

	private static final String TAG_NAME = "TEST";

	public static final int DEBUG = 2;

	public static final int ERROR = 5;

	public static int level = DEBUG;

	public static void init(boolean logdebug){
		if(logdebug){
			level = DEBUG;
		}else{
			level = ERROR;
		}
	}

	public static void d(String tagName, String message) {
		if (level <= DEBUG) {
			if(message == null) return;
			Log.d(tagName, "***********************************************" +
					"**********************************************************");
			Log.d(tagName, getHeadMessage());
			Log.d(tagName, "-----------------------------------------------" +
					"-----------------------------------------------------");
			Log.d(tagName, message);
			Log.d(tagName, "**************************************************" +
					"*******************************************************");
		}
	}

	public static void e(String tagName, Exception e){
		if (level <= ERROR) {
			Log.e(tagName, e.getMessage(), e);
		}
	}

	public static void e(String message){
		e(TAG_NAME, message);
	}

	public static void e(String tagName, String message){
		if(level <= ERROR){
			if(message == null) return;
			Log.e(tagName, "***********************************************" +
					"**********************************************************");
			Log.e(tagName, getHeadMessage());
			Log.e(tagName, "-----------------------------------------------" +
					"-----------------------------------------------------");
			Log.e(tagName, message);
			Log.e(tagName, "**************************************************" +
					"*******************************************************");
		}
	}

	public static void d(String message){
		d(TAG_NAME, message);
	}

	public static void e(Exception e){
		e(TAG_NAME, e);
	}

	private static String getHeadMessage() {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		StringBuilder builder = new StringBuilder();
		for (int i = 2; i > 0; i--) {
			int stackIndex = i + 5;
			builder.append("\n");
			if(trace.length > stackIndex) {
				builder.append(getSimpleClassName(trace[stackIndex].getClassName()))
						.append(".")
						.append(trace[stackIndex].getMethodName())
						.append(" ")
						.append(" (")
						.append(trace[stackIndex].getFileName())
						.append(":")
						.append(trace[stackIndex].getLineNumber())
						.append(")");
			}else{
				builder.append("NULL");
			}

		}
		return builder.toString();
	}

	private static String getSimpleClassName(String name) {
		int lastIndex = name.lastIndexOf(".");
		return name.substring(lastIndex + 1);
	}

}