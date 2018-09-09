package db;

import java.sql.SQLException;

import dies.mappers.AppointmentMapper;
import dies.services.LoginService;

public class DBConnectionTest {

	public static void main(String[] args) throws SQLException {			
//			LoginService lg = new LoginService();
//			boolean x = lg.login("admin", "admin");	
//			System.out.println(x);
		
		AppointmentMapper ap = new AppointmentMapper();
		ap.findAppointment(1);
	}
}
