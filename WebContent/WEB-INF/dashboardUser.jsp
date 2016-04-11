<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="utilities.GameDao"%>
<% GameDao slot = new GameDao();
slot.findAllOfUsersGames(request);%>

<div class="row">
	<div class="col-xs-12 col-sm-5 col-sm-offset-1">
		<h3>Upcoming Games</h3>
		<table class="table table-striped">
			<c:choose>
					<c:when test="${! empty upcomingGames}">
						<c:forEach items="${upcomingGames}" var="game" begin="0" end="2">
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
		<h3>Recent Games</h3>
		<table class="table table-striped">
			<c:choose>
				<c:when test="${! empty pastGames}">
					<c:forEach items="${pastGames}" var="game" begin="0" end="2">
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