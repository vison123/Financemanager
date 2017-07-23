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
        return DbProject.findAllProject();
    }
}
