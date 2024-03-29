package dies.mappers;

import db.DBConnection;
import dies.models.Appointment;
import dies.models.Appointment.State;
import dies.models.IDomainObject;
import dies.models.Image;
import dies.models.Machine;
import dies.models.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentMapper extends DataMapper {


    private ResultSetMap rsm = new ResultSetMap();

    private DBConnection db = new DBConnection();
    private String findAllAppointmentSQL = "" + 
    		"select t1.id                                as appointment_id,\r\n" + 
    		"       to_char(t1.date, 'YYYY-MM-DD HH:MI') as appointment_date,\r\n" + 
    		"       t1.state                             as appointment_state,\r\n" + 
    		"       t1.patient_id                        as patient_id,\r\n" + 
    		"       t2t6.first_name                      as patient_first_name,\r\n" + 
    		"       t2t6.last_name                       as patient_last_name,\r\n" + 
    		"       t2t6.medicare_no                     as patient_medicare_no,\r\n" + 
    		"       t2t6.phone                           as patient_phone,\r\n" + 
    		"       t2t6.email                           as patient_email,\r\n" + 
    		"       t2t6.patient_address_id              as patient_address_id,\r\n" + 
    		"       t2t6.unit_no                         as patient_unit_no,\r\n" + 
    		"       t2t6.street_no                       as patient_street_no,\r\n" + 
    		"       t2t6.street_name                     as patient_street_name,\r\n" + 
    		"       t2t6.city                            as patient_city,\r\n" + 
    		"       t2t6.state                           as patient_state,\r\n" + 
    		"       t2t6.post_code                       as patient_post_code,\r\n" + 
    		"       t3t4.id                              as technician_id,\r\n" + 
    		"       t3t4.username                        as technician_username,\r\n" + 
    		"       t3t4.firstname                       as technician_first_name,\r\n" + 
    		"       t3t4.lastname                        as technician_last_name,\r\n" + 
    		"       t3t4.group_id                        as technician_group,\r\n" + 
    		"       t7t8.id                              as appointment_machine_id,\r\n" + 
    		"       t7t8.serial_code                     as machine_serial_code,\r\n" + 
    		"       t7t8.type                            as machine_type,\r\n" + 
    		"       t7t8.image_url                       as machine_image_url,\r\n" + 
    		"       t1.report_id                         as report_id,\r\n" + 
    		"       t9t10t11.report_author_id            as report_author_id,\r\n" + 
    		"       t9t10t11.report_author_firstname     as report_author_firstname,\r\n" + 
    		"       t9t10t11.report_author_lastname      as report_author_lastname,\r\n" + 
    		"       t9t10t11.report_reviewer_id          as report_reviewer_id,\r\n" + 
    		"       t9t10t11.report_reviewer_firstname   as report_reviewer_firstname,\r\n" + 
    		"       t9t10t11.report_reviewer_lastname    as report_reviewer_lastname,\r\n" + 
    		"       t9t10t11.report_content              as report_content,\r\n" + 
    		"       t9t10t11.report_date_created         as report_date_created,\r\n" + 
    		"       t9t10t11.report_date_updated         as report_date_updated,\r\n" + 
    		"       t9t10t11.report_state                as report_state\r\n" + 
    		"from KEYWORDTOBEREPLACE t1\r\n" + 
    		"       left outer join (select t4.id, t4.username, t4.firstname, t4.lastname, t4.group_id\r\n" + 
    		"                        from public.user t4\r\n" + 
    		"                               inner join public.technician t3 on t4.id = t3.id) t3t4 on t3t4.id = t1.technician_id\r\n" + 
    		"       left outer join (select t9.id             as report_id,\r\n" + 
    		"                               t9.author_id      as report_author_id,\r\n" + 
    		"                               t10.firstname     as report_author_firstname,\r\n" + 
    		"                               t10.lastname      as report_author_lastname,\r\n" + 
    		"                               t9.reviewer_id    as report_reviewer_id,\r\n" + 
    		"                               t11.firstname     as report_reviewer_firstname,\r\n" + 
    		"                               t11.lastname      as report_reviewer_lastname,\r\n" + 
    		"                               t9.content        as report_content,\r\n" + 
    		"                               t9.date_created   as report_date_created,\r\n" + 
    		"                               t9.date_updated   as report_date_updated,\r\n" + 
    		"                               t9.state          as report_state\r\n" + 
    		"                        from public.report t9\r\n" + 
    		"                               left outer join public.user t10 on t9.author_id = t10.id\r\n" + 
    		"                               left outer join public.user t11 on t9.reviewer_id = t11.id) t9t10t11\r\n" + 
    		"         on t9t10t11.report_id = t1.report_id\r\n" + 
    		"       inner join (select t2.id,\r\n" + 
    		"                          t2.first_name,\r\n" + 
    		"                          t2.last_name,\r\n" + 
    		"                          t2.medicare_no,\r\n" + 
    		"                          t2.phone,\r\n" + 
    		"                          t2.email,\r\n" + 
    		"                          t6.id as patient_address_id,\r\n" + 
    		"                          t6.unit_no,\r\n" + 
    		"                          t6.street_no,\r\n" + 
    		"                          t6.street_name,\r\n" + 
    		"                          t6.city,\r\n" + 
    		"                          t6.state,\r\n" + 
    		"                          t6.post_code\r\n" + 
    		"                   from public.patient t2\r\n" + 
    		"                          left outer join public.address t6 on t2.address_id = t6.id) t2t6 on t2t6.id = patient_id\r\n" + 
    		"       left outer join (select t7.id, t7.serial_code, t7.type, t8.appointment_id, t8.image_url\r\n" + 
    		"                        from public.machine t7\r\n" + 
    		"                               right outer join public.appointment_machine t8 on t8.machine_id = t7.id) t7t8\r\n" + 
    		"         on t7t8.appointment_id = t1.id";

    private String findAppointmentSQL = findAllAppointmentSQL.replace("KEYWORDTOBEREPLACE","public.appointment") + " where t1.id = ?";
    private String findAllAppointmentWithLimitSQL = 
    		"with limited_parents as (select * from public.appointment limit ? offset ?) " + 
    		findAllAppointmentSQL.replace("KEYWORDTOBEREPLACE","limited_parents") + 
    		" order by t1.date";
    private String findAllAppointmentWithLimitAndWhereSQL = 
    		"with limited_parents as (select * from public.appointment where (state = ? or state = ?) limit ? offset ?) " + 
    		findAllAppointmentSQL.replace("KEYWORDTOBEREPLACE","limited_parents") + 
    		" order by t1.date";
    private String countSQL = "select count(*) from public.appointment";
    private String countWhereSQL = "select count(*) from public.appointment where (state = ? or state = ?)";
    private String insertReportSQL = " insert into pulic.report (state) values (" + Report.State.INCOMPLETE + ") returning id ";
    private String insertAppointmentSQL = ""
            + "insert into public.appointment (date, patient_id, technician_id, state, report_id) "
            + "values (?, ?, ?, ?, ?) returning id ";
    private String insertAppointmentMachineSQL = ""
            + "insert into public.appointment_machine (appointment_id, machine_id) values (?, ?)";
    private String updateSQL = ""
            + "update public.appointment "
            + "set date=?, patient_id=?, technician_id=?, state=? where id=?";
    private String deleteAppointmentMachineSQL = ""
            + "delete from public.appointment_machine where appointment_id=?";
    private String deleteSQL = ""
            + "delete from public.appointment "
            + "where id=?; "
            + deleteAppointmentMachineSQL;

    public Appointment find(int id) {
        try {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(findAppointmentSQL);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Appointment appointment = null;
            List<Machine> machines = new ArrayList<Machine>();
            List<Image> images = new ArrayList<Image>();

            while (rs.next()) {
                try {
                    machines.add(rsm.getMachine(rs));
                    Image image = rsm.getImage(rs); 
                    if (image != null) {
                    	images.add(image);
                    }
                    appointment = rsm.getAppointment(rs, rsm.getPatient(rs, rsm.getPatientAddress(rs)), rsm.getTechnician(rs), machines, rsm.getReport(rs), images);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            con.close();
            return appointment;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public ArrayList<Appointment> findAll() {
        try {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(findAllAppointmentSQL);
            ResultSet rs = statement.executeQuery();
            Appointment appointment = null;
            List<Machine> machines = new ArrayList<Machine>();
            List<Image> images = new ArrayList<Image>();
            ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
            Map<Integer, Appointment> appointmentMap = new HashMap<Integer, Appointment>();
            
            while (rs.next()) {
                try {
                    machines.add(rsm.getMachine(rs));
                    images.add(rsm.getImage(rs));
                    appointment = rsm.getAppointment(rs, rsm.getPatient(rs, rsm.getPatientAddress(rs)), rsm.getTechnician(rs), machines, rsm.getReport(rs), images);
                    appointmentMap.put(appointment.getId(), appointment);
                   
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            appointmentList.addAll(appointmentMap.values());

            con.close();
            return appointmentList;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public ArrayList<Appointment> findAll(int limit, int offset) {
        try {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(findAllAppointmentWithLimitSQL);
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet rs = statement.executeQuery();
            Appointment appointment = null;
            List<Machine> machines = new ArrayList<Machine>();
            List<Image> images = new ArrayList<Image>();
            ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
            Map<Integer, Appointment> appointmentMap = new HashMap<Integer, Appointment>();

            while (rs.next()) {
                try {
                    Machine machine = rsm.getMachine(rs);
                    if (machine != null) {
                        machines.add(machine);
                    }
                    
                    Image image = rsm.getImage(rs); 
                    if (image != null) {
                    	images.add(image);
                    }
                    
                    appointment = rsm.getAppointment(rs, rsm.getPatient(rs, rsm.getPatientAddress(rs)), rsm.getTechnician(rs), machines, rsm.getReport(rs), images);
                    appointmentMap.put(appointment.getId(), appointment);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            appointmentList.addAll(appointmentMap.values());
            con.close();
            return appointmentList;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }
    
	public ArrayList<Appointment> findAll(int limit, int offset, List<State> states) {
		try {
            Connection con = db.getConnection();            
            PreparedStatement statement = con.prepareStatement(findAllAppointmentWithLimitAndWhereSQL);
            for (int x = 0; x < states.size(); x++) {
            	statement.setString(x + 1, states.get(x).name());
            }
            statement.setInt(3, limit);
            statement.setInt(4, offset);
                        
            ResultSet rs = statement.executeQuery();
            Appointment appointment = null;
            List<Machine> machines = new ArrayList<Machine>();
            List<Image> images = new ArrayList<Image>();
            ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
            Map<Integer, Appointment> appointmentMap = new HashMap<Integer, Appointment>();

            while (rs.next()) {
                try {
                    Machine machine = rsm.getMachine(rs);
                    if (machine != null) {
                        machines.add(machine);
                    }
                    
                    Image image = rsm.getImage(rs); 
                    if (image != null) {
                    	images.add(image);
                    }
                    
                    appointment = rsm.getAppointment(rs, rsm.getPatient(rs, rsm.getPatientAddress(rs)), rsm.getTechnician(rs), machines, rsm.getReport(rs), images);
                    appointmentMap.put(appointment.getId(), appointment);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            appointmentList.addAll(appointmentMap.values());
            con.close();
            return appointmentList;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
	}

    public int countAll(List<State> states) {
        try {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(countWhereSQL);
            for (int x = 0; x < states.size(); x++) {
            	statement.setString(x + 1, states.get(x).name());
            }
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int numberOfRows = rs.getInt(1);
                return numberOfRows;
            } else {
                return 0;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            return 0;
        }
    }
    
    public int countAll() {
        try {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(countSQL);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int numberOfRows = rs.getInt(1);
                return numberOfRows;
            } else {
                return 0;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    public void insert(IDomainObject appointment) {
        try {
            int appointmentId = 0;
            int reportId = 0;
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(insertReportSQL);
            PreparedStatement statement1 = con.prepareStatement(insertAppointmentSQL);
            PreparedStatement statement2 = con.prepareStatement(insertAppointmentMachineSQL);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	reportId = rs.getInt(1);
            }
            
            Appointment m = (Appointment) appointment;
            statement1.setTimestamp(1, Timestamp.valueOf(m.getDate()));
            statement1.setInt(2, m.getPatient().getId());
            statement1.setInt(3, m.getTechnician().getId());
            statement1.setString(4, m.getState().name());
            statement1.setInt(5, reportId);
            ResultSet rs1 = statement1.executeQuery();

            if (rs1.next()) {
                appointmentId = rs1.getInt(1);
            }

            for (Machine machine : m.getMachines()) {
                statement2.setInt(1, appointmentId);
                statement2.setInt(2, machine.getId());
                statement2.executeUpdate();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void update(IDomainObject appointment) {
        try {
            Connection con = db.getConnection();
            Appointment m = (Appointment) appointment;

            PreparedStatement statement = con.prepareStatement(updateSQL);
            statement.setTimestamp(1, Timestamp.valueOf(m.getDate()));
            statement.setInt(2, m.getPatient().getId());
            statement.setInt(3, m.getTechnician().getId());
            statement.setString(4, m.getState().name());
            statement.setInt(5, m.getId());
            statement.executeUpdate();

            PreparedStatement statement2 = con.prepareStatement(deleteAppointmentMachineSQL);
            statement2.setInt(1, m.getId());
            statement2.executeUpdate();

            PreparedStatement statement3 = con.prepareStatement(insertAppointmentMachineSQL);
            for (Machine machine : m.getMachines()) {
                statement3.setInt(1, m.getId());
                statement3.setInt(2, machine.getId());
                statement3.executeUpdate();
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete(IDomainObject appointment) {
        try {
            Connection con = db.getConnection();
            PreparedStatement statement = con.prepareStatement(deleteSQL);
            Appointment m = (Appointment) appointment;
            statement.setInt(1, m.getId());
            statement.setInt(2, m.getId());
            statement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
