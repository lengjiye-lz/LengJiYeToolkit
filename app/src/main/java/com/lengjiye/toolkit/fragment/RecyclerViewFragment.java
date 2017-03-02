package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.adapter.RecyclerViewAdapter;
import com.lengjiye.toolkit.adapter.StaggeredHomeAdapter;
import com.lengjiye.toolkit.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView简单使用
 * Created by lz on 2016/6/27.
 */
public class RecyclerViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<String> stringList;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe_refresh_widget;
    private TextView textView;
    private int max = 30;
    private boolean loading = false;

    private RecyclerViewAdapter adapter;
    private StaggeredHomeAdapter homeAdapter;

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
        setHandler();
        swipe_refresh_widget.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark,
                R.color.colorAccent, R.color.cl_0079ff);
        swipe_refresh_widget.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipe_refresh_widget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipe_refresh_widget.setSize(SwipeRefreshLayout.LARGE);
        stringList = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            stringList.add("" + i);
        }
        adapter = new RecyclerViewAdapter(mContext, stringList);
        homeAdapter = new StaggeredHomeAdapter(mContext, stringList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext)); // ListView
//        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3)); // GridView
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)); //瀑布流 横向滑动
//        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST)); //listview分割线
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext)); //GridView分割线
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    homeAdapter.addData(1);
                } else {
                    Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        // recyclerView 添加监听事件
//        recyclerView.addOnScrollListener(new OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                Log.e("lz", "onScrollStateChanged:");
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//                int totalItemCount = layoutManager.getItemCount();
//                Log.e("lz", "totalItemCount ：" + totalItemCount);
//                if (!loading) {
//                    Log.e("lz", "开始加载");
//                    loading = true;
//                    myHandler.sendEmptyMessageDelayed(2, 1000);
//                }
//            }
//        });

        // recyclerView 移除监听事件
//        recyclerView.removeOnScrollListener(new OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
    }

    @Override
    public void onRefresh() {
        myHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void handler(Message msg) {
        super.handler(msg);
        switch (msg.what) {
            case 1:
                stringList.clear();
                max = 30;
                for (int i = 0; i < max; i++) {
                    stringList.add("" + i);
                }
                adapter.notifyDataSetChanged();
                swipe_refresh_widget.setRefreshing(false);
                break;
            case 2:
                for (int i = max; i < 50 + 10; i++) {
                    stringList.add("" + i);
                }
                adapter.notifyDataSetChanged();
                max = max + 10;
                loading = false;
                break;
        }
    }
}
