// 建表语句，没有逻辑删除，直接删除数据
CREATE TABLE Project(
   project_id     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
   project_name   VARCHAR(50)    NOT NULL,
   start_date     TEXT,
   end_date       TEXT,
   description    VARCHAR(255),
   comment        VARCHAR(255),
   budget         DECIMAL(20,6),
   out_amount     DECIMAL(20,6),
   in_amount      DECIMAL(20,6)
);

CREATE TABLE Category(
   category_id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
   project_id       INTEGER,
   name             VARCHAR(20),
   category_budget  DECIMAL(20,6),
   description      VARCHAR(255)
);

CREATE TABLE Money(
   money_id    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
   money_category_id     INTEGER,
   project_id            INTEGER,
   in_out_type           VARCHAR(20),
   occur_date            DECIMAL(20,6),
   amount                DECIMAL(20,6),
   description           VARCHAR(255)
);

// 初始数据
INSERT INTO Project (project_name,start_date,end_date,budget,in_amount,out_amount,comment,description)
VALUES ('project1', '2017-07-01 11:21:10.64', '2018-07-01 11:21:10.64', 30000000, 20000, 200000, '','');

INSERT INTO Category (project_id,name,category_budget,description)
VALUES (1, 'category1', 3000000, '');
INSERT INTO Category (project_id,name,category_budget,description)
VALUES (1, 'category2', 4000000, '');
INSERT INTO Category (project_id,name,category_budget,description)
VALUES (1, 'category3', 3000000, '');

INSERT INTO Money (money_category_id,project_id,in_out_type,occur_date,amount,description)
VALUES (1,1,'IN','2017-07-07 11:21:10.64',20000,'');
INSERT INTO Money (money_category_id,project_id,in_out_type,occur_date,amount,description)
VALUES (1,1,'IN','2017-07-08 11:21:10.64',30000,'');
INSERT INTO Money (money_category_id,project_id,in_out_type,occur_date,amount,description)
VALUES (1,1,'OUT','2017-07-09 11:21:10.64',40000,'');

// 查询语句
// 首页查询所有项目信息
SELECT * from Project;
//查询某项目下所有类别
SELECT * from Category WHERE project_id = 1;
//查询某类别下所有账单
SELECT * from Money WHERE money_category_id = 1;
//查询项目下所有账单
SELECT * from Money WHERE project_id = 1;

