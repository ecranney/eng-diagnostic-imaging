/**
 * An Appointment registered in the DIES system. Patients make appointments
 * to have their images taken; Technicians then use Machines to take images
 * for patients.
 * 
 * @author ecranney
 * @since 2018-09-09
 * 
 */
package dies.models;

import java.time.LocalDateTime;
import java.util.*;

import dies.data.UnitOfWork;

public class Appointment implements IDomainObject  {

	// identity field, used for database lookup
	private final int id;
	
	// datetime of the appointment
	private LocalDateTime date;
	
	// the patient and attending technician
	private Patient patient;
	private Technician technician;
	
	// the machines to be used in the appointment
	private List<Machine> machines;
	
	// the current state of an appointment
	public enum State {FUTURE, INVOICED, PAID, MISSED, CANCELLED}
	private State state;
	
	public Appointment(int id, LocalDateTime date, Patient patient,
			Technician technician, List<Machine> machines, State state) {
		this.id = id;
		this.date = date;
		this.patient = patient;
		this.technician = technician;
		this.machines = machines;
		this.state = state;
		
		// add to UnitOfWork
		UnitOfWork.registerCreated(this);
	}
	
	public int getId() {
		return id;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public Technician getTechnician() {
		return technician;
	}
	
	public List<Machine> getMachines() {
		return machines;
	}
	
	public State getState() {
		return state;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
		UnitOfWork.registerUpdated(this);
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
		UnitOfWork.registerUpdated(this);
	}
	
	public void setTechnician(Technician technician) {
		this.technician = technician;
		UnitOfWork.registerUpdated(this);
	}
	
	public void addMachine(Machine machine) {
		this.machines.add(machine);
	}
	
	public void removeMachine(Machine machine) {
		this.machines.remove(machine);
	}
	
	public void setState(State state) {
		this.state = state;
		UnitOfWork.registerUpdated(this);
	}
	
	public void delete() {
		UnitOfWork.registerDeleted(this);
	}
}
