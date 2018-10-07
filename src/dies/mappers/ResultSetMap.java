package dies.mappers;

import dies.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ResultSetMap {
    public Address getPatientAddress(ResultSet rs) throws SQLException {
        return new Address(rs.getInt("patient_address_id"), rs.getInt("patient_unit_no"),
                rs.getInt("patient_street_no"), rs.getString("patient_street_name"),
                rs.getString("patient_city"), rs.getString("patient_state"),
                rs.getInt("patient_post_code"));
    }

    public Patient getPatient(ResultSet rs, Address patientAddress) throws SQLException {
        return new Patient(rs.getInt("patient_id"), rs.getString("patient_first_name"),
                rs.getString("patient_last_name"), patientAddress,
                rs.getString("patient_phone"), rs.getString("patient_medicare_no"), rs.getString("patient_email"));
    }

    public Technician getTechnician(ResultSet rs) throws SQLException {
        return new Technician(rs.getInt("technician_id"), rs.getString("technician_username"), null,
                rs.getString("technician_first_name"), rs.getString("technician_last_name"), rs.getString("technician_group"), null);
    }

    public Machine getMachine(ResultSet rs) throws SQLException {
        if (rs.getString("machine_type") != null) {
            return new Machine(rs.getInt("appointment_machine_id"), rs.getString("machine_serial_code"),
                    Machine.Type.valueOf(rs.getString("machine_type")));
        }
        return null;
    }
    
    public Report getReport(ResultSet rs) throws SQLException {
    	
    	Radiologist author = new Radiologist(rs.getInt("report_author_id"), null, null, null, rs.getString("report_author_firstname"), rs.getString("report_author_lastname"), null, null);
    	Radiologist reviewer = new Radiologist(rs.getInt("report_reviewer_id"), null, null, null, rs.getString("report_reviewer_firstname"), rs.getString("report_reviewer_lastname"), null, null);
    	
    	String reportCreatedD = rs.getString("report_date_created");
    	String reportUpdatedD = rs.getString("report_date_updated");
    	LocalDateTime reportCreatedDate= null;
    	LocalDateTime reportUpdatedDate = null;
    	
    	if (reportCreatedD != null) {
    		reportCreatedDate = LocalDateTime.parse(rs.getString("report_date_created"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    	}
    	
    	if (reportUpdatedD != null) {
    		reportUpdatedDate = LocalDateTime.parse(rs.getString("report_date_updated"),
        			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    	}
    	return new Report(rs.getInt("report_id"), author, reviewer, null, rs.getString("report_content"), reportCreatedDate, reportUpdatedDate, Report.State.valueOf(rs.getString("report_state")));
    }
    
    public Image getImage(ResultSet rs) throws SQLException {
        if (rs.getString("machine_image_url") != null) {
            return new Image(0, rs.getString("machine_image_url"), rs.getString("machine_type"));
        }
        return null;
    }

    public Appointment getAppointment(ResultSet rs, Patient patient, Technician technician, List<Machine> machines, Report report, List<Image> images) throws SQLException {
        LocalDateTime appointmentDate = LocalDateTime.parse(rs.getString("appointment_date"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new Appointment(rs.getInt("appointment_id"), appointmentDate, patient,
                technician, machines, Appointment.State.valueOf(rs.getString("appointment_state")), report, images);
    }

    public User getUser(ResultSet rs) {
        try {
            return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getString("group"), rs.getString("password_hash")) {
            };
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
