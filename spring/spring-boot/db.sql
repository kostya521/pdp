create database users_db;
create user dev@localhost identified by 'dev';
grant all privileges on users_db.* to dev@localhost;

use users_db;

create table users (
  id int auto_increment primary key,
  username varchar(50) not null,
  password varchar(255) not null,
  unique (username)
);
