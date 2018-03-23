package com.lengjiye.toolkit.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 类描述:
 * 创建人: lz
 * 创建时间: 2018/3/16
 * 修改备注:
 */

public class TestData implements Parcelable {

    private List<String> list1;
    private List<String> list2;

    public List<String> getList1() {
        return list1;
    }

    public void setList1(List<String> list1) {
        this.list1 = list1;
    }

    public List<String> getList2() {
        return list2;
    }

    public void setList2(List<String> list2) {
        this.list2 = list2;
    }

    public TestData() {
    }

    protected TestData(Parcel in) {
    }

    public static final Creator<TestData> CREATOR = new Creator<TestData>() {
        @Override
        public TestData createFromParcel(Parcel in) {
            return new TestData(in);
        }

        @Override
        public TestData[] newArray(int size) {
            return new TestData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
