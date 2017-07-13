package com.vison.finance.financemanager.biz.money.contact;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MoneyContract {
    public interface MoneyView {

        void switch2Project();

        void switch2Publish();

        void switch2Money();
    }

    public interface MoneyPresenter {
        void switchNavigation(int id);

        void getProjectList();
    }

    public interface MoneyModel {

        void getProjectList();
    }
}
