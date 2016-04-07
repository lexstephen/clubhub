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
<% GameDao game = new GameDao();
String gameID = request.getParameter("gameID");
game.findGame(request, gameID);
game.findTeamsForGames(request);
SeasonDao slot = new SeasonDao();
//Object id = session.getAttribute("loggedInUserID");
//String str = id.toString();
//int userID = Integer.parseInt(str);
Object theCuurrentPlayers = session.getAttribute("theCurrentPlayers");
Object theAvailablePlayers = session.getAttribute("theAvailablePlayers");

%>
<form action="/clubhub/GameController" method="post" class="form" role="form">
	<div class="row">
		<h2>Game ${game.id}: ${game.dayOfWeek}, ${game.scheduledDateFullYear} at ${game.startTime}
		<small>Season ${game.seasonId}: ${game.gender } ${game.season } ${game.year }</small>
		</h2>
	</div>
	<div class="row">
		<div class="col-md-6 col-xs-12">		
			<h3>
					Team A
					<c:if test="${winner == 'Team A' }"><strong> - Winners</strong></c:if>
					<c:if test="${winner == 'Tie' }"><strong> - Tie</strong></c:if>
			</h3>
			<table class="table table-hover sortable">
				<thead>
					<tr>
						<th class="col-md-2 col-xs-12 sorttable_nosort"></th>
						<th class="col-md-6 col-xs-12">Member</th>
						<th class="col-md-4 col-xs-12">Score</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teamA}" var="tm">
						<%@ include file="/WEB-INF/editGameTeam.jsp" %>	
					</c:forEach>						
				</tbody>
				<tfoot>
					<tr>
						<th class="col-md-2 col-xs-12 sorttable_nosort">-</th>
						<th class="col-md-6 col-xs-12 sorttable_nosort">-</th>
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
			</h3>
			<table class="table table-hover sortable">
				<thead>
					<tr>
						<th class="col-md-2 col-xs-12 sorttable_nosort"></th>
						<th class="col-md-6 col-xs-12">Member</th>
						<th class="col-md-4 col-xs-12">Score</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teamB}" var="tm">
						<%@ include file="/WEB-INF/editGameTeam.jsp" %>	
					</c:forEach>										
				</tbody>
				<tfoot>
					<tr>
						<th class="col-md-2 col-xs-12 sorttable_nosort">-</th>
						<th class="col-md-6 col-xs-12 sorttable_nosort">-</th>
						<th class="col-md-4 col-xs-12 sorttable_nosort">${teamBscore }</th>
					</tr>
				</tfoot>
		</table>	
		</div>
		<div class="row">
			<div class="col-xs-12 col-md-6">
				<c:forEach items="${theCurrentPlayers}" var="theCurrentPlayer">
						<input type="radio" name="currentPlayer" value="${theCurrentPlayer}" ><c:out value="${theCurrentPlayer}" /><br>
				</c:forEach>
			</div>
			<div class="col-xs-12 col-md-6">
					<c:forEach items="${theAvailablePlayers}" var="theAvailablePlayer">
						<input type="radio" name="newPlayer" value="${theAvailablePlayer}" ><c:out value="${theAvailablePlayer}" /><br>
					</c:forEach>
			</div>	
			<br><br>
			<button type="submit" value="switchThem">Switch</button>
		</div>
	</div>
</form>


<%@ include file="/WEB-INF/footer_backend.jsp" %>