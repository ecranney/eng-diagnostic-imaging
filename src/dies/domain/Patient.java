package dies.domain;

public class Patient {

	private String firstName;
	private String lastName;
	private Address address;
	private String phone;
	private String medicareNo;
	
	public Patient(String firstName, String lastName, Address address,
			String phone, String identificationNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.medicareNo = medicareNo;
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
