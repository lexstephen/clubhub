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
<% GameDao game = new GameDao();
Object id = session.getAttribute("loggedInUserID");
String str = id.toString();
int userID = Integer.parseInt(str);
System.out.println("The current user ID is: " + userID);
game.findOpenGameSlots(request, userID);
%>

<form action="/clubhub/GameController" method="post" class="form" role="form">
	<h3>Please select your availibility: </h3>
	
		
		<c:forEach items="${slots}" var="game">
			<tr>
				<td class="col-xs-12 col-md-3 control-label">
				<!--Change day of week from number to name of day and add radio buttons -->
					<b>${game.dayOfWeek} at ${game.time} - ${game.scheduledDate}
				</td>
			</tr><br>		
		</c:forEach>
	</form>




<%@ include file="/WEB-INF/footer_backend.jsp" %>