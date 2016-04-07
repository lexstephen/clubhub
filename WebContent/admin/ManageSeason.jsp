<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 18, 2016
	Description: ListGames.jsp
 --%>
 
<% request.setAttribute("thisPage", "Manage Season"); %>
<%@ page import="utilities.SeasonDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% SeasonDao slot = new SeasonDao();
//Object id = session.getAttribute("loggedInUserID");
//String str = id.toString();
//int userID = Integer.parseInt(str);
String seasonID = request.getParameter("seasonID");
System.out.println("The current season ID is: " + seasonID);
slot.listSeasonWithStatus(request);

%>


	<h3><u> ${dayOfWeek} at ${time}</u> </h3>
	
	<c:forEach items="${seasons}" var="season">
		<form action="/clubhub/GameController" method="post" class="form" role="form">
			S ${season.id}: ${season.year }-${season.gender }-${season.season } <b>${season.startDateFullYear	 }</b> ${season.dayOfWeek } ${season.startTime } (${season.duration } weeks) 
			
				<input type="hidden" name="seasonID" value="${season.id}">
				<button type="submit" value="close" name="option">Close</button>
				<button type="submit" value="assign" name="option">Assign Players</button>
			
		</form>
	</c:forEach>
	
	




<%@ include file="/WEB-INF/footer_backend.jsp" %>