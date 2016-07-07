package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class RecyclerViewFragment extends BaseFragment {

    private List<String> stringList;

    private RecyclerView recyclerView;
    private TextView textView;

    private RecyclerViewAdapter adapter;

    public RecyclerViewFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recycler_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        textView = (TextView) view.findViewById(R.id.text);
        return view;
    }

    /**
     * 初始化数据
     */
    public void initData() {
        stringList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(mContext, stringList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
        for (int i = 'A'; i < 'z'; i++) {
            stringList.add("" + (char) i);
        }
        adapter.notifyDataSetChanged();
    }
}
