<%@page import="java.util.List"%>
<%@page import="dies.models.Appointment"%>
<%@page import="dies.services.AppointmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking Summary</title>
</head>
<body>
	<table>
		<tr>
			<th>Date</th>
			<th>Patient Name</th>
			<th>Medicare No</th>
			<th>Status</th>
		</tr>
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

		</tr>

		<%
			}
		%>
	</table>
</body>
</html>