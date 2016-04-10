<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 3, 2016
	Description: EditPost.jsp
 --%>

<% request.setAttribute("thisPage", "Edit Post"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>

	<c:if test="${isAdmin == false}">
		<c:redirect url="index.jsp" />
	</c:if>
<%	
PostDao post = new PostDao();
String postID = request.getParameter("postID");
post.findPost(request, postID); 
%>

<div class="form-group">
	<form action="${pageContext.request.contextPath}/PostController"
		method="post" class="form" role="form">
		<div class="row">
			<div
				class="<c:if test="${!empty errorNewPostTitle}">has-error</c:if>">
				<div class="col-xs-1">
					<label class="control-label"> Title </label>
				</div>
				<div class="col-xs-5">
					<input class="form-control" maxlength="50" type="text"
						name="blogTitle"
						value="<c:choose><c:when test="${!empty errorNewPostTitle}">${blogTitle}</c:when><c:otherwise>${post.title}</c:otherwise></c:choose>">
				</div>
			</div>
			<span class="col-xs-1"></span>
			<div class="col-xs-2">
				<label class="control-label"> Access Level </label>
			</div>
			<div class="col-xs-3">
				<select class="form-control" name="accessLevel" id="editAccess">
					<option value="1" ${post.accessLevel == 'Public' ? 'selected' : ''}>Public</option>
					<option value="2"
						${post.accessLevel == 'Members' ? 'selected' : ''}>Members
						Only</option>
					<option value="3"
						${post.accessLevel == 'Private' ? 'selected' : ''}>Draft</option>
				</select>
			</div>
			<br>
			<br>
		</div>
		<div class="row">
			<div class="form-group">
				<textarea id="blogContent" name="blogContent" class="form-control"
					rows="16"><c:choose>
						<c:when test="${!empty errorNewPostContent}">${blogContent}</c:when>
						<c:otherwise>${post.content}</c:otherwise>
					</c:choose></textarea>
				<br>
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-2">
				<label class="control-label"> Page Type </label>
			</div>
			<div class="col-xs-2">
				<select class="form-control" name="pageType" id="editPageType">
					<option value="1" ${post.postType == 'Blog' ? 'selected' : ''}>Blog
						Post</option>
					<option value="2" ${post.postType == 'Static' ? 'selected' : '' }>Web
						Content</option>
				</select>
			</div>
			<span class="col-xs-1"></span>
			<div class="col-xs-1">
				<label class="control-label"> Category </label>
			</div>
			<div class="col-xs-3">
				<select class="form-control" name="pageCategory"
					id="editPageCategory"
					${post.postType == 'Static' ? 'disabled' : ''}>
					<option value="1"
						${post.category == 'Announcements' ? 'selected' : ''}>Announcements</option>
					<option value="2" ${post.category == 'Events' ? 'selected' : ''}>Events</option>
					<option value="3" ${post.category == 'Contests' ? 'selected' : ''}>Contests</option>
				</select>
			</div>
			<span class="col-xs-1"></span>
			<div class="col-xs-1">
				<input type="hidden" name="postID" value="${post.id}"> <input
					type="hidden" name="option" value="edit"> <input
					class="btn btn-info" type="submit" value="Edit">
			</div>
	</form>
	<div class="col-xs-1">
		<form action="${pageContext.request.contextPath}/PostController"
			method="post">
			<input type="hidden" name="postID" value="${post.id}"> <input
				type="hidden" name="option" value="delete"> <input
				class="btn btn-danger" type="submit" value="Delete">
		</form>
	</div>
</div>
</div>


<%@ include file="/WEB-INF/footer_backend.jsp"%>