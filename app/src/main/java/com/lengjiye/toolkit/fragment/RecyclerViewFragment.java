package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.adapter.RecyclerViewAdapter;
import com.lengjiye.toolkit.adapter.StaggeredHomeAdapter;
import com.lengjiye.toolkit.view.DividerGridItemDecoration;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView简单使用
 * Created by lz on 2016/6/27.
 */
public class RecyclerViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<String> stringList;
    private List<Integer> mHeights;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe_refresh_widget;
    private TextView textView;
    private int max = 30;
    private boolean loading = false;

    private RecyclerViewAdapter adapter;
    private StaggeredHomeAdapter homeAdapter;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private LoadMoreWrapper loadMoreWrapper;
    private MyHandler myHandler;

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
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        swipe_refresh_widget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        textView = (TextView) view.findViewById(R.id.text);
        return view;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        myHandler = new MyHandler(Looper.getMainLooper(), this);
        swipe_refresh_widget.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark,
                R.color.colorAccent, R.color.cl_0079ff);
        swipe_refresh_widget.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipe_refresh_widget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipe_refresh_widget.setSize(SwipeRefreshLayout.LARGE);
        stringList = new ArrayList<>();
        mHeights = new ArrayList<>();
        setData(max);
        adapter = new RecyclerViewAdapter(mContext, stringList);
        homeAdapter = new StaggeredHomeAdapter(mContext, stringList);
        addHeaderAndFooter();
//        addLoadMore();

//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext)); // ListView
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3)); // GridView
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)); //瀑布流 横向滑动
//        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST)); //listview分割线
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext)); //GridView分割线
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(headerAndFooterWrapper);
    }

    @Override
    public void onRefresh() {
        myHandler.sendEmptyMessageDelayed(1, 2000);
    }

    private void setData(int max) {
        for (int i = 0; i < max; i++) {
            stringList.add("" + i);
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    private void addHeaderAndFooter() {
        headerAndFooterWrapper = new HeaderAndFooterWrapper(homeAdapter);
        TextView t1 = new TextView(mContext);
        t1.setText("Header 1");
        t1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        t1.setGravity(Gravity.CENTER);
        TextView t2 = new TextView(mContext);
        t2.setText("Header 2");

        headerAndFooterWrapper.addHeaderView(t1);
        headerAndFooterWrapper.addHeaderView(t2);
        headerAndFooterWrapper.addFootView(t1);
        headerAndFooterWrapper.addFootView(t2);
    }

    private void addLoadMore() {
        loadMoreWrapper = new LoadMoreWrapper(headerAndFooterWrapper);
        loadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setData(10);
                        loadMoreWrapper.notifyDataSetChanged();
                    }
                }, 3000);
            }
        });
    }

    static class MyHandler extends Handler {
        WeakReference<RecyclerViewFragment> reference;

        MyHandler(Looper looper, RecyclerViewFragment fragment) {
            super(looper);
            reference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (reference == null) {
                return;
            }
            RecyclerViewFragment fragment = reference.get();
            switch (msg.what) {
                case 1:
                    fragment.stringList.clear();
                    fragment.max = 30;
                    fragment.setData(fragment.max);
                    fragment.headerAndFooterWrapper.notifyDataSetChanged();
                    fragment.adapter.notifyDataSetChanged();
                    fragment.swipe_refresh_widget.setRefreshing(false);
                    break;
            }
        }
    }
}
