package com.lengjiye.toolkit.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * OkHttp 工具类
 * kohttp 简单封装
 * 异步请求（git、post、）
 * Created by lz on 2016/5/5.
 */
public class OkHttpUtils {

    public static final long DEFAULT_MILLISECONDS = 10000; // 默认请求时间

    private static OkHttpUtils mInstance;

    private OkHttpClient mOkHttpClient;

    public OkHttpUtils() {
        mOkHttpClient = new OkHttpClient();
    }

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取OkHttpClient实例
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * 设置请求相关时间
     *
     * @param readTimeOut  读取超时时间
     * @param writeTimeOut 写超时时间
     * @param connTimeOut  链接超时时间
     */
    public void setTime(long readTimeOut, long writeTimeOut, long connTimeOut) {
        if (readTimeOut > 0 || writeTimeOut > 0 || connTimeOut > 0) {
            readTimeOut = readTimeOut > 0 ? readTimeOut : OkHttpUtils.DEFAULT_MILLISECONDS;
            writeTimeOut = writeTimeOut > 0 ? writeTimeOut : OkHttpUtils.DEFAULT_MILLISECONDS;
            connTimeOut = connTimeOut > 0 ? connTimeOut : OkHttpUtils.DEFAULT_MILLISECONDS;
            getInstance().getOkHttpClient().newBuilder().readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                    .writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                    .connectTimeout(connTimeOut, TimeUnit.MILLISECONDS).build();
        }
    }

    /**
     * git请求
     *
     * @param url
     * @param responseCallback
     */
    public void getRequest(String url, Callback responseCallback) {
        Request request = new Request.Builder().url(url).build();
        getRequest(request, responseCallback);
    }

    /**
     * git请求
     *
     * @param name             添加请求头
     * @param value            添加请求头
     * @param url              请求链接
     * @param responseCallback callback
     */
    public void getRequest(String name, String value, String url, Callback responseCallback) {
        Request request = new Request.Builder().addHeader(name, value).url(url).build();
        getRequest(request, responseCallback);
    }

    /**
     * git请求
     *
     * @param request
     * @param responseCallback
     */
    public void getRequest(Request request, Callback responseCallback) {
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }
}
