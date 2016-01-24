<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>ClubHub</title>
		<link href="/clubhub/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="Main">Logo goes here</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="/clubhub/Register.jsp">Register</a></li>
						<li><a href="/clubhub/Updates.jsp">Updates</a></li>
						<li><a href="/clubhub/Contact.jsp">Contact Us</a></li>
						<li><a href="/clubhub/Fees.jsp">Fees</a></li>
						<li><a href="/clubhub/Rent.jsp">Rent Us</a></li>
					</ul>

				</div>
			    <div class="collapse navbar-collapse navbar-right">
					<c:choose>
						<c:when test="${empty sessionScope.isLoggedIn}">
							<p class="navbar-text navbar-right">Not logged in</span></p>
						</c:when>
						<c:otherwise>
							<p class="navbar-text navbar-right"><a href="/clubhub/Logout" class="navbar-link">${sessionScope.userName }</a><span class="caret"></span></p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</nav>
		<div class="container" role="main">