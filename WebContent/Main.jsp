<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: January 24, 2016
	Description: Main.jsp
 --%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="utilities.PostDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("thisPage", "Rivendell Curling Club"); %>
<%@ include file="/WEB-INF/header_public.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	<%	PostDao post = new PostDao(); %>

	<!-- 	 use this to test admin login. no admin login, no edit button -->
	<%-- 	<% session.setAttribute("isAdmin", true); %>
	<% session.setAttribute("isLoggedIn", true); %> --%>

	<% post.getLastBlogs(request, response); %>
	<c:forEach items="${posts}" var="post">
		<%@ include file="/WEB-INF/displayPosts.jsp" %>					
	</c:forEach>
	
	<form action="/clubhub/PostController" method="post">
		<span class="pagination">
			<button class="btn btn-primary btn-xs" name="option" value="first">&lt;&lt;</button>
			<button class="btn btn-primary btn-xs" name="option" value="previous">&lt;</button>
			<button class="btn btn-primary btn-xs" name="option" value="next">&gt;</button>
			<button class="btn btn-primary btn-xs" name="option" value="last">&gt;&gt;</button>
		</span>
	</form>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

	<%@ include file="/WEB-INF/footer_public.jsp" %>