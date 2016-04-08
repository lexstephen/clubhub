<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 11, 2016
	Description: Availibility.jsp
 --%>

<% request.setAttribute("thisPage", "Update Availability"); %>
<%@ page import="utilities.GameDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% GameDao slot = new GameDao();
slot.findOpenGameSlotsForUser(request);
slot.findAllOfUsersSlots(request);
%>


<form action="/clubhub/GameController" method="post" class="form"
	role="form">
	<c:if test="${!empty errorString}">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Error:</span> ${errorString }
		</div>
	</c:if>
	<c:if test="${!empty successString}">
		<div class="alert alert-success" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Success:</span> ${successString }
		</div>
	</c:if>
	<p>Select the games you are available to play. This information
		will be used by the league administrator to schedule games. If a
		conflict arises, deselect the checkbox in question and this
		information will be provided to the administrator.</p>

	<!--   -->
	<c:forEach items="${slots}" var="slot">
		<input type="checkbox" name="slots" value="${slot.id}"
			<c:forEach items="${user.slotid}" var="uslot">
				<c:if test="${(slot.id == uslot) && (slot.conflict == 0) }"> checked</c:if>
			</c:forEach>>  ${slot.gender} ${slot.seasonName } ${slot.year} ${slot.dayOfWeek} ${slot.scheduledDate} at ${slot.time}  <br>
	</c:forEach>
	<button class="btn btn-primary" type="submit" value="availability"
		name="option">Submit</button>
</form>




<%@ include file="/WEB-INF/footer_backend.jsp"%>