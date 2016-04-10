<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 18, 2016
	Description: ListGames.jsp
 --%>

<% request.setAttribute("thisPage", "Manage Seasons"); %>
<%@ page import="utilities.SeasonDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% SeasonDao season = new SeasonDao();

season.listOpenSeasons(request);
season.listClosedSeasons(request);
//Object id = session.getAttribute("seasonID");
//String str = id.toString();
//int seasonID = Integer.parseInt(str);
//System.out.println("The current user ID is: " + seasonID);

%>
<c:if test="${!empty errorString}">
	<div class="alert alert-danger" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		<span class="sr-only">Error:</span> ${errorString }
	</div>
</c:if>


<form action="/clubhub/GameController" method="post" class="form"
	role="form">
	<h2>Open Seasons</h2>
	<table class="table table-hover sortable">
		<thead>
			<tr>
				<th class="col-md-1">Gender</th>
				<th class="col-md-2">Season</th>
				<th class="col-md-2">Year</th>
				<th class="col-md-2">Day</th>
				<th class="col-md-2">Date</th>
				<th class="col-md-3 sorttable_nosort"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${seasons}" var="season">
				<%@ include file="/WEB-INF/displaySeasons.jsp"%>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${!empty closedSeasons }">
	<h2>Closed Seasons</h2>
	<table class="table table-hover sortable">
		<thead>
			<tr>
				<th class="col-md-1">Gender</th>
				<th class="col-md-2">Season</th>
				<th class="col-md-1">Year</th>
				<th class="col-md-2">Day</th>
				<th class="col-md-2">Date</th>
				<th class="col-md-4 sorttable_nosort"></th>
			</tr>	
		</thead>
		<tbody>
			<c:forEach items="${closedSeasons}" var="season">
				<%@ include file="/WEB-INF/displayClosedSeasons.jsp"%>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
</form>




<%@ include file="/WEB-INF/footer_backend.jsp"%>