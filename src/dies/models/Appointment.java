package dies.models;
import java.time.LocalDateTime;

import dies.data.UnitOfWork;

public class Appointment extends DomainObject  {

	private final int id;
	
	private LocalDateTime date;
	private Patient patient;
	private Technician technician;
	private State state;
	
	public enum State {FUTURE, COMPLETED, MISSED}
	
	public Appointment(int id, LocalDateTime date, Patient patient,
			Technician technician, State state) {
		this.id = id;
		this.date = date;
		this.patient = patient;
		this.technician = technician;
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
	
	public State getState() {
		return this.state;
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
	
	public void setState(State state) {
		this.state = state;
		UnitOfWork.registerUpdated(this);
	}
	
	public void delete() {
		UnitOfWork.registerDeleted(this);
	}
}
