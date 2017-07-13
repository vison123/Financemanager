package com.vison.finance.financemanager.biz.project.view;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.biz.project.contact.ProjectContract;
import com.vison.finance.financemanager.biz.project.presenter.ProjectPresenterImpl;
import com.vison.finance.financemanager.framework.base.BaseFragment;
import com.vison.finance.financemanager.framework.bean.Project;
import com.vison.finance.financemanager.framework.component.MyToast;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ProjectFragment extends BaseFragment implements ProjectContract.ProjectView {
    private ProjectContract.ProjectPresenter mProjectPresenter;
    @BindView(R.id.rv_project)
    RecyclerView mRecyclerView;
    @BindView(R.id.grid_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fab_new_project)
    FloatingActionButton mFloatingActionButton;
    private LinearLayoutManager mLayoutManager;
    private RecyclerViewLinearAdapter mAdapter;
    List<Project> mProjectList;

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_project, null, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    public void initData() {
        initRecycleView();
        mProjectList = mProjectPresenter.getProjectList();
    }

    @Override
    public void initPresent() {
        mProjectPresenter = new ProjectPresenterImpl();
    }

    @Override
    public void initListeners() {
        //swipeRefreshLayout刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1200);
            }
        });

        mAdapter.setOnItemClickListener(new RecyclerViewLinearAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                int position = mRecyclerView.getChildAdapterPosition(view);
                MyToast.show("点击了" + position + "个");
                startActivity(new Intent(getActivity(), NewProjectActivity.class));
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
                startActivity(new Intent(getActivity(), NewProjectActivity.class));
            }
        });
    }


    private void initRecycleView() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //调整SwipeRefreshLayout的位置
        mSwipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                        getResources().getDisplayMetrics()));
        mProjectList = mProjectPresenter.getProjectList();
        if (mAdapter == null) {
            mAdapter = new RecyclerViewLinearAdapter(getContext(), mProjectList);
        }
        mRecyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}