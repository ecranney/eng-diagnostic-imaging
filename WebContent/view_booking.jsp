<%@page import="dies.models.Appointment"%>
<%@page import="dies.services.AppointmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking View</title>
<link href="resources/styles/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.min.js"></script>
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
			<form action="appointment" method="POST">
				<h4>
					<input type="submit" name="back"
						class="btn btn-primary btn-sm btn-outline-dark" value="Back">
				</h4>
			</form>
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
					<td>Appointment Status</td>
					<td class="info-highlight bottom-border"><%=app.getState()%></td>
				</tr>
				<tr>
					<td>Patient First Name</td>
					<td><%=app.getPatient().getFirstName()%></td>

				</tr>
				<tr>
					<td>Patient Last Name</td>
					<td><%=app.getPatient().getLastName()%></td>

				</tr>
				<tr>
					<td>Address</td>

					<td><c:choose>
							<c:when <%=app.getPatient().getAddress()%> !=null}">

							</c:when>
							<c:otherwise>
								<%=app.getPatient().getAddress().getUnitNo()%>,
                    			<%=app.getPatient().getAddress().getStreetNo()%>,
                    			<%=app.getPatient().getAddress().getStreetName()%>,
                    			<%=app.getPatient().getAddress().getCity()%>,
                    			<%=app.getPatient().getAddress().getState()%>,
								<%=app.getPatient().getAddress().getPostCode()%>
							</c:otherwise>
						</c:choose>
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
					<td><%=app.getTechnician().getFirstName() + " " + app.getTechnician().getLastName()%></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>