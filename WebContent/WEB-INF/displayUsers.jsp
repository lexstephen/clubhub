<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayUsers.jsp - HTML formatting for BatchUsers.jsp
 --%>

<tr>
 	<td class="col-xs-12 col-md-1 checkbox"><input type="checkbox" name="userSelected" value="${user.userid}"></td>
	<td class="col-xs-12 col-md-3 control-label">${user.username}</td>
	<td class="col-xs-12 col-md-2">${user.userStatus}</td>
	<td class="col-xs-12 col-md-2">${user.firstName}</td>
	<td class="col-xs-12 col-md-2">${user.lastName}</td>
	<td class="col-xs-12 col-md-2">
		<span class="expand">
			<a href="/clubhub/admin/EditProfile.jsp?userID=${user.userid}" class="btn btn-primary btn-xs">Edit</a>
			<a href="/clubhub/Profile.jsp?userID=${user.userid}" class="btn btn-primary btn-xs">More</a>
		</span>
	</td>
</tr>
