<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayPosts.jsp - HTML formatting for Main.jsp
 --%>
     
	<div class="row">
		<div class="col-xs-12 col-md-2 control-label">${post.title}</div>
		<div class="col-xs-12 col-md-2">Post Type: ${post.postType}</div>
		<div class="col-xs-12 col-md-2">Category: ${post.category}</div>
		<div class="col-xs-12 col-md-2">Username: ${post.username}</div>
		<div class="col-xs-12 col-md-2">Access level: ${post.accessLevel}</div>

		<div class="col-xs-12 col-md-2">
				<span class="expand">
					<a href="/clubhub/admin/EditPost.jsp?postID=${post.id}" class="btn btn-primary btn-xs">Edit</a>
					<a href="/clubhub/Post.jsp?postID=${post.id}" class="btn btn-primary btn-xs">More</a>
				</span>
		</div>
	</div>