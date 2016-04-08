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
				href="/clubhub/admin/EditGame.jsp?gameID=${game.gameid}">14</a></td>
			<td class="col-xs-12 col-md-2">2016-03-02</td>
		</tr>

		<tr>
			<td class="col-xs-12 col-md-3 control-label"><a
				href="/clubhub/admin/EditGame.jsp?gameID=${game.gameid}">15</a></td>
			<td class="col-xs-12 col-md-2">2016-03-09</td>
		</tr>

		<tr>
			<td class="col-xs-12 col-md-3 control-label"><a
				href="/clubhub/admin/EditGame.jsp?gameID=${game.gameid}">17</a></td>
			<td class="col-xs-12 col-md-2">2016-03-12</td>
		</tr>

	</tbody>
</table>