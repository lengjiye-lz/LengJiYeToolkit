package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.adapter.SubAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * RecyclerView 的扩展库vlayout的简单使用
 * Created by lz on 2017-4-5.
 */
public class RecyclerViewVlayoutFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<String> stringList;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe_refresh_widget;
    private TextView textView;
    private int max = 30;

    private MyHandler myHandler;

    public RecyclerViewVlayoutFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static RecyclerViewVlayoutFragment newInstance() {
        RecyclerViewVlayoutFragment fragment = new RecyclerViewVlayoutFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        swipe_refresh_widget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        textView = (TextView) view.findViewById(R.id.text);
        return view;
    }

    /**
     * 初始化数据
     */
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
        setData(max);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerView.RecycledViewPool viewPool = recyclerView.getRecycledViewPool();
        viewPool.setMaxRecycledViews(0, 20);
        recyclerView.setRecycledViewPool(viewPool);
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recyclerView.setAdapter(delegateAdapter);
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        // 线性布局
        adapters.add(new SubAdapter(mContext, new LinearLayoutHelper(), 1) {
            @Override
            public void onViewRecycled(MainViewHolder holder) {
                if (holder.itemView instanceof ViewPager) {
                    ((ViewPager) holder.itemView).setAdapter(null);
                }
            }

            @Override
            public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == 1)
                    return new MainViewHolder(
                            LayoutInflater.from(mContext).inflate(R.layout.view_pager, parent, false));

                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                if (holder.itemView instanceof ViewPager) {
                    ViewPager viewPager = (ViewPager) holder.itemView;

                    viewPager.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));

                    // from position to get adapter
                    viewPager.setAdapter(new com.lengjiye.toolkit.adapter.PagerAdapter(this, viewPool));
                }
            }
        });
        delegateAdapter.setAdapters(adapters);
        recyclerView.requestLayout();
    }

    @Override
    public void onRefresh() {
        myHandler.sendEmptyMessageDelayed(1, 2000);
    }

    private void setData(int max) {
        for (int i = 0; i < max; i++) {
            stringList.add("" + i);
        }
    }


    static class MyHandler extends Handler {
        WeakReference<BaseFragment> reference;

        MyHandler(Looper looper, BaseFragment fragment) {
            super(looper);
            reference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (reference == null) {
                return;
            }
            RecyclerViewVlayoutFragment fragment = (RecyclerViewVlayoutFragment) reference.get();
            switch (msg.what) {
                case 1:
                    fragment.swipe_refresh_widget.setRefreshing(false);
                    break;
            }
        }
    }
}
