<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: January 24, 2016
	Description: Main.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_public.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	
	<h1>Join the Rivendell Curling Club</h1>
	
	<form action="/clubhub/UserController" method="post" class="form" role="form">
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
			      		<input type="radio" name="gender" id="inptGenderF" value="F" checked> Female
			      	</label>
			    	<label class="checkbox-inline">
			      		<input type="radio" name="gender" id="inptGenderM" value="M"> Male
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
				<div class="form-group <c:if test="${!empty errorPostalCode}">has-error</c:if>">
			    	<label for="inptPostalCode">Postal Code</label>
			    	<input type="text" name="postalCode" class="form-control" id="inptPostalCode" value="${postalCode}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorCountry}">has-error</c:if>">
			    	<label for="inptCountry">Country</label>
					<select name="country" class="form-control" id="inptCountry">
					  <option>Canada</option>
					  <option>United States of America</option>
					</select>
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorProvince}">has-error</c:if>">
			    	<label for="inptProvince">Province / State</label>
					<select name="province" class="form-control" id="inptProvince">
					  <option>AB</option>
					  <option>BC</option>
					  <option>MB</option>
					  <option>NB</option>
					  <option>NL</option>
					  <option>NS</option>
					  <option>NT</option>
					  <option>NU</option>
					  <option>ON</option>
					  <option>PE</option>
					  <option>QC</option>
					  <option>SK</option>
					  <option>YT</option>
					</select>
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