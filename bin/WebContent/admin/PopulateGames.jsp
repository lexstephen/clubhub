<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 03, 2016
	Description: PopulateGames.jsp
 --%>

<%@ page import="utilities.GameDao"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% GameDao game = new GameDao();
Object id = session.getAttribute("seasonID");
String str = id.toString();
int seasonID = Integer.parseInt(str);
//String str = request.getParameter("seasonID");
//int seasonID = Integer.parseInt(str);
System.out.println("The current season ID is: " + seasonID);
game.findGameSet(request, seasonID);
%>

<% request.setAttribute("thisPage", "Populate Games"); %>


<form action="/clubhub/GameController" method="post" class="form"
	role="form">
	<h3>Below is a list of the games you have just created:</h3>

	<c:forEach items="${games}" var="game">
		<%@ include file="/WEB-INF/displayGames.jsp"%>
	</c:forEach>
</form>


<%@ include file="/WEB-INF/footer_backend.jsp"%>