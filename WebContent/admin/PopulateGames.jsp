<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 03, 2016
	Description: PopulateGames.jsp
 --%>
 
<% request.setAttribute("thisPage", "Populate Games"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>


<form action="/clubhub/SeasonController" method="post" class="form" role="form">
	<div class="form-group">
		<label class="col-sm-1 control-label">
			Season
		</label>
		<div class="col-sm-5">
			<select class="form-control" name="season">
				Season
				<option value="S">Spring</option>
				<option value="SM">Summer</option>				
				<option value="F">Fall</option>
				<option value="W">Winter</option>
			</select>
		</div>	
		<label class="col-sm-2 control-label">
			Gender
		</label>
		<div class="col-sm-3">

				<input type="radio" name="gender" value="M"> Mens <br>
				<input type="radio" name="gender" value="W"> Womens
		</div>
		<br><br><br><br><br><br>
		<div class="col-sm-6">
		
			<b>Start Date</b></b>
		<br>
			<input class="col-sm-4" type="text" name="startDate" value="${startDate}">
			<%--Insert a calander here --%>
		</div>
		
		<div class="col-sm-6">
		
			<b>Year</b></b>
		<br>
			<input class="col-sm-4" type="text" name="year" value="${year}">
			<%--Insert a calander here --%>
		</div>
		
		<div class="col-sm-6">
		
			<b>Start Time</b></b>
		<br>
			<input class="col-sm-4" type="text" name="startTime" value="${startTime}">
		</div>
		
		<div class="col-sm-6">
		
			<b>Day Of Week </b></b>
		<br>
			<input class="col-sm-4" type="text" name="dayOfWeek" value="${dayOfWeek}">
		</div>
		
		<br><br>
		<div class="col-sm-6">
		<br><br>
			<b>Season Duration(in weeks)</b></b>
		<br>
			<input class="col-sm-4" type="text" name="duration" value="${duration}">
		</div>
		<br><br><br><br>
		<br>
		<button class="btn btn-info" type="submit" value="add" name="option">Submit</button>
	</div>
</form>		
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>