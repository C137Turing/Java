--user
create database if not exists java_chatroom charset utf8;

use java_chatroom;

drop table if exists user;
create table user(
  userId int primary key auto_increment,
  username varchar(20) unique ,
  password varchar(20)
);

insert into user values (null,"zhangsan","123");
insert into user values (null,"lisi","123");
insert into user values (null,"wangwu","123");
insert into user values(null,"zhaoliu","123");


--friend
drop table if exists friend;
create table friend(
    userId int,
    friendId int
);

insert into friend values(1,2);
insert into friend values(2,1);
insert into friend values(1,3);
insert into friend values(3,1);
insert into friend values(1,4);
insert into friend values(4,1);
insert into friend values(2,3);
insert into friend values(3,2);
insert into friend values(2,4);
insert into friend values(4,2);

--会话表
drop table if exists message_session;
create table message_session(
  sessionId int primary key auto_increment,
  lastTime datetime
);
insert into message_session values("1","2025-05-01 00:00:00");
insert into message_session values("2","2025-06-01 00:00:00");

--会话和用户关联表
drop table if exists message_session_user;
create table message_session_user(
    sessionId int,
    userId int
);

insert into message_session_user values(1,1),(1,2);
insert into message_session_user values(2,1),(1,3);
insert into message_session_user values(2,3),(2,2);

--创建消息表

drop table if exists message;
create table message(
  messageId int primary key auto_increment,
  fromId int,
  sessionId int,
  content varchar(2048),
  postTime datetime
);

insert into message values (1,1,1,"今晚吃啥","2000-05-01 17:00:00");
insert into message values (2,2,1,"不知道","2000-05-01 17:01:00");
insert into message values (3,1,1,"那次麻辣烫","2000-05-01 17:02:00");
insert into message values (4,2,1,"不吃","2000-05-01 17:03:00");
insert into message values (5,1,1,"那吃什么,要不去吃板面","2000-05-01 17:04:00");
insert into message values (6,2,1,"不想吃","2000-05-01 17:05:00");
insert into message values (7,1,1,"这也不吃那也不吃","2000-05-01 17:06:00");
insert into message values (8,1,1,"那就去吃史","2000-05-01 17:06:30");


insert into message values (9,1,2,"老王,撸串去","2000-05-01 17:10:00");
insert into message values (9,3,2,"走","2000-05-01 17:10:06");