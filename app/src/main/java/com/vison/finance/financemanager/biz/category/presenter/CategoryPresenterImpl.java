package com.vison.finance.financemanager.biz.category.presenter;

import com.vison.finance.financemanager.biz.category.contact.CategoryContact;
import com.vison.finance.financemanager.framework.bean.Category;
import com.vison.finance.financemanager.framework.db.DbCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class CategoryPresenterImpl implements CategoryContact.CategoryPresenter {

    @Override
    public List<Category> getCategoryListByProjectId(Long projectId) {
        return DbCategory.findCategoryByProjectId(projectId);
    }
}
