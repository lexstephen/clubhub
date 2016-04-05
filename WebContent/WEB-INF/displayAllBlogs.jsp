<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 03, 2016
	Description: displayPosts.jsp - HTML formatting for Updates.jsp and Search.jsp
 --%>

<tr>
	<td class="col-xs-12 col-md-1 text-right">
		<c:choose>
		<c:when test="${blog.accessLevel == 'Private'}">
			<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
		</c:when>
		<c:when test="${blog.accessLevel == 'Members'}">
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
		</c:when>
	</c:choose>
	</td>
	<td class="col-xs-12 col-md-3 control-label">${blog.title}</td>
	<td class="col-xs-12 col-md-2">${blog.category}</td>
	<td class="col-xs-12 col-md-2">${blog.userFirstName}</td>
	<td class="col-xs-12 col-md-2" sorttable_customkey="${blog.postDate}">${blog.dateFormatted}</td>
	<td class="col-xs-12 col-md-2">
		<span class="expand">
			<a href="${pageContext.request.contextPath}/Post.jsp?postID=${blog.id}" class="btn btn-primary btn-xs">More</a>
		</span>
	</td>
</tr>