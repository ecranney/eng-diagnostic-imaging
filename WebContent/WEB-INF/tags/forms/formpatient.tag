<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@attribute name="patientFirstName" required="true"%>
<%@attribute name="patientLastName" required="true"%>
<%@attribute name="patientUnitNo" required="true"%>
<%@attribute name="patientStreetName" required="true"%>
<%@attribute name="patientCity" required="true"%>
<%@attribute name="patientState" required="true"%>
<%@attribute name="patientPostCode" required="true"%>
<%@attribute name="patientEmail" required="true"%>
<%@attribute name="patientMedicareNo" required="true"%>
<%@attribute name="patientPhone" required="true"%>
<%@attribute name="formType"%>
<div class="wrap-input100 validate-input"
   data-validate="First name is required">
   <span class="label-input100">Patient First Name</span> 
   <input
	   <c:if test="${(mode == 'view')}">
	      <c:out value="readonly='readonly'"/>
	   </c:if>
	   class="input100" 
	   type="text" 
	   name="patientFirstName"
	   id="patientFirstName" 
	   placeholder="Enter patien's first name"
	   value="<c:out value="${patientFirstName}"/>" 
	   required>
   <span class="focus-input100"></span>
</div>
<div class="wrap-input100 validate-input"
   data-validate="Lat name is required">
   <span class="label-input100">Patient Last Name</span> 
   <input
	   <c:if test="${(mode == 'view')}">
	      <c:out value="readonly='readonly'"/>
	   </c:if>
	   class="input100" 
	   type="text" 
	   name="patientLastName"
	   id="patientLastName" 
	   placeholder="Enter patient's last name"
	   value="<c:out value="${patientLastName}"/>"
	   required> 
   <span class="focus-input100"></span>
</div>
<div class="wrap-input100 validate-input"
   data-validate="Name is required">
   <span class="label-input100">Patient Address</span>
   <div class="form-group gaddress f12 " data-fid="f12">
      <input
	      <c:if test="${(mode == 'view') }">
	         <c:out value="readonly='readonly'"/>
	      </c:if>
	      type="number" 
	      class="input100" 
	      name="patientUnitNo"
	      id="patientUnitNo" 
	      placeholder="Unit no"
	      data-bind="value:replyNumber"
	      value="<c:out value="${patientUnitNo}"/>"
	      required> 
      <input
	      <c:if test="${(mode == 'view')}">
	         <c:out value="readonly='readonly'"/>
	      </c:if>
	      type="text" 
	      class="input100 gaddress-autocomplete"
	      data-gaddress-types="street_number route"
	      data-gaddress-name="long_name" 
	      id="f12_addressLine1"
	      name="patientStreet" 
	      aria-describedby="f12_addressLine1-help-block"
	      placeholder="1234 Main St."
	      value="<c:out value="${patientStreetName}"/>"
	      required> 
      <input
	      <c:if test="${(mode == 'view')}">
	         <c:out value="readonly='readonly'"/>
	      </c:if>
	      type="text" 
	      class="input100" 
	      data-gaddress-types="locality"
	      data-gaddress-name="long_name" 
	      id="f12_city" name="patientCity"
	      aria-describedby="f12_city-help-block" 
	      placeholder="City"
	      value="<c:out value="${patientCity}"/>"
	      required> 
	  <input
	      <c:if test="${(mode == 'view')}">
	         <c:out value="readonly='readonly'"/>
	      </c:if>
	      type="text" 
	      class="input100"
	      data-gaddress-types="administrative_area_level_1"
	      data-gaddress-name="long_name" 
	      id="f12_state" 
	      name="patientState"
	      aria-describedby="f12_state-help-block"
	      placeholder="State / Province / Region"
	      value="<c:out value="${patientState}"/>"
	      required> 
	  <input
	      <c:if test="${(mode == 'view')}">
	         <c:out value="readonly='readonly'"/>
	      </c:if>
	      type="number"
	      class="input100" 
	      data-gaddress-types="postal_code"
	      data-gaddress-name="patientUPostalCode" 
	      id="f12_postalCode"
	      name="patientPostalCode" 
	      aria-describedby="f12_postalCode-help-block"
	      placeholder="Postal / Zip Code" 
	      data-bind="value:replyNumber"
	      value="<c:out value="${patientPostCode}"/>"
	      required>
   </div>
   <span class="focus-input100"></span>
</div>
<div class="wrap-input100 validate-input"
   data-validate="Valid email is required: ex@abc.xyz">
   <span class="label-input100">Patient Email</span> 
   <input
	   <c:if test="${(mode == 'view') }">
	      <c:out value="readonly='readonly'"/>
	   </c:if>
	   class="input100" 
	   type="text" 
	   name="patientEmail" 
	   id="patientEmail"
	   placeholder="Enter Email"
	   value="<c:out value="${patientEmail}"/>"
	   required> 
   <span class="focus-input100"></span>
</div>
<div class="wrap-input100 validate-input"
   data-validate="Medicare no is required">
   <div class="">
   	  <c:if test="${formType == 'patient'}">
	      <a href="#" 
	      	data-toggle="tooltip"
	        title="You cannot change the medicare no, if it's need to be done create a new patient">
	      	<i class="fa fa-question-circle" aria-hidden="true" aria-hidden="true"></i> info
	      </a>
	  </c:if>
   </div>
   <span class="label-input100">Patient Medicare no.</span> 
   <input
	   <c:if test="${(mode == 'view') || (param.mode == 'edit') || (param.mode == 'create')}">
	      <c:out value="readonly='readonly'"/>
	   </c:if>
	   class="input100" 
	   type="text" 
	   name="patientMedicareNo"
	   id="patientMedicareNo" 
	   placeholder="Enter Medicare no."
	   value="<c:out value="${patientMedicareNo}"/>"
	   required>
	<span class="focus-input100"></span>
</div>
<div class="wrap-input100">
   <span class="label-input100">Patient Phone Number</span> 
   <input
	   <c:if test="${(mode == 'view')}">
	      <c:out value="readonly='readonly'"/>
	   </c:if>
	   class="input100" 
	   type="text" 
	   name="patientMobile" 
	   id="patientMobile"
	   placeholder="Enter phone number"
	   value="<c:out value="${patientPhone}"/>"
	   required> 
   <span class="focus-input100"></span>
</div>