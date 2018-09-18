<%@page import="dies.models.Appointment.State" %>
<%@page import="dies.models.Machine" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>DIES Booking View</title>

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
          href="resources/images/favicon/safari-pinned-tab.svg" color="#5bbad5">
    <meta name="msapplication-TileColor" content="#da532c">
    <meta name="theme-color" content="#ffffff">

    <link href="resources/styles/jquery-ui.css" rel="stylesheet">
    <link href="resources/styles/bootstrap/bootstrap.min.css"
          rel="stylesheet" id="bootstrap-css">
    <link
            href="resources/styles/datetimepicker/bootstrap-datetimepicker.min.css"
            rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/form-util.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/form-main.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/header-main.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/select2/select2.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/animsition/animsition.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/styles/animate/animate.css">
    <link rel="stylesheet" type="text/css"
          href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/styles/basic-header.css">
    <%--
    <link
        href="resources/styles/datetimepicker/bootstrap-datetimepicker.min.css"
        rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css"
        href="resources/styles/daterangepicker/daterangepicker.css">
    --%>
</head>
</head>

<body>
<div>
    <nav class="navbar navbar-default navigation-clean-button">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand title-of-header" href="#">Diagnostic
                    Imaging Enterprise System</a>
                <button class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#navcol-1">
                    <span class="sr-only">Toggle navigation</span><span
                        class="icon-bar"></span><span
                        class="icon-bar"></span><span
                        class="icon-bar"></span>
                </button>
            </div>
            <form action="home" method="post">
                <button type="submit" name="back"
                        class="header100-header-btn header100-form-logout-btn"
                        value="Back">
                    <i class="fa fa-arrow-circle-left"></i> Back
                </button>
            </form>
        </div>
    </nav>
</div>


<div class="container-contact100">

    <div class="wrap-contact100">

        <form action="appointment"
              method=
              <c:choose>
              <c:when test="${mode == 'view'}">
                      "get"
        </c:when>
        <c:otherwise>
            "post"
        </c:otherwise>

        </c:choose>
        class="contact100-form validate-form">


        <c:if test="${param.mode == 'create'}">
            <div class="tab">
                <span class="contact100-form-title"> Patient Information </span>

                <div class="wrap-input100 validate-input"
                     data-validate="Value is required">
                    <span class="label-input100">Search</span> <input
                    <c:if test="${(mode == 'view')}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
                        class="input100" type="text" id="searchValue"
                        placeholder="Enter patient's Medicare no. to search"
                        value=""
                        required> <span class="focus-input100"></span>
                </div>

            </div>
        </c:if>
        <div class="tab">
            <span class="contact100-form-title"> Appointment Form </span>

            <div class="wrap-input100 validate-input"
                 data-validate="Date is required">
                <span class="label-input100">Appointment Date and Time</span>
                <input
                <c:if test="${(mode == 'view')}">
                    <c:out value="disabled='disabled'"/>
                </c:if>
                        class="input100 form_datetime" type="text"
                        name="appointmentDateTime" placeholder="Enter a date"
                        value="<c:out value="${appointment.getDate()}"/>"
                        required>
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 input100-select">
                <span class="label-input100">Appointment Status</span>
                <div>
                    <select
                            <c:if test="${(mode == 'view')}"><c:out
                                    value="disabled='disabled'"/></c:if>
                            class="selection-2" name="appointmentStatus"
                            required>

                        <%
                            for (State state : State.values()) {
                        %>

                        <option value=<%=state.name()%>><%=state.name()%>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 input100-select">
                <span class="label-input100">Examination Type</span>
                <div>
                    <select
                            <c:if test="${(mode == 'view')}"><c:out
                                    value="disabled='disabled'"/></c:if>
                            class="selection-2" name="machineType" required>
                        <%
                            for (Machine.Type machine : Machine.Type.values()) {
                        %>
                        <option value=<%=machine.name()%>><%=machine.name()%>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input"
                 data-validate="Name is required">
                <span class="label-input100">Patient First Name</span> <input
            <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                <c:out value="disabled='disabled'"/>
            </c:if>
                    class="input100" type="text" name="patientFirstName"
                    placeholder="Enter patien's first name"
                    value="<c:out value="${appointment.getPatient().getFirstName()}"/>"
                    required> <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input"
                 data-validate="Name is required">
                <span class="label-input100">Patient Last Name</span> <input
            <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                <c:out value="disabled='disabled'"/>
            </c:if>
                    class="input100" type="text" name="patientLastName"
                    placeholder="Enter patient's last name"
                    value="<c:out value="${appointment.getPatient().getLastName()}"/>"
                    required> <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input"
                 data-validate="Name is required">
                <span class="label-input100">Patient Address</span>
                <div class="form-group gaddress f12 " data-fid="f12">
                    <input
                    <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
                            type="number" class="input100" name="patientUnitNo"
                            placeholder="Unit no" data-bind="value:replyNumber"
                            value="<c:out value="${appointment.getPatient().getAddress().getUnitNo()}"/>"
                            required> <input
                <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                    <c:out value="disabled='disabled'"/>
                </c:if>
                        type="text" class="input100 gaddress-autocomplete"
                        data-gaddress-types="street_number route"
                        data-gaddress-name="long_name" id="f12_addressLine1"
                        name="patientStreet"
                        aria-describedby="f12_addressLine1-help-block"
                        placeholder="1234 Main St."
                        value="<c:out value="${appointment.getPatient().getAddress().getStreetName()}"/>"
                        required> <input
                <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                    <c:out value="disabled='disabled'"/>
                </c:if>
                        type="text" class="input100"
                        data-gaddress-types="locality"
                        data-gaddress-name="long_name" id="f12_city"
                        name="patientCity"
                        aria-describedby="f12_city-help-block"
                        placeholder="City"
                        value="<c:out value="${appointment.getPatient().getAddress().getCity()}"/>"
                        required> <input
                <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                    <c:out value="disabled='disabled'"/>
                </c:if>
                        type="text" class="input100"
                        data-gaddress-types="administrative_area_level_1"
                        data-gaddress-name="long_name" id="f12_state"
                        name="patientState"
                        aria-describedby="f12_state-help-block"
                        placeholder="State / Province / Region"
                        value="<c:out value="${appointment.getPatient().getAddress().getState()}"/>"
                        required> <input
                <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                    <c:out value="disabled='disabled'"/>
                </c:if>
                        type="number" class="input100"
                        data-gaddress-types="postal_code"
                        data-gaddress-name="patientUPostalCode"
                        id="f12_postalCode"
                        name="patientPostalCode"
                        aria-describedby="f12_postalCode-help-block"
                        placeholder="Postal / Zip Code"
                        data-bind="value:replyNumber"
                        value="<c:out value="${appointment.getPatient().getAddress().getPostCode()}"/>"
                        required>
                </div>
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input"
                 data-validate="Valid email is required: ex@abc.xyz">
                <span class="label-input100">Patient Email</span> <input
            <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                <c:out value="disabled='disabled'"/>
            </c:if>
                    class="input100" type="text" name="email"
                    placeholder="Enter Email" required> <span
                    class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input"
                 data-validate="Medicare no is required">
                <span class="label-input100">Patient Medicare no.</span> <input
            <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                <c:out value="disabled='disabled'"/>
            </c:if>
                    class="input100" type="text" name="patientMedicareNo"
                    placeholder="Enter Medicare no."
                    value="<c:out value="${appointment.getPatient().getMedicareNo()}"/>"
                    required> <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100">
                <span class="label-input100">Patient Phone Number</span> <input
            <c:if test="${(mode == 'view') || (param.mode == 'create')}">
                <c:out value="disabled='disabled'"/>
            </c:if>
                    class="input100" type="text" name="patientMobile"
                    placeholder="Enter phone number"
                    value="<c:out value="${appointment.getPatient().getPhone()}"/>"
                    required> <span class="focus-input100"></span>
            </div>
            <div class="wrap-input100 validate-input"
                 data-validate="Message is required">
                <span class="label-input100">Additional Details</span>
                <textarea class="input100" name="message"
                          placeholder="Message here..."></textarea>
                <span class="focus-input100"></span>
            </div>

            <div class="container-contact100-form-btn">
                <div class="wrap-contact100-form-btn">
                    <div class="contact100-form-bgbtn"></div>
                    <input type="hidden" id="app_id" name="appointmentid"
                           value="<c:out value="${appointment.getId()}"/>">
                    <input
                    <c:if test="${mode == 'view'}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
                            type="hidden" id="patient_id" name="patientid"
                            value="<c:out value="${appointment.getPatient().getId()}"/>">
                    <input type="hidden" id="patient_address_id"
                           name="patientaddressid"
                           value="<c:out value="${appointment.getPatient().getAddress().getId()}"/>">

                    <c:choose>
                        <c:when test="${mode == 'view'}">
                            <button type="submit" name="mode"
                                    class="contact100-form-btn"
                                    value="edit">
										<span> Edit <i
                                                class="fa fa-long-arrow-right m-l-7"
                                                aria-hidden="true"></i>
										</span>
                            </button>
                        </c:when>
                        <c:when test="${mode == 'update'}">
                            <button type="submit" name="mode"
                                    class="contact100-form-btn"
                                    value="update">
										<span> Submit <i
                                                class="fa fa-long-arrow-right m-l-7"
                                                aria-hidden="true"></i>
										</span>
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" name="mode"
                                    class="contact100-form-btn"
                                    value="create">
										<span> Submit <i
                                                class="fa fa-long-arrow-right m-l-7"
                                                aria-hidden="true"></i>
										</span>
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div style="overflow: auto;">
            <div style="float: center;">
                <button type="button" class="stepbysteptab100-btn" id="prevBtn"
                        onclick="nextPrev(-1)">Previous
                </button>
                <button type="button" class="stepbysteptab100-btn" id="nextBtn"
                        onclick="nextPrev(1)">Next
                </button>
            </div>
        </div>

        <!-- Circles which indicates the steps of the form: -->
        <div style="text-align: center; margin-top: 40px;">
            <span class="step"></span> <span class="step"></span>
        </div>
        </form>

    </div>

</div>

<div id="dropDownSelect1"></div>

<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>

<script src="resources/js/bootstrap/bootstrap.min.js"></script>

<script type="text/javascript"
        src="resources/js/datetimepicker/bootstrap-datetimepicker.min.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').attr('readonly', true);
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii'
    });
</script>
<script src="resources/js/jquery.validate.min.js"
        type="text/javascript"></script>
<script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDtXpn6gUreNd7lbpKUKPEgt6oXmVl5BSo&libraries=places"></script>
<script src="resources/js/google-address.js" type="text/javascript"></script>
<script src="resources/js/validator.min.js" type="text/javascript"></script>

<script src="resources/js/animsition/animsition.min.js"></script>
<script src="resources/js/popper.js"></script>
<script src="resources/js/select2/select2.min.js"></script>
<script>
    $(".selection-2").select2({
        minimumResultsForSearch: 20,
        dropdownParent: $('#dropDownSelect1')
    });
</script>
<script src="resources/js/form-main.js"></script>
<script async
        src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());

    gtag('config', 'UA-23581568-13');
</script>
<%--<script type="text/javascript">
    var searchValue = document.getElementById("searchValue");
    document
            .getElementById("searchValue")
            .addEventListener(
                    'input',
                    function(evt) {
                        if (searchValue && searchValue.value) {
                            document.getElementById("nextBtn").style.color = "#70bf74";
                        } else if (searchValue.value == '') {
                            document.getElementById("nextBtn").style.color = "#fff";
                        }

                    });
</script>
<script type="text/javascript">
    document.getElementById("prevBtn").addEventListener("click", unblockNextButton);
    function unblockNextButton() {
        document.getElementById("nextBtn").disabled = false;
    }
</script>--%>
<script type="text/javascript">
    $(document).ready(function () {
        $("#searchValue").autocomplete({
            source: "patient?mode=autocomplete",
            select: function (event, ui) {
                $("#searchValue").val(ui.item.value);
                document.getElementById("nextBtn").style.color = "#70bf74";
                document.getElementById("nextBtn").disabled = false;
            }

        });

    });
</script>

<%--
<script src="resources/js/daterangepicker/moment.min.js"></script>
<script src="resources/js/daterangepicker/daterangepicker.js"></script>
<script src="resources/js/countdowntime/countdowntime.js"></script>
 --%>
</body>
</html>