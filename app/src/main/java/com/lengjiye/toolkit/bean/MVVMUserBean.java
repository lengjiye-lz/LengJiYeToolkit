package com.lengjiye.toolkit.bean;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * MVVM中需要用到的bean
 * 这里有两种写法，一种是继承BaseObservable类
 * 但是这样写还需要写notifyPropertyChanged方法，比较麻烦
 * 另一种是使用ObservableField<String>
 * Created by lz on 2016/5/25.
 */
public class MVVMUserBean extends BaseObservable {
    public final ObservableInt id = new ObservableInt();
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();

    /* 另一种写法
    private String name;

    public String getName() {
        return name;
    }

    @Bindable
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    */
}
