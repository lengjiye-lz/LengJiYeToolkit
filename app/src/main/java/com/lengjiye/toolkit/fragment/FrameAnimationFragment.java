package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.view.MaskTextView;

import org.xutils.common.util.LogUtil;

/**
 * Frame动画
 * Created by lz on 2016/7/19.
 */
public class FrameAnimationFragment extends BaseFragment {


    public FrameAnimationFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static FrameAnimationFragment newInstance() {
        FrameAnimationFragment fragment = new FrameAnimationFragment();
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
