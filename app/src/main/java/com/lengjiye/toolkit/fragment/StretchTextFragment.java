package com.lengjiye.toolkit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lengjiye.toolkit.R;

/**
 * 可伸缩的TextView
 */
public class StretchTextFragment extends BaseFragment {
    private LocalBroadcastManager mLocalBroadcastManager;
    private Button button;

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
        View view = inflater.inflate(R.layout.activity_stretch_text, container, false);
        button = (Button) view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.android.supportv4.UPDATE");
                intent.putExtra("value", 12);
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
    }
}
