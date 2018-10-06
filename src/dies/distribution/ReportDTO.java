package dies.distribution;

import dies.models.Report;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.*;
import java.util.*;

public class ReportDTO {
	
	// static method for encoding images
	public static void toXML(ReportDTO reportDTO, OutputStream outputStream) {
		XMLEncoder encoder = new XMLEncoder(outputStream);
		encoder.writeObject(reportDTO);
		encoder.close();
	}
	
	// static method for decoding images
	public static ReportDTO fromXML(InputStream inputStream) {
		XMLDecoder decoder = new XMLDecoder(inputStream);
		ReportDTO reportDTO = (ReportDTO) decoder.readObject();
		decoder.close();
		return reportDTO;
	}
	
	// report id
	private int id;
	
	// required details of the author/reviewer radiologists
	private String author;
	private String reviewer;
	
	// details of the patient
	private String patient;
	
	// required details of the appointment
	private LocalDateTime appointmentDate;
	
	// date of creation/update
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated;
	
	// state of the report
	private Report.State state;
	
	// report content
	private String content;
	
	// the images associated with the appointment
	private List<ImageDTO> images;
	
	public ReportDTO(int id, String author, String reviewer, String patient,
			LocalDateTime appointmentDate, LocalDateTime dateCreated,
			LocalDateTime dateUpdated, String content, Report.State state,
			List<ImageDTO> images) {
		this.id = id;
		this.author = author;
		this.reviewer = reviewer;
		this.appointmentDate = appointmentDate;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.state = state;
		this.content = content;
		this.images = images;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getReviewer() {
		return reviewer;
	}
	
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	
	public String getPatient() {
		return patient;
	}
	
	public void setPatient(String patient) {
		this.patient = patient;
	}
	
	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}
	
	public void setLocalDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}
	
	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	public Report.State getState() {
		return state;
	}
	
	public void setState(Report.State state) {
		this.state = state;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<ImageDTO> getImages() {
		return images;
	}
	
	public void addImage(ImageDTO image) {
		this.images.add(image);
	} 
}
