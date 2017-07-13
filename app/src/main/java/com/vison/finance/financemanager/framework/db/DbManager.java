package com.vison.finance.financemanager.framework.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/7/8.
 */

public class DbManager {
    private final String TAG = "DBManager";
    private Context context;
    private SQLiteDatabase mSQLiteDatabase;
    private SQLiteOpenHelper mSQLiteOpenHelper;
    private Cursor mCursor;

    public DbManager(Context context) {
        this.context = context;
        mSQLiteOpenHelper = new SqLiteHelper(context);
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
    }

    public DbManager open() throws SQLException {

        return this;
    }

    /**
     * 执行一条sql语句
     *
     * @param sql
     */
    public void mExecSQL(String sql) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mSQLiteDatabase.execSQL(sql);
        closeAll();
    }

    /**
     * 增加数据
     *
     * @param tableName      表名
     * @param nullColumnHack 非空字段名
     * @param values         数据源
     */
    public void mInsert(String tableName, String nullColumnHack, ContentValues values) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mSQLiteDatabase.insert(tableName, nullColumnHack, values);
        closeAll();
    }

    /**
     * 删除数据
     *
     * @param tableName   表名
     * @param whereClause （eg:"_id=?"）
     * @param whereArgs   （eg:new String[] { "01" } ）
     */
    public void mDelete(String tableName, String whereClause, String[] whereArgs) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mSQLiteDatabase.delete(tableName, whereClause, whereArgs);
        closeAll();
    }

    /**
     * 更新
     *
     * @param tableName
     * @param values
     * @param whereClause
     * @param whereArgs
     */
    public void mUpdate(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mSQLiteDatabase.update(tableName, values, whereClause, whereArgs);
        closeAll();
    }

    /**
     * 更新
     *
     * @param tableName   表名
     * @param values      更新的数据
     * @param whereClause 更新的条件（eg:_id = 01）
     */
    public void mUpdate(String tableName, ContentValues values, String whereClause) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mSQLiteDatabase.update(tableName, values, whereClause, null);
        closeAll();
    }

    /**
     * 查询
     *
     * @param tableName
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @return mCursor 游标
     */
    public Cursor mQuery(String tableName, String[] columns, String selection,
                         String[] selectionArgs, String groupBy, String having,
                         String orderBy) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mCursor = mSQLiteDatabase.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy);
        return mCursor;
    }

    /**
     * 查询全部(查询后需要在调用的类中手动调用closeAll()方法来关闭全部函数)
     *
     * @param tableName 表名
     * @param orderBy   排序方式（asc升序，desc降序）
     * @return mCursor 游标
     */
    public Cursor mQueryAll(String tableName, String orderBy) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mCursor = mSQLiteDatabase.query(tableName, null, null, null, null, null, orderBy);
        return mCursor;
    }

    /**
     * 从数据库中删除表
     *
     * @param tableName 表名
     */
    public void mDropTable(String tableName) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mSQLiteDatabase.execSQL("drop table if exists " + tableName);
        Logger.e(TAG, "已删除" + tableName + "表");
        closeAll();
    }

    /**
     * 删除表中的全部数据
     *
     * @param tableName 表名
     */
    public void mDeleteTable(String tableName) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mSQLiteDatabase.execSQL("delete from " + tableName);
        Logger.e(TAG, "已清空" + tableName + "表");
        closeAll();
    }

    /**
     * 判断某张表是否存在
     *
     * @param tableName 表名
     * @return true 存在
     */
    public boolean isTableExist(String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        try {
            mSQLiteDatabase = mSQLiteOpenHelper.getReadableDatabase();
            String sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + tableName.trim() + "' ";
            mCursor = mSQLiteDatabase.rawQuery(sql, null);
            if (mCursor.moveToNext()) {
                int count = mCursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            Logger.e(TAG, tableName + "表不存在");
        }
        closeAll();
        return result;
    }

    /**
     * 获取表中有多少条数据
     *
     * @param tableName
     * @return
     */
    public int getDataNum(String tableName) {
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mCursor = mSQLiteDatabase.query(tableName, null, null, null, null, null, null);
        int num = mCursor.getCount();
        closeAll();
        return num;
    }

    /**
     * 数据库是否存在要查询的这条数据
     *
     * @param tableName  表名
     * @param columnName 需要查询字段
     * @param data       需要查询数据
     * @return
     */
    public boolean hasThisData(String tableName, String columnName, String data) {
        boolean result = false;
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        mCursor = mSQLiteDatabase.query(tableName, null, null, null, null, null, null);
        while (mCursor.moveToNext()) {
            String columnValues = mCursor.getString(mCursor.getColumnIndex(columnName));
            // 有这条数据
            if (data.equals(columnValues)) {
                result = true;
                break;
            }
        }
        closeAll();
        return result;
    }


    /**
     * 关闭全部
     */
    public void closeAll() {
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        } else {
            Logger.e(TAG, "closeAll: mCursor已关闭");
        }
        if (mSQLiteOpenHelper != null) {
            mSQLiteOpenHelper.close();
        } else {
            Logger.e(TAG, "closeAll: mSQLiteOpenHelper已关闭");
        }
        if (mSQLiteDatabase != null && mSQLiteDatabase.isOpen()) {
            mSQLiteDatabase.close();
        } else {
            Logger.e(TAG, "closeAll: mSQLiteDatabase已关闭");
        }
    }
}
