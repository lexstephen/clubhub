<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="utilities.*"%>
<%	
	PreferenceDao preference = new PreferenceDao();
	preference.showPrefs(request);
%>
<html>
<head>
<title><%= request.getAttribute("thisPage") %></title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/custom.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/fileinput.min.css"
	media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.min.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/countryoptions.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/sorttable.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/invoice.js" charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/colourscheme.js"
	charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/fileinput.min.js"></script>
</head>
<body class="backend">
	<c:if test="${isLoggedIn == false || isLoggedIn == null}">
		<c:redirect url="/Main.jsp" />
	</c:if>

	<nav class="navbar navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<!--  todo: modify link to main page - modify image to pull dynamically -->
			<a href="/clubhub/Main.jsp" class="pull-left"><img
				src="/clubhub/ImageDao?t=image_small_logo"></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:if test="${isLoggedIn == true}">
					<li><a href="/clubhub/Profile.jsp?userID=${loggedInUserID }">Logged
							in as ${loggedInUserFullName }</a></li>
					<li><img
						src="/clubhub/ImageDao?t=profile&id=${loggedInUserID }"
						class="header_photo"></li>
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
				<%@ include file="/WEB-INF/sidebar_backend.jsp"%>
			</div>

			<div class="content col-sm-10 col-xs-12">
				<h1><%= request.getAttribute("thisPage") %></h1>
				<hr>
				<br>