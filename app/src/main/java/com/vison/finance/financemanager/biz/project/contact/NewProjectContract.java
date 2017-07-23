package com.vison.finance.financemanager.biz.project.contact;

import com.vison.finance.financemanager.framework.bean.Project;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class NewProjectContract {
    public interface NewProjectView {

    }

    public interface NewProjectPresenter {
        Long saveProject(Project project);
    }

    public interface NewProjectModel {
        Long saveProject(Project project);
    }
}
