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
<title>Dashboard</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/styles/bootstrap/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script src="resources/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/styles/table-util.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/table-main.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/select2/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/styles/basic-header.css">
</head>
<body>
	<div>
		<nav class="navbar navbar-default navigation-clean-button">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Diagnostic Imaging Enterprise
						System</a>
					<button class="navbar-toggle collapsed" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
					<a href="create_booking.jsp"
						class="btn btn-primary btn-xs pull-right"><b>+</b> Add new
						appointment</a>
				</div>

				<form action="logout" method="post">
					<h4>
						Hola!
						<%=request.getSession(false).getAttribute("firstname")%>

						<input type="submit" name="logout"
							class="btn btn-primary btn-sm btn-outline-dark" value="logout">
					</h4>
				</form>

				<div class="collapse navbar-collapse" id="navcol-1">
					<ul class="nav navbar-nav">
						<li class="active" role="presentation"><a href="#">First
								Item</a></li>
						<li role="presentation"><a href="#">Second Item</a></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" aria-expanded="false" href="#">Dropdown
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li role="presentation"><a href="#">First Item</a></li>
								<li role="presentation"><a href="#">Second Item</a></li>
								<li role="presentation"><a href="#">Third Item</a></li>
							</ul></li>
					</ul>
					<p class="navbar-text navbar-right actions">
						<a class="navbar-link login" href="#">Log In</a> <a
							class="btn btn-default action-button" role="button" href="#">Sign
							Up</a>
					</p>
				</div>
			</div>
		</nav>
	</div>
	<div class="limiter">

		<div class="container-table100">

			<div class="wrap-table100">
				<div class="table100">
					<table>
						<thead>
							<tr class="table100-head">
								<th class="column1">Date</th>
								<th class="column2">Patient Name</th>
								<th class="column3">Medicare No</th>
								<th class="column4">Status</th>
								<th class="column5"></th>
								<th class="column6"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="appointmentList" items="${appointmentList}">
								<tr>
									<td class="column1"><c:out
											value="${appointmentList.getDate()}" /></td>
									<td class="column2"><c:out
											value="${appointmentList.getPatient().getFirstName()} ${appointmentList.getPatient().getLastName()}" /></td>
									<td class="column3"><c:out
											value="${appointmentList.getPatient().getMedicareNo()}" /></td>
									<td class="column4"><c:out
											value="${appointmentList.getState()}" /></td>
									<td class="column5">
										<form action="appointment" method="GET">

											<div class="container-login100-form-btn">
												<input type="hidden" name="appointmentid"
													value=<c:out value="${appointmentList.getId()}" />>
												<button type="submit"
													class="table100-form-btn table100-form-edit-btn"
													name="edit" value="View">
													<i class="fa fa-edit fa-fw"></i>Edit
												</button>
											</div>
										</form>
									</td>
									<td class="column6">
										<form action="appointment" method="GET">
											<div class="container-login100-form-btn">
												<input type="hidden" name="appointmentid"
													value=<c:out value="${appointmentList.getId()}" />>
												<button type="submit"
													class="table100-form-btn table100-form-delete-btn"
													name="delete" value="Delete">
													<i class="fa fa-trash"></i>
												</button>
											</div>
										</form>
									</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/popper.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/select2/select2.min.js"></script>
</body>
</html>