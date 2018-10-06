/**
 * Implementation of Remote Facade for collecting the bundle of
 * information required for viewing the reports.
 * 
 */

package dies.distribution;

import dies.mappers.*;
import dies.models.*;

import java.util.*;

public class ReportFacade {
	
	public ReportDTO getReportDTO(int appointmentId) {
		
		// find the appointment
		AppointmentMapper appointmentMapper = new AppointmentMapper();
		Appointment appointment = appointmentMapper.find(appointmentId);
		
		// find the report
		Report report = appointment.getReport();
		
		// find the author
		Radiologist author = report.getAuthor();
		
		// find the reviewer
		Radiologist reviewer = report.getReviewer();
		
		// find the patient
		Patient patient = appointment.getPatient();
		
		// find the set of images
		List<Image> images = appointment.getImages();
		
		// create the set of image DTOs
		List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
		for (Image image : images) {
			ImageDTO imageDTO = new ImageDTO(
				image.getId(),
				image.getURL()
			);
			imageDTOs.add(imageDTO);
		}
		
		// create the report DTO
		ReportDTO reportDTO = new ReportDTO(
			report.getId(),
			author.getFirstName() + " " + author.getLastName(),
			reviewer.getFirstName() + " " + reviewer.getLastName(),
			patient.getFirstName() + " " + patient.getLastName(),
			appointment.getDate(),
			report.getDateCreated(),
			report.getDateUpdated(),
			report.getContent(),
			report.getState(),
			imageDTOs
		);
		
		// return the report DTO
		return reportDTO;
	}
}
