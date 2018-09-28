<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>DIES Dashboard</title>
        <link rel="apple-touch-icon" sizes="57x57"
              href="resources/images/favicon/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="180x180"
              href="resources/images/favicon/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32"
              href="resources/images/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16"
              href="resources/images/favicon/favicon-16x16.png">
        <link rel="manifest" href="resources/images/favicon/site.webmanifest">
        <link rel="mask-icon"
              href="resources/images/favicon/safari-pinned-tab.svg">
        <meta name="msapplication-TileColor" content="#da532c">
        <meta name="theme-color" content="#ffffff">
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
              href="resources/styles/header-main.css">
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
                        <a class="navbar-brand title-of-header" href="#">Diagnostic
                                                                         Imaging Enterprise System</a>
                        <button class="navbar-toggle collapsed"
                                data-toggle="collapse"
                                data-target="#navcol-1">
                            <span class="sr-only">Toggle navigation</span><span
                                class="icon-bar"></span><span
                                class="icon-bar"></span><span
                                class="icon-bar"></span>
                        </button>
                        <div class="welcome-message">
                            Hola! ${firstname} ${lastname}
                            <c:if test="${group == 'STAFF'}">
	                            <a href="appointment.jsp?mode=create"
	                               class="header100-header-btn header100-form-add-appointment-btn"><b>+</b>
	                                Create Appointment</a> <a
	                                href="patient.jsp?mode=create"
	                                class="header100-header-btn header100-form-add-appointment-btn"><b>+</b>
	                            Patient Registration</a>
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
                                    <th class="column1">Date</th>
                                    <th class="column2">Patient Name</th>
                                    <th class="column3">Medicare No</th>
                                    <th class="column4">Status</th>
                                    <th class="column5"></th>
                                    <th class="column6"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="appointmentList"
                                           items="${appointmentList}">
                                    <tr>
                                        <td class="column1"><c:out
                                                value="${appointmentList.getDate()}"/></td>
                                        <td class="column2"><c:out
                                                value="${appointmentList.getPatient().getFirstName()} ${appointmentList.getPatient().getLastName()}"/></td>
                                        <td class="column3"><c:out
                                                value="${appointmentList.getPatient().getMedicareNo()}"/></td>
                                        <td class="column4"><c:out
                                                value="${appointmentList.getState()}"/></td>
                                        <td class="column5">
                                            <form action="appointment"
                                                  method="GET">
                                                <div class="container-login100-form-btn">
                                                    <div class="hidden-item">
                                                        <input type="hidden"
                                                               class="hidden-item"
                                                               name="appointmentid"
                                                               value=<c:out
                                                                value="${appointmentList.getId()}"/>>
                                                    </div>
                                                    <button type="submit"
                                                            class="table100-form-btn table100-form-edit-btn"
                                                            name="mode"
                                                            value="view">
                                                        <i class="fa fa-edit fa-fw"></i>View
                                                    </button>
                                                </div>
                                            </form>
                                        </td>
                                        <td class="column6">
                                            <form action="appointment"
                                                  method="GET">
                                                <div class="container-login100-form-btn">
                                                    <div class="hidden-item">
                                                        <input type="hidden"
                                                               class="hidden-item"
                                                               name="appointmentid"
                                                               value=<c:out
                                                                value="${appointmentList.getId()}"/>>
                                                    </div>
                                                    <c:if test="${group == 'STAFF'}">
	                                                    <button type="submit"
	                                                            class="table100-form-btn table100-form-delete-btn alert alert-danger"
	                                                            name="mode"
	                                                            value="Delete"
	                                                            onclick="return confirm('Are you sure you want to delete?')">
	                                                        <i class="fa fa-trash"></i>
	                                                    </button>
                                                    </c:if>
                                                </div>
                                            </form>
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
        <script src="resources/js/jquery.min.js"></script>
        <script src="resources/js/popper.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/select2/select2.min.js"></script>
    </body>
</html>