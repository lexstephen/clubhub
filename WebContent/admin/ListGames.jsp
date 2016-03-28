<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 18, 2016
	Description: ListGames.jsp
 --%>
 
<% request.setAttribute("thisPage", "List of Games"); %>
<%@ page import="utilities.SeasonDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% SeasonDao slot = new SeasonDao();
//Object id = session.getAttribute("loggedInUserID");
//String str = id.toString();
//int userID = Integer.parseInt(str);
String seasonID = request.getParameter("seasonID");
System.out.println("The current season ID is: " + seasonID);
slot.listSeasonWithGames(request, seasonID);
%>


	<h3><u> ${dayOfWeek} at ${time}</u> </h3>
	<form action="/clubhub/GameController" method="post" class="form" role="form">
	<c:forEach items="${slots}" var="slot">
			<tr>
				<td class="col-xs-12 col-md-3 control-label">
					<ol>
						${slot.scheduledDate} ${slot.players}
						<c:choose>
							<c:when test="${slot.status == 1}">
								<button type="submit" value="close" name="option">Close</button>
							</c:when>
							<c:otherwise>
								<button type="submit" value="switchPlayers" name="option">Switch Players</button>
							</c:otherwise>
						</c:choose>
						
						
					</ol>
				</td>
			</tr><br>
			
			
		
		</c:forEach>
	</form>
	




<%@ include file="/WEB-INF/footer_backend.jsp" %>