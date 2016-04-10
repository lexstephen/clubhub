<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-hover sortable jumbotron">
	<thead>
		<tr>
			<th class="col-xs-12 col-md-3 control-label">Game ID</th>
			<th class="col-xs-12 col-md-2">Date</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="col-xs-12 col-md-3 control-label"><a
				href="${pageContext.request.contextPath}/admin/EditGame.jsp?gameID=${game.gameid}">${game.gameid}</a></td>
			<td class="col-xs-12 col-md-2">${game.scheduledDate }</td>
		</tr>
	</tbody>
</table>