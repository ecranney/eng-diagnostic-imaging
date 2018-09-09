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
		
		String patientUnitNo = request.getParameter("patient_unit_no");
		String patientStreetNo = request.getParameter("patient_street_no");
		String patientStreetName = request.getParameter("patient_street_name");
		String patientCity = request.getParameter("patient_city");
		String patientState = request.getParameter("patient_state");
		String patientPostCode = request.getParameter("patient_post_code");
		String patientId = request.getParameter("patient_id");
		String patientFirstName = request.getParameter("patient_first_name");
		String patientLastName = request.getParameter("patient_last_name");
		String patientMobile = request.getParameter("patient_mobile");
		String patientMedicareNo = request.getParameter("patient_medicate_no");
		String technicianId = request.getParameter("tech_id");
		String technicianFirstName = request.getParameter("patient_first_name");
		String technicianLastName = request.getParameter("patient_last_name");
		List<Machine> machines = new ArrayList<Machine>();
		
		String machineId = request.getParameter("machineId");
		String machineSerialCode = request.getParameter("machineSerialCode");
		String machineType = request.getParameter("machineType");
	
	
		int app_id = 0;
		LocalDateTime app_date = null;
		Appointment.State app_state = null;

		Appointment app = null;
		Address patientAddress = null;
		Patient patient = null;
		Technician technician = null;
		Machine machine = null;
		
	}

}
