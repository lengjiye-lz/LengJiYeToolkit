package com.lengjiye.toolkit.model;

import android.util.SparseArray;

import com.lengjiye.toolkit.bean.UserBean;

/**
 * 用来保存数据
 * 对model接口的实现类
 * model对数据的处理
 * Created by lz on 2016/5/23.
 */
public class UserModel implements IUserModel {
    private String firstName;
    private String lastName;
    private int id;
    private SparseArray<UserBean> userArray = new SparseArray<UserBean>();

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
        UserBean UserBean = new UserBean(firstName, this.lastName);
        userArray.append(id, UserBean);
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public UserBean load(int id) {
        this.id = id;
        UserBean userBean = userArray.get(this.id, new UserBean("not found",
                "not found"));
        return userBean;

    }

}
