package com.vison.finance.financemanager.biz.money.view;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.biz.category.contact.CategoryContact;
import com.vison.finance.financemanager.biz.category.presenter.CategoryPresenterImpl;
import com.vison.finance.financemanager.biz.money.contact.NewMoneyContact;
import com.vison.finance.financemanager.biz.money.presenter.NewMoneyPresenterImpl;
import com.vison.finance.financemanager.biz.project.contact.ProjectContract;
import com.vison.finance.financemanager.biz.project.presenter.ProjectPresenterImpl;
import com.vison.finance.financemanager.biz.project.view.NewProjectActivity;
import com.vison.finance.financemanager.framework.base.BaseActivity;
import com.vison.finance.financemanager.framework.bean.Category;
import com.vison.finance.financemanager.framework.bean.Money;
import com.vison.finance.financemanager.framework.bean.Project;
import com.vison.finance.financemanager.framework.utils.DateUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.angmarch.views.NiceSpinner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import butterknife.BindView;

public class NewMoneyActivity extends BaseActivity
        implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.toolbar_new_money)
    Toolbar toolbar;
    @BindView(R.id.et_money_name)
    EditText etMoneyName;
    @BindView(R.id.et_money_amount)
    EditText etMoneyAmount;
    @BindView(R.id.spinner_money_type)
    MaterialSpinner moneyTypeSpinner;
    @BindView(R.id.spinner_project_name)
    MaterialSpinner projectNameSpinner;
    @BindView(R.id.spinner_category_name)
    MaterialSpinner categoryNameSpinner;
    @BindView(R.id.tv_money_date)
    TextView tvMoneyDate;
    @BindView(R.id.et_money_description)
    TextView etMoneyDescription;
    @BindView(R.id.btn_money_save)
    Button btnMoneySave;
    ProjectContract.ProjectPresenter projectPresenter;
    CategoryContact.CategoryPresenter categoryPresenter;
    NewMoneyContact.NewMoneyPresenter newMoneyPresenter;
    List<Project> projectList;
    List<Category> categoryList;
    Long selectProjectId;
    String selectProjectName;
    Long selectCategoryId;
    String selectCategoryName;
    String moneyInOutType;


    @Override
    public void initParams() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_new_money);
    }

    @Override
    public void initView() {
        toolbar.setTitle("流水新增");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_return);
    }

    @Override
    public void initData() {
        projectPresenter = new ProjectPresenterImpl();
        categoryPresenter = new CategoryPresenterImpl();
        newMoneyPresenter = new NewMoneyPresenterImpl();
        moneyTypeSpinner.setItems("支出","收入");
        moneyInOutType = "支出";
        projectList = projectPresenter.getProjectList();
        if (projectList.size() > 0){
            projectNameSpinner.setItems(projectList);
            selectProjectId = projectList.get(0).getProject_id();
            selectProjectName = projectList.get(0).getProject_name();
            categoryList = categoryPresenter.getCategoryListByProjectId(
                    projectList.get(0).getProject_id());
            if (categoryList.size() > 0){
                categoryNameSpinner.setItems(categoryList);
                selectCategoryId = categoryList.get(0).getCategory_id();
                selectCategoryName = categoryList.get(0).getName();
            }
        }
    }

    @Override
    public void initListeners() {
        tvMoneyDate.setOnClickListener(this);
        btnMoneySave.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        moneyTypeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item.toString(), Snackbar.LENGTH_LONG).show();
                moneyInOutType = item;
            }
        });
        projectNameSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<Project>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, Project item) {
                Snackbar.make(view, "Clicked " + item.toString(), Snackbar.LENGTH_LONG).show();
                selectProjectId = item.getProject_id();
                selectProjectName = item.getProject_name();
                categoryList = categoryPresenter.getCategoryListByProjectId(item.getProject_id());
                if (categoryList.size() == 0){
                    categoryNameSpinner.setItems("");
                    selectCategoryId = null;
                    selectCategoryName = null;
                } else {
                    categoryNameSpinner.setItems(categoryList);
                    selectCategoryId = categoryList.get(0).getCategory_id();
                    selectCategoryName = categoryList.get(0).getName();
                }
            }
        });
        categoryNameSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<Category>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, Category item) {
                Snackbar.make(view, "Clicked " + item.toString(), Snackbar.LENGTH_LONG).show();
                categoryList = categoryPresenter.getCategoryListByProjectId(selectProjectId);
                categoryNameSpinner.setItems(categoryList);
                selectCategoryId = item.getCategory_id();
            }
        });
    }

    @Override
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.tv_money_date:
                showDatePicker();
                break;
            case R.id.btn_money_save:
                saveMoney();
                break;
            default:
                break;
        }
    }

    private void saveMoney() {
        Money money = new Money();
        money.setName(etMoneyName.getText().toString());
        money.setAmount(new BigDecimal(etMoneyAmount.getText().toString()));
        money.setIn_out_type(moneyInOutType);
        money.setProject_id(selectProjectId);
        money.setProject_name(selectProjectName);
        money.setCategory_id(selectCategoryId);
        money.setCategory_name(selectCategoryName);
        money.setOccur_date(
                DateUtils.stringToDate(tvMoneyDate.getText().toString() + " 00:00:00"));
        money.setDescription(etMoneyDescription.getText().toString());
        newMoneyPresenter.save(money);
        finish();
    }

    private void showDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                NewMoneyActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        tvMoneyDate.setText(+dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
    }
}
