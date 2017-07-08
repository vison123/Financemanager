package com.vison.finance.financemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.vison.finance.financemanager.framework.base.BaseActivity;
import com.vison.finance.financemanager.framework.base.BaseApplication;
import com.vison.finance.financemanager.framework.db.DbProject;
import com.vison.finance.financemanager.framework.db.SqLiteHelper;
import com.vison.finance.financemanager.framework.utils.DbUtil;


public class MainActivity extends BaseActivity {
    private SqLiteHelper sqLiteHelper;
    private SQLiteDatabase db;
    @Override
    public void initParams() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        DbUtil dbUtil = new DbUtil(this);
        dbUtil.open();
        Cursor cursor = DbProject.fetchAllProject();
        if(cursor != null){
            while(cursor.moveToNext()){
                Logger.i("Student", "Student Name: " + cursor.getString(1) +
                        " Grade " + cursor.getString(2));
            }
        }
        dbUtil.close();
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void setClick(View view) {

    }
}
