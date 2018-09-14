package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dies.models.Appointment;
import dies.services.AppointmentService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentService appointmentService = new AppointmentService();
	List<Appointment> appointmentList = appointmentService.findAllAppointments();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.setAttribute("appointmentList", appointmentList);
		
		if (session != null && session.getAttribute("userid") != null) {
			request.getSession(true);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/appointments.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/invalid_login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.setAttribute("appointmentList", appointmentList);
		
		if (session != null && session.getAttribute("userid") != null) {
			request.getSession(true);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/appointments.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/invalid_login.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
