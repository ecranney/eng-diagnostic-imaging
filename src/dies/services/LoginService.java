package dies.services;

import dies.models.*;

import java.sql.SQLException;

import dies.mappers.*;

public class LoginService {
	
	private UserMapper userMapper;
	
	public LoginService() {
		userMapper = new UserMapper();
	}

	
	public boolean login(String username, String password) {
		User user = null;
		
		try {
			user = userMapper.find(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(user.getUsername() + " this is the user name in loginservice");
		if (user != null && user.getPassword().equalsIgnoreCase(password)) {
			System.out.println(user.getUsername());
			return true;
		}
		return false;
	}

}
