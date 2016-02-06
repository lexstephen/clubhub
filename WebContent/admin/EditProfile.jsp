<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: EditProfile.jsp
 --%>
 
<%-- Should we get the page title here? Can more of this go into the header? --%>

<% request.setAttribute("thisPage", "Edit Profile"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	
	<%	
		UserDao user = new UserDao();
		String userID;
		if (request.getParameter("userID") != null) {
			userID = request.getParameter("userID");
		} else {
			userID = "1";
		}
		
		user.findUser(request, userID);
	%>
	
	<form action="/clubhub/UserController" method="post" class="form" role="form">
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorFirstName}">has-error</c:if>">
			    	<label for="inptFirstName">First Name</label>
			    	<input type="text" name="firstName" class="form-control" id="inptFirstName" value="${user.firstName}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorLastName}">has-error</c:if>">
			    	<label for="inptLastName">Last Name</label>
			    	<input type="text" name="lastName" class="form-control" id="inptLastName" value="${user.lastName}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorUsername}">has-error</c:if>">
			    	<label for="inptUserName">User Name</label>
			    	<input type="text" name="username" class="form-control" id="inptUserName" value="${user.username}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorTelephone}">has-error</c:if>">
			    	<label for="inptPhoneNumber">Phone Number</label>
			    	<input type="text" name="telephone" class="form-control" id="inptPhoneNumber" value="${user.telephone}">
			  	</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorEmail1}">has-error</c:if>">
			    	<label for="inptEmailAddress">Email Address</label>
			    	<input type="text" name="emailAddress" class="form-control" id="inptEmailAddress" value="${user.emailAddress}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorPassword1}">has-error</c:if>">
			    	<label for="inptPassword">Password</label>
			    	<input type="password" name="password1" class="form-control" id="inptPassword" value="${user.password}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorPassword2}">has-error</c:if>">
			    	<label for="inptPassword">Confirm Password</label>
			    	<input type="password" name="password2" class="form-control" id="inptPassword" value="${user.password}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
			    <label for="inptGender <c:if test="${!empty errorGender}">has-error</c:if>">Gender</label>
				<div class="radio">
			    	<label class="checkbox-inline">
			      		<input type="radio" name="gender" id="inptGenderF" value="F"   ${user.gender == 'F' ? 'checked' : ''}> Female
			      	</label>
			    	<label class="checkbox-inline">
			      		<input type="radio" name="gender" id="inptGenderM" value="M"  ${user.gender == 'M' ? 'checked' : ''}> Male
			      	</label>
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorDateOfBirth}">has-error</c:if>">
			    	<label for="inptDOB">Date of Birth</label>
			    	<input type="date" name="dateOfBirth" class="form-control" id="inptDOB" value="${user.dateOfBirth}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group <c:if test="${!empty errorStreetAddress}">has-error</c:if>">
			    <label for="inptStreetAddress">Street Address</label>
			    	<input type="text" name="streetAddress" class="form-control" id="inptStreetAddress" value="${user.streetAddress}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorCity}">has-error</c:if>">
			    	<label for="inptCity">City</label>
			    	<input type="text" name="city" class="form-control" id="inptCity" value="${user.city}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorPostalCode}">has-error</c:if>">
			    	<label for="inptPostalCode">Postal Code</label>
			    	<input type="text" name="postalCode" class="form-control" id="inptPostalCode" value="${user.postalCode}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorCountry}">has-error</c:if>">
			    	<label for="inptCountry">Country</label>
					<select name="country" class="form-control" id="inptCountry">
					  <option ${user.country == 'Canada' ? 'selected' : ''}>Canada</option>
					  <option ${user.country == 'United States of America' ? 'selected' : ''}>United States of America</option>
					</select>
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorProvince}">has-error</c:if>">
			    	<label for="inptProvince">Province / State</label>
					<select name="province" class="form-control" id="inptProvince">
					  <option ${user.province == 'AB' ? 'selected' : ''}>AB</option>
					  <option ${user.province == 'BC' ? 'selected' : ''}>BC</option>
					  <option ${user.province == 'MB' ? 'selected' : ''}>MB</option>
					  <option ${user.province == 'NB' ? 'selected' : ''}>NB</option>
					  <option ${user.province == 'NL' ? 'selected' : ''}>NL</option>
					  <option ${user.province == 'NS' ? 'selected' : ''}>NS</option>
					  <option ${user.province == 'NT' ? 'selected' : ''}>NT</option>
					  <option ${user.province == 'NU' ? 'selected' : ''}>NU</option>
					  <option ${user.province == 'ON' ? 'selected' : ''}>ON</option>
					  <option ${user.province == 'PE' ? 'selected' : ''}>PE</option>
					  <option ${user.province == 'QC' ? 'selected' : ''}>QC</option>
					  <option ${user.province == 'SK' ? 'selected' : ''}>SK</option>
					  <option ${user.province == 'YT' ? 'selected' : ''}>YT</option>
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
			    	<input type="text" name="emergencyContactName" class="form-control" id="inptEmergencyContactName" value="${user.emergencyContactName}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorEmergencyContactPhoneNumber}">has-error</c:if>">
			    	<label for="inptEmergencyContactNumber">Emergency Contact Number</label>
			    	<input type="text" name="emergencyContactPhoneNumber" class="form-control" id="inptEmergencyContactNumber" value="${user.emergencyContactPhoneNumber}">
			  	</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<input type="hidden" name="userID" value="${user.userid}">
					<input type="hidden" name="option" value="edit">
			    	<input type="submit" class="btn btn-default" value="Update Profile">
			  	</div>
			</div>
		</div>
	</form>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>