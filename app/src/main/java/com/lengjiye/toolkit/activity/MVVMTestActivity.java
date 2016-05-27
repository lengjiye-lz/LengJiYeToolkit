package com.lengjiye.toolkit.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

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


    private SparseArray sparseArray; // 保存临时变量

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_test);
    }

    @Override
    protected void initView() {
        sparseArray = new SparseArray();
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
                userBean.id.set(Integer.parseInt(StringUtils.isBlank(binding.etId.getText().toString()) ? "0" : binding.etId.getText().toString()));
                userBean.firstName.set(binding.etFirstName.getText().toString());
                userBean.lastName.set(binding.etLastName.getText().toString());
                sparseArray.append(0, userBean);
                binding.etId.setText("");
                binding.etFirstName.setText("");
                binding.etLastName.setText("");
                break;
            case R.id.btn_du:
                MVVMUserBean bean = (MVVMUserBean) sparseArray.get(0);
                binding.setUser(bean);
                break;
            case R.id.btn_jia1:
                MVVMUserBean mvvmUserBean = (MVVMUserBean) sparseArray.get(0);
                mvvmUserBean.id.set(Integer.parseInt(binding.etId.getText().toString()) + 1);
                binding.setUser(mvvmUserBean);
                break;
        }
    }
}
