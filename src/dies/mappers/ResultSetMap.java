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
        System.out.println(rs.getString("patient_first_name") + " " + rs.getString("patient_last_name") + " ");
        return new Patient(rs.getInt("patient_id"), rs.getString("patient_first_name"),
                rs.getString("patient_last_name"), patientAddress,
                rs.getString("patient_phone"), rs.getString("patient_medicare_no"), rs.getString("patient_email"));
    }

    public Technician getTechnician(ResultSet rs) throws SQLException {
        return new Technician(rs.getInt("technician_id"), rs.getString("technician_username"), null,
                rs.getString("technician_first_name"), rs.getString("technician_last_name"), rs.getString("technician_group"));
    }

    public Machine getMachine(ResultSet rs) throws SQLException {
        if (rs.getString("machine_type") != null) {
            System.out.print(rs.getString("machine_serial_code") + " " +
                    Machine.Type.valueOf(rs.getString("machine_type")));
            return new Machine(rs.getInt("appointment_machine_id"), rs.getString("machine_serial_code"),
                    Machine.Type.valueOf(rs.getString("machine_type")));
        }
        System.out.println("NULL");
        return null;

    }

    public Appointment getAppointment(ResultSet rs, Patient patient, Technician technician, List<Machine> machines) throws SQLException {
        LocalDateTime appointmentDate = LocalDateTime.parse(rs.getString("appointment_date"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new Appointment(rs.getInt("appointment_id"), appointmentDate, patient,
                technician, machines, Appointment.State.valueOf(rs.getString("appointment_state")));
    }

    public User getUser(ResultSet rs) {
        try {
            return new User(0, rs.getString("username"), rs.getString("password"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getString("group")) {
            };
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}