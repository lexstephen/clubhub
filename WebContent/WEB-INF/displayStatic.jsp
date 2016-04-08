<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 03, 2016
	Description: displays custom static pages in header
 --%>

<tr>
	<td class="col-xs-12 col-md-3 control-label">${post.title}</td>
	<td class="col-xs-12 col-md-2">${post.category}</td>
	<td class="col-xs-12 col-md-2">${post.username}</td>
	<td class="col-xs-12 col-md-3">${post.accessLevel}</td>
	<td class="col-xs-12 col-md-2"><span class="expand"> <c:if
				test="${(isAdmin == true)}">
				<a
					href="${pageContext.request.contextPath}/admin/EditPost.jsp?postID=${post.id}"
					class="btn btn-primary btn-xs">Edit</a>
			</c:if> <a
			href="${pageContext.request.contextPath}/Post.jsp?postID=${post.id}"
			class="btn btn-primary btn-xs">More</a>
	</span></td>
</tr>