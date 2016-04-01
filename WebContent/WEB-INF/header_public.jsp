<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="utilities.*"%>
<%	
	PreferenceDao preference = new PreferenceDao();
	preference.showPrefs(request);
%>

<%	PostDao postHead = new PostDao(); 
	
	postHead.listStatic(request); %>
<%
   Cookie cookie = null;
   Cookie[] cookies = null;
   cookies = request.getCookies();
   if( cookies != null ){
      for (int i = 0; i < cookies.length; i++){
         cookie = cookies[i];
         if ((cookies[i].getName().equals("isAdmin")) && (cookies[i].getValue().equals("true"))) {
        	 session.setAttribute("isAdmin", true);
         }         
         if ((cookies[i].getName().equals("isLoggedIn")) && (cookies[i].getValue().equals("true"))) {
        	 session.setAttribute("isLoggedIn", true);
         }
      }   
  }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><%= request.getAttribute("thisPage") %></title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
		<%@ include file="/WEB-INF/header_public_css.jsp" %>

	</head>
	<body class="frontend">	
		<nav class="navbar navbar-static-top navbar-inverse sidebar" role="navigation">
			<div class="container">
					<div class="navbar-header">
						<a href="${pageContext.request.contextPath}/Main.jsp" class="pull-left"><img src="${pageContext.request.contextPath}/ImageDao?t=image_logo" id="rcc_circle_logo"></a>
						
			            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
			                <span class="sr-only">Toggle navigation</span>
			                <span class="icon-bar"></span>
			                <span class="icon-bar"></span>
			                <span class="icon-bar"></span>
			            </button>
					</div>
					<div class="collapse navbar-collapse navbar-right">
						<form action="${pageContext.request.contextPath}/PostController" method="post" class="form-inline clearfix pushdown">
						<div class="form-group">
								<input type="text" class="form-control" name="searchTerm" placeholder="search updates" onfocus="this.placeholder = ''" onblur="this.placeholder = 'search updates'">
								<input type="submit" class="btn btn-primary btn-xs" name="option" value="search">
						</div>
						</form>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
					
						<ul class="nav navbar-nav">
							<li><a href="${pageContext.request.contextPath}/Updates.jsp">Updates</a></li>
							<c:forEach items="${posts}" var="post">
								<li><a href="${pageContext.request.contextPath}/Static.jsp?postID=${post.id}">${post.title}</a></li>					
							</c:forEach>
							<c:choose>
							<c:when test="${isLoggedIn == true}">
								<li><a href="${pageContext.request.contextPath}/admin/">Dashboard [${loggedInUserFullName }]</a></li>
								<li><a href="${pageContext.request.contextPath}/Logout">Log Out</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/Login.jsp">Login</a></li>
								<li><a href="${pageContext.request.contextPath}/Register.jsp">Register</a></li>
							</c:otherwise>
							</c:choose>
						</ul>
					</div>
			</div>
		</nav>
		<div class="container">
			<div class="row">	
				<div class="content col-md-8 col-xs-12">