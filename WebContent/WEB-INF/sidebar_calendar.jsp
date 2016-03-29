<!-- this is the calendar public visitors would see i.e. no private information displayed -->
<%@ page import="utilities.CalendarBuilder"%>

	
	<div class="col-sm-12">
		<h3>Club Schedule:</h3>
	</div>


	<table class="table">
		<% 
		CalendarBuilder cal = new CalendarBuilder();
		out.println(cal.toString());
		%>				
	</table>
