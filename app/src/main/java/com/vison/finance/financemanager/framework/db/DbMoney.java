package com.vison.finance.financemanager.framework.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.vison.finance.financemanager.framework.base.BaseApplication;
import com.vison.finance.financemanager.framework.bean.Money;
import com.vison.finance.financemanager.framework.bean.Project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    public static final String KEY_MONEY_ID = "money_id";
    public static final String KEY_CATEGORY_ID = "category_id";
    public static final String KEY_CATEGORY_NAME = "category_name";
    public static final String KEY_PROJECT_ID = "project_id";
    public static final String KEY_PROJECT_NAME = "project_name";
    public static final String KEY_NAME = "name";
    public static final String KEY_IN_OUT_TYPE = "in_out_type";
    public static final String KEY_OCCUR_DATE = "occur_date";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_DESCRIPTION = "description";

    /**
     * Database creation sql statement
     */
    public static final String CREATE_MONEY_TABLE =
            "CREATE TABLE " + DATABASE_TABLE_MONEY + " (" + KEY_MONEY_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + KEY_PROJECT_ID + "  INTEGER  NOT NULL, "
                    + KEY_PROJECT_NAME + "  VARCHAR(30)  NOT NULL, "
                    + KEY_CATEGORY_ID + "  INTEGER  NOT NULL, "
                    + KEY_CATEGORY_NAME + " VARCHAR(30)  NOT NULL, "
                    + KEY_NAME + " VARCHAR(30) NOT NULL, "
                    + KEY_IN_OUT_TYPE + " VARCHAR(20) NOT NULL, "
                    + KEY_OCCUR_DATE + " DECIMAL(20,6) NOT NULL,"
                    + KEY_AMOUNT + " DECIMAL(20,6)  NOT NULL,"
                    + KEY_DESCRIPTION + " VARCHAR(255)" + ");";

    public static String getTableName() {
        return DATABASE_TABLE_MONEY;
    }

    public static List<Money> findAllMoney () {
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        Cursor cursor = dbManager.mQueryAll(DbMoney.getTableName(), KEY_MONEY_ID);
        List<Money> moneyList =  new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Money money = new Money();
                money.setMoney_id(cursor.getLong(cursor.getColumnIndex(DbMoney.KEY_MONEY_ID)));
                money.setName(cursor.getString(cursor.getColumnIndex(DbMoney.KEY_NAME)));
                money.setAmount(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(DbMoney.KEY_AMOUNT))));
                money.setIn_out_type(cursor.getString(cursor.getColumnIndex(DbMoney.KEY_IN_OUT_TYPE)));
                money.setProject_id(cursor.getLong(cursor.getColumnIndex(DbMoney.KEY_PROJECT_ID)));
                money.setProject_name(cursor.getString(cursor.getColumnIndex(DbMoney.KEY_PROJECT_NAME)));
                money.setCategory_id(cursor.getLong(cursor.getColumnIndex(DbMoney.KEY_CATEGORY_ID)));
                money.setCategory_name(cursor.getString(cursor.getColumnIndex(DbMoney.KEY_CATEGORY_NAME)));
                moneyList.add(money);
            } while (cursor.moveToNext());
        }
        dbManager.closeAll();
        Collections.reverse(moneyList);
        return moneyList;
    }

    public static Long insertNewMoney(Money money) {
        DbManager dbManager = new DbManager(BaseApplication.getContext());
        ContentValues cv = new ContentValues();
        cv.put(KEY_PROJECT_ID, money.getProject_id());
        cv.put(KEY_PROJECT_NAME, money.getProject_name());
        cv.put(KEY_CATEGORY_ID, money.getCategory_id());
        cv.put(KEY_CATEGORY_NAME, money.getCategory_name());
        cv.put(KEY_NAME, money.getName());
        cv.put(KEY_IN_OUT_TYPE, money.getIn_out_type());
        cv.put(KEY_OCCUR_DATE, money.getOccur_date().toString());
        cv.put(KEY_AMOUNT, money.getAmount().toString());
        cv.put(KEY_DESCRIPTION, money.getDescription());
        return dbManager.mInsert(DbMoney.getTableName(), null, cv);
    }
}
