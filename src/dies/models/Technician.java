package dies.models;

import dies.data.UnitOfWork;

public class Technician extends User {

	public Technician(int id, String username, String password) {
		super(id, username, password);
		UnitOfWork.registerClean(this);
	}
	
}
