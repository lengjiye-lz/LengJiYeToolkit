package com.lengjiye.toolkit.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.lengjiye.toolkit.R;

/**
 * 加载对话框
 * 现在有两种形式，默认是loading框，还有一个是确认对话框
 * 需要设置type
 * Created by lz on 2016/6/12.
 */
public class BaseDialog extends Dialog {

    public static final int LOADING = 0; // loading框
    public static final int LOADING_ANIM = 2; // 带有动画效果的loading框
    public static final int CONFIRM = 1; // 确认对话框

    private Context mContext;

    private TextView tv_title;
    private TextView tv_message;
    private Button btn_ok;
    private Button btn_cancel;
    private View vw_divider;

    private int type;// 根据传入的type显示不同的样式

    public BaseDialog(Context context) {
        this(context, R.style.BaseDialog);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context mContext) {
        this.mContext = mContext;
        setType(LOADING);
    }

    /**
     * 根据type显示不同的dialog
     * 默认显示loading框
     */
    private void setDialogType() {
        switch (type) {
            case LOADING:
                loadingDialog();
                break;
            case CONFIRM:
                confirmDialog();
                break;
            case LOADING_ANIM:
                loadingDialogAnim();
                break;
        }
    }

    /**
     * loading
     * 默认对话框不能取消
     */
    private void loadingDialog() {
        setLayoutRes(R.layout.loading_dialog);
        setIsCancel(false);
    }

    /**
     * 带有动画效果的loading框
     * 默认对话框不能取消
     */
    private void loadingDialogAnim() {
        loadingDialog();
        setAnimations(R.style.main_menu_animstyle);
    }

    /**
     * 确认对话框
     */
    private void confirmDialog() {
        View view = setLayoutRes(R.layout.confirm_dialog);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_message = (TextView) view.findViewById(R.id.tv_message);
        btn_ok = (Button) view.findViewById(R.id.btn_ok);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        vw_divider = view.findViewById(R.id.vw_divider);
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public BaseDialog setTitle(String title) {
        if (tv_title != null) {
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(title);
        }
        return this;
    }

    /**
     * 设置内容
     *
     * @param message
     * @return
     */
    public BaseDialog setMessage(String message) {
        if (tv_message != null) {
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText(message);
        }
        return this;
    }

    /**
     * 设置确定按钮文字
     *
     * @param ok
     * @return
     */
    public BaseDialog setOk(String ok) {
        if (btn_ok != null) {
            btn_ok.setText(ok);
        }
        return this;
    }

    /**
     * 设置取消按钮文字
     *
     * @param cancle
     * @return
     */
    public BaseDialog setCancle(String cancle) {
        if (btn_cancel != null) {
            btn_cancel.setText(cancle);
        }
        return this;
    }

    /**
     * 设置ok的点击事件
     *
     * @param l
     * @return
     */
    public BaseDialog setOkClickListener(View.OnClickListener l) {
        if (btn_ok != null) {
            btn_ok.setOnClickListener(l);
        }
        return this;
    }

    /**
     * 设置cancel的点击事件
     *
     * @param l
     * @return
     */
    public BaseDialog setCancelClickListener(View.OnClickListener l) {
        if (btn_cancel != null) {
            btn_cancel.setOnClickListener(l);
        }
        return this;
    }

    /**
     * 设置显示确认对话框还是取消对话框
     * 不设置，两个按钮都显示
     *
     * @param isConfirm
     * @return {@code true} 显示确认按钮，{@code false} 显示取消按钮
     */
    public BaseDialog setIsConfirm(boolean isConfirm) {
        if (vw_divider != null) {
            vw_divider.setVisibility(View.GONE);
        }
        if (btn_ok != null && isConfirm) {
            btn_cancel.setVisibility(View.GONE);
            btn_ok.setVisibility(View.VISIBLE);
            btn_ok.setBackgroundResource(R.drawable.bg_confirm_dialog_btn);
        } else if (btn_cancel != null && !isConfirm) {
            btn_cancel.setVisibility(View.VISIBLE);
            btn_ok.setVisibility(View.GONE);
            btn_cancel.setBackgroundResource(R.drawable.bg_confirm_dialog_btn);
        } else {
            if (vw_divider != null) {
                vw_divider.setVisibility(View.VISIBLE);
            }
        }
        return this;
    }

    /**
     * 设置dialog布局
     */
    public View setLayoutRes(int layoutResID) {
        View view = View.inflate(mContext, layoutResID, null);
        setContentView(view);
        return view;
    }

    /**
     * 设置动画效果
     *
     * @param resId
     * @return
     */
    public void setAnimations(int resId) {
        Window window = getWindow();
        //设置显示动画
        window.setWindowAnimations(resId);
    }

    /**
     * 设置点击的时候是否可以取消
     *
     * @param isCancel
     */
    public void setIsCancel(boolean isCancel) {
        setCancelable(isCancel);
        setCanceledOnTouchOutside(isCancel);
    }

    public int getType() {
        return type;
    }

    /**
     * 设置显示属性
     * 默认是loading对话框
     *
     * @param type
     */
    public void setType(int type) {
        this.type = type;
        setDialogType();
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
