<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayUsers.jsp - HTML formatting for Main.jsp
 --%>
     
	<div class="row">
		<div class="col-xs-12 col-md-2">
			<b>${user.username}</b>
		</div>
		<div class="col-xs-12 col-md-2">
			<span class="userMeta">User Type: ${user.userStatus}</span>
		</div>
		<div class="col-xs-12 col-md-2">
			<p>${user.firstName}
		</div>
		<div class="col-xs-12 col-md-2"> ${user.lastName}</p>
				<span class="expand">
					<a href="/clubhub/admin/EditProfile.jsp?userID=${user.userid}" class="btn btn-primary btn-xs">Edit</a>
					<a href="/clubhub/Profile.jsp?userID=${user.userid}" class="btn btn-primary btn-xs">More</a>
				</span>
			<hr>
		</div>
	</div>