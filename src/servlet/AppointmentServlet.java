package servlet;

import dies.models.*;
import dies.services.AppointmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet(urlPatterns = "/appointment")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentService as = new AppointmentService();
	private ServletDetails sd = new ServletDetails();
	private String mode = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppointmentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		mode = request.getParameter("mode");
		if (mode.equalsIgnoreCase("view") || mode.equalsIgnoreCase("edit")) {
			int appointment_id = sd.getAppointmentId(request);
			request.setAttribute("appointment", as.findAppointment(appointment_id));
			request.setAttribute("mode", request.getParameter("mode"));
			getServletContext().getRequestDispatcher("/appointment.jsp?appointmentid=" + appointment_id)
					.forward(request, response);

		} else if (mode.equalsIgnoreCase("delete")) {
			as.finishDeleteAppointment(new Appointment(sd.getAppointmentId(request), null, null, null, null, null));
			getServletContext().getRequestDispatcher("/home").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		mode = request.getParameter("mode");
		if (mode.equalsIgnoreCase("update")) {
			Patient patient = sd.getPatientDetails(request, sd.getAddressDetails(request));
			Technician technician = sd.getTechnicianDetails(request, "create");
			Appointment appointment = sd.getAppointmentDetails(request, patient, technician, null);
			as.finishUpdatePatient(patient);
			as.finishEditAppointment(appointment);
			response.sendRedirect("appointment?appointmentid=" + request.getParameter("appointmentid") + "&mode=view");

		} else if (mode.equalsIgnoreCase("create")) {
			Patient patient = sd.getPatientDetails(request, sd.getAddressDetails(request));
			Technician technician = sd.getTechnicianDetails(request, "create");
			// TODO Add machine list
			Appointment appointment = sd.getAppointmentDetails(request, patient, technician, null);
			as.finishUpdatePatient(patient);
			as.finishCreateAppointment(appointment);
			getServletContext().getRequestDispatcher("/home").forward(request, response);
		} else if (request.getParameter("back") != null) {
			getServletContext().getRequestDispatcher("/home").forward(request, response);
		}
	}
}
