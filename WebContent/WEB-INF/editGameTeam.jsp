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
<tr>
	<td class="col-md-2 col-xs-12 form-group"><a
		href="${pageContext.request.contextPath}/Profile.jsp?userID=${tm.userid }"><img
			src="${pageContext.request.contextPath}/ImageDao?t=profile&id=${tm.userid }"
			class="game_photo"></a></td>
	<td class="col-md-6 col-xs-12 form-group">
		<div class="row">
			<div class="col-xs-12">
				<select name="userID" class="form-control" id="inptUserID">
					<c:forEach items="${users}" var="user">
						<option value="${user.userid}"
							${tm.userid == user.userid ? 'selected' : ''}>${user.firstName}
							${user.lastName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</td>
	<td class="col-md-4 col-xs-12 form-group"><input type="text"
		class="form-control" value="${tm.score }"></td>
</tr>
