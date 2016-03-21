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
	user.getUserId(request,"viewprofile");
	String thisTitle = "View Profile: " + session.getAttribute("userFullName");
	request.setAttribute("thisPage", thisTitle); 
	InvoiceDao invoice = new InvoiceDao();
	invoice.listAllForUser(request);
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
		
	    	<img src="ImageDao?t=profile&id=${userID }" class="profile_photo">
		  
		</div>
		<div class="col-xs-6 col-xs-offset-1 jumbotron vertical-center">
			<p><b>${user.firstName } ${user.lastName }</b> [${user.gender }]</p>
			<p>${user.city }, ${user.province }</p>
			<p>Member Since ${dateCreated }</p>
		
		 	<c:if test="${(isAdmin == true) || user.userid == loggedInUserID}">	
		 		<b>Registration Info</b>
			 	<p><small>${user.streetAddress }<br>${user.city }, ${user.country }<br>${user.postalCode }</small></p>
			 	<p><small>${user.telephone }</small></p>
			 	<p><small>Birthday: ${user.dateOfBirth }</small></p>
			 	<p><small>Emergency Contact:<br>${user.emergencyContactName } ${user.emergencyContactPhoneNumber }</small></p>
		 	
			 	<span class="expand">
					<a href="/clubhub/admin/EditProfile.jsp?userID=${user.userid}" class="btn btn-info btn-primary">Edit</a>
				</span>
			</c:if>
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
	
	<c:if test="${(isAdmin == true) || (invoice.userID == loggedInUserID)}">
		<div class="row">
			<h3>Invoices</h3>
			<div class="col-xs-10 col-xs-offset-1">
				<c:choose>
					<c:when test = "${not empty invoices}">
						<form action="/clubhub/InvoiceController" method="post" class="form" role="form">
						  	<table class="table table-striped">
						    <thead>
						      <tr>
						        <th class="col-xs-12 col-md-1 checkbox"></th>
						        <th class="col-xs-12 col-md-3 control-label">Invoice ID</th>
						        <th class="col-xs-12 col-md-2">Invoice Date</th>
						        <th class="col-xs-12 col-md-2">Invoice Status</th>
						        <th class="col-xs-12 col-md-2"></th>
						      </tr>
						    </thead>
						    <tbody>
							      <c:forEach items="${invoices}" var="invoice">
										<%@ include file="/WEB-INF/displayUserInvoices.jsp" %>			
								  </c:forEach>		
						     </tbody>
						     </table>
						     <c:if test="${isAdmin == true }">
								<div class="row">
									<div class="col-sm-2 control-label">
										Invoice Status
									</div>
									<div class="col-sm-3">
										<select class="form-control" name="status">
											<option value="paid">paid</option>
											<option value="unpaid">unpaid</option>
										</select>
									</div>
									<div class="col-sm-4 control-label">
										<input type="hidden" name="profileRedirect" value="Profile.jsp?userID=${userID}">
										<button class="btn btn-warning" type="submit" name="option" value="batchEdit">Edit Marked</button>
										<button class="btn btn-danger" type="submit" name="option" value="batchDelete">Delete Marked</button>
									</div>
								</div>
							</c:if>
						</form>	
					</c:when>
					<c:otherwise><span class="text-center">No invoices found</span></c:otherwise>
				</c:choose>
			</div>		
		</div>
	</c:if>

	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>
