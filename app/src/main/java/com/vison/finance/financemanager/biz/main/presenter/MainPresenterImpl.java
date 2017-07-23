package com.vison.finance.financemanager.biz.main.presenter;

import com.vison.finance.financemanager.biz.main.contact.MainContract;
import com.vison.finance.financemanager.biz.main.model.MainModelImpl;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MainPresenterImpl implements MainContract.MainPresenter{
    private MainContract.MainView mainView;
    private MainContract.MainModel mainModel;
    private int lastTab;

    public MainPresenterImpl(MainContract.MainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModelImpl();
    }
    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case 0:
                mainView.switch2Project();
                break;
            case 1:
                mainView.switch2Money();
                break;
            case 2:
                mainView.switch2Statistics();
                break;
        }
    }

    @Override
    public void getProjectList() {

    }
}
