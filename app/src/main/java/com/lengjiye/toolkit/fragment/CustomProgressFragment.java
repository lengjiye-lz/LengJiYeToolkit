package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.view.MaskTextView;
import com.lengjiye.tools.LogTool;

/**
 * 自定义进度条
 * Created by lz on 2016/7/19.
 */
public class CustomProgressFragment extends BaseFragment {

    private MaskTextView mask_text_view;
    private SeekBar seekBar;

    public CustomProgressFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static CustomProgressFragment newInstance() {
        CustomProgressFragment fragment = new CustomProgressFragment();
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom_progress, container, false);
    }

    @Override
    public void initView(View view) {
        mask_text_view = (MaskTextView) view.findViewById(R.id.mask_text_view);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
    }

    @Override
    public void initData() {
        super.initData();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LogTool.e("progress:" + progress);
                mask_text_view.setMaxProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
