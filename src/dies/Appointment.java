package dies;
import java.util.Date;

public class Appointment {

	private Date date;
	private Patient patient;
	private Technician technician;
	private State state;
	
	public enum State {FUTURE, COMPLETED, MISSED}
	
	public Appointment(Date date, Patient patient, Technician technician) {
		this.date = date;
		this.patient = patient;
		this.technician = technician;
	}
	
	public Appointment(Date date, Patient patient, Technician technician,
			State state) {
		this.date = date;
		this.patient = patient;
		this.technician = technician;
		this.state = state;
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
