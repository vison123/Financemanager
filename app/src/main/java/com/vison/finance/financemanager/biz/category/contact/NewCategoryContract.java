package com.vison.finance.financemanager.biz.category.contact;

import com.vison.finance.financemanager.framework.bean.Category;

/**
 * Created by Administrator on 2017/7/11.
 */

public class NewCategoryContract {
    public interface NewCategoryView {

    }

    public interface NewCategoryPresenter {
        Long saveCategory(Category project);
    }

    public interface NewCategoryModel {
        Long saveCategory(Category project);
    }
}
