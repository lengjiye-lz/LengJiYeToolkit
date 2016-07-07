package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
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
public class FrameModeFragment extends BaseFragment {

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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frame_mode, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        drawer_layout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        List<String> strings = getData();
        MainAdapter adapter = new MainAdapter(mContext, strings);
        drawer_layout.openDrawer(Gravity.RIGHT);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        FragmentTransaction transactionMVPTest = getChildFragmentManager().beginTransaction();
                        transactionMVPTest.replace(R.id.frame_layout, MVPTestFragment.newInstance());
                        transactionMVPTest.commit();
                        break;
                    case 1:
                        FragmentTransaction transactionMVVMTest = getChildFragmentManager().beginTransaction();
                        transactionMVVMTest.replace(R.id.frame_layout, MVVMTestFragment.newInstance());
                        transactionMVVMTest.commit();
                        break;
                }
                drawer_layout.closeDrawers();
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
