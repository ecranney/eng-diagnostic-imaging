package db;

import dies.mappers.AppointmentMapper;
import dies.mappers.UserMapper;
import dies.models.Appointment;
import dies.models.Technician;
import dies.services.AppointmentService;
import dies.services.LoginService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DBConnectionTest {

    public static void main(String[] args) {
//        String valuee = "25/04/2013";
//        String time1 = "2017-10-06T17:48:23.558";
//        LocalDateTime localDateTime = LocalDateTime.parse(time1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
//        AppointmentService appointmentService = new AppointmentService();
//        List<Technician> tList = appointmentService.findAvailableTechnicians(localDateTime);
    	
//    	UserMapper um = new UserMapper();
////    	um.find("admin", "admin");
//    	LoginService loginService = new LoginService();
//        loginService.login("admin", "admin");
    	
    	AppointmentMapper appointmentMapper = new AppointmentMapper();
    	Appointment appointment = new Appointment(5, null, null, null, null, null);
		
		appointmentMapper.delete(appointment);
        
    }
}
