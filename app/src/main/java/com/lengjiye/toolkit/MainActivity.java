package com.lengjiye.toolkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lengjiye.toolkit.activity.BaseActivity;
import com.lengjiye.toolkit.activity.StretchTextActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.stretch).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stretch:
                Intent intent = new Intent(this, StretchTextActivity.class);
                startActivity(intent);
                break;
        }
    }
}
