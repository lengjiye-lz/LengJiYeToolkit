package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.bean.User;
import com.lengjiye.toolkit.model.IUserView;
import com.lengjiye.toolkit.model.LoginUserView;
import com.lengjiye.toolkit.presenter.LoginUserPresenter;
import com.lengjiye.toolkit.presenter.UserPresenter;
import com.lengjiye.tools.LogTool;
import com.lengjiye.tools.StringTool;
import com.lengjiye.tools.ToastTool;

/**
 * mvp设计模式中view的操作
 * Created by lz on 2017/9/18.
 */
public class MVPLoginFragment extends BaseFragment implements LoginUserView {

    private LoginUserPresenter userPresenter;
    private EditText et_user_name, et_user_pas;
    private TextView tv_forget_pas;
    private LinearLayout linear;

    public MVPLoginFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static MVPLoginFragment newInstance() {
        MVPLoginFragment fragment = new MVPLoginFragment();
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mvp_login, container, false);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        et_user_name = (EditText) view.findViewById(R.id.et_user_name);
        et_user_pas = (EditText) view.findViewById(R.id.et_user_pas);
        tv_forget_pas = (TextView) view.findViewById(R.id.tv_forget_pas);
        linear = (LinearLayout) view.findViewById(R.id.linear);
        view.findViewById(R.id.btn_login).setOnClickListener(this);
        view.findViewById(R.id.btn_sign_in).setOnClickListener(this);
        view.findViewById(R.id.tv_forget_pas).setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        userPresenter = new LoginUserPresenter(this);
    }

    @Override
    protected void click(View v) {
        super.click(v);
        switch (v.getId()) {
            case R.id.btn_login:
                userPresenter.login();
                break;
            case R.id.btn_sign_in:
                userPresenter.signIn();
                break;
            case R.id.tv_forget_pas:
                userPresenter.forgetPas();
                break;
        }
    }

    @Override
    public String getName() {
        return et_user_name.getText().toString().trim();
    }

    @Override
    public String getPas() {
        return et_user_pas.getText().toString().trim();
    }

    @Override
    public void cleanPas() {
        et_user_pas.setText("");
    }

    @Override
    public void showDialog() {
        linear.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDialog() {
        linear.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess(int code, User user) {
        if (code == 200) {
            ToastTool.getInstance().show(getActivity().getApplicationContext(), "登录成功");
        }
    }

    @Override
    public void loginFailure(int code, String error) {
        LogTool.e("error:" + error);
        ToastTool.getInstance().show(getActivity().getApplicationContext(), error);
    }
}
