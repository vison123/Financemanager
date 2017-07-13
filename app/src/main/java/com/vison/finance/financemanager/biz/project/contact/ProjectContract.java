package com.vison.finance.financemanager.biz.project.contact;

import com.vison.finance.financemanager.framework.bean.Project;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ProjectContract {
    public interface ProjectView {

    }

    public interface ProjectPresenter {

        List<Project> getProjectList();
    }

    public interface ProjectModel {

        List<Project> getProjectList();
    }
}
