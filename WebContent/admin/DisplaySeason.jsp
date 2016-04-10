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


		<h3>
	S#${season.id}: ${season.year }-${season.gender }-${season.season } | <small>
	${season.dayOfWeek }s at ${season.startTime } (${season.duration } weeks)</small><br><br>


	<c:forEach items="${games}" var="game">
		<a
			href="${pageContext.request.contextPath}/admin/Game.jsp?gameID=${game.id }"
			class="form-control btn btn-info">Game ${game.week} - ${game.scheduledDateFullYear}</a> 
	</c:forEach>






<%@ include file="/WEB-INF/footer_backend.jsp"%>