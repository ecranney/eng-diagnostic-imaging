<%@page import="dies.services.LoginService"%>
<%@page import="dies.models.Appointment"%>
<%@page import="dies.services.AppointmentService"%>
<%@page import="java.util.List"%>
<%@ page import="dies.models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking Summary</title>
<link href="resources/styles/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<form action="logout" method="GET">
			<h4>
				Hola!
				<%=request.getSession().getAttribute("firstname")%>

				<input type="submit" name="logout"
					class="btn btn-primary btn-sm btn-outline-dark" value="Logout">
			</h4>
		</form>
		
			<div>
				<a href="create_booking.jsp"
					class="btn btn-primary btn-xs pull-right"><b>+</b> Add new
					appointment</a>

			<table class="table table-striped custab">
				<thead>
					<tr>
						<th>Date</th>
						<th>Patient Name</th>
						<th>Medicare No</th>
						<th>Status</th>
					</tr>
				</thead>
				<c:forEach var="appointmentList" items="${appointmentList}">
					<tr>

						<td><c:out value="${appointmentList.getDate()}" /></td>
						<td><c:out
								value="${appointmentList.getPatient().getFirstName()} ${appointmentList.getPatient().getLastName()}" /></td>
						<td><c:out
								value="${appointmentList.getPatient().getMedicareNo()}" /></td>
						<td><c:out value="${appointmentList.getState()}" /></td>

						<td class="text-center">
							<form action="appointment" method="GET">
								<input type="hidden" name="appointmentid"
									value=<c:out value="${appointmentList.getId()}" />> <input
									type="submit" class="btn btn-primary" name="view" value="View">
								<input type="submit" name="delete" class="btn btn-primary"
									value="Delete"> <input type="submit" name="edit"
									class="btn btn-primary" value="Edit">
							</form>
						</td>

					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>