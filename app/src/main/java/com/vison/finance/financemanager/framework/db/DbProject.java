package com.vison.finance.financemanager.framework.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vison.finance.financemanager.framework.base.BaseApplication;
import com.vison.finance.financemanager.framework.utils.DbUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DbProject {
    /**
     * Table Name
     */
    private static final String DATABASE_TABLE_PROJECT = "t_project";

    /**
     * Table columns
     */
    private static final String KEY_PROJECT_ID = "project_id";
    private static final String KEY_PROJECT_NAME = "project_name";
    private static final String KEY_START_DATE = "start_date";
    private static final String KEY_END_DATE = "end_date";
    private static final String KEY_budget = "budget";
    private static final String KEY_in_amount = "in_amount";
    private static final String KEY_out_amount = "out_amount";
    private static final String KEY_description = "description";
    private static final String KEY_comment = "comment";

    /**
     * Database creation sql statement
     */
    public static final String CREATE_PROJECT_TABLE =
            "CREATE TABLE " + DATABASE_TABLE_PROJECT + " (" + KEY_PROJECT_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + KEY_PROJECT_NAME + " VARCHAR(50) NOT NULL, "
                    + KEY_START_DATE + " TEXT NOT NULL, "
                    + KEY_END_DATE + " TEXT NOT NULL, "
                    + KEY_budget + " DECIMAL(20,6) NOT NULL, "
                    + KEY_in_amount + " DECIMAL(20,6), "
                    + KEY_out_amount + " DECIMAL(20,6), "
                    + KEY_description + " VARCHAR(255), "
                    + KEY_comment + " VARCHAR(255) " + ");";

    public static Cursor fetchAllProject() {
        SqLiteHelper sqLiteHelper = new SqLiteHelper(BaseApplication.getContext());
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        return db.query(DATABASE_TABLE_PROJECT, new String[] {KEY_PROJECT_ID, KEY_PROJECT_NAME,
                KEY_budget}, null, null, null, null, null);
    }
}
