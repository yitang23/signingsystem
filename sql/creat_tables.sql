use attendance;
create table IF NOT EXISTS student(
	sno int primary key,
    sname varchar(20) NOT NULL,
    sphone varchar(20) NOT NULL,
    spassword varchar(20) NOT NULL,
    sphoto blob 
);
insert into student values(190201001,'李勇','15426738945','123',null);
insert into student values(190201002,'王敏','16780389405','abc',null);

create table IF NOT EXISTS teacher(
	tid int primary key,
    tname varchar(20) NOT NULL,
    tphone varchar(20) NOT NULL,
    tpassword varchar(20) NOT NULL
);
insert into teacher values(180201,'张立','15000889945','123');
insert into teacher values(180202,'张三','15222889945','zhangsan');

create table IF NOT EXISTS  courses(
	cid int not null auto_increment,
    cname varchar(30) not null,
     tid int ,
	foreign key(tid) references teacher(tid),
    cstarttime datetime,
    cendtime datetime,
    primary key(cid)
);
insert into courses values(1001,'机器学习',180201,'2020-01-01 09:00:00','2020-01-01 11:00:00');
insert into courses values(1002,'算法分析与设计',180201,'2020-01-04 13:00:00','2020-01-01 15:00:00');

create table IF NOT EXISTS  set_qd(
    cid int ,
    foreign key(cid) references courses(cid),
    starttime datetime,
    continue_time int not null, 
    lng decimal(10,6),
    lat decimal(10,6)
);
insert into set_qd values('1001','2020-01-01 08:50:00',60,0,0);
insert into set_qd values('1002','2020-01-01 12:50:00',60,0,0);

create table IF NOT EXISTS qdrecord(
	qid int not null auto_increment,
    sno int,
    foreign key(sno) references student(sno),
    cid int,
    foreign key(cid) references courses(cid),
    qtime datetime,
    lng decimal(10,6),
    lat decimal(10,6),
    qteachermsg boolean,
	primary key(qid)
);
insert into qdrecord values(1,190201001,1001,'2020-01-01 08:56:00',0,0,false);
insert into qdrecord values(2,190201002,1001,'2020-01-01 08:53:00',0,0,false);

create table IF NOT EXISTS manage_student_course(
	sno int,
    foreign key(sno) references student(sno),
    cid int,
	foreign key(cid) references courses(cid),
    tid int,
	foreign key(tid) references teacher(tid),
	status int not null 
);
insert into manage_student_course values(190201001,1001,180201,0);
insert into manage_student_course values(190201002,1001,180201,1);