<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="utilities.*"%>
<%	
	PreferenceDao preference = new PreferenceDao();
	preference.showPrefs(request);
%>

<%	PostDao postHead = new PostDao(); 
	
	postHead.listStatic(request); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<body class="frontend">
	<nav class="navbar navbar-static-top sidebar">
	<div class="container">
		<div class="row">
			<div class="navbar-header">
				<a href="/clubhub/Main.jsp" class="pull-left"><img
					src="/clubhub/ImageDao?t=image_logo" id="rcc_circle_logo"></a>
			</div>
			<div class="collapse navbar-collapse navbar-right">
				<form action="/clubhub/PostController" method="post"
					class="clearfix">
					<div class="form-group inline pull-left">


						<input type="text" name="searchTerm" placeholder="search updates"
							onfocus="this.placeholder = ''"
							onblur="this.placeholder = 'search updates'"> <input
							type="submit" class="btn btn-default btn-xs" name="option"
							value="search">


					</div>
				</form>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/clubhub/Register.jsp">Register</a></li>
					<li><a href="/clubhub/Updates.jsp">Updates</a></li>
					<c:forEach items="${posts}" var="post">
						<li><a href="/clubhub/Static.jsp?postID=${post.id}">${post.title}</a></li>
					</c:forEach>
					<c:if test="${isLoggedIn == true}">
						<li><a href="/clubhub/admin/">Dashboard</a></li>
						<li><a href="/clubhub/Logout">Log Out</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="content col-sm-8 col-xs-12">