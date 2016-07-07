package com.lengjiye.toolkit.fragment;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lengjiye.toolkit.utils.NoDoubleClickUtils;

import org.xutils.x;

/**
 * fragment的基类
 * Created by lz on 2016/4/13.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected OnFragmentInteractionListener mListener;
    public Activity mActivity;
    public Context mContext;

    public boolean isVisible; // 是否显示
    public boolean isCreate; // 是否创建

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("lz", "onAttach");
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * Fragment被创建
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        Log.e("lz", "onCreate");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("lz", "onDetach");
        mListener = null;
    }

    /**
     * 初始化Fragment布局
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        x.view().inject(this, view);
        if (isVisible && !isCreate) {
            Log.e("lz", "第一次加载fragment");
        } else if (isVisible && isCreate) {
            Log.e("lz", "第二次加载fragment");
        }
        isCreate = true;
        Log.e("lz", "onCreateView:" + (view == null));
        return view;
    }

    /**
     * activity创建结束
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        Log.e("lz", "onActivityCreated");
    }

    /**
     * 在使用viewpager的时候调用，防止预加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
        Log.e("lz", "setUserVisibleHint");
    }

    /**
     * fragment可见时调用
     */
    protected void onVisible() {
    }

    /**
     * fragment不可见时调用
     */
    protected void onInvisible() {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("lz", "onHiddenChanged");
    }

    /**
     * 初始化布局, 子类必须实现
     */
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 初始化数据, 子类可以不实现
     */
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        if (NoDoubleClickUtils.isDoubleClick()) {
            Toast.makeText(mContext, "你点击太快了", Toast.LENGTH_SHORT).show();
            Log.e("lz", "你点击太快了");
            return;
        }
        click(v);
    }

    /**
     * 添加点击事件
     *
     * @param v
     */
    protected void click(View v) {
    }

    /**
     * 与activity通信需要实现的接口
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
