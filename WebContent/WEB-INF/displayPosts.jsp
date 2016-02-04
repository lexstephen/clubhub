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
				<!-- <span class="expand"> -->
					<form action="/clubhub/admin/EditPost.jsp" method="post" class="expand">
						<input type="hidden" name="postID" value="${post.id}">
						<input class="btn btn-primary btn-xs" type="submit" value="Edit">
					</form>
					<form action="/clubhub/Post.jsp" method="post" class="expand">
						<input type="hidden" name="postID" value="${post.id}">
						<input type="hidden" name="blogTitle" value="${post.title}">
						<input class="btn btn-link" type="submit" value="More">
					</form>
				<!-- </span> -->
			<hr>
		</div>
	</div>