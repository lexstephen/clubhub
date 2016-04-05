<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="utilities.GameDao"%>
<% 
GameDao game = new GameDao();
game.findRecentGames(request);	
%>

			<table class="table table-hover sortable jumbotron">
			<thead>
				<tr>
					<th class="col-xs-12 col-md-3 control-label">Game ID</th>
					<th class="col-xs-12 col-md-2">Date</th>
					<th class="col-xs-12 col-md-2">Match</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${games}" var="game">
				<tr>
					<td class="col-xs-12 col-md-3 control-label"><a href="${pageContext.request.contextPath}/admin/EditGame.jsp?gameID=${game.id}">${game.id}</a></td>
					<td class="col-xs-12 col-md-2">${game.scheduledDate}</td>
					<td class="col-xs-12 col-md-2">${game.gender}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>