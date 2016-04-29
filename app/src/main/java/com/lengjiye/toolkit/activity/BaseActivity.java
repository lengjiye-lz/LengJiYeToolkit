package com.lengjiye.toolkit.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

/**
 * activity基类
 * Created by lz on 2016/4/12.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initOnCreate(savedInstanceState);
        x.view().inject(this);
        init();
    }

    protected abstract void initOnCreate(Bundle savedInstanceState);


    /**
     * 初始化方法,每个子类都必须继承，用于初始化数组、控件等。
     */
    protected abstract void init();
}
