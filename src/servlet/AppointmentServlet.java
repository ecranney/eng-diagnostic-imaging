package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dies.models.Address;
import dies.models.Appointment;
import dies.models.Machine;
import dies.models.Machine.Type;
import dies.models.Patient;
import dies.models.Technician;
import dies.models.User;
import dies.services.AppointmentService;
import dies.services.LoginService;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String appointmentDate = request.getParameter("appointmentDate");
		String appointmentTime= request.getParameter("appointmentTime");
		
		//String patientId = request.getParameter("patientId");
		String patientFirstName = request.getParameter("patientFirstName");
		String patientLastName = request.getParameter("patientLastName");
		String patientMobile = request.getParameter("patientMobile");
		String patientMedicareNo = request.getParameter("patientMedicareNo");
		
		//String patientUnitNo = request.getParameter("patientUnitNo");
		Integer patientStreetNo = Integer.parseInt(request.getParameter("patientStreetNo"));
		String patientStreetName = request.getParameter("patientStreetName");
		String patientCity = request.getParameter("patientCity");
		String patientState = request.getParameter("patientState");
		Integer patientPostCode = Integer.parseInt(request.getParameter("patientPostalCode"));
		
		String appointmentType = request.getParameter("appointmentType");
//		String machineId = request.getParameter("machineId");
//		String machineSerialCode = request.getParameter("machineSerialCode");
//		String machineType = request.getParameter("machineType");
		
		int technicianId = Integer.parseInt(request.getParameter("technician"));
//		String technicianFirstName = request.getParameter("technicianFirstName");
//		String technicianLastName = request.getParameter("technicianLastName");
		
		LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		
		LocalDateTime app_date = date;
		Appointment.State app_state = Appointment.State.FUTURE;

		
		Address patientAddress = new Address(0, 0, patientStreetNo, patientStreetName, patientCity, patientState, patientPostCode);
		Patient patient = new Patient(0, patientFirstName, patientLastName, patientAddress, patientMobile, patientMedicareNo);
		
		Technician technician = new Technician(technicianId, "", "", "", "");
		Machine machine = new Machine(1, 0, Type.CAT);		
		List<Machine> machines = new ArrayList<Machine>();
		machines.add(machine);
		AppointmentService as = new AppointmentService();
		
		Appointment appointment = new Appointment(0, date, patient, technician, machines, app_state);
		as.finishCreatePatient(patient);
		as.finishCreateAppointment(appointment);
		
		
		response.sendRedirect("booking.jsp");
//	    LoginService lg = new LoginService();
//		User x = lg.login(username, password);
//		if (x!=null) {
//			response.sendRedirect("booking.jsp");
//		}else {
//			response.sendRedirect("booking2.jsp");
//		}
		
	}

}
