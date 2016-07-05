package com.lengjiye.toolkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lengjiye.toolkit.activity.BaseActivity;
import com.lengjiye.toolkit.activity.FrameModeActivity;
import com.lengjiye.toolkit.activity.ImageCompressActivity;
import com.lengjiye.toolkit.activity.NoDoubleTestActivity;
import com.lengjiye.toolkit.activity.OKHttpActivity;
import com.lengjiye.toolkit.activity.RecyclerViewActivity;
import com.lengjiye.toolkit.activity.StretchTextActivity;
import com.lengjiye.toolkit.activity.TouchTestActivity;
import com.lengjiye.toolkit.adapter.MainAdapter;
import com.lengjiye.toolkit.application.LJYApplication;
import com.lengjiye.toolkit.view.MaskTextView;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 主activity
 * Created by lz on 2016/5/23.
 */
public class MainActivity extends BaseActivity {

    private long lastPressTime;
    private TextView textView;
    private int i = 10;

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        List<String> strings = getData();
        MainAdapter adapter = new MainAdapter(mContext, strings);
        ListView listView = (ListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.text);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, StretchTextActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, TouchTestActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, OKHttpActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mContext, NoDoubleTestActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(mContext, FrameModeActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(mContext, ImageCompressActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(mContext, RecyclerViewActivity.class));
                        break;
                }
            }
        });
        final MaskTextView mask_text_view = (MaskTextView) findViewById(R.id.mask_text_view);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mask_text_view.setProgress(mask_text_view.getProgress() + i);
                i += 10;
                LogUtil.e("getProgress:" + mask_text_view.getProgress());
            }
        });
    }

    /**
     * 获取数据源
     *
     * @return
     */
    private List<String> getData() {
        List<String> strings = new ArrayList<>();
        strings.add("可伸缩的TextView");
        strings.add("事件分发机制");
        strings.add("OKHttp请求");
        strings.add("不可重复点击测试");
        strings.add("android常用到的设计模式");
        strings.add("图片压缩工具类");
        strings.add("使用RecyclerView");
        return strings;
    }

    @Override
    protected void click(View v) {
        super.click(v);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastPressTime > 2000) {
                Toast.makeText(getBaseContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                lastPressTime = currentTime;
            } else {
                LJYApplication.getInstance().exit();
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onNetworkLinkFailure() {
        super.onNetworkLinkFailure();
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetworkLinkSuccess() {
        super.onNetworkLinkSuccess();
        textView.setVisibility(View.GONE);
    }
}
