package com.lengjiye.toolkit.view.TouchTest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 测试点击事件
 * Created by lz on 2016/4/22.
 */
public class TextView1 extends TextView {

    public TextView1(Context context) {
        super(context);
    }

    public TextView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("lz", "TextView1  dispatchTouchEvent:" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("lz", "TextView1  onTouchEvent:" + super.onTouchEvent(event));
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("lz", "TextView1  ACTION_DOWN:");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("lz", "TextView1  ACTION_UP:");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("lz", "TextView1  ACTION_MOVE:");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("lz", "TextView1  ACTION_CANCEL:");
                break;
        }
        return super.onTouchEvent(event);
    }
}
