# Financemanager

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