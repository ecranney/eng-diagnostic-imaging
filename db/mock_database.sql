INSERT INTO public.patient (id, first_name, last_name, address_id, phone, medicare_no) VALUES (4, 'Rajitha', 'Karunatilleke', 27, '0478134368', 'MEDI004');
INSERT INTO public.patient (id, first_name, last_name, address_id, phone, medicare_no) VALUES (6, 'Denzel', 'Washington', 29, '0478134368', 'MEDI006');
INSERT INTO public.patient (id, first_name, last_name, address_id, phone, medicare_no) VALUES (5, 'Shalitha Weerakoon', 'Karunatilleke', 28, '0478134368', 'MEDI005');
INSERT INTO public.patient (id, first_name, last_name, address_id, phone, medicare_no) VALUES (1, 'Robert De', 'Nero', 1, '0478134368', 'MEDI001');
INSERT INTO public.patient (id, first_name, last_name, address_id, phone, medicare_no) VALUES (3, 'Evan', 'Cranney', 26, '0478134368', 'MEDI003');
INSERT INTO public.patient (id, first_name, last_name, address_id, phone, medicare_no) VALUES (7, 'Shalitha Weerakoon', 'Karunatilleke', 30, '0478134368', 'MEDI007');
INSERT INTO public.patient (id, first_name, last_name, address_id, phone, medicare_no) VALUES (8, 'Evan', 'Cranney', 32, '0478134368', 'MEDI008');
INSERT INTO public.patient (id, first_name, last_name, address_id, phone, medicare_no) VALUES (2, 'Tom', 'Hanks', 25, '0478134368', 'MEDI002');

INSERT INTO public.machine (id, serial_code, type) VALUES (2, 1, 'XRAY');
INSERT INTO public.machine (id, serial_code, type) VALUES (1, 2, 'CAT');
INSERT INTO public.machine (id, serial_code, type) VALUES (3, 3, 'MRI');

INSERT INTO public.appointment_machine (id, appointment_id, machine_id) VALUES (1, 1, 1);
INSERT INTO public.appointment_machine (id, appointment_id, machine_id) VALUES (8, 8, 2);
INSERT INTO public.appointment_machine (id, appointment_id, machine_id) VALUES (3, 3, 3);
INSERT INTO public.appointment_machine (id, appointment_id, machine_id) VALUES (6, 6, 3);
INSERT INTO public.appointment_machine (id, appointment_id, machine_id) VALUES (2, 2, 2);
INSERT INTO public.appointment_machine (id, appointment_id, machine_id) VALUES (5, 5, 2);
INSERT INTO public.appointment_machine (id, appointment_id, machine_id) VALUES (4, 4, 1);
INSERT INTO public.appointment_machine (id, appointment_id, machine_id) VALUES (7, 7, 1);

INSERT INTO public."user" (id, username, password, firstname, lastname) VALUES (1, 'admin', 'admin', 'Evan', 'Cranney');
INSERT INTO public."user" (id, username, password, firstname, lastname) VALUES (3, 'tom', 'tom', 'Tom', 'Hanks');
INSERT INTO public."user" (id, username, password, firstname, lastname) VALUES (2, 'user', 'password', 'Robert', 'Nero');

INSERT INTO public.technician (id, qualification) VALUES (1, 'Senior');
INSERT INTO public.technician (id, qualification) VALUES (2, 'Junior');

INSERT INTO public.address (id, unit_no, street_no, street_name, city, state, post_code) VALUES (8, 333, 92, '333 Ballarat Road', 'Braybrook', 'Victoria', 3019);
INSERT INTO public.address (id, unit_no, street_no, street_name, city, state, post_code) VALUES (1, 2, 92, '338 Boylston Street', 'Boston', 'Massachusetts', 2116);
INSERT INTO public.address (id, unit_no, street_no, street_name, city, state, post_code) VALUES (7, 33, 92, '353 Greenwich Street', 'New York', 'New York', 10013);
INSERT INTO public.address (id, unit_no, street_no, street_name, city, state, post_code) VALUES (6, 23, 92, '2095 3rd Avenue', 'New York', 'New York', 10029);
INSERT INTO public.address (id, unit_no, street_no, street_name, city, state, post_code) VALUES (5, 23, 92, '2226 Massachusetts Avenue', 'Cambridge', 'Massachusetts', 2140);
INSERT INTO public.address (id, unit_no, street_no, street_name, city, state, post_code) VALUES (3, 44, 92, '2095 3rd Avenue', 'New York', 'New York', 10029);
INSERT INTO public.address (id, unit_no, street_no, street_name, city, state, post_code) VALUES (2, 33, 92, '353 Greenwich Street', 'New York', 'New York', 10013);


INSERT INTO public.appointment (id, date, patient_id, technician_id, state) VALUES (3, '2018-09-09 21:55:00.000000', 6, 1, 'FUTURE');
INSERT INTO public.appointment (id, date, patient_id, technician_id, state) VALUES (2, '2018-09-09 19:24:00.000000', 5, 1, 'MISSED');
INSERT INTO public.appointment (id, date, patient_id, technician_id, state) VALUES (4, '2018-09-12 08:35:00.000000', 8, 3, 'FUTURE');
INSERT INTO public.appointment (id, date, patient_id, technician_id, state) VALUES (1, '2018-09-11 20:34:00.000000', 1, 2, 'FUTURE');
INSERT INTO public.appointment (id, date, patient_id, technician_id, state) VALUES (8, '2018-08-28 19:25:00.000000', 2, 2, 'FUTURE');
INSERT INTO public.appointment (id, date, patient_id, technician_id, state) VALUES (7, '2018-09-12 08:41:00.000000', 3, 2, 'FUTURE');
INSERT INTO public.appointment (id, date, patient_id, technician_id, state) VALUES (6, '2018-09-09 21:56:00.000000', 7, 1, 'MISSED');
INSERT INTO public.appointment (id, date, patient_id, technician_id, state) VALUES (5, '2018-09-12 08:40:00.000000', 4, 2, 'FUTURE');

