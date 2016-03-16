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

	<p class="memberMeta">
		You are now logged in as ${loggedInUserFullName } 
		<c:if test="${isAdmin == true}">
			[Administrator Access]
		</c:if>
	</p>
	
	<c:if test="${isAdmin == true}">
		<%@ include file="/WEB-INF/dashboardAdmin.jsp"%>
	</c:if>
	<c:if test="${isAdmin == false}">
		<%@ include file="/WEB-INF/dashboardUser.jsp"%>
	</c:if>

<%@ include file="/WEB-INF/footer_backend.jsp" %>