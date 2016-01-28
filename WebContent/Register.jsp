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
	
	</form>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_public.jsp" %>