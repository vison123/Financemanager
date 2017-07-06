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
