<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: Game.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.PostDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% GameDao game = new GameDao();
String gameID = request.getParameter("gameID");
game.findGame(request, gameID);
game.findTeamsForGames(request);
request.setAttribute("thisPage", "Game Details"); %>

<%@ include file="/WEB-INF/header_backend.jsp"%>
<div class="row">
		<h2>Season ${game.seasonId}: ${game.gender } ${game.season } ${game.year }</h2>
		<h3>Game ${game.id}: ${game.dayOfWeek}, ${game.scheduledDateFullYear} at ${game.startTime}</h3>
	</div>
	<div class="row">
		<div class="col-md-6 col-xs-12">
			<h3>
					Team A
					<c:if test="${winner == 'Team A' }"><strong> - Winners</strong></c:if>
					<c:if test="${winner == 'Tie' }"><strong> - Tie</strong></c:if>
					<c:if test="${winner == 'TBD' }"><strong> - TBD</strong></c:if>
			</h3>
			<table class="table table-hover sortable">
				<thead>
					<tr>
						<th class="col-md-4 col-xs-12 sorttable_nosort"></th>
						<th class="col-md-4 col-xs-12">Member</th>
						<th class="col-md-4 col-xs-12">Score</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teamA}" var="tm">
						<%@ include file="/WEB-INF/displayGameTeam.jsp" %>	
					</c:forEach>						
				</tbody>
				<tfoot>
					<tr>
						<th class="col-md-4 col-xs-12 sorttable_nosort">-</th>
						<th class="col-md-4 col-xs-12 sorttable_nosort">-</th>
						<th class="col-md-4 col-xs-12 sorttable_nosort">${teamAscore }</th>
					</tr>
				</tfoot>
		</table>	
		</div>
		
		<div class="col-md-6 col-xs-12">
			<h3>
				Team B
				<c:if test="${winner == 'Team B' }"><strong> - Winners</strong></c:if>
				<c:if test="${winner == 'Tie' }"><strong> - Tie</strong></c:if>
				<c:if test="${winner == 'TBD' }"><strong> - TBD</strong></c:if>
			</h3>
			<table class="table table-hover sortable">
				<thead>
					<tr>
						<th class="col-md-4 col-xs-12 sorttable_nosort"></th>
						<th class="col-md-4 col-xs-12">Member</th>
						<th class="col-md-4 col-xs-12">Score</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teamB}" var="tm">
						<%@ include file="/WEB-INF/displayGameTeam.jsp" %>	
					</c:forEach>										
				</tbody>
				<tfoot>
					<tr>
						<th class="col-md-4 col-xs-12 sorttable_nosort">-</th>
						<th class="col-md-4 col-xs-12 sorttable_nosort">-</th>
						<th class="col-md-4 col-xs-12 sorttable_nosort">${teamBscore }</th>
					</tr>
				</tfoot>
		</table>	
		</div>
		
	</div>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>