/**
 * A Report written for an image by a radiologist.
 *
 * @author ecranney
 * @date October 2018
 */

package dies.models;

import java.time.LocalDateTime;

public class Report implements IDomainObject {
	
	// identity field, used for database lookup
	private final int id;
	
	// the author and reviewer of the report
	private Radiologist author;
	private Radiologist reviewer;
	
	// image that the report has been written for
	private Image image; 
	
	// the report content, implemented simply as text
	private String content;
	
	// dates created/last updated
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated ;
	
	// the current state of a report
	public enum State {
		INCOMPLETE, AWAITING_REVIEW, REVIEW_PASSED, REVIEW_FAILED
	}
	
	private State state;
	
	// constructor for when report is first created
	public Report(int id, Radiologist author, Image image,
			LocalDateTime dateCreated) {
		this.id = id;
		this.author = author;
		this.reviewer = null;
		this.image = image;
		this.content = "";
		this.dateCreated = dateCreated;
		this.dateUpdated = dateCreated;
		this.state = State.INCOMPLETE;
	}
	
	// constructor used to load an existing report
	public Report(int id, Radiologist author, Radiologist reviewer, 
			Image image, String content, LocalDateTime dateCreated,
			LocalDateTime dateUpdated, State state) {
		this.id = id;
		this.author = author;
		this.reviewer = reviewer;
		this.image = image;
		this.content = content;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}
	
	public Radiologist getAuthor() {
		return author;
	}
	
	public Radiologist getReviewer() {
		return reviewer;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	
	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}
	
	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	public State getState() {
		return state;
	}
	
	public void markIncomplete() {
		this.state = State.INCOMPLETE;
	}
	
	public void markCompelte() {
		this.state = State.INCOMPLETE;
	}
	
	public void markPassed() {
		this.state = State.REVIEW_PASSED;
	}
	
	public void markFailed() {
		this.state = State.REVIEW_FAILED;
	}
}
