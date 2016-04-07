<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 11, 2016
	Description: Availibility.jsp
 --%>
 
<% request.setAttribute("thisPage", "Schedule"); %>
<%@ page import="utilities.GameDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% GameDao slot = new GameDao();
slot.findAllOfUsersGames(request);
%>
<form action="/clubhub/GameController" method="post" class="form" role="form">
	<c:forEach items="${assignedGames}" var="game">
		<a href="${pageContext.request.contextPath}/admin/Game.jsp?gameID=${game.id }" class="btn btn-primary">Game ${game.week}</a> ${game.dayOfWeek}, ${game.scheduledDateFullYear} at ${game.startTime}<br>
	</c:forEach>
</form>


<%@ include file="/WEB-INF/footer_backend.jsp" %>