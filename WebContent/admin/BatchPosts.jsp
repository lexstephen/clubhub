<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: BatchPosts.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.PostDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("thisPage", "Showing All Posts"); %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	
	<%	PostDao post = new PostDao(); %>
	
	<% post.listAll(request); %>
	
	<!-- This form wont work because there are embedded forms in the included file. I dont know how to work around this.  --> 
		
	<form action="/clubhub/PostController" method="post" class="form" role="form">
		<c:forEach items="${posts}" var="post">
			<input type="checkbox" name="postSelected" value="${post.id}">
			<%@ include file="/WEB-INF/displayPosts.jsp" %>			
		</c:forEach>
		
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
		<br><br>
		<button class="btn btn-warning" type="submit" name="option" value="batchEdit">Edit Marked</button>
		<button class="btn btn-danger" type="submit" name="option" value="batchDelete">Delete Marked</button>
	</form>
	
	<span class="pagination">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</span>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>