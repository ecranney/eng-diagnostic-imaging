package db;

import java.sql.SQLException;

import dies.mappers.AppointmentMapper;
import dies.models.Appointment;
import dies.models.User;
import dies.services.AppointmentService;
import dies.services.LoginService;

public class DBConnectionTest {

	public static void main(String[] args) throws SQLException {			
			LoginService lg = new LoginService();
			User x = lg.login("admin", "admin");
		
//		AppointmentMapper ap = new AppointmentMapper();
//		ap.find(1);
		
		
//		AppointmentService ap = new AppointmentService();
//		ap.findAppointment(1);
		
		
	}
}
