drop database if exists njwb_oa;

create database njwb_oa default charset utf8;

use njwb_oa

drop table if exists t_dept;
#创建部门表
create table t_dept(
   t_dept_no  char(5) primary key,
   t_dept_name varchar(20) not null,
   t_dept_loc  varchar(30) not null,
   t_dept_manager varchar(20),
   t_create_time  date

)engine=InnoDB;
#添加外键
alter table t_dept add foreign key(t_dept_manager) references t_user(t_emp_na);

#初始化部门信息
insert into t_dept(t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time)
values('A0001','xxx','101','a',now());

insert into t_dept(t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time)
values('A0002','ooo','102','b',now());


#创建用户表
 drop table if exists t_user;
create table t_user(
   t_id  int auto_increment primary key,
   t_user_account varchar(20) not null,
   t_user_pwd   varchar(20) not null,
   t_emp_no int not null,
   t_emp_na varchar(10) not null,
   t_role_id int,
   t_create_time date
)engine=Innodb;

insert into t_user (t_id,t_user_account,t_user_pwd,t_emp_no,t_emp_na,t_create_time,t_role_id) values(1000,'admin','admin',1001,'李雷',now(),1);
insert into t_user (t_user_account,t_user_pwd,t_emp_no,t_emp_na,t_create_time,t_role_id) values('admin','admin',1002,'韩梅梅',now(),1);
insert into t_user (t_user_account,t_user_pwd,t_emp_no,t_emp_na,t_create_time,t_role_id) values('admin','admin',1010,'林华',now(),1);

--###########################
drop table if exists t_employee;
#员工信息表
create table t_employee(
   id  int auto_increment primary key,
   t_emp_no int not null,
   t_emp_name  varchar(20),
   t_emp_dept varchar(5) ,
   t_sex varchar(5),
   t_education varchar(20),
   t_email varchar(20),
   t_phone varchar(20),
   t_entry_time varchar(20),
   t_create_time date
)engine=InnoDB;

insert into t_employee (t_emp_no ,
t_emp_name,t_emp_dept,t_sex,t_education,t_email,
t_phone,t_entry_time,t_create_time) 
values('E1003','李雷','总经办','男','研究生','111111111@qq.com','17314971086','2019-12-10',now());
insert into t_employee (t_emp_no ,
t_emp_name,t_emp_dept,t_sex,t_education,t_email,
t_phone,t_entry_time,t_create_time) 
values('E1004','韩梅梅','渠道部','女','本科','222222222@qq.com','17314971087','2019-12-10',now());

--###########################
drop table if exists t_holiday;
#员工请假表
create table t_holiday(
   id  int auto_increment primary key,
   t_holiday_user  varchar(20),
  t_holiday_type varchar(5) ,
   t_holiday_bz varchar(5),
   t_start_time varchar(20),
  t_end_time varchar(20),
   t_holiday_status varchar(20),
   t_create_time varchar(20)
)engine=Innodb;

insert into t_holiday (id ,
t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,
t_holiday_status,t_create_time) 
values(null,'李雷','婚假','结婚','2019-12-10','2019-12-15','已提交',now());
insert into t_holiday (id ,
t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,
t_holiday_status,t_create_time) 
values(null,'韩梅梅','事假','结婚','2019-12-10','2019-12-15','草稿',now());



--###########################
#账户维护表
drop table if exists t_account;
create table t_account(
   id  int auto_increment primary key,
   t_account_number  varchar(20),
  t_name varchar(5) ,
   t_account_status varchar(5),
   t_role varchar(20)
)engine=Innodb;

insert into t_account (id,t_account_number,t_name,t_account_status,t_role) 
values(null,'lisi','李雷','正常','普通用户');

insert into t_account (id,t_account_number,t_name,t_account_status,t_role) 
values(null,'admin','阿萨德','正常','管理员');

insert into t_account (id,t_account_number,t_name,t_account_status,t_role) 
values(null,'heman','韩梅梅','正常','人事专员');


--###########################
#角色表
drop table if exists t_role;
create table t_role(
   id  int auto_increment primary key,
   t_role_name  varchar(20),
   t_create_time varchar(20)
)engine=Innodb;

insert into t_role (id,t_role_name,t_create_time) 
values(null,'管理员',now());
insert into t_role (id,t_role_name,t_create_time) 
values(null,'普通用户',now());
insert into t_role (id,t_role_name,t_create_time) 
values(null,'人事专员',now());


--###########################
#权限表

drop table if exists t_permissions;
create table t_permissions(
    id  int auto_increment primary key,
    t_role_id int,
	t_menu_id int,
	t_create_time varchar(20)
)engine=Innodb;
insert into t_permissions (id,t_role_id,t_menu_id,t_create_time) 
values(null,6,1,now());
insert into t_permissions (id,t_role_id,t_menu_id,t_create_time) 
values(null,6,2,now());
insert into t_permissions (id,t_role_id,t_menu_id,t_create_time) 
values(null,6,3,now());
insert into t_permissions (id,t_role_id,t_menu_id,t_create_time) 
values(null,6,4,now());
insert into t_permissions (id,t_role_id,t_menu_id,t_create_time) 
values(null,2,2,now());
insert into t_permissions (id,t_role_id,t_menu_id,t_create_time) 
values(null,2,6,now());


--###########################
#菜单表

drop table if exists t_menu;
create table t_menu(
    id  int auto_increment primary key,
  	t_menu_nam varchar(20),
	t_create_time varchar(20)
)engine=Innodb;

insert into t_menu (id,t_menu_nam,t_create_time) 
values(null,'部门管理',now());
insert into t_menu (id,t_menu_nam,t_create_time) 
values(null,'员工管理',now());
insert into t_menu (id,t_menu_nam,t_create_time) 
values(null,'请假管理',now());
insert into t_menu (id,t_menu_nam,t_create_time) 
values(null,'报销管理',now());
insert into t_menu (id,t_menu_nam,t_create_time) 
values(null,'账户维护',now());
insert into t_menu (id,t_menu_nam,t_create_time) 
values(null,'角色管理',now());
insert into t_menu (id,t_menu_nam,t_create_time) 
values(null,'权限管理',now());
insert into t_menu (id,t_menu_nam,t_create_time) 
values(null,'密码重置',now());

--###########################
#权限表多表查询
select p.id,p.t_role_id,r.t_role_name,p.t_menu_id,m.t_menu_nam from
 t_role r,t_permissions p,t_menu m 
 where  p.t_role_id = r.id and t_menu_id = m.id;



#创建报销表
drop table if exists t_expense;
create table t_expense(
	t_exp_no int primary key auto_increment, #报销编号
	t_emp_id int,							#员工id 
	t_exp_type varchar(5),								#报销类型 1 差旅费 2 招待费 3 办公费
	t_exp_money double,							#double
	t_exp_status varchar(5),					#状态 0 草稿 1 提交
	t_exp_time date,						#时间
	t_exp_about varchar(50)
	)engine=InnoDB;
#建立外键
	# ALTER TABLE t_expense ADD FOREIGN KEY (t_emp_no) REFERENCES t_employee(id);	
	
#初始化数据
	insert into t_expense(t_exp_no,t_emp_id,t_exp_type,t_exp_money,t_exp_status,t_exp_time,t_exp_about)value(1,1001,1,500.0,0,now(),"阿飞嘎的发送");
	insert into t_expense(t_emp_id,t_exp_type,t_exp_money,t_exp_status,t_exp_time,t_exp_about)value(1002,2,2300.5,0,now(),"2019年12月1日-12月30日，出差上海，车票400，住宿800，餐饮336.5");




