package com.lengjiye.toolkit;

import android.test.AndroidTestCase;
import android.util.Log;

import com.lengjiye.toolkit.model.IUserView;
import com.lengjiye.toolkit.presenter.UserPresenter;

/**
 * 类说明
 * Created by lz on 2016/5/25.
 */
public class MVPTest extends AndroidTestCase implements IUserView {
    private UserPresenter userPresenter;

    @Override
    public int getUId() {
        return 0;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public void setFirstName(String firstName) {
        Log.e("lz", "firstName:" + firstName);
    }

    @Override
    public void setLastName(String lastName) {
        Log.e("lz", "lastName:" + lastName);
    }

    public void test() {
        userPresenter = new UserPresenter(this);
        userPresenter.saveUser(10, "asdcs", "csadcas");
        userPresenter.loadUser(10);
    }
}
