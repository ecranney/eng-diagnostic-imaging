package dies.models;

import dies.data.UnitOfWork;

public class Radiologist extends User {

	private String registrationNo;
	
	public Radiologist(int id, String username, String password,
			String registrationNo) {
		super(id, username, password);
		this.registrationNo = registrationNo;
		UnitOfWork.registerClean(this);
	}
	
	public String getRegistrationNo() {
		return registrationNo;
	}
	
}
