<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: BatchGames.jsp
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.GameDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.setAttribute("thisPage", "Showing All Games"); %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->

<% GameDao gamedao = new GameDao(); %>
<% gamedao.listAll(request); %>

<form action="${pageContext.request.contextPath}/GameController"
	method="post" class="form" role="form">
	<table class="table table-hover sortable">
		<thead>
			<tr>
				<th class="col-md-1 sorttable_nosort"></th>
				<th class="col-md-1">Season ID</th>
				<th class="col-md-1">Game ID</th>
				<th class="col-md-1">Gender</th>
				<th class="col-md-1">Season</th>
				<th class="col-md-1">Year</th>
				<th class="col-md-1">Week</th>
				<th class="col-md-1">Day</th>
				<th class="col-md-1">Date</th>
				<th class="col-md-1 sorttable_nosort"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${games}" var="game">
				<%@ include file="/WEB-INF/displayGames.jsp"%>
			</c:forEach>
		</tbody>
	</table>
</form>

<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp"%>