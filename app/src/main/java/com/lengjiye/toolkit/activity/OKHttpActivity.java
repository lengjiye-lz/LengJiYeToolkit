package com.lengjiye.toolkit.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lengjiye.toolkit.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
     * 请求网页数据
     */
    private void loadWebPageDataGit() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        Request request = new Request.Builder()
                .url("https://www.baidu.com/")
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

    MyHandler ttsHandler = new MyHandler(OKHttpActivity.this);

    @Event({R.id.bt_wangye_post, R.id.bt_wangye_get, R.id.bt_wangye_tring})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_wangye_get:
                loadWebPageDataGit();
                break;

            case R.id.bt_wangye_post:
                loadWebPageDataPost();
                break;
            case R.id.bt_wangye_tring:
                loadWebPageDataString();
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
