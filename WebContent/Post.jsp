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
request.setAttribute("thisPage", request.getAttribute("postTitle")); %>

<%@ include file="/WEB-INF/header_public.jsp"%>
	<c:if test="${!empty errorString}">
		<div class="alert alert-danger" role="alert">
		  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  <span class="sr-only">Error:</span>
		  ${errorString }
				<c:if test="${!empty errorString2}"><li>${ errorString2 }</li></c:if>
				<c:if test="${!empty errorString3}"><li>${ errorString3 }</li></c:if>
				<c:if test="${!empty errorString4}"><li>${ errorString4 }</li></c:if>
		</div>
	</c:if>
	<c:if test="${!empty successString}">
		<div class="alert alert-success" role="alert">
		  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  <span class="sr-only">Success:</span>
		  ${successString }
		</div>
	</c:if>
	<div class="row">
		<div class="col-xs-12">
			<h1>${post.title}</h1>
			<div class="text-justify">${post.content}</div>
				<div class="expand postMeta">
					Posted in [${post.category}] by ${post.username} | 
						<c:choose>
							<c:when test="${post.accessLevel == 'Private'}">
								<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> | 
							</c:when>
							<c:when test="${post.accessLevel == 'Members'}">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span> | 
							</c:when>
						</c:choose>
					${post.postDate}
			<c:if test="${(isAdmin == true)}">
					<a href="${pageContext.request.contextPath}/admin/EditPost.jsp?postID=${post.id}" class="btn btn-primary btn-xs">Edit</a>
			</c:if>
							</div>
			<hr>
		</div>
	</div>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->
 
<%@ include file="/WEB-INF/footer_public.jsp" %>