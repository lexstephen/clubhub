<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: January 24, 2016
	Description: Main.jsp
 --%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.PostDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("thisPage", "Rivendell Curling Club"); %>
<%@ include file="/WEB-INF/header_public.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	
	<%	PostDao post = new PostDao(); %>
	

<!-- 	 use this to test admin login. no admin login, no edit button -->
<%-- 	<% session.setAttribute("isAdmin", true); %> --%> 

	
	<% String[] postIDs = post.getLastPosts(request, response); %>
	<% for (String i : postIDs) { %>
	<% post.findPost(request, i); %>
	<%@ include file="/WEB-INF/displayPosts.jsp" %>
	<% } %>
	
	<span class="pagination">
		<a href="?posts=-5">&lt;&lt;</a> | <a href="?posts=-1">&lt;</a> | <a href="?posts=1">&gt;</a> | <a href="?posts=5">&gt;&gt;</a>
	</span>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

	<%@ include file="/WEB-INF/footer_public.jsp" %>