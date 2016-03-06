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
<% request.setAttribute("thisPage", "Showing All Updates"); %>
<%@ include file="/WEB-INF/header_public.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	
	<!---- use these attributes to test access level functionality -->
<%-- 	<% session.setAttribute("isAdmin", true); %>
	<% session.setAttribute("isLoggedIn", true); %> --%>
	<!---- end of access level testing stuff         ---------------->
	
	<%	PostDao post = new PostDao(); %>
	
	<% post.listAllBlogs(request); %> 
	
	<h1>Blog Archives</h1>
		
	<table class="table table-hover sortable">
		<thead>
			<tr><th class="col-xs-12 col-md-3 control-label">Title</th>
			<th class="col-xs-12 col-md-2">Category</th>
			<th class="col-xs-12 col-md-2">Author</th><th class="col-xs-12 col-md-3">Access Level</th><th class="sorttable_nosort"></th></tr>
		</thead>
		<tbody>
			<c:forEach items="${posts}" var="post">
				<%@ include file="/WEB-INF/displayAllBlogs.jsp" %>					
			</c:forEach>
		</tbody>
	</table>
	
<!-- 	<span class="pagination">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</span> -->
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_public.jsp" %>