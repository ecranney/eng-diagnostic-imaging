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
	<div class="container">
		<div>
			<form action="appointment" method="post">
				<table class="table table-striped custab">
					<tr>
						<td>Appointment Date</td>
						<td><input type="text" class="form-control"
							name="appointmentDate"></td>
					</tr>

					<tr>
						<td>Appointment Time</td>
						<td><input type="text" class="form-control"
							name="appointmentTime"></td>
					</tr>

					<tr>
						<td>Appointment Status</td>
						<td class="info-highlight bottom-border"></td>
					</tr>
					<tr>
						<td>Patient First Name</td>
						<td><input type="text" class="form-control"
							name="patientFirstName"></td>
					</tr>
					<tr>
						<td>Patient Last Name</td>
						<td><input type="text" class="form-control"
							name="patientLastName"></td>
					</tr>
					<tr>
						<td>Patient unit no</td>
						<td><input type="text" class="form-control"
							name="patienUnitNo"></td>
					</tr>

					<tr>
						<td>Patient street no</td>
						<td><input type="text" class="form-control"
							name="patientStreetNo"></td>
					</tr>

					<tr>
						<td>Patient Street name</td>

						<td><input type="text" class="form-control"
							name="patientStreetName"></td>
					</tr>

					<tr>
						<td>Patient city</td>
						<td><input type="text" class="form-control"
							name="patientCity"></td>
					</tr>

					<tr>
						<td>Patient state</td>
						<td><input type="text" class="form-control"
							name="patientState"></td>
					</tr>

					<tr>
						<td>Patient post code</td>
						<td><input type="text" class="form-control"
							name="patientPostalCode"></td>
					</tr>

					<tr>
						<td>Patient medicare No</td>
						<td><input type="text" class="form-control"
							name="patientMedicareNo"></td>
					</tr>
					<tr>
						<td>Patient phone Number</td>
						<td><input type="text" class="form-control"
							name="patientMobile"></td>
					</tr>
					<tr>
						<td>Issuer</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" name="update"
							class="btn btn-primary" value="Update"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>