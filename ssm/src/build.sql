create table card(
	id int primary key auto_increment,
	username varchar(50),
	pro int,
	tele varchar(20),
	iphone varchar(20),
	email varchar(30),
	addr varchar(500),
	img varchar(100)
)engine=Innodb default charset=utf8;