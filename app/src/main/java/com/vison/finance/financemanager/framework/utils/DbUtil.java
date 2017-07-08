package com.vison.finance.financemanager.framework.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.vison.finance.financemanager.MainActivity;
import com.vison.finance.financemanager.framework.db.SqLiteHelper;

/**
 * Created by Administrator on 2017/7/8.
 */

public class DbUtil {
    private SqLiteHelper mDbHelper;
    private SQLiteDatabase mDb;
    private Context context;

    public DbUtil(Context context) {
        this.context = context;
    }

    public DbUtil open() throws SQLException {
        mDbHelper = new SqLiteHelper(context);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    /**
     * This method is used for closing the connection.
     */
    public void close() {
        mDbHelper.close();
    }
}
