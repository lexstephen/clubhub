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
	
	<form>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptFirstName">First Name</label>
			    	<input type="text" class="form-control" id="inptFirstName" placeholder="First Name">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptLastName">Last Name</label>
			    	<input type="text" class="form-control" id="inptLastName" placeholder="Last Name">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptUserName">User Name</label>
			    	<input type="text" class="form-control" id="inptUserName" placeholder="User Name">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptPhoneNumber">Phone Number</label>
			    	<input type="text" class="form-control" id="inptPhoneNumber" placeholder="Phone Number">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptEmailAddress">Email Address</label>
			    	<input type="text" class="form-control" id="inptEmailAddress" placeholder="Email Address">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptEmailAddress2">Confirm Email Address</label>
			    	<input type="text" class="form-control" id="inptEmailAddress2" placeholder="Confirm Email Address">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptProvince">Province</label>
			    	<input type="text" class="form-control" id="inptProvince" placeholder="Province">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptProvince2">Confirm Province</label>
			    	<input type="text" class="form-control" id="inptProvince2" placeholder="Confirm Province">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
			    <label for="inptGender">Gender</label>
				<div class="radio">
			    	<label class="checkbox-inline">
			      		<input type="radio" name="gender" id="inptGenderF" value="F"> Female
			      	</label>
			    	<label class="checkbox-inline">
			      		<input type="radio" name="gender" id="inptGenderM" value="M"> Male
			      	</label>
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptDOB">Date of Birth</label>
			    	<input type="date" class="form-control" id="inptDOB">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
			    <label for="inptStreetAddress">Street</label>
			    	<input type="text" class="form-control" id="inptStreetAddress" placeholder="123 Fake Street">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptCity">City</label>
			    	<input type="text" class="form-control" id="inptCity" placeholder="City">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptProvince">Confirm Province</label>
			    	<input type="text" class="form-control" id="inptProvince" placeholder="Confirm Province">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptPostalCode">Postal Code</label>
			    	<input type="text" class="form-control" id="inptPostalCode" placeholder="Postal Code">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptCountry">Confirm Country</label>
					<select class="form-control" id="inptCountry">
					  <option>Canada</option>
					  <option>United States of America</option>
					</select>
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
			    	<label for="inptProfilePhoto">Profile Photo</label>
			    	<input type="file" class="form-control" id="inptProfilePhoto" placeholder="Postal Code">
	    			<p class="help-block">Upload a square jpg or png.</p>
			  	</div>
			</div>
			<div class="col-xs-6">
		    	<label>Preview</label>
				<img src="/clubhub/images/avatar.gif">
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
			    	<input type="submit" class="form-control" value="Submit Registration">
			  	</div>
			</div>
		</div>
	</form>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_public.jsp" %>