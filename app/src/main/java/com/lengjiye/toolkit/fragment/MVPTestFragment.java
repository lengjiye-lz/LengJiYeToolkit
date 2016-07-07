package com.lengjiye.toolkit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.model.IUserView;
import com.lengjiye.toolkit.presenter.UserPresenter;

/**
 * mvp设计模式中view的操作
 * Created by lz on 2016/5/23.
 */
public class MVPTestFragment extends BaseFragment implements IUserView {

    private UserPresenter userPresenter;
    private EditText et_id;
    private EditText et_first_name;
    private EditText et_last_name;

    public MVPTestFragment() {
    }

    /**
     * 创建一个fragment
     *
     * @return
     */
    public static MVPTestFragment newInstance() {
        MVPTestFragment fragment = new MVPTestFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mvp_test, container, false);
        view.findViewById(R.id.btn_cun).setOnClickListener(this);
        view.findViewById(R.id.btn_du).setOnClickListener(this);
        et_id = (EditText) view.findViewById(R.id.et_id);
        et_first_name = (EditText) view.findViewById(R.id.et_first_name);
        et_last_name = (EditText) view.findViewById(R.id.et_last_name);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        userPresenter = new UserPresenter(this);
    }

    @Override
    protected void click(View v) {
        super.click(v);
        switch (v.getId()) {
            case R.id.btn_cun:
                userPresenter.saveUser(getUId(), getFirstName(), getLastName());
//                et_id.setText("");
                et_first_name.setText("");
                et_last_name.setText("");
                break;
            case R.id.btn_du:
                userPresenter.loadUser(getUId());
                break;
        }
    }

    @Override
    public int getUId() {
        return Integer.parseInt(et_id.getText().toString());
    }

    @Override
    public String getFirstName() {
        return et_first_name.getText().toString();
    }

    @Override
    public String getLastName() {
        return et_last_name.getText().toString();
    }

    @Override
    public void setFirstName(String firstName) {
        et_first_name.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        et_last_name.setText(lastName);
    }
}
