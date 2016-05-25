package com.lengjiye.toolkit.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;

import com.lengjiye.toolkit.R;
import com.lengjiye.toolkit.bean.MVVMUserBean;
import com.lengjiye.toolkit.databinding.ActivityMvvmTestBinding;
import com.lengjiye.toolkit.utils.StringUtils;

/**
 * MVVM框架模式
 * Created by lz on 2016/5/24.
 */
public class MVVMTestActivity extends BaseActivity {

    private ActivityMvvmTestBinding binding;
    private EditText et_id;
    private EditText et_first_name;
    private EditText et_last_name;

    private SparseArray sparseArray; // 保存临时变量

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_test);
    }

    @Override
    protected void initView() {
        sparseArray = new SparseArray();
        et_id = (EditText) findViewById(R.id.et_id);
        et_first_name = (EditText) findViewById(R.id.et_first_name);
        et_last_name = (EditText) findViewById(R.id.et_last_name);
        binding.btnCun.setOnClickListener(this);
        binding.btnDu.setOnClickListener(this);
        binding.btnJia1.setOnClickListener(this);
    }


    @Override
    protected void click(View v) {
        super.click(v);
        switch (v.getId()) {
            case R.id.btn_cun:
                MVVMUserBean userBean = new MVVMUserBean();
                userBean.id.set(Integer.parseInt(StringUtils.isBlank(et_id.getText().toString()) ? "0" : et_id.getText().toString()));
                userBean.firstName.set(et_first_name.getText().toString());
                userBean.lastName.set(et_last_name.getText().toString());
                sparseArray.append(0, userBean);
                et_id.setText("");
                et_first_name.setText("");
                et_last_name.setText("");
                break;
            case R.id.btn_du:
                MVVMUserBean bean = (MVVMUserBean) sparseArray.get(0);
                binding.setUser(bean);
                break;
            case R.id.btn_jia1:
                MVVMUserBean mvvmUserBean = (MVVMUserBean) sparseArray.get(0);
                mvvmUserBean.id.set(Integer.parseInt(et_id.getText().toString()) + 1);
                binding.setUser(mvvmUserBean);
                break;
        }
    }
}
