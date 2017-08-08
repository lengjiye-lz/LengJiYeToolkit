package com.lengjiye.toolkit.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.multidex.MultiDex;

import com.lengjiye.toolkit.receiver.NetworkChangedReceiver;
import com.lengjiye.tools.AppTool;

import org.xutils.BuildConfig;
import org.xutils.x;

import java.util.List;

/**
 * 类说明
 * Created by lz on 2016/4/12.
 */
public class LJYApplication extends Application {

    private static LJYApplication sInstance;

    private NetworkChangedReceiver networkChangedReceiver = null;
    public List<Activity> activityManager; // 管理Activity栈

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        // 初始化xUtils3
        x.Ext.init(this);
        setEnvironmentConfiguration();
        registerNetworkChangedListener();
    }

    public static synchronized LJYApplication getInstance() {
        if (sInstance == null) {
            sInstance = new LJYApplication();
        }
        //该实例是在onCreate事件中实例化的
        return sInstance;

    }

    /**
     * 设置环境配置
     */
    private void setEnvironmentConfiguration() {
        if (AppTool.isDebugMode()) {
            x.Ext.setDebug(true);
        } else {
            x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
        }
    }

    /**
     * 注册网络变化的监听
     */
    private void registerNetworkChangedListener() {
        networkChangedReceiver = new NetworkChangedReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangedReceiver, intentFilter);
    }

    /**
     * 解除网络变化的监听
     */
    private void unRegisterNetworkChangedListener() {
        if (networkChangedReceiver != null) {
            unregisterReceiver(networkChangedReceiver);
            networkChangedReceiver = null;
        }
    }

    public void addActivity(Activity activity) {
        if (activityManager != null && activity != null) {
            activityManager.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activityManager != null && activityManager.size() > 0 && activityManager.contains(activity)) {
            activityManager.remove(activity);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 方法超过64k，需要分包
        MultiDex.install(this);
    }

    /**
     * 退出应用
     */
    public void exit() {
        try {
            unRegisterNetworkChangedListener();
//            setCurrentActivity(null);
            while (activityManager != null && activityManager.size() > 0) {
                Activity activity = activityManager.get(activityManager.size() - 1);
                if (activity != null) {
                    activity.finish();
                }
                if (activityManager.contains(activity)) {
                    activityManager.remove(activity);
                }
            }
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
