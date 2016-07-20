package com.lengjiye.toolkit.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.lengjiye.toolkit.R;

/**
 * 属性动画
 * Created by lz on 2016/7/19.
 */
public class PropertyAnimationFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    private ImageView image1, image2, image3, image4, image5, image6, image7;
    private Button button1, button2, button3, button4, button5;
    private ViewGroup viewGroup;
    private GridLayout mGridLayout;
    private int mVal;
    private LayoutTransition mTransition;

    private CheckBox mAppear, mChangeAppear, mDisAppear, mChangeDisAppear;

    public PropertyAnimationFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static PropertyAnimationFragment newInstance() {
        PropertyAnimationFragment fragment = new PropertyAnimationFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_animation, container, false);
        image1 = (ImageView) view.findViewById(R.id.image1);
        image2 = (ImageView) view.findViewById(R.id.image2);
        image3 = (ImageView) view.findViewById(R.id.image3);
        image4 = (ImageView) view.findViewById(R.id.image4);
        image5 = (ImageView) view.findViewById(R.id.image5);
        image6 = (ImageView) view.findViewById(R.id.image6);
        image7 = (ImageView) view.findViewById(R.id.image7);

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);

        viewGroup = (ViewGroup) view.findViewById(R.id.id_container);

        mAppear = (CheckBox) view.findViewById(R.id.id_appear);
        mChangeAppear = (CheckBox) view.findViewById(R.id.id_change_appear);
        mDisAppear = (CheckBox) view.findViewById(R.id.id_disappear);
        mChangeDisAppear = (CheckBox) view.findViewById(R.id.id_change_disappear);


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
        image6.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

        mAppear.setOnCheckedChangeListener(this);
        mChangeAppear.setOnCheckedChangeListener(this);
        mDisAppear.setOnCheckedChangeListener(this);
        mChangeDisAppear.setOnCheckedChangeListener(this);

        // 创建一个GridLayout
        mGridLayout = new GridLayout(mContext);
        // 设置每列5个按钮
        mGridLayout.setColumnCount(5);
        // 添加到布局中
        viewGroup.addView(mGridLayout);
        //默认动画全部开启
        mTransition = new LayoutTransition();
        mGridLayout.setLayoutTransition(mTransition);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.image1:
                ObjectAnimator//
                        .ofFloat(image1, "rotationX", 0.0F, 720.0F)//
                        .setDuration(500)//
                        .start();
                break;
            case R.id.image2:
                ObjectAnimator anim = ObjectAnimator//
                        .ofFloat(image2, "sdds", 1.0F, 0.0F)//
                        .setDuration(500);
                anim.start();
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float cVal = (Float) animation.getAnimatedValue();
                        image2.setAlpha(cVal);
                        image2.setScaleX(cVal);
                        image2.setScaleY(cVal);
                    }
                });
                break;
            case R.id.image3:
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
                ObjectAnimator.ofPropertyValuesHolder(image3, pvhX, pvhY, pvhZ).setDuration(1000).start();
                break;
            case R.id.image4:
                ValueAnimator animator = ValueAnimator.ofFloat(0, mContext.getResources().getDimension(R.dimen.dp_500)
                        - image4.getHeight());
                animator.setTarget(image4);
                animator.setDuration(1000).start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image4.setTranslationY((Float) animation.getAnimatedValue());
                    }
                });
            case R.id.button1:
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(image5, "scaleX", 1.0f, 2f);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(image5, "scaleY", 1.0f, 2f);
                AnimatorSet animSet = new AnimatorSet();
                animSet.setDuration(2000);
                animSet.setInterpolator(new LinearInterpolator());
                //两个动画同时执行
                animSet.playTogether(anim1, anim2);
                animSet.start();
            case R.id.button2:
                float cx = image5.getX();
                ObjectAnimator anim11 = ObjectAnimator.ofFloat(image5, "scaleX", 1.0f, 2f);
                ObjectAnimator anim21 = ObjectAnimator.ofFloat(image5, "scaleY", 1.0f, 2f);
                ObjectAnimator anim31 = ObjectAnimator.ofFloat(image5, "x", cx, 0f);
                ObjectAnimator anim41 = ObjectAnimator.ofFloat(image5, "x", cx);

                /**
                 * anim11，anim21,anim31同时执行
                 * anim41接着执行
                 */
                AnimatorSet animSet1 = new AnimatorSet();
                animSet1.play(anim11).with(anim21);
                animSet1.play(anim21).with(anim31);
                animSet1.play(anim41).after(anim31);
                animSet1.setDuration(1000);
                animSet1.start();
                break;

            case R.id.image6:
                Animator animator1 = AnimatorInflater.loadAnimator(mContext, R.animator.property_animation);
                image6.setPivotX(0);
                image6.setPivotY(0);
                //显示的调用invalidate
                image6.invalidate();
                animator1.setTarget(image6);
                animator1.start();
                break;
            case R.id.button3:
                final Button button = new Button(mContext);
                button.setText((++mVal) + "");
                mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mGridLayout.removeView(button);
                    }
                });
                break;
            case R.id.button4:
                // need API12
                image7.animate()//  
                        .alpha(0)//  
                        .y(mContext.getResources().getDimension(R.dimen.dp_500) / 2).setDuration(1000)
                        // need API 12  
                        .withStartAction(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("lz", "START");
                            }
                            // need API 16  
                        }).withEndAction(new Runnable() {

                    @Override
                    public void run() {
                        Log.e("lz", "END");
                        // runOnUiThread是Activity内部的方法,如果当前线程是UI线程,那么行动是立即执行。
                        // 如果当前线程不是UI线程,操作是发布到事件队列的UI线程
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                image7.setY(0);
                                image7.setAlpha(1.0f);
                            }
                        });
                    }
                }).start();
                break;
            case R.id.button5:
                PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("alpha", 1f,
                        0f, 1f);
                PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("y", 0,
                        mContext.getResources().getDimension(R.dimen.dp_500) / 2, 0);
                ObjectAnimator.ofPropertyValuesHolder(image7, holderX, holderY).setDuration(1000).start();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mTransition = new LayoutTransition();
//        mTransition.setAnimator(
//                LayoutTransition.APPEARING,
//                (mAppear.isChecked() ? mTransition
//                        .getAnimator(LayoutTransition.APPEARING) : null));
        mTransition.setAnimator(LayoutTransition.APPEARING, (mAppear
                .isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 0, 1)
                : null));
        mTransition
                .setAnimator(
                        LayoutTransition.CHANGE_APPEARING,
                        (mChangeAppear.isChecked() ? mTransition
                                .getAnimator(LayoutTransition.CHANGE_APPEARING)
                                : null));
        mTransition.setAnimator(
                LayoutTransition.DISAPPEARING,
                (mDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.DISAPPEARING) : null));
        mTransition.setAnimator(
                LayoutTransition.CHANGE_DISAPPEARING,
                (mChangeDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.CHANGE_DISAPPEARING)
                        : null));
        mGridLayout.setLayoutTransition(mTransition);
    }
}
