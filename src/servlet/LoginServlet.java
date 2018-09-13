package servlet;

import dies.models.Appointment;
import dies.models.User;
import dies.services.AppointmentService;
import dies.services.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Login
 */
@WebServlet("/home")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginService loginService = new LoginService();
        User user = loginService.login(username, password);

        
        if (user != null) {
            HttpSession session = request.getSession(true);   
            session.setAttribute("userid", user.getId());   
            session.setAttribute("username", user.getUsername());   
            session.setAttribute("firstname", user.getFirstName());  
            session.setAttribute("lastname", user.getLastName());  
            
            AppointmentService appointmentService = new AppointmentService();
			List<Appointment> appointmentList = appointmentService.findAllAppointments();
			request.setAttribute("appointmentList", appointmentList);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/booking.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/invalid_login.jsp");
            dispatcher.forward(request, response);
        }
    }

}
