package com.vison.finance.financemanager.biz.money.presenter;

import com.vison.finance.financemanager.biz.money.contact.NewMoneyContact;
import com.vison.finance.financemanager.framework.bean.Money;
import com.vison.finance.financemanager.framework.db.DbCategory;
import com.vison.finance.financemanager.framework.db.DbMoney;
import com.vison.finance.financemanager.framework.db.DbProject;

/**
 * Created by Administrator on 2017/7/22.
 */

public class NewMoneyPresenterImpl implements NewMoneyContact.NewMoneyPresenter {
    @Override
    public void save(Money money) {
        Long moneyId = DbMoney.insertNewMoney(money);
        if ("支出".equals(money.getIn_out_type())) {
            DbProject.updateProjectBudgetAndOutAmount(
                    money.getProject_id(), money.getAmount());
            DbCategory.updateCategoryBudgetAndOutAmount(
                    money.getCategory_id(), money.getAmount());
        }
    }
}
