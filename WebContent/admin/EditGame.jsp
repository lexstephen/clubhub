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
request.setAttribute("thisPage", "Edit Game Details"); %>

<%@ include file="/WEB-INF/header_backend.jsp"%>
<form action="${pageContext.request.contextPath}/GameController" method="post" class="form" role="form">
	<div class="row">
		<h2>Season ${game.seasonId}: ${game.gender } ${game.season } ${game.year }</h2>
		<h3>Game ${game.id}: ${game.dayOfWeek}, ${game.scheduledDateFullYear} at ${game.startTime}</h3>
	</div>
	<div class="row">
		<div class="col-md-6 col-xs-12">
			<h3>
					Team A				
			</h3>
			<div class="row">
				<div class="col-xs-3">
					<label>Team A score: </label>
				</div>
				<div class="col-xs-2">	
					<input class="form-control col-xs-2" maxlength="3" type="text" name="teamAscore" 
						value="<c:choose><c:when test="${!empty teamAscore}">${teamAscore}</c:when><c:otherwise>0</c:otherwise></c:choose>">
				</div>	
			</div>			
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
			</h3>
			<div class="row">
				<div class="col-xs-3">
					<label>Team B score: </label>
				</div>
				<div class="col-xs-2">	
					<input class="form-control col-xs-2" maxlength="3" type="text" name="teamBscore" 
						value="<c:choose><c:when test="${!empty teamBscore}">${teamBscore}</c:when><c:otherwise>0</c:otherwise></c:choose>">
				</div>	
			</div>	
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
		<c:if test="${(isAdmin == true)}">
			<input type="hidden" name="gameID" value="${game.id}">
			<button class="btn btn-primary btn-xs" name="option" value="editScores" type="submit">Update</button>
		</c:if>
		
	</div>
</form>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>