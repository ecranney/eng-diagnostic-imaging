package dies.models;
import java.util.Date;

public class Appointment {

	private final int id;
	private Date date;
	private Patient patient;
	private Technician technician;
	private State state;
	
	public enum State {FUTURE, COMPLETED, MISSED}
	
	public Appointment(int id, Date date, Patient patient,
			Technician technician) {
		this.id = id;
		this.date = date;
		this.patient = patient;
		this.technician = technician;
	}
	
	public Appointment(int id, Date date, Patient patient,
			Technician technician, State state) {
		this.id = id;
		this.date = date;
		this.patient = patient;
		this.technician = technician;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}
	
	public Date getDate() {
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
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void setTechnician(Technician technician) {
		this.technician = technician;
	}
	
	public void setState(State state) {
		this.state = state;
	}
}
