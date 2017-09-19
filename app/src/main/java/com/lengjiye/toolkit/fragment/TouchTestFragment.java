package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lengjiye.toolkit.R;

/**
 * 测试事件分发机制
 */
public class TouchTestFragment extends BaseFragment {

    public TouchTestFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static TouchTestFragment newInstance() {
        TouchTestFragment fragment = new TouchTestFragment();
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_touch_test, container, false);
    }
}
