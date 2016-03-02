<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: January 24, 2016
	Description: Main.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.UserDao"%>
<% request.setAttribute("thisPage", "Dashboard"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<c:if test="${isAdmin == true}">
	<h2>You are now logged in as ${loggedInUserFullName }</h2>
	<p>You are an admin, congrats</p>
	admin gets: upcoming games with conflicts, recent games, new members, last few blog posts
</c:if>
<c:if test="${isAdmin == false}">
	<h2>You are now logged in as ${loggedInUserFullName }</h2>
	<p>You are but a lowly peasant</p>
	member gets: upcoming games they are scheduled in, last four games they played, next few games - all players, last few games - all player
</c:if>

<%@ include file="/WEB-INF/footer_backend.jsp" %>