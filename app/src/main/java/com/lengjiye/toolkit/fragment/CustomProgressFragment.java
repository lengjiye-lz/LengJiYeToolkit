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
 * 自定义进度条
 * Created by lz on 2016/7/19.
 */
public class CustomProgressFragment extends BaseFragment {

    private MaskTextView mask_text_view;
    private Button button;

    private int i = 10;

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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_progress, container, false);
        mask_text_view = (MaskTextView) view.findViewById(R.id.mask_text_view);
        button = (Button) view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mask_text_view.setProgress(i);
                i += 10;
                LogUtil.e("getProgress:" + mask_text_view.getProgress());
            }
        });
    }
}
