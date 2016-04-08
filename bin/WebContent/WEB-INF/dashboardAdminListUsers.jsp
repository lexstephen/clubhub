<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="utilities.UserDao"%>

<%	UserDao user = new UserDao(); %>

<% user.getLastUsers(request, response); %>
<table class="table table-hover sortable jumbotron">
	<thead>
		<tr>
			<th class="col-xs-12 col-md-3 control-label">User</th>
			<th class="col-xs-12 col-md-2">Status</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td class="col-xs-12 col-md-3 control-label"><a
					href="/clubhub/admin/EditProfile.jsp?userID=${user.userid}">${user.firstName}
						${user.lastName}</a></td>
				<td class="col-xs-12 col-md-2">${user.userStatus}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>