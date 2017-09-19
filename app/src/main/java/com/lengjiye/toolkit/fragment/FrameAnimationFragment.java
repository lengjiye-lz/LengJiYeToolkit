package com.lengjiye.toolkit.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.lengjiye.toolkit.R;

/**
 * Frame动画
 * Created by lz on 2016/7/19.
 */
public class FrameAnimationFragment extends BaseFragment {

    private ImageView image;
    private Button button;

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
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frame_animation, container, false);
        return view;
    }

    @Override
    public void initView(View view) {
        image = (ImageView) view.findViewById(R.id.image);
        button = (Button) view.findViewById(R.id.button);
    }

    @Override
    public void initData() {
        super.initData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.frame_animation);
                AnimationDrawable drawable = (AnimationDrawable) image.getDrawable();
                drawable.start();
            }
        });

    }
}
