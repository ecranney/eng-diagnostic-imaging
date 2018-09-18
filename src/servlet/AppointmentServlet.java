package servlet;

import dies.models.*;
import dies.models.Appointment.State;
import dies.services.AppointmentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet(urlPatterns = "/appointment")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		System.out.println(request.getParameter("appointmentid") + " id during laoding");
		
		if (request.getParameter("mode").equalsIgnoreCase("view")
				|| request.getParameter("mode").equalsIgnoreCase("edit")) {
			AppointmentService appointmentService = new AppointmentService();
			int app_id = Integer.parseInt(request.getParameter("appointmentid"));
			Appointment appointment = appointmentService.findAppointment(app_id);
			request.setAttribute("appointment", appointment);
			request.setAttribute("mode", request.getParameter("mode"));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
					"/appointment.jsp?appointmentid=" + Integer.valueOf(request.getParameter("appointmentid")));
			dispatcher.forward(request, response);

		} else if (request.getParameter("mode").equalsIgnoreCase("delete")) {
			System.out.println(request.getParameter("appointmentid") + " requested appointment id");
			Appointment appointment = new Appointment(Integer.valueOf(request.getParameter("appointmentid")), null,
					null, null, null, null);
			AppointmentService as = new AppointmentService();
			as.finishDeleteAppointment(appointment);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
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
			Address patientAddress = getAddressDetails(request, "update");
			Patient patient = getPatientDetails(request, patientAddress, "update");
			AppointmentService as = new AppointmentService();
			as.finishUpdatePatient(patient);
			response.sendRedirect("appointment?appointmentid=" + request.getParameter("appointmentid") + "&mode=view");

		} else if (request.getParameter("mode").equalsIgnoreCase("create")) {
			Date date = new Date();
			String appointmentDateTime = request.getParameter("appointmentDateTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			try {
				date = sdf.parse(appointmentDateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
			
			// Creating each objects from form data
			Address patientAddress = getAddressDetails(request, "create");
			Patient patient = getPatientDetails(request, patientAddress, "create");
			Technician technician = getTechnicianDetails(request, "create");
			
			String appointmentStatus = request.getParameter("appointmentStatus");
			State appointmentStatusCastType = State.valueOf(appointmentStatus);
			
			String machineType = request.getParameter("machineType");
			Machine.Type machineCastType = Machine.Type.valueOf(machineType);
			

			// Updating the data in the server
			AppointmentService as = new AppointmentService();

			Machine machine = new Machine(0, 0, machineCastType);
			List<Machine> machines = new ArrayList<Machine>();
			machines.add(machine);

			as.finishCreatePatient(patient);
			//Patient createdPatient = as.findPatient(patient.getMedicareNo());
			System.out.println("Patient created successfully");
			//Appointment appointment = new Appointment(0, ldt, createdPatient, technician, null,
			//		appointmentStatusCastType);
			//as.finishCreateAppointment(appointment);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
			dispatcher.forward(request, response);
			
		} else if (request.getParameter("back") != null) {
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

	private Address getAddressDetails(HttpServletRequest request, String mode) {
		Integer patientUnitNo = Integer.valueOf(request.getParameter("patientUnitNo"));
		Integer patientStreetNo = 0;
		String patientStreetName = request.getParameter("patientStreet");
		String patientCity = request.getParameter("patientCity");
		String patientState = request.getParameter("patientState");
		Integer patientPostCode = Integer.valueOf(request.getParameter("patientPostalCode"));
		Integer patientAddressidId = 0;

		if (mode == "update") {
			patientAddressidId = Integer.valueOf(request.getParameter("patientAddressid"));
		}
		return new Address(patientAddressidId, patientUnitNo, patientStreetNo, patientStreetName, patientCity,
				patientState, patientPostCode);
	}
	
	private Technician getTechnicianDetails(HttpServletRequest request, String mode) {
		int technicianId = (int) request.getSession().getAttribute("userid");
		String technicianUsername = (String) request.getSession().getAttribute("username");
		String technicianFirstname = (String) request.getSession().getAttribute("firstname");
		String technicianLastName = (String) request.getSession().getAttribute("lastname");
		
		return new Technician(technicianId, technicianUsername, "", technicianFirstname,
				technicianLastName);
	}

}
