package com.vison.finance.financemanager.biz.category.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.biz.category.contact.CategoryContact;
import com.vison.finance.financemanager.biz.category.presenter.CategoryPresenterImpl;
import com.vison.finance.financemanager.biz.project.view.NewProjectActivity;
import com.vison.finance.financemanager.biz.project.view.RecyclerViewLinearAdapter;
import com.vison.finance.financemanager.framework.base.BaseActivity;
import com.vison.finance.financemanager.framework.bean.Category;
import com.vison.finance.financemanager.framework.bean.Project;
import com.vison.finance.financemanager.framework.component.MyToast;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class CategoryActivity extends BaseActivity {
    @BindView(R.id.toolbar_category)
    Toolbar toolbar;
    @BindView(R.id.category_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_category)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab_new_category)
    FloatingActionButton mFloatingActionButton;
    private LinearLayoutManager mLayoutManager;
    private CategoryListAdapter mAdapter;
    List<Category> mCategoryList;
    Project mProject;
    CategoryContact.CategoryPresenter mPresenter;

    @Override
    public void initParams() {
        Intent intent = this.getIntent();
        mProject = (Project)intent.getSerializableExtra("project");
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_category);
    }

    @Override
    public void initView() {
        toolbar.setTitle("流水分类");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_return);
    }

    @Override
    public void initData() {
        mPresenter = new CategoryPresenterImpl();
        initRecycleView();
    }

    @Override
    public void initListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCategoryList = mPresenter.getCategoryListByProjectId(mProject.getProject_id());
                mAdapter = new CategoryListAdapter(CategoryActivity.this, mCategoryList);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(CategoryActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
                // 加载完数据设置为不刷新状态，将下拉进度收起来
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mAdapter.setOnItemClickListener(new CategoryListAdapter.OnRecyclerViewItemClickListener() {
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
                Intent intent = new Intent(CategoryActivity.this, NewCategoryActivity.class);
                intent.putExtra("projectId", mProject.getProject_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setClick(View view) {

    }

    private void initRecycleView() {
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //调整SwipeRefreshLayout的位置
        mSwipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                        getResources().getDisplayMetrics()));
        mCategoryList = mPresenter.getCategoryListByProjectId(mProject.getProject_id());
        if (mAdapter == null) {
            mAdapter = new CategoryListAdapter(this, mCategoryList);
        }
        mRecyclerView.setAdapter(mAdapter);

        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
