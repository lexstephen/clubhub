<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><%= request.getAttribute("thisPage") %></title>
		<link href="/clubhub/css/style.css" rel="stylesheet" type="text/css" />
		<link href="/clubhub/css/custom.css" rel="stylesheet" type="text/css" />
	</head>
	<body class="backend">
		<c:if test="${isLoggedIn == false || isLoggedIn == null}">
			<c:redirect url="/Main.jsp"/>
		</c:if>
	                       
		<nav class="navbar navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<!--  todo: modify link to main page - modify image to pull dynamically -->
					<a href="/clubhub/Main.jsp" class="pull-left"><img src="/clubhub/images/rcc-square.png"></a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
					<c:if test="${isLoggedIn == true}">
						<li><a href="/clubhub/Profile.jsp?userID=${loggedInUserID }">Logged in as ${loggedInUserFullName }</a></li>
						<li><img src="/clubhub/images/avatar.gif"></li>
						<li><a href="/clubhub/Logout">Log Out</a></li>
					</c:if>
						<li><a href="/clubhub/admin/">Dashboard Main</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container backend">
			<div class="row">
				<div class="sidebar col-sm-2 col-xs-12">
					<%@ include file="/WEB-INF/sidebar_backend.jsp" %>
				</div>
				
				<div class="content col-sm-10 col-xs-12">
					<h1><%= request.getAttribute("thisPage") %></h1>
					<hr><br>