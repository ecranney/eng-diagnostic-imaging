<%@page import="dies.models.Appointment"%>
<%@page import="dies.services.AppointmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking View</title>
</head>
<body>
	<table>
		<tbody>
			<%
				AppointmentService appointmentService = new AppointmentService();
				int app_id = Integer.parseInt(request.getParameter("appointmentid"));

				Appointment app = appointmentService.findAppointment(app_id);
			%>
			<tr>
				<td>app_id</td>
				<td>Appointment Date</td>
				<td><%=app.getDate()%></td>

			</tr>
			<tr>
				<td>Appointment Reference</td>
				<td><%=app.getId()%></td>
			</tr>
			<tr>
				<td>
					<p>Patient Details</p>
				</td>

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
		</tbody>
	</table>
</body>
</html>