<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="dies.models.Technician"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="dies.models.Appointment"%>
<%@page import="dies.services.AppointmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Booking</title>
<meta charset="utf-8">
<meta name="generator" content="jqueryform.com">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.min.css"
	rel="stylesheet">

<link href="vendor.css" rel="stylesheet">

</head>
<body>


	<!--  String patientUnitNo = request.getParameter("patientUnitNo");
		String patientStreetNo = request.getParameter("patient_street_no");
		String patientStreetName = request.getParameter("patient_street_name");
		String patientCity = request.getParameter("patient_city");
		String patientState = request.getParameter("patient_state");
		String patientPostCode = request.getParameter("patient_post_code");
		String patientId = request.getParameter("patient_id");
		String patientFirstName = request.getParameter("patient_first_name");
		String patientLastName = request.getParameter("patient_last_name");
		String patientMobile = request.getParameter("patient_mobile");
		String patientMedicareNo = request.getParameter("patient_medicate_no");
		String technicianId = request.getParameter("tech_id");
		String technicianFirstName = request.getParameter("patient_first_name");
		String technicianLastName = request.getParameter("patient_last_name");
		List<Machine> machines = new ArrayList<Machine>();
		
		String machineId = request.getParameter("machineId");
		String machineSerialCode = request.getParameter("machineSerialCode");
		String machineType = request.getParameter("machineType");-->

	<div class="container jf-form">
		<for action='' method='post'>

		<div class="form-group f6 " data-fid="f6">
			<label class="control-label" for="f6">Date</label>
			<div class="input-group date">
				<input type="text" class="form-control datepicker" id="f6"
					name="appointmentDate" value=""
					data-datepicker-format="DD, MM d, yyyy"
					data-datepicker-startDate="0d" /> <span
					class="input-group-addon right"><i
					class="glyphicon glyphicon-th"></i> </span>
			</div>
		</div>
		<div class="form-group f7 " data-fid="f7">
			<label class="control-label" for="f7">Time</label> <select
				class="form-control" id="f7" name="appointmentTime">
				<option value="">- Select -</option>
				<option value="Option 1">1.00 pm</option>
				<option value="Option 2">2.00 pm</option>
				<option value="Option 3">3.00 pm</option>
			</select>
		</div>
		<div class="form-group f5 " data-fid="f5">
			<label class="control-label" for="f5">First Name</label>
			<div class="input-group">
				<span class="input-group-addon left"><i
					class="glyphicon glyphicon-user"></i> </span> <input type="text"
					class="form-control" id="f5" name="patientFirstName" value="" />
			</div>
		</div>
		<div class="form-group f4 " data-fid="f4">
			<label class="control-label" for="f4">Last Name</label>

			<div class="input-group">
				<span class="input-group-addon left"><i
					class="glyphicon glyphicon-user"></i> </span> <input type="text"
					class="form-control" id="f4" name="patientLasttName" value="" />
			</div>
		</div>
		<div class="form-group f8 " data-fid="f8">
			<label class="control-label" for="f8">Phone</label>

			<div class="input-group">
				<span class="input-group-addon left"><i
					class="glyphicon glyphicon-earphone"></i> </span> <input type="tel"
					class="form-control" id="f8" name="phone" value=""
					placeholder="xxx-xxx-xxxx"
					data-rule-pattern="[0-9]{3,4}[ -.]*[0-9]{3,4}[ -.]*[0-9]{4}"
					data-msg-pattern="Invalid phone number" />
			</div>
		</div>
		<div class="form-group f10 " data-fid="f10">
			<label class="control-label" for="f10">Medicare No</label> <input
				type="text" class="form-control" id="f10" name="medicareNo" value="" />
		</div>
		<div class="form-group gaddress f12 " data-fid="f12">
			<label class="control-label">Address</label>

			<div class="form-group addressLine1">
				<label class="control-label sr-only" for="f12_addressLine1">Unit
					No</label> <input type="text" class="form-control gaddress-autocomplete"
					data-gaddress-types="street_number route"
					data-gaddress-name="long_name" id="f12_addressLine1"
					name="patientUnitNo" value=""
					aria-describedby="f12_addressLine1-help-block"
					placeholder="1234 Main St." />
				<p id="f12_addressLine1-help-block" class="description sr-only">Street
					address, P.O. box, company name, c/o</p>
			</div>
			<div class="form-group city">
				<label class="control-label sr-only" for="f12_city">City</label> <input
					type="text" class="form-control" data-gaddress-types="locality"
					data-gaddress-name="long_name" id="f12_city" name="patientCity"
					value="" aria-describedby="f12_city-help-block" placeholder="City" />
				<p id="f12_city-help-block" class="description sr-only">City</p>
			</div>

			<div class="form-group state">
				<label class="control-label sr-only" for="f12_state">State /
					Province / Region</label> <input type="text" class="form-control"
					data-gaddress-types="administrative_area_level_1"
					data-gaddress-name="long_name" id="f12_state" name="patientState"
					value="" aria-describedby="f12_state-help-block"
					placeholder="State / Province / Region" />
				<p id="f12_state-help-block" class="description sr-only">State /
					Province / Region</p>
			</div>
			<div class="form-group postalCode">
				<label class="control-label sr-only" for="f12_postalCode">Postal
					/ Zip Code</label> <input type="text" class="form-control"
					data-gaddress-types="postal_code"
					data-gaddress-name="patientUPostalCode" id="f12_postalCode"
					name="f12_postalCode" value=""
					aria-describedby="f12_postalCode-help-block"
					placeholder="Postal / Zip Code" />
				<p id="f12_postalCode-help-block" class="description sr-only">Postal
					/ Zip Code</p>
			</div>

		</div>
		<div class="form-group f14 " data-fid="f14">
			<label class="control-label" for="f14">Scan Type</label> <select
				class="form-control" id="f14" name="scanType">
				<option value="">- Select -</option>
				<option value="XRAY">XRAY</option>
				<option value="CAT">CAT</option>
				<option value="MRI">MRI</option>
			</select>
		</div>


		<div class="form-group f13 " data-fid="f13">
			<label class="control-label" for="f13">Technician</label> <select
				class="form-control" id="f13" name="f13">

				<option value="">- Select -</option>
				<%
					String valuee = "25/04/2013";
					String time1 = "2017-10-06T17:48:23.558";
					LocalDateTime localDateTime = LocalDateTime.parse(time1);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

					Date currentDate = new SimpleDateFormat("dd/MM/yyyy").parse(valuee);
					AppointmentService appointmentService = new AppointmentService();
					List<Technician> tList = appointmentService.findAvailableTechnicians(localDateTime);
					for (Technician t : tList) {
				%>
				<option value=<%=t.getId()%>><%=t.getFirstName()%></option>
				<%
					}
				%>
			</select>
		</div>

		<div class="form-group submit f0 " data-fid="f0"
			style="position: relative;">
			<label class="control-label sr-only" for="f0" style="display: block;">Submit
				Button</label>

			<div class="progress"
				style="display: none; z-index: -1; position: absolute;">
				<div class="progress-bar progress-bar-striped active"
					role="progressbar" style="width: 100%"></div>
			</div>
			<button type="submit" class="btn btn-primary btn-lg"
				style="z-index: 1;">Submit</button>
		</div>
		<div class="clearfix"></div>

		<div class="submit">
			<p class="error bg-warning" style="display: none;">Please check
				the required fields.</p>
		</div>
		<div class="clearfix"></div>
		</form>
	</div>
</body>
</html>
