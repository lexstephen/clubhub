<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayGames.jsp - HTML formatting for DisplayGames.jsp
 --%>


<tr>
	<td class="col-md-1"><c:if test="${(isAdmin == true)}">
			<input type="checkbox" name="gameSelected" value="${game.id}">
		</c:if></td>
	<td class="col-md-1">${game.seasonId}</td>
	<td class="col-md-1">${game.id}</td>
	<td class="col-md-1">${game.gender}</td>
	<td class="col-md-1">${game.season}</td>
	<td class="col-md-1">${game.year}</td>
	<td class="col-md-1">${game.week}</td>
	<td class="col-md-1">${game.dayOfWeek}</td>
	<td class="col-md-1" sorttable_customkey="${game.scheduledDate}">${game.dateFormatted}</td>
	<td class="col-md-1"><span class="expand"> <c:if
				test="${(isAdmin == true) || (game.userid == loggedInUserID)}">
				<a
					href="${pageContext.request.contextPath}/admin/EditGame.jsp?gameID=${game.id}"
					class="btn btn-primary btn-xs">Edit</a>
			</c:if> <a
			href="${pageContext.request.contextPath}/admin/Game.jsp?gameID=${game.id}"
			class="btn btn-primary btn-xs">More</a>
	</span></td>
</tr>

