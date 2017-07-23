package com.vison.finance.financemanager.framework.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/5.
 */

public class SqLiteHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME  = "finance.db"; //数据库名称

    private static final int DATABASE_VERSION  = 1; //数据库版本

    public SqLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //实现方法  是一个回调方法
    //在创建数据库时调用
    //什么时候创建数据库：连接数据库的时候，如果数据库文件不存在
    //只调用一次

    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库升级要保证数据不会丢失
    }

    private void createTable (SQLiteDatabase db) {
        db.execSQL(DbProject.CREATE_PROJECT_TABLE);
        db.execSQL(DbCategory.CREATE_CATEGORY_TABLE);
        db.execSQL(DbMoney.CREATE_MONEY_TABLE);
    }

}
