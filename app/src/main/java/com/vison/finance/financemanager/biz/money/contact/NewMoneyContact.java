package com.vison.finance.financemanager.biz.money.contact;

import com.vison.finance.financemanager.framework.bean.Money;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */

public class NewMoneyContact {
    public interface NewMoneyView {

    }

    public interface NewMoneyPresenter {
        void save(Money money);
    }

    public interface NewMoneyModel {
        void findAllMoneyList();
    }
}
