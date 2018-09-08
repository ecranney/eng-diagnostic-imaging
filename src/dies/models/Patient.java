package dies.models;

import dies.data.UnitOfWork;

public class Patient extends DomainObject  {

	private final int id;
	
	private String firstName;
	private String lastName;
	private Address address;
	private String phone;
	private String medicareNo;
	
	public Patient(int id, String firstName, String lastName, Address address,
			String phone, String identificationNo) {
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
}
