<%@page import="dies.models.Machine"%>
<%@page import="dies.models.Appointment.State"%>
<%@page import="java.util.List"%>
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

<script type="text/javascript"
	src="resources/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<link href="resources/styles/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

</head>
</head>

<body>
	<div class="container ">
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
						<td>Appointment Date and Time</td>
						<td><input size="16" type="text" value=" " name="appointmentDateTime"
							readonly class="form-control form_datetime">
					<tr>
						<td>Appointment Status</td>
						<td><select name="appointmentStatus" class="form-control">

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
						<td><select name="machineType" class="form-control">
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
						<td><input type="text" class="form-control"
							name="patientFirstName"></td>
					</tr>
					<tr>
						<td>Patient Last Name</td>
						<td><input type="text" class="form-control"
							name="patientLastName"></td>
					</tr>

					<tr>
						<td>Patient Address</td>
						<td>
							<div class="form-group gaddress f12 " data-fid="f12">
								<input type="text" class="form-control" name="patientUnitNo"
									placeholder="Unit no"> <input type="text"
									class="form-control gaddress-autocomplete"
									data-gaddress-types="street_number route"
									data-gaddress-name="long_name" id="f12_addressLine1"
									name="patientStreet" value=""
									aria-describedby="f12_addressLine1-help-block"
									placeholder="1234 Main St." /> <input type="text"
									class="form-control" data-gaddress-types="locality"
									data-gaddress-name="long_name" id="f12_city" name="patientCity"
									value="" aria-describedby="f12_city-help-block"
									placeholder="City" /> <input type="text" class="form-control"
									data-gaddress-types="administrative_area_level_1"
									data-gaddress-name="long_name" id="f12_state"
									name="patientState" value=""
									aria-describedby="f12_state-help-block"
									placeholder="State / Province / Region" /> <input type="text"
									class="form-control" data-gaddress-types="postal_code"
									data-gaddress-name="patientUPostalCode" id="f12_postalCode"
									name="patientPostalCode" value=""
									aria-describedby="f12_postalCode-help-block"
									placeholder="Postal / Zip Code" />
							</div>
						</td>
					<tr>
					<tr>
						<td>Patient Medicare No</td>
						<td><input type="text" class="form-control"
							name="patientMedicareNo"></td>
					</tr>
					<tr>
						<td>Patient Phone Number</td>
						<td><input type="text" class="form-control"
							name="patientMobile"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" name="create"
							class="btn btn-primary" value="Create"></td>
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
	<script
		src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDtXpn6gUreNd7lbpKUKPEgt6oXmVl5BSo&libraries=places"></script>
	<script src="resources/js/address.js" type="text/javascript"></script>
	s
</body>
</html>