package dies.controllers;

import dies.models.Appointment;
import dies.models.Image;
import dies.models.Machine;
import dies.models.Patient;
import dies.models.Technician;
import dies.services.AppointmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet(urlPatterns = "/report")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String mode = "";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        mode = request.getParameter("mode");

        if (mode.equalsIgnoreCase("savedraft")){
        	createDraftReportCookie(request, response);
        	
        } else if (mode.equalsIgnoreCase("autocomplete")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            AppointmentService as = new AppointmentService();
            LocalDateTime appointmentDate = LocalDateTime.parse(request.getParameter("appointmentdatetime"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            List<Machine> machines = as.findAvailableMachines(appointmentDate);
            for (Machine m : machines) {
                out.print(
                        "<option value=" + m.getType() + ">" + m.getType() + "</option>"
                );
            }
            
        } else if (mode.equalsIgnoreCase("create")) {
            ServletParam sd = new ServletParam();
            request.setAttribute("available_machines", sd.getAvailableMachines(null));
            request.setAttribute("appointment_states", Appointment.State.values());
            getServletContext().getRequestDispatcher("/appointment.jsp?mode=create")
                    .forward(request, response);
            
        } else if (mode.equalsIgnoreCase("view") || mode.equalsIgnoreCase("edit")) {
            AppointmentService as = new AppointmentService();
            ServletParam sd = new ServletParam();
            int appointment_id = sd.getAppointmentId(request);
            Appointment appointment = as.findAppointment(appointment_id);
            List<Machine> appointment_machines = appointment.getMachines();
            List<Image> appointment_images = appointment.getImages();
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
             for (Cookie cookie : cookies) {
               if (cookie.getName().equals("patient_draft_report_" + appointment_id)) {
            	   request.setAttribute("patient_draft_report", cookie.getValue());
                }
              }
            }
            
            request.setAttribute("available_images", appointment_images);
            request.setAttribute("available_machines", sd.getAvailableMachines(appointment_machines));
            request.setAttribute("appointment_machines", appointment.getMachines());
            request.setAttribute("appointment_states", Appointment.State.values());
            request.setAttribute("appointment", as.findAppointment(appointment_id));
            request.setAttribute("mode", request.getParameter("mode"));
            getServletContext().getRequestDispatcher("/appointment.jsp?appointmentid=" + appointment_id)
                    .forward(request, response);
            
        } else if (mode.equalsIgnoreCase("delete")) {
            AppointmentService as = new AppointmentService();
            ServletParam sd = new ServletParam();
            as.finishDeleteAppointment(new Appointment(sd.getAppointmentId(request), null, null, null, null, null, null));
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        mode = request.getParameter("mode");
        if (mode.equalsIgnoreCase("update")) {
        	createDraftReportCookie(request, response);
        	
            AppointmentService as = new AppointmentService();
            ServletParam sd = new ServletParam();
            Patient patient = sd.getPatientDetails(request, sd.getAddressDetails(request));
            Technician technician = sd.getTechnicianDetails(request, "create");
            List<Machine> appointment_machines = sd.getMachineDetails(request, "create");
            Appointment appointment = sd.getAppointmentDetails(request, patient, technician, appointment_machines, null);
            as.finishUpdatePatient(patient);
            as.finishEditAppointment(appointment);
            response.sendRedirect("appointment?appointmentid=" + request.getParameter("appointmentid") + "&mode=view");

        } else if (mode.equalsIgnoreCase("create")) {
            AppointmentService as = new AppointmentService();
            ServletParam sd = new ServletParam();
            Patient patient = sd.getPatientDetails(request, sd.getAddressDetails(request));
            Technician technician = sd.getTechnicianDetails(request, "create");
            List<Machine> appointment_machines = sd.getMachineDetails(request, "create");
            Appointment appointment = sd.getAppointmentDetails(request, patient, technician, appointment_machines, null);
            as.finishUpdatePatient(patient);
            as.finishCreateAppointment(appointment);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        } else if (request.getParameter("back") != null) {
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }
    }

	private void createDraftReportCookie(HttpServletRequest request, HttpServletResponse response) {
		String patientReport = request.getParameter("patientReport").replaceAll(" ", "_");
		Cookie appointmentReport = new Cookie("patient_draft_report_" + request.getParameter("appointmentid"), patientReport);
		
		// Set expiry date after 24 Hrs for the cookie.
		appointmentReport.setMaxAge(60*60*24);
		
		response.addCookie(appointmentReport);
		response.setContentType("text/html");
	}
}
