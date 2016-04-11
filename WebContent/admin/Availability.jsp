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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
	<jsp:useBean id="now" class="java.util.Date"/>
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate" />
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
	<p>Games listed below are open for user registration; please select those that you are available to play. This information
		will be used by the league administrator to schedule games. When the schedule is created, you will be notified at the email address provided in your profile.
		
		<p>If a conflict arises, deselect the checkbox in question and this
		information will be provided to the administrator. Conflicts that occur after scheduling can be managed on their individual game pages.</p>

	<div class="row">
		<div class="col-xs-12 col-sm-5 col-sm-offset-1">
						<h3>Upcoming Slots <small> <input type="checkbox" name="selectall" onClick="toggleBox(this)"/> Select all?</small></h3>
			<table class="table table-striped">
				<c:choose>
					<c:when test="${!empty slot.scheduledDateWithYear ge nowDate}">
						<c:forEach items="${slots}" var="slot" >
						<c:if test="${slot.scheduledDateWithYear ge nowDate}">
							<tr>
								<td>
									<input type="checkbox" name="slots" value="${slot.id}"
				<c:forEach items="${user.slotid}" var="uslot">
					<c:if test="${(slot.id == uslot) && (slot.conflict == 0) }"> checked</c:if> 
				</c:forEach>>
								</td>
								<td>${slot.scheduledDate}</td><td>${slot.dayOfWeek}s at ${slot.time}</td><td>${slot.gender} ${slot.seasonName } ${slot.year}
								</td>
							</tr>
						</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info"><strong>No games exist for you to join!</strong></div>
					</c:otherwise>
				</c:choose>
			</table>
	<button class="btn btn-primary" type="submit" value="availability"
		name="option">Submit</button>
			</div>
			<div class="col-xs-12 col-sm-5 col-sm-offset-1">
			<h3>Past Slots</h3>
			<table class="table table-striped">
				<c:choose>
					<c:when test="${! empty slot.scheduledDateWithYear le nowDate}">
						<c:forEach items="${slots}" var="slot" >
						<c:if test="${slot.scheduledDateWithYear le nowDate}">
							<tr>
								<td>
									<input type="checkbox" name="slots" value="${slot.id}"
				<c:forEach items="${user.slotid}" var="uslot">
					<c:if test="${(slot.id == uslot) && (slot.conflict == 0) }"> checked</c:if> disabled
				</c:forEach>>
								</td>
								<td>${slot.scheduledDate}</td><td>${slot.dayOfWeek}s at ${slot.time}</td><td>${slot.gender} ${slot.seasonName } ${slot.year}
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info"><strong>No games exist for you to join!</strong></div>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</div>

</form>




<%@ include file="/WEB-INF/footer_backend.jsp"%>