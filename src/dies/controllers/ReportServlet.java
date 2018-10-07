package dies.controllers;

import dies.auth.LoginSession;
import dies.models.Appointment;
import dies.models.Image;
import dies.models.Machine;
import dies.models.Radiologist;
import dies.models.Report;
import dies.services.AppointmentService;
import dies.services.ReportsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
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
        } else if (mode.equalsIgnoreCase("view") || mode.equalsIgnoreCase("edit") || mode.equalsIgnoreCase("review") ) {
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
            getServletContext().getRequestDispatcher("/report.jsp?appointmentid=" + appointment_id)
                    .forward(request, response);
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
        	Cookie[] cookies = request.getCookies();

        	if (cookies != null) {
        	 for (Cookie cookie : cookies) {
        	   if (cookie.getName().equals("patient_draft_report_" + request.getParameter("appointmentid"))) {
        		   cookie.setPath("/");
        		   cookie.setValue("");
        		   cookie.setMaxAge(0);
        		   response.addCookie(cookie);
        	    }
        	  }
        	}
        	
        	ReportsService rs = new ReportsService();
        	ServletParam sd = new ServletParam();
        	Radiologist author = new Radiologist(LoginSession.getUser().getId(), null, null, null, null, null, null, null);
        	String trimmedReport = sd.getPatientReport(request);
        	int reportId = Integer.valueOf(Integer.parseInt(request.getParameter("reportid")));
        	LocalDateTime date = LocalDateTime.now();
        	Report report = new Report(reportId, author, null, null, trimmedReport, date, date, Report.State.AWAITING_REVIEW );
            System.out.println(" UPDATING THE REPORT");
            rs.submitReport(report);            
            response.sendRedirect("report?appointmentid=" + request.getParameter("appointmentid") + "&mode=view");

        } else if (mode.equalsIgnoreCase("reject")) {
        	ReportsService rs = new ReportsService();
        	int reportId = Integer.valueOf(Integer.parseInt(request.getParameter("reportid")));
        	Radiologist reviewer = new Radiologist(LoginSession.getUser().getId(), null, null, null, null, null, null, null);
        	Report report = new Report(reportId, null, reviewer, null, null, null, null, Report.State.REVIEW_FAILED );
        	rs.submitReview(report);
        	response.sendRedirect("report?appointmentid=" + request.getParameter("appointmentid") + "&mode=review");
        	
        } else if (mode.equalsIgnoreCase("approve")) {
        	ReportsService rs = new ReportsService();
        	int reportId = Integer.valueOf(Integer.parseInt(request.getParameter("reportid")));
        	Radiologist reviewer = new Radiologist(LoginSession.getUser().getId(), null, null, null, null, null, null, null);
        	Report report = new Report(reportId, null, reviewer, null, null, null, null, Report.State.REVIEW_PASSED );
        	rs.submitReview(report);
        	response.sendRedirect("report?appointmentid=" + request.getParameter("appointmentid") + "&mode=review");
        	
        } else if (request.getParameter("back") != null) {
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }
    } 

	private void createDraftReportCookie(HttpServletRequest request, HttpServletResponse response) {
		ServletParam sd = new ServletParam();
		String trimmedPatientReport = sd.getPatientReport(request);
		Cookie appointmentReport = new Cookie("patient_draft_report_" + request.getParameter("appointmentid"), trimmedPatientReport);
		
		// Set expiry date after 24 Hrs for the cookie.
		appointmentReport.setMaxAge(60*60*24);
		
		response.addCookie(appointmentReport);
		response.setContentType("text/html");
	}

}
