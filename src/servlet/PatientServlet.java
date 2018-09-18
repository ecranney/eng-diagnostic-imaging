package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dies.models.Address;
import dies.models.Appointment;
import dies.models.Machine;
import dies.models.Patient;
import dies.models.Technician;
import dies.models.Appointment.State;
import dies.services.AppointmentService;

/**
 * Servlet implementation class PatientServlet
 */
@WebServlet("/patients")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PatientServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("mode").equalsIgnoreCase("autocomplete")) {
			String medicareNo = request.getParameter("term");

			System.out.println(medicareNo + " THIS IS TO CHECK WHICH VLUE IS PASSING TO MEDICARE NO");
			AppointmentService as = new AppointmentService();
			ArrayList<Patient> patients = as.findPatient(medicareNo);
			for (Patient p : patients) {
				System.out.println("patient " + p.getMedicareNo());
			}
			response.setContentType("application/json");
			System.out.println(new JSONArray(patients) + " pringiting responsr ");
			PrintWriter out = response.getWriter();
			out.print(new JSONArray(patients));
			
		} else if (request.getParameter("mode").equalsIgnoreCase("view")
				|| request.getParameter("mode").equalsIgnoreCase("edit")) {
			AppointmentService appointmentService = new AppointmentService();
			int app_id = Integer.parseInt(request.getParameter("patienttid"));
			Appointment appointment = appointmentService.findAppointment(app_id);
			request.setAttribute("appointment", appointment);
			request.setAttribute("mode", request.getParameter("mode"));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
					"/patient.jsp?patientid=" + Integer.valueOf(request.getParameter("patienttid")));
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("mode").equalsIgnoreCase("update")) {
			Address patientAddress = getPatientAddressDetails(request, "update");
			Patient patient = getPatientDetails(request, patientAddress, "update");
			AppointmentService as = new AppointmentService();
			as.finishUpdatePatient(patient);
			response.sendRedirect("patients?patienttid=" + request.getParameter("patienttid") + "&mode=view");

		} else if (request.getParameter("mode").equalsIgnoreCase("create")) {
			// Creating each objects from form data
			Address patientAddress = getPatientAddressDetails(request, "create");
			Patient patient = getPatientDetails(request, patientAddress, "create");

			// Updating the data in the server
			AppointmentService as = new AppointmentService();

			as.finishCreatePatient(patient);
			System.out.println("Patient created successfully");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
			dispatcher.forward(request, response);

		} else if (request.getParameter("mode").equalsIgnoreCase("search")) {
			AppointmentService as = new AppointmentService();
			String medicareNo = request.getParameter("medicareNo");
			as.findPatient(medicareNo);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
			dispatcher.forward(request, response);
		}
	}

	private Patient getPatientDetails(HttpServletRequest request, Address patientAddress, String mode) {
		String patientFirstName = request.getParameter("patientFirstName");
		String patientLastName = request.getParameter("patientLastName");
		String patientMobile = request.getParameter("patientMobile");
		String patientMedicareNo = request.getParameter("patientMedicareNo");
		Integer patientId = 0;

		if (mode == "update") {
			patientId = Integer.valueOf(request.getParameter("patientid"));
		}
		return new Patient(patientId, patientFirstName, patientLastName, patientAddress, patientMobile,
				patientMedicareNo);
	}

	private Address getPatientAddressDetails(HttpServletRequest request, String mode) {
		Integer patientUnitNo = Integer.valueOf(request.getParameter("patientUnitNo"));
		Integer patientStreetNo = 0;
		String patientStreetName = request.getParameter("patientStreet");
		String patientCity = request.getParameter("patientCity");
		String patientState = request.getParameter("patientState");
		Integer patientPostCode = Integer.valueOf(request.getParameter("patientPostalCode"));
		Integer patientAddressidId = 0;

		if (mode == "update") {
			patientAddressidId = Integer.valueOf(request.getParameter("patientaddressid"));
		}
		return new Address(patientAddressidId, patientUnitNo, patientStreetNo, patientStreetName, patientCity,
				patientState, patientPostCode);
	}

	private Technician getTechnicianDetails(HttpServletRequest request, String mode) {
		int technicianId = (int) request.getSession().getAttribute("userid");
		String technicianUsername = (String) request.getSession().getAttribute("username");
		String technicianFirstname = (String) request.getSession().getAttribute("firstname");
		String technicianLastName = (String) request.getSession().getAttribute("lastname");

		return new Technician(technicianId, technicianUsername, "", technicianFirstname, technicianLastName);
	}

}
