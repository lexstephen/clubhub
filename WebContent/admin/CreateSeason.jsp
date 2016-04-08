<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 6, 2016
	Description: CreateSeason.jsp
 --%>

<% request.setAttribute("thisPage", "Create Season"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>


<form action="${pageContext.request.contextPath}/SeasonController"
	method="post" class="form" role="form">
	<div class="row form-group">
		<div class="col-md-6 col-md-offset-3 col-xs-12">
			<label for="inptSeason">Season</label> <select class="form-control"
				name="season" id="inptSeason">
				<option value="S" <c:if test="${season == 'S'}"> checked</c:if>>Spring</option>
				<option value="SM" <c:if test="${season == 'SM'}"> checked</c:if>>Summer</option>
				<option value="F" <c:if test="${season == 'F'}"> checked</c:if>>Fall</option>
				<option value="W" <c:if test="${season == 'W'}"> checked</c:if>>Winter</option>
			</select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-6 col-md-offset-3 col-xs-12">
			<label for="inputTheDate">Start Date</label> <input
				class="form-control" type="date" name="theDate" id="inputTheDate"
				value="${theDate }">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-6 col-md-offset-3 col-xs-12">
			<label for="inptStartTime">Start Time</label> <select
				class="form-control" name="startTime" id="inptStartTime">
				<option value="6" <c:if test="${startTime == 6}"> checked</c:if>>6
					AM</option>
				<option value="7" <c:if test="${startTime == 7}"> checked</c:if>>7
					AM</option>
				<option value="8" <c:if test="${startTime == 8}"> checked</c:if>>8
					AM</option>
				<option value="9" <c:if test="${startTime == 9}"> checked</c:if>>9
					AM</option>
				<option value="10" <c:if test="${startTime == 10}"> checked</c:if>>10
					AM</option>
				<option value="11" <c:if test="${startTime == 11}"> checked</c:if>>11
					AM</option>
				<option value="12" <c:if test="${startTime == 12}"> checked</c:if>>12
					PM</option>
				<option value="13" <c:if test="${startTime == 13}"> checked</c:if>>1
					PM</option>
				<option value="14" <c:if test="${startTime == 14}"> checked</c:if>>2
					PM</option>
				<option value="15" <c:if test="${startTime == 15}"> checked</c:if>>3
					PM</option>
				<option value="16" <c:if test="${startTime == 16}"> checked</c:if>>4
					PM</option>
				<option value="17" <c:if test="${startTime == 17}"> checked</c:if>>5
					PM</option>
				<option value="18" <c:if test="${startTime == 18}"> checked</c:if>>6
					PM</option>
				<option value="19" <c:if test="${startTime == 19}"> checked</c:if>>7
					PM</option>
				<option value="20" <c:if test="${startTime == 20}"> checked</c:if>>8
					PM</option>
				<option value="21" <c:if test="${startTime == 21}"> checked</c:if>>9
					PM</option>
				<option value="22" <c:if test="${startTime == 22}"> checked</c:if>>10
					PM</option>
				<option value="23" <c:if test="${startTime == 23}"> checked</c:if>>11
					PM</option>
			</select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-6 col-md-offset-3 col-xs-12">
			<label for="duration">Season Duration (weeks)</label> <input
				class="form-control" type="text" name="duration" value="${duration}">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-1 col-md-offset-3 control-label">
			<label for="inptGender">Gender</label>
		</div>
		<div class="col-md-5 control-label">
			<input type="radio" name="gender" value="M" id="inptGender"
				<c:if test="${gender == 'M'}"> checked</c:if>> Men
			&nbsp;&nbsp; <input type="radio" name="gender" value="F"
				<c:if test="${gender == 'F'}"> checked</c:if>> Women
			&nbsp;&nbsp; <input type="radio" name="gender" value="X"
				<c:if test="${gender == 'X'}"> checked</c:if>> Mixed
			&nbsp;&nbsp;
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-6 col-md-offset-3 col-xs-12">
			<button class="btn btn-info" type="submit" value="add" name="option">Submit</button>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/footer_backend.jsp"%>