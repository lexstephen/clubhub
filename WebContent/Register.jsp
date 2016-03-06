<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: January 24, 2016
	Description: Main.jsp
 --%>
 
<% request.setAttribute("thisPage", "Join the Rivendell Curling Club"); %>

 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_public.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	${errorString }
	<form action="/clubhub/UserController" method="post" class="form" role="form" enctype="multipart/form-data">
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorFirstName}">has-error</c:if>">
			    	<label for="inptFirstName">First Name</label>
			    	<input type="text" name="firstName" class="form-control" id="inptFirstName" value="${firstName}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorLastName}">has-error</c:if>">
			    	<label for="inptLastName">Last Name</label>
			    	<input type="text" name="lastName" class="form-control" id="inptLastName" value="${lastName}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorUsername}">has-error</c:if>">
			    	<label for="inptUserName">User Name</label>
			    	<input type="text" name="username" class="form-control" id="inptUserName" value="${username}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorTelephone}">has-error</c:if>">
			    	<label for="inptPhoneNumber">Phone Number</label>
			    	<input type="text" name="telephone" class="form-control" id="inptPhoneNumber" value="${telephone}">
			  	</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorEmail1}">has-error</c:if>">
			    	<label for="inptEmailAddress">Email Address</label>
			    	<input type="text" name="emailAddress" class="form-control" id="inptEmailAddress" value="${emailAddress}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorEmail2}">has-error</c:if>">
			    	<label for="inptEmailAddress2">Confirm Email Address</label>
			    	<input type="text" name="emailAddress2" class="form-control" id="inptEmailAddress2" value="${emailAddress2}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorPassword1}">has-error</c:if>">
			    	<label for="inptPassword">Password</label>
			    	<input type="password" name="password1" class="form-control" id="inptPassword">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorPassword2}">has-error</c:if>">
			    	<label for="inptPassword2">Confirm Password</label>
			    	<input type="password" name="password2" class="form-control" id="inptPassword2">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
			    <label for="inptGender <c:if test="${!empty errorGender}">has-error</c:if>">Gender</label>
				<div class="radio">
			    	<label class="checkbox-inline">
			      		<input type="radio" name="gender" id="inptGenderF" value="F" checked  ${gender == 'F' ? 'checked' : ''}> Female
			      	</label>
			    	<label class="checkbox-inline">
			      		<input type="radio" name="gender" id="inptGenderM" value="M"  ${gender == 'M' ? 'checked' : ''}> Male
			      	</label>
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorDateOfBirth}">has-error</c:if>">
			    	<label for="inptDOB">Date of Birth</label>
			    	<input type="date" name="dateOfBirth" class="form-control" id="inptDOB" value="${dateOfBirth}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group <c:if test="${!empty errorStreetAddress}">has-error</c:if>">
			    <label for="inptStreetAddress">Street Address</label>
			    	<input type="text" name="streetAddress" class="form-control" id="inptStreetAddress" value="${streetAddress}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorCity}">has-error</c:if>">
			    	<label for="inptCity">City</label>
			    	<input type="text" name="city" class="form-control" id="inptCity" value="${city}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorCountry}">has-error</c:if>">
			    	<label for="inptCountry">Country</label>
					<select name="country" class="form-control" id="inptCountry">
					  <option ${country == 'Canada' ? 'selected' : ''}>Canada</option>
					  <option ${country == 'United States of America' ? 'selected' : ''}>United States of America</option>
					</select>
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorProvince}">has-error</c:if>">
					<label for="inptProvince" id="lblProvince" ${country == 'United States of America' ? ' class="hiddenest"' : ''}>
						Province
					</label>
					<label for="inptState" id="lblState" ${country == 'Canada' ? ' class="hiddenest"' : ''} ${country == null ? ' class="hiddenest"' : ''}>
						State
					</label>
					<select name="province" class="form-control ${user.country == 'United States of America' ? ' hiddenest' : ''}" id="inptProvince">
					  <option ${province == 'AB' ? 'selected' : ''}>AB</option>
					  <option ${province == 'BC' ? 'selected' : ''}>BC</option>
					  <option ${province == 'MB' ? 'selected' : ''}>MB</option>
					  <option ${province == 'NB' ? 'selected' : ''}>NB</option>
					  <option ${province == 'NL' ? 'selected' : ''}>NL</option>
					  <option ${province == 'NS' ? 'selected' : ''}>NS</option>
					  <option ${province == 'NT' ? 'selected' : ''}>NT</option>
					  <option ${province == 'NU' ? 'selected' : ''}>NU</option>
					  <option ${province == 'ON' ? 'selected' : ''}>ON</option>
					  <option ${province == 'PE' ? 'selected' : ''}>PE</option>
					  <option ${province == 'QC' ? 'selected' : ''}>QC</option>
					  <option ${province == 'SK' ? 'selected' : ''}>SK</option>
					  <option ${province == 'YT' ? 'selected' : ''}>YT</option>
					</select>
					<select name="state" class="form-control ${user.country == 'Canada' ? ' hiddenest' : ''} ${user.country == null ? ' hiddenest' : ''}" id="inptState">
					  <option ${province == 'AK' ? 'selected' : ''}>AK</option>
					  <option ${province == 'AL' ? 'selected' : ''}>AL</option>
					  <option ${province == 'AR' ? 'selected' : ''}>AR</option>
					  <option ${province == 'AZ' ? 'selected' : ''}>AZ</option>
					  <option ${province == 'CA' ? 'selected' : ''}>CA</option>
					  <option ${province == 'CO' ? 'selected' : ''}>CO</option>
					  <option ${province == 'CT' ? 'selected' : ''}>CT</option>
					  <option ${province == 'DC' ? 'selected' : ''}>DC</option>
					  <option ${province == 'DE' ? 'selected' : ''}>DE</option>
					  <option ${province == 'FL' ? 'selected' : ''}>FL</option>
					  <option ${province == 'GA' ? 'selected' : ''}>GA</option>
					  <option ${province == 'HI' ? 'selected' : ''}>HI</option>
					  <option ${province == 'IA' ? 'selected' : ''}>IA</option>
					  <option ${province == 'ID' ? 'selected' : ''}>ID</option>
					  <option ${province == 'IL' ? 'selected' : ''}>IL</option>
					  <option ${province == 'IN' ? 'selected' : ''}>IN</option>
					  <option ${province == 'KS' ? 'selected' : ''}>KS</option>
					  <option ${province == 'KY' ? 'selected' : ''}>KY</option>
					  <option ${province == 'LA' ? 'selected' : ''}>LA</option>
					  <option ${province == 'MA' ? 'selected' : ''}>MA</option>
					  <option ${province == 'MD' ? 'selected' : ''}>MD</option>		
					  <option ${province == 'ME' ? 'selected' : ''}>ME</option>
  					  <option ${province == 'MI' ? 'selected' : ''}>MI</option>
  					  <option ${province == 'MN' ? 'selected' : ''}>MN</option>
  					  <option ${province == 'MO' ? 'selected' : ''}>MO</option>
  					  <option ${province == 'MS' ? 'selected' : ''}>MS</option>
  					  <option ${province == 'MT' ? 'selected' : ''}>MT</option>
  					  <option ${province == 'NC' ? 'selected' : ''}>NC</option>
  					  <option ${province == 'ND' ? 'selected' : ''}>ND</option>
  					  <option ${province == 'NE' ? 'selected' : ''}>NE</option>
  					  <option ${province == 'NH' ? 'selected' : ''}>NH</option>
  					  <option ${province == 'NJ' ? 'selected' : ''}>NJ</option>
  					  <option ${province == 'NM' ? 'selected' : ''}>NM</option>
  					  <option ${province == 'NV' ? 'selected' : ''}>NV</option>
  					  <option ${province == 'NY' ? 'selected' : ''}>NY</option>
  					  <option ${province == 'OH' ? 'selected' : ''}>OH</option>
  					  <option ${province == 'OK' ? 'selected' : ''}>OK</option>
  					  <option ${province == 'OR' ? 'selected' : ''}>OR</option>
  					  <option ${province == 'PA' ? 'selected' : ''}>PA</option>
  					  <option ${province == 'RI' ? 'selected' : ''}>RI</option>
  					  <option ${province == 'SC' ? 'selected' : ''}>SC</option>
  					  <option ${province == 'SD' ? 'selected' : ''}>SD</option>
  					  <option ${province == 'TN' ? 'selected' : ''}>TN</option>
  					  <option ${province == 'TX' ? 'selected' : ''}>TX</option>
  					  <option ${province == 'UT' ? 'selected' : ''}>UT</option>
  					  <option ${province == 'VA' ? 'selected' : ''}>VA</option>
  					  <option ${province == 'VT' ? 'selected' : ''}>VT</option>
  					  <option ${province == 'WA' ? 'selected' : ''}>WA</option>
  					  <option ${province == 'WI' ? 'selected' : ''}>WI</option>
  					  <option ${province == 'WV' ? 'selected' : ''}>WV</option>
  					  <option ${province == 'WY' ? 'selected' : ''}>WY</option>
					</select>
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorPostalCode}">has-error</c:if>">
			    	<label for="inptPostalCode" id="lblPostalCode" ${country == 'United States of America' ? ' class="hiddenest"' : ''}>
						Postal Code
					</label>
					<label for="inptProvince" id="lblZipCode" ${country == 'Canada' ? ' class="hiddenest"' : ''} ${country == null ? ' class="hiddenest"' : ''}>
						Zip Code
					</label>
			    	<input type="text" name="postalCode" class="form-control" id="inptPostalCode" value="${postalCode}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorProfilePhoto}">has-error</c:if>">
			    	<label for="inptProfilePhoto">Profile Photo</label>
			    	<input type="file" name="profilePhoto" class="form-control" id="inptProfilePhoto">
	    			<p class="help-block">Upload a square jpg or png.</p>
			  	</div>
			</div>
			<div class="col-xs-6 pull-right">
		    	<label>Preview</label>
				<img src="/clubhub/images/avatar.gif">
			</div>
		</div>
		
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorEmergencyContactName}">has-error</c:if>">
			    	<label for="inptEmergencyContactName">Emergency Contact Name</label>
			    	<input type="text" name="emergencyContactName" class="form-control" id="inptEmergencyContactName" value="${emergencyContactName}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorEmergencyContactPhoneNumber}">has-error</c:if>">
			    	<label for="inptEmergencyContactNumber">Emergency Contact Number</label>
			    	<input type="text" name="emergencyContactPhoneNumber" class="form-control" id="inptEmergencyContactNumber" value="${emergencyContactPhoneNumber}">
			  	</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<input type="hidden" name="option" value="register">
			    	<input type="submit" class="btn btn-default" value="Submit Registration">
			  	</div>
			</div>
		</div>
	</form>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_public.jsp" %>