package dies.services;

import dies.mappers.UserMapper;
import dies.models.User;

public class LoginService {

    // data mapper required for login service
    private UserMapper userMapper;

    public LoginService() {
        userMapper = new UserMapper();
    }

    // attempt a user login using a given username and password
    public User login(String username, String password) {
        User user = null;
        user = userMapper.find(username, password);

        System.out.print(user.getFirstName() + " to check shailtha");
        // check that we have actually retrieved a user, and password matches
        if (user != null && !user.getPassword().equalsIgnoreCase(password)) {
            System.out.println(user.getUsername());
            return null;
        }
        return user;
    }

}
