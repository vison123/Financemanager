package com.vison.finance.financemanager.biz.category.presenter;

import com.vison.finance.financemanager.biz.category.contact.NewCategoryContract;
import com.vison.finance.financemanager.framework.bean.Category;
import com.vison.finance.financemanager.framework.db.DbCategory;
import com.vison.finance.financemanager.framework.db.DbProject;

/**
 * Created by Administrator on 2017/7/17.
 */

public class NewCategoryPresenterImpl implements NewCategoryContract.NewCategoryPresenter {
    @Override
    public Long saveCategory(Category category) {
        return DbCategory.insertNewCategory(category);
    }
}
