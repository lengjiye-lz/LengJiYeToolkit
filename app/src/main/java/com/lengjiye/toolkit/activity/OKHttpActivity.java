package com.lengjiye.toolkit.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.utils.OkHttpUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class OKHttpActivity extends BaseActivity {

    @ViewInject(R.id.text)
    private TextView text;

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_okhttp);
    }

    @Override
    protected void init() {
    }

    /**
     * 请求网页数据   异步请求
     */
    private void loadWebPageDataGitAsync() {
        String path = "https://www.baidu.com/";
        OkHttpUtils.getInstance().getRequest(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.obj = e;
                message.what = 1;
                ttsHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.e("lz", "htmlStr:" + htmlStr);
                Message message = new Message();
                message.obj = htmlStr;
                message.what = 0;
                ttsHandler.sendMessage(message);
            }
        });
    }

    /**
     * 请求网页数据  同步请求
     */
    private void loadWebPageDataGit() {
        Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        try {
                            String path = "https://www.baidu.com/";
                            Response response = OkHttpUtils.getInstance().getRequest(path);
                            if (response.code() == 200) {
                                sub.onNext(response.toString());
                            } else {
                                sub.onError(new Exception("code" + response.code()));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            sub.onError(e);
                        }
                    }
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();
                        text.setText(s);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                        text.setText("请求失败:" + e);
                    }
                });

    }

    /**
     * post 表单提交数据
     */
    private void loadWebPageDataPost() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("search", "Jurassic Park");
        RequestBody requestBody = builder.build();
        //创建一个Request
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php").post(requestBody)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.obj = e;
                message.what = 1;
                ttsHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.e("lz", "htmlStr:" + htmlStr);
                Message message = new Message();
                message.obj = htmlStr;
                message.what = 0;
                ttsHandler.sendMessage(message);
            }
        });
    }


    /**
     * post string
     */
    private void loadWebPageDataString() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        //创建一个Request
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), postBody))
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.obj = e;
                message.what = 1;
                ttsHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.e("lz", "htmlStr:" + htmlStr);
                Message message = new Message();
                message.obj = htmlStr;
                message.what = 0;
                ttsHandler.sendMessage(message);
            }
        });
    }

    /**
     * 上传图片
     */
    private void sendImageView() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "logo-square.png",
                        RequestBody.create(MediaType.parse("image/png"), new File("C:\\Users\\Administrator\\Desktop\\asdcasdc.gif")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "9199fdef135c122")
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.obj = e;
                message.what = 1;
                ttsHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.e("lz", "htmlStr:" + htmlStr);
                Message message = new Message();
                message.obj = htmlStr;
                message.what = 0;
                ttsHandler.sendMessage(message);
            }
        });
    }

    /**
     * 测试使用rxJava通讯
     *
     * @param message 消息
     * @param b       成功还是失败
     */
    private void testRxJavaCommunication(String message, final boolean b) {
        Observable.just(message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (b) {
                            Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();
                            text.setText(s);
                        } else {
                            Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();
                            text.setText("请求失败：" + s);
                        }
                    }
                });
    }

    MyHandler ttsHandler = new MyHandler(OKHttpActivity.this);

    @Event({R.id.bt_wangye_post, R.id.bt_wangye_get_async, R.id.bt_wangye_get, R.id.bt_wangye_tring, R.id.bt_shanghcuantupian})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_wangye_get_async:
                loadWebPageDataGitAsync();
                break;
            case R.id.bt_wangye_get:
                loadWebPageDataGit();
                break;
            case R.id.bt_wangye_post:
                loadWebPageDataPost();
                break;
            case R.id.bt_wangye_tring:
                loadWebPageDataString();
                break;
            case R.id.bt_shanghcuantupian:
                sendImageView();
                break;
        }
    }

    /**
     * 防止内存溢出
     */
    class MyHandler extends Handler {
        WeakReference<BaseActivity> mActivity;

        public MyHandler(BaseActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity theActivity = mActivity.get();
            if (theActivity == null) {
                return;
            }
            switch (msg.what) {
                case 0:
                    Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();
                    text.setText((CharSequence) msg.obj);
                    break;
                case 1:
                    Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                    text.setText("请求失败：" + msg.obj);
                    break;
            }
        }
    }
}
