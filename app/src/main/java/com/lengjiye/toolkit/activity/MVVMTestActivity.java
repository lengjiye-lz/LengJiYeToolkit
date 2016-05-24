package com.lengjiye.toolkit.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.databinding.ActivityMvvmTestBinding;

/**
 * MVVM框架模式
 * Created by lz on 2016/5/24.
 */
public class MVVMTestActivity extends BaseActivity {

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        //TODO 需要添加代码
        ActivityMvvmTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_test);
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void click(View v) {
        super.click(v);
    }
}
