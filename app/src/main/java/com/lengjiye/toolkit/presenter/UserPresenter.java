package com.lengjiye.toolkit.presenter;

import com.lengjiye.toolkit.bean.UserBean;
import com.lengjiye.toolkit.model.IUserModel;
import com.lengjiye.toolkit.model.IUserView;
import com.lengjiye.toolkit.model.UserModel;

/**
 * mvp中的Presenter类
 * 负责逻辑操作，通过Presenter实现view和model的通信
 * Created by lz on 2016/5/23.
 */
public class UserPresenter {
    private IUserView userView;
    private IUserModel userModel;

    public UserPresenter(IUserView userView) {
        this.userView = userView;
        userModel = new UserModel();
    }

    /**
     * 保存数据方法
     *
     * @param id
     * @param firstName
     * @param lastName
     */
    public void saveUser(int id, String firstName, String lastName) {
        userModel.setID(id);
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
    }

    /**
     * 读取数据方法
     *
     * @param id
     */
    public void loadUser(int id) {
        UserBean user = userModel.load(id);
        userView.setFirstName(user.getFirstName());//通过调用IUserView的方法来更新显示
        userView.setLastName(user.getLastName());
    }
}
