<%@page import="dies.models.Appointment"%>
<%@page import="dies.services.AppointmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking View</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
</head>
<body>
	<%
		AppointmentService appointmentService = new AppointmentService();
		int app_id = Integer.parseInt(request.getParameter("appointmentid"));

		Appointment app = appointmentService.findAppointment(app_id);
	%>
	<div class="container">
		<div>
			<table class="table table-striped custab">
				<tr>
					<td>Appointment Date</td>
					<td><%=app.getDate()%></td>

				</tr>
				<tr>
					<td>Appointment Reference</td>
					<td class="info-highlight bottom-border"><%=app.getId()%></td>
				</tr>
				
				

				<tr>
					<td>First Name</td>
					<td><%=app.getPatient().getFirstName()%></td>

				</tr>
				<tr>
					<td>Last Name</td>
					<td><%=app.getPatient().getLastName()%></td>

				</tr>
				<tr>
					<td>Address</td>
					<td><%=app.getPatient().getAddress().getUnitNo()%>, <%=app.getPatient().getAddress().getStreetNo()%>,
						<%=app.getPatient().getAddress().getStreetName()%>, <%=app.getPatient().getAddress().getCity()%>,
						<%=app.getPatient().getAddress().getState()%> <%=app.getPatient().getAddress().getPostCode()%>
					</td>

				</tr>
				<tr>
					<td>Medicare No</td>
					<td><%=app.getPatient().getMedicareNo()%></td>

				</tr>
				<tr>
					<td>Phone Number</td>
					<td><%=app.getPatient().getPhone()%></td>

				</tr>
				<tr>
					<td>Issuer</td>
					<td><%=app.getTechnician().getFirstName()%></td>

				</tr>
			</table>
		</div>
	</div>
</body>
</html>