package com.guohe.ltsyandroid.custome;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 弱引用的Handler
 * @author shuihan
 *
 * @param <T>
 */
public abstract class WeakRefrenceHandler<T> extends Handler{
	
	private final WeakReference<T> mWeakRef;

	public WeakRefrenceHandler(T ref){
		mWeakRef = new WeakReference<T>(ref);
	}
	
	/**
	 * 解除引用
	 */
	public void clearRef(){
		mWeakRef.clear();
	}
	
	/**
	 * 派生类不要重写该方法，改用handleMessage(T, Message)
	 */
	@Override
	public void handleMessage(Message msg) {
		T ref = mWeakRef.get();
		if(ref != null){
			handleMessage(ref, msg);
		}
	}
	
	/**
	 * 派生的Handler来实现
	 * @param ref 属主 （外部类，或其他需要的引用），保证不为NULL
	 * @param msg 消息
	 */
	protected abstract void handleMessage(T ref, Message msg);
}
