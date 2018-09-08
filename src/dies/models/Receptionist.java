package dies.models;

import dies.data.UnitOfWork;

public class Receptionist extends User {

	public Receptionist(int id, String username, String password) {
		super(id, username, password);
		UnitOfWork.registerClean(this);
	}
	
}
