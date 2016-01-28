<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Rivendell Curling Club</title>
		<link href="style.css" rel="stylesheet" type="text/css" />
	</head>
	<body class="frontend">	
	<nav class="navbar navbar-static-top sidebar">
		<div class="container">
		<div class="row">
			<div class="navbar-header">
				<a href="/clubhub/index.jsp" class="pull-left"><img src="images/rcc.png" id="rcc_circle_logo"></a>
			</div>
			<div class="collapse navbar-collapse navbar-right">
				<form>
				  	<div class="form-group">
						<input type="text" class="form-control" name="search" placeholder="search">
					</div>
				</form>
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
		</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">	
			<div class="content col-sm-8 col-xs-12">