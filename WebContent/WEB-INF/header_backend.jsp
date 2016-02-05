<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><%= request.getAttribute("thisPage") %></title>
		<link href="/clubhub/css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<nav class="navbar navbar-static-top backend">
			<div class="container">
				<div class="navbar-header">
					<a href="#" class="pull-left"><img src="/clubhub/images/rcc.png" id="rcc_circle_logo"></a>
				</div>
				<div class="collapse navbar-collapse navbar-right">
					<input type="text" name="search" placeholder="search">
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<span class="memberMeta"><img src="/clubhub/images/avatar.gif"> You are now logged in as this puppy</span>
				</div>
			</div>
		</nav>
		<div>
			<div class="container backend">
				<div class="row">
					<div class="sidebar col-sm-2 col-xs-12">
						<%@ include file="/WEB-INF/sidebar_backend.jsp" %>
					</div>
					
					<div class="content col-sm-10 col-xs-12">
					
					
					<h1><%= request.getAttribute("thisPage") %></h1>
					<hr><br>