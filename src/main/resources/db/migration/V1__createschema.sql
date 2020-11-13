create sequence hibernate_sequence start with 1 increment by 1;
create table customer (id bigint not null, customer_name varchar(255) not null UNIQUE, customer_type integer not null, joining_date date not null, primary key (id));
create table item_detail (id bigint not null, item_name varchar(255) UNIQUE, item_price double, item_type integer, primary key (id));