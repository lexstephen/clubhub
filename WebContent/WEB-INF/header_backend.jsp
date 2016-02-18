<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><%= request.getAttribute("thisPage") %></title>
		<link href="/clubhub/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/clubhub/js/jquery.min.js" charset="utf-8"></script>
		<script type="text/javascript" src="/clubhub/js/countryoptions.js" charset="utf-8"></script>
	</head>
	<body class="backend">
		<nav class="navbar navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<!--  todo: modify link to main page - modify image to pull dynamically -->
					<a href="/clubhub/Main.jsp" class="pull-left"><img src="/clubhub/images/rcc-square.png"></a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="">Logged in as this puppy</a></li>
						<li><img src="/clubhub/images/avatar.gif"></li>
						<li><a href="">Log Out</a></li>
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