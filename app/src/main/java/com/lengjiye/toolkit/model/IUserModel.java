package com.lengjiye.toolkit.model;

import com.lengjiye.toolkit.bean.UserBean;

/**
 * model接口
 * 主要是model对数据的读写操作
 * model需要对ID、FirstName、LastName进行读写操作，并存储在某个载体内
 * (这不是我们所关心的，可以存在内存、文件、数据库或者远程服务器，但对于Presenter及View无影响)
 * Created by lz on 2016/5/23.
 */
public interface IUserModel {
    void setID(int id);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    int getID();

    UserBean load(int id);//通过id读取user信息,返回一个UserBean
}
