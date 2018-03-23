//package com.lengjiye.toolkit.fragment;
//
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.lengjiye.toolkit.R;
//import com.lengjiye.toolkit.bean.MVVMUserBean;
////import com.lengjiye.toolkit.databinding.FragmentMvvmTestBinding;
//import com.lengjiye.tools.StringTool;
//
///**
// * MVVM框架模式
// * Created by lz on 2016/5/24.
// */
//public class MVVMTestFragment extends BaseFragment {
//
////    private FragmentMvvmTestBinding binding;
//    private SparseArray sparseArray; // 保存临时变量
//
//    public MVVMTestFragment() {
//    }
//
//    /**
//     * 创建一个fragment
//     *
//     * @return
//     */
//    public static MVVMTestFragment newInstance() {
//        MVVMTestFragment fragment = new MVVMTestFragment();
//        return fragment;
//    }
//
//    @Override
//    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_mvvm_test, container, false);
////        binding = DataBindingUtil.bind(view);
//        return view;
//    }
//
//    @Override
//    public void initData() {
//        super.initData();
//        sparseArray = new SparseArray();
//        binding.btnCun.setOnClickListener(this);
//        binding.btnDu.setOnClickListener(this);
//        binding.btnJia1.setOnClickListener(this);
//    }
//
//    @Override
//    protected void click(View v) {
//        super.click(v);
//        switch (v.getId()) {
//            case R.id.btn_cun:
//                MVVMUserBean userBean = new MVVMUserBean();
//                userBean.id.set(Integer.parseInt(StringTool.isBlank(binding.etId.getText().toString()) ? "0" : binding.etId.getText().toString()));
//                userBean.firstName.set(binding.etFirstName.getText().toString());
//                userBean.lastName.set(binding.etLastName.getText().toString());
//                sparseArray.append(0, userBean);
//                binding.etId.setText("");
//                binding.etFirstName.setText("");
//                binding.etLastName.setText("");
//                break;
//            case R.id.btn_du:
//                MVVMUserBean bean = (MVVMUserBean) sparseArray.get(0);
//                binding.setUser(bean);
//                break;
//            case R.id.btn_jia1:
//                MVVMUserBean mvvmUserBean = (MVVMUserBean) sparseArray.get(0);
//                mvvmUserBean.id.set(Integer.parseInt(binding.etId.getText().toString()) + 1);
//                binding.setUser(mvvmUserBean);
//                break;
//        }
//    }
//}
