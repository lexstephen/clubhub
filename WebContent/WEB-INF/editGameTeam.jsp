<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: editGameTeam.jsp - HTML formatting for Games View
 --%>
<%@ page import="utilities.UserDao"%>
<% 
	UserDao user = new UserDao();
	user.listAllUsers(request);
%>

<tr <c:if test="${tm.inConflict == true}">class="alert alert-danger" role="alert"></c:if>>
	<form action="/clubhub/GameController" method="post" class="form" role="form">
		<td class="col-md-2 col-xs-12 form-group"><a
			href="${pageContext.request.contextPath}/Profile.jsp?userID=${tm.userid }"><img
				src="${pageContext.request.contextPath}/ImageDao?t=profile&id=${tm.userid }"
				class="game_photo"></a></td>
		<td class="col-md-6 col-xs-12 form-group">
			<div class="row">
				<div class="col-xs-12">
					<select name="userID" class="form-control" id="inptUserID">
							<option value="${tm.userid}"
								${tm.userid == backup.userid ? 'selected' : ''}>${tm.firstName}
								${tm.lastName}</option>
						 <c:forEach items="${backupUsers}" var="backup">
							<option value="${backup.userid}"
								${tm.userid == backup.userid ? 'selected' : ''}>${backup.firstName}
								${backup.lastName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</td>
		<td class="col-md-4 col-xs-12">			
			<c:if test="${(isAdmin == true)}">
				<input type="hidden" name="gameID" value="${game.id}">
				<input type="hidden" name="currentPlayer" value="${tm.userid}">
				<button class="btn btn-primary btn-xs" name="option"
					value="switchPlayers" type="submit">Update</button>
			</c:if>
		</td>
	</form>
</tr>
