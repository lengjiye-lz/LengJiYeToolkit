package com.lengjiye.toolkit.bean;

import java.io.Serializable;

/**
 * Created by liuz on 2017/9/14.
 */

public class User implements Serializable {

    private int id;
    private String name;
    private int age;
    private String sex;
    private String test;
    private Integer integer;

    public String string;

    public User() {
    }

    private User(String test) {
        this.test = test;
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    protected User(int age) {
        this.age = age;
    }

    public User(int age, int id) {
        this.age = age;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String getTest() {
        return test;
    }

    private void setTest(String test) {
        this.test = test;
    }

    public Integer getInteger() {
        return integer;
    }

    private void setInteger(Integer integer) {
        this.integer = integer;
    }
}
