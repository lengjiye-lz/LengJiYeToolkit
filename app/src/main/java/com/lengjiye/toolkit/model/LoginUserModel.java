package com.lengjiye.toolkit.model;

import com.lengjiye.toolkit.bean.User;

/**
 * Created by liuz on 2017/9/20.
 */

public class LoginUserModel implements LoginUser {

    @Override
    public void login(final String name, final String pas, final OnLoginListener onLoginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    if (pas.equals("123")) {
                        User user = new User();
                        user.setName(name);
                        user.setAge(18);
                        user.setSex("男");
                        user.setId(1);
                        onLoginListener.onSuccess(user);
                    } else {
                        onLoginListener.onFailed();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void signIn(final String name, final OnLoginListener onLoginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    User user = new User();
                    user.setName(name);
                    user.setAge(18);
                    user.setSex("男");
                    user.setId(1);
                    onLoginListener.onSuccess(user);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void forgetPas(String name) {

    }
}
