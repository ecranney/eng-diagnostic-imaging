package dies.controllers;

import dies.models.Appointment;
import dies.services.AppointmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int RECORDS_PER_PAGE = 10;
    private AppointmentService appointmentService = new AppointmentService();
    private int page = 1;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        pagePagination(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        pagePagination(request, response);
    }

    private void pagePagination(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
		List<Appointment> appointmentList = appointmentService.findAllAppointments(RECORDS_PER_PAGE ,
				(page - 1) * RECORDS_PER_PAGE);

		int noOfRecords = appointmentService.countAllAppointments();
		int noOfPages = (int) Math.ceil((noOfRecords - 1) * 1.0 / RECORDS_PER_PAGE);

		request.setAttribute("appointmentList", appointmentList);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);

		getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
        
    }

}
