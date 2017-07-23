package com.vison.finance.financemanager.framework.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.vison.finance.financemanager.framework.base.BaseApplication;
import com.vison.finance.financemanager.framework.bean.Category;
import com.vison.finance.financemanager.framework.bean.Project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DbCategory {
    /**
     * Table Name
     */
    private static final String DATABASE_TABLE_CATEGORY = "t_category";

    /**
     * Table columns
     */
    public static final String KEY_CATEGORY_ID = "category_id";
    public static final String KEY_PROJECT_ID = "project_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_CATEGORY_BUDGET = "category_budget";
    public static final String KEY_IN_AMOUNT = "in_amount";
    public static final String KEY_OUT_AMOUNT = "out_amount";
    public static final String KEY_DESCRIPTION = "description";

    /**
     * Database creation sql statement
     */
    public static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + DATABASE_TABLE_CATEGORY + " (" + KEY_CATEGORY_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + KEY_PROJECT_ID + " VARCHAR(50) NOT NULL, "
                    + KEY_NAME + " VARCHAR(20) NOT NULL, "
                    + KEY_CATEGORY_BUDGET + "  DECIMAL(20,6)  NOT NULL, "
                    + KEY_IN_AMOUNT + "  DECIMAL(20,6), "
                    + KEY_OUT_AMOUNT + "  DECIMAL(20,6), "
                    + KEY_DESCRIPTION + " VARCHAR(255) " + ");";


    public static String getTableName() {
        return DATABASE_TABLE_CATEGORY;
    }


    public static List<Category> findCategoryByProjectId(Long projectId) {
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        String[] columns = {KEY_CATEGORY_ID, KEY_PROJECT_ID, KEY_NAME,
                KEY_CATEGORY_BUDGET, KEY_IN_AMOUNT, KEY_OUT_AMOUNT, KEY_DESCRIPTION};
        Cursor cursor = dbManager.mQuery(DbCategory.getTableName(), columns, KEY_PROJECT_ID + "=?",
                new String[]{projectId.toString()}, null, null, KEY_CATEGORY_ID);
        List<Category> categoryList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setCategory_id(cursor.getLong(cursor.getColumnIndex(DbCategory.KEY_CATEGORY_ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(DbCategory.KEY_NAME)));
                category.setCategory_budget(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(DbCategory.KEY_CATEGORY_BUDGET))));
                category.setIn_amount(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(DbCategory.KEY_IN_AMOUNT))));
                category.setOut_amount(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(DbCategory.KEY_OUT_AMOUNT))));
                category.setDescription(cursor.getString(cursor.getColumnIndex(DbCategory.KEY_DESCRIPTION)));
                categoryList.add(category);
            } while (cursor.moveToNext());
        }
        dbManager.closeAll();
        Collections.reverse(categoryList);
        return categoryList;
    }

    public static Long insertNewCategory(Category category) {
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        ContentValues cv = new ContentValues();
        cv.put(KEY_PROJECT_ID, category.getProject_id());
        cv.put(KEY_NAME, category.getName());
        cv.put(KEY_CATEGORY_BUDGET, category.getCategory_budget().toString());
        cv.put(KEY_IN_AMOUNT, 0);
        cv.put(KEY_OUT_AMOUNT, 0);
        cv.put(KEY_DESCRIPTION, category.getDescription());
        return dbManager.mInsert(DbCategory.getTableName(), null, cv);
    }

    public static Category findCategoryById(Long categoryId) {
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        String[] columns = new String[]{ KEY_CATEGORY_ID, KEY_NAME,KEY_CATEGORY_BUDGET,
                KEY_IN_AMOUNT, KEY_OUT_AMOUNT};
        Cursor cursor = dbManager.mQuery(DbCategory.getTableName(), columns, KEY_CATEGORY_ID + "=?",
                new String[]{categoryId.toString()}, null, null, null);
        Category category = null;
        if (cursor.moveToFirst()) {
            do {
                category = new Category();
                category.setCategory_id(cursor.getLong(cursor.getColumnIndex(KEY_CATEGORY_ID)));
                category.setCategory_budget(
                        BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(KEY_CATEGORY_BUDGET))));
                category.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                category.setIn_amount(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(KEY_IN_AMOUNT))));
                category.setOut_amount(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(KEY_OUT_AMOUNT))));
            } while (cursor.moveToNext());
        }
        return category;
    }

    public static void updateCategoryBudgetAndOutAmount(Long categoryId, BigDecimal amount){
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        Category category = findCategoryById(categoryId);
        if (null != category){
            ContentValues cv = new ContentValues();
            cv.put(KEY_CATEGORY_BUDGET, category.getCategory_budget().subtract(amount).toString());
            cv.put(KEY_OUT_AMOUNT, category.getOut_amount().add(amount).toString());
            dbManager.mUpdate(DbCategory.getTableName(), cv, KEY_CATEGORY_ID + "=?",
                    new String[]{categoryId.toString()});
        }
    }

}
