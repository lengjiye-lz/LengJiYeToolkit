package com.lengjiye.toolkit.utils;

import com.lengjiye.toolkit.BuildConfig;

/**
 * 常量类
 * Created by lz on 2016/4/12.
 */
public class CommonUtils {

    /**
     * 是否是debug模式
     *
     * @return
     */
    public static boolean isDebugMode() {
        return BuildConfig.DEBUG;
    }
}
