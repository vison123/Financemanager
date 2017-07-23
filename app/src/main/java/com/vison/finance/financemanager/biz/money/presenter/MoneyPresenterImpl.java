package com.vison.finance.financemanager.biz.money.presenter;

import com.vison.finance.financemanager.biz.money.contact.MoneyContract;
import com.vison.finance.financemanager.framework.bean.Money;
import com.vison.finance.financemanager.framework.db.DbManager;
import com.vison.finance.financemanager.framework.db.DbMoney;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */

public class MoneyPresenterImpl implements MoneyContract.MoneyPresenter {
    @Override
    public List<Money> findAllMoneyList() {
      return DbMoney.findAllMoney();
    }
}
