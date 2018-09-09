/**
 * 
 * A Patient registered in the DIES system. Patients are registered the first
 * time that they make a booking in the system. Note: Patients are not a user-
 * type for the system (there is no way for Patients to interface with DIES).
 * 
 * Note: Currently, patients can be uniquely identified by their Medicare
 * Number; this will need to be updated to accommodate people without a
 * Medicare Number (e.g. international students).
 * 
 * @author ecranney
 * @since 2018-09-09
 * 
 */

package dies.models;

import dies.data.UnitOfWork;

public class Patient implements IDomainObject  {

	// identity field, used for database lookup
	private final int id;
	
	private String firstName;
	private String lastName;
	private Address address;
	private String phone;
	private String medicareNo;
	
	public Patient(int id, String firstName, String lastName, Address address,
			String phone, String medicareNo) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.medicareNo = medicareNo;
		
		// add to UnitOfWork
		UnitOfWork.registerCreated(this);
	}
	
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getMedicareNo() {
		return medicareNo;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setMedicareNo(String medicareNo) {
		this.medicareNo = medicareNo;
	}
}
