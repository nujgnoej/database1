show tables;

desc aaa;

drop table aaa;

create table aaa (
  name   varchar(20) not null,
  age    int default 20,
  gender char(1) default 'm',
  joinday datetime default now()
);

insert into aaa values ('홍길동',25,default,default);
insert into aaa values ('홍길순',29,'f',default);
insert into aaa values ('김말숙',35,'f','2020-1-5');

select * from aaa;
