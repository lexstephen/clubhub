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
		
	<form action="/clubhub/PostController" method="post" class="form" role="form" id="batchDelete">
		<c:forEach items="${posts}" var="post">
			<input type="checkbox" name="markedForDeletion" value="${post.id}">Delete
			<%@ include file="/WEB-INF/displayPosts.jsp" %>			
		</c:forEach>
		<input type="hidden" name="option" value="batchDelete" form="batchDelete">
		<input class="btn btn-danger" type="submit" value="Delete Marked" form="batchDelete">
	</form>
	
	<span class="pagination">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</span>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>