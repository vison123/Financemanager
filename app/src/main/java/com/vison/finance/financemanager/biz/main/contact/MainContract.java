package com.vison.finance.financemanager.biz.main.contact;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MainContract {
    public interface MainView {

        void switch2Project();

        void switch2Statistics();

        void switch2Money();
    }

    public interface MainPresenter {
        void switchNavigation(int id);

        void getProjectList();
    }

    public interface MainModel {

        void getProjectList();
    }
}
