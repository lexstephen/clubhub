<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 03, 2016
	Description: displayPosts.jsp - HTML formatting for Updates.jsp and Search.jsp
 --%>

<tr>
	<td class="col-xs-12 col-md-3 control-label">${blog.title}</td>
	<td class="col-xs-12 col-md-2">${blog.category}</td>
	<td class="col-xs-12 col-md-2">${blog.username}</td>
	<td class="col-xs-12 col-md-3">${blog.accessLevel}</td>
	<td class="col-xs-12 col-md-2">
		<span class="expand">
			<c:if test="${(isAdmin == true)}">
				<a href="${pageContext.request.contextPath}/admin/EditPost.jsp?postID=${blog.id}" class="btn btn-primary btn-xs">Edit</a>
			</c:if>
		<a href="${pageContext.request.contextPath}/Post.jsp?postID=${blog.id}" class="btn btn-primary btn-xs">More</a>
		</span>
	</td>
</tr>