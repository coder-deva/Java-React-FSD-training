show databases;

create database practice;
use practice;

-- table: category
create table category (
    id int primary key auto_increment,
    name varchar(45)
);

-- table: product
create table product (
    id int primary key auto_increment,
    title varchar(45),
    price double,
    description varchar(100),
    category_id int,
    foreign key (category_id) references category(id)
);

-- table: customer
create table customer (
    id int primary key auto_increment,
    name varchar(45),
    city varchar(45)
);

-- table: purchase
create table purchase (
    id int primary key auto_increment,
    date_of_purchase varchar(45),
    customer_id int,
    product_id int,
    foreign key (customer_id) references customer(id),
    foreign key (product_id) references product(id)
);

-- add customer

insert into customer(name,city)values("john doe","mumbai"),("jane doe","chennai");

insert into category (name) values ('mobiles'), ('laptops');


select * from product;
select * from category;
select * from purchase;
select * from customer;

describe purchase;
alter table purchase add column coupon varchar(50) null;
alter table purchase add column amount_paid double not null;

