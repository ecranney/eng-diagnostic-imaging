package servlet;

import dies.models.*;
import dies.models.Machine.Type;
import dies.services.AppointmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());

        if (request.getParameter("view") != null) {
            response.sendRedirect("view_booking.jsp?appointmentid=" + request.getParameter("appointmentid"));

        } else if (request.getParameter("delete") != null) {
            Appointment appointment = new Appointment(Integer.valueOf(request.getParameter("appointmentid")), null, null, null, null, null);

            AppointmentService as = new AppointmentService();
            as.finishDeleteAppointment(appointment);
            response.sendRedirect("booking.jsp");
        } else if (request.getParameter("edit") != null) {
            Appointment appointment = new Appointment(Integer.valueOf(request.getParameter("appointmentid")), null, null, null, null, null);
            AppointmentService as = new AppointmentService();
            response.sendRedirect("edit_booking.jsp?appointmentid=" + Integer.valueOf(request.getParameter("appointmentid")));
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("update") != null) {

            Integer appointmentId = Integer.valueOf(request.getParameter("appointmentid"));
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

            Address patientAddress = new Address(patientAddressidId, patientUnitNo, patientStreetNo, patientStreetName, patientCity, patientState, patientPostCode);
            Patient patient = new Patient(patientId, patientFirstName, patientLastName, patientAddress, patientMobile, patientMedicareNo);

            AppointmentService as = new AppointmentService();
            as.finishUpdatePatient(patient);

            response.sendRedirect("view_booking.jsp?appointmentid=" + request.getParameter("appointmentid"));
            
        } else if (request.getParameter("create") != null) {
          //String appointmentDate = request.getParameter("appointmentDate");
          //String appointmentTime = request.getParameter("appointmentTime");
  
          String patientFirstName = request.getParameter("patientFirstName");
          String patientLastName = request.getParameter("patientLastName");
          String patientMobile = request.getParameter("patientMobile");
          String patientMedicareNo = request.getParameter("patientMedicareNo");
  
          //String patientUnitNo = request.getParameter("patientUnitNo");
          Integer patientUnitNo = Integer.parseInt(request.getParameter("patienUnitNo"));
          Integer patientStreetNo = Integer.parseInt(request.getParameter("patientStreetNo"));
          String patientStreetName = request.getParameter("patientStreetName");
          String patientCity = request.getParameter("patientCity");
          String patientState = request.getParameter("patientState");
          Integer patientPostCode = Integer.parseInt(request.getParameter("patientPostalCode"));
  
          String appointmentType = request.getParameter("appointmentType");
  
          int technicianId = Integer.parseInt(request.getParameter("technician"));
  
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
        } else if (request.getParameter("back") != null) {
        	response.sendRedirect("booking.jsp");
        }
    }

}
