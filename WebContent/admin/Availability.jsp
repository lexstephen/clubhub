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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% GameDao slot = new GameDao();
Object id = session.getAttribute("loggedInUserID");
String str = id.toString();
int userID = Integer.parseInt(str);
System.out.println("The current user ID is: " + userID);
slot.findOpenGameSlots(request, userID);
slot.findAllOfUsersSlots(request);
%>

<form action="/clubhub/GameController" method="post" class="form" role="form">
	<h3>Please select your availability: </h3>

	<c:forEach items="${slots}" var="slt"> <!--  list of slots that the user can sign up for -->
		
	</c:forEach>
	
	<br>
	<br>
	<c:forEach items="${slots}" var="slot">
			<input type="checkbox" name="slots" value="${slot.id}"
		<c:forEach items="${user.slotid}" var="uslot">
			<c:choose>
				<c:when test="${slot.id == uslot }"> checked</c:when>
			</c:choose>
		</c:forEach>
		> ${slot.dayOfWeek}  ${slot.time} - ${slot.scheduledDate} ---- ${slot.id}<br>
	</c:forEach>
	<button class="btn btn-info" type="submit" value="players" name="option">Submit</button>
	</form>




<%@ include file="/WEB-INF/footer_backend.jsp" %>