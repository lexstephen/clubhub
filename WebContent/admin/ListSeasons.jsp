<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 18, 2016
	Description: ListGames.jsp
 --%>
 
<% request.setAttribute("thisPage", "List of Seasons"); %>
<%@ page import="utilities.SeasonDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% SeasonDao season = new SeasonDao();
season.listSeasonWithStatus(request);
//Object id = session.getAttribute("seasonID");
//String str = id.toString();
//int seasonID = Integer.parseInt(str);
//System.out.println("The current user ID is: " + seasonID);
%>

<form action="/clubhub/GameController" method="post" class="form" role="form">
	<h3><u>List of Seasons: </u> </h3>
		
		<c:forEach items="${seasons}" var="season">
			<tr>
				<td class="col-xs-12 col-md-3 control-label">
					<a href="ListGames.jsp?seasonID=${season.id}" /> <b>&nbsp&nbsp&nbsp&nbsp&nbsp
					${season.dayOfWeek} at ${season.startTime} (${season.duration} weeks) - ${season.gender} </a></b></td>
			</tr><br>
			
			
		
		</c:forEach>
	</form>




<%@ include file="/WEB-INF/footer_backend.jsp" %>