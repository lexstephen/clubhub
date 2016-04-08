<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 13, 2016
	Description: CreateGames.jsp
 --%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="utilities.SeasonDao"%>

<% SeasonDao season = new SeasonDao();
Object id = session.getAttribute("seasonID");
String seasonID = id.toString();
System.out.println("The current season ID is: " + seasonID);
season.findSeason(request, seasonID);%>
<% request.setAttribute("thisPage", "Confirm New Season"); %>

<%@ include file="/WEB-INF/header_backend.jsp"%>

<form action="${pageContext.request.contextPath}/SeasonController"
	method="post" class="form" role="form">

	<div class="col-md-6 col-md-offset-3">
		<p>Please review the following information:</p>
		<div class="row">
			<label class="col-md-3">Year</label> ${season.year}
		</div>
		<div class="row">
			<label class="col-md-3">Season</label> ${season.season}
		</div>
		<div class="row">
			<label class="col-md-3">Gender</label> ${season.gender}
		</div>
		<div class="row">
			<label class="col-md-3">Start Date</label>
			${season.startDateFullYear}
		</div>
		<div class="row">
			<label class="col-md-3">Start Time</label> ${season.startTime}
		</div>
		<div class="row">
			<label class="col-md-3">Day Of Week</label> ${season.dayOfWeek}
		</div>
		<div class="row">
			<label class="col-md-3">Duration</label> ${season.duration} weeks
		</div>
		<p>Click Confirm to create games, or Cancel to return to the
			season creation screen.</p>
		<button class="btn btn-info" type="submit" value="confirm"
			name="option">Confirm</button>
		<button class="btn btn-danger" type="submit" value="delete"	name="option">Cancel</button>
	</div>
</form>


<%@ include file="/WEB-INF/footer_backend.jsp"%>