package com.lengjiye.toolkit.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.lengjiye.toolkit.utils.CommonUtils;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * 类说明
 * Created by lz on 2016/4/12.
 */
public class LJYApplication extends Application {

    public static Context appContext;
    private static LJYApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        appContext = getApplicationContext();
        x.Ext.init(this);
        setEnvironmentConfiguration();
    }

    public static synchronized LJYApplication getInstance() {
        if (sInstance == null || appContext == null) {
            sInstance = new LJYApplication();
            appContext = sInstance.getApplicationContext();
        }
        //该实例是在onCreate事件中实例化的
        return sInstance;

    }

    /**
     * 设置环境配置
     */
    private void setEnvironmentConfiguration() {
        Log.e("lz", "isDebugMode:" + CommonUtils.isDebugMode());
        if (CommonUtils.isDebugMode()) {
            x.Ext.setDebug(true);
        } else {
            x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
        }
    }
}
