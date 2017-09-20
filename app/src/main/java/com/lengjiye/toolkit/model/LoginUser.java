package com.lengjiye.toolkit.model;

/**
 * Created by liuz on 2017/9/20.
 */

public interface LoginUser {
    /**
     * 登录
     *
     * @param name
     * @param pas
     */
    void login(String name, String pas, OnLoginListener onLoginListener);

    /**
     * 注册
     *
     * @param name
     */
    void signIn(String name, OnLoginListener onLoginListener);

    /**
     * 忘记密码
     *
     * @param name
     */
    void forgetPas(String name);
}
