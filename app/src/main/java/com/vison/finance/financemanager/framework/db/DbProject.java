package com.vison.finance.financemanager.framework.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.Logger;
import com.vison.finance.financemanager.framework.base.BaseApplication;
import com.vison.finance.financemanager.framework.bean.Project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
        Cursor cursor = dbManager.mQueryAll(DbProject.getTableName(), KEY_PROJECT_ID);
        List<Project> projectList = new ArrayList<>();
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
        Collections.reverse(projectList);
        return projectList;
    }

    public static Project findProjectById(Long projectId) {
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        String[] columns = new String[]{KEY_PROJECT_ID, KEY_PROJECT_NAME, KEY_BUDGET,
                KEY_IN_AMOUNT, KEY_OUT_AMOUNT};
        Cursor cursor = dbManager.mQuery(DbProject.getTableName(), columns, KEY_PROJECT_ID + "=?",
                new String[]{projectId.toString()}, null, null, null);
        Project project = null;
        if (cursor.moveToFirst()) {
            do {
                project = new Project();
                project.setProject_id(cursor.getLong(cursor.getColumnIndex(KEY_PROJECT_ID)));
                project.setProject_name(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)));
                project.setBudget(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(KEY_BUDGET))));
                project.setIn_amount(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(KEY_IN_AMOUNT))));
                project.setOut_amount(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(KEY_OUT_AMOUNT))));
            } while (cursor.moveToNext());
        }
        return project;
    }


    public static Long insertNewProject(Project project) {
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        ContentValues cv = new ContentValues();
        cv.put(KEY_PROJECT_NAME, project.getProject_name());
        cv.put(KEY_BUDGET, project.getBudget().toString());
        cv.put(KEY_START_DATE, project.getStart_date().toString());
        cv.put(KEY_END_DATE, project.getEnd_date().toString());
        cv.put(KEY_IN_AMOUNT, 0);
        cv.put(KEY_OUT_AMOUNT, 0);
        cv.put(KEY_DESCRIPTION, project.getDescription());
        cv.put(KEY_COMMENT, "");
        return dbManager.mInsert(DbProject.getTableName(), null, cv);
    }

    public static void deleteProjectById(Long projectId) {

    }

    public static void updateProjectBudgetAndOutAmount(Long projectId, BigDecimal amount) {
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        Project project = findProjectById(projectId);
        if (null != project){
            ContentValues cv = new ContentValues();
            cv.put(KEY_BUDGET, project.getBudget().subtract(amount).toString());
            cv.put(KEY_OUT_AMOUNT, project.getOut_amount().add(amount).toString());
            dbManager.mUpdate(DbProject.getTableName(), cv, KEY_PROJECT_ID + "=?",
                    new String[]{projectId.toString()});
        }
    }

}
