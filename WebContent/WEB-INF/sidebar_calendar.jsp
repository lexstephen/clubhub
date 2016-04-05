<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: January 24, 2016
	Description: displays results from CalendarBuilder.java
 --%>
 

<!-- this is the calendar public visitors would see i.e. no private information displayed -->
<%@ page import="utilities.CalendarBuilder" %>
  	<h3 id="calendar">Club Schedule</h3>
	<table class="table">
		<% 
		CalendarBuilder cal = new CalendarBuilder(request);
		out.println(cal.toString());

		%>				
	</table>