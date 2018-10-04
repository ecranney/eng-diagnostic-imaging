package dies.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import dies.models.User;

public class LoginSession {
	public static final String USER_ATTRIBUTE_NAME = "user";
	public static final String RECEPTIONIST_ROLE = "RECEPTIONIST";
	public static final String RADIOLOGITST_ROLE = "RADIOLOGIST";
	public static final String TECHNICIAN_ROLE = "TECHNICIAN";

	public static boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}

	public static boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}
	
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static void init(User user) {
		SecurityUtils.getSubject().getSession().setAttribute(USER_ATTRIBUTE_NAME, user);
	}

	public static User getUser() {
		return (User) SecurityUtils.getSubject().getSession().getAttribute(USER_ATTRIBUTE_NAME);
	}
}
