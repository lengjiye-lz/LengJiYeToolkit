package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lengjiye.toolkit.R;

/**
 * 测试不可点击两次
 * Created by lz on 2016/5/12.
 */
public class NoDoubleTestFragment extends BaseFragment {

    public NoDoubleTestFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static NoDoubleTestFragment newInstance() {
        NoDoubleTestFragment fragment = new NoDoubleTestFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_no_double_test, container, false);
        view.findViewById(R.id.text).setOnClickListener(this);
        view.findViewById(R.id.button).setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void click(View v) {
        super.click(v);
        switch (v.getId()) {
            case R.id.text:
                Toast.makeText(mContext, "text点击事件", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                Toast.makeText(mContext, "button点击事件", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
