package db;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dies.mappers.AppointmentMapper;
import dies.models.Appointment;
import dies.models.Technician;
import dies.models.User;
import dies.services.AppointmentService;
import dies.services.LoginService;

public class DBConnectionTest {

	public static void main(String[] args) throws SQLException {			
//			LoginService lg = new LoginService();
//			User x = lg.login("admin", "admin");
		
//		AppointmentMapper ap = new AppointmentMapper();
//		ap.find(1);
		
		
//		AppointmentService ap = new AppointmentService();
//		ap.findAppointment(1);
		
		
		String valuee = "25/04/2013";
		String time1 = "2017-10-06T17:48:23.558";
		LocalDateTime localDateTime = LocalDateTime.parse(time1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
		AppointmentService appointmentService = new AppointmentService();
		List<Technician> tList = appointmentService.findAvailableTechnicians(localDateTime);
		
		
	}
}
