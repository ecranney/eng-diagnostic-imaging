package dies.services;
import dies.models.*;
import dies.mappers.*;
import dies.data.*;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AppointmentService {

	private AppointmentMapper appointmentMapper;
	private UserMapper userMapper;
	private UnitOfWork createAppointmentTransaction;
	
	public AppointmentService() {
		appointmentMapper = new AppointmentMapper();
		userMapper = new UserMapper();

	}
//
//	public ArrayList<Appointment> findAllAppointments() {
//		return appointmentMapper.findAllAppointments();
//	}
	
	public ArrayList<LocalDateTime> findAvailableDates() {
		// STUB: returns all datetimes (by hour) from current
		// date, to one week into the future
		LocalDateTime start = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		LocalDateTime end = start.plusWeeks(2);
		ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
		dates.add(start);
		dates.add(end);
		return dates;
	}
	
//	public ArrayList<Technician> findAvailableTechnicians(
//			Appointment appointment) {
//		// STUB logic: returns all techncians
//		return userMapper.findAllTechnicians();
//	}
	
	public void registerNewPatient(Patient patient) {
		createAppointmentTransaction.registerCreated(patient);
	}
	
	public void createNewAppointment(Appointment appointment) {
		createAppointmentTransaction.registerCreated(appointment);
	}
}
