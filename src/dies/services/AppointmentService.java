package dies.services;
import dies.models.*;
import dies.mappers.*;
import dies.data.*;

import java.util.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AppointmentService {

	// data mappers required for service
	private AppointmentMapper appointmentMapper;
	private UserMapper userMapper;
	private PatientMapper patientMapper;
	
	// transaction object for create / edit appointments
	private UnitOfWork transaction;
	
	public AppointmentService() {
		appointmentMapper = new AppointmentMapper();
		userMapper = new UserMapper();
		patientMapper = new PatientMapper();
	}
	
	// load all appointment objects
	public List<Appointment> findAllAppointments() {
		return appointmentMapper.findAll();
	}
	
	// load an appointment object by id
	public Appointment findAppointment(int id) {
		Appointment appointment = appointmentMapper.find(id);
		transaction.registerClean(appointment); // start EDIT transaction
		return appointmentMapper.find(id);
	}
	
	// returns a list of all available datetimes for a new appointment
	public List<LocalDateTime> findAvailableDatetimes() {
		// STUB: currently returns every hour from now to one week ahead
		LocalDateTime start = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		LocalDateTime end = start.plusWeeks(2);
		List<LocalDateTime> dates = new ArrayList<LocalDateTime>();
		dates.add(start);
		dates.add(end);
		return dates;
	}
	
	// returns a list of all available technicians at the given datetime
	public List<Technician> findAvailableTechnicians(LocalDateTime datetime) {
		// STUB: currently just returns ALL technicians
		return userMapper.findAllTechnicians();
	}
	
	// attempts to locate a patient by medicare number, returns null if unable
	public Patient findPatient(String medicareNo) {
	    Patient patient = patientMapper.find(medicareNo);
	    transaction.registerClean(patient);
		return patient;
	}
	
	// finish CREATE patient sub-transaction
	public void finishCreatePatient(Patient patient) {
		transaction.registerCreated(patient);
		transaction.commit();
	}
	
	// finish CREATE new appointment
	public void finishCreateAppointment(Appointment appointment) {
		transaction.registerCreated(appointment);
		transaction.commit();
	}
	
	// finish EDIT appointment
	public void finishEditAppointment(Appointment appointment) {
		transaction.registerUpdated(appointment.getPatient());
		transaction.registerUpdated(appointment);
		transaction.commit();
	}
}