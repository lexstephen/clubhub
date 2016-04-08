<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayUsers.jsp - HTML formatting for BatchUsers.jsp
 --%>

<tr>
	<td class="col-xs-12 col-md-1 checkbox"><c:choose>
			<c:when test="${(isAdmin == true)}">
				<input type="checkbox" name="userSelected" value="${user.userid}">
			</c:when>
		</c:choose></td>
	<td class="col-xs-12 col-md-3 control-label">${user.username}</td>
	<td class="col-xs-12 col-md-2">${user.userStatus}</td>
	<td class="col-xs-12 col-md-2">${user.firstName}</td>
	<td class="col-xs-12 col-md-2">${user.lastName}</td>
	<td class="col-xs-12 col-md-2">
		<span class="expand"> 
			<a href="${pageContext.request.contextPath}/Profile.jsp?userID=${user.userid}" class="btn btn-info btn-xs">View</a>
		</span>
	</td>
</tr>
