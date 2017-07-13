package com.vison.finance.financemanager.biz.project.presenter;

import com.vison.finance.financemanager.biz.project.contact.ProjectContract;
import com.vison.finance.financemanager.framework.bean.Project;
import com.vison.finance.financemanager.framework.db.DbProject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ProjectPresenterImpl implements ProjectContract.ProjectPresenter {
    @Override
    public List<Project> getProjectList() {
        List<Project> projectList = new ArrayList<>();
        for (int i = 1; i< 10; i++){
            Project project = new Project();
            project.setProject_name("项目" + i);
            project.setBudget(BigDecimal.valueOf(1000 * i));
            project.setIn_amount(BigDecimal.valueOf(100 * i));
            project.setOut_amount(BigDecimal.valueOf(100 * i));
            projectList.add(project);
        }
        return projectList;
    }
}
