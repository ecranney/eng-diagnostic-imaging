package dies.services;
import org.apache.catalina.User;

import dies.models.*;
import dies.mappers.*;

public class LoginService {
	
	public boolean login(String username, String password) {
		User user = UserMapper.find(username);
		if (user != null && user.getPassword() == password) {
			return true;
		}
		return false;
	}

}
