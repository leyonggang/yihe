
select u.* from yihe_meta_user u
drop table student
show databases
show tables
DESCRIBE yihe_meta_user
--mysqldump -uroot -proot yihe > test.sql ;
drop table yihe_meta_user
drop table yihe_meta_dept
show variables like 'character_set_database';

delete from yihe_meta_dept where did=13;
select * from yihe_meta_dept;
update yihe_meta_dept  set name='铁西区片区'  where did=1
update yihe_meta_dept  set name='开发区片区'  where did=2

select * from yihe_meta_dept
update yihe_meta_dept set type=2 where did=6
insert into yihe_meta_dept(name,type) values('管理部门',2);
insert into yihe_meta_dept(name,type) values('铁西北片区',1);
insert into yihe_meta_dept(name,type) values('安漳路片区',1);
insert into yihe_meta_dept(name,type) values('平原路片区',1);
insert into yihe_meta_dept(name,type) values('铁西南片区',1);
insert into yihe_meta_dept(name,type) values('三角湖片区',1);
insert into yihe_meta_dept(name,type) values('开发区片区',1);
insert into yihe_meta_dept(name,type) values('铁西区片区',1);
insert into yihe_meta_dept(name,type) values('安东南片区',1);
insert into yihe_meta_dept(name,type) values('安东北片区',1);
insert into yihe_meta_dept(name,type) values('东区片区',1);
insert into yihe_meta_dept(name,type) values('灯塔路片区',1);
insert into yihe_meta_dept(name,type) values('地源热泵',1);

select * from yihe_meta_user
update yihe_meta_user set password='4ca4238a0b923820' where account='t1'
update yihe_meta_user set did=1 where account='赵洋'
insert into yihe_meta_user(account,did,name,password,phone,type) values('t1',1,'测试帐号1','4ca4238a0b923820','110',1);
insert into yihe_meta_user(account,did,name,password,phone,type) values('admin',1,'超级管理员','4ca4238a0b923820','110',0);
insert into yihe_meta_user(account,did,name,password,phone,type) values('赵洋',1,'赵洋','4ca4238a0b923820','110',1);
update yihe_meta_user set password='4ca4238a0b923820' where account='t1'

select * from yihe_meta_function;
delete from yihe_meta_function

insert into yihe_meta_function(fid,name,pfid,type) values(1,'信息查询',null,1);
insert into yihe_meta_function(fid,name,pfid,type) values(2,'维修人员调度',null,1);
insert into yihe_meta_function(fid,name,pfid,type) values(3,'自动抄表信息',null,1);
insert into yihe_meta_function(fid,name,pfid,type) values(4,'系统管理',null,1);
insert into yihe_meta_function(fid,name,pfid,type) values(11,'用户基本情况',1,2);
insert into yihe_meta_function(fid,name,pfid,type) values(12,'管网监测数据',1,2);
insert into yihe_meta_function(fid,name,pfid,type) values(13,'开关栓与稽查',1,2);

insert into yihe_meta_function(fid,name,pfid,type) values(22,'维修定位',2,2);
insert into yihe_meta_function(fid,name,pfid,type) values(23,'巡线轨迹',2,2);
insert into yihe_meta_function(fid,name,pfid,type) values(24,'维修信息',2,2);
insert into yihe_meta_function(fid,name,pfid,type) values(25,'维修报表(小区)',2,2);
insert into yihe_meta_function(fid,name,pfid,type) values(26,'维修报表(人员)',2,2);

insert into yihe_meta_function(fid,name,pfid,type) values(31,'日报表',3,2);
insert into yihe_meta_function(fid,name,pfid,type) values(32,'月报表',3,2);
insert into yihe_meta_function(fid,name,pfid,type) values(41,'用户管理',4,2);
insert into yihe_meta_function(fid,name,pfid,type) values(42,'部门管理',4,3);
insert into yihe_meta_function(fid,name,pfid,type) values(43,'功能管理',4,3);



select * from yihe_map_user_function
delete from  yihe_map_user_function where account='admin' and fid=43
insert into yihe_map_user_function(account,fid)values('t1',1);
insert into yihe_map_user_function(account,fid)values('t1',2);
insert into yihe_map_user_function(account,fid)values('t1',4);
insert into yihe_map_user_function(account,fid)values('t1',11);
insert into yihe_map_user_function(account,fid)values('t1',12);
insert into yihe_map_user_function(account,fid)values('t1',13);
insert into yihe_map_user_function(account,fid)values('t1',23);
insert into yihe_map_user_function(account,fid)values('赵洋',11);
insert into yihe_map_user_function(account,fid)values('赵洋',12);
insert into yihe_map_user_function(account,fid)values('赵洋',13);
insert into yihe_map_user_function(account,fid)values('赵洋',22);
insert into yihe_map_user_function(account,fid)values('赵洋',23);
insert into yihe_map_user_function(account,fid)values('赵洋',24);
insert into yihe_map_user_function(account,fid)values('赵洋',25);
insert into yihe_map_user_function(account,fid)values('赵洋',26);
insert into yihe_map_user_function(account,fid)values('赵洋',41);


insert into yihe_map_user_function(account,fid)values('赵洋',42);
insert into yihe_map_user_function(account,fid)values('赵洋',43);

insert into yihe_map_user_function(account,fid)values('admin',41);
insert into yihe_map_user_function(account,fid)values('admin',42);
insert into yihe_map_user_function(account,fid)values('admin',43);


select * from yihe_meta_codetag 


delete from yihe_meta_codetag where tid=4
select * from yihe_meta_codetag where date_format(`date`,'%Y-%m-%d')=date_format(now(),'%Y-%m-%d');
select now() from yihe_meta_codetag 

select * from location;
update location set account='赵洋';
insert into location(account,) values

delete from yihe_map_user_function where account='赵洋' and fid=23;

select * from yihe_meta_function
select CAST(did AS CHAR) as 'value',CAST(name AS CHAR) as text,CAST(type AS CHAR) as 'desc' 
from yihe_meta_dept where 1=1

select u.account,u.name, from yihe_meta_user u left join yihe_map_user_function f on u.account=f.account 
where fid=22 

select * from location
INSERT INTO `location`(locationdate,locationtime,longitude,latitude,account) 
VALUES ('2016-10-15','11:43:40',114.21,36.06,'赵洋'),
('2016-10-15','11:43:40',114.221,36.061,'赵洋'),
('2016-10-15','11:44:00',114.212,36.062,'赵洋'),
('2016-10-15','11:45:20',114.213,36.063,'赵洋');

select * from location

select u.account,d.did,d.name dname,u.name,u.phone from yihe_meta_user u left join yihe_meta_dept d on u.did=d.did 
				left join yihe_map_user_function m on u.account=m.account 
				where u.type=1 and exists( 
				select * from yihe_meta_function f2 where (fid=22 or fid=23) and not exists( 
				select * from yihe_map_user_function m2 where m2.account=u.account and m2.fid=f2.fid ))
				 and m.fid=22


