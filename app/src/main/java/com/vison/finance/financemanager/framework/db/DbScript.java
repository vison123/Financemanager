package com.vison.finance.financemanager.framework.db;

// 建表语句，没有逻辑删除，直接删除数据
public class DbScript {
    public static final String createProject = "CREATE TABLE Project(\n" +
            "   project_id     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "   project_name   VARCHAR(50)    NOT NULL,\n" +
            "   start_date     TEXT,\n" +
            "   end_date       TEXT,\n" +
            "   description    VARCHAR(255),\n" +
            "   comment        VARCHAR(255),\n" +
            "   budget         DECIMAL(20,6),\n" +
            "   out_amount     DECIMAL(20,6),\n" +
            "   in_amount      DECIMAL(20,6)\n" +
            ");";

    public static final String createCategory = "CREATE TABLE Category(\n" +
            "   category_id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "   project_id       INTEGER,\n" +
            "   name             VARCHAR(20),\n" +
            "   category_budget  DECIMAL(20,6),\n" +
            "   description      VARCHAR(255)\n" +
            ");";

    public static final String createMoney = "CREATE TABLE Money(\n" +
            "   money_id    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "   money_category_id     INTEGER,\n" +
            "   project_id            INTEGER,\n" +
            "   in_out_type           VARCHAR(20),\n" +
            "   occur_date            DECIMAL(20,6),\n" +
            "   amount                DECIMAL(20,6),\n" +
            "   description           VARCHAR(255)\n" +
            ");";
};

