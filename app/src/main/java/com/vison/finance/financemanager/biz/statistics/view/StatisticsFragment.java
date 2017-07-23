package com.vison.finance.financemanager.biz.statistics.view;

import android.view.LayoutInflater;
import android.view.View;

import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.framework.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/11.
 */

public class StatisticsFragment extends BaseFragment{
    @Override
    public void initListeners() {

    }

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_statistics, null, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresent() {

    }
}
