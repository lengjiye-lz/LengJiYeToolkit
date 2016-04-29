package com.lengjiye.toolkit.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lengjiye.toolkit.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpActivity extends BaseActivity {

    @ViewInject(R.id.bt_wangye)
    private Button btWangye;

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
    private void loadWebPageData() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("https://github.com/hongyangAndroid")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.e("lz", "htmlStr:" + htmlStr);
            }
        });
    }

    @Event({R.id.bt_wangye})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_wangye:
                loadWebPageData();
                break;
        }
    }

}
