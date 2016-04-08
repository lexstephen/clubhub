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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.setAttribute("thisPage", "Showing All Users"); %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->

<%	UserDao user = new UserDao(); %>

<% user.listAllUsers(request); %>

<!-- This form wont work because there are embedded forms in the included file. I dont know how to work around this.  -->

<form action="${pageContext.request.contextPath}/UserController"
	method="post" class="form" role="form">

	<table class="table table-hover sortable">
		<thead>
			<tr>
				<th class="col-xs-12 col-md-1 sorttable_nosort"></th>
				<th class="col-xs-12 col-md-3 control-label">Username</th>
				<th class="col-xs-12 col-md-2">User Status</th>
				<th class="col-xs-12 col-md-2">First Name</th>
				<th class="col-xs-12 col-md-2">Last Name</th>
				<th class="col-xs-12 col-md-2 sorttable_nosort"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<%@ include file="/WEB-INF/displayUsers.jsp"%>
			</c:forEach>
		</tbody>
	</table>

	<c:choose>
		<c:when test="${(isAdmin == true)}">
			<div class="row">
				<div class="col-sm-2 control-label">User Status:</div>
				<div class="col-sm-3">
					<select class="form-control" name="userStatus">
						<option value="unverified">Unverified</option>
						<option value="user">User</option>
						<option value="admin">Administrator</option>
						<option value="inactive">Inactive</option>
					</select>
				</div>
				<div class="col-sm-4">
					<button class="btn btn-warning" type="submit" name="option"
						value="batchEdit">Edit Marked</button>
				</div>
			</div>
		</c:when>
	</c:choose>

</form>
<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp"%>