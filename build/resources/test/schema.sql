drop table if exists INVOICE_OLD cascade;

create table INVOICE_OLD
(
    ID VARCHAR(36 char) primary key,
    ERP_NUMBER VARCHAR(16 char) not null unique,
    NAME VARCHAR(16 char) not null,
    TOTAL NUMBER not null,
    INVOICE_DATE DATE not null
);

drop table if exists invoice cascade;

create table invoice (
    id varchar(36) primary key,
    supplier_id varchar(64) not null,
    invoice_id varchar(64) not null,
    invoice_date date,
    amount decimal(23,4),
    terms int,
    payment_date date,
    payment_amount decimal(23,4),
    status varchar(32)
);

create unique index unique_index_invoice_id_supplier_id
ON invoice (invoice_id, supplier_id);

