package com.lengjiye.toolkit.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.lengjiye.toolkit.application.LJYApplication;

/**
 * SharedPreferences操作工具类
 * 如果只是保存数据，没什么特殊的要求可以直接调用getInstance()方法获取实例，putXX()保存值，getXX()方法获取值。
 * Created by lz on 2016/7/14.
 */
public class SharedPreferencesUtil {

    private final static String NAME = "lengjiye";

    private static SharedPreferencesUtil preferencesUtil;
    private static SharedPreferences sharedPreferences;

    private SharedPreferencesUtil() {
    }

    /**
     * @return SharedPreferencesUtil
     */
    public synchronized static SharedPreferencesUtil getInstance() {
        return getInstance(LJYApplication.getInstance().getApplicationContext(), NAME, Context.MODE_PRIVATE);
    }

    /**
     * @param mContext 上下文
     * @return SharedPreferencesUtil
     */
    public synchronized static SharedPreferencesUtil getInstance(Context mContext) {
        return getInstance(mContext, NAME, Context.MODE_PRIVATE);
    }

    /**
     * @param mContext 上下文
     * @param name     SharedPreferences文件的名字，不传默认是 @code{NAME}
     * @return SharedPreferencesUtil
     */
    public synchronized static SharedPreferencesUtil getInstance(Context mContext, String name) {
        return getInstance(mContext, name, Context.MODE_PRIVATE);
    }

    /**
     * @param mContext 上下文
     * @param name     SharedPreferences文件的名字，不传默认是 @code{NAME}
     * @param mode     SharedPreferences mode，默认是 Context.MODE_PRIVATE
     * @return SharedPreferencesUtil
     */
    public synchronized static SharedPreferencesUtil getInstance(Context mContext, String name, int mode) {
        if (preferencesUtil == null) {
            preferencesUtil = new SharedPreferencesUtil();
        }
        sharedPreferences = mContext.getSharedPreferences(name, mode);
        return preferencesUtil;
    }

    /**
     * 将传入的key和value放入到SharedPreferences中
     */
    public void putString(String key, String value) {
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 根据传入的key和defValue从SharedPreferences中获取相应的值
     */
    public String getString(String key, String defValue) {
        if (sharedPreferences == null) {
            return defValue;
        }
        String value = sharedPreferences.getString(key, defValue);
        return value;
    }


    /**
     * 将传入的key和value放入到SharedPreferences中
     */
    public void putBoolean(String key, boolean value) {
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 根据传入的key和defValue从SharedPreferences中获取相应的值
     */
    public boolean getBoolean(String key, boolean defValue) {
        if (sharedPreferences == null) {
            return defValue;
        }
        boolean value = sharedPreferences.getBoolean(key, defValue);
        return value;
    }


    /**
     * 将传入的key和value放入到SharedPreferences中
     */
    public void putInt(String key, int value) {
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 根据传入的key和defValue从SharedPreferences中获取相应的值
     */
    public int getInt(String key, int defValue) {
        if (sharedPreferences == null) {
            return defValue;
        }
        int value = sharedPreferences.getInt(key, defValue);
        return value;
    }


    /**
     * 将传入的key和value放入到SharedPreferences中
     */
    public void putLong(String key, long value) {
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 根据传入的key和defValue从SharedPreferences中获取相应的值
     */
    public long getLong(String key, long defValue) {
        if (sharedPreferences == null) {
            return defValue;
        }
        long value = sharedPreferences.getLong(key, defValue);
        return value;
    }
}
