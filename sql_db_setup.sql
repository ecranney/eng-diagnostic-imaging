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

drop table public.user;

CREATE TABLE public.user
(
  id BIGSERIAL PRIMARY KEY,
  username text NOT NULL,
  password text NOT NULL,
  firstname text,
  lastname text
);

INSERT INTO public.USER(username, password, firstname, lastname)
VALUES
 ('admin', 'admin', 'firsname1', 'lastname1');

 
--CREATE TABLE public.ADDRESS(
-- ID	      	INT PRIMARY KEY,
-- UNIT_NO      	INT,
-- STREET_NO    	INT,
-- STREET_NAME  	TEXT,
-- CITY	      	TEXT,
-- STATE        	TEXT,
-- POST_CODE    	INT
--);
--
--CREATE TABLE public.APPOITNMENT(
-- ID	       	INT PRIMARY KEY,
-- DATE          	TEXT,
-- PATIENT_ID    	INT,
-- TECHNICIAN_ID 	INT,
-- STATE	       	TEXT 
--);
--
--CREATE TABLE public.PATIENT(
-- ID	      	INT PRIMARY KEY,
-- FIRST_NAME    	TEXT,
-- LAST_NAME     	TEXT,
-- ADDRESS_ID    	INT,
-- PHONE         	TEXT,
-- MEDICARE_NO   	TEXT 
--);
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
--CREATE TABLE public.TECHNICIAN(
-- ID		INT PRIMARY KEY
--);
--
--CREATE TABLE public.USERS(
-- ID	      	INT PRIMARY KEY,
-- FIRST_NAME    	TEXT,
-- LAST_NAME     	TEXT
--);
--
--CREATE TABLE public.MACHINE(
-- ID	      	INT PRIMARY KEY,
-- SERIAL_CODE    TEXT,
-- TYPE     	TEXT
--);