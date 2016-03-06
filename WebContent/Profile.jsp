<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: Post.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%	
	UserDao user = new UserDao();
	user.findUser(request, request.getParameter("userID"));
	
	request.setAttribute("userID", request.getParameter("userID"));
	user.getUserAge(request);
	user.getName(request,"viewprofile");
	String thisTitle = "View Profile: " + session.getAttribute("userFullName");
	request.setAttribute("thisPage", thisTitle); 
%>

<%@ include file="/WEB-INF/header_backend.jsp"%>

	<div class="row">
		<div class="col-xs-2 col-xs-offset-6">
			View another profile:
		</div>
		<div class="col-xs-3">
			<% user.listAllUsers(request); %>
			<noscript>This form requires that you have JavaScript enabled to work properly. Please enable JavaScript in your browser.</noscript>
			<form action="Profile.jsp" class="form-group"> 
				<select name="userID" class="form-control" id="inptUserID" onchange="this.form.submit()">
					<c:forEach items="${users}" var="user">
				 		<option value="${user.userid}" ${userID == user.userid ? 'selected' : ''}>${user.firstName} ${user.lastName}</option>
					</c:forEach>
				</select>
			</form>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-xs-3 col-xs-offset-1">
		<!-- 	<img src="/clubhub/images/avatar.gif" class="profile_photo"> -->
			
	                          <img src="ImageDao?id=${userID }" class="profile_photo">

	      
		</div>
		<div class="col-xs-6 col-xs-offset-1 jumbotron vertical-center">
			<p><b>${user.firstName } ${user.lastName }</b> [${user.gender }]</p>
			<p>${user.city }, ${user.province }</p>
			<p>Member Since ${dateCreated }</p>
			<span class="expand">
				<a href="/clubhub/admin/EditProfile.jsp?userID=${user.userid}" class="btn btn-primary btn-xs">Edit</a>
			</span>
		</div>
	</div>
	
	<!--  dummy data to hold eventual season stats -->
	<div class="row">
	<h3>Season Stats</h3>
		<div class="col-xs-10 col-xs-offset-1">
			  	<table class="table table-striped">
			    <thead>
			      <tr>
			        <th>Season</th>
			        <th>Games</th>
			        <th>Wins</th>
			        <th>Losses</th>
			      </tr>
			    </thead>
			    <tbody>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>13</td>
					<td>2</td>
					<td>11</td>
			      </tr>
			      <tr>
					<td>2014-Spring-${user.teamGender }</td>
					<td>14</td>
					<td>1</td>
					<td>13</td>
			      </tr>
			      <tr>
					<td>2013-Fall-${user.teamGender }</td>
					<td>14</td>
					<td>8</td>
					<td>6</td>
			      </tr>
			      <tr>
					<td>2013-Spring-${user.teamGender }</td>
					<td>14</td>
					<td>5</td>
					<td>9</td>
			      </tr>
			     </tbody>
			     </table>
		</div>
	</div>
	
	
	<!--  dummy data to hold eventual individual season stats -->
	<div class="row">
	<h3>Game Stats</h3>
		<div class="col-xs-10 col-xs-offset-1">
			  	<table class="table table-striped">
			    <thead>
			      <tr>
			        <th>Season</th>
			        <th>Game</th>
			        <th>${user.firstName }'s Points</th>
			        <th>Team Points</th>
			        <th>Outcome</th>
			      </tr>
			    </thead>
			    <tbody>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>9</td>
					<td>2</td>
					<td>2</td>
					<td>L</td>
			      </tr>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>8</td>
					<td>7</td>
					<td>14</td>
					<td>W</td>
			      </tr>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>7</td>
					<td>3</td>
					<td>4</td>
					<td>L</td>
			      </tr>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>6</td>
					<td>6</td>
					<td>8</td>
					<td>L</td>
			      </tr>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>5</td>
					<td>2</td>
					<td>7</td>
					<td>W</td>
			      </tr>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>4</td>
					<td>1</td>
					<td>5</td>
					<td>W</td>
			      </tr>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>3</td>
					<td>7</td>
					<td>13</td>
					<td>W</td>
			      </tr>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>2</td>
					<td>1</td>
					<td>3</td>
					<td>L</td>
			      </tr>
			      <tr>
					<td>2014-Fall-${user.teamGender }</td>
					<td>1</td>
					<td>5</td>
					<td>12</td>
					<td>W</td>
			      </tr>
			     </tbody>
			     </table>
		</div>
	</div>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>
