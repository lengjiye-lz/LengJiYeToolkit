package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private Button btn_login, btn_sign_in;
    private TextView tv_forget_pas;

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
                login();
                break;
            case R.id.btn_sign_in:
                signIn();
                break;
            case R.id.tv_forget_pas:
                forgetPas();
                break;
        }
    }

    /**
     * 用户登录
     */
    private void login() {
        String name = et_user_name.getText().toString().trim();
        String pas = et_user_pas.getText().toString().trim();
        if (StringTool.isBlank(name) || StringTool.isBlank(pas)) {
            ToastTool.getInstance().show(getActivity().getApplicationContext(), "用户名或密码不能为空");
            return;
        }
        userPresenter.login(name, pas);
    }

    /**
     * 用户注册
     */
    private void signIn() {
        String name = et_user_name.getText().toString().trim();
        if (StringTool.isBlank(name)) {
            ToastTool.getInstance().show(getActivity().getApplicationContext(), "用户名不能为空");
            return;
        }
        userPresenter.signIn(name);
    }

    /**
     * 用户注册
     */
    private void forgetPas() {
        String name = et_user_name.getText().toString().trim();
        if (StringTool.isBlank(name)) {
            ToastTool.getInstance().show(getActivity().getApplicationContext(), "用户名不能为空");
            return;
        }
        userPresenter.forgetPas(name);
    }

    @Override
    public void LoginSuccess(int code, User user) {

    }

    @Override
    public void loginFailure(int code, String error) {

    }
}
