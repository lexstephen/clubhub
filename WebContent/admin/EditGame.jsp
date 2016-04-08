<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: EditGame.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.PostDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% GameDao game = new GameDao();
String gameID = request.getParameter("gameID");
game.findGame(request, gameID);
game.findTeamsForGames(request);
request.setAttribute("thisPage", "Edit Game Details"); 
%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
	<div class="row">
		<h3>
			Game ${game.week}: ${game.dayOfWeek}, ${game.scheduledDateFullYear} at ${game.startTime}
			<small>Season ${game.seasonId}: ${game.gender } ${game.season } ${game.year }</small>
		</h3>
		
		<p>
			<form action="/clubhub/GameController" method="post" class="form" role="form">
			<p>(This should only display if the user is scheduled for this game)
				Can't make this game? Let the admin know:
				<input type="hidden" name="gameID" value="${game.id}">
				<button type="submit" name="option" value="conflict">Switch</button>
			</form>
		</p>

<form action="/clubhub/GameController" method="post" class="form" role="form">
	<div class="row">
		<div class="col-md-6 col-xs-12">
			<h3>
					Team A
					<input class="form-control" maxlength="50" type="text" name="teamAedit" value="<c:if test="${!empty teamAscore}">${teamAscore}</c:if>">					
			</h3>
			<table class="table table-hover sortable">
				<thead>
					<tr>
						<th class="col-md-4 col-xs-12 sorttable_nosort"></th>
						<th class="col-md-4 col-xs-12">Member</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teamA}" var="tm">
						<%@ include file="/WEB-INF/displayGameTeam.jsp" %>	
					</c:forEach>						
				</tbody>
		</table>	
		</div>
		
		<div class="col-md-6 col-xs-12">
			<h3>
				Team B
				<c:if test="${winner == 'Team B' }"><strong> - Winners - ${teamBscore}</strong></c:if>
				<c:if test="${winner == 'Tie' }"><strong> - Tie - ${teamBscore}</strong></c:if>
				<c:if test="${winner == 'TBD' }"><strong> - TBD</strong></c:if>
			</h3>
			<table class="table table-hover sortable">
				<thead>
					<tr>
						<th class="col-md-4 col-xs-12 sorttable_nosort"></th>
						<th class="col-md-4 col-xs-12">Member</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teamB}" var="tm">
						<%@ include file="/WEB-INF/displayGameTeam.jsp" %>	
					</c:forEach>										
				</tbody>
		</table>	
		</div>

		<div class="row">
			<button type="submit" value="switchThem">Switch</button>
		</div>
	</div>
</form>
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>