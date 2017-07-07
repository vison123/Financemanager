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
   categoty_id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
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

INSERT INTO Project (project_name,start_date,end_date,budget,in_amount,out_amount,comment,description)
VALUES ('project1', '2017-07-01 11:21:10.64', '2018-07-01 11:21:10.64', 30000000, 20000, 200000, '','');
