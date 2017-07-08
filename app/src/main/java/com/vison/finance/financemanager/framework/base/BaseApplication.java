package com.vison.finance.financemanager.framework.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.util.DisplayMetrics;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.vison.finance.financemanager.framework.db.SqLiteHelper;

import java.util.Locale;

/**
 * Created by Administrator on 2017/7/5.
 */

public class BaseApplication  extends Application {
    private static Context context;
    private SqLiteHelper sqLiteHelper;
    private SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        setLanguage();
        // Initialize Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
        context = getApplicationContext();
        SqLiteHelper dh = new SqLiteHelper(context);
        //连接数据库 获取数据库实例
        //getWritableDatabase() 数据写满会报错
        //getReadableDatabase() 数据写满不会报错
        SQLiteDatabase sd = dh.getWritableDatabase();
        sd.close();
    }

    public static Context getContext() {
        return context;
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

