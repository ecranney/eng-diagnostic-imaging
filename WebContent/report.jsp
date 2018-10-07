<%@page import="dies.models.Appointment.State" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="mtform" tagdir="/WEB-INF/tags/forms"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<mtform:formtemplate title="DIES Booking View">
   <jsp:attribute name="content">
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
            <form action="report"
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
            <span class="contact100-form-title"> Report [${appointment.getPatient().getFirstName()} ${appointment.getPatient().getLastName()}]</span>
			    
            <div class="row">
            	<div class="col-sm-4" >
	            	<span class="label-input100">Report</span> 
					<div class="wrap-textarea100-div">
						<textarea 
				                     <c:if test="${(mode == 'view')}">
								      <c:out value="readonly='readonly'"/>
								    </c:if>
				                 	class="wrap-textarea100" 
				                 	name="patientreport"
								    id="patientreport" 
				                 	placeholder="Enter patient report..."><c:out value="${fn:replace(patient_draft_report, '_', ' ')}"/>
						</textarea>
					</div>
					<div class="container-contact100-form-btn">
	                  <div class="wrap-contact100-form-btn">
	                     <div class="contact100-form-bgbtn"></div>
	                     <input 
	                     	type="hidden" 
	                     	id="app_id"
	                        name="appointmentid"
	                        value="<c:out value="${appointment.getId()}"/>">
	                     <input 
	                     	type="hidden" 
	                     	id="report_id"
	                        name="reportid"
	                        value="<c:out value="${appointment.getReport().getId()}"/>">
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
	                        <c:when test="${mode == 'edit'}">
	                           <button type="submit" name="mode"
	                              class="contact100-form-btn"
	                              value="update">
	                           <span> Update <i
	                              class="fa fa-long-arrow-right m-l-7"
	                              aria-hidden="true"></i>
	                           </span>
	                           </button>
	                        </c:when>
	                        <c:when test="${mode == 'review'}">
	                           <button type="submit" name="mode"
	                              class="contact100-form-btn"
	                              value="approve">
		                           <span> Approve <i
		                              class="fa fa-long-arrow-right m-l-7"
		                              aria-hidden="true"></i>
		                           </span>
	                           </button>
	                           <button type="submit" name="mode"
	                              class="contact100-form-reject-btn"
	                              value="reject">
		                           <span> Reject <i
		                              class="fa fa-long-arrow-right m-l-7"
		                              aria-hidden="true"></i>
		                           </span>
	                           </button>
	                        </c:when>
	                     </c:choose>
	                  </div>
	               </div>
				</div>
			    <div class="col-sm-8">
			    	<div class="wrap-input100">
			    		 <span class="label-input100">Results</span>
			             <div>
				             <c:forEach var="available_images"
			                        items="${available_images}">
			                        <img 
						         		id="reportImg${available_images.getType()}"
						         		src="${available_images.getUrl()}"
						           		alt="${available_images.getType()}"
						           		class="reportImg"
						           		onclick="expandImage(this.id)">
							</c:forEach>
					            <!-- The Modal -->
					            <div id="reportModal" class="modal">
					            	<span class="close">&times;</span>
					            	<img class="modal-content" id="img01">
					            	<div id="caption"></div>
					            </div>
					            <span class="focus-input100"></span>
				       	 </div>
				    </div>
				    <div class="wrap-input100 input100-select">
				    <span class="contact100-form-sub-title1"> Appointment Information</span>
		               <div class="row">
			               <div class="col-sm-4" >
			               	<div class="custom-row"><span class="label-input100">Date                 : </span></div>
			               	<div class="custom-row"><span class="label-input100">Status               : </span></div>
			               	<div class="custom-row"><span class="label-input100">Examinations         : </span></div>
	
						   </div>
						   <div class="col-sm-8">
							    <div class="custom-row"><span class="input100">${appointment.getDate()}</span></div>
							    <div class="custom-row"><span class="input100">${appointment.getState()}</span></div>
							    <div class="custom-row">
								    <span class="input100">
								    <c:forEach 
								    	var="appointment_machine"
				                        items="${appointment_machines}">
				                        <div class="select2-container--default select2-selection--multiple select2-selection__choice"
													title="CAT">${appointment_machine.getType()} </div>
				                    </c:forEach>
								    </span>
							   </div>
						  </div>
					</div>
					</div>
			    	
	               <div class="wrap-input100 input100-select">
	                  <span class="focus-input100"></span>
					   <span class="contact100-form-sub-title1"> Patient Information</span>
		               <div class="row">
			               <div class="col-sm-4" >
			               	<div class="custom-row"><span class="label-input100">Medicare No  : </span></div>
			               	<div class="custom-row"><span class="label-input100">Email        : </span></div>
			               	<div class="custom-row"><span class="label-input100">Phone        : </span></div>
	
						   </div>
						   <div class="col-sm-8">
						    <div class="custom-row"><span class="input100">${appointment.getPatient().getMedicareNo()}</span></div>
						    <div class="custom-row"><span class="input100">${appointment.getPatient().getEmail()}</span></div>
						    <div class="custom-row"><span class="input100">${appointment.getPatient().getPhone()}</span></div>
						   </div>
					   </div>
	                  
	               </div>
	               
			    </div>
			</div>
	               
            </form>
         </div>
      </div>
      <div id="dropDownSelect1"></div>
   </jsp:attribute>
   
   
   <jsp:attribute name="scripts">
      <script type="text/javascript"
         src="resources/js/datetimepicker/bootstrap-datetimepicker.min.js"
         charset="UTF-8"></script>
      <script type="text/javascript">
      		$("#patientreport").on('keyup', function () {
      			  $patientreport = $('#patientreport');
      			  $appointmentid = ${appointment.getId()};
	      		  data = {
	      		    patientreport: $patientreport.val(),
	      			appointmentid: $appointmentid
	      		  }
      		     
	      		  $.ajax({
	                  type: "GET",
	                  url: "report?mode=savedraft",
	                  data: data,
	                  success: function (data) {
	                  }
	              });
      		});
      </script>
      <script>
         window.dataLayer = window.dataLayer || [];
         
         function gtag() {
             dataLayer.push(arguments);
         }
         
         gtag('js', new Date());
         gtag('config', 'UA-23581568-13');
      </script>
      <script>
      	 function expandImage(Id) {
	         // Get the modal
	         var modal = document.getElementById('reportModal');
	         // Get the image and insert it inside the modal - use its "alt" text as a caption
	         
	        	 var img = document.getElementById(Id);
		         var modalImg = document.getElementById("img01");
		         var captionText = document.getElementById("caption");
		         img.onclick = function () {
		             modal.style.display = "block";
		             modalImg.src = this.src;
		             captionText.innerHTML = this.alt;
		         }
	         
	         // Get the <span> element that closes the modal
	         var span = document.getElementsByClassName("close")[0];
	         // When the user clicks on <span> (x), close the modal
	         span.onclick = function () {
	             modal.style.display = "none";
	         }
      	 }
      </script>
   </jsp:attribute>
</mtform:formtemplate>