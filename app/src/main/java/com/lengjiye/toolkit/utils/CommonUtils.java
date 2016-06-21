package com.lengjiye.toolkit.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.widget.Toast;

import com.lengjiye.toolkit.BuildConfig;

import java.io.File;

/**
 * 公共类
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

    /**
     * 获取版本号
     *
     * @return
     */
    public static int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    /**
     * 获取版本名称
     *
     * @return
     */
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 获取包名
     *
     * @return
     */
    public static String getApplicationId() {
        return BuildConfig.APPLICATION_ID;
    }

    /**
     * 获取类名和方法名
     *
     * @return
     */
    public static String generateClassAndMethodTag() {
        String customTagPrefix = "";
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    /**
     * 获取手机可用内存
     *
     * @param mContext
     * @return
     */
    public static String getAvailMemory(Context mContext) {// 获取android当前可用内存大小
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        //mi.availMem; 当前系统的可用内存
        return Formatter.formatFileSize(mContext, mi.availMem);// 将获取的内存大小规格化
    }

    /**
     * 读取SD卡剩余空间 字节类型
     *
     * @param mContext
     */
    public static long getSDMemoryLong(Context mContext) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(mContext, "手机没有发现SD卡", Toast.LENGTH_SHORT).show();
            return -1;
        }
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();

        long availSize = availableBlocks * blockSize;
        return availSize;
    }

    /**
     * 返回SD卡剩余空间  String类型
     *
     * @param mContext
     * @return
     */
    public static String getSDMemoryString(Context mContext) {
        long availSize = getSDMemoryLong(mContext);
        String availStr = Formatter.formatFileSize(mContext, availSize);
        return availStr;
    }
}
