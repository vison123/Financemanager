package com.vison.finance.financemanager.biz.category.contact;

import com.vison.finance.financemanager.framework.bean.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class CategoryContact {
    public interface CategoryView {

    }

    public interface CategoryPresenter {

        List<Category> getCategoryListByProjectId(Long projectId);
    }

    public interface CategoryModel {

        List<Category> getCategoryList();
    }
}
