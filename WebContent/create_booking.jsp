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
</head>
<body>
	<table>
		<tbody>
			
			<tr>
				<td>Select Date <input type="text" name="fdate"></td>
			</tr>
			<tr>
				<td>Select Time <input type="text" name="ftime"></td>
			</tr>
			<tr>
				<td>
					<p>Select Technician</p>
				</td>
				<td><select>
						<option value="a">A</option>
						<option value="b">B</option>
						<option value="c">C</option>
						<option value="d">D</option>
				</select></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" name=""></td>

			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name=""></td>

			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" name=""></td>

			</tr>
			<tr>
				<td>Sex</td>
				<td><input type="text" name=""></td>

			</tr>
			<tr>
				<td>Date of Birth</td>
				<td><input type="text" name=""></td>

			</tr>
			<tr>
				<td>Medicare No</td>
				<td><input type="text" name=""></td>

			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name=""></td>

			</tr>
			<tr>
				<td>Phone Number</td>
				<td><input type="text" name=""></td>

			</tr>
			<tr>
				<td>Issuer</td>
				<td><input type="text" name=""></td>

			</tr>
			
		</tbody>
	</table>
</body>
</html>