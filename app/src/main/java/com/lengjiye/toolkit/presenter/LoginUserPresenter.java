package com.lengjiye.toolkit.presenter;

import android.os.Handler;

import com.lengjiye.toolkit.bean.User;
import com.lengjiye.toolkit.model.LoginUserModel;
import com.lengjiye.toolkit.model.LoginUserView;
import com.lengjiye.toolkit.model.OnLoginListener;

/**
 * mvp中的Presenter类
 * 负责逻辑操作，通过Presenter实现view和model的通信
 * Created by lz on 2016/5/23.
 */
public class LoginUserPresenter {
    private LoginUserView userView;
    private LoginUserModel userModel;

    private Handler mainHandler = new Handler();

    public LoginUserPresenter(LoginUserView userView) {
        this.userView = userView;
        userModel = new LoginUserModel();
    }

    /**
     * 登录
     */
    public void login() {
        userView.showDialog();
        userModel.login(userView.getName(), userView.getPas(), new OnLoginListener() {
            @Override
            public void onSuccess(final User user) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userView.cleanPas();
                        userView.hideDialog();
                        userView.loginSuccess(200, user);
                    }
                });
            }

            @Override
            public void onFailed() {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userView.cleanPas();
                        userView.hideDialog();
                        userView.loginFailure(0, "密码错误");
                    }
                });
            }
        });
    }

    /**
     * 注册
     */
    public void signIn() {
        userView.showDialog();
        userModel.signIn(userView.getName(), new OnLoginListener() {
            @Override
            public void onSuccess(User user) {
                login();
            }

            @Override
            public void onFailed() {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userView.hideDialog();
                        userView.loginFailure(1, "注册失败");
                    }
                });
            }
        });
    }

    /**
     * 忘记密码
     */
    public void forgetPas() {

    }
}
