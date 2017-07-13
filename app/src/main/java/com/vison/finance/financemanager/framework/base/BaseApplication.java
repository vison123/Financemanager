package com.vison.finance.financemanager.framework.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.util.DisplayMetrics;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.vison.finance.financemanager.framework.db.SqLiteHelper;

import java.util.Locale;

/**
 * Created by Administrator on 2017/7/5.
 */

public class BaseApplication  extends Application {
    private static Context context;
    private static SqLiteHelper sqLiteHelper;
    private static SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        setLanguage();
        // Initialize Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
        context = getApplicationContext();
        sqLiteHelper = new SqLiteHelper(context);
        //连接数据库 获取数据库实例
        //getWritableDatabase() 数据写满会报错
        //getReadableDatabase() 数据写满不会报错
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        sqLiteDatabase.close();
    }

    public static Context getContext() {
        return context;
    }

    public static SQLiteDatabase getDataBase() {
        return sqLiteDatabase;
    }

    public static SqLiteHelper getSqLiteHelper() {
        return sqLiteHelper;
    }

    public void setLanguage() {
        Locale locale = getResources().getConfiguration().locale;
        String systemLanguage = locale.getLanguage();
        Configuration config = getResources().getConfiguration();
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (systemLanguage.equals("zh")) {
            config.locale = Locale.CHINA;
        } else {
            config.locale = Locale.ENGLISH;
        }
        resources.updateConfiguration(config, displayMetrics);
    }
}

