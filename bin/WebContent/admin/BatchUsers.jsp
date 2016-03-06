<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: BatchUsers.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.UserDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("thisPage", "Showing All Users"); %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	
	<%	UserDao user = new UserDao(); %>
	
	<% user.listAllUsers(request); %>
	
	<!-- This form wont work because there are embedded forms in the included file. I dont know how to work around this.  --> 
		
	<form action="/clubhub/UserController" method="post" class="form" role="form">
		<c:forEach items="${users}" var="user">
			<input type="checkbox" name="userSelected" value="${user.userid}">
			<%@ include file="/WEB-INF/displayUsers.jsp" %>			
		</c:forEach>
		
		<label class="col-sm-2 control-label">
			User Status
		</label>
		<div class="col-sm-3">
			<select class="form-control" name="userStatus">
				<option value="unverified">Unverified</option>
				<option value="user">User</option>
				<option value="admin">Administrator</option>
			</select>
		</div>
		<br><br>
		<button class="btn btn-warning" type="submit" name="option" value="batchEdit">Edit Marked</button>
		<button class="btn btn-danger" type="submit" name="option" value="batchDelete">Delete Marked</button>
	</form>
	
	<span class="pagination">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</span>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>