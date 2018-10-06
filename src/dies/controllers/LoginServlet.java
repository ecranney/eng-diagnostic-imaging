package dies.controllers;

import dies.auth.LoginSession;
import dies.models.User;
import dies.services.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

import java.io.IOException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    LoginService loginService = new LoginService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userid") != null) {
            request.getSession(false);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println(loginService.findPasswordHash(username) + " > PASSWORD HASH");
        String encodedPassword = passwordGenerator(username, password, loginService.findPasswordHash(username));
        
        UsernamePasswordToken token = new UsernamePasswordToken(username, encodedPassword);
        token.setRememberMe(true);
        
        // get the currently executing user:
        Subject currentUser = SecurityUtils.getSubject();
        String view = "/login.jsp";
        try {
			currentUser.login(token);
			view = "/home";
			User user = loginService.findByUsername(username);
			LoginSession.init(user);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} finally {
			ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
            requestDispatcher.forward(request, response);
		}
    }

	private String passwordGenerator(String username, String password, String passowrdHash) {
		int hashIterations = 10000;
        String algorithmName =  "SHA-512";  
        String salt1 = username;  
        String salt2 =  new  SecureRandomNumberGenerator().nextBytes().toHex();  
        if (passowrdHash != null) {
        	salt2 =  passowrdHash;
        }
        SimpleHash hash =  new  SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);  
        String encodedPassword = hash.toHex();
        return encodedPassword;
	}
}
