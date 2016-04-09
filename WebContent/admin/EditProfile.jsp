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
<%@ page import="utilities.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->

<%	
		UserDao user = new UserDao();
		String userID = null;
		if (request.getParameter("userID") != null) {
			userID = request.getParameter("userID");
		}		
		user.findUser(request, userID);
	%>

<c:if test="${!empty errorString}">
	<div class="alert alert-danger" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		<span class="sr-only">Error:</span> ${errorString }
		<c:if test="${!empty errorString2}">
			<li>${ errorString2 }</li>
		</c:if>
		<c:if test="${!empty errorString3}">
			<li>${ errorString3 }</li>
		</c:if>
		<c:if test="${!empty errorString4}">
			<li>${ errorString4 }</li>
		</c:if>
	</div>
</c:if>
<c:if test="${!empty successString}">
	<div class="alert alert-success" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		<span class="sr-only">Success:</span> ${successString }
	</div>
</c:if>

<form action="${pageContext.request.contextPath}/UserController"
	method="post" class="form" role="form" enctype="multipart/form-data">
	<div class="row">
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorFirstName}">has-error</c:if>">
				<label for="inptFirstName">First Name</label> <input required type="text"
					name="firstName" class="form-control" id="inptFirstName"
					value="${user.firstName}">
			</div>
		</div>
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorLastName}">has-error</c:if>">
				<label for="inptLastName">Last Name</label> <input required type="text"
					name="lastName" class="form-control" id="inptLastName"
					value="${user.lastName}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorUsername}">has-error</c:if>">
				<label for="inptUserName">User Name</label> <input required type="text"
					name="username" class="form-control" id="inptUserName"
					value="${user.username}">
			</div>
		</div>
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorTelephone}">has-error</c:if>">
				<label for="inptPhoneNumber">Phone Number</label> <input required type="text" maxlength="10" 
					name="telephone" class="form-control" id="inptPhoneNumber"
					value="${user.telephone}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorEmail1}">has-error</c:if>">
				<label for="inptEmailAddress">Email Address</label> <input required
					type="text" name="emailAddress" class="form-control"
					id="inptEmailAddress" value="${user.emailAddress}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorPassword1}">has-error</c:if>">
				<label for="inptPassword">Password</label> <input required type="password"
					name="password" class="form-control" id="inptPassword"
					value="${user.password}">
			</div>
		</div>
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorPassword2}">has-error</c:if>">
				<label for="inptPassword">Confirm Password</label> <input required
					type="password" name="password2" class="form-control"
					id="inptPassword" value="${user.password}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-6">
			<label
				for="inptGender <c:if test="${!empty errorGender}">has-error</c:if>">Gender</label>
			<div class="radio">
				<label class="checkbox-inline"> <input required type="radio"
					name="gender" id="inptGenderF" value="F"
					${user.gender == 'F' ? 'checked' : ''}> Female
				</label> <label class="checkbox-inline"> <input required type="radio"
					name="gender" id="inptGenderM" value="M"
					${user.gender == 'M' ? 'checked' : ''}> Male
				</label>
			</div>
		</div>
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorDateOfBirth}">has-error</c:if>">
				<label for="inptDOB">Date of Birth</label> <input required type="date"
					name="dateOfBirth" class="form-control" id="inptDOB"
					value="${user.dateOfBirth}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<div
				class="form-group <c:if test="${!empty errorStreetAddress}">has-error</c:if>">
				<label for="inptStreetAddress">Street Address</label> <input required
					type="text" name="streetAddress" class="form-control"
					id="inptStreetAddress" value="${user.streetAddress}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorCity}">has-error</c:if>">
				<label for="inptCity">City</label> <input required type="text" name="city"
					class="form-control" id="inptCity" value="${user.city}">
			</div>
		</div>
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorPostalCode}">has-error</c:if>">
				<label for="inptPostalCode" id="lblPostalCode"
					${user.country == 'United States of America' ? ' class="hiddenest"' : ''}>
					Postal Code </label> <label for="inptProvince" id="lblZipCode"
					${user.country == 'Canada' ? ' class="hiddenest"' : ''}
					${user.country == null ? ' class="hiddenest"' : ''}> Zip
					Code </label> <input required type="text" name="postalCode" class="form-control"
					id="inptPostalCode" value="${user.postalCode}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorCountry}">has-error</c:if>">
				<label for="inptCountry">Country</label> <select name="country"
					class="form-control" id="inptCountry">
					<option ${user.country == 'Canada' ? 'selected' : ''}>Canada</option>
					<option
						${user.country == 'United States of America' ? 'selected' : ''}>United
						States of America</option>
				</select>
			</div>
		</div>
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorProvince}">has-error</c:if>">
				<label for="inptProvince" id="lblProvince"
					${user.country == 'United States of America' ? ' class="hiddenest"' : ''}>
					Province </label> <label for="inptState" id="lblState"
					${user.country == 'Canada' ? ' class="hiddenest"' : ''}
					${user.country == null ? ' class="hiddenest"' : ''}> State
				</label> <select name="province"
					class="form-control ${user.country == 'United States of America' ? ' hiddenest' : ''}"
					id="inptProvince">
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
				</select> <select name="state"
					class="form-control ${user.country == 'Canada' ? ' hiddenest' : ''}"
					id="inptState">
					<option ${user.province == 'AK' ? 'selected' : ''}>AK</option>
					<option ${user.province == 'AL' ? 'selected' : ''}>AL</option>
					<option ${user.province == 'AR' ? 'selected' : ''}>AR</option>
					<option ${user.province == 'AZ' ? 'selected' : ''}>AZ</option>
					<option ${user.province == 'CA' ? 'selected' : ''}>CA</option>
					<option ${user.province == 'CO' ? 'selected' : ''}>CO</option>
					<option ${user.province == 'CT' ? 'selected' : ''}>CT</option>
					<option ${user.province == 'DC' ? 'selected' : ''}>DC</option>
					<option ${user.province == 'DE' ? 'selected' : ''}>DE</option>
					<option ${user.province == 'FL' ? 'selected' : ''}>FL</option>
					<option ${user.province == 'GA' ? 'selected' : ''}>GA</option>
					<option ${user.province == 'HI' ? 'selected' : ''}>HI</option>
					<option ${user.province == 'IA' ? 'selected' : ''}>IA</option>
					<option ${user.province == 'ID' ? 'selected' : ''}>ID</option>
					<option ${user.province == 'IL' ? 'selected' : ''}>IL</option>
					<option ${user.province == 'IN' ? 'selected' : ''}>IN</option>
					<option ${user.province == 'KS' ? 'selected' : ''}>KS</option>
					<option ${user.province == 'KY' ? 'selected' : ''}>KY</option>
					<option ${user.province == 'LA' ? 'selected' : ''}>LA</option>
					<option ${user.province == 'MA' ? 'selected' : ''}>MA</option>
					<option ${user.province == 'MD' ? 'selected' : ''}>MD</option>
					<option ${user.province == 'ME' ? 'selected' : ''}>ME</option>
					<option ${user.province == 'MI' ? 'selected' : ''}>MI</option>
					<option ${user.province == 'MN' ? 'selected' : ''}>MN</option>
					<option ${user.province == 'MO' ? 'selected' : ''}>MO</option>
					<option ${user.province == 'MS' ? 'selected' : ''}>MS</option>
					<option ${user.province == 'MT' ? 'selected' : ''}>MT</option>
					<option ${user.province == 'NC' ? 'selected' : ''}>NC</option>
					<option ${user.province == 'ND' ? 'selected' : ''}>ND</option>
					<option ${user.province == 'NE' ? 'selected' : ''}>NE</option>
					<option ${user.province == 'NH' ? 'selected' : ''}>NH</option>
					<option ${user.province == 'NJ' ? 'selected' : ''}>NJ</option>
					<option ${user.province == 'NM' ? 'selected' : ''}>NM</option>
					<option ${user.province == 'NV' ? 'selected' : ''}>NV</option>
					<option ${user.province == 'NY' ? 'selected' : ''}>NY</option>
					<option ${user.province == 'OH' ? 'selected' : ''}>OH</option>
					<option ${user.province == 'OK' ? 'selected' : ''}>OK</option>
					<option ${user.province == 'OR' ? 'selected' : ''}>OR</option>
					<option ${user.province == 'PA' ? 'selected' : ''}>PA</option>
					<option ${user.province == 'RI' ? 'selected' : ''}>RI</option>
					<option ${user.province == 'SC' ? 'selected' : ''}>SC</option>
					<option ${user.province == 'SD' ? 'selected' : ''}>SD</option>
					<option ${user.province == 'TN' ? 'selected' : ''}>TN</option>
					<option ${user.province == 'TX' ? 'selected' : ''}>TX</option>
					<option ${user.province == 'UT' ? 'selected' : ''}>UT</option>
					<option ${user.province == 'VA' ? 'selected' : ''}>VA</option>
					<option ${user.province == 'VT' ? 'selected' : ''}>VT</option>
					<option ${user.province == 'WA' ? 'selected' : ''}>WA</option>
					<option ${user.province == 'WI' ? 'selected' : ''}>WI</option>
					<option ${user.province == 'WV' ? 'selected' : ''}>WV</option>
					<option ${user.province == 'WY' ? 'selected' : ''}>WY</option>
				</select>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<div
				class="form-group <c:if test="${!empty errorProfilePhoto}">has-error</c:if>">
				<label for="inptProfilePhoto">Profile Photo</label> <input 
					type="file" name="profilePhoto" class="form-control file"
					id="inptProfilePhoto">
				<p class="help-block">Upload a square jpg.</p>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorEmergencyContactName}">has-error</c:if>">
				<label for="inptEmergencyContactName">Emergency Contact Name</label>
				<input required type="text" name="emergencyContactName" class="form-control"
					id="inptEmergencyContactName" value="${user.emergencyContactName}">
			</div>
		</div>
		<div class="col-xs-6">
			<div
				class="form-group <c:if test="${!empty errorEmergencyContactPhoneNumber}">has-error</c:if>">
				<label for="inptEmergencyContactNumber">Emergency Contact
					Number</label> <input required type="text" maxlength="10" name="emergencyContactPhoneNumber"
					class="form-control" id="inptEmergencyContactNumber"
					value="${user.emergencyContactPhoneNumber}">
			</div>
		</div>
	</div>

	<c:if test="${isAdmin == true}">
		<div class="row">
			<div class="col-xs-6">
				<div
					class="form-group <c:if test="${!empty errorUserStatus}">has-error</c:if>">
					<label for="inptUserStatus">User Status</label> <select
						name="userStatus" class="form-control" id="inptUserStatus">
						<option ${user.userStatus == 'admin' ? 'selected' : ''}>admin</option>
						<option ${user.userStatus == 'user' ? 'selected' : ''}>user</option>
					</select>
				</div>
			</div>
		</div>
	</c:if>

	<div class="row">
		<div class="col-md-2 col-xs-12">
			<label>&nbsp;</label>
			<div class="form-group">
				<input  type="hidden" name="userID" value="${user.userid}"> <input 
					type="hidden" name="option" value="edit"> <input 
					type="submit" class="btn btn-info" value="Update Profile">
			</form>
		</div>
	</div>
</div>


<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp"%>