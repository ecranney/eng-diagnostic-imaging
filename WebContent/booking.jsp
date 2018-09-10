<%@page import="dies.models.Appointment" %>
<%@page import="dies.services.AppointmentService" %>
<%@page import="java.util.List" %>
<%@ page import="dies.models.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Booking Summary</title>
    <link href="resources/styles/bootstrap.min.css"
          rel="stylesheet" id="bootstrap-css">
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div>
        <table class="table table-striped custab">
            <thead>
            <a href="create_booking.jsp"
               class="btn btn-primary btn-xs pull-right"><b>+</b>
                Add new appointment</a>
            <tr>
                <th>Date</th>
                <th>Patient Name</th>
                <th>Medicare No</th>
                <th>Status</th>
            </tr>
            </thead>
            <%
                AppointmentService appointmentService = new AppointmentService();
                List<Appointment> appointmentList = appointmentService.findAllAppointments();
                for (Appointment app : appointmentList) {
            %>
            <tr>
                <td><%=app.getDate()%>
                </td>
                <td><%=app.getPatient().getFirstName()%>
                </td>
                <td><%=app.getPatient().getMedicareNo()%>
                </td>
                <td><%=app.getState()%>
                </td>
                <td class="text-center">
                    <form action="view_booking.jsp" method="GET">
                        <input type="hidden" id="app_id" name="appointmentid"
                               value=<%=app.getId()%>> <input type="submit"
                                                              class="btn btn-primary"
                                                              value="Open">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</div>
</body>
</html>