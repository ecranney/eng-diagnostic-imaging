
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

create table appointment
(
  id            bigserial not null
    constraint appointment_pkey
    primary key,
  date          timestamp default date_trunc('minute' :: text, now()),
  patient_id    integer,
  technician_id integer,
  state         text
);

create table appointment_machine
(
  id             bigserial not null
    constraint appointment_machine_pkey
    primary key,
  appointment_id integer,
  machine_id     integer
);

create table machine
(
  id          bigserial not null
    constraint machine_pkey
    primary key,
  serial_code integer,
  type        text
);

create table patient
(
  id          bigserial not null
    constraint patient_pkey
    primary key,
  first_name  text,
  last_name   text,
  address_id  integer,
  phone       text,
  medicare_no text
);

create table radiologist
(
  id     integer not null
    constraint radiologist_pkey
    primary key,
  reg_no integer
);

create table receptionist
(
  id integer not null
    constraint receptionist_pkey
    primary key
);

create table technician
(
  id            bigserial not null
    constraint technician_pkey
    primary key,
  qualification text
);

create table user
(
  id        bigserial not null
    constraint user_pkey
    primary key,
  username  text      not null,
  password  text      not null,
  firstname text,
  lastname  text
);
  
  