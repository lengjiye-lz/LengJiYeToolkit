package com.lengjiye.toolkit.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.utils.Constants;
import com.lengjiye.toolkit.utils.FileUtil;
import com.lengjiye.toolkit.utils.ImageCompress;

import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试图片压缩工具类
 * Created by lz on 2016/6/8.
 */
public class ImageCompressActivity extends BaseActivity {

    @ViewInject(R.id.button1)
    private Button button1;
    @ViewInject(R.id.button2)
    private Button button2;

    private List<String> strings;

    private String oldPath = Environment.getExternalStorageDirectory() + File.separator + Constants.IMAGE_ARTWORK;
    private String newPath = Environment.getExternalStorageDirectory() + File.separator + Constants.IMAGE_THUMB;

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_image_compress);
    }

    @Override
    protected void initView() {
        strings = new ArrayList<>();
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        FileUtil.createFolder(oldPath);
        FileUtil.createFolder(newPath);
        String[] children = FileUtil.getChildren(oldPath);
        formatArray(children);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.button1:
                if (strings.size() < 0) {
                    return;
                }
                ImageCompress.getInstance().compress(strings.get(0), newPath, new ImageCompress.CompressCallBack() {
                    @Override
                    public void success(List<String> strings) {

                    }

                    @Override
                    public void success(String s) {
                        Log.e("lz", "s:" + s);
                    }

                    @Override
                    public void failure(String error) {
                        Log.e("lz", "error:" + error);
                    }
                });
                break;
            case R.id.button2:
                if (strings.size() < 0) {
                    return;
                }
                ImageCompress.getInstance().compress(strings, newPath, new ImageCompress.CompressCallBack() {
                    @Override
                    public void success(List<String> strings) {
                        Log.e("lz", "strings:" + strings);
                    }

                    @Override
                    public void success(String s) {

                    }

                    @Override
                    public void failure(String error) {
                        Log.e("lz", "error:" + error);
                    }
                });
                break;
        }
    }

    /**
     * 数组转化为list
     */
    private void formatArray(String[] children) {
        if (children == null || children.length == 0) {
            return;
        }
        for (String s : children) {
            strings.add(oldPath + File.separator + s);
        }
        Log.e("lz", "strings:" + strings);
    }

}
