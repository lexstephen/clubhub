<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.PostDao"%>

<%	PostDao postLink = new PostDao(); 
	String postID = request.getParameter("postID");
	postLink.findPost(request, postID);  %>

<% request.setAttribute("thisPage", request.getAttribute("postTitle")); %>
<%@ include file="/WEB-INF/header_public.jsp"%>
<h1>${post.title}</h1>
<br><br>
${post.content}
<br>
<h1></h1>
<%@ include file="/WEB-INF/footer_public.jsp" %>
