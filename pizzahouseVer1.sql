create database pizzahouse;
use pizzahouse;

create table role(
 id int auto_increment,
 role_name varchar(20),
 primary key(id)
);

create table users(
 id int auto_increment,
 email varchar(100),
 password varchar(100),
 create_date timestamp,
 role_id int,
 primary key(id)
);
create table orders(
 id int auto_increment,
 price decimal,
 user_id int,
 primary key(id)
);
create table ordersDetail(
 order_id int ,
 food_id int ,
 create_date timestamp,
 primary key(order_id,food_id)
);
create table category(
 id int auto_increment,
 name varchar(50),
 create_date timestamp,
 primary key(id)
);
create table food(
 id int auto_increment,
 name varchar(100),
 image varchar(255),
 description varchar(255),
 price decimal,
 category_id int,
 primary key(id)
);
alter table users add constraint fk_users_role_id foreign key (role_id) references role(id);
alter table orders add constraint fk_orders_users_id foreign key(user_id) references users(id);
alter table ordersDetail add constraint fk_orders_id foreign key(order_id) references orders(id);
alter table ordersDetail add constraint fk_foods_id foreign key(food_id) references food(id);
alter table food add constraint fk_category_id foreign key(category_id) references category(id);

insert into role(role_name) values ("Admin");
insert into role(role_name) values ("Users");
select*from role;

insert into users(email,password,create_date,role_id) values ("admin@gmail.com","admin123",now(),1);
insert into users(email,password,create_date,role_id) values ("user1@gmail.com","user123",now(),2);
select*from users;

