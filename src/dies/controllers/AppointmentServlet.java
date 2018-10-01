package dies.controllers;

import dies.models.Appointment;
import dies.models.Machine;
import dies.models.Patient;
import dies.models.Technician;
import dies.services.AppointmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(urlPatterns = "/appointment")
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String mode = "";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        mode = request.getParameter("mode");

        if (mode.equalsIgnoreCase("autocomplete")) {
        	response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
        	AppointmentService as = new AppointmentService();            
            LocalDateTime appointmentDate = LocalDateTime.parse(request.getParameter("appointmentdatetime"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            List<Machine> machines = as.findAvailableMachines(appointmentDate);           
            for(Machine m: machines) {
            	out.print(
            		
            			 "<option value="+ m.getType() +">"+ m.getType() +"</option>"             
            	);
            }   
        } else if (mode.equalsIgnoreCase("create")) {
            ServletParam sd = new ServletParam();
            request.setAttribute("available_machines", sd.getAvailableMachines(null));
            getServletContext().getRequestDispatcher("/appointment.jsp?mode=create")
                    .forward(request, response);
        } else if (mode.equalsIgnoreCase("view") || mode.equalsIgnoreCase("edit")) {
            AppointmentService as = new AppointmentService();
            ServletParam sd = new ServletParam();
            int appointment_id = sd.getAppointmentId(request);
            Appointment appointment = as.findAppointment(appointment_id);
            List<Machine> appointment_machines = appointment.getMachines();

            request.setAttribute("available_machines", sd.getAvailableMachines(appointment_machines));
            request.setAttribute("appointment_machines", appointment.getMachines());

            request.setAttribute("appointment", as.findAppointment(appointment_id));
            request.setAttribute("mode", request.getParameter("mode"));
            getServletContext().getRequestDispatcher("/appointment.jsp?appointmentid=" + appointment_id)
                    .forward(request, response);
        } else if (mode.equalsIgnoreCase("delete")) {
            AppointmentService as = new AppointmentService();
            ServletParam sd = new ServletParam();
            as.finishDeleteAppointment(new Appointment(sd.getAppointmentId(request), null, null, null, null, null));
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
            AppointmentService as = new AppointmentService();
            ServletParam sd = new ServletParam();
            Patient patient = sd.getPatientDetails(request, sd.getAddressDetails(request));
            Technician technician = sd.getTechnicianDetails(request, "create");
            List<Machine> appointment_machines = sd.getMachineDetails(request, "create");
            Appointment appointment = sd.getAppointmentDetails(request, patient, technician, appointment_machines);
            as.finishUpdatePatient(patient);
            as.finishEditAppointment(appointment);
            response.sendRedirect("appointment?appointmentid=" + request.getParameter("appointmentid") + "&mode=view");

        } else if (mode.equalsIgnoreCase("create")) {
            AppointmentService as = new AppointmentService();
            ServletParam sd = new ServletParam();
            Patient patient = sd.getPatientDetails(request, sd.getAddressDetails(request));
            Technician technician = sd.getTechnicianDetails(request, "create");
            List<Machine> appointment_machines = sd.getMachineDetails(request, "create");
            Appointment appointment = sd.getAppointmentDetails(request, patient, technician, appointment_machines);
            as.finishUpdatePatient(patient);
            as.finishCreateAppointment(appointment);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        } else if (request.getParameter("back") != null) {
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }
    }
}
