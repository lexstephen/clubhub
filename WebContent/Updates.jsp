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
	
	<%	PostDao post = new PostDao(); %>
	
	<% post.listAllBlogs(request); %> 
	
	<h1>Blog Archives</h1>

	<!---- use these attributes to test access level functionality -->
	<% session.setAttribute("isAdmin", false); %>
	<% session.setAttribute("isLoggedIn", true); %>
	<!---- end of access level testing stuff         ---------------->
		
	<c:forEach items="${posts}" var="post">
		<c:if test="${post.accessLevel == 'Public'}">
			<%@ include file="/WEB-INF/displayAllPosts.jsp" %>		
		</c:if>	
		<c:if test="${(post.accessLevel == 'Members') && ((isLoggedIn == true) || (isAdmin == true))}">
			<%@ include file="/WEB-INF/displayAllPosts.jsp" %>		
		</c:if>
		<c:if test="${(post.accessLevel == 'Private') && (post.postMatchUser == true)}">
			<%@ include file="/WEB-INF/displayAllPosts.jsp" %>		
		</c:if>				
	</c:forEach>
	
	<span class="pagination">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</span>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_public.jsp" %>