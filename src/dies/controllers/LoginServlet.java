package dies.controllers;

import dies.auth.LoginSession;
import dies.models.User;
import dies.services.LoginService;
import dies.util.PasswordUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
        String salt = "dies*";
        int iterations = 10000;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        byte[] hashedBytes = PasswordUtil.hashPassword(passwordChars, saltBytes, iterations, keyLength);
        String hashedPassword = Hex.encodeHexString(hashedBytes);     
        System.out.println(hashedPassword);
        
        UsernamePasswordToken token = new UsernamePasswordToken(username, hashedPassword);
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
}
