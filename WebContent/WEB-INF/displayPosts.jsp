<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayPosts.jsp - HTML formatting for Main.jsp
 --%>
     
	<div class="row">
		<div class="col-xs-12">
			<h2>${blog.title}</h2>
			<span class="postMeta">Posted in ${blog.category} by ${blog.username} on ${blog.postDate}. Access level: ${blog.accessLevel}</span>
			<p>"${blog.content_short}"</p>
				<span class="expand">
					<c:choose>
						<c:when test="${(isAdmin == true) && (blog.accessLevel != 'Private')}">
							<a href="${pageContext.request.contextPath}/admin/EditPost.jsp?postID=${blog.id}" class="btn btn-primary btn-xs">Edit</a>
						</c:when>
						<c:when test="${(postMatchesUser == true) && (blog.accessLevel == 'Private')}">
							<a href="${pageContext.request.contextPath}/admin/EditPost.jsp?postID=${blog.id}" class="btn btn-primary btn-xs">Edit</a>
						</c:when>
					</c:choose>
					<a href="${pageContext.request.contextPath}/Post.jsp?postID=${blog.id}" class="btn btn-primary btn-xs">More</a>
				</span>
			<hr>
		</div>
	</div>