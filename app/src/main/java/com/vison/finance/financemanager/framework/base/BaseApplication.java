package com.vison.finance.financemanager.framework.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vison.finance.financemanager.framework.db.SqLiteHelper;

/**
 * Created by Administrator on 2017/7/5.
 */

public class BaseApplication  extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("application","create");
        SqLiteHelper dh = new SqLiteHelper(BaseApplication.getContext());
        //连接数据库 获取数据库实例
        //getWritableDatabase() 数据写满会报错
        //getReadableDatabase() 数据写满不会报错
        SQLiteDatabase sd = dh.getWritableDatabase();
        sd.close();
    }

    public static Context getContext() {
        return context;
    }
}

