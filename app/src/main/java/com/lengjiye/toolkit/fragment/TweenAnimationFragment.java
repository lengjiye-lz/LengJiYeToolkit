package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lengjiye.toolkit.R;

/**
 * Tween动画
 * Created by lz on 2016/7/19.
 */
public class TweenAnimationFragment extends BaseFragment {


    public TweenAnimationFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static TweenAnimationFragment newInstance() {
        TweenAnimationFragment fragment = new TweenAnimationFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_animation, container, false);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

    }
}
