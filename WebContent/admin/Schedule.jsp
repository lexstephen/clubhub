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
	<div class="col-md-5">
		<table class="table table-striped">
			<c:forEach items="${assignedGames}" var="game">
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
		</table>
	</div>
</form>


<%@ include file="/WEB-INF/footer_backend.jsp"%>