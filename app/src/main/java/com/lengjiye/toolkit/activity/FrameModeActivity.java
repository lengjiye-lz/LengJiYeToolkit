package com.lengjiye.toolkit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 框架模式
 * Created by lz on 2016/5/24.
 */
public class FrameModeActivity extends BaseActivity {

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        List<String> strings = getData();
        MainAdapter adapter = new MainAdapter(mContext, strings);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, MVPTestActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, TouchTestActivity.class));
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
        strings.add("MVP框架模式");
        strings.add("MVVM框架模式");
        return strings;
    }

    @Override
    protected void click(View v) {
        super.click(v);
    }
}
