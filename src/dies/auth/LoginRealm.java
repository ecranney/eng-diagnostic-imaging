package dies.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import dies.models.Radiologist;
import dies.models.Receptionist;
import dies.models.Technician;
import dies.models.User;
import dies.services.LoginService;

import java.util.HashSet;
import java.util.Set;

public class LoginRealm extends JdbcRealm {
	LoginService loginService = new LoginService();
	
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // identify account to log to
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        final String username = userPassToken.getUsername();
        
        User user = loginService.findByUsername(username);
        String salt2 = "";
        
        if (user == null) {
            return null;
        } else {
        	salt2 = user.getHash();
        }
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(username + salt2), getName());
    }

    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roles = new HashSet<>();
        if (principals.isEmpty()) {
            return null;
        }

        int username = (Integer) principals.getPrimaryPrincipal();
        User user = loginService.findByUserId(username);

        if (user == null) {
            return null;
        }
        
        // add roles of the user according to its type
        if (user instanceof Receptionist) {
            roles.add(LoginSession.RECEPTIONIST_ROLE);
        } else if (user instanceof Radiologist) {
            roles.add(LoginSession.RADIOLOGITST_ROLE);
        } else if (user instanceof Technician) {
            roles.add(LoginSession.TECHNICIAN_ROLE);
        }
        return new SimpleAuthorizationInfo(roles);
    }
}