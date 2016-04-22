package com.lengjiye.toolkit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lengjiye.toolkit.activity.BaseActivity;
import com.lengjiye.toolkit.activity.StretchTextActivity;
import com.lengjiye.toolkit.activity.TouchTestActivity;
import com.lengjiye.toolkit.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Intent intent;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
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
        return strings;
    }
}
