package com.vison.finance.financemanager.biz.category.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.biz.category.contact.NewCategoryContract;
import com.vison.finance.financemanager.biz.category.presenter.NewCategoryPresenterImpl;
import com.vison.finance.financemanager.framework.base.BaseActivity;
import com.vison.finance.financemanager.framework.bean.Category;
import com.vison.finance.financemanager.framework.bean.Project;

import java.math.BigDecimal;

import butterknife.BindView;

public class NewCategoryActivity extends BaseActivity {

    @BindView(R.id.toolbar_new_category)
    Toolbar toolbar;
    @BindView(R.id.et_category_name)
    EditText etCategoryName;
    @BindView(R.id.et_category_budget)
    EditText etCategoryBudget;
    @BindView(R.id.et_category_description)
    EditText etCategoryDescription;
    @BindView(R.id.btn_category_save)
    Button btnSave;
    private NewCategoryContract.NewCategoryPresenter mPresenter;
    private Long mProjectId;

    @Override
    public void initParams() {
        mPresenter = new NewCategoryPresenterImpl();
        Intent intent = this.getIntent();
        mProjectId = intent.getLongExtra("projectId", 0);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_new_category);
    }

    @Override
    public void initView() {
        toolbar.setTitle("类别新增");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_return);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {
        btnSave.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.btn_category_save:
                saveCategory();
                finish();
                break;
            default:
                break;
        }
    }

    private void saveCategory() {
        Category category = new Category();
        category.setProject_id(mProjectId);
        category.setName(etCategoryName.getText().toString());
        category.setCategory_budget(new BigDecimal(etCategoryBudget.getText().toString()));
        category.setDescription(etCategoryDescription.getText().toString());
        mPresenter.saveCategory(category);
    }
}
