<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Rivendell Curling Club</title>
		<link href="style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<a href="#" class="pull-left"><img src="images/rcc.png" id="rcc_circle_logo"></a>
					<a class="navbar-brand" href="Home">Message Board</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="/comp3095/assignment2/Home">Home</a></li>
						<li><a href="/comp3095/assignment2/Posts.jsp">Posts</a></li>
						<li><a href="/comp3095/assignment2/AdminController">Admin</a></li>
					</ul>

				</div>
			    <div class="collapse navbar-collapse navbar-right">
					<c:choose>
						<c:when test="${empty sessionScope.isLoggedIn}">
							<p class="navbar-text navbar-right">Not logged in</span></p>
						</c:when>
						<c:otherwise>
							<p class="navbar-text navbar-right"><a href="/comp3095/assignment2/Logout" class="navbar-link">${sessionScope.userName }</a><span class="caret"></span></p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</nav>
		<div class="container" role="main">