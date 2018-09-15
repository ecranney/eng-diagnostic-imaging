
select t1.id                   as ap_id,
       t1.date                 as ap_date,
       t1.state                as ap_state,
       t1.patient_id           as patient_id,
       t2t6.first_name         as patient_first_name,
       t2t6.last_name          as patient_last_name,
       t2t6.medicare_no        as patient_medicate_no,
       t2t6.phone              as patient_mobile_no,
       t2t6.patient_address_id as patient_address_id,
       t2t6.unit_no            as patient_unit_no,
       t2t6.street_no          as patient_street_no,
       t2t6.street_name        as patient_street_name,
       t2t6.city               as patient_city,
       t2t6.state              as patient_state,
       t2t6.post_code          as patient_post_code,
       t3t4.id                 as technician_id,
       t3t4.username           as technician_username,
       t3t4.firstname          as technician_first_name,
       t3t4.lastname           as technician_last_name,
       t7t8.id                 as appointment_machine_id,
       t7t8.serial_code        as machine_serial_code,
       t7t8.type               as machine_type
from public.appointment t1
       left outer join (select t4.id, t4.username, t4.firstname, t4.lastname
                        from public.user t4
                               inner join public.technician t3 on t4.id = t3.id) t3t4 on t3t4.id = t1.technician_id
       inner join (select t2.id,
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
                          left outer join public.address t6 on t2.address_id = t6.id) t2t6 on t2t6.id = patient_id
       left outer join (select t7.id, t7.serial_code, t7.type, t8.appointment_id
                        from public.machine t7
                               right outer join public.appointment_machine t8 on t8.machine_id = t7.id) t7t8
         on t7t8.appointment_id = t1.id limit 4 offset 8;

select count(*) from public.appointment;




