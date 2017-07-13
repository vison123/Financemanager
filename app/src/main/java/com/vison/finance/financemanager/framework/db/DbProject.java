package com.vison.finance.financemanager.framework.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.Logger;
import com.vison.finance.financemanager.framework.base.BaseApplication;
import com.vison.finance.financemanager.framework.bean.Project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public static final String KEY_PROJECT_ID = "project_id";
    public static final String KEY_PROJECT_NAME = "project_name";
    public static final String KEY_START_DATE = "start_date";
    public static final String KEY_END_DATE = "end_date";
    public static final String KEY_BUDGET = "budget";
    public static final String KEY_IN_AMOUNT = "in_amount";
    public static final String KEY_OUT_AMOUNT = "out_amount";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_COMMENT = "comment";

    /**
     * Database creation sql statement
     */
    public static final String CREATE_PROJECT_TABLE =
            "CREATE TABLE " + DATABASE_TABLE_PROJECT + " (" + KEY_PROJECT_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + KEY_PROJECT_NAME + " VARCHAR(50) NOT NULL, "
                    + KEY_START_DATE + " TEXT NOT NULL, "
                    + KEY_END_DATE + " TEXT NOT NULL, "
                    + KEY_BUDGET + " DECIMAL(20,6) NOT NULL, "
                    + KEY_IN_AMOUNT + " DECIMAL(20,6), "
                    + KEY_OUT_AMOUNT + " DECIMAL(20,6), "
                    + KEY_DESCRIPTION + " VARCHAR(255), "
                    + KEY_COMMENT + " VARCHAR(255) " + ");";

    public static String getTableName() {
        return DATABASE_TABLE_PROJECT;
    }


    public static List<Project> findAllProject() {
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        Cursor cursor = dbManager.mQueryAll(DbProject.getTableName(), null);
        List<Project> projectList =  new ArrayList<Project>();
        String data = "";
        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setProject_id(cursor.getLong(cursor.getColumnIndex(DbProject.KEY_PROJECT_ID)));
                project.setProject_name(cursor.getString(cursor.getColumnIndex(DbProject.KEY_PROJECT_NAME)));
                project.setBudget(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(DbProject.KEY_BUDGET))));
                project.setIn_amount(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(DbProject.KEY_IN_AMOUNT))));
                project.setOut_amount(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(DbProject.KEY_OUT_AMOUNT))));
                project.setDescription(cursor.getString(cursor.getColumnIndex(DbProject.KEY_DESCRIPTION)));
                projectList.add(project);
            } while (cursor.moveToNext());
        }
        dbManager.closeAll();
        return projectList;
    }
}
