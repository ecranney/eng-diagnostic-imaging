<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>DIES Dashboard</title>
        <mt:header/>
        <mt:favicon />
        <link rel="stylesheet" type="text/css"
              href="resources/styles/perfect-scrollbar/perfect-scrollbar.css">
        <link rel="stylesheet" type="text/css"
              href="resources/styles/table-util.css">
        <link rel="stylesheet" type="text/css"
              href="resources/styles/table-main.css">
    </head>
    <body>
    	<c:if test="${empty user}">
        	<c:redirect url="/login"/>
      	</c:if>
        <div>
            <nav class="navbar navbar-default navigation-clean-button">
                <div class="container">
                    <div class="navbar-header">
                        <a class="navbar-brand title-of-header" href="#">Diagnostic
                                                                         Imaging Enterprise System</a>
                        <button class="navbar-toggle collapsed"
                                data-toggle="collapse"
                                data-target="#navcol-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <div class="welcome-message navbar-nav mr-auto">
                        	<div>Hola! ${user.getFirstName()} ${user.getLastName()}</div>
                        	<c:if test="${user.getGroup() == 'RECEPTIONIST'}">
                        	     <div class="welcome-message-buttons">
                        	        <a href="appointment?mode=create"
                                    class="header100-header-btn header100-form-add-appointment-btn"><b>+</b>
                                    Create Appointment</a></div>
                                 <div class="welcome-message-buttons">
                                    <a href="patient?mode=create"
                                    class="header100-header-btn header100-form-add-appointment-btn"><b>+</b>
                                    Patient Registration</a></div>
                            </c:if>                          
                        </div>
                    </div>
                    <form action="logout" method="post">
                        <button type="submit" name="logout"
                                class="header100-header-btn header100-form-logout-btn"
                                value="logout">
                            <i class="fa fa-sign-out"></i> Logout
                        </button>
                    </form>
                </div>
            </nav>
        </div>
        <div class="limiter">
            <div class="container-table100">
                <div class="wrap-table100">
                    <div>
                        <input class="tableSearchInputField" type="text"
                               id="patientNameInput"
                               onkeyup="nameFindFunction()"
                               placeholder="Search for names.."
                               title="Type in a name">
                    </div>
                    <div class="table100">
                        <table id="patientsTable">
                            <thead>
                                <tr class="table100-head">
                                    <th class="column1 th-sm">Date<i class="fa fa-sort float-right" aria-hidden="true"></i></th>
                                    <th class="column2 th-sm">Patient Name<i class="fa fa-sort float-right" aria-hidden="true"></i></th>
                                    <th class="column3 th-sm">
                                    		<c:choose>
												<c:when test="${user.getGroup() == 'RADIOLOGIST'}">
											        Author
												</c:when>
												<c:otherwise>
											        Medicare No
												</c:otherwise>
											</c:choose> 
                                    <i class="fa fa-sort float-right" aria-hidden="true"></i></th>
                                    <c:choose>
												<c:when test="${user.getGroup() == 'RADIOLOGIST'}">
													<th class="column3 th-sm">
											        	Reviewer<i class="fa fa-sort float-right" aria-hidden="true"></i>
											         </th>
												</c:when>
									</c:choose> 
                                    <th class="column4 th-sm">Status<i class="fa fa-sort float-right" aria-hidden="true"></i></th>
                                    <th class="column5 th-sm"></th>
                                    <th class="column6 th-sm"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="appointment"
                                           items="${appointmentList}">
                                    <tr>
                                        <td class="column1"><c:out
                                                value="${appointment.getDate()}"/></td>
                                        <td class="column2"> <c:out
                                                value="${appointment.getPatient().getFirstName()} ${appointment.getPatient().getLastName()}"/></td>
                                        <td class="column3">
                                        	<c:choose>
												<c:when test="${user.getGroup() == 'RADIOLOGIST'}">
											        <c:out value="${appointment.getReport().getAuthor().getFirstName()}"/>
												</c:when>
												<c:otherwise>
											        <c:out value="${appointment.getPatient().getMedicareNo()}"/>
												</c:otherwise>
											</c:choose> 
                                        </td>
                                        <c:choose>
												<c:when test="${user.getGroup() == 'RADIOLOGIST'}">
													<td class="column3">
											        	<c:out value="${appointment.getReport().getReviewer().getFirstName()}"/>
											         </td>
												</c:when>
										</c:choose> 
                                        <td class="column4">
                                        	<c:choose>
												<c:when test="${user.getGroup() == 'RADIOLOGIST'}">
											        <c:out value="${appointment.getReport().getState()}"/>
												</c:when>
												<c:otherwise>
											        <c:out value="${appointment.getState()}"/>
												</c:otherwise>
											</c:choose> 
										</td>
                                        <td class="column5">
                                       	<c:choose>
											<c:when test="${user.getGroup() == 'RADIOLOGIST'}">
												<form action="report"  method="GET">
	                                                <div class="container-login100-form-btn">
	                                                    <div class="hidden-item">
															<input type="hidden"
	                                                        	class="hidden-item"
	                                                            name="appointmentid"
	                                                            value=<c:out
	                                                            value="${appointment.getId()}"/>>
	                                                    </div>
															<button type="submit"
		                                                    	class="table100-form-btn table100-form-edit-btn"
		                                                        name="mode"
		                                                        value="view">
			                                                    <i class="fa fa-edit fa-fw"></i>View
		                                                   	</button>
	                                                </div>
                                            	</form>
                                            </c:when>
											<c:otherwise>
												<form action="appointment"  method="GET">
	                                            	<div class="container-login100-form-btn">
	                                                    <div class="hidden-item">
															<input type="hidden"
	                                                        	class="hidden-item"
	                                                            name="appointmentid"
	                                                            value=<c:out
	                                                            value="${appointment.getId()}"/>>
	                                                    </div>
															<button type="submit"
		                                                    	class="table100-form-btn table100-form-edit-btn"
		                                                        name="mode"
		                                                        value="view">
			                                                    <i class="fa fa-edit fa-fw"></i>View
		                                                   	</button>
	                                                </div>
                                            	</form>
											</c:otherwise>
										</c:choose>
                                        </td>
                                        <td class="column6">
											<c:if test="${user.getGroup() == 'RECEPTIONIST'}">
                                            <form action="appointment"
                                                  method="GET">
                                                  <div class="hidden-item">
	                                                        <input type="hidden"
	                                                               class="hidden-item"
	                                                               name="appointmentid"
	                                                               value=<c:out
	                                                                value="${appointment.getId()}"/>>
												</div>
                                                <div class="container-login100-form-btn">
                                                    
                                                        <button type="submit"
                                                                class="table100-form-btn table100-form-delete-btn alert alert-danger"
                                                                name="mode"
                                                                value="Delete"
                                                                onclick="return confirm('Are you sure you want to delete?')">
                                                            <i class="fa fa-trash"></i>
                                                        </button>
                                                </div>
                                            </form>
                                            </c:if>
                                            <c:if test="${user.getGroup() == 'RADIOLOGIST'}">
                                            <form action="report"
                                                  method="GET">
                                                <div class="hidden-item">
	                                                        <input type="hidden"
	                                                               class="hidden-item"
	                                                               name="appointmentid"
	                                                               value=<c:out
	                                                                value="${appointment.getId()}"/>>
											    </div>
                                                <div class="container-login100-form-btn">
                                                        <button type="submit"
		                                                    	class="table100-form-btn table100-form-review-btn"
		                                                        name="mode"
		                                                        value="review">
			                                                    <i class="fa fa-edit fa-fw"></i>Review
		                                                </button>
                                                </div>
                                            </form>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="pagination">
                            <c:if test="${currentPage != 1}">
                                <a href="home?page=${currentPage - 1}">Previous</a>
                            </c:if>
                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <a class="pcurrunt-pagination-number"> ${i} </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="pagination-numbers"
                                           href="home?page=${i}">${i}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${currentPage lt noOfPages}">
                                <a href="home?page=${currentPage + 1}">Next</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <mt:footer/>
        <script src="resources/js/popper.js"></script>
        <script src="resources/js/select2/select2.min.js"></script>
        <script>
            function nameFindFunction() {
                var input, filter, table, tr, td, i;
                input = document.getElementById("patientNameInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("patientsTable");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[1];
                    if (td) {
                        if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
        </script>
        <script>
            $(document).ready(function () {
                $('[data-toggle="popover"]').popover();
            });
        </script>
        <script type="text/javascript">
        $(document).ready(function () {
        	  $('#patientsTable').DataTable({
        	    "ordering": true // false to disable sorting (or any other option)
        	  });
        	  $('.dataTables_length').addClass('bs-select');
        });
        </script>
    </body>
</html>