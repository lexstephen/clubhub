<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: January 24, 2016
	Description: Main.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_public.jsp"%>

<main style="float:left;width:80%;">
	<h3 style="text-align:center;">Exciting Blog Post</h3><hr><br>
	Posted in {Schedules} by Admin on Sept 7, 2015<br>
	<br>
	"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	<br>
	<p style="text-align:right;">
		<a href="more">More</a><br><hr>
	</p>
	
	<br><br>
	
	<h3 style="text-align:center;">Exciting Blog Post #2</h3><hr><br>
	Posted in {Schedules} by Admin on Sept 7, 2015<br>
	<br>
	"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	<br>
	<p style="text-align:right;">
		<a href="more">More</a><br><hr>
	</p>
	
	<br>
	<p style="text-align:right;">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</p>
</main>

<div class="sidebar" style="float:left;">
	<%@ include file="/WEB-INF/sidebar_public.jsp" %>
</div>
<%@ include file="/WEB-INF/footer_public.jsp" %>