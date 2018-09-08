package dies.services;
import dies.models.*;
import dies.mappers.*;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AppointmentService {

	public ArrayList<Appointment> findAllAppointments() {
		return ((AppointmentMapper) DataMapper.getMapper(Appointment.class)).findAllAppointments();
	}
	
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
	
	public ArrayList<Technician> findAvailableTechnicians(
			Appointment appointment) {
		// STUB logic: returns all techncians
		return UserMapper.findAllTechnicians();
	}
	
}
