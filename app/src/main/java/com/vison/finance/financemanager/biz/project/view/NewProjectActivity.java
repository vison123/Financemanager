package com.vison.finance.financemanager.biz.project.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.framework.base.BaseActivity;

import butterknife.BindView;

public class NewProjectActivity extends BaseActivity {
    @BindView(R.id.toolbar_new_project)
    Toolbar toolbar;
    @BindView(R.id.et_project_name)
    EditText mEditText;


    @Override
    public void initParams() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_new_project);
    }

    @Override
    public void initView() {
        toolbar.setTitle("项目新增");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void setClick(View view) {

    }
}
