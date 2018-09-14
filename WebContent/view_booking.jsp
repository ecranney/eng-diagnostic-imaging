<%@page import="dies.models.Machine"%>
<%@page import="dies.models.Appointment.State"%>
<%@page import="java.util.List"%>
<%@page import="dies.models.Appointment"%>
<%@page import="dies.services.AppointmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking View</title>
<link href="resources/styles/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.min.js"></script>

<script type="text/javascript"
	src="resources/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<link href="resources/styles/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

</head>
</head>

<body>
	<div class="container ">
		<div>
			<form action="appointment" method="post">
				<h4>
					<input type="home" name="get"
						class="btn btn-primary btn-sm btn-outline-dark" value="Back">
				</h4>
			</form>

			<form action="appointment" method="post">
				<table class="table table-striped custab">
					<tr>
						<td>Appointment Date and Time</td>
						<td><input size="16" type="text"
						disabled="disabled"
							value="<c:out value="${appointment.getDate()}"/>"
							name="appointmentDateTime" readonly
							class="form-control form_datetime" required>
					<tr>
						<td>Appointment Status</td>
						<td><select disabled="disabled" name="appointmentStatus" class="form-control"
							required>

								<%
									for (State state : State.values()) {
								%>

								<option value=<%=state.name()%>><%=state.name()%></option>
								<%
									}
								%>
						</select>
					</tr>

					<tr>
						<td>Examination Type</td>
						<td><select disabled="disabled" name="machineType" class="form-control" required>
								<%
									for (Machine.Type machine : Machine.Type.values()) {
								%>
								<option value=<%=machine.name()%>><%=machine.name()%></option>
								<%
									}
								%>
						</select>
					</tr>
					<tr>
						<td>Patient First Name</td>
						<td><input disabled="disabled" id="patientFirstName" type="text"
							class="form-control" name="patientFirstName"
							value="<c:out value="${appointment.getPatient().getFirstName()}"/>"
							required></td>
					</tr>

					<tr>
						<td>Patient Last Name</td>
						<td><input disabled="disabled" id="patientLastName" type="text"
							value="<c:out value="${appointment.getPatient().getLastName()}"/>"
							class="form-control" name="patientLastName" required></td>
					</tr>

					<tr>
						<td>Patient Address</td>
						<td>
							<div class="form-group gaddress f12 " data-fid="f12">
								<input disabled="disabled" type="number" class="form-control" name="patientUnitNo"
									placeholder="Unit no" data-bind="value:replyNumber"
									value="<c:out value="${appointment.getPatient().getAddress().getUnitNo()}"/>"
									required> <input disabled="disabled" type="text"
									class="form-control gaddress-autocomplete"
									data-gaddress-types="street_number route"
									data-gaddress-name="long_name" id="f12_addressLine1"
									name="patientStreet"
									aria-describedby="f12_addressLine1-help-block"
									placeholder="1234 Main St."
									disabled="disabled" 
									value="<c:out value="${appointment.getPatient().getAddress().getStreetName()}"/>"
									required /> <input type="text" class="form-control"
									data-gaddress-types="locality" data-gaddress-name="long_name"
									id="f12_city" name="patientCity" 
									aria-describedby="f12_city-help-block" placeholder="City"
									disabled="disabled"
									value="<c:out value="${appointment.getPatient().getAddress().getCity()}"/>"
									required /> <input type="text" class="form-control"
									data-gaddress-types="administrative_area_level_1"
									data-gaddress-name="long_name" id="f12_state"
									name="patientState" 
									aria-describedby="f12_state-help-block"
									placeholder="State / Province / Region"
									disabled="disabled"
									value="<c:out value="${appointment.getPatient().getAddress().getState()}"/>"
									required /> <input type="number" class="form-control"
									data-gaddress-types="postal_code"
									data-gaddress-name="patientUPostalCode" id="f12_postalCode"
									name="patientPostalCode" 
									aria-describedby="f12_postalCode-help-block"
									placeholder="Postal / Zip Code" data-bind="value:replyNumber"
									disabled="disabled"
									value="<c:out value="${appointment.getPatient().getAddress().getPostCode()}"/>"
									required />
							</div>
						</td>
					<tr>
					<tr>
						<td>Patient Medicare No</td>
						<td><input type="text" disabled="disabled" class="form-control"
							value="<c:out value="${appointment.getPatient().getMedicareNo()}"/>"
							name="patientMedicareNo" required></td>
					</tr>
					<tr>
						<td>Patient Phone Number</td>
						<td><input type="number" disabled="disabled" class="form-control"
							value="<c:out value="${appointment.getPatient().getPhone()}"/>"
							name="patientMobile" data-bind="value:replyNumber" required></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(".form_datetime").datetimepicker({
			format : 'yyyy-mm-dd hh:ii'
		});
	</script>
	<script src="resources/js/jquery.validate.min.js"
		type="text/javascript"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDtXpn6gUreNd7lbpKUKPEgt6oXmVl5BSo&libraries=places"></script>
	<script src="resources/js/address.js" type="text/javascript"></script>
	<script src="resources/js/validator.min.js" type="text/javascript"></script>

</body>
</html>