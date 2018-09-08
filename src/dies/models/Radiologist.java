package dies.models;

public class Radiologist extends User {

	private String registrationNo;
	
	public Radiologist(int id, String username, String password,
			String registrationNo) {
		super(id, username, password);
		this.registrationNo = registrationNo;
	}
	
	public String getRegistrationNo() {
		return registrationNo;
	}
	
}
