<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="utilities.*"%>
<%	
	PreferenceDao preference = new PreferenceDao();
	preference.showPrefs(request);
%>
<html>
	<head>
		<title><%= request.getAttribute("thisPage") %></title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
		<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0" />
	</head>
	<body class="backend">
		<c:if test="${isLoggedIn == false || isLoggedIn == null}">
			<c:redirect url="/Main.jsp"/>
		</c:if>
	                       
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a href="${pageContext.request.contextPath}/Main.jsp" class="pull-left"><img src="${pageContext.request.contextPath}/ImageDao?t=image_small_logo"></a>					
		            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
		                <span class="sr-only">Toggle navigation</span>
		                <span class="icon-bar"></span>
		                <span class="icon-bar"></span>
		                <span class="icon-bar"></span>
		            </button>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav justified">
						<c:if test="${(isAdmin == true)}">
			              <li class="dropdown">
			                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Posts <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/admin/BatchPosts.jsp">List All Posts</a></li>
									<li><a href="${pageContext.request.contextPath}/admin/AddPost.jsp">Add New</a></li>
								</ul>
			              </li>
						</c:if>
			              <li class="dropdown">
			                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Users <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/admin/BatchUsers.jsp">List All Users</a></li>
									<c:if test="${isLoggedIn == true}">
										<li><a href="${pageContext.request.contextPath}/admin/EditProfile.jsp?userID=${loggedInUserID }">Edit Profile</a></li>
										<li><a href="${pageContext.request.contextPath}/admin/Availability.jsp">Game Availability</a></li>
									</c:if>
									<c:if test="${(isAdmin == true)}">
										<li><a href="${pageContext.request.contextPath}/Register.jsp">Add New User</a></li>
									</c:if>
								</ul>
			              </li>
			              <li class="dropdown">
			                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Invoices <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/admin/BatchInvoices.jsp">List All Invoices</a></li>
									<c:if test="${(isAdmin == true)}">
										<li><a href="${pageContext.request.contextPath}/admin/AddInvoice.jsp">Add Invoice</a></li>
										<li><a href="${pageContext.request.contextPath}/admin/AddLineItems.jsp">Add Line Item</a></li>
									</c:if>
								</ul>
			              </li>
			              <li class="dropdown">
			                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Games <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/admin/DisplayGames.jsp">List All Games</a></li>
									<li><a href="${pageContext.request.contextPath}/admin/Game.jsp">View Game Details</a></li>
									<li><a href="${pageContext.request.contextPath}/admin/Game.jsp">Edit Game Details</a></li>
									<c:if test="${(isAdmin == true)}">
										<li><a href="${pageContext.request.contextPath}/admin/CreateSeason.jsp">Add New</a></li>
									</c:if>
								</ul>
			              </li>
			              <li class="dropdown">
			                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Settings <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/admin/UserGuide.jsp">User Guide</a></li>
									<c:if test="${(isAdmin == true)}">
										<li><a href="${pageContext.request.contextPath}/admin/ColourSchemes.jsp">Colour Scheme</a></li>
										<li><a href="${pageContext.request.contextPath}/admin/Preferences.jsp">Preferences</a></li>
										<li><a href="${pageContext.request.contextPath}/admin/SetPreferences.jsp">Set Default</a></li>
									</c:if>
								</ul>
			              </li>
						<li><a href="${pageContext.request.contextPath}/admin/">Dashboard</a></li>
						<c:if test="${isLoggedIn == true}">
							<li><a href="${pageContext.request.contextPath}/Profile.jsp?userID=${loggedInUserID }">Logged in as ${loggedInUserFullName }</a></li>
							<li><img src="${pageContext.request.contextPath}/ImageDao?t=profile&id=${loggedInUserID }" class="header_photo"></li>
							<li><a href="${pageContext.request.contextPath}/Logout">Log Out</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container backend">
			<div class="row">				
				<div class="content col-xs-12" style="padding-left:50px;">
					<h1><%= request.getAttribute("thisPage") %></h1>
					<hr>