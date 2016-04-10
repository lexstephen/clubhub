<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayGameTeam.jsp - HTML formatting for Games View
 --%>

<tr <c:if test="${tm.inConflict == true}">class="alert alert-danger" role="alert"</c:if>>
	<td class="col-md-4 col-xs-12">
		<a href="${pageContext.request.contextPath}/Profile.jsp?userID=${tm.userid }">
			<img src="${pageContext.request.contextPath}/ImageDao?t=profile&id=${tm.userid }" class="game_photo"></a>
	</td>
	<td class="col-md-4 col-xs-12">
				${tm.firstName } ${tm.lastName }
	</td>
	<td class="col-md-4 col-xs-12">
	<form action="/clubhub/GameController" method="post" class="form" role="form">
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate" />
	    <c:if test="${(game.scheduledDateWithYear ge nowDate) && (tm.userid == loggedInUserID)}"> 
			<input type="hidden" name="gameID" value="${game.id}">
			<button type="submit" name="option" value="conflict" class="btn btn-primary">I can't play</button>
	    </c:if>
	</form>
	</td>
</tr>
