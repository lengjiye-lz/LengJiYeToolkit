package com.lengjiye.toolkit.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.lengjiye.toolkit.utils.NoDoubleClickUtils;

import org.xutils.x;

/**
 * activity基类
 * Created by lz on 2016/4/12.
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initOnCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    protected abstract void initOnCreate(Bundle savedInstanceState);


    /**
     * 初始化方法,每个子类都必须继承，用于初始化数组、控件等。
     */
    protected abstract void initView();

    @Override
    public void onClick(View v) {
        if (NoDoubleClickUtils.isDoubleClick()) {
            Toast.makeText(mContext, "你点击太快了", Toast.LENGTH_SHORT).show();
            Log.e("lz", "你点击太快了");
            return;
        }
        click(v);
    }

    
    /**
     * 添加点击事件
     *
     * @param v
     */
    protected void click(View v) {
        Log.e("lz", "点击事件");
    }
}
