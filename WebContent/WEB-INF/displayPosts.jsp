<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<div class="text-justify">${blog.content_short}</div>
		<div class="expand postMeta">
			Posted in [${blog.category}] by ${blog.username} |
			<c:choose>
				<c:when test="${blog.accessLevel == 'Private'}">
					<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> | 
							</c:when>
				<c:when test="${blog.accessLevel == 'Members'}">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span> | 
							</c:when>
			</c:choose>
			${blog.postDate} <a
				href="${pageContext.request.contextPath}/Post.jsp?postID=${blog.id}"
				class="btn btn-primary btn-xs">More</a>
		</div>
		<hr>
	</div>
</div>