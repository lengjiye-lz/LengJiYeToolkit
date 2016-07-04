package com.lengjiye.toolkit.utils;

/**
 * 不可以点击两次
 * Created by lz on 2016/5/12.
 */
public class NoDoubleClickUtils {
    private static long lastClickTime = 0;
    private final static int SPACE_TIME = 500;

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime >
                SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        return isClick2;
    }
}