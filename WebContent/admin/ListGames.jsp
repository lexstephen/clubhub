<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 28, 2016
	Description: ListGames.jsp Display games from a season
 --%>
 
<% request.setAttribute("thisPage", "List of Games"); %>
<%@ page import="utilities.GameDao"%>
<%@ page import="utilities.SeasonDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<% 
GameDao games = new GameDao();   
SeasonDao seasons = new SeasonDao();

//request.setAttribute("seasonID", "2");
request.setAttribute("isAdmin", true);


System.out.println("The current season ID is: " + request.getAttribute("seasonID"));
System.out.println("The current month is: " + request.getAttribute("gameMonth"));
seasons.listSeasonIDs(request);
games.listAll(request);
%>

	<h3>Season ${seasonID}</h3>
	Select Season
	<form action="/clubhub/GameController" method="post" class="form" role="form">
		<select name="seasonID">
			<c:forEach items="${seasons}" var="season">
				<option value="${season}" ${season == seasonID ? 'selected' : ''}>${season}</option>
			</c:forEach>	
		</select>
		<input type="hidden" name="option" value="displayGames">
		<input type="submit" value="Go">
	<table><!-- <table class="table table-hover sortable"> -->
		<thead>
			<tr>
				<th class="sorttable_nosort col-md-1"></th>
				<th class="col-xs-12 col-md-3 control-label">Game ID</th>
				<th class="col-xs-12 col-md-2">Date Scheduled</th>
				<th class="sorttable_nosort col-md-2"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${games}" var="game">			
				 <tr>
				 	<td class="col-xs-12 col-md-1">
					 	<c:if test="${(isAdmin == true)}">
							<input type="checkbox" name="gameSelected" value="${game.id}">
						</c:if>
					</td>
					<td class="col-xs-12 col-md-3 control-label">${game.id}</td>
					<td class="col-xs-12 col-md-2">${game.scheduledDate}</td>
					<td class="col-xs-12 col-md-2">
						<span class="expand">
						<c:if test="${isAdmin == true}">
							<a href="/clubhub/admin/EditPost.jsp?postID=${post.id}" class="btn btn-primary btn-xs">Edit</a>
						</c:if>
							<a href="/clubhub/Post.jsp?postID=${post.id}" class="btn btn-primary btn-xs">More</a>
						</span>
					</td>
				</tr>
				
							
			</c:forEach>
		</tbody>
	</table>
	
	Select month
		<select name="gameMonth">
			<c:forEach var="i" begin="1" end="12">
			   <option value="${i}" ${i == gameMonth ? 'selected' : ''}>${i}</option>
			</c:forEach>

		</select>
		<input type="hidden" name="option" value="displayGames">
		<input type="submit" value="Go"> 
		<table><!-- <table class="table table-hover sortable"> -->
		<thead>
			<tr>
				<th class="sorttable_nosort col-md-1"></th>
				<th class="col-xs-12 col-md-3 control-label">Game ID</th>
				<th class="col-xs-12 col-md-2">Date Scheduled</th>
				<th class="sorttable_nosort col-md-2"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${games}" var="game">		
				 <c:if test="${gameMonth == game.schedMonth}">
					 <tr>
					 	<td class="col-xs-12 col-md-1">
						 	<c:if test="${(isAdmin == true)}">
								<input type="checkbox" name="gameSelected" value="${game.id}">
							</c:if>
						</td>
						<td class="col-xs-12 col-md-3 control-label">${game.id}</td>
						<td class="col-xs-12 col-md-2">${game.scheduledDate}</td>
						<td class="col-xs-12 col-md-2">
							<span class="expand">
							<c:if test="${isAdmin == true}">
								<a href="/clubhub/admin/EditPost.jsp?postID=${post.id}" class="btn btn-primary btn-xs">Edit</a>
							</c:if>
								<a href="/clubhub/Post.jsp?postID=${post.id}" class="btn btn-primary btn-xs">More</a>
							</span>
						</td>
					</tr>
				</c:if>
							
			</c:forEach>
		</tbody>
	</table>
		
	</form>

<%@ include file="/WEB-INF/footer_backend.jsp" %>