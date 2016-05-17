package com.lengjiye.toolkit.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp 工具类
 * kohttp 简单封装
 * 异步请求（get、post）
 * 同步请求（get、post）
 * Created by lz on 2016/5/5.
 */
public class OkHttpUtils {

    public static final long DEFAULT_MILLISECONDS = 10000; // 默认请求时间

    private static OkHttpUtils mInstance;

    private OkHttpClient mOkHttpClient;
    private Call call;
    private long readTimeOut;
    private long writeTimeOut;
    private long connTimeOut;

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
     * @return mOkHttpClient
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
        this.readTimeOut = readTimeOut;
        this.writeTimeOut = writeTimeOut;
        this.connTimeOut = connTimeOut;
    }

    /**
     * git请求
     *
     * @param url              请求url
     * @param tag              请求标识，用来取消本次请求，可以为null
     * @param responseCallback callback
     */
    public void getRequest(String url, Object tag, Callback responseCallback) {
        Request request = new Request.Builder().url(url).tag(tag).build();
        request(request, responseCallback);
    }

    /**
     * git请求
     * 自定义请求头
     *
     * @param name             添加请求头
     * @param value            添加请求头
     * @param url              请求链接
     * @param tag              请求标识，用来取消本次请求，可以为null
     * @param responseCallback callback
     */
    public void getRequest(String name, String value, String url, Object tag, Callback responseCallback) {
        Request request = new Request.Builder().addHeader(name, value).url(url).tag(tag).build();
        request(request, responseCallback);
    }

    /**
     * post 提交字节流  比如json
     *
     * @param url              请求url
     * @param mediaType        类型，"text/x-markdown; charset=utf-8" 提交markdown类型 "application/json; charset=utf-8" json类型
     * @param content          提交内容
     * @param tag              请求标识
     * @param responseCallback callback
     */
    public void postRequest(String url, String mediaType, String content, Object tag, Callback responseCallback) {
//        Request request = new Request.Builder()
//                .url("https://api.github.com/markdown/raw")
//                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), postBody))
////                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString()))   josn 请求
//                .tag(tag)
//                .build();
//        request(request, responseCallback);
        postRequest(url, RequestBody.create(MediaType.parse(mediaType), content), tag, responseCallback);
    }

    /**
     * post 带参数请求，比如：
     * FormBody.Builder builder = new FormBody.Builder();
     * builder.add("search", "Jurassic Park");
     * RequestBody requestBody = builder.build();
     *
     * @param url              请求url
     * @param requestBody      请求参数
     * @param tag              请求标识
     * @param responseCallback callback
     */
    public void postRequest(String url, RequestBody requestBody, Object tag, Callback responseCallback) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .tag(tag)
                .build();
        request(request, responseCallback);
    }

    /**
     * 上传文件 可以上传多张 如：
     * RequestBody requestBody = new MultipartBody.Builder()
     * .setType(MultipartBody.FORM)
     * .addFormDataPart("title", "Square Logo")
     * .addFormDataPart("image", "logo-square.png",
     * RequestBody.create(MediaType.parse("image/png"), new File("C:\\Users\\Administrator\\Desktop\\asdcasdc.gif")))
     * .build();
     *
     * @param name             请求头 如"Authorization"
     * @param value            请求头 如"Client-ID " + "9199fdef135c122"
     * @param url              请求url
     * @param requestBody      请求参数
     * @param tag              请求标识
     * @param responseCallback callback
     */
    public void postRequest(String name, String value, String url, RequestBody requestBody, Object tag, Callback responseCallback) {
        Request request = new Request.Builder()
                .header(name, value)
                .url(url)
                .post(requestBody)
                .tag(tag)
                .build();
        request(request, responseCallback);
    }


    /**
     * git请求
     *
     * @param request          请求内容，需自行封装
     * @param responseCallback 返回callback
     */
    public void request(Request request, Callback responseCallback) {
        buildCall(request).enqueue(responseCallback);
    }

    /**
     * git请求
     *
     * @param url 请求url
     * @param tag 请求标识，用来取消本次请求，可以为null
     */
    public Response getRequest(String url, Object tag) throws IOException {
        Request request = new Request.Builder().url(url).tag(tag).build();
        return request(request);
    }

    /**
     * git请求
     *
     * @param name  添加请求头
     * @param value 添加请求头
     * @param url   请求链接
     * @param tag   请求标识，用来取消本次请求，可以为null
     */
    public Response getRequest(String name, String value, String url, Object tag) throws IOException {
        Request request = new Request.Builder().addHeader(name, value).url(url).tag(tag).build();
        return request(request);
    }

    /**
     * git请求
     *
     * @param request 请求内容，需自行封装
     */
    public Response request(Request request) throws IOException {
        return buildCall(request).execute();
    }


    /**
     * 添加设置超时
     *
     * @param request
     * @return
     */
    private Call buildCall(Request request) {
        if (readTimeOut > 0 || writeTimeOut > 0 || connTimeOut > 0) {
            readTimeOut = readTimeOut > 0 ? readTimeOut : OkHttpUtils.DEFAULT_MILLISECONDS;
            writeTimeOut = writeTimeOut > 0 ? writeTimeOut : OkHttpUtils.DEFAULT_MILLISECONDS;
            connTimeOut = connTimeOut > 0 ? connTimeOut : OkHttpUtils.DEFAULT_MILLISECONDS;
            mOkHttpClient.newBuilder().readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                    .writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                    .connectTimeout(connTimeOut, TimeUnit.MILLISECONDS).build();
            call = mOkHttpClient.newCall(request);
        } else {
            call = mOkHttpClient.newCall(request);
        }
        return call;
    }

    /**
     * 取消单个请求
     */
    public void cancleRequest() {
        if (call != null) {
            call.cancel();
        }
    }

    /**
     * 取消同一个tag标记是所有请求
     *
     * @param tag 标记
     */
    public void cancelAllTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }
}
