package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lengjiye.toolkit.R;

/**
 * 可伸缩的TextView
 */
public class StretchTextFragment extends BaseFragment {

    public StretchTextFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static StretchTextFragment newInstance() {
        StretchTextFragment fragment = new StretchTextFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_stretch_text, container, false);
    }
}
