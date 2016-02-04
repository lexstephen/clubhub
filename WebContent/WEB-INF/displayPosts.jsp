<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayPosts.jsp - HTML formatting for Main.jsp
 --%>
     
	<div class="row">
		<div class="col-xs-12">
			<h1>${post.title}</h1>
			<span class="postMeta">Post Type: ${post.postType} - Posted in ${post.category} by ${post.username} on Sept 7, 2015. Access level: ${post.accessLevel}</span>
			<p>"${post.content}".</p>
				<span class="expand">
					<a href="/clubhub/admin/EditPost.jsp?postID=${post.id}" class="btn btn-primary btn-xs">Edit</a>
					<a href="/clubhub/Post.jsp?postID=${post.id}" class="btn btn-primary btn-xs">More</a>
				</span>
			<hr>
		</div>
	</div>