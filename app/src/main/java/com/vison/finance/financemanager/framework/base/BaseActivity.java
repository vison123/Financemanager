package com.vison.finance.financemanager.framework.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/8.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActivityManager.getAppManager().addActivity(this);
        setContentView();
        ButterKnife.bind(this);
        initParams();
        initView();
        initData();
        initListeners();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getAppManager().finishActivity(this);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

    // 绑定Presenter
    public abstract void initParams();

    //绑定布局文件
    public abstract void setContentView();

    //初始化数据
    public abstract void initView();

    //初始化数据
    public abstract void initData();

    //设置监听事件
    public abstract void initListeners();

    @Override
    public void onClick(View v) {
        setClick(v);
    }

    //处理点击事件
    public abstract void setClick(View view);

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }
}

