<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 18, 2016
	Description: ListGames.jsp
 --%>
 
<% request.setAttribute("thisPage", "List of Games"); %>
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
%>

<form action="/clubhub/GameController" method="post" class="form" role="form">
	<h3><u>List of Games: </u> </h3>
	
	
		
		<button class="btn btn-info" type="submit" value="players" name="option">Submit</button>
	</form>




<%@ include file="/WEB-INF/footer_backend.jsp" %>