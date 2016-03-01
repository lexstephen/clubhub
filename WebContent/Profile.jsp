<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: Post.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%	
	UserDao user = new UserDao();
	user.findUser(request, request.getParameter("userID"));
	request.setAttribute("userID", request.getParameter("userID"));
	request.setAttribute("dateCreated", user.getUserAge(request, request.getParameter("userID")));
	String thisTitle = "View Profile: " + user.getName(request);
	request.setAttribute("thisPage", thisTitle); 
%>

<%@ include file="/WEB-INF/header_backend.jsp"%>

	<div class="row">
		<div class="col-xs-12">
				<img src="/clubhub/images/avatar.gif">
				<p><b>Name:</b> ${user.firstName } ${user.lastName } [${user.gender }]</p>
				<p><b>Location:</b> ${user.city }, ${user.province }, ${user.country }</p>
				<p><b>Member Since:</b> ${dateCreated }</p>
				<span class="expand">
					<a href="/clubhub/admin/EditProfile.jsp?userID=${user.userid}" class="btn btn-primary btn-xs">Edit</a>
				</span>
			<hr>
		</div>
	</div>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>
