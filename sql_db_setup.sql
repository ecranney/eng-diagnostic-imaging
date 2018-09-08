-- Database: dies

-- DROP DATABASE dies;

--CREATE DATABASE dies
--  WITH OWNER = postgres
--       ENCODING = 'UTF8'
--       TABLESPACE = pg_default
--       LC_COLLATE = 'English_Australia.1252'
--       LC_CTYPE = 'English_Australia.1252'
--       CONNECTION LIMIT = -1;

--CREATE TABLE public.users(
-- username VARCHAR (50) PRIMARY KEY,
-- password VARCHAR (50) NOT NULL
--);

INSERT INTO public.users(username, password)
VALUES
 ('admin', 'admin');
 
