/**
 * An Appointment registered in the DIES system. Patients make appointments
 * to have their images taken; Technicians then use Machines to take images
 * for patients.
 *
 * @author ecranney
 * @since 2018-09-09
 */
package dies.models;

import java.time.LocalDateTime;
import java.util.List;

public class Appointment implements IDomainObject {

    // identity field, used for database lookup
    private final int id;

    // datetime of the appointment
    private LocalDateTime date;

    // the patient and attending technician
    private Patient patient;
    private Technician technician;

    // the machines to be used in the appointment
    private List<Machine> machines;
    private State state;

    public Appointment(int id, LocalDateTime date, Patient patient,
                       Technician technician, List<Machine> machines, State state) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.technician = technician;
        this.machines = machines;
        this.state = state;
        System.out.println("this is the constrcutor of appointment " + this.state.toString());
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addMachine(Machine machine) {
        this.machines.add(machine);
    }

    public void removeMachine(Machine machine) {
        this.machines.remove(machine);
    }

    @Override
    public String toString() {
        return "Appointment [id=" + id + ", date=" + date + ", patient=" + patient.getFirstName() + ", technician=" + technician.getFirstName()
                + ", machines=" + machines + ", state=" + state + ", getId()=" + getId() + ", getDate()=" + getDate() + ", getMachines()="
                + getMachines().get(0).getSerialNo() + ", getState()=" + getState() + " ]";
    }

    // the current state of an appointment
    public enum State {
        FUTURE, INVOICED, PAID, MISSED, CANCELLED
    }


}
