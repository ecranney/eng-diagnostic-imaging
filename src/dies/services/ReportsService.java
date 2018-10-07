/**
 * Class for providing Reports services to the presentation layer.
 */

package dies.services;

import java.util.*;

import dies.models.*;
import dies.locks.LockingMapper;
import dies.mappers.*;

public class ReportsService {
	
	// mappers for accessing the necessary tables
	private AppointmentMapper appointmentMapper;
	private LockingMapper reportMapper;
	
	public ReportsService() {
		appointmentMapper = new AppointmentMapper();
		ReportMapper wrappedReportMapper = new ReportMapper();
		reportMapper = new LockingMapper(wrappedReportMapper);
	}
	
	public List<Appointment> findAllCompletedAppointments() {
		
		// fetch the list of all appointments
		ArrayList<Appointment> appointments = appointmentMapper.findAll();
		
		// filter out appointments that aren't completed
		ArrayList<Appointment> filtered = new ArrayList<Appointment>();
		for (Appointment appointment : appointments) {
			if (appointment.isCompleted()) {
				filtered.add(appointment);
			}
		}
		return filtered;
	}
	
	// load limited appointment set objects
    public List<Appointment> findAllCompletedAppointments(int limit, int offset) {
    	// fetch the list of all appointments
    	List<Appointment.State> complatedStates = new ArrayList<Appointment.State>();
    	complatedStates.add(Appointment.State.PAID);
    	complatedStates.add(Appointment.State.INVOICED);
    	
    	ArrayList<Appointment> appointments = appointmentMapper.findAll(limit, offset, complatedStates);
    	return appointments;
    }
    
    // load all appointment objects
    public int countAllAppointments() {
    	List<Appointment.State> complatedStates = new ArrayList<Appointment.State>();
    	complatedStates.add(Appointment.State.PAID);
    	complatedStates.add(Appointment.State.INVOICED);
    	
        return appointmentMapper.countAll(complatedStates);
    }
	
	public Report findReport(int id) {
		return (Report) reportMapper.find(id);
	}
	
	public void submitReport(Report report) {
		reportMapper.update(report);
	}
	
	public void submitReview(Report report) {
		reportMapper.update(report);
	}
	
	public void deleteReport(Report report) {
		reportMapper.delete(report);
	}
	
	// submit this when a user stops viewing a report
	public void closeReport(Report report) {
		//TODO: need to send this to tell the lock to release
		//  the resource
	}
}
