package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dies.models.Address;
import dies.models.Appointment;
import dies.models.Patient;
import dies.services.AppointmentService;

/**
 * Servlet implementation class PatientServlet
 */
@WebServlet("/patient")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final boolean AUTOCOMPLETE = true;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PatientServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println( " THIS IS TO CHECK WHICH VLUE IS PASSING TO MEDICARE NO");
		if (request.getParameter("mode").equalsIgnoreCase("autocomplete")) {
			String medicareNo = request.getParameter("term");
			
			System.out.println(medicareNo + " THIS IS TO CHECK WHICH VLUE IS PASSING TO MEDICARE NO");
			AppointmentService as = new AppointmentService();
			ArrayList<Patient> patients = as.findPatient(medicareNo, AUTOCOMPLETE);
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
			response.sendRedirect("patient?patienttid=" + request.getParameter("patienttid") + "&mode=view");

		} else if (request.getParameter("mode").equalsIgnoreCase("create")) {
			Map<String, String> errors = new HashMap<String, String>();

			String patientMedicareNo = request.getParameter("patientMedicareNo");
			AppointmentService as = new AppointmentService();
			
			if (as.findPatient(patientMedicareNo)) {
				errors.put("Error", "Patient medicare no is already registered");
			}

			// Creating each objects from form data
			Address patientAddress = getPatientAddressDetails(request, "create");
			Patient patient = getPatientDetails(request, patientAddress, "create");
			
			if (errors.isEmpty()) {
				
				// Updating the data in the server

				as.finishCreatePatient(patient);
				System.out.println("Patient created successfully");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("patient", patient);
				request.getRequestDispatcher("patient.jsp?mode=create").forward(request, response);
			}

		} else if (request.getParameter("mode").equalsIgnoreCase("search")) {
			AppointmentService as = new AppointmentService();
			String medicareNo = request.getParameter("medicareNo");
			as.findPatient(medicareNo, !AUTOCOMPLETE);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
			dispatcher.forward(request, response);
		}
	}

	private Patient getPatientDetails(HttpServletRequest request, Address patientAddress, String mode) {
		String patientFirstName = request.getParameter("patientFirstName");
		String patientLastName = request.getParameter("patientLastName");
		String patientMobile = request.getParameter("patientMobile");
		String patientMedicareNo = request.getParameter("patientMedicareNo");
		String patientEmail = request.getParameter("patientEmail");
		
		Integer patientId = 0;

		if (mode == "update") {
			patientId = Integer.valueOf(request.getParameter("patientid"));
		}
		return new Patient(patientId, patientFirstName, patientLastName, patientAddress, patientMobile,
				patientMedicareNo, patientEmail);
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

}
