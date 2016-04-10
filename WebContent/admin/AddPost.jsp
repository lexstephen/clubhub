<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddPost.jsp
 --%>

<% request.setAttribute("thisPage", "Add Post"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
	<c:if test="${isAdmin == false}">
		<c:redirect url="index.jsp" />
	</c:if>
<c:if test="${!empty errorString}">
	<div class="alert alert-danger" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		<span class="sr-only">Error:</span> ${errorString }
	</div>
</c:if>
<form action="${pageContext.request.contextPath}/PostController"
	method="post" class="form" role="form">
	<div class="row">
		<div class="form-group">
			<div
				class="<c:if test="${!empty errorNewPostTitle}">has-error</c:if>">
				<label class="col-sm-1 control-label"> Title </label>
				<div class="col-sm-6">
					<input class="form-control" type="text" maxlength="50"
						name="blogTitle" value="${blogTitle}">
				</div>
			</div>
			<label class="col-sm-2 control-label"> Access Level </label>
			<div class="col-sm-3">
				<select class="form-control" name="accessLevel">
					<option value="3">Draft</option>
					<option value="2">Members Only</option>
					<option value="1">Public</option>
				</select>
			</div>
		</div>
		<br>
		<br>
	</div>
	<div class="row">
		<div class="form-group">
			<div
				class="<c:if test="${!empty errorNewPostContent}">has-error</c:if>">
				<textarea id="blogContent" name="blogContent" class="form-control"
					rows="16">${blogContent}</textarea>
			</div>
		</div>
	</div>
	<div class="row">
		<label class="col-sm-2 control-label"> Page Type </label>
		<div class="col-sm-3">
			<select class="form-control" name="pageType" id="editPageType">
				<option value="1">Blog Post</option>
				<option value="2">Web Content</option>
			</select>
		</div>
		<label class="col-sm-2 control-label"> Category </label>
		<div class="col-sm-3">
			<select class="form-control" name="pageCategory"
				id="editPageCategory">
				<option value="1">Announcements</option>
				<option value="2">Events</option>
				<option value="3">Contests</option>
			</select>
		</div>
		<button class="btn btn-info" type="submit" value="add" name="option">Submit</button>
	</div>
</form>

<%@ include file="/WEB-INF/footer_backend.jsp"%>