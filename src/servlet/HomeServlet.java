package servlet;

import java.io.IOException;
import java.util.List;

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
	private int page = 1;
	private static final int RECORDS_PER_PAGE = 10;

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
		pagePagination(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pagePagination(request, response);
	}

	private void pagePagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		if (session != null && session.getAttribute("userid") != null) {
			List<Appointment> appointmentList = appointmentService.findAllAppointments(RECORDS_PER_PAGE + 1,
					(page - 1) * RECORDS_PER_PAGE);

			int noOfRecords = appointmentService.countAllAppointments();
			int noOfPages = (int) Math.ceil((noOfRecords - 1) * 1.0 / RECORDS_PER_PAGE);

			request.setAttribute("appointmentList", appointmentList);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);

			getServletContext().getRequestDispatcher("/appointments.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/login").forward(request, response);
		}
	}

}
