package com.vison.finance.financemanager;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vison.finance.financemanager.biz.main.contact.MainContract;
import com.vison.finance.financemanager.biz.main.presenter.MainPresenterImpl;
import com.vison.finance.financemanager.biz.money.view.MoneyFragment;
import com.vison.finance.financemanager.biz.project.view.ProjectFragment;
import com.vison.finance.financemanager.biz.publish.view.PublishFragment;
import com.vison.finance.financemanager.framework.base.ActivityManager;
import com.vison.finance.financemanager.framework.base.BaseActivity;
import com.vison.finance.financemanager.framework.db.SqLiteHelper;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements MainContract.MainView{
    private SqLiteHelper sqLiteHelper;
    private SQLiteDatabase db;
    public boolean isExit = false;
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private ImageView tabIcon;
    private TextView tabDesc;
    private int[] tabIconsRes = {R.drawable.selector_rb_project,
            R.drawable.selector_rb_publish, R.drawable.selector_rb_money};
    private int[] tabDescText = {R.string.title_project,
            R.string.title_publish, R.string.title_money};
    private ProjectFragment projectFragment =new ProjectFragment();
    private MoneyFragment moneyfragment =new MoneyFragment();
    private PublishFragment publishfragment = new PublishFragment();
    private Fragment currFragment = projectFragment;
    public MainContract.MainPresenter mainPresenter;

    @Override
    public void initParams() {
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.switchNavigation(0);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        initToolBar();
        initTabLayout();

    }

    @Override
    public void initData() {
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void setClick(View view) {

    }

    @Override
    public void switch2Project() {
        changeFragment(projectFragment);
        toolbar.setTitle(R.string.title_project);
        currFragment = projectFragment;
    }

    @Override
    public void switch2Publish() {
        changeFragment(publishfragment);
        toolbar.setTitle(R.string.title_publish);
        currFragment = publishfragment;
    }

    @Override
    public void switch2Money() {
        changeFragment(moneyfragment);
        toolbar.setTitle(R.string.title_money);
        currFragment = moneyfragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                ActivityManager.getAppManager().finishActivity(MainActivity.this);
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                isExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 1000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initToolBar() {
        toolbar.setTitle("财务管理");
        setSupportActionBar(toolbar);
    }

    private void initTabLayout() {
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_item_publish));
            } else {
                tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_item_view));
                View customView = tabLayout.getTabAt(i).getCustomView();
                tabIcon = (ImageView) customView.findViewById(R.id.tabIcon);
                tabDesc = (TextView) customView.findViewById(R.id.tabDesc);
                tabIcon.setImageResource(tabIconsRes[i]);
                tabDesc.setText(tabDescText[i]);
            }
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainPresenter.switchNavigation(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void changeFragment(Fragment to){
        FragmentManager mFragmentMan = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentMan.beginTransaction();
        if (!to.isAdded()) {    // 先判断是否被add过
            if(currFragment==to){
                transaction.add(R.id.fl_fragments, to).commit();
            }else{
                transaction.hide(currFragment).add(R.id.fl_fragments, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            }
        } else {
            transaction.hide(currFragment).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }
}
