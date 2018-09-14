/**
 * Class for providing Appointment-management services.
 * 
 * @author ecranney, sweerakoon
 * @since September 2018
 * 
 */
package dies.services;

import dies.data.UnitOfWork;
import dies.mappers.AppointmentMapper;
import dies.mappers.MachineMapper;
import dies.mappers.PatientMapper;
import dies.mappers.UserMapper;
import dies.models.Appointment;
import dies.models.Machine;
import dies.models.Patient;
import dies.models.Technician;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

	// mappers for accessing necessary tables
	private AppointmentMapper appointmentMapper;
	private UserMapper userMapper;
	private PatientMapper patientMapper;
	private MachineMapper machineMapper;

	// transaction object for creating/editing appointments
	private UnitOfWork transaction;

	public AppointmentService() {
		appointmentMapper = new AppointmentMapper();
		userMapper = new UserMapper();
		patientMapper = new PatientMapper();
		machineMapper = new MachineMapper();
		transaction = new UnitOfWork();
	}

	// load all appointment objects
	public List<Appointment> findAllAppointments() {
		return appointmentMapper.findAll();
	}
	
	// load limited appointment set objects
		public List<Appointment> findAllAppointments(int limit, int offset) {
			return appointmentMapper.findAll(limit, offset);
		}

	// load an appointment object by id
	public Appointment findAppointment(int id) {
		Appointment appointment = appointmentMapper.find(id);
		System.out.println(id + " checking this one has the right id ");
		transaction.registerClean(appointment); // start EDIT transaction
		return appointment;
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

	public List<Machine> findAvailableMachines(LocalDateTime datetime) {

		// STUB: currently just returns all machines
		return machineMapper.findAll();
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

	// finish UPDATE patient sub-transaction
	public void finishUpdatePatient(Patient patient) {
		transaction.registerUpdated(patient);
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

	// finish Delete appointment
	public void finishDeleteAppointment(Appointment appointment) {
		transaction.registerDeleted(appointment);
		transaction.commit();
	}
}