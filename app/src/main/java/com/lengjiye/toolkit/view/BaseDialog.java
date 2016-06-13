package com.lengjiye.toolkit.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lengjiye.toolkit.R;

/**
 * 加载对话框
 * Created by lz on 2016/6/12.
 */
public class BaseDialog extends Dialog {

    private Context mContext;

    private int layoutResID;// 布局的id
    private int type;// 根据传入的type显示不同的样式

    public BaseDialog(Context context) {
        super(context);
        init(context);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("lz", "onCreate");
        setDialogType();
        initCreate();
    }

    /**
     * 初始化
     */
    private void init(Context mContext) {
        this.mContext = mContext;
        setType(0);
    }

    /**
     * 根据type显示不同的dialog
     * 默认显示loading框
     */
    private void setDialogType() {
        switch (getType()) {
            case 0:
                loadingDialog();
                break;
            case 1:
                confirmDialog();
                break;
        }
    }

    /**
     * loading
     * 默认对话框不能取消
     */
    private void loadingDialog() {
        setLayoutResID(R.layout.loading_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 确认对话框
     */
    private void confirmDialog() {
        setLayoutResID(R.layout.confirm_dialog);
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
