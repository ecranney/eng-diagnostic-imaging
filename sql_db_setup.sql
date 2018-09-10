--create role "user" login
--  encrypted password 'md54d45974e13472b5a0be3533de4666414'
--  superuser inherit createdb createrole replication;
--
--database: dies
--drop database dies;
--
--create database dies
-- with owner = user
--      encoding = 'utf8'
--      tablespace = pg_default
--      lc_collate = 'english_australia.1252'
--      lc_ctype = 'english_australia.1252'
--      connection limit = -1;


drop table public.machine;
drop table public.appointment_machine;

drop table public.user;
create table public.user
(
  id bigserial primary key,
  username text not null,
  password text not null,
  firstname text,
  lastname text
);

insert into public.user(username, password, firstname, lastname)
values
('admin', 'admin', 'evan', 'cranney'),
('rob', 'rob', 'robert', 'de nero'),
('tom', 'tom', 'tom', 'hanks');



insert into public.technician(qualification)
values
('');

drop table public.technician;
create table public.technician(
id		bigserial primary key,
 qualification      text
);

select * from public.appointment;
select * from public.user;




select
 t1.id,
 t1.firstname,
 t1.lastname
 from
 public.user t1
inner join public.technician t2 on t1.id = t2.id;



select * from appointment

select username, password, firstname, lastname from public.user where username='admin' 

drop table public.appointment;

create table public.appointment(
 id	       	bigserial primary key,
 date          	timestamp,
 patient_id    	int,
 technician_id 	int,
 appointment_machine_id	int,
 state	       	text 
);

insert into public.appointment(date, patient_id, technician_id, appointment_machine_id, state)
values
 (current_timestamp, '1', '1', '1', 'missed');

 insert into public.appointment(date, patient_id, technician_id, appointment_machine_id, state)
values
 (current_timestamp, '1', '1', '1', 'future');


select t1.id as ap_id, t1.date as ap_date, t1.state as ap_state,
t1.patient_id as patient_id, t2.first_name as patient_first_name, t2.last_name as patient_last_name, 
t6.id as patient_address_id, t6.unit_no as patient_unit_no, t6.street_no as patient_street_no, 
t6.street_name as patient_street_name, t6.city as patient_city, t6.state as patient_state, t6.post_code as patient_post_code, 
t2.phone as patient_mobile, 
t2.medicare_no as patient_medicate_no,
t5join.id as tech_id, t5join.username as tech_username, t5join.password as tech_password,
t5join.firstname as tech_first_name, t5join.lastname as tech_last_name,
t8join.machine_id, t8join.serial_code, t8join.type
from public.appointment t1
inner join public.patient t2
on t1.patient_id = t2.id
full outer join 
(select t4.id, t4.username, t4.password, t4.firstname, t4.lastname
from public.user t4
inner join public.technician t3
on t4.id = t3.id
) t5join on t5join.id = t1.technician_id
left join public.address t6
on t6.id = t2.address_id
full outer join
(select t7.id, t7.machine_id, t7.appointment_id, t8.serial_code, t8.type
from public.appointment_machine t7
full outer join public.machine t8
on t7.machine_id = t8.id)
t8join on t8join.appointment_id = t1.id
where t1.id = 4


select t1.id as ap_id, t1.date as ap_date, t1.state as ap_state,
t2.first_name as patient_first_name, t2.last_name as patient_last_name, 
t2.medicare_no as patient_medicate_no
from public.appointment t1
inner join public.patient t2
on t1.patient_id = t2.id
inner join 
(select t4.id, t4.firstname, t4.lastname
from public.user t4
inner join public.technician t3
on t4.id = t3.id
) t5join on t5join.id = t1.technician_id
inner join public.address t6
on t6.id = t2.address_id


new query for appointments even for bunull technicans 

"SELECT t1.id as ap_id, t1.date as ap_date, t1.state as ap_state,\r\n"
			+ "t2.FIRST_NAME as patient_first_name, t2.last_name as patient_last_name, \r\n"
			+ "t2.medicare_no as patient_medicate_no\r\n" + "from public.appointment t1\r\n"
			+ "inner join public.patient t2\r\n" + "on t1.patient_id = t2.id\r\n" + "INNER join \r\n"
			+ "(SELECT t4.id, t4.firstname, t4.lastname\r\n" + "from public.user t4\r\n"
			+ "inner join public.TECHNICIAN t3\r\n" + "on t4.id = t3.id\r\n"
			+ ") t5join on t5join.id = t1.TECHNICIAN_ID\r\n" + "inner join public.address t6\r\n"
			+ "on t6.id = t2.address_id";
			
select t1.id as ap_id, t1.date as ap_date, t1.state as ap_state,
t2.first_name as patient_first_name, t2.last_name as patient_last_name, 
t2.medicare_no as patient_medicate_no,
t5join.id, t5join.firstname, t5join.lastname
from public.appointment t1
left outer join
(
select t4.id, t4.firstname, t4.lastname
from public.user t4
inner join public.technician t3
on t4.id = t3.id
) t5join on t5join.id = t1.technician_id
inner join public.patient t2
on t1.patient_id = t2.id






--insert into public.appointment_machine(appointment_id, machine_id)
--values
--('1','2');

drop table public.machine;
create table public.machine(
 id	      	bigserial primary key,
 serial_code    int,
 type     	text
);

insert into public.machine(serial_code, type)
values
 ('122334', 'cat');

insert into public.machine(serial_code, type)
values
('999999', 'xray');

--create table public.appointment_machine(
-- id	       	bigserial primary key,
-- appointment_id int,
-- machine_id 	int
--);
--
--create table public.machine(
-- id	      	bigserial primary key,
-- serial_code    text,
-- type     	text
--);
-- 


-- 

--

--
drop table public.address;
create table public.address(
 id	      	bigserial primary key,
 unit_no      	int,
 street_no    	int,
 street_name  	text,
 city	      	text,
 state        	text,
 post_code    	int
);

insert into public.address(unit_no, street_no, street_name, city, state, post_code)
values
('0', '92', 'station st', 'carlton', 'vic', '3053');

--drop table public.patient;
--create table public.patient(
-- id	      	bigserial primary key,
-- first_name    	text,
-- last_name     	text,
-- address_id    	int,
-- phone         	text,
-- medicare_no   	text 
--);
--
--insert into public.patient(first_name, last_name, address_id, phone, medicare_no)
--values ('shalitha', 'weerakoon', '1', '123456789', '1');
--
--select * from public.patient;


--create table public.appointment_machine(
-- id	       	bigserial primary key,
-- appointment_id int,
-- machine_id 	int
--);
--

--

--
--create table public.radiologist(
-- id		int primary key,
-- reg_no 	int
--);
--
--create table public.receptionist(
-- id		int primary key
--);
--
--

--
--create table public.users(
-- id	      	int primary key,
-- first_name    	text,
-- last_name     	text
--);
--

----------------------------------

select 
t1.id as ap_id, 
t1.date as ap_date, 
t1.state as ap_state,

t1.patient_id as patient_id, 
t2t6.first_name as patient_first_name, 
t2t6.last_name as patient_last_name, 
t2t6.medicare_no as patient_medicate_no,
t2t6.phone as patient_mobile_no,

t2t6.patient_address_id as patient_address_id, 
t2t6.unit_no as patient_unit_no, 
t2t6.street_no as patient_street_no, 
t2t6.street_name as patient_street_name, 
t2t6.city as patient_city, 
t2t6.state as patient_state, 
t2t6.post_code as patient_post_code,

t3t4.id as technician_id, 
t3t4.username as technician_username, 
t3t4.firstname as technician_first_name, 
t3t4.lastname as technician_last_name,

t7t8.id as appointment_machine_id,
t7t8.serial_code as machine_serial_code,
t7t8.type as machine_type

from public.appointment t1
left outer join
(
select t4.id, t4.username, t4.firstname, t4.lastname
from public.user t4
inner join public.technician t3
on t4.id = t3.id
) t3t4 on t3t4.id = t1.technician_id

inner join 
(select 
t2.id , 
t2.first_name, 
t2.last_name, 
t2.medicare_no,
t2.phone,

t6.id as patient_address_id, 
t6.unit_no, 
t6.street_no, 
t6.street_name, 
t6.city, 
t6.state, 
t6.post_code

from public.patient t2 
inner join public.address t6
on t2.address_id=t6.id
) t2t6 on t2t6.id = patient_id
left outer join
(
select t7.id, t7.serial_code, t7.type,t8.appointment_id

from public.machine t7
right outer join 
public.appointment_machine t8
on t8.machine_id = t7.id
) t7t8 on t7t8.appointment_id = t1.id

where t1.id=1

update appointment_machine where machine

UPDATE 
SET type = "CAT"
WHERE
 last_update IS NULL;

select * from public.appointment_machine

INSERT INTO public.appointment_machine(id,appointment_id,machine_id)
values ('7','5','1'), ('8','6','1'),('9','4','1');




--------------------


