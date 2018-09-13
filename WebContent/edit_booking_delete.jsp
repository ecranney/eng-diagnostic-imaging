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
			<form action="appointment" method="post">
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
						<td><input type="text" class="form-control"
							name="patientFirstName"
							value=<%=app.getPatient().getFirstName()%>></td>
					</tr>
					<tr>
						<td>Patient Last Name</td>
						<td><input type="text" class="form-control"
							name="patientLastName" value=<%=app.getPatient().getLastName()%>></td>
					</tr>
					<tr>
						<td>Patient unit no</td>

						<td><input type="text" class="form-control"
							name="patienUnitNo"
							value=<%=app.getPatient().getAddress().getUnitNo()%>></td>
					</tr>

					<tr>
						<td>Patient street no</td>
						<td><input type="text" class="form-control"
							name="patientStreetNo"
							value=<%=app.getPatient().getAddress().getStreetNo()%>>
					</tr>

					<tr>
						<td>Patient Street name</td>

						<td><input type="text" class="form-control"
							name="patientStreetName"
							value=<%=app.getPatient().getAddress().getStreetName()%>>
					</tr>

					<tr>
						<td>Patient city</td>
						<td><input type="text" class="form-control"
							name="patientCity"
							value=<%=app.getPatient().getAddress().getCity()%>>
					</tr>

					<tr>
						<td>Patient state</td>
						<td><input type="text" class="form-control"
							name="patientState"
							value=<%=app.getPatient().getAddress().getState()%>>
					</tr>

					<tr>
						<td>Patient post code</td>
						<td><input type="text" class="form-control"
							name="patientPostalCode"
							value=<%=app.getPatient().getAddress().getPostCode()%>>
					</tr>

					<tr>
						<td>Patient medicare No</td>
						<td><input type="text" class="form-control"
							name="patientMedicareNo"
							value=<%=app.getPatient().getMedicareNo()%>></td>
					</tr>
					<tr>
						<td>Patient phone Number</td>
						<td><input type="text" class="form-control"
							name="patientMobile" value=<%=app.getPatient().getPhone()%>></td>

					</tr>
					<tr>
						<td>Issuer</td>
						<td><%=app.getTechnician().getFirstName() + " " + app.getTechnician().getLastName()%></td>

					</tr>

					<tr>
						<td><input type="hidden" id="app_id" name="appointmentid"
							value=<%=app.getId()%>> <input type="hidden"
							id="patient_id" name="patientid"
							value=<%=app.getPatient().getId()%>> <input type="hidden"
							id="patient_address_id" name="patientaddressid"
							value=<%=app.getPatient().getId()%>></td>
						<td><input type="submit" name="update"
							class="btn btn-primary" value="Update"></td>

					</tr>

				</table>
			</form>
		</div>
	</div>
</body>
</html>