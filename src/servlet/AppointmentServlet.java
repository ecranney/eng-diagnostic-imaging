package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
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
		String patientStreetNo = request.getParameter("patientStreetNo");
		String patientStreetName = request.getParameter("patientStreetName");
		String patientCity = request.getParameter("patientCity");
		String patientState = request.getParameter("patientState");
		String patientPostCode = request.getParameter("patientPostalCode");
		
		String appointmentType = request.getParameter("appointmentType");
//		List<Machine> machines = new ArrayList<Machine>();
//		String machineId = request.getParameter("machineId");
//		String machineSerialCode = request.getParameter("machineSerialCode");
//		String machineType = request.getParameter("machineType");
		
		String technicianId = request.getParameter("technician");
//		String technicianFirstName = request.getParameter("technicianFirstName");
//		String technicianLastName = request.getParameter("technicianLastName");
		
	
	
		int app_id = 0;
		LocalDateTime app_date = null;
		Appointment.State app_state = null;

		Appointment app = null;
		Address patientAddress = null;
		Patient patient = null;
		Technician technician = null;
		Machine machine = null;
		
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
