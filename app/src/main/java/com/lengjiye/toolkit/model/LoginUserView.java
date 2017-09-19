package com.lengjiye.toolkit.model;

import com.lengjiye.toolkit.bean.User;

/**
 * view接口
 * 主要是view对数据的读写操作
 * view只对id进行读操作，对FirstName和LastName进行读写操作
 * Created by lz on 2016/5/23.
 */
public interface LoginUserView {
    void LoginSuccess(int code, User user);

    void loginFailure(int code, String error);
}
