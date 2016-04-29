package com.lengjiye.toolkit.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lengjiye.toolkit.R;

import org.xutils.view.annotation.ViewInject;

public class TouchTestActivity extends BaseActivity {

    @ViewInject(R.id.layout1)
    private LinearLayout layout1;
    @ViewInject(R.id.layout2)
    private LinearLayout layout2;
    @ViewInject(R.id.text)
    private TextView text;

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_touch_test);
    }

    @Override
    protected void init() {

    }

}
