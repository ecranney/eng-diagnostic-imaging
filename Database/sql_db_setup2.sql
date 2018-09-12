
CREATE TABLE public.address (
    id bigint NOT NULL,
    unit_no integer,
    street_no integer,
    street_name text,
    city text,
    state text,
    post_code integer
);

CREATE TABLE public.appointment (
    id bigint NOT NULL,
    date timestamp without time zone,
    patient_id integer,
    technician_id integer,
    state text
);

alter table public.appointment
alter column date 
set default date_trunc('minute', now());


CREATE TABLE public.appointment_machine (
    id bigint NOT NULL,
    appointment_id integer,
    machine_id integer
);



CREATE TABLE public.machine (
    id bigint NOT NULL,
    serial_code integer,
    type text
);


CREATE TABLE public.patient (
    id bigint NOT NULL,
    first_name text,
    last_name text,
    address_id integer,
    phone text,
    medicare_no text
);



CREATE TABLE public.radiologist (
    id integer NOT NULL,
    reg_no integer
);



CREATE TABLE public.receptionist (
    id integer NOT NULL
);

CREATE TABLE public.technician (
    id bigint NOT NULL,
    qualification text
);



CREATE TABLE public."user" (
    id bigint NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    firstname text,
    lastname text
);


CREATE TABLE public.appointment (
    id bigint NOT NULL,
    date timestamp without time zone,
    patient_id integer,
    technician_id integer,
    state text
);

insert into public.appointment (id, date, patient_id, technician_id, state) 
values
('1',	'2018-09-09 19:24:36.3159',	'1',	'1',	'FUTURE'),
('2',	'2018-09-09 19:24:36.3159',	'1',	'1',	'MISSED'),
('4',	'2018-09-09 21:56:24.661651',	'1',	'1',	'MISSED'),
('3',	'2018-09-09 21:55:02.671553',	'3',	'1',	'FUTURE');

insert into  public.appointment_machine (id, appointment_id, machine_id) values
(1,	1,	1),
(2,	1,	2),
(3,	1,	3),
(4,	2,	1),
(5,	2,	3),
(6,	3,	3);

insert into public.machine (id, serial_code, type) values
(2,	1,	'XRAY'),
(1,	2,	'CAT'),
(3,	3,	'MRI');



insert into public.patient (id, first_name, last_name, address_id, phone, medicare_no) values
(1,	'Shalitha', 'Weearakoon',	1,	04791242428,	'MEDI0943'),
(2,	'Rajitha', 'Karunatilleke',	2,	0471311752,	'MEDI0921'),
(3,	'Harun',	'Lanthra',	3,	0456333234,	'INTL0211');


insert into public.technician (id, qualification) values
(1,	'Senior'),
(2,	'Junior');



insert into public."user" (id, username, password, firstname, lastname) values 
(1,	'admin',	'admin',	'Evan',	'Cranney'),
(2,	'rob',	'rob',	'Robert De',	'Nero'),
(3,	'tom',	'tom',	'Tom',	'Hanks');


