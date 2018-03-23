package com.lengjiye.toolkit.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.adapter.RecyclerView1Adapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 类描述:
 * 创建人: lz
 * 创建时间: 2018/3/14
 * 修改备注:
 */

public class ChartLayout extends LinearLayout {

    private RecyclerView recycler_view1;
    private RecyclerView recycler_view2;

    private RecyclerView1Adapter view1Adapter;
    private RecyclerView1Adapter view2Adapter;
    private Context mContext;

    public ChartLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ChartLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ChartLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = View.inflate(context, R.layout.chart_layout, this);
        recycler_view1 = (RecyclerView) view.findViewById(R.id.recycler_view1);
        recycler_view2 = (RecyclerView) view.findViewById(R.id.recycler_view2);
        mContext = context;

        List<String> list1 = new ArrayList<>();
        list1.add("11");
        list1.add("12");
        list1.add("13");
        list1.add("14");
        List<String> list2 = new ArrayList<>();
        list2.add("21");
        list2.add("22");
        list2.add("23");
        list2.add("24");
        list2.add("25");
        setData(list1, list2);
    }


    /**
     * 设置数据
     *
     * @param list1
     * @param list2
     */
    public void setData(List<String> list1, List<String> list2) {
        view1Adapter = new RecyclerView1Adapter(mContext, list1);
        view2Adapter = new RecyclerView1Adapter(mContext, list2);
        recycler_view1.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view2.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view1.setAdapter(view1Adapter);
        recycler_view2.setAdapter(view2Adapter);
    }
}
