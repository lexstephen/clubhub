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
<%@ include file="/WEB-INF/header_public.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	
	<% 
	PostDao post1 = new PostDao();
	// findPost requires blog id and order on page to be passsed //
	post1.findPost(request, response, "1", "1");
	
	System.out.println("Post 1: " + request.getAttribute("post1"));

	/* PostDao post2 = new PostDao();
	post2.findPost(request, response, "2", "2"); */
	
	System.out.println("Post 2: " + request.getAttribute("post2"));
	%>
	
	
	
	
	<div class="row">
		<div class="col-xs-12">
			<h1>${post1.title}</h1>
			<span class="postMeta">Post Type: ${post1.postType} - Posted in ${post1.category} by ${post1.username} on Sept 7, 2015. Access level: ${post1.accessLevel}</span>
			<p>"${post1.content}".</p>
			<span class="expand">
				<a href="more">More</a><br>
			</span>
			<hr>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-12">
			<h1>Exciting Blog Post 2</h1>
			<span class="postMeta">Posted in {Schedules} by Admin on Sept 7, 2015</span>
			<p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
			<span class="expand">
				<a href="more">More</a><br>
			</span>
			<hr>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-12">
			<h1>Exciting Blog Post 3</h1>
			<span class="postMeta">Posted in {Schedules} by Admin on Sept 7, 2015</span>
			<p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
			<span class="expand">
				<a href="more">More</a><br>
			</span>
			<hr>
		</div>
	</div>
	
	<span class="pagination">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</span>
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_public.jsp" %>