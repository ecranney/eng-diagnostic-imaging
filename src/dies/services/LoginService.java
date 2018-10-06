/**
 * Class for providing Login services to the presentation layer - this mainly
 * consists of checking that username corresponds to an actual user in
 * the database, and that the username-password matches.
 *
 * @author ecranney
 * @since September 2018
 */
package dies.services;

import dies.mappers.UserMapper;
import dies.models.User;

public class LoginService {

    // data mapper to access users tables
    private UserMapper userMapper;

    public LoginService() {
        userMapper = new UserMapper();
    }

    // login attempt using a given username and password, returns
    // null or a user object
    public User findByUsername(String username) {

        // fetch the user (or null) from the database
        User user = userMapper.find(username);
        return user;
    }
    
    public User findByUserId(int userid) {

        // fetch the user (or null) from the database
        User user = userMapper.find(userid);
        return user;
    }
    
    public String findPasswordHash(String username) {

        // fetch the user (or null) from the database
        String hash = userMapper.findHash(username);
        return hash;
    }

}
