CREATE TABLE stock (
  id NUMBER(10) NOT NULL,
  stock_code VARCHAR2(10) NOT NULL,
  stock_name VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_stock PRIMARY KEY (id),
  CONSTRAINT uq_stock_code_stock UNIQUE (stock_code),
  CONSTRAINT uq_stock_name_stock UNIQUE (stock_name)
);

CREATE TABLE stock_detail (
  stock_id NUMBER(10) NOT NULL PRIMARY KEY,
  comp_name VARCHAR2(100) NOT NULL,
  comp_desc VARCHAR2(100) NOT NULL,
  remark VARCHAR2(100) NOT NULL,
  listed_date DATE NOT NULL,
  CONSTRAINT pk_stock_detail FOREIGN KEY (stock_id) REFERENCES stock (id)
);

CREATE TABLE stock_daily_record (
  record_id NUMBER(10) NOT NULL,
  stock_id NUMBER(10) NOT NULL,
  price_open NUMBER(10,6),
  price_close NUMBER(10, 6),
  price_change NUMBER(10, 6),
  volume NUMBER(10),
  record_date DATE,
  CONSTRAINT pk_stock_daily_record PRIMARY KEY (record_id),
  CONSTRAINT fk_stock_stock_daily_record FOREIGN KEY (stock_id) REFERENCES stock (id),
  CONSTRAINT uq_record_date UNIQUE (record_date)
);

CREATE SEQUENCE stock_sequence START WITH 1 INCREMENT BY 1 CACHE 100;

CREATE SEQUENCE daily_record_sequence START WITH 1 INCREMENT BY 1 CACHE 100;