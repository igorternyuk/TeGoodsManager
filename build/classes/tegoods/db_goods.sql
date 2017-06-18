/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  igor
 * Created: Jun 14, 2017
 */

create database db_goods;

use db_goods;
create table goods(
    id int not null auto_increment primary key,
    name varchar(100),
    price double,
    add_date varchar(20),
    image longblob
);


drop database db_goods;
