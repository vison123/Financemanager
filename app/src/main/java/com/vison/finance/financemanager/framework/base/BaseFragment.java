package com.vison.finance.financemanager.framework.base;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vison.finance.financemanager.R;

/**
 * Created by Administrator on 2017/7/8.
 */

public abstract class BaseFragment extends Fragment {

    public FragmentActivity mActivity;

    /**
     * 此方法可以得到上下文对象
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*
     * 返回一个需要展示的View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (FragmentActivity) getActivity();
        View view = initView(inflater);
        initPresent();
        initFindViewById(view);
        initData();
        initListeners();
        return view;
    }

    /**
     * 子类可以复写此方法初始化事件
     */
    public abstract void initListeners();

    /**
     * 子类实现此抽象方法返回View进行展示
     *
     * @return
     */
    public abstract View initView(LayoutInflater inflater);

    /**
     * 初始化控件
     */
    protected abstract void initFindViewById(View view);

    /**
     * 子类在此方法中实现数据的初始化
     */
    public abstract void initData();

    public abstract void initPresent();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_enter_anim, R.anim.activity_out_anim);
    }

}

