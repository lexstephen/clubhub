<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


			<table class="table table-hover sortable jumbotron">
			<thead>
				<tr>
					<th class="col-xs-12 col-md-3 control-label">Game ID</th>
					<th class="col-xs-12 col-md-2">Date</th>
					<th class="col-xs-12 col-md-2">Match</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td class="col-xs-12 col-md-3 control-label"><a href="/clubhub/admin/EditGame.jsp?gameID=${game.gameid}">4</a></td>
					<td class="col-xs-12 col-md-2">2016-02-04</td>
					<td class="col-xs-12 col-md-2">F</td>
				</tr>

				<tr>
					<td class="col-xs-12 col-md-3 control-label"><a href="/clubhub/admin/EditGame.jsp?gameID=${game.gameid}">3</a></td>
					<td class="col-xs-12 col-md-2">2016-02-03</td>
					<td class="col-xs-12 col-md-2">M</td>
				</tr>

				<tr>
					<td class="col-xs-12 col-md-3 control-label"><a href="/clubhub/admin/EditGame.jsp?gameID=${game.gameid}">2</a></td>
					<td class="col-xs-12 col-md-2">2016-02-02</td>
					<td class="col-xs-12 col-md-2">F</td>
				</tr>

			</tbody>
		</table>