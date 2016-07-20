package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.lengjiye.toolkit.R;

/**
 * Tween动画
 * Created by lz on 2016/7/19.
 */
public class TweenAnimationFragment extends BaseFragment {

    private ImageView image1, image2, image3, image4, image5;

    public TweenAnimationFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static TweenAnimationFragment newInstance() {
        TweenAnimationFragment fragment = new TweenAnimationFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tween_animation, container, false);
        image1 = (ImageView) view.findViewById(R.id.image1);
        image2 = (ImageView) view.findViewById(R.id.image2);
        image3 = (ImageView) view.findViewById(R.id.image3);
        image4 = (ImageView) view.findViewById(R.id.image4);
        image5 = (ImageView) view.findViewById(R.id.image5);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.image1:
                Animation tween_scale = AnimationUtils.loadAnimation(mContext, R.anim.tween_scale);
                image1.startAnimation(tween_scale);
                break;
            case R.id.image2:
                Animation tween_rotate = AnimationUtils.loadAnimation(mContext, R.anim.tween_rotate);
                image2.startAnimation(tween_rotate);
                break;
            case R.id.image3:
                Animation tween_translate = AnimationUtils.loadAnimation(mContext, R.anim.tween_translate);
                image3.startAnimation(tween_translate);
                break;
            case R.id.image4:
                Animation tween_alpha = AnimationUtils.loadAnimation(mContext, R.anim.tween_alpha);
                image4.startAnimation(tween_alpha);
                break;
            case R.id.image5:
                AnimationSet animationSet = new AnimationSet(true);
                ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, 0.5f, 0.5f);
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, 0.5f, 0.5f);
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 0);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(rotateAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.addAnimation(alphaAnimation);
                animationSet.setDuration(3000);
                image5.startAnimation(animationSet);
                break;
        }
    }
}
