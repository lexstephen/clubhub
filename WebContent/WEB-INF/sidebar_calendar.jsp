<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: January 24, 2016
	Description: displays results from CalendarBuilder.java
 --%>
 

<!-- this is the calendar public visitors would see i.e. no private information displayed -->
<%@ page import="utilities.CalendarBuilder"%>
<%
	int selectedMonth = (session.getAttribute("selectedMonth") == null ? 0 : Integer.parseInt(session.getAttribute("selectedMonth").toString()));
	int selectedYear = (session.getAttribute("selectedYear") == null ? 0 : Integer.parseInt(session.getAttribute("selectedYear").toString()));
	System.out.println("month: " + selectedMonth);
	System.out.println("year: " + selectedYear);

%>

	
  	<h3>Club Schedule</h3>

	<table class="table">
		<% 
		CalendarBuilder cal = new CalendarBuilder(request, selectedMonth, selectedYear);
		out.println(cal.toString());

		%>				
	</table>