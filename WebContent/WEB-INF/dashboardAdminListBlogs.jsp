<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="utilities.PostDao"%>

	<% PostDao post = new PostDao(); %>
	<% post.getLastBlogs(request, response); %> 
			<table class="table table-hover sortable jumbotron">
			<thead>
				<tr>
					<th class="col-xs-12 col-md-3 control-label">Title</th>
					<th class="col-xs-12 col-md-2">Author</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${posts}" var="post">
				<tr>
					<td class="col-xs-12 col-md-3 control-label"><a href="/clubhub/admin/EditPost.jsp?postID=${post.id}">${post.title}</a></td>
					<td class="col-xs-12 col-md-2">${post.username}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>