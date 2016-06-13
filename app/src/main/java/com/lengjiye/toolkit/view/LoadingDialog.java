package com.lengjiye.toolkit.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.lengjiye.toolkit.R;

/**
 * 加载对话框
 * Created by lz on 2016/6/12.
 */
public class LoadingDialog extends Dialog {

    private Context mContext;

    private int layoutResID;// 布局的id

    public LoadingDialog(Context context) {
        super(context);
        init(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCreate();
    }

    /**
     * 初始化
     */
    private void init(Context mContext) {
        this.mContext = mContext;
        setLayoutResID(R.layout.loading_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 创建时设置值
     */
    private void initCreate() {
        View view = View.inflate(mContext, getLayoutResID(), null);
        setContentView(view);
    }

    public int getLayoutResID() {
        return layoutResID;
    }

    public void setLayoutResID(int layoutResID) {
        this.layoutResID = layoutResID;
    }

    /**
     * activity 销毁前调用
     */
    public void destroy() {
        if (isShowing()) {
            dismiss();
        }
        cancel();
    }
}
