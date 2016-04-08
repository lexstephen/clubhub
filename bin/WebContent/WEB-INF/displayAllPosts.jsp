<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayPosts.jsp - HTML formatting for BatchPosts.jsp
 --%>


<tr>
	<td class="col-xs-12 col-md-1"><c:if test="${(isAdmin == true)}">
			<input type="checkbox" name="postSelected" value="${post.id}">
		</c:if></td>
	<td class="col-xs-12 col-md-3 control-label">${post.title}</td>
	<td class="col-xs-12 col-md-2">${post.category}</td>
	<td class="col-xs-12 col-md-2">${post.username}</td>
	<td class="col-xs-12 col-md-1">${post.postType}</td>
	<td class="col-xs-12 col-md-1">${post.accessLevel}</td>
	<td class="col-xs-12 col-md-2"><span class="expand"> <c:if
				test="${(isAdmin == true) || (post.userid == loggedInUserID)}">
				<a href="/clubhub/admin/EditPost.jsp?postID=${post.id}"
					class="btn btn-primary btn-xs">Edit</a>
			</c:if> <a href="/clubhub/Post.jsp?postID=${post.id}"
			class="btn btn-primary btn-xs">More</a>
	</span></td>
</tr>

