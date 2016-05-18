package com.lengjiye.toolkit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lengjiye.toolkit.activity.BaseActivity;
import com.lengjiye.toolkit.activity.NoDoubleTestActivity;
import com.lengjiye.toolkit.activity.OKHttpActivity;
import com.lengjiye.toolkit.activity.StretchTextActivity;
import com.lengjiye.toolkit.activity.TouchTestActivity;
import com.lengjiye.toolkit.adapter.MainAdapter;
import com.lengjiye.toolkit.application.LJYApplication;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Intent intent;
    private Context mContext;
    private long lastPressTime;

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        mContext = this;
        List<String> strings = getData();
        MainAdapter adapter = new MainAdapter(mContext, strings);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(mContext, StretchTextActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(mContext, TouchTestActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(mContext, OKHttpActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(mContext, NoDoubleTestActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    /**
     * 获取数据源
     *
     * @return
     */
    private List<String> getData() {
        List<String> strings = new ArrayList<>();
        strings.add("可伸缩的TextView");
        strings.add("事件分发机制");
        strings.add("OKHttp请求");
        strings.add("不可重复点击测试");
        return strings;
    }

    @Override
    protected void click(View v) {
        super.click(v);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastPressTime > 2000) {
                Toast.makeText(getBaseContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                lastPressTime = currentTime;
            } else {
                LJYApplication.getInstance().exit();
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
