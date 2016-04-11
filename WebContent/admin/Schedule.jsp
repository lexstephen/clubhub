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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% GameDao slot = new GameDao();
slot.findAllOfUsersGames(request);
%>
<form action="/clubhub/GameController" method="post" class="form"
	role="form">
	<div class="row">
		<div class="col-xs-12 col-sm-5 col-sm-offset-1">
			<h3>Upcoming Games</h3>
			<table class="table table-striped">
				<c:choose>
					<c:when test="${! empty upcomingGames}">
						<c:forEach items="${upcomingGames}" var="game" >
							<tr>
								<td>
									<strong>Season ${game.seasonId}</strong>
								</td>
								<td> 
									<a
										href="${pageContext.request.contextPath}/admin/Game.jsp?gameID=${game.id }"
										class="btn btn-info btn-sm">Game ${game.week}</a>
								</td>
								<td>${game.dayOfWeek}, ${game.scheduledDateFullYear} at ${game.startTime}<br>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info"><strong>You have not been scheduled for any games yet!</strong></div>
					</c:otherwise>
				</c:choose>
			</table>
			</div>
			<div class="col-xs-12 col-sm-5 col-sm-offset-1">
			<h3>Past Games</h3>
			<table class="table table-striped">
				<c:choose>
					<c:when test="${! empty pastGames}">
						<c:forEach items="${pastGames}" var="game" >
							<tr>
								<td>
									<strong>Season ${game.seasonId}</strong>
								</td>
								<td> 
									<a
										href="${pageContext.request.contextPath}/admin/Game.jsp?gameID=${game.id }"
										class="btn btn-info btn-sm">Game ${game.week}</a>
								</td>
								<td>${game.dayOfWeek}, ${game.scheduledDateFullYear} at ${game.startTime}<br>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info"><strong>You have not been scheduled for any games yet!</strong></div>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</div>
</form>


<%@ include file="/WEB-INF/footer_backend.jsp"%>