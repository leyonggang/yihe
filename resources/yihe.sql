create table student(
 sno varchar(10),
 sname varchar(20)
);
select * from student
drop table student
show databases
show tables
DESCRIBE student
--mysqldump -uroot -proot yihe > test.sql ;
show variables like 'character_set_database';