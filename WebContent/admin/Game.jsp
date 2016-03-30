<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: Game.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.PostDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% GameDao game = new GameDao();
String gameID = "33";//(String)request.getAttribute("gameID");
game.findGames(request, gameID);
request.setAttribute("thisPage", "Game Details"); %>

<%@ include file="/WEB-INF/header_backend.jsp"%>
	<div style="padding-left:150px;">
			<div class="row">
				<h2 class="hang_left">Season ${game.seasonId}</h2>
				<h3 class="hang_left">Game ${game.id}</h3>
				
				<span class="postMeta">${game.scheduledDate}</span>
				<strong>Winner: TBD</strong>
			</div>
			<div class="row">
				<div class="pull-left col-sm-3">
					<h3 class="hang_left">Team A</h3>
					<%@ include file="/WEB-INF/displayGameTeam.jsp" %>			
				</div>
				<div class="pull-left col-sm-9">
					<h3 class="hang_left">Team B</h3>
					<%@ include file="/WEB-INF/displayGameTeam.jsp" %>
				</div>
				
			</div>
	</div>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>