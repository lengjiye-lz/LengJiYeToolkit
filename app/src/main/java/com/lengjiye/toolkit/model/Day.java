package com.lengjiye.toolkit.model;

import com.lengjiye.tools.LogTool;

/**
 * 枚举类
 * 创建人: lz
 * 创建时间: 2018/3/12
 * 修改备注:
 *
 * @author lz
 */

public enum Day implements Behaviour {
    MONDAY("cdcd"),
    TUESDAY(),
    WEDNESDAY(),
    THURSDAY(),
    FRIDAY(),
    SATURDAY(),
    // 记住要用分号结束
    SUNDAY();

    private String name;

    Day() {
    }

    Day(String name) {
        this.name = name;
    }

    /**
     * 在枚举类里添加一个方法
     *
     * @return
     */
    public String init() {
        return "Hello Word!";
    }

    @Override
    public void print() {
        LogTool.e("name:" + name);
    }

    @Override
    public String getInfo() {
        return name;
    }

}
