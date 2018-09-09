	package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dies.mappers.UserMapper;
import dies.models.Address;
import dies.models.Appointment;
import dies.models.Machine;
import dies.models.Patient;
import dies.models.Technician;
import dies.models.User;
import dies.services.AppointmentService;
import dies.services.LoginService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/loginconfirm")
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
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = null;
		
	    LoginService lg = new LoginService();
		User x = lg.login(username, password);
		if (x!=null) {
			response.sendRedirect("booking.jsp");
		}else {
			response.sendRedirect("booking2.jsp");
		}
	}

}
