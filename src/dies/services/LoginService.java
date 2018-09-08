package dies.services;

import dies.models.*;
import dies.mappers.*;

public class LoginService {
	
	public boolean login(String username, String password) {
		User user = ((UserMapper) DataMapper.getMapper(User.class)).find(username);
		if (user != null && user.getPassword() == password) {
			return true;
		}
		return false;
	}

}
