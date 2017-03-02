package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lengjiye.toolkit.R;

/**
 * HandlerThread的使用
 * Created by lz on 2016/7/26.
 */
public class HandlerThreadFragment extends BaseFragment {

    private TextView text;
    private Button button;
    private HandlerThread handlerThread;
    private Handler handler;
    private int i = 0;

    public HandlerThreadFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static HandlerThreadFragment newInstance() {
        HandlerThreadFragment fragment = new HandlerThreadFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handler_thread, container, false);
        text = (TextView) view.findViewById(R.id.text);
        button = (Button) view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        text.setText("asdcasd");
        initBackThread();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(1, 500);
            }
        });
    }

    private void initBackThread() {
        handlerThread = new HandlerThread("timer");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(mContext, "" + i, Toast.LENGTH_SHORT).show();
                i++;
                handler.sendEmptyMessageDelayed(1, 500);
            }
        };
    }

}
