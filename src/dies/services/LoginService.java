/**
 * Class for providing Login services to the presentation layer - this mainly
 * consists of checking that username corresponds to an actual user in
 * the database, and that the username-password matches.
 * 
 * @author ecranney
 * @since September 2018
 * 
 */
package dies.services;

import dies.models.*;
import dies.mappers.*;

public class LoginService {
	
	// data mapper to access users tables
	private UserMapper userMapper;
	
	public LoginService() {
		userMapper = new UserMapper();
	}

	// login attempt using a given username and password, returns
	// null or a user object
	public User login(String username, String password) {
		
		// fetch the user (or null) from the database
		User user = userMapper.find(username, password);
		
		// check that password matches
		if (user != null && !user.getPassword().equalsIgnoreCase(password)) {
			System.out.println(user.getUsername());
			return null;
		}
		return user;
	}

}
