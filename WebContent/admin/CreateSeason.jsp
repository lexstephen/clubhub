<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 6, 2016
	Description: CreateSeason.jsp
 --%>
 
<% request.setAttribute("thisPage", "Create Season"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>


<form action="/clubhub/SeasonController" method="post" class="form" role="form">
	<div class="form-group">
		<label class="col-sm-1 control-label">
			Season
		</label><br><br>
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
				<input type="radio" name="gender" value="F"> Womens <br>
				<input type="radio" name="gender" value="MF"> Mixed
		</div>
		<br><br><br><br>
		<div class="col-sm-6">
		
			<b>Start Date</b></b>
		<br>
			<input type="text" name="theDate"><p class="help-block">yyyy-mm-dd</p>
			
		</div>
		<br><br><br>
		<div class="col-sm-6">
		
			<b>Start Time</b></b>
		<br>
			<input class="col-sm-4" type="text" name="startTime" value="${startTime}">
		</div>
		
		<br>
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