package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.utils.Constants;
import com.lengjiye.toolkit.utils.FileUtil;
import com.lengjiye.toolkit.utils.ImageCompress;
import com.lengjiye.toolkit.view.BaseDialog;

import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试图片压缩工具类
 * Created by lz on 2016/6/8.
 */
public class ImageCompressFragment extends BaseFragment implements ImageCompress.CompressCallBack {

    @ViewInject(R.id.button1)
    private Button button1;
    @ViewInject(R.id.button2)
    private Button button2;

    private BaseDialog baseDialog;

    private List<String> strings;

    private String oldPath = Environment.getExternalStorageDirectory() + File.separator + Constants.IMAGE_ARTWORK;
    private String newPath = Environment.getExternalStorageDirectory() + File.separator + Constants.IMAGE_THUMB;

    public ImageCompressFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static ImageCompressFragment newInstance() {
        ImageCompressFragment fragment = new ImageCompressFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_image_compress, container, false);
    }

    @Override
    public void initData() {
        super.initData();
        strings = new ArrayList<>();
        baseDialog = new BaseDialog(mContext);
        baseDialog.setType(BaseDialog.LOADING_ANIM);
        Log.e("lz", "button1:" + button1);
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
                baseDialog.show();
                ImageCompress.getInstance().compress(strings.get(0), newPath, this);
                break;
            case R.id.button2:
                if (strings.size() < 0) {
                    return;
                }
                baseDialog.show();
                ImageCompress.getInstance().compress(strings, newPath, this);
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

    @Override
    public void success(List<String> strings) {
        Log.e("lz", "strings:" + strings);
        baseDialog.dismiss();
    }

    @Override
    public void success(String s) {
        Log.e("lz", "s:" + s);
        baseDialog.dismiss();
    }

    @Override
    public void failure(String error) {
        Log.e("lz", "error:" + error);
        baseDialog.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        baseDialog.destroy();
    }
}
