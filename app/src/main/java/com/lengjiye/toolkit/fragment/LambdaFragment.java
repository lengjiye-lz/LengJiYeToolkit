package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lengjiye.toolkit.R;
import com.lengjiye.tools.LogTool;

import butterknife.OnClick;

/**
 * lambda
 * Created by lz on 2016/7/19.
 */
public class LambdaFragment extends BaseFragment {

    private Button button1, button2, button3, button4;

    public LambdaFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static LambdaFragment newInstance() {
        LambdaFragment fragment = new LambdaFragment();
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lambda, container, false);
        return view;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.button1).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
        view.findViewById(R.id.button3).setOnClickListener(this);
        view.findViewById(R.id.button4).setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                testLambda1();
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            default:
                break;
        }
    }

    /**
     * 测试
     */
    private void testLambda1(){
        new Thread(()-> LogTool.e("asdcasd")).start();
    }


}
