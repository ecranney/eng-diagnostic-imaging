create table address
(
  id          bigserial not null
    constraint address_pkey
    primary key,
  unit_no     integer,
  street_no   integer,
  street_name text,
  city        text,
  state       text,
  post_code   integer
);

alter table address
  owner to postgres;

create table appointment
(
  id            bigserial not null
    constraint appointment_pkey
    primary key,
  date          timestamp default date_trunc('minute' :: text, now()),
  patient_id    integer,
  technician_id integer,
  state         text,
  report        text
);

alter table appointment
  owner to postgres;

create table appointment_machine
(
  id             bigserial not null
    constraint appointment_machine_pkey
    primary key,
  appointment_id integer,
  machine_id     integer
);

alter table appointment_machine
  owner to postgres;

create table "group"
(
  id          bigserial not null,
  name        text,
  description text
);

alter table "group"
  owner to "user";



select t1.username, t1.password, t1.firstname as first_name, t1.lastname as last_name, t2.name as group

from public.user t1
       
inner join public.group t2 on t1.group_id = t2.id
where t1.username= 'admin'

create table group_role
(
  group_id integer not null,
  role_id  integer not null,
  constraint group_role_pk
  primary key (group_id, role_id)
);

alter table group_role
  owner to "user";

create table machine
(
  id          bigserial not null
    constraint machine_type_pkey
    primary key,
  type        text,
  serial_code text
);

alter table machine
  owner to "user";

create table patient
(
  id          bigserial not null
    constraint patient_pkey
    primary key,
  first_name  text,
  last_name   text,
  address_id  integer,
  phone       text,
  medicare_no text,
  email       text
);

alter table patient
  owner to postgres;

create table radiologist
(
  id          integer not null
    constraint radiologist_pkey
    primary key,
  reg_no      integer,
  description text
);

alter table radiologist
  owner to postgres;

create table receptionist
(
  id integer not null
    constraint receptionist_pkey
    primary key
);

alter table receptionist
  owner to postgres;

create table role
(
  id          bigserial not null
    constraint role_pkey
    primary key,
  name        text,
  description text
);

alter table role
  owner to "user";

create table technician
(
  id            bigserial not null
    constraint technician_pkey
    primary key,
  qualification text
);

alter table technician
  owner to postgres;

create table "user"
(
  id        bigserial not null
    constraint user_pkey
    primary key,
  username  text      not null,
  password  text      not null,
  firstname text,
  lastname  text,
  group_id  integer,
  hash      text
);

alter table "user"
  owner to postgres;

