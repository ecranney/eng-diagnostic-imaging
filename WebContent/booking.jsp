<%@page import="dies.services.LoginService"%>
<%@page import="dies.models.Appointment"%>
<%@page import="dies.services.AppointmentService"%>
<%@page import="java.util.List"%>
<%@ page import="dies.models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking Summary</title>
<link href="resources/styles/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<form action="logout" method="GET">
			<h4>
				Hola!,
				<%=request.getSession().getAttribute("firstname")%>

				<input type="submit" name="logout" class="btn btn-primary btn-sm btn-outline-dark"
					value="Logout">
			</h4>
		</form>
		<form action="appointment" method="GET">
			<div>
				<a href="create_booking.jsp"
					class="btn btn-primary btn-xs pull-right"><b>+</b> Add new
					appointment</a>

				<table class="table table-striped custab">

					<thead>

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
						<td><%=app.getDate()%></td>
						<td><%=app.getPatient().getFirstName()%></td>
						<td><%=app.getPatient().getMedicareNo()%></td>
						<td><%=app.getState()%></td>
						<td class="text-center"><input type="hidden" id="app_id"
							name="appointmentid" value=<%=app.getId()%>> <input
							type="submit" class="btn btn-primary" name="view" value="View">
							<input type="submit" name="delete" class="btn btn-primary"
							value="Delete"> <input type="submit" name="edit"
							class="btn btn-primary" value="Edit"></td>
					</tr>
					<%
						}
					%>

				</table>

			</div>
		</form>
	</div>

</body>
</html>