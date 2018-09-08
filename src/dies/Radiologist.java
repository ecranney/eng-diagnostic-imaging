package dies;

public class Radiologist extends User {

	private String registrationNo;
	
	public Radiologist(String username, String password,
			String registrationNo) {
		super(username, password);
		this.registrationNo = registrationNo;
	}
	
	public String getRegistrationNo() {
		return registrationNo;
	}
	
}
