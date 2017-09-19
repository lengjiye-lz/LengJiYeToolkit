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
 * 动画
 * Created by lz on 2016/7/19.
 */
public class AnimatorFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private DrawerLayout drawer_layout;

    public AnimatorFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static AnimatorFragment newInstance() {
        AnimatorFragment fragment = new AnimatorFragment();
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
        strings.add("Tween Animation");
        strings.add("Frame Animation");
        strings.add("Property Animation");
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
                FragmentTransaction transactionFrame = getChildFragmentManager().beginTransaction();
                transactionFrame.replace(R.id.frame_layout, FrameAnimationFragment.newInstance());
                transactionFrame.commit();
                break;
            case 2:
                FragmentTransaction transactionProperty = getChildFragmentManager().beginTransaction();
                transactionProperty.replace(R.id.frame_layout, PropertyAnimationFragment.newInstance());
                transactionProperty.commit();
                break;
        }
        drawer_layout.closeDrawers();
    }

    /**
     * 显示默认的fragment
     */
    private void addDefaultFragment() {
        FragmentTransaction transactionTween = getChildFragmentManager().beginTransaction();
        transactionTween.replace(R.id.frame_layout, TweenAnimationFragment.newInstance());
        transactionTween.commit();
    }
}
