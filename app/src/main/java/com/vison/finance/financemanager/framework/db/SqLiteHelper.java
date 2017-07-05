package com.vison.finance.financemanager.framework.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2017/7/5.
 */

public class SqLiteHelper extends SQLiteOpenHelper {


    private static final String name = "finance.db"; //数据库名称

    private static final int version = 1; //数据库版本

    //构造方法

    public SqLiteHelper(Context context) {
        //需要调用父类的构造方法 写在第一行
        super(context, name, null, version);
    }

    public SqLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SqLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //实现方法  是一个回调方法
    //在创建数据库时调用
    //什么时候创建数据库：连接数据库的时候，如果数据库文件不存在
    //只调用一次

    public void onCreate(SQLiteDatabase db) {
        //1.创建数据库的语句
        //构造建表语句
        String creaTTable = "create table user (_id integer PRIMARY KEY AUTOINCREMENT NOT NULL,name varchar,age int)";
        db.execSQL(creaTTable);
        Log.e("dbhelper","create table");
        //2.初始化参数 ContentValues
        ContentValues cv = new ContentValues();

        cv.put("name", "tom");
        cv.put("age", "20");
        //返回id long型  如果不成功返回-1
        //1-表名
        //2-空列的默认值
        //3-字段和值的key/value集合
        Long l = db.insert("user", null, cv);
        Log.e("dbhelper","insert data");
        //2.初始化数据

    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库升级要保证数据不会丢失
    }
}
