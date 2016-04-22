package com.lengjiye.toolkit.view.TouchTest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 测试点击事件
 * Created by lz on 2016/4/22.
 */
public class LinearLayout1 extends LinearLayout {

    public LinearLayout1(Context context) {
        super(context);
    }

    public LinearLayout1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayout1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("lz", "LinearLayout1  dispatchTouchEvent:" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("lz", "LinearLayout1  onInterceptTouchEvent:" + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("lz", "LinearLayout1  onTouchEvent:" + super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
