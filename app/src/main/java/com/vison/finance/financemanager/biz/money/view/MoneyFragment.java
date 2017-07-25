package com.vison.finance.financemanager.biz.money.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.biz.category.view.CategoryActivity;
import com.vison.finance.financemanager.biz.category.view.CategoryListAdapter;
import com.vison.finance.financemanager.biz.category.view.NewCategoryActivity;
import com.vison.finance.financemanager.biz.money.contact.MoneyContract;
import com.vison.finance.financemanager.biz.money.presenter.MoneyPresenterImpl;
import com.vison.finance.financemanager.biz.project.contact.ProjectContract;
import com.vison.finance.financemanager.biz.project.view.RecyclerViewLinearAdapter;
import com.vison.finance.financemanager.framework.base.BaseFragment;
import com.vison.finance.financemanager.framework.bean.Money;
import com.vison.finance.financemanager.framework.bean.Project;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MoneyFragment extends BaseFragment {
    private MoneyContract.MoneyPresenter mMoneyPresenter;
    @BindView(R.id.rv_money)
    RecyclerView mRecyclerView;
    @BindView(R.id.money_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fab_new_money)
    FloatingActionButton mFloatingActionButton;
    private LinearLayoutManager mLayoutManager;
    private MoneyListAdapter mAdapter;
    List<Money> moneyList;

    @Override
    public void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<Money> newMoneyList = mMoneyPresenter.findAllMoneyList();
                moneyList.clear();
                moneyList.addAll(newMoneyList);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                // 加载完数据设置为不刷新状态，将下拉进度收起来
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mAdapter.setOnItemClickListener(new MoneyListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onItemLongClick(View view) {
                Snackbar.make(view, "Long Click", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewMoneyActivity.class));
            }
        });
    }

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_money, null, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        initRecycleView();
        moneyList = mMoneyPresenter.findAllMoneyList();
    }

    @Override
    public void initPresent() {
        mMoneyPresenter = new MoneyPresenterImpl();
    }

    private void initRecycleView() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //调整SwipeRefreshLayout的位置
        mSwipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                        getResources().getDisplayMetrics()));
        moneyList = mMoneyPresenter.findAllMoneyList();
        if (mAdapter == null) {
            mAdapter = new MoneyListAdapter(getContext(), moneyList);
        }
        mRecyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
