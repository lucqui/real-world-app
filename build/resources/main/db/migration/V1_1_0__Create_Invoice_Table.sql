create table if not exists invoice (
    id varchar(36) primary key,
    supplier_id varchar(64) not null,
    invoice_id varchar(64) not null,
    amount decimal(23,4),
    invoice_date date,
    terms int,
    payment_date date,
    payment_amount decimal(23,4),
    status varchar(32)
);

create unique index if not exists unique_index_invoice_id_supplier_id
ON invoice (invoice_id, supplier_id);