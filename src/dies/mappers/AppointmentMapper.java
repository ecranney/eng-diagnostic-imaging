package dies.mappers;

import db.DBConnection;
import dies.data.IdentityMap;
import dies.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class AppointmentMapper extends DataMapper {

	private DBConnection db = new DBConnection();
	private String findAllAppointmentSQL = "select \r\n" + 
			"t1.id as ap_id, \r\n" + 
			"t1.date as ap_date, \r\n" + 
			"t1.state as ap_state,\r\n" + 
			"\r\n" + 
			"t1.patient_id as patient_id, \r\n" + 
			"t2t6.first_name as patient_first_name, \r\n" + 
			"t2t6.last_name as patient_last_name, \r\n" + 
			"t2t6.medicare_no as patient_medicate_no,\r\n" + 
			"t2t6.phone as patient_mobile_no,\r\n" + 
			"\r\n" + 
			"t2t6.patient_address_id as patient_address_id, \r\n" + 
			"t2t6.unit_no as patient_unit_no, \r\n" + 
			"t2t6.street_no as patient_street_no, \r\n" + 
			"t2t6.street_name as patient_street_name, \r\n" + 
			"t2t6.city as patient_city, \r\n" + 
			"t2t6.state as patient_state, \r\n" + 
			"t2t6.post_code as patient_post_code,\r\n" + 
			"\r\n" + 
			"t3t4.id as technician_id, \r\n" + 
			"t3t4.username as technician_username, \r\n" + 
			"t3t4.firstname as technician_first_name, \r\n" + 
			"t3t4.lastname as technician_last_name,\r\n" + 
			"\r\n" + 
			"t7t8.id as appointment_machine_id,\r\n" + 
			"t7t8.serial_code as machine_serial_code,\r\n" + 
			"t7t8.type as machine_type\r\n" + 
			"\r\n" + 
			"from public.appointment t1\r\n" + 
			"left outer join\r\n" + 
			"(\r\n" + 
			"select t4.id, t4.username, t4.firstname, t4.lastname\r\n" + 
			"from public.user t4\r\n" + 
			"inner join public.technician t3\r\n" + 
			"on t4.id = t3.id\r\n" + 
			") t3t4 on t3t4.id = t1.technician_id\r\n" + 
			"\r\n" + 
			"inner join \r\n" + 
			"(select \r\n" + 
			"t2.id , \r\n" + 
			"t2.first_name, \r\n" + 
			"t2.last_name, \r\n" + 
			"t2.medicare_no,\r\n" + 
			"t2.phone,\r\n" + 
			"\r\n" + 
			"t6.id as patient_address_id, \r\n" + 
			"t6.unit_no, \r\n" + 
			"t6.street_no, \r\n" + 
			"t6.street_name, \r\n" + 
			"t6.city, \r\n" + 
			"t6.state, \r\n" + 
			"t6.post_code\r\n" + 
			"\r\n" + 
			"from public.patient t2 \r\n" + 
			"left outer join public.address t6\r\n" + 
			"on t2.address_id=t6.id\r\n" + 
			") t2t6 on t2t6.id = patient_id\r\n" + 
			"left outer join\r\n" + 
			"(\r\n" + 
			"select t7.id, t7.serial_code, t7.type,t8.appointment_id\r\n" + 
			"\r\n" + 
			"from public.machine t7\r\n" + 
			"right outer join \r\n" + 
			"public.appointment_machine t8\r\n" + 
			"on t8.machine_id = t7.id\r\n" + 
			") t7t8 on t7t8.appointment_id = t1.id\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"";
	private String findAppointmentSQL = findAllAppointmentSQL + " where t1.id = ?";
	private String insertSQL = "insert into id, date, patient_id, technician_id, state public.appointment values (?, ?, ?)";
	private String updateSQL = "update public.appointment set into id=?, date=?, patient_id=?, technician_id=?, state=?";
	private String deleteSQL = "delete from public.appointment where id=? and date=? and patient_id=? and technician_id=? and state=?";

	public Appointment find(int id) throws SQLException {
		Connection con = db.getConnection();
		PreparedStatement statement = con.prepareStatement(findAppointmentSQL);
		statement.setInt(1, id);
		int app_id = 0;
		LocalDateTime app_date = null;
		Appointment.State app_state = null;
		Appointment app = null;
		Address patientAddress = null;
		Patient patient = null;
		Technician technician = null;
		Machine machine = null;
		List<Machine> machines = new ArrayList<Machine>();
		ResultSet rs = statement.executeQuery();

		IdentityMap<Appointment> map = IdentityMap.getInstance(Appointment.class);
		if (map.contains(id)) {
			Appointment appointment = map.get(id);
		} else {
			while (rs.next()) {
				try {
					patientAddress = new Address(rs.getInt("patient_address_id"), rs.getInt("patient_unit_no"),
							rs.getInt("patient_street_no"), rs.getString("patient_street_name"),
							rs.getString("patient_city"), rs.getString("patient_state"),
							rs.getInt("patient_post_code"));

					technician = new Technician(rs.getInt("technician_id"), rs.getString("technician_username"),
							null, rs.getString("technician_first_name"),
							rs.getString("technician_last_name"));
					
					patient = new Patient(rs.getInt("patient_id"), rs.getString("patient_first_name"), rs.getString("patient_last_name"),
							patientAddress, rs.getString("patient_address_id"), rs.getString("patient_medicate_no"));
					machine = new Machine(rs.getInt("appointment_machine_id"), rs.getLong("machine_serial_code"),
							Machine.Type.valueOf(rs.getString("machine_type")));
					machines.add(machine);					
					
					app_id = rs.getInt("ap_id");
					app_date = rs.getTimestamp("ap_date").toLocalDateTime();
					app_state = Appointment.State.valueOf(rs.getString("ap_state"));

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			app = new Appointment(app_id, app_date, patient, technician, null, app_state);
			return app;
		}
		return null;
	}

	public ArrayList<Appointment> findAll() throws SQLException {
		Connection con = db.getConnection();
		PreparedStatement statement = con.prepareStatement(findAllAppointmentSQL);

		Appointment app = null;
		ArrayList<Appointment> appList = new ArrayList<Appointment>();
		Address patientAddress = null;
		Technician technician = null;
		Patient patient = null;
		Machine machine = null;
		List<Machine> machines = new ArrayList<Machine>();
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			
			try {
				patientAddress = new Address(rs.getInt("patient_address_id"), rs.getInt("patient_unit_no"),
						rs.getInt("patient_street_no"), rs.getString("patient_street_name"),
						rs.getString("patient_city"), rs.getString("patient_state"),
						rs.getInt("patient_post_code"));

				technician = new Technician(rs.getInt("technician_id"), rs.getString("technician_username"),
						null, rs.getString("technician_first_name"),
						rs.getString("technician_last_name"));
				
				patient = new Patient(rs.getInt("patient_id"), rs.getString("patient_first_name"), rs.getString("patient_last_name"),
						patientAddress, rs.getString("patient_address_id"), rs.getString("patient_medicate_no"));
				machine = new Machine(rs.getInt("appointment_machine_id"), rs.getLong("machine_serial_code"),
						Machine.Type.valueOf(rs.getString("machine_type")));
				machines.add(machine);
				app = new Appointment(rs.getInt("ap_id"), rs.getTimestamp("ap_date").toLocalDateTime(), patient, technician,
						null, Appointment.State.valueOf(rs.getString("ap_state")));
				
				appList.add(app);				
				System.out.println(appList.contains(app));
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return appList;
	}

	public void insert(IDomainObject appointment) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(insertSQL);
			Appointment m = (Appointment) appointment;

			statement.setInt(1, m.getId());

			statement.setTimestamp(2, Timestamp.valueOf(m.getDate()));

			statement.setInt(3, m.getPatient().getId());

			statement.setInt(4, m.getTechnician().getId());

			statement.setString(5, m.getState().name());

			statement.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void update(IDomainObject appointment) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(updateSQL);
			Appointment m = (Appointment) appointment;

			statement.setInt(1, m.getId());

			statement.setTimestamp(2, Timestamp.valueOf(m.getDate()));

			statement.setInt(3, m.getPatient().getId());

			statement.setInt(4, m.getTechnician().getId());

			statement.setString(5, m.getState().name());

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void delete(IDomainObject appointment) {
		try {
			Connection con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(deleteSQL);
			Appointment m = (Appointment) appointment;

			statement.setInt(1, m.getId());

			statement.setTimestamp(2, Timestamp.valueOf(m.getDate()));

			statement.setInt(3, m.getPatient().getId());

			statement.setInt(4, m.getTechnician().getId());

			statement.setString(5, m.getState().name());

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
