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
	
	<%	PostDao post = new PostDao(); %>
	
	<%-- <% 	// findPost requires blog id and order on page to be passsed //
	PostDao post1 = new PostDao();
	post1.findPostsMain(request, response, "1", "1");

	PostDao post2 = new PostDao();
	post2.findPostsMain(request, response, "4", "2");
	
	PostDao post3 = new PostDao();
	post3.findPostsMain(request, response, "2", "3");
	%>
 --%>	
	
	
	<% 	// findPost requires blog id to be passsed //
	post.findPostsMain(request, response, "1"); %>
	<div class="row">
		<div class="col-xs-12">
			<h1>${post.title}</h1>
			<span class="postMeta">Post Type: ${post.postType} - Posted in ${post.category} by ${post.username} on Sept 7, 2015. Access level: ${post.accessLevel}</span>
			<p>"${post.content}".</p>
			<span class="expand">
				<a href="more">More</a><br>
			</span>
			<hr>
		</div>
	</div>
	
	<% post.findPostsMain(request, response, "4"); %>
	<div class="row">
		<div class="col-xs-12">
			<h1>${post.title}</h1>
			<span class="postMeta">Post Type: ${post.postType} - Posted in ${post.category} by ${post.username} on Sept 7, 2015. Access level: ${post.accessLevel}</span>
			<p>"${post.content}".</p>
			<span class="expand">
				<a href="more">More</a><br>
			</span>
			<hr>
		</div>
	</div>
	
	<% post.findPostsMain(request, response, "2"); %>
	<div class="row">
		<div class="col-xs-12">
			<h1>${post.title}</h1>
			<span class="postMeta">Post Type: ${post.postType} - Posted in ${post.category} by ${post.username} on Sept 7, 2015. Access level: ${post.accessLevel}</span>
			<p>"${post.content}".</p>
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