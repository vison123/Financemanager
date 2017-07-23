package com.vison.finance.financemanager.biz.project.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.biz.main.contact.MainContract;
import com.vison.finance.financemanager.biz.project.contact.NewProjectContract;
import com.vison.finance.financemanager.biz.project.presenter.NewProjectPresenterImpl;
import com.vison.finance.financemanager.framework.base.BaseActivity;
import com.vison.finance.financemanager.framework.bean.Project;
import com.vison.finance.financemanager.framework.component.MyToast;
import com.vison.finance.financemanager.framework.utils.DateUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;

public class NewProjectActivity extends BaseActivity
        implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.toolbar_new_project)
    Toolbar toolbar;
    @BindView(R.id.et_project_name)
    EditText etProjectName;
    @BindView(R.id.et_project_budget)
    EditText etProjectBudget;
    @BindView(R.id.et_project_description)
    EditText etProjectDescription;
    @BindView(R.id.tv_project_start_time)
    TextView tvProjectStartTime;
    @BindView(R.id.tv_project_end_time)
    TextView tvProjectEndTime;
    @BindView(R.id.btn_project_save)
    Button btnSave;
    public static String timeSelector = "start";
    public NewProjectContract.NewProjectPresenter newProjectPresenter;
    

    @Override
    public void initParams() {
        newProjectPresenter = new NewProjectPresenterImpl();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_new_project);
    }

    @Override
    public void initView() {
        toolbar.setTitle("项目新增");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_return);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {
        tvProjectStartTime.setOnClickListener(this);
        tvProjectEndTime.setOnClickListener(this);
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
            case R.id.tv_project_start_time:
                timeSelector = "start";
                showDatePicker();
                break;
            case R.id.tv_project_end_time:
                timeSelector = "end";
                showDatePicker();
                break;
            case R.id.btn_project_save:
                saveProject();
                break;
            default:
                break;
        }
    }

    private void saveProject() {
        Project project = new Project();
        project.setProject_name(etProjectName.getText().toString());
        project.setBudget(new BigDecimal(etProjectBudget.getText().toString()));
        project.setStart_date(
                DateUtils.stringToDate(tvProjectStartTime.getText().toString() + " 00:00:00"));
        project.setEnd_date(
                DateUtils.stringToDate(tvProjectEndTime.getText().toString() + " 00:00:00"));
        project.setDescription(etProjectDescription.getText().toString());
        Long projectId = newProjectPresenter.saveProject(project);
        MyToast.show(projectId.toString());
        finish();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        if (timeSelector.equals("start")){
            tvProjectStartTime.setText(+dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
        } else if (timeSelector.equals("end")) {
            tvProjectEndTime.setText(+dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
        }
    }

    private void showDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                NewProjectActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
}
