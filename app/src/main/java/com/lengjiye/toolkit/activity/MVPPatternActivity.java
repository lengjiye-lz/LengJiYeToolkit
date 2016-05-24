package com.lengjiye.toolkit.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.model.IUserView;
import com.lengjiye.toolkit.presenter.UserPresenter;

/**
 * mvp设计模式中view的操作
 * Created by lz on 2016/5/23.
 */
public class MVPPatternActivity extends BaseActivity implements IUserView {

    private UserPresenter userPresenter;
    private EditText et_id;
    private EditText et_first_name;
    private EditText et_last_name;

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mvp_pattern);
    }

    @Override
    protected void initView() {
        userPresenter = new UserPresenter(this);
        findViewById(R.id.btn_cun).setOnClickListener(this);
        findViewById(R.id.btn_du).setOnClickListener(this);
        et_id = (EditText) findViewById(R.id.et_id);
        et_first_name = (EditText) findViewById(R.id.et_first_name);
        et_last_name = (EditText) findViewById(R.id.et_last_name);
    }

    @Override
    protected void click(View v) {
        super.click(v);
        switch (v.getId()) {
            case R.id.btn_cun:
                userPresenter.saveUser(getId(), getFirstName(), getLastName());
                et_id.setText("");
                et_first_name.setText("");
                et_last_name.setText("");
                break;
            case R.id.btn_du:
                userPresenter.loadUser(getId());
                break;
        }
    }

    @Override
    public int getId() {
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
