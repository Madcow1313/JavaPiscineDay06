
CREATE SCHEMA if not exists shop ;

drop table if exists shop.product;

create table shop.product(
    identifier INTEGER IDENTITY PRIMARY KEY,
    name varchar(255),
    price int
);