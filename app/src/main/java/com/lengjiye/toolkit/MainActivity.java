package com.lengjiye.toolkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lengjiye.toolkit.activity.BaseActivity;
import com.lengjiye.toolkit.activity.StretchTextActivity;
import com.lengjiye.toolkit.view.Toast;

import org.xutils.view.annotation.ViewInject;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.stretch).setOnClickListener(this);
        Toast.makeText(this, "asdc", Toast.LENGTH_SHORT).show();
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
