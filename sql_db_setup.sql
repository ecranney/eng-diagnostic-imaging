--CREATE ROLE "user" LOGIN
--  ENCRYPTED PASSWORD 'md54d45974e13472b5a0be3533de4666414'
--  SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
--
--Database: dies
--DROP DATABASE dies;
--
--CREATE DATABASE dies
-- WITH OWNER = user
--      ENCODING = 'UTF8'
--      TABLESPACE = pg_default
--      LC_COLLATE = 'English_Australia.1252'
--      LC_CTYPE = 'English_Australia.1252'
--      CONNECTION LIMIT = -1;

--drop table public.user;
--
--CREATE TABLE public.user
--(
--  id BIGSERIAL PRIMARY KEY,
--  username text NOT NULL,
--  password text NOT NULL,
--  firstname text,
--  lastname text
--);
--
--INSERT INTO public.USER(username, password, firstname, lastname)
--VALUES
-- ('admin', 'admin', 'firsname1', 'lastname1');


SELECT
 t1.id,
 t1.firstname,
 t1.lastname
 FROM
 public.user t1
INNER JOIN public.technician t2 ON t1.id = t2.id;













select * from appointment

SELECT username, password, firstname, lastname from public.user where username='admin' 

drop table public.appointment;

CREATE TABLE public.appointment(
 ID	       	BIGSERIAL PRIMARY KEY,
 DATE          	TIMESTAMP,
 PATIENT_ID    	INT,
 TECHNICIAN_ID 	INT,
 APPOINTMENT_MACHINE_ID	INT,
 STATE	       	TEXT 
);

INSERT INTO public.appointment(date, patient_id, technician_id, APPOINTMENT_MACHINE_ID, state)
VALUES
 (current_timestamp, '1', '1', '1', 'MISSED');

 INSERT INTO public.appointment(date, patient_id, technician_id, APPOINTMENT_MACHINE_ID, state)
VALUES
 (current_timestamp, '1', '1', '1', 'FUTURE');

--DROP TABLE public.MACHINE;
--DROP TABLE public.APPOINTMENT_MACHINE;
--

--INSERT INTO public.TECHNICIAN(QUALIFICATION)
--VALUES
--('');
--
--drop table public.TECHNICIAN;
--CREATE TABLE public.TECHNICIAN(
-- ID		BIGSERIAL PRIMARY KEY,
-- QUALIFICATION      TEXT
--);
--
--select * from public.appointment;
--select * from public.user;

SELECT t1.id as ap_id, t1.date as ap_date, t1.state as ap_state,
t1.patient_id as patient_id, t2.FIRST_NAME as patient_first_name, t2.last_name as patient_last_name, 
t6.id as patient_address_id, t6.unit_no as patient_unit_no, t6.street_no as patient_street_no, 
t6.street_name as patient_street_name, t6.city as patient_city, t6.state as patient_state, t6.post_code as patient_post_code, 
t2.phone as patient_mobile, 
t2.medicare_no as patient_medicate_no,
t5join.id as tech_id, t5join.username as tech_username, t5join.password as tech_password,
t5join.firstname as tech_first_name, t5join.lastname as tech_last_name,
t8join.MACHINE_id, t8join.SERIAL_CODE, t8join.TYPE
from public.appointment t1
inner join public.patient t2
on t1.patient_id = t2.id
FULL OUTER join 
(SELECT t4.id, t4.username, t4.password, t4.firstname, t4.lastname
from public.user t4
inner join public.TECHNICIAN t3
on t4.id = t3.id
) t5join on t5join.id = t1.TECHNICIAN_ID
left join public.address t6
on t6.id = t2.address_id
full outer join
(SELECT t7.id, t7.MACHINE_id, t7.APPOINTMENT_ID, t8.SERIAL_CODE, t8.TYPE
from public.APPOINTMENT_MACHINE t7
FULL OUTER join public.MACHINE t8
on t7.MACHINE_id = t8.id)
t8join on t8join.APPOINTMENT_ID = t1.ID
where t1.id = 4


SELECT t1.id as ap_id, t1.date as ap_date, t1.state as ap_state,
t2.FIRST_NAME as patient_first_name, t2.last_name as patient_last_name, 
t2.medicare_no as patient_medicate_no
from public.appointment t1
inner join public.patient t2
on t1.patient_id = t2.id
INNER join 
(SELECT t4.id, t4.firstname, t4.lastname
from public.user t4
inner join public.TECHNICIAN t3
on t4.id = t3.id
) t5join on t5join.id = t1.TECHNICIAN_ID
inner join public.address t6
on t6.id = t2.address_id




--INSERT INTO public.APPOINTMENT_MACHINE(APPOINTMENT_ID, MACHINE_id)
--VALUES
--('1','2');

DROP Table public.machine;
CREATE TABLE public.MACHINE(
 ID	      	BIGSERIAL PRIMARY KEY,
 SERIAL_CODE    INT,
 TYPE     	TEXT
);

INSERT INTO public.MACHINE(SERIAL_CODE, TYPE)
VALUES
 ('122334', 'CAT');

INSERT INTO public.MACHINE(SERIAL_CODE, TYPE)
VALUES
('999999', 'XRAY');

--CREATE TABLE public.APPOINTMENT_MACHINE(
-- ID	       	BIGSERIAL PRIMARY KEY,
-- APPOINTMENT_ID INT,
-- MACHINE_ID 	INT
--);
--
--CREATE TABLE public.MACHINE(
-- ID	      	BIGSERIAL PRIMARY KEY,
-- SERIAL_CODE    TEXT,
-- TYPE     	TEXT
--);
-- 


-- 

--

--
DROP TABLE public.ADDRESS;
CREATE TABLE public.ADDRESS(
 ID	      	BIGSERIAL PRIMARY KEY,
 UNIT_NO      	INT,
 STREET_NO    	INT,
 STREET_NAME  	TEXT,
 CITY	      	TEXT,
 STATE        	TEXT,
 POST_CODE    	INT
);

INSERT INTO public.ADDRESS(UNIT_NO, STREET_NO, STREET_NAME, CITY, STATE, POST_CODE)
VALUES
('0', '92', 'Station St', 'carlton', 'VIC', '3053');

--drop table public.PATIENT;
--CREATE TABLE public.PATIENT(
-- ID	      	BIGSERIAL PRIMARY KEY,
-- FIRST_NAME    	TEXT,
-- LAST_NAME     	TEXT,
-- ADDRESS_ID    	INT,
-- PHONE         	TEXT,
-- MEDICARE_NO   	TEXT 
--);
--
--INSERT INTO public.PATIENT(first_name, last_name, address_id, phone, medicare_no)
--VALUES ('shalitha', 'weerakoon', '1', '123456789', '1');
--
--select * from public.patient;


--CREATE TABLE public.APPOINTMENT_MACHINE(
-- ID	       	BIGSERIAL PRIMARY KEY,
-- APPOINTMENT_ID INT,
-- MACHINE_ID 	INT
--);
--

--

--
--CREATE TABLE public.RADIOLOGIST(
-- ID		INT PRIMARY KEY,
-- REG_NO 	INT
--);
--
--CREATE TABLE public.RECEPTIONIST(
-- ID		INT PRIMARY KEY
--);
--
--

--
--CREATE TABLE public.USERS(
-- ID	      	INT PRIMARY KEY,
-- FIRST_NAME    	TEXT,
-- LAST_NAME     	TEXT
--);
--

