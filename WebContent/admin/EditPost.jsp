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
<%@ page import="utilities.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<%	
PostDao post = new PostDao();
String postID = request.getParameter("postID");
post.findPost(request, postID); 
%>

<div class="form-group">
	<form action="/clubhub/PostController" method="post" class="form" role="form">
		<label class="col-sm-1 control-label">
			Title
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="text" name="blogTitle" value="${post.title}">
		</div>	
		<label class="col-sm-2 control-label">
			Access Level
		</label>
		<div class="col-sm-3">
			<select class="form-control" name="accessLevel">
				<option value="1" ${accessLevel == 'Public' ? 'selected' : ''}>Public</option>
				<option value="2" ${accessLevel == 'Members' ? 'selected' : ''}>Members Only</option>
				<option value="3" ${accessLevel == 'Private' ? 'selected' : ''}>Draft</option>
<!-- 				<option value="3">Draft</option>
				<option value="1">Public</option>
				<option value="2">Members Only</option> -->
			</select>
		</div>
		<br><br>
		<div>
			<textarea id="blogContent" name="blogContent" class="form-control" rows="16">${post.content}</textarea><br><br>
		</div>
		<br>
		<label class="col-sm-2 control-label">
			Page Type
		</label>
		<div class="col-sm-3">
			<select class="form-control" name="pageType" id="editPageType">
				<option value="1" ${postType == 'Blog' ? 'selected' : ''}>Blog Post</option>
				<option value="2" ${postType == 'Static' ? 'selected' : '' }>Web Content</option>
			</select>
		</div>
		<label class="col-sm-2 control-label">
			Category
		</label>
		<div class="col-sm-3">
			<select class="form-control" name="pageCategory" id="editPageCategory" ${postType == 'Static' ? 'disabled' : ''}>
				<option value="1" ${pageCategory == 'Announcements' ? 'selected' : ''}>Announcements</option>
				<option value="2" ${pageCategory == 'Events' ? 'selected' : ''}>Events</option>
				<option value="3" ${pageCategory == 'Contests' ? 'selected' : ''}>Contests</option>
			</select>
		</div>
		<input type="hidden" name="postID" value="${post.id}">
		<input type="hidden" name="option" value="edit">
		<input class="btn btn-info" type="submit" value="Edit">
	</form>
	<form action="/clubhub/PostController" method="post">
		<input type="hidden" name="postID" value="${post.id}">
		<input type="hidden" name="option" value="delete">
		<input class="btn btn-danger" type="submit" value="Delete">
	</form>
</div>
		
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>