package com.lengjiye.toolkit.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lengjiye.toolkit.R;

/**
 * 测试不可点击两次
 * Created by lz on 2016/5/12.
 */
public class NoDoubleTestActivity extends BaseActivity {

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_no_double_test);
    }

    @Override
    protected void initView() {
        findViewById(R.id.text).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    protected void click(View v) {
        super.click(v);
        switch (v.getId()) {
            case R.id.text:
                Toast.makeText(mContext, "text点击事件", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                Toast.makeText(mContext, "button点击事件", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
