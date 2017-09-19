package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class FrameModeFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private DrawerLayout drawer_layout;

    public FrameModeFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static FrameModeFragment newInstance() {
        FrameModeFragment fragment = new FrameModeFragment();
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frame_mode, container, false);

        return view;
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        drawer_layout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
    }

    @Override
    public void initData() {
        super.initData();
        List<String> strings = getData();
        MainAdapter adapter = new MainAdapter(mContext, strings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        addDefaultFragment();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                addDefaultFragment();
                break;
            case 1:
                FragmentTransaction transactionMVVMTest = getChildFragmentManager().beginTransaction();
                transactionMVVMTest.replace(R.id.frame_layout, MVVMTestFragment.newInstance());
                transactionMVVMTest.commit();
                break;
        }
        drawer_layout.closeDrawers();
    }

    /**
     * 显示默认的fragment
     */
    private void addDefaultFragment() {
        FragmentTransaction transactionMVPTest = getChildFragmentManager().beginTransaction();
        transactionMVPTest.replace(R.id.frame_layout, MVPTestFragment.newInstance());
        transactionMVPTest.commit();
    }
}
