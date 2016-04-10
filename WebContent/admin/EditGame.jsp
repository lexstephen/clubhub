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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%
	GameDao game = new GameDao();
	String gameID = request.getParameter("gameID");
	game.findGame(request, gameID);
	game.findTeamsForGames(request, response);
	game.findAvailableUsersWhoArentScheduled(request, response, gameID);
	request.setAttribute("thisPage", "Edit Game Details");
%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
	<jsp:useBean id="now" class="java.util.Date"/>
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate" />
	<c:if test="${isAdmin == false}">
		<c:redirect url="index.jsp" />
	</c:if>
	<div class="row">
		<h3>Game ${game.week}: ${game.dayOfWeek}, ${game.scheduledDateFullYear}
			at ${game.startTime} <small>Season ${game.seasonId}:
				${game.gender } ${game.season } ${game.year }</small></h3>
		<hr>
		<form action="/clubhub/GameController" method="post" class="form" role="form">
			<div class="row">
				<div class="col-md-3 col-md-offset-3 col-xs-12">
					<div class="row">
						<div class="col-xs-6">
							<label>Team A score: </label>
						</div>
						<div class="col-xs-6">
							<input class="form-control col-xs-2" maxlength="3" type="text"
								name="teamAscore"
								value="<c:choose><c:when test="${!empty teamAscore}">${teamAscore}</c:when><c:otherwise>0</c:otherwise></c:choose>">
						</div>
					</div>
				</div>
				<div class="col-md-3 col-xs-12">
					<div class="row">
						<div class="col-xs-6">
							<label>Team B score: </label>
						</div>
						<div class="col-xs-6">
							<input class="form-control col-xs-2" maxlength="3" type="text"
								name="teamBscore"
								value="<c:choose><c:when test="${!empty teamBscore}">${teamBscore}</c:when><c:otherwise>0</c:otherwise></c:choose>">
						</div>
					</div>
				</div>
				<div class="col-md-3 col-xs-12">
					<c:if test="${(isAdmin == true)}">
						<input type="hidden" name="gameID" value="${game.id}">
						<button class="btn btn-primary btn-xs" name="option"
							value="editScores" type="submit">Update</button>
					</c:if>
				</div>
			</div>
		</form>
		<hr>

		<form action="/clubhub/GameController" method="post" class="form" role="form">
			<div class="row">
				<div class="col-md-6 col-xs-12">
					<table class="table table-hover sortable">
						<thead>
							<tr>
								<th class="col-md-4 col-xs-12 sorttable_nosort">Team A</th>
								<th class="col-md-4 col-xs-12">Member</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${teamA}" var="tm">
								<%@ include file="/WEB-INF/displayEditGameTeam.jsp"%>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="col-md-6 col-xs-12">
					<table class="table table-hover sortable">
						<thead>
							<tr>
								<th class="col-md-4 col-xs-12 sorttable_nosort">Team B</th>
								<th class="col-md-4 col-xs-12">Member</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${teamB}" var="tm">
								<%@ include file="/WEB-INF/displayEditGameTeam.jsp"%>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp"%>