<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: Post.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.PostDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% PostDao post = new PostDao();
post.findPost(request, request.getParameter("postID"));
request.setAttribute("thisPage", request.getParameter("blogTitle")); %>

<%@ include file="/WEB-INF/header_public.jsp"%>

	<div class="row">
		<div class="col-xs-12">
			<h1>${post.title}</h1>
			<span class="postMeta">Post Type: ${post.postType} - Posted in ${post.category} by ${post.username} on Sept 7, 2015. Access level: ${post.accessLevel}</span>
			<p>"${post.content}"</p>
				<span class="expand">
				<c:if test="${(isAdmin == true)}">
					<a href="/clubhub/admin/EditPost.jsp?postID=${post.id}" class="btn btn-primary btn-xs">Edit</a>
				</c:if>
				</span>
			<hr>
		</div>
	</div>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_public.jsp" %>