package com.vison.finance.financemanager.biz.money.contact;

import com.vison.finance.financemanager.framework.bean.Money;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MoneyContract {
    public interface MoneyView {

    }

    public interface MoneyPresenter {
        List<Money> findAllMoneyList();
    }

    public interface MoneyModel {
        void findAllMoneyList();
    }
}
