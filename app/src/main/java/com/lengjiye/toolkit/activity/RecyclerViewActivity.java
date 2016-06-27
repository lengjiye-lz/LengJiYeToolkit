package com.lengjiye.toolkit.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.adapter.RecyclerViewAdapter;
import com.lengjiye.toolkit.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView简单使用
 * Created by lz on 2016/6/27.
 */
public class RecyclerViewActivity extends BaseActivity {

    private List<String> stringList;

    private RecyclerView recyclerView;
    private TextView textView;

    private RecyclerViewAdapter adapter;

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recycler_view);
    }

    @Override
    protected void initView() {
        stringList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        textView = (TextView) findViewById(R.id.text);
        adapter = new RecyclerViewAdapter(mContext, stringList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    protected void initData() {
        for (int i = 'A'; i < 'z'; i++) {
            stringList.add("" + (char) i);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNetworkLinkFailure() {
        super.onNetworkLinkFailure();
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetworkLinkSuccess() {
        super.onNetworkLinkSuccess();
        textView.setVisibility(View.GONE);
    }
}
