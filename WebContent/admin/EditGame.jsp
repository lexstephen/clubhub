<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 18, 2016
	Description: ListGames.jsp
 --%>
 
<% request.setAttribute("thisPage", "Edit Game"); %>
<%@ page import="utilities.SeasonDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% SeasonDao slot = new SeasonDao();
Object gameID = session.getAttribute("gameID");

Object theCurrentPlayers = session.getAttribute("theCurrentPlayers");
Object theAvailablePlayers = session.getAttribute("theAvailablePlayers");


%>
<div class="row">
<form action="/clubhub/GameController" method="post" class="form" role="form">
	<div class="col-xs-12 col-md-6">
	
		<c:forEach items="${theCurrentPlayers}" var="theCurrentPlayers">
			
				<input type="radio" name="currentPlayer" value="${theCurrentPlayers}" ><c:out value="${theCurrentPlayers}" /><br>
				
		</c:forEach>
	
	</div>
	<div >
		
			<c:forEach items="${theAvailablePlayers}" var="theAvailablePlayers">
				<input type="radio" name="newPlayer" value="${theAvailablePlayers}" ><c:out value="${theAvailablePlayers}" /><br>
			</c:forEach>
		
	</div>	
	<br><br>
	<input type="hidden" name="gameID" value="${gameID}">
	<button type="submit" value="switchThem" name="option">Switch</button>
</form>
</div>
	




<%@ include file="/WEB-INF/footer_backend.jsp" %>