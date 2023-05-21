create database saludos;
create user 'yo'@'localhost' identified by '123';
create user 'yo'@'127.0.0.1' identified by '123';
grant all privileges on saludos.* to 'yo'@'localhost';
grant all privileges on saludos.* to 'yo'@'127.0.0.1';

grant all privileges on saludos.* to 'yo'@'%';
flush privileges;

use saludos;
use salida;
create table saludos (id int,nombre varchar(40));
drop table saludos;
show databases;
show tables;
select * from saludador;
