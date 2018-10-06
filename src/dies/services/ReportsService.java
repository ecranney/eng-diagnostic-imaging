/**
 * Class for providing Reports services to the presentation layer.
 */

package dies.services;

import java.util.*;

import dies.models.*;
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
	
	public Report findReport(int id) {
		return reportMapper.find(id);
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
