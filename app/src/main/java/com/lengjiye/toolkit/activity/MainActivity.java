package com.lengjiye.toolkit.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.adapter.MainAdapter;
import com.lengjiye.toolkit.application.LJYApplication;
import com.lengjiye.toolkit.fragment.AnimatorFragment;
import com.lengjiye.toolkit.fragment.BaseFragment;
import com.lengjiye.toolkit.fragment.CustomProgressFragment;
import com.lengjiye.toolkit.fragment.FrameModeFragment;
import com.lengjiye.toolkit.fragment.HandlerThreadFragment;
import com.lengjiye.toolkit.fragment.ImageCompressFragment;
import com.lengjiye.toolkit.fragment.LocalBroadcastManagerFragment;
import com.lengjiye.toolkit.fragment.NoDoubleTestFragment;
import com.lengjiye.toolkit.fragment.OKHttpFragment;
import com.lengjiye.toolkit.fragment.RecyclerViewFragment;
import com.lengjiye.toolkit.fragment.RecyclerViewVlayoutFragment;
import com.lengjiye.toolkit.fragment.StretchTextFragment;
import com.lengjiye.toolkit.fragment.TouchTestFragment;
import com.lengjiye.tools.TimeTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 主activity
 * Created by lz on 2016/5/23.
 */
public class MainActivity extends BaseActivity implements BaseFragment.OnFragmentInteractionListener, AdapterView.OnItemClickListener {

    static final String ACTION_STARTED = "com.example.android.supportv4.STARTED";
    static final String ACTION_UPDATE = "com.example.android.supportv4.UPDATE";
    static final String ACTION_STOPPED = "com.example.android.supportv4.STOPPED";

    private long lastPressTime;
    private TextView textView;
    private DrawerLayout drawer_layout;
    private Toolbar toolbar;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void initOnCreate(Bundle savedInstanceSstate) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        List<String> strings = getData();
        ListView listView = (ListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.text);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
        MainAdapter adapter = new MainAdapter(mContext, strings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        addDefaultFragment();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_STARTED);
        filter.addAction(ACTION_UPDATE);
        filter.addAction(ACTION_STOPPED);
        mLocalBroadcastManager.registerReceiver(mReceiver, filter);
    }

    @Override
    protected void click(View v) {
        super.click(v);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long currentTime = TimeTool.getCurrentTimeMillis();
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * 显示默认的fragment
     */
    private void addDefaultFragment() {
        FragmentTransaction transactionStretchText = getSupportFragmentManager().beginTransaction();
        transactionStretchText.replace(R.id.frame_layout, StretchTextFragment.newInstance());
        transactionStretchText.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                addDefaultFragment();
                break;
            case 1:
                FragmentTransaction transactionTouchTest = getSupportFragmentManager().beginTransaction();
                transactionTouchTest.replace(R.id.frame_layout, TouchTestFragment.newInstance());
                transactionTouchTest.commit();
                break;
            case 2:
                FragmentTransaction transactionOKHttp = getSupportFragmentManager().beginTransaction();
                transactionOKHttp.replace(R.id.frame_layout, OKHttpFragment.newInstance());
                transactionOKHttp.commit();
                break;
            case 3:
                FragmentTransaction transactionNoDoubleTest = getSupportFragmentManager().beginTransaction();
                transactionNoDoubleTest.replace(R.id.frame_layout, NoDoubleTestFragment.newInstance());
                transactionNoDoubleTest.commit();
                break;
            case 4:
                FragmentTransaction transactionFrameMode = getSupportFragmentManager().beginTransaction();
                transactionFrameMode.replace(R.id.frame_layout, FrameModeFragment.newInstance());
                transactionFrameMode.commit();
                break;
            case 5:
                FragmentTransaction transactionImageCompress = getSupportFragmentManager().beginTransaction();
                transactionImageCompress.replace(R.id.frame_layout, ImageCompressFragment.newInstance());
                transactionImageCompress.commit();
                break;
            case 6:
                FragmentTransaction transactionRecyclerView = getSupportFragmentManager().beginTransaction();
                transactionRecyclerView.replace(R.id.frame_layout, RecyclerViewFragment.newInstance());
                transactionRecyclerView.commit();
                break;
            case 7:
                FragmentTransaction transactionCustomProgress = getSupportFragmentManager().beginTransaction();
                transactionCustomProgress.replace(R.id.frame_layout, CustomProgressFragment.newInstance());
                transactionCustomProgress.commit();
                break;
            case 8:
                FragmentTransaction transactionAnimator = getSupportFragmentManager().beginTransaction();
                transactionAnimator.replace(R.id.frame_layout, AnimatorFragment.newInstance());
                transactionAnimator.commit();
                break;
            case 9:
                FragmentTransaction transactionLocalBroadcastManager = getSupportFragmentManager().beginTransaction();
                transactionLocalBroadcastManager.replace(R.id.frame_layout, LocalBroadcastManagerFragment.newInstance());
                transactionLocalBroadcastManager.commit();
                break;
            case 10:
                FragmentTransaction transactionHandlerThread = getSupportFragmentManager().beginTransaction();
                transactionHandlerThread.replace(R.id.frame_layout, HandlerThreadFragment.newInstance());
                transactionHandlerThread.commit();
                break;
            case 11:
                FragmentTransaction recyclerViewVlayoutThread = getSupportFragmentManager().beginTransaction();
                recyclerViewVlayoutThread.replace(R.id.frame_layout, RecyclerViewVlayoutFragment.newInstance());
                recyclerViewVlayoutThread.commit();
                break;
        }
        drawer_layout.closeDrawers();
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
        strings.add("自定义进度条");
        strings.add("动画");
        strings.add("LocalBroadcastManager");
        strings.add("HandlerThread");
        strings.add("RecyclerView使用阿里的vlayout库");
        return strings;
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_STARTED)) {
                Log.e("lz", "STARTED");
            } else if (intent.getAction().equals(ACTION_UPDATE)) {
                Log.e("lz", "Got update: " + intent.getIntExtra("value", 0));
                addDefaultFragment();
            } else if (intent.getAction().equals(ACTION_STOPPED)) {
                Log.e("lz", "STOPPED");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }
}
