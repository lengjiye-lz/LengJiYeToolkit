package com.lengjiye.toolkit.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.lengjiye.toolkit.application.LJYApplication;

/**
 * 常量类
 * Created by lz on 2016/4/12.
 */
public class CommonUtils {

    private static final String DEBUG_KYE = "DEBUG_MODE";

    public static boolean isDebugMode() {
        boolean isDebug = false;
        isDebug = getMetaValues(LJYApplication.getInstance().appContext, DEBUG_KYE);
        return isDebug;
    }

    // 获取Manifest文件中的 meta value
    public static boolean getMetaValues(Context context, String metaKey) {
        Bundle metaData = null;
        boolean metaValue = true;
        if (context == null || metaKey == null) {
            return true;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                metaValue = metaData.getBoolean(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return metaValue;
    }
}
