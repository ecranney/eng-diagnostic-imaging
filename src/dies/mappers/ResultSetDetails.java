package dies.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import dies.models.Address;
import dies.models.Appointment;
import dies.models.Machine;
import dies.models.Patient;
import dies.models.Technician;
import dies.models.User;

public class ResultSetDetails {
	public Address getPatientAddress(ResultSet rs) throws SQLException {
		return new Address(rs.getInt("patient_address_id"), rs.getInt("patient_unit_no"),
				rs.getInt("patient_street_no"), rs.getString("patient_street_name"),
				rs.getString("patient_city"), rs.getString("patient_state"),
				rs.getInt("patient_post_code"));		
	}
	
	public Patient getPatient(ResultSet rs, Address patientAddress) throws SQLException{
		return new Patient(rs.getInt("patient_id"), rs.getString("patient_first_name"),
				rs.getString("patient_last_name"), patientAddress,
				rs.getString("patient_phone"), rs.getString("patient_medicare_no"), rs.getString("patient_email"));
	}
	
	public Technician getTechnician(ResultSet rs) throws SQLException {
		return new Technician(rs.getInt("technician_id"), rs.getString("technician_username"), null,
				rs.getString("technician_first_name"), rs.getString("technician_last_name"));
	}
	
	public Machine getMachine(ResultSet rs) throws SQLException{
		return new Machine(rs.getInt("appointment_machine_id"), rs.getLong("machine_serial_code"),
				Machine.Type.valueOf(rs.getString("machine_type")));
	}
	
	public Appointment getAppointment(ResultSet rs, Patient patient, Technician technician) throws SQLException{
		return new Appointment(rs.getInt("appointment_id"), rs.getTimestamp("appointment_date").toLocalDateTime(), patient,
				technician, null, Appointment.State.valueOf(rs.getString("appointment_state")));
	}
	
	public User getUser(ResultSet rs){
		try {
			return new User(0, rs.getString("username"), rs.getString("password"), rs.getString("first_name"),
					rs.getString("last_name")) {
			};
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
