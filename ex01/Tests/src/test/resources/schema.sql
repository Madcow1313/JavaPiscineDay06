drop schema if exists product;

create schema product;

create table if not exists product(
    identifier INTEGER IDENTITY PRIMARY KEY,
    name varchar(255),
    price int
);