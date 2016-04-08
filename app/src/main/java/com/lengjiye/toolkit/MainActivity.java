package com.lengjiye.toolkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lengjiye.toolkit.activity.StretchTextActivity;
import com.lengjiye.toolkit.view.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
