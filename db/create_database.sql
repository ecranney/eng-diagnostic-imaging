create table public.user
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

create table public.technician
(
  id            bigserial not null
    constraint technician_pkey
    primary key,
  qualification text
);

create table public.role
(
  id          bigserial not null
    constraint role_pkey
    primary key,
  name        text,
  description text
);

create table public.report
(
  id           bigserial not null
    constraint report_pkey
    primary key,
  author_id    integer,
  reviewer_id  integer,
  content      text,
  date_created timestamp,
  date_updated timestamp,
  state        text
);

create table public.receptionist
(
  id integer not null
    constraint receptionist_pkey
    primary key
);

create table public.radiologist
(
  id          integer not null
    constraint radiologist_pkey
    primary key,
  reg_no      integer,
  description text
);

create table public.patient
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

create table public.machine
(
  id          bigserial not null
    constraint machine_type_pkey
    primary key,
  type        text,
  serial_code text
);

create table group_role
(
  group_id integer not null,
  role_id  integer not null,
  constraint group_role_pk
  primary key (group_id, role_id)
);


create table public.group
(
  id          bigserial not null,
  name        text,
  description text
);

create table public.appointment_machine
(
  id             bigserial not null
    constraint appointment_machine_pkey
    primary key,
  appointment_id integer,
  machine_id     integer,
  image_url      text
);

create table public.appointment
(
  id            bigserial not null
    constraint appointment_pkey
    primary key,
  date          timestamp default date_trunc('minute' :: text, now()),
  patient_id    integer,
  technician_id integer,
  state         text,
  report_id     integer
);

create table public.address
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


