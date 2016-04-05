<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.PostDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%	PostDao postLink = new PostDao(); 
	String postID = request.getParameter("postID");
	postLink.findPost(request, postID);  %>
	
	
<c:if test="${(post.accessLevel == 'Private' || post.accessLevel == 'Members') && (isLoggedIn == false || isLoggedIn == null)}">
	<c:redirect url="/Main.jsp"/>
	redirect 1
</c:if>
<c:if test="${(post.accessLevel == 'Private') && !(post.postMatchUser)}">
	<c:redirect url="/Main.jsp"/>
	redirect 2
</c:if>

<% request.setAttribute("thisPage", request.getAttribute("postTitle")); %>
<%@ include file="/WEB-INF/header_public.jsp"%>
<h2>${post.title}</h2>
<br><br>
${post.content}
<br><br> 
<div class="expand postMeta">
	<c:choose>
		<c:when test="${post.accessLevel == 'Private'}">
			| <span class="glyphicon glyphicon-lock" aria-hidden="true"></span> | 
		</c:when>
		<c:when test="${post.accessLevel == 'Members'}">
			| <span class="glyphicon glyphicon-user" aria-hidden="true"></span> | 
		</c:when>
	</c:choose>
	<c:if test="${(isAdmin == true)}">
			<a href="${pageContext.request.contextPath}/admin/EditPost.jsp?postID=${post.id}" class="btn btn-primary btn-xs">Edit</a>
	</c:if>
</div>

<%@ include file="/WEB-INF/footer_public.jsp" %>
