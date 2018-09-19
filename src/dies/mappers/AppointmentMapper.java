package dies.mappers;

import db.DBConnection;
import dies.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentMapper extends DataMapper {


	private ResultSetDetails rsm = new ResultSetDetails();
	
	private DBConnection db = new DBConnection();
	private String findAllAppointmentSQL = "\r\n" + 
			"select t1.id                   					as appointment_id,\r\n" + 
			"       to_char(t1.date, 'YYYY-MM-DD HH:MI') 		as appointment_date,\r\n" + 
			"       t1.state                					as appointment_state,\r\n" + 
			"       t1.patient_id           					as patient_id,\r\n" + 
			"       t2t6.first_name         as patient_first_name,\r\n" + 
			"       t2t6.last_name          as patient_last_name,\r\n" + 
			"       t2t6.medicare_no        as patient_medicare_no,\r\n" + 
			"       t2t6.phone              as patient_phone,\r\n" + 
			"       t2t6.email              as patient_email,\r\n" + 
			"       t2t6.patient_address_id as patient_address_id,\r\n" + 
			"       t2t6.unit_no            as patient_unit_no,\r\n" + 
			"       t2t6.street_no          as patient_street_no,\r\n" + 
			"       t2t6.street_name        as patient_street_name,\r\n" + 
			"       t2t6.city               as patient_city,\r\n" + 
			"       t2t6.state              as patient_state,\r\n" + 
			"       t2t6.post_code          as patient_post_code,\r\n" + 
			"       t3t4.id                 as technician_id,\r\n" + 
			"       t3t4.username           as technician_username,\r\n" + 
			"       t3t4.firstname          as technician_first_name,\r\n" + 
			"       t3t4.lastname           as technician_last_name,\r\n" + 
			"       t7t8.id                 as appointment_machine_id,\r\n" + 
			"       t7t8.serial_code        as machine_serial_code,\r\n" + 
			"       t7t8.type               as machine_type\r\n" + 
			"from public.appointment t1\r\n" + 
			"       left outer join (select t4.id, t4.username, t4.firstname, t4.lastname\r\n" + 
			"                        from public.user t4\r\n" + 
			"                               inner join public.technician t3 on t4.id = t3.id) t3t4 on t3t4.id = t1.technician_id\r\n" + 
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
			"                          from public.patient t2\r\n" + 
			"                                left outer join public.address t6 on t2.address_id = t6.id) t2t6 on t2t6.id = patient_id\r\n" + 
			"                                left outer join (select t7.id, t7.serial_code, t7.type, t8.appointment_id\r\n" + 
			"                                from public.machine t7\r\n" + 
			"                                      right outer join public.appointment_machine t8 on t8.machine_id = t7.id) t7t8\r\n" + 
			"                                      on t7t8.appointment_id = t1.id";

	private String findAppointmentSQL = findAllAppointmentSQL + " where t1.id = ?";
	private String findAllAppointmentWithLimitSQL = findAllAppointmentSQL + " limit ? offset ?";
	private String countSQL = "select count(*) from public.appointment";
	private String insertSQL = ""
			+ "with rows as (insert into public.appointment (date, patient_id, technician_id, state) "
			+ "values (?, ?, ?, ?) returning id) "
			+ "insert into public.appointment_machine (appointment_id, machine_id) select id, ? from rows ";
	private String updateSQL = ""
			+ "update public.appointment "
			+ "set date=?, patient_id=?, technician_id=?, state=? where id=?";
	private String deleteSQL = ""
			+ "delete from public.appointment "
			+ "where id=?; delete from public.appointment_machine where appointment_id=?";

	public Appointment find(int id) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(findAppointmentSQL);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			Appointment appointment = null;
			Machine machine = null;
			List<Machine> machines = new ArrayList<Machine>();

			while (rs.next()) {
				try {
					machine = rsm.getMachine(rs);
					machines.add(machine);
					appointment = rsm.getAppointment(rs, rsm.getPatient(rs, rsm.getPatientAddress(rs)), rsm.getTechnician(rs));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
			Machine machine = null;
			List<Machine> machines = new ArrayList<Machine>();
			ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
			Map<Integer, Appointment> appointmentMap = new HashMap<Integer, Appointment>();
			
			while (rs.next()) {
				try {
					machine = rsm.getMachine(rs);
					machines.add(machine);
					appointment = rsm.getAppointment(rs, rsm.getPatient(rs, rsm.getPatientAddress(rs)), rsm.getTechnician(rs));
					appointmentMap.put(appointment.getId(), appointment);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			appointmentList.addAll(appointmentMap.values());
			return appointmentList;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	public ArrayList<Appointment> findAll(int limit, int offset) {
		try {
			Connection connection = db.getConnection();
			PreparedStatement statement = connection.prepareStatement(findAllAppointmentWithLimitSQL);
			statement.setInt(1, limit);
			statement.setInt(2, offset);
			ResultSet rs = statement.executeQuery();
			Appointment appointment = null;
			Machine machine = null;
			List<Machine> machines = new ArrayList<Machine>();
			ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
			Map<Integer, Appointment> appointmentMap = new HashMap<Integer, Appointment>();
			
			while (rs.next()) {
				try {
					machine = rsm.getMachine(rs);
					machines.add(machine);
					appointment = rsm.getAppointment(rs, rsm.getPatient(rs, rsm.getPatientAddress(rs)), rsm.getTechnician(rs));
					appointmentMap.put(appointment.getId(), appointment);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			appointmentList.addAll(appointmentMap.values());
			return appointmentList;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
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
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(insertSQL);
			Appointment m = (Appointment) appointment;
			statement.setTimestamp(1, Timestamp.valueOf(m.getDate()));
			statement.setInt(2, m.getPatient().getId());
			statement.setInt(3, m.getTechnician().getId());
			statement.setString(4, m.getState().name());
			statement.setLong(5, 1);
			statement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void update(IDomainObject appointment) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(updateSQL);
			Appointment m = (Appointment) appointment;
			statement.setTimestamp(1, Timestamp.valueOf(m.getDate()));
			statement.setInt(2, m.getPatient().getId());
			statement.setInt(3, m.getTechnician().getId());
			statement.setString(4, m.getState().name());
			statement.setInt(5, m.getId());
			statement.executeUpdate();
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
