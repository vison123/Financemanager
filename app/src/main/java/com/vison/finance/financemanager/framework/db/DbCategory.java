package com.vison.finance.financemanager.framework.db;

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
    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_PROJECT_ID = "project_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CATEGORY_BUDGET = "category_budget";
    private static final String KEY_IN_AMOUNT = "in_amount";
    private static final String KEY_OUT_AMOUNT = "out_amount";
    private static final String KEY_DESCRIPTION = "description";

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

}
