package dies;
import java.util.Date;

public class Appointment {

	private Date datetime;
	private Patient patient;
	private Technician technician;
	
	public Appointment(Date datetime, Patient patient, Technician technician) {
		this.datetime = datetime;
		this.patient = patient;
		this.technician = technician;
	}
		
}
