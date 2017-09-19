package com.lengjiye.toolkit.presenter;

import android.os.Handler;

import com.lengjiye.toolkit.bean.User;
import com.lengjiye.toolkit.model.IUserModel;
import com.lengjiye.toolkit.model.LoginUserView;
import com.lengjiye.toolkit.model.UserModel;

/**
 * mvp中的Presenter类
 * 负责逻辑操作，通过Presenter实现view和model的通信
 * Created by lz on 2016/5/23.
 */
public class LoginUserPresenter {
    private LoginUserView userView;
    private IUserModel userModel;

    private Handler mainHandler = new Handler();

    public LoginUserPresenter(LoginUserView userView) {
        this.userView = userView;
        userModel = new UserModel();

    }

    /**
     * 登录
     *
     * @param name
     * @param pas
     */
    public void login(String name, String pas) {
        if (!pas.equals("123")) {
            userView.loginFailure(1, "密码错误");
            return;
        }
        final String nameString = name;
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.setName(nameString);
                user.setAge(18);
                user.setSex("男");
                user.setId(1);
                userView.LoginSuccess(200, user);
            }
        }, 3000);
    }

    /**
     * 注册
     *
     * @param name
     */
    public void signIn(String name) {
        final String nameString = name;
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.setName(nameString);
                user.setAge(18);
                user.setSex("男");
                user.setId(1);
                userView.LoginSuccess(201, user);
            }
        }, 3000);
    }

    /**
     * 忘记密码
     *
     * @param name
     */
    public void forgetPas(String name) {
        final String nameString = name;
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.setName(nameString);
                user.setAge(18);
                user.setSex("男");
                user.setId(1);
                userView.LoginSuccess(202, user);
            }
        }, 3000);
    }
}
