<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddEditBlog.jsp
 --%>
 
<%-- Should we get the page title here? Can more of this go into the header? --%>

<% request.setAttribute("thisPage", "Add Post"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<form action="/clubhub/PostController" method="post" class="form" role="form">
	<div class="form-group">
		<label class="col-sm-1 control-label">
			Title
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="text" name="blogTitle" value="${blogTitle}">
		</div>	
		<label class="col-sm-2 control-label">
			Access Level
		</label>
		<div class="col-sm-3">
			<select class="form-control" name="accessLevel">
				<option value="3">Draft</option>
				<option value="1">Public</option>
				<option value="2">Members Only</option>
			</select>
		</div>
		<br><br>
		<div>
			<textarea id="blogContent" name="blogContent" class="form-control" rows="16"></textarea><br><br>
		</div>
		<br>
		<label class="col-sm-2 control-label">
			Page Type
		</label>
		<div class="col-sm-3">
			<select class="form-control" name="pageType">
				<option value="1">Blog Post</option>
				<option value="2">Web Content</option>
			</select>
		</div>
		<label class="col-sm-2 control-label">
			Category
		</label>
		<div class="col-sm-3">
			<select class="form-control" name="pageCategory">
				<option value="1">Announcements</option>
				<option value="2">Events</option>
				<option value="3">Contests</option>
			</select>
		</div>
		<input type="hidden" name="option" value="add">
		<input class="btn btn-info" type="submit" value="Submit">
	</div>
</form>		
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>