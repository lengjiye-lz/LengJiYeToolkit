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
 * LocalBroadcastManager的使用
 */
public class LocalBroadcastManagerFragment extends BaseFragment {
    private LocalBroadcastManager mLocalBroadcastManager;
    private Button button;

    public LocalBroadcastManagerFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static LocalBroadcastManagerFragment newInstance() {
        LocalBroadcastManagerFragment fragment = new LocalBroadcastManagerFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_broadcast_manager, container, false);
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
