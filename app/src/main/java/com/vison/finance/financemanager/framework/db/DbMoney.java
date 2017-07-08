package com.vison.finance.financemanager.framework.db;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DbMoney {
    /**
     * Table Name
     */
    private static final String DATABASE_TABLE_MONEY = "t_money";

    /**
     * Table columns
     */
    private static final String KEY_MONEY_ID = "money_id";
    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_PROJECT_ID = "project_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IN_OUT_TYPE = "in_out_type";
    private static final String KEY_OCCUR_DATE = "occur_date";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_DESCRIPTION = "description";

    /**
     * Database creation sql statement
     */
    public static final String CREATE_MONEY_TABLE =
            "CREATE TABLE " + DATABASE_TABLE_MONEY + " (" + KEY_MONEY_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + KEY_CATEGORY_ID + "  INTEGER  NOT NULL, "
                    + KEY_PROJECT_ID + "  INTEGER  NOT NULL, "
                    + KEY_NAME + " VARCHAR(30) NOT NULL, "
                    + KEY_IN_OUT_TYPE + " VARCHAR(20) NOT NULL, "
                    + KEY_OCCUR_DATE + " DECIMAL(20,6) NOT NULL,"
                    + KEY_AMOUNT + " DECIMAL(20,6)  NOT NULL,"
                    + KEY_DESCRIPTION + " VARCHAR(255)" + ");";
}
