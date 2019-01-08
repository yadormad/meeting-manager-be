create table "user"
(
  id       serial       not null
    constraint user_pkey
    primary key,
  username varchar(100) not null
    constraint username
    unique,
  password varchar(100) not null
);

alter table "user"
  owner to postgres;

create table position
(
  id   serial       not null
    constraint position_pkey
    primary key,
  name varchar(100) not null
);

alter table position
  owner to postgres;

create table meeting_priority
(
  id   serial       not null
    constraint "meeting-priority_pkey"
    primary key,
  name varchar(100) not null
);

alter table meeting_priority
  owner to postgres;

create table person
(
  user_id     integer      not null
    constraint person_pkey
    primary key
    constraint user_id_fk
    references "user",
  fullname    varchar(200) not null,
  position_id integer      not null
    constraint position_id_fk
    references position
);

alter table person
  owner to postgres;

create table meeting
(
  id          serial       not null
    constraint meeting_pkey
    primary key,
  name        varchar(100) not null,
  description varchar(300),
  startdate   timestamp    not null,
  enddate     timestamp    not null,
  priority_id integer      not null
    constraint priority_id_fk
    references meeting_priority
);

alter table meeting
  owner to postgres;

create index fki_priority_id_fk
  on meeting (priority_id);

create table meeting_person_relation
(
  person_id  integer not null
    constraint person_id_fk
    references person,
  meeting_id integer not null
    constraint meeting_id_fk
    references meeting,
  constraint meeting_person_pk
  primary key (person_id, meeting_id)
);

alter table meeting_person_relation
  owner to postgres;

create index fki_meeting_id_fk
  on meeting_person_relation (meeting_id);

create index fki_person_id_fk
  on meeting_person_relation (person_id);

insert into meeting_priority (name)
values ('immediate');

insert into meeting_priority (name)
values ('planned');

insert into meeting_priority (name)
values ('whenever possible');

insert into position (name) values ('manager');
insert into position (name) values ('trainee');
insert into position (name) values ('software engineer');
insert into position (name) values ('qa engineer');

insert into meeting (name, description, startdate, enddate, priority_id) values ('syncup', 'blablablablablabla', '2019-01-09 09:00:00', '2019-01-09 10:00:00', 1);
insert into meeting (name, description, startdate, enddate, priority_id) values ('another syncup', 'blablablablablabla', '2019-01-09 11:00:00', '2019-01-09 12:00:00', 1);
insert into meeting (name, description, startdate, enddate, priority_id) values ('another one', 'blablablablablabla', '2019-01-09 11:00:00', '2019-01-09 12:00:00', 2);
insert into meeting (name, description, startdate, enddate, priority_id) values ('another two', 'blablablablablabla', '2019-01-09 11:00:00', '2019-01-09 12:00:00', 2);
insert into meeting (name, description, startdate, enddate, priority_id) values ('another three', 'blablablablablabla', '2019-01-09 11:00:00', '2019-01-09 12:00:00', 1);
insert into meeting (name, description, startdate, enddate, priority_id) values ('another 4', 'uuuuuuuuuuuuuuu', '2019-01-09 11:00:00', '2019-01-09 12:00:00', 1);


