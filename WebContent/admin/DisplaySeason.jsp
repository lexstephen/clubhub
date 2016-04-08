<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 18, 2016
	Description: ListGames.jsp
 --%>

<% request.setAttribute("thisPage", "Display Season"); %>
<%@ page import="utilities.SeasonDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% 
String seasonID = request.getParameter("seasonID");
SeasonDao seasondao = new SeasonDao();
seasondao.findSeason(request,seasonID);
GameDao gamedao = new GameDao();
gamedao.findGameSet(request, seasonID); 
%>

<form action="/clubhub/GameController" method="post" class="form"
	role="form">
	S ${season.id}: ${season.year }-${season.gender }-${season.season } <b>${season.startDateFullYear	 }</b>
	${season.dayOfWeek } ${season.startTime } (${season.duration } weeks)<br>


	<c:forEach items="${games}" var="game">
		<a
			href="${pageContext.request.contextPath}/admin/Game.jsp?gameID=${game.id }"
			class="btn btn-default">Game ${game.week}</a> ${game.dayOfWeek}, ${game.scheduledDateFullYear} at ${game.startTime}<br>
	</c:forEach>
	<input type="hidden" name="slotID" value="542"> <input
		type="hidden" name="seasonID" value="${season.id}">
	<button type="submit" value="close" name="option">Close</button>
	<button type="submit" value="assign" name="option">Assign
		Players</button>

</form>






<%@ include file="/WEB-INF/footer_backend.jsp"%>