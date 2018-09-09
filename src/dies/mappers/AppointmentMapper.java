package dies.mappers;

import dies.data.IdentityMap;
import dies.models.*;
import dies.models.Appointment.State;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.DBConnection;

public class AppointmentMapper extends DataMapper {

	private DBConnection db = new DBConnection();
	private String findAppointmentSQL = "SELECT id, date, patient_id, technician_id, APPOINTMENT_MACHINE_ID, state from public.appointment ";

	public Appointment findAppointment(int id) throws SQLException {
		Connection con = db.getConnection();
		PreparedStatement statement = con.prepareStatement(findAppointmentSQL);
//		statement.setInt(1, id);
		Appointment user = null;

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			try {
				System.out.println(rs.getInt("id") + " " + rs.getString("date") + " " + rs.getInt("patient_id") + " "
						+ rs.getInt("technician_id") + " " + rs.getInt("APPOINTMENT_MACHINE_ID") + " "
						+ rs.getString("state"));

//				user = new Appointment(rs.getInt("id"), rs.getString("date") , rs.getInt("patient_id"), rs.getInt("technician_id")
//				, rs.getInt("APPOINTMENT_MACHINE_ID") , rs.getString("state"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			
		}
		return null;

//		IdentityMap<Appointment> map = IdentityMap.getInstance(Appointment.class);
//		if (map.contains(id)) {
//			Appointment appointment = map.get(id);
//		} else {
//			
//		}
//		return null;
	}

	public ArrayList<Appointment> findAllAppointments() throws SQLException {
		/*
		 * if {IdentityMap.getInstance(User.class).contains(id)} { User user =
		 * IdentityMap.getInstance(User.class).get(id); return user; } 1) move to data
		 * mappers, then (2) try to access identity map every time find is called
		 */
		Connection con = db.getConnection();
		PreparedStatement statement = con.prepareStatement(findAppointmentSQL);
		Appointment app = null;

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			try {
				System.out.println(rs.getInt("id") + " " + rs.getString("date") + " " + rs.getInt("patient_id") + " "
						+ rs.getInt("technician_id") + " " + rs.getInt("APPOINTMENT_MACHINE_ID") + " "
						+ rs.getString("state"));

				app = new Appointment(rs.getInt("id"), rs.getString("date"), rs.getInt("patient_id"),
						Technician technician, List<Machine> machines, State state);
				user = new Appointment(rs.getInt("id"), rs.getString("date") , rs.getInt("patient_id"), rs.getInt("technician_id")
				, rs.getInt("APPOINTMENT_MACHINE_ID") , rs.getString("state"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			//
		}
		return null;

//		IdentityMap<Appointment> map = IdentityMap.getInstance(Appointment.class);
//		if (map.contains(id)) {
//			Appointment appointment = map.get(id);
//		} else {
//			
//		}
//		return null;
	}

	public void insert(IDomainObject appointment) {
		// SQL
	}

	public void update(IDomainObject appointment) {
		// SQL
	}

	public void delete(IDomainObject appointment) {
		// SQL
	}

}
