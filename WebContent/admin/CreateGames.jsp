<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 13, 2016
	Description: CreateGames.jsp
 --%>
 
<% request.setAttribute("thisPage", "Create Games"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="utilities.SeasonDao"%>

<% SeasonDao season = new SeasonDao();
Object id = session.getAttribute("seasonID");
String seasonID = id.toString();
System.out.println("The current season ID is: " + seasonID);
season.findSeason(request, seasonID);%>

<%@ include file="/WEB-INF/header_backend.jsp"%>

	<form action="${pageContext.request.contextPath}/SeasonController" method="post" class="form" role="form">
		
		<div class="col-sm-9">
			Please review the following information: (clicking continue will make this final!)

		<h1>Season Information:</h1>
			<br>
			
			<b>Year:</b> ${season.year}<br>
			<b>Season:</b> ${season.season}<br>
			<b>Gender:</b> ${season.gender}<br>
			<b>Start Date:</b> ${season.startDate}<br>
			<b>Start Time:</b> ${season.startTime}<br>
			<b>Day Of Week:</b> ${season.dayOfWeek}<br>
			<b>Duration:</b> ${season.duration}<br>
			<br>
			<button class="btn btn-info" type="submit" value="delete" name="option">Cancel</button>
			<button class="btn btn-info" type="submit" value="confirm" name="option"> Confirm</button>
		</div>
	</form>
		
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>