drop schema if exists store;

create schema store;

create table if not exists store.product (
    id integer,
    name varchar(60),
    price integer
);
