package dies.mappers;

import db.DBConnection;
import dies.data.IdentityMap;
import dies.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper extends DataMapper {

	private DBConnection db = new DBConnection();
	private String findAppointmentSQL = "SELECT t1.id as ap_id, t1.date as ap_date, t1.state as ap_state,\r\n"
			+ "t1.patient_id as patient_id, t2.FIRST_NAME as patient_first_name, t2.last_name as patient_last_name, \r\n"
			+ "t6.id as patient_address_id, t6.unit_no as patient_unit_no, t6.street_no as patient_street_no, \r\n"
			+ "t6.street_name as patient_street_name, t6.city as patient_city, t6.state as patient_state, t6.post_code as patient_post_code, \r\n"
			+ "t2.phone as patient_mobile, \r\n" + "t2.medicare_no as patient_medicate_no,\r\n"
			+ "t5join.id as tech_id, t5join.username as tech_username, t5join.password as tech_password,\r\n"
			+ "t5join.firstname as tech_first_name, t5join.lastname as tech_last_name,\r\n"
			+ "t8join.MACHINE_id, t8join.SERIAL_CODE, t8join.TYPE\r\n" + "from public.appointment t1\r\n"
			+ "inner join public.patient t2\r\n" + "on t1.patient_id = t2.id\r\n" + "FULL OUTER join \r\n"
			+ "(SELECT t4.id, t4.username, t4.password, t4.firstname, t4.lastname\r\n" + "from public.user t4\r\n"
			+ "inner join public.TECHNICIAN t3\r\n" + "on t4.id = t3.id\r\n"
			+ ") t5join on t5join.id = t1.TECHNICIAN_ID\r\n" + "left join public.address t6\r\n"
			+ "on t6.id = t2.address_id\r\n" + "full outer join\r\n"
			+ "(SELECT t7.id, t7.MACHINE_id, t7.APPOINTMENT_ID, t8.SERIAL_CODE, t8.TYPE\r\n"
			+ "from public.APPOINTMENT_MACHINE t7\r\n" + "FULL OUTER join public.MACHINE t8\r\n"
			+ "on t7.MACHINE_id = t8.id)\r\n" + "t8join on t8join.APPOINTMENT_ID = t1.ID\r\n" + "where t1.id = ?";

	private String findAllAppointmentSQL = "SELECT t1.id as ap_id, t1.date as ap_date, t1.state as ap_state,\r\n"
			+ "t2.FIRST_NAME as patient_first_name, t2.last_name as patient_last_name, \r\n"
			+ "t2.medicare_no as patient_medicate_no\r\n" + "from public.appointment t1\r\n"
			+ "inner join public.patient t2\r\n" + "on t1.patient_id = t2.id\r\n" + "INNER join \r\n"
			+ "(SELECT t4.id, t4.firstname, t4.lastname\r\n" + "from public.user t4\r\n"
			+ "inner join public.TECHNICIAN t3\r\n" + "on t4.id = t3.id\r\n"
			+ ") t5join on t5join.id = t1.TECHNICIAN_ID\r\n" + "inner join public.address t6\r\n"
			+ "on t6.id = t2.address_id";

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
					System.out.println(rs.getInt("ap_id") + " " + rs.getTimestamp("ap_date").toLocalDateTime() + " "
							+ Appointment.State.valueOf(rs.getString("ap_state")) + " "
							+ rs.getString("patient_first_name") + " " + rs.getString("patient_last_name") + " "
							+ rs.getInt("patient_address_id") + rs.getInt("patient_unit_no")
							+ rs.getInt("patient_street_no") + rs.getString("patient_street_name")
							+ rs.getString("patient_city") + rs.getString("patient_state")
							+ rs.getInt("patient_post_code") + rs.getString("patient_mobile")
							+ rs.getString("patient_medicate_no") + rs.getInt("tech_id")
							+ rs.getString("tech_first_name") + rs.getString("tech_last_name") + rs.getInt("MACHINE_id")
							+ rs.getLong("SERIAL_CODE") + Machine.Type.valueOf(rs.getString("TYPE")));

					patientAddress = new Address(rs.getInt("patient_address_id"), rs.getInt("patient_unit_no"),
							rs.getInt("patient_street_no"), rs.getString("patient_street_name"),
							rs.getString("patient_city"), rs.getString("patient_state"),
							rs.getInt("patient_post_code"));

					patient = new Patient(rs.getInt("patient_id"), rs.getString("patient_first_name"),
							rs.getString("patient_last_name"), patientAddress, rs.getString("patient_mobile"),
							rs.getString("patient_medicate_no"));

					technician = new Technician(rs.getInt("tech_id"), rs.getString("tech_username"),
							rs.getString("tech_password"), rs.getString("tech_first_name"),
							rs.getString("tech_last_name"));
					machine = new Machine(rs.getInt("MACHINE_id"), rs.getLong("SERIAL_CODE"),
							Machine.Type.valueOf(rs.getString("TYPE")));
					machines.add(machine);
					app_id = rs.getInt("ap_id");
					app_date = rs.getTimestamp("ap_date").toLocalDateTime();
					app_state = Appointment.State.valueOf(rs.getString("ap_state"));

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			app = new Appointment(app_id, app_date, patient, technician, machines, app_state);
			System.out.println("this is from the app mapper ");
			System.out.println(app);
			app.toString();
			System.out.println("this is from the app mapper ");
			System.out.println("this is from the app mapper ");
			return app;
		}
		return null;
	}

	public ArrayList<Appointment> findAll() throws SQLException {
		Connection con = db.getConnection();
		PreparedStatement statement = con.prepareStatement(findAllAppointmentSQL);
		Appointment app = null;
		ArrayList<Appointment> appList = new ArrayList<Appointment>();
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			try {
				Patient patient = new Patient(0, rs.getString("patient_first_name"), rs.getString("patient_last_name"),
						null, "", "");

				app = new Appointment(rs.getInt("ap_id"), rs.getTimestamp("ap_date").toLocalDateTime(), patient, null,
						null, Appointment.State.valueOf(rs.getString("ap_state")));
				appList.add(app);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return appList;
	}

	public void insert(IDomainObject appointment) {
		// STUB
		// SQL goes here
	}

	public void update(IDomainObject appointment) {
		// STUB
		// SQL goes here
	}

	public void delete(IDomainObject appointment) {
		// STUB
		// SQL goes here
	}

}
