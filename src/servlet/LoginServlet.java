package servlet;

import dies.models.User;
import dies.services.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    LoginService loginService = new LoginService();
    private User user = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);  
    	
        if (session != null && session.getAttribute("userid") != null) {
            request.getSession(true);               
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
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
        System.out.println(username+ " " + password);
        user = loginService.login(username, password);
        
        if (user != null) {
        	HttpSession session = request.getSession(true);   
        	setSessionDetails(request, user, session);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private HttpSession setSessionDetails(HttpServletRequest request, User user, HttpSession session) {
        session.setAttribute("userid", user.getId());   
        session.setAttribute("username", user.getUsername());   
        session.setAttribute("firstname", user.getFirstName());  
        session.setAttribute("lastname", user.getLastName());
		return session;  
    }

}
