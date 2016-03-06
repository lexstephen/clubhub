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
		
	<form action="/clubhub/PostController" method="post" class="form" role="form">
		<table class="table table-hover sortable">
			<thead>
				<tr><th class="sorttable_nosort"></th><th class="col-xs-12 col-md-3 control-label">Title</th>
				<th class="col-xs-12 col-md-2">Category</th>
				<th class="col-xs-12 col-md-2">Author</th><th class="col-xs-12 col-md-3">Access Level</th><th class="sorttable_nosort"></th></tr>
			</thead>
			<tbody>
				<c:forEach items="${posts}" var="post">
					<%@ include file="/WEB-INF/displayAllPosts.jsp" %>			
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${(isAdmin == true)}">
			<br><br>
			
			<label class="col-sm-1 control-label">
				Access Level
			</label>
			<div class="col-sm-3">
				<select class="form-control" name="accessLevel">
					<option value="0">-- no change --</option>
					<option value="3">Draft</option>
					<option value="1">Public</option>
					<option value="2">Members Only</option>
				</select>
			</div>
			<label class="col-sm-1 control-label">
				Post Type
			</label>
			<div class="col-sm-3">
				<select class="form-control" name="pageType" id="editPageType">
					<option value="0">-- no change --</option>
					<option value="1">Blog Post</option>
					<option value="2">Web Content</option>
				</select>
			</div>
			<label class="col-sm-1 control-label">
				Category
			</label>
			<div class="col-sm-3">
				<select class="form-control" name="pageCategory" id="editPageCategory">
					<option value="0">-- no change --</option>
					<option value="1">Announcements</option>
					<option value="2">Events</option>
					<option value="3">Contests</option>
				</select>
			</div>
			<br><br><br><br>
			<div class="col-sm-3">
				<button class="btn btn-warning " type="submit" name="option" value="batchEdit">Edit Marked</button>
			</div>
			<div class="col-sm-3">
				<button class="btn btn-danger" type="submit" name="option" value="batchDelete">Delete Marked</button>
			</div>
		</c:if>
	</form>
	
<!-- 	<span class="pagination">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</span> -->
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>