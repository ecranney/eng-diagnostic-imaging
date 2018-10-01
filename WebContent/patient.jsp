<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="mtform" tagdir="/WEB-INF/tags/forms"%>
<!DOCTYPE html>

<mtform:formtemplate title="DIES Booking View">
	<jsp:attribute name="content">
		<c:if test="${empty sessionScope.userid}">
            <c:redirect url="/login" />
        </c:if>
        <div>
            <nav class="navbar navbar-default navigation-clean-button">
                <div class="container">
                    <div class="navbar-header">
                        <a class="navbar-brand title-of-header" href="#">Diagnostic
                                                                         Imaging Enterprise System</a>
                        <button class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navcol-1">
                            <span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span><span
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
                <form action="patient"
					method=<c:choose>
                      <c:when test="${mode == 'view'}">
                              "get"
                </c:when>
                <c:otherwise>
                    "post"
                </c:otherwise>
                </c:choose>
					class="contact100-form validate-form">
                <span class="contact100-form-title"> Patient Registration </span>
                <c:if test="${not empty errors}">
                    <div class="alert alert-danger">
                        <strong> ${errors} </strong>
                    </div>
                </c:if>
                <div class="wrap-input100 validate-input"
						data-validate="Name is required">
                    <span class="label-input100">Patient First Name</span>
                    <input
							<c:if test="${mode == 'view'}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
							class="input100" type="text" name="patientFirstName"
							placeholder="Enter patien's first name"
							value="<c:out value="${patient.getFirstName()}"/>" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input"
						data-validate="Name is required">
                    <span class="label-input100">Patient Last Name</span> <input
							<c:if test="${mode == 'view'}">
                    <c:out value="disabled='disabled'"/>
                </c:if>
							class="input100" type="text" name="patientLastName"
							placeholder="Enter patient's last name"
							value="<c:out value="${patient.getLastName()}"/>" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input"
						data-validate="Name is required">
                    <span class="label-input100">Patient Address</span>
                    <div class="form-group gaddress f12 " data-fid="f12">
                        <input
								<c:if test="${mode == 'view'}">
                            <c:out value="disabled='disabled'"/>
                        </c:if>
								type="number" class="input100" name="patientUnitNo"
								placeholder="Unit no" data-bind="value:replyNumber"
								value="<c:out value="${patient.getAddress().getUnitNo()}"/>"
								required> <input
								<c:if test="${mode == 'view'}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
								type="text" class="input100 gaddress-autocomplete"
								data-gaddress-types="street_number route"
								data-gaddress-name="long_name" id="f12_addressLine1"
								name="patientStreet"
								aria-describedby="f12_addressLine1-help-block"
								placeholder="1234 Main St."
								value="<c:out value="${patient.getAddress().getStreetName()}"/>"
								required> <input
								<c:if test="${mode == 'view'}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
								type="text" class="input100" data-gaddress-types="locality"
								data-gaddress-name="long_name" id="f12_city" name="patientCity"
								aria-describedby="f12_city-help-block" placeholder="City"
								value="<c:out value="${patient.getAddress().getCity()}"/>"
								required> <input
								<c:if test="${mode == 'view'}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
								type="text" class="input100"
								data-gaddress-types="administrative_area_level_1"
								data-gaddress-name="long_name" id="f12_state"
								name="patientState" aria-describedby="f12_state-help-block"
								placeholder="State / Province / Region"
								value="<c:out value="${patient.getAddress().getState()}"/>"
								required> <input
								<c:if test="${mode == 'view'}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
								type="number" class="input100" data-gaddress-types="postal_code"
								data-gaddress-name="patientUPostalCode" id="f12_postalCode"
								name="patientPostalCode"
								aria-describedby="f12_postalCode-help-block"
								placeholder="Postal / Zip Code" data-bind="value:replyNumber"
								value="<c:out value="${patient.getAddress().getPostCode()}"/>"
								required>
                    </div>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input"
						data-validate="Valid email is required: ex@abc.xyz">
                    <span class="label-input100">Patient Email</span> <input
							<c:if test="${mode == 'view'}">
                    <c:out value="disabled='disabled'"/>
                </c:if>
							class="input100" type="text" name="patientEmail"
							placeholder="Enter Email" required> <span
							class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input"
						data-validate="Medicare no is required">
                    <span class="label-input100">Patient Medicare no.</span>
                    <input
							<c:if test="${mode == 'view'}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
							class="input100" type="text" name="patientMedicareNo"
							placeholder="Enter Medicare no."
							value="<c:out value="${patient.getMedicareNo()}"/>" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100">
                    <span class="label-input100">Patient Phone Number</span>
                    <input
							<c:if test="${mode == 'view'}">
                        <c:out value="disabled='disabled'"/>
                    </c:if>
							class="input100" type="text" name="patientMobile"
							placeholder="Enter phone number"
							value="<c:out value="${patient.getPhone()}"/>" required> <span
							class="focus-input100"></span>
                </div>
                <div class="container-contact100-form-btn">
                    <div class="wrap-contact100-form-btn">
                        <div class="contact100-form-bgbtn"></div>
                        <input type="hidden" id="app_id"
								name="appointmentid" value="<c:out value="${patient.getId()}"/>">
                        <input
								<c:if test="${mode == 'view'}">
                            <c:out value="disabled='disabled'"/>
                        </c:if>
								type="hidden" id="patient_id" name="patientid"
								value="<c:out value="${patient.getId()}"/>">
                        <input type="hidden" id="patient_address_id"
								name="patientaddressid"
								value="<c:out value="${patient.getAddress().getId()}"/>">
                        <c:choose>
                            <c:when test="${mode == 'view'}">
                                <button type="submit" name="mode"
										class="contact100-form-btn" value="edit">
									<span> Edit <i class="fa fa-long-arrow-right m-l-7"
											aria-hidden="true"></i>
									</span>
                                </button>
                            </c:when>
                            <c:when test="${mode == 'update'}">
                                <button type="submit" name="mode"
										class="contact100-form-btn" value="update">
									<span> Submit <i class="fa fa-long-arrow-right m-l-7"
											aria-hidden="true"></i>
									</span>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" name="mode"
										class="contact100-form-btn" value="create">
									<span> Submit <i class="fa fa-long-arrow-right m-l-7"
											aria-hidden="true"></i>
									</span>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                </form>
            </div>
        </div>
	</jsp:attribute>
	<jsp:attribute name="scripts"/>
</mtform:formtemplate>
