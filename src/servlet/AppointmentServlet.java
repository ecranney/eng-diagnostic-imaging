package servlet;

import dies.models.*;
import dies.models.Appointment.State;
import dies.services.AppointmentService;

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
@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppointmentServlet() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		if (request.getParameter("view") != null) {
			response.sendRedirect("view_booking.jsp?appointmentid=" + request.getParameter("appointmentid"));

		} else if (request.getParameter("delete") != null) {
			Appointment appointment = new Appointment(Integer.valueOf(request.getParameter("appointmentid")), null,
					null, null, null, null);

			AppointmentService as = new AppointmentService();
			as.finishDeleteAppointment(appointment);
			response.sendRedirect("booking.jsp");
		} else if (request.getParameter("edit") != null) {
			response.sendRedirect(
					"edit_booking.jsp?appointmentid=" + Integer.valueOf(request.getParameter("appointmentid")));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("update") != null) {

			//Integer appointmentId = Integer.valueOf(request.getParameter("appointmentid"));
			Integer patientAddressidId = Integer.valueOf(request.getParameter("patientaddressid"));

			Integer patientId = Integer.valueOf(request.getParameter("patientid"));
			String patientFirstName = request.getParameter("patientFirstName");
			String patientLastName = request.getParameter("patientLastName");
			String patientMobile = request.getParameter("patientMobile");
			String patientMedicareNo = request.getParameter("patientMedicareNo");

			Integer patientUnitNo = Integer.valueOf(request.getParameter("patienUnitNo"));
			Integer patientStreetNo = Integer.valueOf(request.getParameter("patientStreetNo"));
			String patientStreetName = request.getParameter("patientStreetName");
			String patientCity = request.getParameter("patientCity");
			String patientState = request.getParameter("patientState");
			Integer patientPostCode = Integer.valueOf(request.getParameter("patientPostalCode"));

			Address patientAddress = new Address(patientAddressidId, patientUnitNo, patientStreetNo, patientStreetName,
					patientCity, patientState, patientPostCode);
			Patient patient = new Patient(patientId, patientFirstName, patientLastName, patientAddress, patientMobile,
					patientMedicareNo);

			AppointmentService as = new AppointmentService();
			as.finishUpdatePatient(patient);

			response.sendRedirect("view_booking.jsp?appointmentid=" + request.getParameter("appointmentid"));

		} else if (request.getParameter("create") != null) {

			Date date = new Date();

			String appointmentDateTime = request.getParameter("appointmentDateTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			try {
				date = sdf.parse(appointmentDateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);

			String appointmentStatus = request.getParameter("appointmentStatus");
			State appointmentStatusCastType = State.valueOf(appointmentStatus);

			String machineType = request.getParameter("machineType");
			Machine.Type machineCastType = Machine.Type.valueOf(machineType);

			int technicianId = (int) request.getSession().getAttribute("userid");
			String technicianUsername = (String) request.getSession().getAttribute("username");
			String technicianFirstname = (String) request.getSession().getAttribute("firstname");
			String technicianLastName = (String) request.getSession().getAttribute("lastname");
			
			String patientFirstName = request.getParameter("patientFirstName");
			String patientLastName = request.getParameter("patientLastName");
			String patientMobile = request.getParameter("patientMobile");
			String patientMedicareNo = request.getParameter("patientMedicareNo");

			Integer patientUnitNo = Integer.parseInt(request.getParameter("patientUnitNo"));
			String patientStreet = request.getParameter("patientStreet");
			String patientCity = request.getParameter("patientCity");
			String patientState = request.getParameter("patientState");
			Integer patientPostCode = Integer.parseInt(request.getParameter("patientPostalCode"));

			// Creating each objects from form data
			Address patientAddress = new Address(0, patientUnitNo, 0, patientStreet, patientCity, patientState,
					patientPostCode);
			Patient patient = new Patient(0, patientFirstName, patientLastName, patientAddress, patientMobile,
					patientMedicareNo);

			Technician technician = new Technician(technicianId, technicianUsername, "", technicianFirstname, technicianLastName);
			
			// Updating the data in the server
			AppointmentService as = new AppointmentService();

			Machine machine = new Machine(0, 0, machineCastType);
			List<Machine> machines = new ArrayList<Machine>();
			machines.add(machine);
			
			as.finishCreatePatient(patient);
			Patient createdPatient = as.findPatient(patientMedicareNo);	
			System.out.println("Patient created successfully");
			Appointment appointment = new Appointment(0, ldt, createdPatient, technician, null, appointmentStatusCastType);			
			as.finishCreateAppointment(appointment);

			response.sendRedirect("booking.jsp");
		} else if (request.getParameter("back") != null) {
			response.sendRedirect("booking.jsp");
		}
	}

}
