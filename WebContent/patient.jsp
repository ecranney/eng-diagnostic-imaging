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
            <span class="contact100-form-title"> Patient Registration </span>
            <c:if test="${not empty errors}">
               <div class="alert alert-danger">
                  <strong> ${errors} </strong>
               </div>
            </c:if>
            
            <mtform:formpatient 
                  patientFirstName="${patient.getFirstName()}"
                  patientLastName="${patient.getLastName()}"
                  patientUnitNo="${patient.getAddress().getUnitNo()}" 
                  patientStreetName="${patient.getAddress().getStreetName()}"
                  patientCity="${patient.getAddress().getCity()}" 
                  patientState="${patient.getAddress().getState()}"
                  patientPostCode="${patient.getAddress().getPostCode()}" 
                  patientEmail="${patient.getEmail()}" 
                  patientMedicareNo="${patient.getMedicareNo()}" 
                  patientPhone="${patient.getPhone()}"
                  formType="patient"
            />
            
            <div class="container-contact100-form-btn">
               <div class="wrap-contact100-form-btn">
                  <div class="contact100-form-bgbtn"></div>
                  <input 
                  	  type="hidden" 
                  	  id="app_id"
                  	  name="appointmentid" 
                  	  value="<c:out value="${patient.getId()}"/>">
                  <input
	                  <c:if test="${mode == 'view'}">
	                     <c:out value="disabled='disabled'"/>
	                  </c:if>
	                  type="hidden" 
	                  id="patient_id" 
	                  name="patientid"
	                  value="<c:out value="${patient.getId()}"/>">
                  <input 
                      type="hidden" 
                  	  id="patient_address_id"
                  	  name="patientaddressid"
                      value="<c:out value="${patient.getAddress().getId()}"/>">
                  <c:choose>
                     <c:when test="${mode == 'view'}">
                        <button 
                        	type="submit" 
                        	name="mode"
                           	class="contact100-form-btn" 
                           	value="edit">
	                        <span> Edit <i class="fa fa-long-arrow-right m-l-7" aria-hidden="true"></i>
	                        </span>
                        </button>
                     </c:when>
                     <c:when test="${mode == 'update'}">
                        <button 
                        	type="submit" 
                        	name="mode"
                            class="contact100-form-btn" 
                            value="update">
	                        <span> Submit <i class="fa fa-long-arrow-right m-l-7" aria-hidden="true"></i>
	                        </span>	
                        </button>
                     </c:when>
                     <c:otherwise>
                        <button 
                        	type="submit" 
                        	name="mode"
                            class="contact100-form-btn" 
                            value="create">
                        	<span> Submit <i class="fa fa-long-arrow-right m-l-7" aria-hidden="true"></i>
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