<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 18, 2016
	Description: ListGames.jsp
 --%>

<% request.setAttribute("thisPage", "Manage Season"); %>
<%@ page import="utilities.SeasonDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
	<c:if test="${isAdmin == false}">
		<c:redirect url="index.jsp" />
	</c:if>
<% SeasonDao season = new SeasonDao();
//Object id = session.getAttribute("loggedInUserID");
//String str = id.toString();
//int userID = Integer.parseInt(str);
String seasonID = request.getParameter("seasonID");
System.out.println("The current season ID is: " + seasonID);
season.listOpenSeasons(request);

%>
<c:if test="${!empty errorString}">
	<div class="alert alert-danger" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		<span class="sr-only">Error:</span> ${errorString }
	</div>
</c:if>

<div class="col-md-7">
	<table class="table table-striped">
		<c:forEach items="${seasons}" var="season">
			<tr>	
				<td>S ${season.id}: ${season.year }-${season.gender }-${season.season } <b>${season.startDateFullYear }</b>
				${season.dayOfWeek } ${season.startTime } (${season.duration } weeks)</td>
				<td>
					<form action="/clubhub/SeasonController" method="post" class="form"
						role="form">
						<input type="hidden" name="seasonID" value="${season.id}">
						<button class="btn btn-info btn-sm" type="submit" value="close" name="option">Close/Assign
							Players</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@ include file="/WEB-INF/footer_backend.jsp"%>