<%@page import="dies.models.Appointment.State" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="mtform" tagdir="/WEB-INF/tags/forms"%>
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
            <c:if test="${(param.mode == 'create') || (empty param.mode)}">
               <div class="tab">
                  <span class="contact100-form-title"> Patient Information </span>
                  <div class="wrap-input100 validate-input"
                     data-validate="Value is required">
                     <span class="label-input100">Search</span> 
                     <input
	                     <c:if test="${(mode == 'view')}">
	                        <c:out value="readonly='readonly'"/>
	                     </c:if>
	                     class="input100" 
	                     type="text" 
	                     id="searchValue"
	                     placeholder="Enter patient's Medicare no. to search"
	                     value=""
	                     required> 
                     <span class="focus-input100"></span>
                  </div>
               </div>
            </c:if>
            <!-- second tab -->
            <div class="tab">
               <span class="contact100-form-title"> Appointment Form </span>
               <div class="wrap-input100 validate-input"
                  data-validate="Date is required">
                  <span class="label-input100">Appointment Date and Time</span>
                  <input
                      <c:if test="${user.getGroup() != 'RECEPTIONIST'}">
					      <c:out value="readonly='readonly'"/>
					  </c:if>
	                  class="input100 form_datetime" 
	                  type="text"
	                  name="appointmentDateTime"
	                  id="appointmentDateTime"
	                  placeholder="Enter a date"
	                  value="<c:out value="${appointment.getDate()}"/>"
	                  required>
                  <span class="focus-input100"></span>
               </div>
               <div class="wrap-input100 input100-select">
                  <span class="label-input100">Appointment Status</span>
                  <div>
                     <select
	                     <c:if test="${(mode == 'view') || user.getGroup() != 'RECEPTIONIST'}">
	                        <c:out
	                           value="readonly='readonly'"/>
	                     </c:if>
	                     class="selection-2" 
	                     name="appointmentStatus"
	                     required>
	                     <c:forEach var="appointment_state"
	                        items="${appointment_states}">
	                        <option value="${appointment_state.name()}">${appointment_state.name()}</option>
	                     </c:forEach>
                     </select>
                  </div>
                  <span class="focus-input100"></span>
               </div>
               <div class="wrap-input100 input100-select">
                  <span class="label-input100">Examination Type</span>
                  <div class="selection-2-multiple-fixed">
                     <select style="width: 200px;"
	                     <c:if test="${(mode == 'view') || user.getGroup() != 'RECEPTIONIST'}">
	                        <c:out value="readonly='readonly'"/>
	                     </c:if>
	                     class="selection-2"
	                     multiple='multiple'
	                     data-live-search="true"
	                     name="machineType" 
	                     id="machineType"
                     	required>
	                     <c:forEach var="appointment_machine"
	                        items="${appointment_machines}">
	                        <option selected='selected'
	                           value="${appointment_machine.getType()}">${appointment_machine.getType()}</option>
	                     </c:forEach>
	                     <c:forEach var="available_machine"
	                        items="${available_machines}">
	                        <option value="${available_machine.getType()}">${available_machine.getType()}</option>
	                     </c:forEach>
                     </select>
                  </div>
               </div>
               <c:if test="${user.getGroup() == 'RADIOLOGIST'}">
				   <span class="label-input100">Report</span> 
		                 <div class="wrap-input100">
		                 <textarea 
		                     <c:if test="${(mode == 'view')}">
						      <c:out value="readonly='readonly'"/>
						    </c:if>
		                 	class="input100" 
		                 	name="patientReport"
						    id="patientReport" 
		                 	placeholder="Enter patient report..."><c:out value="${cookie.patientReport.value}"/></textarea>
		             <div>
		             
		             <c:forEach var="appointment_machine"
	                        items="${appointment_machines}">
	                        <img 
				         		id="reportImg${appointment_machine.getType()}" 
				         		src="resources/uploads/${appointment_machine.getType()}.jpg"
				           		alt="${appointment_machine.getType()}"
				           		class="reportImg"
				           		onclick="expandImage(this.id)">
					</c:forEach>
			            <!-- The Modal -->
			            <div id="reportModal" class="modal">
			            	<span class="close">&times;</span>
			            	<img class="modal-content" id="img01">
			            	<div id="caption"></div>
			            </div>
			            <!-- <button type="submit" name="mode"
			            class="contact100-form-btn-report"
			            value="report">
			            <span> View Report <i
			            class="fa fa-long-arrow-right m-l-7"
			            aria-hidden="true"></i>
			            </span>
			            </button> -->
			            <span class="focus-input100"></span>
			       	 </div>
			       </div>
               </c:if>
               
               <mtform:formpatient 
                  patientFirstName="${appointment.getPatient().getFirstName()}"
                  patientLastName="${appointment.getPatient().getLastName()}"
                  patientUnitNo="${appointment.getPatient().getAddress().getUnitNo()}" 
                  patientStreetName="${appointment.getPatient().getAddress().getStreetName()}"
                  patientCity="${appointment.getPatient().getAddress().getCity()}" 
                  patientState="${appointment.getPatient().getAddress().getState()}"
                  patientPostCode="${appointment.getPatient().getAddress().getPostCode()}" 
                  patientEmail="${appointment.getPatient().getEmail()}" 
                  patientMedicareNo="${appointment.getPatient().getMedicareNo()}" 
                  patientPhone="${appointment.getPatient().getPhone()}" 
               />
                  
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
                     <c:if test="${param.mode != 'create'}">
                        <input type="hidden" id="app_id"
                        name="appointmentid"
                        value="<c:out value="${appointment.getId()}"/>">
                     </c:if>
                     <input
	                     <c:if test="${mode == 'view'}">
	                        <c:out value="readonly='readonly'"/>
	                     </c:if>
	                     type="hidden" id="patientid"
	                     name="patientid"
	                     value="<c:out value="${appointment.getPatient().getId()}"/>">
                     <input type="hidden" id="patientAddressid"
	                     name="patientAddressid"
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
                        <c:otherwise>
                           <button type="submit" name="mode"
                              class="contact100-form-btn"
                              value="create">
                           <span> Create <i
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
                  <button type="button" class="stepbysteptab100-btn"
                     id="prevBtn"
                     onclick="nextPrev(-1)">Previous
                  </button>
                  <button type="button" class="stepbysteptab100-btn"
                     id="nextBtn"
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
   </jsp:attribute>
   <jsp:attribute name="scripts">
      <script type="text/javascript"
         src="resources/js/datetimepicker/bootstrap-datetimepicker.min.js"
         charset="UTF-8"></script>
      <script type="text/javascript">
         $("#appointmentDateTime").attr('readonly', true);
         $("#appointmentDateTime").datetimepicker({
             format: 'yyyy-mm-dd hh:ii',
             inline: true,
             sideBySide: true
         }).on('change.dp', function (e) {
             $appointmentdatetime = $('#appointmentDateTime');
             $.ajax({
                 type: "GET",
                 url: "appointment?mode=autocomplete",
                 data: {appointmentdatetime: $appointmentdatetime.val()},
                 success: function (data) {
                     $("#machineType").html(data)
                 }
             });
         });
      </script>
      <script>
         $(".selection-2").select2({
             minimumResultsForSearch: 20,
             dropdownParent: $('#dropDownSelect1')
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
      <script type="text/javascript">
         $(document).ready(function () {
             $("#searchValue").autocomplete({
                 source: "patient?mode=autocomplete",
                 select: function (event, ui) {
                     $("#searchValue").val(ui.item.value);
         
                     $("#patientFirstName").val(ui.item.firstName);
                     $("#patientLastName").val(ui.item.lastName);
                     $("#patientMobile").val(ui.item.phone);
                     $("#patientMedicareNo").val(ui.item.medicareNo);
                     $("#patientEmail").val(ui.item.email);
                     $("#patientid").val(ui.item.id);
         
                     $("#patientUnitNo").val(ui.item.address.unitNo);
                     $("#f12_addressLine1").val(ui.item.address.streetName);
                     $("#f12_city").val(ui.item.address.city);
                     $("#f12_state").val(ui.item.address.state);
                     $("#f12_postalCode").val(ui.item.address.postCode);
                     $("#patientAddressid").val(ui.item.address.id);
         
                     document.getElementById("nextBtn").style.color = "#70bf74";
                     document.getElementById("nextBtn").disabled = false;
                 },
                 focus: function (event, ui) {
                     console.log(ui);
                     $("#searchValue").val(ui.item.value);
                     console.log(ui.item.value);
                     return false;
                 }
             });
         });
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
      <script>
      
      </script>
   </jsp:attribute>
</mtform:formtemplate>